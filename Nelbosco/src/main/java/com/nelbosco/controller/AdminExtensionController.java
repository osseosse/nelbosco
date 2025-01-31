package com.nelbosco.controller;

import java.time.Duration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nelbosco.constant.IpPolicy;
import com.nelbosco.constant.Method;
import com.nelbosco.domain.IpMonitorDTO;
import com.nelbosco.domain.NoticeDTO;
import com.nelbosco.domain.ReleaseDTO;
import com.nelbosco.mapper.IpMonitorMapper;
import com.nelbosco.service.AdminMusicService;
import com.nelbosco.service.AdminNoticeService;
import com.nelbosco.service.AdminReleaseService;
import com.nelbosco.service.AdminService;
import com.nelbosco.service.BoardService;
import com.nelbosco.service.ReservationService;
import com.nelbosco.util.UiUtils;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AdminExtensionController extends UiUtils{
	
	@Autowired
	private AdminNoticeService adminNoticeService;
	
	@Autowired
	private AdminReleaseService adminReleaseService;
	
	@Autowired
	private IpMonitorMapper ipMonitorMapper;
	   
	
	//==================새 관리자  기능 추가 ==================  
	/**
	 * 어드민 메인이자 공지사항 리스트 
	 * @param req
	 * @param model
	 * @param params
	 * @return
	 */
	@GetMapping("/admin/main")
	public String getAdminMain(HttpServletRequest req, Model model,  @ModelAttribute("params") NoticeDTO params) {		
		List<NoticeDTO> list = adminNoticeService.getAllNotices(params);
			
		model.addAttribute("notices", list);
		
		return "admin/main";	
	}
	
	/**
	 * 공지 사항 작성 화면
	 * @param req
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/notice")
	public String getMakeNotice(HttpServletRequest req, Model model) {
		
		model.addAttribute("notice", new NoticeDTO("ADMIN"));
		return "admin/postNotice";
	}
	
	/**
	 * 공지사항 상세 
	 * @param req
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/notice/detail/{id}")
	public String getNoticeContent(HttpServletRequest req, Model model, @PathVariable("id") Long id) {
		
		model.addAttribute("notice", adminNoticeService.getNoticeDetail(id));

		return "admin/detailNotice";
	}
	
	/**
	 * 공지사항 수정 화면 이동
	 * @param req
	 * @param model
	 * @param id
	 * @return
	 */	
	@GetMapping("/admin/notice/update/{id}")
	public String getUpdatedNotice(HttpServletRequest req, Model model, @PathVariable("id") Long id) {
		
		model.addAttribute("notice", adminNoticeService.getNoticeDetail(id));
		
		return "admin/updateNotice";
	}
	
	/**
	 * 공지사항 수정 
	 * @param req
	 * @param model
	 * @param dto
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/admin/notice/update")
	public String PostUpdatedNotice(HttpServletRequest req, Model model,  @ModelAttribute("notice") @Valid NoticeDTO dto,  BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			log.info("PostUpdatedNotice == > bindingResult");
	        return "admin/updateNotice"; // 오류가 있을 경우 폼 페이지로 돌아감
	    }
		
		String ipAddr = req.getHeader("X-Forwarded-For");
		if(StringUtils.isEmpty(ipAddr)) {
			ipAddr = req.getRemoteAddr();
		}
		
		dto.setModifyIp(ipAddr);
	   		
		try {
			boolean isDeleted =	adminNoticeService.noticeUpdate(dto);
			if (isDeleted == false) {
				return showMessageWithRedirect("공지글 수정에 실패하였습니다.", "/admin/notice/update/" + dto.getId(), Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/notice/update/" + dto.getId(), Method.GET, null, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/notice/update/" + dto.getId(), Method.GET, null, model);
		}
		
		return showMessageWithRedirect("공지글 수정이 완료되었습니다.", "/admin/notice/detail/"+dto.getId() , Method.GET, null, model);
	}
	
	/**
	 * 공지글 삭제
	 * @param req
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/notice/delete/{id}")
	public String deleteNotice(HttpServletRequest req, @PathVariable("id") Long id, Model model) {
		
		
		try {
			boolean isDeleted =	adminNoticeService.deleteNotice(id);
			if (isDeleted == false) {
				return showMessageWithRedirect("공지글 삭제에 실패하였습니다.", "/admin/notice/" + id, Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/notice/" + id, Method.GET, null, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/notice/" + id, Method.GET, null, model);
		}
		
		return showMessageWithRedirect("공지글 삭제가 완료되었습니다.", "/admin/main", Method.GET, null, model);

	}
	
	/**
	 * 공지글 등록
	 * @param req
	 * @param model
	 * @param dto
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/admin/notice")
	public String PostNotice(HttpServletRequest req, Model model, @ModelAttribute("notice") NoticeDTO dto,  BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
	        return "admin/postNotice"; // 오류가 있을 경우 폼 페이지로 돌아감
	    }
		
		String ipAddr = req.getHeader("X-Forwarded-For");
		if(StringUtils.isEmpty(ipAddr)) {
			ipAddr = req.getRemoteAddr();
		}
					
		// 아이피 세팅 
		dto.setCreateIp(ipAddr);		
		dto.setModifyIp(ipAddr);
		
		adminNoticeService.postNotice(dto);
		return "redirect:/admin/main";
	}
	
	
	// ======================== 뉴스 기능 ========================
	/**
	 * 뉴스글 리스트
	 * @param req
	 * @param model
	 * @param params
	 * @return
	 */
	@GetMapping("/admin/release")
	public String getAdminRelease(HttpServletRequest req, Model model, @ModelAttribute("params") ReleaseDTO params) {		
		
		List<ReleaseDTO> list = adminReleaseService.getAllReleases(params);
			
		model.addAttribute("releases", list);
		
		return "admin/release";	
	}
	
	/**
	 * 뉴스글 게시 화면
	 * @param req
	 * @param model
	 * @return
	 */
	@GetMapping ("/admin/release/post")
	public String getPostReleaseForm (HttpServletRequest req, Model model) {
		
		model.addAttribute("release", new ReleaseDTO("ADMIN"));
		return "admin/postRelease";
	}
	
	/**
	 * 뉴스글 게시 
	 * @param req
	 * @param model
	 * @param dto
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/admin/release/post")
	public String postRelease(HttpServletRequest req, Model model, @ModelAttribute("release") @Valid ReleaseDTO dto,  BindingResult bindingResult) {
			
		if (bindingResult.hasErrors()) {
	        return "admin/postRelease"; // 오류가 있을 경우 폼 페이지로 돌아감
	    }
		
		String ipAddr = req.getHeader("X-Forwarded-For");
		if(StringUtils.isEmpty(ipAddr)) {
			ipAddr = req.getRemoteAddr();
		}
			
		// 아이피 세팅 
		dto.setCreateIp(ipAddr);
		
		adminReleaseService.postRelease(dto);
				
		return "redirect:/admin/release";					
	}
	
	/**
	 * 뉵스글 상세
	 * @param req
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/release/detail/{id}")
	public String getReleaseContent(HttpServletRequest req, Model model, @PathVariable("id") Long id) {
		
		model.addAttribute("release", adminReleaseService.getReleaseDetail(id));

		return "admin/detailRelease";
	}
	
	
	/**
	 * 뉴스글 수정화면 이동 
	 * @param req
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/admin/release/update/{id}")
	public String getUpdatedRelease(HttpServletRequest req, Model model, @PathVariable("id") Long id) {
		
		model.addAttribute("release", adminReleaseService.getReleaseDetail(id));
		
		return "admin/updateRelease";
	}
	
	/**
	 * 뉴스글 수정 등록
	 * @param req
	 * @param model
	 * @param dto
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/admin/release/update")
	public String PostUpdatedRelease(HttpServletRequest req, Model model,  @ModelAttribute("release") ReleaseDTO dto,  BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
	        return "admin/updateRelease"; // 오류가 있을 경우 폼 페이지로 돌아감
	    }
		
		String ipAddr = req.getHeader("X-Forwarded-For");
		if(StringUtils.isEmpty(ipAddr)) {
			ipAddr = req.getRemoteAddr();
		}			
		// 아이피 세팅 
		dto.setModifyIp(ipAddr);
		
		try {
			boolean isDeleted =	adminReleaseService.releaseUpdate(dto);
			if (isDeleted == false) {
				return showMessageWithRedirect("뉴스글 수정에 실패하였습니다.", "/admin/release/update/" + dto.getId(), Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/release/update/" + dto.getId(), Method.GET, null, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/release/update/" + dto.getId(), Method.GET, null, model);
		}
		
		return showMessageWithRedirect("뉴스글 수정이 완료되었습니다.", "/admin/release/detail/"+dto.getId() , Method.GET, null, model);
	}
	
	/**
	 * 뉴스글 삭제 
	 * @param req
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/release/delete/{id}")
	public String deleteRelease(HttpServletRequest req, @PathVariable("id") Long id, Model model) {
		
		try {
			boolean isDeleted =	adminReleaseService.deleteRelease(id);
			if (isDeleted == false) {
				return showMessageWithRedirect("뉴스글 삭제에 실패하였습니다.", "/admin/release/detail/" + id, Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/release/detail/" + id, Method.GET, null, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/release/detail/" + id, Method.GET, null, model);
		}
		
		return showMessageWithRedirect("뉴스글 삭제가 완료되었습니다.", "/admin/release", Method.GET, null, model);
	}
	
	/**
	 * 게시글 공개여부 설정 
	 * @param type
	 * @param id
	 * @param noticeYn
	 * @return
	 */
	@PostMapping("/admin/update/openState")
	@ResponseBody
	public ResponseEntity<String> changePostOpenState(@RequestParam("type") String type,
										@RequestParam("id") Long id, @RequestParam("noticeYn") String noticeYn) {
		
		if(!"Y".equals(noticeYn) && !"N".equals(noticeYn)){
			log.info("잘못된 요청 ==>",  noticeYn);
			return ResponseEntity.badRequest().body("잘못된 요청");
		}
		
		if("notice".equals(type)) {
			adminNoticeService.changeOpenState(id, noticeYn, noticeYn);
		}else if("release".equals(type)) {		
			adminReleaseService.changeOpenState(id, noticeYn, noticeYn);		
		}
		
		return ResponseEntity.ok("success");
	}

}

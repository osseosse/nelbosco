package com.nelbosco.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nelbosco.constant.Method;
import com.nelbosco.domain.AdminDTO;
import com.nelbosco.domain.BoardDTO;
import com.nelbosco.domain.EventDTO;
import com.nelbosco.domain.MusicDTO;
import com.nelbosco.domain.ReservationDTO;
import com.nelbosco.service.AdminMusicService;
import com.nelbosco.service.AdminService;
import com.nelbosco.service.BoardService;
import com.nelbosco.service.ReservationService;
import com.nelbosco.util.UiUtils;
import com.nelbosco.adapter.GsonLocalDateTimeAdapter;

@Controller
public class AdminController extends UiUtils {

	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AdminMusicService adminMusicService;
	
	@GetMapping("admin/login")
    public String openLogin() {
        return "admin/login";
    }
	
	@GetMapping("admin/logout")
    public String openLogout(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		session.setAttribute("administrator", null);
		return showMessageWithRedirect("로그아웃 되었습니다.", "/admin/login", Method.GET, null, model);
    }
	
	@GetMapping("admin/adminList")
    public String openAdminList(HttpServletRequest req, Model model) {
		
		HttpSession session = req.getSession();
		if(session.getAttribute("administrator") == null) {
			return showMessageWithRedirect("관리자 로그인 하십시오.", "/admin/login", Method.GET, null, model);
		}
		
        return "admin/adminList";
    }
	
	@PostMapping("/admin/login" )
	public String execMail(AdminDTO dto, Model model , HttpServletRequest req, RedirectAttributes rttr) {
		int result = 0;
		HttpSession session = req.getSession();
		result = adminService.login(dto);
		
		if(result == 1) {
			session.setAttribute("administrator", dto.getId());
			return showMessageWithRedirect("관리자로 로그인 합니다.", "/admin/adminList", Method.GET, null, model);
		} else {
			session.setAttribute("administrator", null);
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/", Method.GET, null, model);
		}
    }

	@GetMapping(value = "/admin/adminBookingList")
	public String openReservationListAdmin(@ModelAttribute("params") ReservationDTO params, Model model , HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		if(session.getAttribute("administrator") == null) {
			return showMessageWithRedirect("관리자 로그인 하십시오.", "/admin/login", Method.GET, null, model);
		}
		
		List<ReservationDTO> reservationList = reservationService.getReservationList(params);
		model.addAttribute("reservationList", reservationList);

		return "admin/adminBookingList";
	}
	
	@GetMapping(value = "/admin/adminBookingDetail")
	public String openReservationDetailAdmin(@ModelAttribute("params") ReservationDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model , HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		if(session.getAttribute("administrator") == null) {
			return showMessageWithRedirect("관리자 로그인 하십시오.", "/admin/login", Method.GET, null, model);
		}
		
		if (idx == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/admin/adminBookingList", Method.GET, null, model);
		}

		ReservationDTO reservation = reservationService.getReservationDetail(idx);
		if (reservation == null || "Y".equals(reservation.getDeleteYn())) {
			return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/admin/adminBookingList", Method.GET, null, model);
		}
		model.addAttribute("reservation", reservation);
		return "admin/adminBookingDetail";
	}
	
	@GetMapping(value = "/admin/adminContactList")
	public String openContactListAdmin(@ModelAttribute("params") BoardDTO params, Model model , HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		if(session.getAttribute("administrator") == null) {
			return showMessageWithRedirect("관리자 로그인 하십시오.", "/admin/login", Method.GET, null, model);
		}
		
		List<BoardDTO> contactList = boardService.getBoardList(params);
		model.addAttribute("contactList", contactList);

		return "admin/adminContactList";
	}
	
	@GetMapping(value = "/admin/adminContactDetail")
	public String openContactDetailAdmin(@ModelAttribute("params") BoardDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model , HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		if(session.getAttribute("administrator") == null) {
			return showMessageWithRedirect("관리자 로그인 하십시오.", "/admin/login", Method.GET, null, model);
		}
		
		if (idx == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/admin/adminContactList", Method.GET, null, model);
		}

		BoardDTO contact = boardService.getBoardDetail(idx);
		if (contact == null || "Y".equals(contact.getDeleteYn())) {
			return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/admin/adminContactList", Method.GET, null, model);
		}
		model.addAttribute("contact", contact);
		return "admin/adminContactDetail";
	}
	
	@GetMapping(value = "/admin/music")
	public String openMusicAdmin(@ModelAttribute("params") MusicDTO params, Model model , HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		if(session.getAttribute("administrator") == null) {
			return showMessageWithRedirect("관리자 로그인 하십시오.", "/admin/login", Method.GET, null, model);
		}

		
		return "admin/adminMusic";
	}
	
	@RequestMapping(value = "/admin/music/calendar", method = {RequestMethod.GET, RequestMethod.POST})
	 public @ResponseBody JsonObject getCalendarList(@ModelAttribute("params") MusicDTO params, Model model) {

		JsonObject jsonObj = new JsonObject();
		List<MusicDTO> calendarModelList = adminMusicService.getAdminMusicList(params);
		if (CollectionUtils.isEmpty(calendarModelList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(calendarModelList).getAsJsonArray();
			jsonObj.add("events", jsonArr);
		}

		return jsonObj;
	 }
	
	@PostMapping(value = "/admin/music/register")
	public String registerAdminMusic(final MusicDTO params, Model model, HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		if(session.getAttribute("administrator") == null) {
			return showMessageWithRedirect("관리자 로그인 하십시오.", "/admin/login", Method.GET, null, model);
		}
		
		try {
			boolean isRegistered = adminMusicService.registerAdminMusic(params);
			if (isRegistered == false) {
				return showMessageWithRedirect("일정 등록에 실패하였습니다.", "/admin/music", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/music", Method.GET, null, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/music", Method.GET, null, model);
		}

		return showMessageWithRedirect("일정 등록이 완료되었습니다.", "/admin/music", Method.GET, null, model);
	}
	
	@RequestMapping(value = "/admin/music/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteMusic(@ModelAttribute("params") MusicDTO params, Model model, HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		if(session.getAttribute("administrator") == null) {
			return showMessageWithRedirect("관리자 로그인 하십시오.", "/admin/login", Method.GET, null, model);
		}

		
		try {
			boolean isDeleted = adminMusicService.deleteAdminMusic(params);
			if (isDeleted == false) {
				return showMessageWithRedirect("공연일정 삭제에 실패하였습니다.", "/admin/music", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/admin/music", Method.GET, null, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/admin/music", Method.GET, null, model);
		}

		return showMessageWithRedirect("공연일정 삭제가 완료되었습니다.", "/admin/music", Method.GET, null, model);
	}
}

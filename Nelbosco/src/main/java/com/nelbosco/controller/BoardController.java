package com.nelbosco.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nelbosco.constant.Method;
import com.nelbosco.domain.AttachDTO;
import com.nelbosco.domain.BoardDTO;
import com.nelbosco.domain.MailDTO;
import com.nelbosco.service.BoardService;
import com.nelbosco.service.MailService;
import com.nelbosco.util.UiUtils;

@Controller
public class BoardController extends UiUtils {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private MailService mailService;

	@GetMapping(value = "/board/write")
	public String openBoardWrite(@ModelAttribute("params") BoardDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model) {
		if (idx == null) {
			model.addAttribute("board", new BoardDTO());
		} else {
			BoardDTO board = boardService.getBoardDetail(idx);
			if (board == null || "Y".equals(board.getDeleteYn())) {
				return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/board/list", Method.GET, null, model);
			}
			model.addAttribute("board", board);

			List<AttachDTO> fileList = boardService.getAttachFileList(idx);
			model.addAttribute("fileList", fileList);
		}

		return "board/write";
	}

	@RequestMapping(value = "/board/register", method = {RequestMethod.GET, RequestMethod.POST})
	public String registerBoard(final BoardDTO params, Model model) {
		String uri = "/";
		MailDTO mailDto = new MailDTO();
		try {
			String title = "[" + params.getCategory() + "] " + "넬보스코 고객상담 문의입니다.";
			String visitDate = "";
			
			if(params.getCategory().equals("남촌빵집") || params.getCategory().equals("공연문의"))	visitDate = "";
			else if(params.getCategory().equals("레스토랑"))	visitDate = "방문날짜 : " + params.getVisitDate();
			else if(params.getCategory().equals("공연출연"))	visitDate = "출연날짜 : " + params.getVisitDate();
			else if(params.getCategory().equals("루프탑"))	visitDate = "방문날짜 : " + params.getVisitDate();
			
			boolean isRegistered = boardService.registerBoard(params);
			
			mailDto.setTitle(title);
			mailDto.setMessage("제목 : " + params.getTitle() + 
							   "\n\n고객명 : " + params.getWriter() + 
							   "\n\n이메일 : " + params.getEmail() + 
							   "\n\n전화번호 : " + params.getHp() + 
							   "\n\n내용 : " + params.getContent() +
							   "\n\n" + visitDate +
							   "\n\n\n기타 문의는 오쎄 정보전산팀으로 문의바랍니다(4926/4927)");
			
			if(params.getCategory().equals("남촌빵집") || params.getCategory().equals("공연문의") || params.getCategory().equals("공연출연")) {
				uri = "/cafe/contact/contact";
				if(params.getCategory().equals("공연문의") || params.getCategory().equals("공연출연"))	uri = "/restaurant/music/music";
				mailDto.setAddress("bncbusinessteam@vegemil.co.kr");
				mailService.mailSend(mailDto);
				mailDto.setAddress("webmaster@vegemil.co.kr");
				mailService.mailSend(mailDto);
			}
			else if(params.getCategory().equals("레스토랑")){
				uri = "/restaurant/bcontact/contact";
				mailDto.setAddress("bncbusinessteam@vegemil.co.kr");
				mailService.mailSend(mailDto);
			}
			else if(params.getCategory().equals("루프탑")){
				uri = "/rooftop/contact/contact";
				mailDto.setAddress("bncbusinessteam@vegemil.co.kr");
				mailService.mailSend(mailDto);
			}
			
			if (isRegistered == false) {
				return showMessageWithRedirect("고객상담 등록에 실패하였습니다.", uri, Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", uri, Method.GET, null, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", uri, Method.GET, null, model);
		}

		return showMessageWithRedirect("고객상담 등록이 완료되었습니다.", uri, Method.GET, null, model);
	}

	@GetMapping(value = "/board/list")
	public String openBoardList(@ModelAttribute("params") BoardDTO params, Model model) {
		List<BoardDTO> boardList = boardService.getBoardList(params);
		model.addAttribute("boardList", boardList);

		return "board/list";
	}

	@GetMapping(value = "/board/view")
	public String openBoardDetail(@ModelAttribute("params") BoardDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model) {
		if (idx == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list", Method.GET, null, model);
		}

		BoardDTO board = boardService.getBoardDetail(idx);
		if (board == null || "Y".equals(board.getDeleteYn())) {
			return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/board/list", Method.GET, null, model);
		}
		model.addAttribute("board", board);

		List<AttachDTO> fileList = boardService.getAttachFileList(idx); // 추가된 로직
		model.addAttribute("fileList", fileList); // 추가된 로직

		return "board/view";
	}

	@PostMapping(value = "/board/delete")
	public String deleteBoard(@ModelAttribute("params") BoardDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model) {
		if (idx == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list", Method.GET, null, model);
		}

		Map<String, Object> pagingParams = getPagingParams(params);
		try {
			boolean isDeleted = boardService.deleteBoard(idx);
			if (isDeleted == false) {
				return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/board/list", Method.GET, pagingParams, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/board/list", Method.GET, pagingParams, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list", Method.GET, pagingParams, model);
		}

		return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/board/list", Method.GET, pagingParams, model);
	}

	@GetMapping("/board/download")
	public void downloadAttachFile(@RequestParam(value = "idx", required = false) final Long idx, Model model, HttpServletResponse response) {

		if (idx == null) throw new RuntimeException("올바르지 않은 접근입니다.");

		AttachDTO fileInfo = boardService.getAttachDetail(idx);
		if (fileInfo == null || "Y".equals(fileInfo.getDeleteYn())) {
			throw new RuntimeException("파일 정보를 찾을 수 없습니다.");
		}

		String uploadDate = fileInfo.getInsertTime().format(DateTimeFormatter.ofPattern("yyMMdd"));
		String uploadPath = Paths.get("C:", "develop", "upload", uploadDate).toString();

		String filename = fileInfo.getOriginalName();
		File file = new File(uploadPath, fileInfo.getSaveName());

		try {
			byte[] data = FileUtils.readFileToByteArray(file);
			response.setContentType("application/octet-stream");
			response.setContentLength(data.length);
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(filename, "UTF-8") + "\";");

			response.getOutputStream().write(data);
			response.getOutputStream().flush();
			response.getOutputStream().close();

		} catch (IOException e) {
			throw new RuntimeException("파일 다운로드에 실패하였습니다.");

		} catch (Exception e) {
			throw new RuntimeException("시스템에 문제가 발생하였습니다.");
		}
	}

}

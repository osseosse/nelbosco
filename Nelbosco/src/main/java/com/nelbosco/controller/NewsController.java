package com.nelbosco.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nelbosco.adapter.GsonLocalDateTimeAdapter;
import com.nelbosco.constant.Method;
import com.nelbosco.domain.AttachDTO;
import com.nelbosco.domain.GoodsDTO;
import com.nelbosco.domain.NewsDTO;
import com.nelbosco.service.NewsService;
import com.nelbosco.util.UiUtils;

@Controller
public class NewsController extends UiUtils {

	@Autowired
	private NewsService newsService;

	@GetMapping(value = "/notice/write")
	public String openNewsWrite(@ModelAttribute("params") NewsDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model) {
		if (idx == null) {
			model.addAttribute("board", new NewsDTO());
		} else {
			NewsDTO board = newsService.getNewsDetail(idx);
			if (board == null || "Y".equals(board.getDeleteYn())) {
				return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/board/list", Method.GET, null, model);
			}
			model.addAttribute("board", board);

			List<AttachDTO> fileList = newsService.getAttachFileList(idx);
			model.addAttribute("fileList", fileList);
		}

		return "community/write";
	}

	@PostMapping(value = "/notice/register")
	public String registerNews(final NewsDTO params, final MultipartFile[] files, Model model) {
		Map<String, Object> pagingParams = getPagingParams(params);
		try {
			boolean isRegistered = newsService.registerNews(params, files);
			if (isRegistered == false) {
				return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/board/list", Method.GET, pagingParams, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/board/list", Method.GET, pagingParams, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list", Method.GET, pagingParams, model);
		}

		return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/board/list", Method.GET, pagingParams, model);
	}

	@GetMapping(value = "/cafe/community/notice")
	public String openNewsList(@ModelAttribute("params") NewsDTO params, Model model) {
		List<NewsDTO> newsList = newsService.getNewsList(params);
		model.addAttribute("newsList", newsList);

		return "community/notice";
	}
	
	@GetMapping(value = "/notice/more")
	 public @ResponseBody JsonObject getCommentList(@ModelAttribute("params") NewsDTO params, Model model) {

		JsonObject jsonObj = new JsonObject();
		List<NewsDTO> newsList = newsService.getNewsList(params);
		if (CollectionUtils.isEmpty(newsList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(newsList).getAsJsonArray();
			jsonObj.add("newsList", jsonArr);
		}

		return jsonObj;
	 }

	@GetMapping(value = "/notice/view")
	public String openNewsDetail(@ModelAttribute("params") NewsDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model) {
		if (idx == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list", Method.GET, null, model);
		}

		NewsDTO news = newsService.getNewsDetail(idx);
		if (news == null || "Y".equals(news.getDeleteYn())) {
			return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/board/list", Method.GET, null, model);
		}
		model.addAttribute("board", news);

		List<AttachDTO> fileList = newsService.getAttachFileList(idx); // 추가된 로직
		model.addAttribute("fileList", fileList); // 추가된 로직

		return "board/view";
	}

	@PostMapping(value = "/notice/delete")
	public String deleteNews(@ModelAttribute("params") NewsDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model) {
		if (idx == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list", Method.GET, null, model);
		}

		Map<String, Object> pagingParams = getPagingParams(params);
		try {
			boolean isDeleted = newsService.deleteNews(idx);
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


	@GetMapping(value = "/restaurant/bcontact/notice")
	public String openBNewsList(@ModelAttribute("params") NewsDTO params, Model model) {
		List<NewsDTO> bnewsList = newsService.getBNewsList(params);
		model.addAttribute("bnewsList", bnewsList);

		return "/bcontact/notice";
	}
	
	@GetMapping(value = "/rooftop/community/notice")
	public String openRNewsList(@ModelAttribute("params") NewsDTO params, Model model) {
		List<NewsDTO> newsList = newsService.getRNewsList(params);
		model.addAttribute("newsList", newsList);

		return "/rcommunity/notice";
	}

}

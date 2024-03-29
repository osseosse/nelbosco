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
import com.nelbosco.domain.EventDTO;
import com.nelbosco.service.EventService;
import com.nelbosco.util.UiUtils;

@Controller
public class EventController extends UiUtils {

	@Autowired
	private EventService eventService;

	@GetMapping(value = "/event/write")
	public String openEventWrite(@ModelAttribute("params") EventDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model) {
		if (idx == null) {
			model.addAttribute("board", new EventDTO());
		} else {
			EventDTO board = eventService.getEventDetail(idx);
			if (board == null || "Y".equals(board.getDeleteYn())) {
				return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/board/list", Method.GET, null, model);
			}
			model.addAttribute("board", board);

			List<AttachDTO> fileList = eventService.getAttachFileList(idx);
			model.addAttribute("fileList", fileList);
		}

		return "community/write";
	}

	@PostMapping(value = "/event/register")
	public String registerEvent(final EventDTO params, final MultipartFile[] files, Model model) {
		Map<String, Object> pagingParams = getPagingParams(params);
		try {
			boolean isRegistered = eventService.registerEvent(params, files);
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

	@GetMapping(value = "/cafe/community/event")
	public String openEventList(@ModelAttribute("params") EventDTO params, Model model) {
		List<EventDTO> eventList = eventService.getEventList(params);
		model.addAttribute("eventList", eventList);

		return "community/event";
	}
	
	@GetMapping(value = "/cafe/event/more")
	 public @ResponseBody JsonObject getCommentList(@ModelAttribute("params") EventDTO params, Model model) {

		JsonObject jsonObj = new JsonObject();
		List<EventDTO> eventList = eventService.getEventList(params);
		if (CollectionUtils.isEmpty(eventList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(eventList).getAsJsonArray();
			jsonObj.add("eventList", jsonArr);
		}

		return jsonObj;
	 }

	@GetMapping(value = "/event/view")
	public String openEventDetail(@ModelAttribute("params") EventDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model) {
		if (idx == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list", Method.GET, null, model);
		}

		EventDTO event = eventService.getEventDetail(idx);
		if (event == null || "Y".equals(event.getDeleteYn())) {
			return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/board/list", Method.GET, null, model);
		}
		model.addAttribute("board", event);

		List<AttachDTO> fileList = eventService.getAttachFileList(idx); // 추가된 로직
		model.addAttribute("fileList", fileList); // 추가된 로직

		return "board/view";
	}

	@PostMapping(value = "/event/delete")
	public String deleteEvent(@ModelAttribute("params") EventDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model) {
		if (idx == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list", Method.GET, null, model);
		}

		Map<String, Object> pagingParams = getPagingParams(params);
		try {
			boolean isDeleted = eventService.deleteEvent(idx);
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
	
	
	@GetMapping(value = "/restaurant/bcontact/event")
	public String openBEventList(@ModelAttribute("params") EventDTO params, Model model) {
		List<EventDTO> beventList = eventService.getBEventList(params);
		model.addAttribute("beventList", beventList);

		return "/bcontact/event";
	}
	
	@GetMapping(value = "/rooftop/community/event")
	public String openREventList(@ModelAttribute("params") EventDTO params, Model model) {
		List<EventDTO> eventList = eventService.getREventList(params);
		model.addAttribute("eventList", eventList);

		return "/rcommunity/event";
	}

}

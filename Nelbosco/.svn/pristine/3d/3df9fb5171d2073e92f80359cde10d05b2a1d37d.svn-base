package com.nelbosco.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.nelbosco.constant.Method;
import com.nelbosco.domain.ReservationDTO;
import com.nelbosco.service.ReservationService;
import com.nelbosco.util.UiUtils;

@Controller
public class ReservationController extends UiUtils {

	@Autowired
	private ReservationService reservationService;

	@GetMapping(value = "/cafe/reservation/write")
	public String openReservationWrite(@ModelAttribute("params") ReservationDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model) {
		if (idx == null) {
			model.addAttribute("reservation", new ReservationDTO());
		} else {
			ReservationDTO reservation = reservationService.getReservationDetail(idx);
			if (reservation == null || "Y".equals(reservation.getDeleteYn())) {
				return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/cafe/reservation/list", Method.GET, null, model);
			}
			model.addAttribute("reservation", reservation);
		}

		return "contact/booking";
	}

	@RequestMapping(value = "/cafe/reservation/register", method = {RequestMethod.GET, RequestMethod.POST})
	public String registerReservation(final ReservationDTO params, Model model) {
		try {
			boolean isRegistered = reservationService.registerReservation(params);
			if (isRegistered == false) {
				return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/cafe/contact/booking", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/cafe/contact/booking", Method.GET, null, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/cafe/contact/booking", Method.GET, null, model);
		}

		return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/cafe/contact/booking", Method.GET, null, model);
	}

	@GetMapping(value = "/cafe/contact/booking")
	public String openReservationList(@ModelAttribute("params") ReservationDTO params, Model model) {
		List<ReservationDTO> reservationList = reservationService.getReservationList(params);
		model.addAttribute("reservationList", reservationList);

		return "contact/bookingList";
	}

	@GetMapping(value = "/cafe/reservation/view")
	public String openReservationDetail(@ModelAttribute("params") ReservationDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model) {
		if (idx == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/cafe/reservation/list", Method.GET, null, model);
		}

		ReservationDTO reservation = reservationService.getReservationDetail(idx);
		if (reservation == null || "Y".equals(reservation.getDeleteYn())) {
			return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/cafe/contact/booking", Method.GET, null, model);
		}
		model.addAttribute("reservation", reservation);
		return "contact/bookingDetail";
	}
	
	@RequestMapping(value = { "/cafe/reservation/passCheck", "/cafe/reservation/passCheck/{idx}" }, method = { RequestMethod.POST, RequestMethod.PATCH })
	public @ResponseBody JsonObject passCheckReservation(@PathVariable(value = "idx", required = false) Long idx, @RequestBody final ReservationDTO params) {

		JsonObject jsonObj = new JsonObject();

		try {
			boolean isRegistered = reservationService.checkReservationByPass(params);
			jsonObj.addProperty("result", isRegistered);

		} catch (DataAccessException e) {
			jsonObj.addProperty("message", "데이터베이스 처리 과정에 문제가 발생하였습니다.");

		} catch (Exception e) {
			jsonObj.addProperty("message", "시스템에 문제가 발생하였습니다.");
		}

		return jsonObj;
	}

	@PostMapping(value = "/cafe/reservation/delete")
	public String deleteReservation(@ModelAttribute("params") ReservationDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model) {
		if (idx == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/cafe/contact/booking", Method.GET, null, model);
		}

		Map<String, Object> pagingParams = getPagingParams(params);
		try {
			boolean isDeleted = reservationService.deleteReservation(idx);
			if (isDeleted == false) {
				return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/cafe/contact/booking", Method.GET, pagingParams, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/cafe/contact/booking", Method.GET, pagingParams, model);

		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/cafe/contact/booking", Method.GET, pagingParams, model);
		}

		return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/cafe/contact/booking", Method.GET, pagingParams, model);
	}

}

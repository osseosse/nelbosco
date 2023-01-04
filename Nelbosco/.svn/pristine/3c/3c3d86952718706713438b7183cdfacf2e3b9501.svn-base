package com.nelbosco.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nelbosco.domain.ReservationDTO;
import com.nelbosco.mapper.ReservationMapper;
import com.nelbosco.paging.PaginationInfo;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationMapper reservationMapper;

	@Override
	public boolean registerReservation(ReservationDTO params) {

		int queryResult = 0;
		queryResult = reservationMapper.insertReservation(params);
		if (params.getIdx() == null) {
			queryResult = reservationMapper.insertReservation(params);
		} else {
			queryResult = reservationMapper.updateReservation(params);
		}

		return (queryResult > 0);
	}
	
	@Override
	public boolean checkReservationByPass(ReservationDTO params) {

		int queryResult = 0;
		queryResult = reservationMapper.selectReservCountByPass(params);

		return (queryResult == 1) ? true : false;
	}


	@Override
	public ReservationDTO getReservationDetail(Long idx) {
		return reservationMapper.selectReservationDetail(idx);
	}

	@Override
	public boolean deleteReservation(Long idx) {
		int queryResult = 0;

		ReservationDTO reservation = reservationMapper.selectReservationDetail(idx);

		if (reservation != null && "N".equals(reservation.getDeleteYn())) {
			queryResult = reservationMapper.deleteReservation(idx);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<ReservationDTO> getReservationList(ReservationDTO params) {
		List<ReservationDTO> reservationList = Collections.emptyList();

		int reservationTotalCount = reservationMapper.selectReservationTotalCount(params);

		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(reservationTotalCount);

		params.setPaginationInfo(paginationInfo);

		if (reservationTotalCount > 0) {
			reservationList = reservationMapper.selectReservationList(params);
		}

		return reservationList;
	}

}

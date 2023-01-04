package com.nelbosco.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nelbosco.domain.ReservationDTO;

@Mapper
public interface ReservationMapper {

	public int insertReservation(ReservationDTO params);

	public ReservationDTO selectReservationDetail(Long idx);

	public int updateReservation(ReservationDTO params);

	public int deleteReservation(Long idx);

	public List<ReservationDTO> selectReservationList(ReservationDTO params);

	public int selectReservationTotalCount(ReservationDTO params);
	
	public int selectReservCountByPass(ReservationDTO params);

}

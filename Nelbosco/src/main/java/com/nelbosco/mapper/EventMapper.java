package com.nelbosco.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nelbosco.domain.EventDTO;

@Mapper
public interface EventMapper {

	public int insertEvent(EventDTO params);

	public EventDTO selectEventDetail(Long idx);

	public int updateEvent(EventDTO params);

	public int deleteEvent(Long idx);

	public List<EventDTO> selectEventList(EventDTO params);

	public int selectEventTotalCount(EventDTO params);
	
	
	public List<EventDTO> selectBEventList(EventDTO params);

	public int selectBEventTotalCount(EventDTO params);

	
	public List<EventDTO> selectREventList(EventDTO params);

	public int selectREventTotalCount(EventDTO params);
}

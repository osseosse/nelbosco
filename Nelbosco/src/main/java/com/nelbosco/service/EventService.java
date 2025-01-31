package com.nelbosco.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nelbosco.domain.AttachDTO;
import com.nelbosco.domain.EventDTO;

public interface EventService {

	public boolean registerEvent(EventDTO params);

	public boolean registerEvent(EventDTO params, MultipartFile[] files);

	public EventDTO getEventDetail(Long idx);

	public boolean deleteEvent(Long idx);

	public List<EventDTO> getEventList(EventDTO params);

	public List<AttachDTO> getAttachFileList(Long boardIdx);

	public AttachDTO getAttachDetail(Long idx);
	
	
	public List<EventDTO> getBEventList(EventDTO params);

	public List<EventDTO> getREventList(EventDTO params);
}

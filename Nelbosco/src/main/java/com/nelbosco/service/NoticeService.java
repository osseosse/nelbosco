package com.nelbosco.service;

import java.util.List;

import com.nelbosco.domain.NoticeDTO;

public interface NoticeService {
	
	public List<NoticeDTO> retrieveNotices(NoticeDTO params);
	

}

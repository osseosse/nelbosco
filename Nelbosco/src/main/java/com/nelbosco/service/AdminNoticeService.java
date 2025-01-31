package com.nelbosco.service;

import java.util.List;

import com.nelbosco.domain.NoticeDTO;

public interface AdminNoticeService {
	
	public int postNotice(NoticeDTO dto);
	public List<NoticeDTO> getAllNotices();
	public List<NoticeDTO> getAllNotices(NoticeDTO params);
	public NoticeDTO getNoticeDetail(Long id);
	public boolean noticeUpdate(NoticeDTO dto);
	public boolean deleteNotice(Long id);
	public boolean changeOpenState(Long id, String noticeYn, String secretYn);
	
}

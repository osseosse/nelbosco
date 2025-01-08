package com.nelbosco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelbosco.domain.NoticeDTO;
import com.nelbosco.mapper.NoticeMapper;

@Service
public class AdminNoticeServiceImpl implements AdminNoticeService{
	
	@Autowired 
	NoticeMapper noticeMapper;

	@Override
	public int postNotice(NoticeDTO dto) {
		return noticeMapper.insertNotice(dto);
	}

	@Override
	public List<NoticeDTO> getAllNotices() {
		return noticeMapper.selectAllNotices();
	}

	@Override
	public NoticeDTO getNoticeDetail(Long id) {
		return noticeMapper.selectNewsDetail(id);
	}

	@Override
	public boolean noticeUpdate(NoticeDTO params) {
		Long result = noticeMapper.updateNotice(params);
		
		Long id = params.getId();
		
		if(result == null || result <0 || result != id) {
			return false;
		}
		
		return true;
	}
	
	

}

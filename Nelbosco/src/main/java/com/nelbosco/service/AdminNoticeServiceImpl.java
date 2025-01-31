package com.nelbosco.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelbosco.domain.NoticeDTO;
import com.nelbosco.mapper.NoticeMapper;
import com.nelbosco.paging.PaginationInfo;

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
	public List<NoticeDTO> getAllNotices(NoticeDTO params) {
		
		List<NoticeDTO> list = Collections.emptyList();
		
		int count = noticeMapper.selectTotalCount(params);
		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(count);
		
		params.setPaginationInfo(paginationInfo);
		
		if(count > 0) {
			list = noticeMapper.selectAllNotices(params);
		}

		return list;
	}
	

	@Override
	public NoticeDTO getNoticeDetail(Long id) {
		return noticeMapper.selectNewsDetail(id);
	}

	@Override
	public boolean noticeUpdate(NoticeDTO params) {
		Long result = noticeMapper.updateNotice(params);
		
		Long id = params.getId();
		
		if(result == null || result <0) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean deleteNotice(Long id) {
		
		Long result = noticeMapper.deleteNotice(id);
		if(result == null || result <0 ) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean changeOpenState(Long id, String noticeYn, String secretYn) {
		NoticeDTO findDTO = noticeMapper.selectNewsDetail(id);
		if(findDTO == null) {
			return false;			
		}
			
		findDTO.setNoticeYn(noticeYn);
		findDTO.setSecretYn(secretYn);
		
		Long result = noticeMapper.updateNoticeOpenState(findDTO);
		
		if(result == null || result <0 ) {
			return false;
		}
		
		return true;
		
			
	}

	


}

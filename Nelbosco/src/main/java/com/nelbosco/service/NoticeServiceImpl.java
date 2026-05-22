package com.nelbosco.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelbosco.constant.Business;
import com.nelbosco.domain.NoticeDTO;
import com.nelbosco.mapper.NoticeMapper;
import com.nelbosco.paging.PaginationInfo;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired 
	NoticeMapper noticeMapper;
	
	@Override
	public List<NoticeDTO> retrieveNotices(NoticeDTO params) {
		List<NoticeDTO> list = Collections.emptyList();
		
		int count = noticeMapper.selectTotalCount(params);
		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(count);
		
		params.setPaginationInfo(paginationInfo);
		
		if(count > 0) {
			list = noticeMapper.selectAllOpenNotices(params);
		}

		return list;
		
	}

}

package com.nelbosco.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelbosco.domain.NoticeDTO;
import com.nelbosco.domain.ReleaseDTO;
import com.nelbosco.mapper.ReleaseMapper;
import com.nelbosco.paging.PaginationInfo;

@Service
public class AdminReleaseServiceImpl implements AdminReleaseService{
	
	@Autowired 
	ReleaseMapper releaseMapper;

	@Override
	public int postRelease(ReleaseDTO dto) {
		return releaseMapper.insertRelease(dto);
	}

	@Override
	public List<ReleaseDTO> getAllReleases() {
		return releaseMapper.selectAllReleases();
	}
	

	@Override
	public List<ReleaseDTO> getAllReleases(ReleaseDTO params) {
		
		List<ReleaseDTO> list = Collections.emptyList();
		
		int count = releaseMapper.selectTotalCount(params);
		PaginationInfo paginationInfo = new PaginationInfo(params);	
		paginationInfo.setTotalRecordCount(count);
		
		params.setPaginationInfo(paginationInfo);
		
		if(count  > 0) {
			list = releaseMapper.selectAllReleases(params);
		}
		
		return list;
		
		
		
	}
	

	@Override
	public ReleaseDTO getReleaseDetail(Long id) {
		return releaseMapper.selectReleaseDetail(id);
	}

	@Override
	public boolean releaseUpdate(ReleaseDTO params) {
		Long result = releaseMapper.updateRelease(params);
		
		Long id = params.getId();
		
		if(result == null || result <0) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean deleteRelease(Long id) {
		
		Long result = releaseMapper.deleteRelease(id);
		if(result == null || result <0 ) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean changeOpenState(Long id, String noticeYn, String secretYn) {
		ReleaseDTO findDTO = releaseMapper.selectReleaseDetail(id);
		if(findDTO == null) {
			return false;			
		}
			
		findDTO.setNoticeYn(noticeYn);
		findDTO.setSecretYn(secretYn);
		
		Long result = releaseMapper.updateReleaseOpenState(findDTO);
		
		if(result == null || result <0 ) {
			return false;
		}
		
		return true;
	}

	

}

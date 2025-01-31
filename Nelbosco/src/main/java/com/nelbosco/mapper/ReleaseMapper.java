package com.nelbosco.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nelbosco.domain.NoticeDTO;
import com.nelbosco.domain.ReleaseDTO;

@Mapper
public interface ReleaseMapper {
	
	public int insertRelease(ReleaseDTO params);
	
	List<ReleaseDTO> selectAllReleases();
	
	List<ReleaseDTO> selectAllReleases(ReleaseDTO params);
	
	ReleaseDTO selectReleaseDetail(Long id);
	
	Long updateRelease(ReleaseDTO params);
	
	Long deleteRelease(Long id);
	
	int selectTotalCount(ReleaseDTO params);
	
	Long updateReleaseOpenState(ReleaseDTO params);
}

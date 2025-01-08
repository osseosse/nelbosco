package com.nelbosco.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nelbosco.domain.NoticeDTO;

@Mapper
public interface NoticeMapper {
	
	public int insertNotice(NoticeDTO params);
	
	List<NoticeDTO> selectAllNotices();
	
	NoticeDTO selectNewsDetail(Long id);
	
	Long updateNotice(NoticeDTO params);
	
	
	
	


}

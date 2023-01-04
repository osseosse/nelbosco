package com.nelbosco.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nelbosco.domain.MusicDTO;

@Mapper
public interface AdminMusicMapper {

	public int insertAdminMusic(MusicDTO params);

	public MusicDTO selectAdminMusicDetail(Long idx);

	public int updateAdminMusic(MusicDTO params);

	public boolean deleteAdminMusic(MusicDTO params);

	public List<MusicDTO> selectAdminMusicList(MusicDTO params);

	public int selectAdminMusicTotalCount(MusicDTO params);
	
	
	public List<MusicDTO> selectConcertList(MusicDTO params);
	
}

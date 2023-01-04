package com.nelbosco.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nelbosco.domain.AttachDTO;
import com.nelbosco.domain.MusicDTO;

public interface AdminMusicService {

	public boolean registerAdminMusic(MusicDTO params);

	public MusicDTO getAdminMusicDetail(Long idx);

	public boolean deleteAdminMusic(MusicDTO params);

	public List<MusicDTO> getAdminMusicList(MusicDTO params);

	public List<AttachDTO> getAttachFileList(Long boardIdx);

	public AttachDTO getAttachDetail(Long idx);
	
	
	public List<MusicDTO> getConcertList(MusicDTO params);

}

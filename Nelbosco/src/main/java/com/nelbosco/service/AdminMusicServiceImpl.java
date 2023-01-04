package com.nelbosco.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nelbosco.domain.AttachDTO;
import com.nelbosco.domain.MusicDTO;
import com.nelbosco.mapper.AdminMusicMapper;
import com.nelbosco.mapper.AttachMapper;
import com.nelbosco.paging.PaginationInfo;
import com.nelbosco.util.FileUtils;

@Service
public class AdminMusicServiceImpl implements AdminMusicService {

	@Autowired
	private AdminMusicMapper adminMusicMapper;

	@Autowired
	private AttachMapper attachMapper;

	@Autowired
	private FileUtils fileUtils;

	@Override
	public boolean registerAdminMusic(MusicDTO params) {

		int queryResult = 0;

		if (params.getIdx() == null) {
			queryResult = adminMusicMapper.insertAdminMusic(params);
		} else {
			queryResult = adminMusicMapper.updateAdminMusic(params);
		}

		return (queryResult > 0);
	}

	@Override
	public MusicDTO getAdminMusicDetail(Long idx) {
		return adminMusicMapper.selectAdminMusicDetail(idx);
	}

	@Override
	public boolean deleteAdminMusic(MusicDTO params) {
	
		return adminMusicMapper.deleteAdminMusic(params);
	}

	@Override
	public List<MusicDTO> getAdminMusicList(MusicDTO params) {
		List<MusicDTO> eventList = Collections.emptyList();

		eventList = adminMusicMapper.selectAdminMusicList(params);
		
		return eventList;
	}

	@Override
	public List<AttachDTO> getAttachFileList(Long boardIdx) {

		int fileTotalCount = attachMapper.selectAttachTotalCount(boardIdx);
		if (fileTotalCount < 1) {
			return Collections.emptyList();
		}
		return attachMapper.selectAttachList(boardIdx);
	}

	@Override
	public AttachDTO getAttachDetail(Long idx) {
		return attachMapper.selectAttachDetail(idx);
	}
	
	
	@Override
	public List<MusicDTO> getConcertList(MusicDTO params) {
		List<MusicDTO> musicList = Collections.emptyList();

		musicList = adminMusicMapper.selectConcertList(params);

		return musicList;
	}

}

package com.nelbosco.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nelbosco.domain.AttachDTO;
import com.nelbosco.domain.NewsDTO;

public interface NewsService {

	public boolean registerNews(NewsDTO params);

	public boolean registerNews(NewsDTO params, MultipartFile[] files);

	public NewsDTO getNewsDetail(Long idx);

	public boolean deleteNews(Long idx);

	public List<NewsDTO> getNewsList(NewsDTO params);

	public List<AttachDTO> getAttachFileList(Long boardIdx);

	public AttachDTO getAttachDetail(Long idx);
	
	

	public List<NewsDTO> getBNewsList(NewsDTO params);
	
	public List<NewsDTO> getRNewsList(NewsDTO params);

}

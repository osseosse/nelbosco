package com.nelbosco.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nelbosco.domain.AttachDTO;
import com.nelbosco.domain.NewsDTO;
import com.nelbosco.mapper.AttachMapper;
import com.nelbosco.mapper.NewsMapper;
import com.nelbosco.paging.PaginationInfo;
import com.nelbosco.util.FileUtils;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsMapper newsMapper;

	@Autowired
	private AttachMapper attachMapper;

	@Autowired
	private FileUtils fileUtils;

	@Override
	public boolean registerNews(NewsDTO params) {

		int queryResult = 0;

		if (params.getIdx() == null) {
			queryResult = newsMapper.insertNews(params);
		} else {
			queryResult = newsMapper.updateNews(params);

			// 파일이 추가, 삭제, 변경된 경우
			if ("Y".equals(params.getChangeYn())) {
				attachMapper.deleteAttach(params.getIdx());

				// fileIdxs에 포함된 idx를 가지는 파일의 삭제여부를 'N'으로 업데이트
				if (CollectionUtils.isEmpty(params.getFileIdxs()) == false) {
					attachMapper.undeleteAttach(params.getFileIdxs());
				}
			}
		}

		return (queryResult > 0);
	}

	@Override
	public boolean registerNews(NewsDTO params, MultipartFile[] files) {
		int queryResult = 1;

		if (registerNews(params) == false) {
			return false;
		}

		List<AttachDTO> fileList = fileUtils.uploadFiles(files, params.getIdx());
		if (CollectionUtils.isEmpty(fileList) == false) {
			queryResult = attachMapper.insertAttach(fileList);
			if (queryResult < 1) {
				queryResult = 0;
			}
		}

		return (queryResult > 0);
	}

	@Override
	public NewsDTO getNewsDetail(Long idx) {
		return newsMapper.selectNewsDetail(idx);
	}

	@Override
	public boolean deleteNews(Long idx) {
		int queryResult = 0;

		NewsDTO board = newsMapper.selectNewsDetail(idx);

		if (board != null && "N".equals(board.getDeleteYn())) {
			queryResult = newsMapper.deleteNews(idx);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<NewsDTO> getNewsList(NewsDTO params) {
		List<NewsDTO> newsList = Collections.emptyList();

		int newsTotalCount = newsMapper.selectNewsTotalCount(params);

		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(newsTotalCount);

		params.setPaginationInfo(paginationInfo);

		if (newsTotalCount > 0) {
			newsList = newsMapper.selectNewsList(params);
		}

		return newsList;
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
	public List<NewsDTO> getBNewsList(NewsDTO params) {
		List<NewsDTO> bnewsList = Collections.emptyList();

		int newsTotalCount = newsMapper.selectBNewsTotalCount(params);

		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(newsTotalCount);

		params.setPaginationInfo(paginationInfo);

		if (newsTotalCount > 0) {
			bnewsList = newsMapper.selectBNewsList(params);
		}

		return bnewsList;
	}
	
	@Override
	public List<NewsDTO> getRNewsList(NewsDTO params) {
		List<NewsDTO> newsList = Collections.emptyList();

		int newsTotalCount = newsMapper.selectRNewsTotalCount(params);

		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(newsTotalCount);

		params.setPaginationInfo(paginationInfo);

		if (newsTotalCount > 0) {
			newsList = newsMapper.selectRNewsList(params);
		}

		return newsList;
	}

}

package com.nelbosco.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nelbosco.domain.AttachDTO;
import com.nelbosco.domain.EventDTO;
import com.nelbosco.mapper.AttachMapper;
import com.nelbosco.mapper.EventMapper;
import com.nelbosco.paging.PaginationInfo;
import com.nelbosco.util.FileUtils;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventMapper eventMapper;

	@Autowired
	private AttachMapper attachMapper;

	@Autowired
	private FileUtils fileUtils;

	@Override
	public boolean registerEvent(EventDTO params) {

		int queryResult = 0;

		if (params.getIdx() == null) {
			queryResult = eventMapper.insertEvent(params);
		} else {
			queryResult = eventMapper.updateEvent(params);

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
	public boolean registerEvent(EventDTO params, MultipartFile[] files) {
		int queryResult = 1;

		if (registerEvent(params) == false) {
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
	public EventDTO getEventDetail(Long idx) {
		return eventMapper.selectEventDetail(idx);
	}

	@Override
	public boolean deleteEvent(Long idx) {
		int queryResult = 0;

		EventDTO board = eventMapper.selectEventDetail(idx);

		if (board != null && "N".equals(board.getDeleteYn())) {
			queryResult = eventMapper.deleteEvent(idx);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<EventDTO> getEventList(EventDTO params) {
		List<EventDTO> eventList = Collections.emptyList();

		int eventTotalCount = eventMapper.selectEventTotalCount(params);

		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(eventTotalCount);

		params.setPaginationInfo(paginationInfo);

		if (eventTotalCount > 0) {
			eventList = eventMapper.selectEventList(params);
		}

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
	public List<EventDTO> getBEventList(EventDTO params) {
		List<EventDTO> beventList = Collections.emptyList();

		int eventTotalCount = eventMapper.selectBEventTotalCount(params);

		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(eventTotalCount);

		params.setPaginationInfo(paginationInfo);

		if (eventTotalCount > 0) {
			beventList = eventMapper.selectBEventList(params);
		}

		return beventList;
	}

	
	@Override
	public List<EventDTO> getREventList(EventDTO params) {
		List<EventDTO> eventList = Collections.emptyList();

		int eventTotalCount = eventMapper.selectREventTotalCount(params);

		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(eventTotalCount);

		params.setPaginationInfo(paginationInfo);

		if (eventTotalCount > 0) {
			eventList = eventMapper.selectREventList(params);
		}

		return eventList;
	}
}

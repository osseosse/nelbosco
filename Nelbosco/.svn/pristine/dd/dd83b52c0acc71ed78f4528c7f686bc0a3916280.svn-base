package com.nelbosco.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nelbosco.domain.AttachDTO;
import com.nelbosco.domain.BoardDTO;

public interface BoardService {

	public boolean registerBoard(BoardDTO params);

	public boolean registerBoard(BoardDTO params, MultipartFile[] files);

	public BoardDTO getBoardDetail(Long idx);

	public boolean deleteBoard(Long idx);

	public List<BoardDTO> getBoardList(BoardDTO params);

	public List<AttachDTO> getAttachFileList(Long boardIdx);

	public AttachDTO getAttachDetail(Long idx);

}

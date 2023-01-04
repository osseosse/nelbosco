package com.nelbosco.service;

import java.util.List;

import com.nelbosco.domain.CommentDTO;

public interface CommentService {

	public boolean registerComment(CommentDTO params);

	public boolean deleteComment(Long idx);

	public List<CommentDTO> getCommentList(CommentDTO params);

}

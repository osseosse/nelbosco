package com.nelbosco.service;

import java.util.List;


import com.nelbosco.domain.ReleaseDTO;

public interface AdminReleaseService {
	
	public int postRelease(ReleaseDTO dto);
	public List<ReleaseDTO> getAllReleases();
	public List<ReleaseDTO> getAllReleases(ReleaseDTO params);
	public ReleaseDTO getReleaseDetail(Long id);
	public boolean releaseUpdate(ReleaseDTO dto);
	public boolean deleteRelease(Long id);
	public boolean changeOpenState(Long id, String noticeYn, String secretYn);
	
}

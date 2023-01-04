package com.nelbosco.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO extends CommonDTO {

	/** 번호 (PK) */
	private Long idx;

	/** 제목 */
	private String title;

	/** 내용 */
	private String content;

	/** 작성자 */
	private String writer;
	
	/** 방문일자 */
	private String visitDate;
	
	/** 이메일 */
	private String email;
	
	/** 전화번호 */
	private String hp;
	
	/** 비밀번호 */
	private String pass;

	/** 조회 수 */
	private int viewCnt;

	/** 공지 여부 */
	private String noticeYn;

	/** 비밀 여부 */
	private String secretYn;

	/** 파일 변경 여부 */
	private String changeYn;

	/** 파일 인덱스 리스트 */
	private List<Long> fileIdxs;
	
	/** 카테고리 */
	private String category;

}

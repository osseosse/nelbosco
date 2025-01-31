package com.nelbosco.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsDTO extends CommonDTO {

	private Long   idx;		/** 번호 (PK) */
	private String title;	/** 제목 */
	private String content;	/** 내용 */
	private String imgPath;	/** 이미지명 */
	private int    viewCnt;	/** 조회 수 */
	private String noticeYn;/** 공지 여부 */
	private String insertId;/** 작성자 */
	private String updateId;/** 수정자 */
	private String secretYn;/** 비밀 여부 */
	private String changeYn;/** 파일 변경 여부 */
	private List<Long> fileIdxs;/** 파일 인덱스 리스트 */
}

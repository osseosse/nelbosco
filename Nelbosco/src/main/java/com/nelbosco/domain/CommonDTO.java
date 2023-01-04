package com.nelbosco.domain;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.nelbosco.paging.Criteria;
import com.nelbosco.paging.PaginationInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonDTO extends Criteria {

	/** 페이징 정보 */
	private PaginationInfo paginationInfo;

	/** 삭제 여부 */
	private String deleteYn;

	/** 등록일 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime insertTime;

	/** 수정일 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime updateTime;

	/** 삭제일 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime deleteTime;

}

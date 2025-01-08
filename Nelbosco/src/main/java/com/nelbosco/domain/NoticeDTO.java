package com.nelbosco.domain;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.nelbosco.constant.Business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDTO {
	
	private Long id;
	
	private String writer;
	
	@NotNull@NotBlank
	private String title;
	
	private String content;
	private String imgSaved; 
	private String imgOriginal; 
	private LocalDateTime insertDate; 
	private LocalDateTime modifiedDate; 
	private String noticeYn;
	private String secretYn;
	
	private MultipartFile uploadImg;
	
	@NotNull@NotBlank
	private Business division;
}

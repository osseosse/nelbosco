package com.nelbosco.domain;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.nelbosco.constant.Business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReleaseDTO  extends CommonDTO {
	
	private Long id;
	
	private String writer;
	
	@NotNull@NotBlank
	private String title;
	
	@NotNull@NotBlank
	private String content;
	private String imgSaved; 
	private String imgOriginal; 
	private LocalDateTime insertDate; 
	private LocalDateTime modifiedDate; 
	private String noticeYn;
	private String secretYn;
	
	private MultipartFile uploadImg;
	
	@NotNull
	private Business division;
	
	private String createIp;
	private String modifyIp;

	public ReleaseDTO(String writer) {
		super();
		this.writer = writer;
	}
	

}

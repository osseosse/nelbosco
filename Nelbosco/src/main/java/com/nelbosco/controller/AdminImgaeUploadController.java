package com.nelbosco.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@RestController
public class AdminImgaeUploadController {
	
	 	@PostMapping("/upload/notice/img")
	    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
	        // 파일을 저장할 경로 지정
	        String uploadDir = "src/main/resources/static/img/upload/";
	        File dir = new File(uploadDir);
	        if (!dir.exists()) {
	            dir.mkdirs();  // 디렉토리가 없으면 생성
	        }

	        // 파일을 지정된 경로에 저장
	        try {
	            String fileName = file.getOriginalFilename();
	            File dest = new File(uploadDir + fileName);
	            file.transferTo(dest);
	            System.out.println(">>" + uploadDir + fileName);
	            // 이미지 파일 경로를 클라이언트에 전달
	            return ResponseEntity.ok().body(new UploadResponse(fileName));
	        } catch (IOException e) {
	            return ResponseEntity.status(500).body("파일 업로드 실패");
	        }
	    }

	    @Data
	    @Getter
	    @Setter
	    @AllArgsConstructor
	    static class UploadResponse {
	        private String path;
	    }
}

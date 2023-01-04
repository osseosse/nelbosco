package com.nelbosco.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.nelbosco.domain.AdminDTO;

@Service
@AllArgsConstructor
public class AdminService {
    private static final String ID = "vegemil";
    private static final String PW = "vege@pass";

    public int login(AdminDTO dto) {
    	int result = 0;
    	if (dto == null) throw new RuntimeException("올바르지 않은 접근입니다.");
    	if(dto.getId().equals(ID) && dto.getPw().equals(PW))  {
    		result = 1;
    	}

        return result;
    }
}
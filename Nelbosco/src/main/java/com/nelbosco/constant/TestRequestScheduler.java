package com.nelbosco.constant;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestRequestScheduler {

	@Autowired
	com.nelbosco.service.MailService mailService;

	
	@Scheduled(fixedDelay = 100000) // 1초마다 실행
	public void getHeapResourceInfo()
	{
	
	
	}
	
	
}

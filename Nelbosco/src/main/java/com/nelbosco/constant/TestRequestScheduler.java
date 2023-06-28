package com.nelbosco.constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestRequestScheduler {

	@Autowired
	com.nelbosco.service.MailService mailService;

	/*
	 * @Scheduled(fixedDelay = 100000) // 1초마다 실행 public void getHeapResourceInfo()
	 * {
	 * 
	 * 
	 * MemoryMXBean membean = (MemoryMXBean) ManagementFactory.getMemoryMXBean();
	 * MemoryUsage heap = membean.getHeapMemoryUsage(); MemoryUsage nonheap =
	 * membean.getNonHeapMemoryUsage(); long heapInit = heap.getInit(); // 초기 상태 메모리
	 * long heapUsed = heap.getUsed(); // 사용중인 메모리 long heapCommit =
	 * heap.getCommitted(); // JVM에 할당된 메모리 long heapMax = heap.getMax(); //총 메모리 용량
	 * long nonheapUsed = nonheap.getUsed();
	 * 
	 * System.out.println("초기 메모리 : " + heapInit); System.out.println("사용중인 메모리 : "
	 * + heapUsed); System.out.println("JVM에 할당된 메모리 : " + heapCommit);
	 * System.out.println("총 메모리 용량 : " + heapMax);
	 * System.out.println("nonheapUsed : " + nonheapUsed);
	 * 
	 * String resourceInfo = "리소스 코드 진입"; try { // java vm이 사용할수 있는 총 메모리(bytes),
	 * -Xmx long maxMem = Runtime.getRuntime().maxMemory()/1024/1024; // java vm에
	 * 할당된 총 메모리 long totalMem = Runtime.getRuntime().totalMemory()/1024/1024; //
	 * java vm이 추가로 할당 가능한 메모리 long freeMem =
	 * Runtime.getRuntime().freeMemory()/1024/1024;
	 * 
	 * // 현재 사용중인 메모리 long usedMem = totalMem - freeMem; // 퍼센트 double pct = usedMem
	 * * 100 / maxMem;
	 * 
	 * String t = "heap.current \t heap.percent \t heap.max"; String s =
	 * String.format("%s\r\n%10dmb \t %11.1f%% \t %6dmb", t, usedMem, pct, maxMem);
	 * 
	 * //String resourceInfo = "JVM에 할당된 메모리 > "+heapMax+" | 사용중인 메모리 : " + heapUsed
	 * +" | 자원 사용량 :" + usagePercent + "%"; resourceInfo = "JVM에 할당된 메모리 : " +
	 * maxMem + " | 사용중인 메모리 : " + usedMem +" | 자원 사용량 :" + pct + "%";
	 * 
	 * } catch (Exception e) { e.printStackTrace(); resourceInfo =
	 * "에러내용 : "+e.getMessage(); }finally {
	 * mailService.sendMail("hypark023@osse.co.kr",resourceInfo,resourceInfo); }
	 * 
	 * }
	 */
}

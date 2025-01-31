package com.nelbosco.domain;

import java.time.LocalDateTime;

import com.nelbosco.constant.IpPolicy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IpMonitorDTO {
	
	private int id;
	private String ipAddr;
	private IpPolicy policy;
	private LocalDateTime insertDate;
	private LocalDateTime modifyDate;
	private String note;
	
	public IpMonitorDTO(String ipAddr, IpPolicy ipPolicy) {
		
		this.ipAddr = ipAddr;
		this.policy = ipPolicy;	
	}
	
	
}

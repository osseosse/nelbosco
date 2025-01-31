package com.nelbosco.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nelbosco.domain.IpMonitorDTO;

@Mapper
public interface IpMonitorMapper {
	
	public int insertIp(IpMonitorDTO params);
	List<IpMonitorDTO> selectAllDeniedIps();
	List<IpMonitorDTO> selectAllAllowedIps();
	List<IpMonitorDTO> selectAllIps();
	


	


}

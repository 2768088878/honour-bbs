package com.ssh.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssh.dao.ZoneDao;
import com.ssh.pojo.Zone;


public interface ZoneService {

	//查询全部区
	public List<Zone> Zones();

	//查询默认区
	public Zone getDefaultZone();
}

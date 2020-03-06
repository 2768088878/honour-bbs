package com.ssh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.dao.ZoneDao;
import com.ssh.pojo.Zone;
import com.ssh.service.ZoneService;


@Service("zoneService")
@Transactional
public class ZoneServiceImpl implements ZoneService {

	@Autowired
	ZoneDao zoneDao;
	
	@Override
	public List<Zone> Zones() {
		return zoneDao.zones();
		
	}

	//查询默认区
	public Zone getDefaultZone(){
		return zoneDao.getDefaultZone();
	}
}

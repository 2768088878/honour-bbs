package com.ssh.dao;

import java.sql.SQLException;
import java.util.List;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ssh.pojo.Zone;
import com.ssh.service.ArticleService;


public interface ZoneDao {

	//查询所有的区
	public List<Zone> zones();
	
	//查询默认的区
	public Zone getDefaultZone();

}

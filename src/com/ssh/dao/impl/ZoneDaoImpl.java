package com.ssh.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssh.dao.ZoneDao;
import com.ssh.pojo.Article;
import com.ssh.pojo.Zone;

@Repository("zoneDao")
public class ZoneDaoImpl implements ZoneDao{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	
	
	@Override
	public List<Zone> zones() {
		String hql="from Zone";
		Query query=getSession().createQuery(hql);
		return query.list();
			
	}
	
	//查询默认的区
	public Zone getDefaultZone(){
		String hql="from Zone z where z.isDef = 1";
		Zone query=(Zone) getSession().createQuery(hql).uniqueResult();
		return query;
	}

}

package com.ssh.servlet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssh.pojo.Zone;
import com.ssh.service.ZoneService;

@Controller
@RequestMapping("zoneServlet")
public class ZoneServlet {
	
	@Autowired
	ZoneService zoneService;
	
	//查询所有的区
	public void Zones(Model model){
		List<Zone> zones=zoneService.Zones();
		model.addAttribute("zones", zones);
		
	}
}

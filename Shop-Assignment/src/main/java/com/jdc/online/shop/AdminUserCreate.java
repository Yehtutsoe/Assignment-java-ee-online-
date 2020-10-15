package com.jdc.online.shop;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.jdc.online.shop.model.entity.Member;
import com.jdc.online.shop.model.entity.Member.Role;
import com.jdc.online.shop.model.service.MemberService;

@Startup
@Singleton
public class AdminUserCreate {
	
	@Inject
	private MemberService service;
	
	@PostConstruct
	public void init() {
		
		Long count = service.findCount();
		
		if(count == 0) {
			Member member = new Member();
			member.setName("Admin User");
			member.setEmail("yehtut@jdc.com");
			member.setPassword("admin");
			member.setRole(Role.Admin);
			service.create(member);	
		}
	}
}

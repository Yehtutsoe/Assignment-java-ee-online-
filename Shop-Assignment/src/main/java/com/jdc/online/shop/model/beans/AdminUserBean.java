package com.jdc.online.shop.model.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.jdc.online.shop.model.entity.Member;
import com.jdc.online.shop.model.service.MemberService;

@Named
@RequestScoped
public class AdminUserBean {
	
	private String userName;
	private String role;
	private String keywords;
	
	
	@Named
	@Produces
	private List<Member> member;
	
	@Inject
	private MemberService service;
	
	public void Search() {
		member =service.Search(userName,role,keywords);
	}

	
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public List<Member> getMember() {
		return member;
	}

	public void setMember(List<Member> member) {
		this.member = member;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

}

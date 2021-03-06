package com.jdc.online.shop.model.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jdc.online.shop.model.entity.Member;
import com.jdc.online.shop.model.entity.Shop;
import com.jdc.online.shop.model.service.ShopService;

@Named
@RequestScoped
public class ShopEditBean {
	
	private Shop shop;
	
	@Inject
	private ShopService service;
	
	@PostConstruct
	public void init() {
		shop = new Shop();
		Member member = new Member();
		shop.setOwner(member);
	}
	
	public String create() {
		
		service.create(shop);
		
		return "/index?faces-redirect=true";
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

}

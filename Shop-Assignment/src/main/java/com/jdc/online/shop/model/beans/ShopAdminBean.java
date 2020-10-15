package com.jdc.online.shop.model.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jdc.online.shop.model.entity.Shop;
import com.jdc.online.shop.model.service.ShopService;

@Named
@RequestScoped
public class ShopAdminBean {
	
	private String shopName;
	private String owner;
	private String keywords;
	
	@Inject
	private ShopService service;
	
	//result for table
	
	private List<Shop> shop;
	
	public void Search() {
		shop = service.Search(shopName,owner,keywords);
	}

	public String getShopName() {
		return shopName;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public List<Shop> getShop() {
		return shop;
	}

	public void setShop(List<Shop> shop) {
		this.shop = shop;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	

}

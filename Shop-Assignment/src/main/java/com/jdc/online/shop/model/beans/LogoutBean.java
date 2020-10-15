package com.jdc.online.shop.model.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class LogoutBean {
	
	private String email;
	private String password;
	
	@Inject
	private ExternalContext externalContext;
	
	public String logOut() {
		externalContext.invalidateSession();
		return "index.xhtml?faces-redirect=true";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String name) {
		this.email = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}

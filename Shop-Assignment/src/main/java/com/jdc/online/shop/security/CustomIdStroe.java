package com.jdc.online.shop.security;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import com.jdc.online.shop.AppException;
import com.jdc.online.shop.model.entity.Member;
import com.jdc.online.shop.model.service.MemberService;

@ApplicationScoped
public class CustomIdStroe implements IdentityStore{
	
	@Inject
	private Pbkdf2PasswordHash encrypt;
	
	@Inject
	private MemberService service;
	
	@Inject
	private Event<Member> event;
	
	@Override
	public CredentialValidationResult validate(Credential credential) {
		
		UsernamePasswordCredential userNameAndPassword = (UsernamePasswordCredential) credential;
		Member member = service.findByEmail(userNameAndPassword.getCaller());
		
		if(null == member) {
			throw new AppException("There is no user with this email address");
		}
		//check email is deleted
		if(member.isDeleted()) {
			throw new AppException("Invalid Member,Please contact admin");
		}
			
			//check password
			if(!encrypt.verify(userNameAndPassword.getPasswordAsString().toCharArray(), member.getPassword())) {
				
				throw new AppException("Invalid password.Please Enter correct password.");
			}
			event.fire(member);
		
		return new CredentialValidationResult(member.getEmail(),Set.of(member.getRole().name()));
	}
	
}

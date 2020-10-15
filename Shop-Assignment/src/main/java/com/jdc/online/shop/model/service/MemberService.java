package com.jdc.online.shop.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import com.jdc.online.shop.AppException;
import com.jdc.online.shop.model.entity.Member;

@LocalBean
@Stateless
public class MemberService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private Pbkdf2PasswordHash encrypt;
	
	public void create(Member member) {
		
		//Check Email
		Long count = em.createNamedQuery("Member.findCountByEmail" ,Long.class)
						.setParameter("email",member.getEmail()).getSingleResult();
		
		if(count > 0) {
			// there is member with same email with new member
			throw new AppException("Your email has already registered");
			
		}
		
		//encrypt password
		member.setPassword(encrypt.generate(member.getPassword().toCharArray()));
		em.persist(member);
	}

	public Long findCount() {
	
		return em.createNamedQuery("Member.findCount" ,Long.class).getSingleResult();
	}

	public Member findByEmail(String email) {
		
		TypedQuery<Member> query = em.createNamedQuery("Member.findByEmail", Member.class);
		query.setParameter("email", email);
		List<Member> list =query.getResultList();
		for(Member m : list) {
			return m;
		}
		return null;
	}
	//User Management Search
	public List<Member> Search(String userName, String role, String keywords) {
		
		StringBuffer sb = new StringBuffer("select m from Member m where 1=1");
		Map<String, String> params = new HashMap<>();
		
		if(null != userName && !userName.isEmpty()) {
			sb.append(" and lower(m.name) like lower(:name)");
			params.put("name",userName);
		}
		if(null != role && !role.isEmpty()) {
			sb.append(" and lower(m.role.role) like lower(:role)");
			params.put("role",role);
		}
		
		if(null != keywords && !keywords.isEmpty()) {
			sb.append(" and lower(m.email) like lower(:email)");
			params.put("email",("%").concat(keywords).concat("%"));
		}
		TypedQuery<Member> query = em.createQuery(sb.toString(), Member.class);
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		return query.getResultList();
	}
}

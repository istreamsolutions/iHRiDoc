package com.istream.ihr.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.istream.ihr.orm.UserAccount;

@Repository
public class UserAccountDao {
	
	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public UserAccount getUserAccountByEmployeeId(Integer empId) {
		Session session = getSessionFactory().openSession();
		return (UserAccount) session.createCriteria(UserAccount.class).add(Restrictions.eq("employeeId", empId))
				.uniqueResult();
	}
	
	public UserAccount getUserAccountByUsername(String username) {
		Session session = getSessionFactory().openSession();
		return (UserAccount) session.createCriteria(UserAccount.class).add(Restrictions.eq("username", username))
				.uniqueResult();
	}


}

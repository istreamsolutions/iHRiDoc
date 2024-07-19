package com.istream.ihr.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.istream.ihr.dao.MasterEntityDao;
import com.istream.ihr.orm.Client;
import com.istream.ihr.orm.HealthCarrier;
import com.istream.ihr.orm.Project;

public class MasterEntityDaoImpl implements MasterEntityDao {
	
	private SessionFactory sessionFactory;
	
	public List<Client> getAllClient() {
		
		Session session = getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Client.class);
		List<Client> clients = (List<Client>) criteria.list();
		return clients;
	}
	
	public List<HealthCarrier> getAllHealthCarrier() {
		
		Session session = getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(HealthCarrier.class);
		List<HealthCarrier> healthCarriers = (List<HealthCarrier>) criteria.list();
		return healthCarriers;
	}
	
	public List<Project> getAllProject() {
		
		Session session = getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Project.class);
		List<Project> projects = (List<Project>) criteria.list();
		return projects;
	}
	
	public void addHealthCarrier(HealthCarrier healthCarrier) {
		Session session = getSessionFactory().openSession();
		session.saveOrUpdate(healthCarrier);
	}
	
	public void updateHealthCarrier(HealthCarrier healthCarrier) {
		
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		//tem.out.println("Session. update ------------>"+healthCarrier +  " name is "+healthCarrier.getName());
		session.saveOrUpdate(healthCarrier);
		tx.commit();
		session.close();
		
	}
	
	public void addClient(Client client) {
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(client);
		tx.commit();
		session.close();
	}
	
	public void updateClient(Client client) {
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(client);
		tx.commit();
		session.close();

	}
	
	public void addProject(Project project) {
		Session session = getSessionFactory().openSession();
		session.saveOrUpdate(project);
	}
	
	public void updateProject(Project project) {
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(project);
		tx.commit();
		session.close();

	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}

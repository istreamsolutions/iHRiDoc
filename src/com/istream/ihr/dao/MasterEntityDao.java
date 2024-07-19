package com.istream.ihr.dao;

import java.util.List;

import com.istream.ihr.orm.Client;
import com.istream.ihr.orm.HealthCarrier;
import com.istream.ihr.orm.Project;

public interface MasterEntityDao {
	
	public List<Client> getAllClient();
	
	public List<HealthCarrier> getAllHealthCarrier();
	
	public List<Project> getAllProject(); 
	
	public void addHealthCarrier(HealthCarrier healthCarrier);
	
	public void updateHealthCarrier(HealthCarrier healthCarrier);
	
	public void addClient(Client client);
	
	public void updateClient(Client client);
	
	public void addProject(Project project);
	
	public void updateProject(Project project);
}

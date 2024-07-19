package com.istream.ihr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.istream.ihr.dao.MasterEntityDao;
import com.istream.ihr.orm.Client;
import com.istream.ihr.orm.HealthCarrier;
import com.istream.ihr.orm.Project;
import com.istream.ihr.service.MasterEntityService;
import com.istream.ihr.vo.AddClientReq;
import com.istream.ihr.vo.AddHealthCarrierReq;
import com.istream.ihr.vo.AddProjectReq;
import com.istream.ihr.vo.ClientRes;
import com.istream.ihr.vo.EditClientReq;
import com.istream.ihr.vo.EditHealthCarrierReq;
import com.istream.ihr.vo.EditProjectReq;
import com.istream.ihr.vo.HealthCarrierRes;
import com.istream.ihr.vo.ProjectRes;

public class MasterEntityServiceImpl implements MasterEntityService {
	
	MasterEntityDao masterEntityDao;
	
	public List<ClientRes> getAllClient() {
		
		List<Client> clients = masterEntityDao.getAllClient();
				
		return convertClientToRes(clients);
	}
	
	public List<ClientRes> convertClientToRes(List<Client> clients) {
		
		List<ClientRes> response = new ArrayList<ClientRes>();
		ClientRes clientRes = new ClientRes();
		
		for(Client client: clients) {
			clientRes = new ClientRes();
			BeanUtils.copyProperties(client, clientRes);
			response.add(clientRes);
		}
		
		return response;
	}
	
	public List<HealthCarrierRes> getAllHealthCarrier() {
		
		List<HealthCarrier> healthCarriers = masterEntityDao.getAllHealthCarrier();
				
		return convertHealthCarriersToRes(healthCarriers);
	}
	
	public List<HealthCarrierRes> convertHealthCarriersToRes(List<HealthCarrier> healthCarriers) {
		
		List<HealthCarrierRes> response = new ArrayList<HealthCarrierRes>();
		HealthCarrierRes healthCarrierRes = new HealthCarrierRes();
		
		for(HealthCarrier healthCarrier: healthCarriers) {
			healthCarrierRes = new HealthCarrierRes();
			BeanUtils.copyProperties(healthCarrier, healthCarrierRes);
			response.add(healthCarrierRes);
		}
		
		return response;
	}
	
	public List<ProjectRes> getAllProject() {
		
		List<Project> projects = masterEntityDao.getAllProject();
				
		return convertProjectsToRes(projects);
	}
	
	public List<ProjectRes> convertProjectsToRes(List<Project> projects) {
		
		List<ProjectRes> response = new ArrayList<ProjectRes>();
		ProjectRes projectRes = new ProjectRes();
		
		for(Project project: projects) {
			projectRes = new ProjectRes();
			BeanUtils.copyProperties(project, projectRes);
			response.add(projectRes);
		}
		
		return response;
	}
	
	public void addHealthCarrier(AddHealthCarrierReq healthCarrierReq) {
		
		HealthCarrier healthCarrier = new HealthCarrier(); 
		BeanUtils.copyProperties(healthCarrierReq, healthCarrier);
		healthCarrier.setCreationTs(new Date());
		healthCarrier.setLastUpdateTs(new Date());
		
		masterEntityDao.addHealthCarrier(healthCarrier);
	}

	public void updateHealthCarrier(EditHealthCarrierReq healthCarrierReq) {
		HealthCarrier healthCarrier = new HealthCarrier(); 
		BeanUtils.copyProperties(healthCarrierReq, healthCarrier);		
		
		healthCarrier.setCreationTs(new Date());
		healthCarrier.setLastUpdateTs(new Date());
		
		masterEntityDao.updateHealthCarrier(healthCarrier);
	}
	
	public void addClient(AddClientReq clientReq) {
		
		Client client = new Client(); 
		BeanUtils.copyProperties(clientReq, client);
		client.setCreationTs(new Date());
		client.setLastUpdateTs(new Date());
		
		masterEntityDao.addClient(client);
	}
	
	public void updateClient(EditClientReq clientReq) {
		
		Client client = new Client(); 
		BeanUtils.copyProperties(clientReq, client);
		client.setCreationTs(new Date());
		client.setLastUpdateTs(new Date());
		
		masterEntityDao.updateClient(client);
	}
	
	public void addProject(AddProjectReq projectReq) {
		
		Project project = new Project(); 
		BeanUtils.copyProperties(projectReq, project);
		project.setCreationTs(new Date());
		project.setLastUpdateTs(new Date());
		
		masterEntityDao.addProject(project);
	}
	
	public void updateProject(EditProjectReq projectReq) {
		
		Project project = new Project(); 
		BeanUtils.copyProperties(projectReq, project);
		project.setCreationTs(new Date());
		project.setLastUpdateTs(new Date());
		
		masterEntityDao.updateProject(project);
	}
	
	public void setMasterEntityDao(MasterEntityDao masterEntityDao) {
		this.masterEntityDao = masterEntityDao;
	}
	
}

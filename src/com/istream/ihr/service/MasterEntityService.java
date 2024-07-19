package com.istream.ihr.service;

import java.util.List;

import com.istream.ihr.vo.AddClientReq;
import com.istream.ihr.vo.AddHealthCarrierReq;
import com.istream.ihr.vo.AddProjectReq;
import com.istream.ihr.vo.ClientRes;
import com.istream.ihr.vo.EditClientReq;
import com.istream.ihr.vo.EditHealthCarrierReq;
import com.istream.ihr.vo.EditProjectReq;
import com.istream.ihr.vo.HealthCarrierRes;
import com.istream.ihr.vo.ProjectRes;

public interface MasterEntityService {
	
	public List<ClientRes> getAllClient();
	
	public List<HealthCarrierRes> getAllHealthCarrier();
	
	public List<ProjectRes> getAllProject();
	
	public void addHealthCarrier(AddHealthCarrierReq healthCarrierReq);
	
	public void updateHealthCarrier(EditHealthCarrierReq healthCarrierReq);
	
	public void addClient(AddClientReq clientReq);
	
	public void updateClient(EditClientReq clientReq);
	
	public void addProject(AddProjectReq projectReq);
	
	public void updateProject(EditProjectReq projectReq);
}

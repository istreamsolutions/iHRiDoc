package com.istream.ihr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
public class MasterEntityController {
	
	@Autowired
	MasterEntityService masterEntityService;
	
	@RequestMapping(value = "/master/allClients", method = RequestMethod.GET, headers="Accept=application/json")
    public ResponseEntity<List<ClientRes>> getAllClient() {
    	
        List<ClientRes> clients = masterEntityService.getAllClient();
        
        if(clients.isEmpty()){
            return new ResponseEntity<List<ClientRes>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<ClientRes>>(clients, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/master/allHealthCarriers", method = RequestMethod.GET, headers="Accept=application/json")
	public ResponseEntity<List<HealthCarrierRes>> getAllHealthCarrier() {
    	
        List<HealthCarrierRes> healthCarriers = masterEntityService.getAllHealthCarrier();
        
        if(healthCarriers.isEmpty()){
            return new ResponseEntity<List<HealthCarrierRes>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<HealthCarrierRes>>(healthCarriers, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/master/allProjects", method = RequestMethod.GET, headers="Accept=application/json")
	public ResponseEntity<List<ProjectRes>> getAllProject() {
    	
        List<ProjectRes> projects = masterEntityService.getAllProject();
        
        if(projects.isEmpty()){
            return new ResponseEntity<List<ProjectRes>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<ProjectRes>>(projects, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/master/addHealthCarrier", method = RequestMethod.POST)
	public ResponseEntity<Void> addHealthCarrier(@RequestBody AddHealthCarrierReq healthCarrierReq) {
        
        masterEntityService.addHealthCarrier(healthCarrierReq);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/master/updateHealthCarrier", method = RequestMethod.POST)
	public ResponseEntity<Void> updateHealthCarrier(@RequestBody EditHealthCarrierReq healthCarrierReq) {
        
        masterEntityService.updateHealthCarrier(healthCarrierReq);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@RequestMapping(value = "/master/addClient", method = RequestMethod.POST)
	public ResponseEntity<Void> addClient(@RequestBody AddClientReq clientReq) {
        
        masterEntityService.addClient(clientReq);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/master/updateClient", method = RequestMethod.POST)
	public ResponseEntity<Void> updateClient(@RequestBody EditClientReq clientReq) {
        
        masterEntityService.updateClient(clientReq);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/master/addProject", method = RequestMethod.POST)
	public ResponseEntity<Void> addProject(@RequestBody AddProjectReq projectReq) {
        
        masterEntityService.addProject(projectReq);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/master/updateProject", method = RequestMethod.POST)
	public ResponseEntity<Void> updateProject(@RequestBody EditProjectReq projectReq) {
        
        masterEntityService.updateProject(projectReq);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
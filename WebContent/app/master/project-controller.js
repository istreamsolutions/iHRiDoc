'use strict';

angular.module('ihr.project', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/project', {
    templateUrl: 'app/master/project.html',
    //controller: 'ProjectCtrl'
  });
}])

.controller('ProjectCtrl', ['$scope', 'MasterEntityService', function($scope, MasterEntityService) {
	
	var self = this;
    self.project;
    self.projects=[];
    
    self.getAllProjects = function(){
   	 MasterEntityService.getAllProjects()
            .then(
					       function(d) {
						        self.projects = d;
					       },
      					function(errResponse){
      						console.error('Error while fetching Project');
      					}
			       );
    };
    
    self.addProject = function(project){
   	 MasterEntityService.addProject(project)
	              .then(
                self.getAllProjects, 
			              function(errResponse){
				               console.error('Error while creating Project.');
			              }	
            );
    };
    
    self.updateProject = function(project, id){
   	 MasterEntityService.updateProject(project, id)
	              .then(
			              self.getAllProjects, 
			              function(errResponse){
				               console.error('Error while updating Project.');
			              }	
            );
    };
    
    self.submit = function() {
        if(self.project.projectId==null){
            console.log('Saving New Project', self.project);
            self.addProject(self.project);
        }else{
            self.updateProject(self.project, self.project.projectId);
            console.log('Project updated with id ', self.project.projectId);
        }
        self.reset();
    };
    
    self.edit = function(projectId){
        console.log('id to be edited', projectId);
        for(var i = 0; i < self.projects.length; i++){
            if(self.projects[i].projectId == projectId) {
               self.project = angular.copy(self.projects[i]);
               break;
            }
        }
    };
    
    self.reset = function(){
    	self.project = {};
    	$scope.projectForm.$setPristine();
    	$scope.projectForm.$setValidity();
    	$scope.projectForm.$setUntouched();
    };
    
    self.getAllProjects();
}]);
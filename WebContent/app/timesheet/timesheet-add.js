'use strict';

angular.module('ihr.addTimesheet', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/addTimesheet/:ID', {
    templateUrl: 'app/timesheet/timesheet-add.html',
    controller: 'AddTimesheetCtrl'
  });
}])

.controller('AddTimesheetCtrl', ['$scope', 'TimesheetService', 'MasterEntityService', '$routeParams', function($scope, TimesheetService, MasterEntityService, $routeParams) {
	
	var self = this;
	self.timesheet;
	self.projectList=[];
	
	// ----- Get All Projects -----
    self.getAllProjects = function(){
    	MasterEntityService.getAllProjects()
            .then(
					       function(d) {
						        self.projectList = d;
					       },
      					function(errResponse){
      						console.error('Error while fetching Project List');
      					}
			       );
    };
    
    self.addTimesheet = function() {
    	self.timesheet.employeeId = $routeParams.ID;
    	TimesheetService.addTimesheet(self.timesheet)
        .then(
  		  function(d) {
			        alert('Timesheet added successfully');			        
			        self.resetForm();
		       },
			function(errResponse){
				console.error('Error while adding Timesheet');
			}	
        );
    };
    
    self.resetForm = function() {
    	
    	self.timesheet = {};
    	$scope.addTimesheetForm.$setPristine();
    	$scope.addTimesheetForm.$setValidity();
    	$scope.addTimesheetForm.$setUntouched();
    };
    
    self.getAllProjects();	
}]);
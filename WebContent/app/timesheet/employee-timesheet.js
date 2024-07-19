'use strict';

angular.module('ihr.employeeTimesheet', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/employeeTimesheet/:ID', {
    templateUrl: 'app/timesheet/employee-timesheet.html',
    //controller: 'EmployeeTimesheetCtrl'
  });
}])

.controller('EmployeeTimesheetCtrl', ['$scope', 'TimesheetService', '$routeParams', function($scope, TimesheetService, $routeParams) {
	
	var self = this;
	self.monthList = [{id:1, name:'January'}, {id:2, name:'February'}, {id:3, name:'March'}, {id:4, name:'April'}, {id:5, name:'May'}, {id:6, name:'June'}, {id:7, name:'Jul'}, {id:8, name:'August'}, {id:9, name:'September'}, {id:10, name:'October'}, {id:11, name:'November'}, {id:12, name:'December'}];
	self.yearList = [{id:2015, name:2015}, {id:2016, name:2016}];
	self.timesheet;
	self.timesheetList=[];
	
	// ----- Search Timesheet -----
    self.searchTimesheet = function() {
    	self.timesheet.employeeId = $routeParams.ID;
    	TimesheetService.searchTimesheet(self.timesheet)
	        .then(
		        
	        	function(d) {
				        self.timesheetList = d;
			       },
					function(errResponse){
						console.error('Error while fetching Timesheet');
					}
	        		
	        );
    };
	
	/*
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
    */	
}]);
'use strict';

angular.module('idoc.employees', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/idoc/employees', {
    templateUrl: 'app/document/employees.html',
    controller: 'DocEmployeeCtrl'
  });
}])

.controller('DocEmployeeCtrl', ['$scope', 'EmployeeService', 'MasterEntityService', function($scope, EmployeeService, MasterEntityService) {
	
	var self = this;
    self.searchEmp;
    self.employeeList=[];
    self.clientList=[];
    
    // ----- Get All Clients -----
    self.getAllClients = function(){
    	MasterEntityService.getAllClients()
            .then(
					       function(d) {
						        self.clientList = d;
					       },
      					function(errResponse){
      						console.error('Error while fetching Currencies');
      					}
			       );
    };
    
    // ----- Fetch All Employees -----
    self.fetchAllEmployees = function(){
    	EmployeeService.fetchAllEmployees()
            .then(
					       function(d) {
						        self.employeeList = d;
					       },
      					function(errResponse){
      						console.error('Error while fetching Currencies');
      					}
			       );
    };
    
    // ----- Search Employee -----
    self.searchEmployee = function() {
            
    	EmployeeService.searchEmployee(self.searchEmp)
	        .then(
		        
	        	function(d) {
				        self.employeeList = d;
			       },
					function(errResponse){
						console.error('Error while fetching Currencies');
					}
	        		
	        );
    };

    self.getAllClients();
    self.fetchAllEmployees();
    
}]);
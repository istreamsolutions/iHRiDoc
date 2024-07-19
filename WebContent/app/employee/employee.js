'use strict';

angular.module('ihr.employee', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/employee', {
    templateUrl: 'app/employee/employee.html',
    //controller: 'EmployeeCtrl'
  });
}])

.controller('EmployeeCtrl', ['$scope', 'EmployeeService', 'MasterEntityService', function($scope, EmployeeService, MasterEntityService) {
	
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
    
}])


.factory('EmployeeService', ['$http', '$q', function($http, $q){

	return {
		
			createEmployee: function(employee){
				return $http.post(apiContext + '/employee/', employee)
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									console.error('Error while creating user');
									return $q.reject(errResponse);
								}
						);
			},
		
			fetchAllEmployees: function() {
					return  $http({
					        method : "GET",
					        url : apiContext + "/employee/",
					        headers: {'Access-Control-Allow-Origin': 'Yes' }
					    }).then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching users');
										return $q.reject(errResponse);
									}
							);
			},
			
			searchEmployee: function(searchEmp){
				return $http.post(apiContext + '/employee/search/', searchEmp)
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									console.error('Error while creating user');
									return $q.reject(errResponse);
								}
						);
			},
			
			viewEmployeeDetail: function(id){
				return $http.get(apiContext + '/employee/'+id)
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									console.error('Error while creating user');
									return $q.reject(errResponse);
								}
						);
			},
			
			editEmployee: function(employee) {
				return $http.put(apiContext + '/employee/edit/', employee)
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									console.error('Error while creating user');
									return $q.reject(errResponse);
								}
						);
			}
		
	};

}]);
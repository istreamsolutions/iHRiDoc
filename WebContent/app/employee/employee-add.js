'use strict';

angular.module('ihr.addEmployee', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/addEmployee', {
    templateUrl: 'app/employee/employee-add.html',
    //controller: 'AddEmployeeCtrl'
  });
}])

.controller('AddEmployeeCtrl', ['$scope', 'EmployeeService', function($scope, EmployeeService) {
	
	var self = this;
	self.employee;	
	self.genderList = [{GenderId: 1, GenderName:'Male'}, {GenderId: 2, GenderName:'Female'}];
	
	self.createEmployee = function(){
        EmployeeService.createEmployee(self.employee)
	              .then(
            		  function(d) {
					        alert('Employee added successfully');
					        
					        self.resetForm();
				       },
					function(errResponse){
						console.error('Error while fetching Currencies');
					}	
            );
    };
    
    self.resetForm = function() {
    	
    	self.employee = {};
    	$scope.addEmployeeForm.$setPristine();
    	$scope.addEmployeeForm.$setValidity();
    	$scope.addEmployeeForm.$setUntouched();
    };
	
}]);
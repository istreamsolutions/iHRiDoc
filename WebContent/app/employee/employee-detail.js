'use strict';

angular.module('ihr.employeeDetail', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/employeeDetail/:ID', {
    templateUrl: 'app/employee/employee-detail.html',
    //controller: 'EmployeeDetailCtrl'
  });
}])

.controller('EmployeeDetailCtrl', ['$scope', 'EmployeeService', '$routeParams', function($scope, EmployeeService, $routeParams) {
	
	var self = this;
    self.employee;
    
    // ----- View Employee Detail -----
    self.viewEmployeeDetail = function(id) {
    	
    	EmployeeService.viewEmployeeDetail(id)
	    	.then(	    			
	    			function(d) {
	    				self.employee = d;
	    			},
	    			function(errResponse) {
	    				console.error('Error while fetching Currencies');
	    			}
	    	);
    };

    self.viewEmployeeDetail($routeParams.ID);
    
}]);
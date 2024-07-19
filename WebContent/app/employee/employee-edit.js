'use strict';

angular.module('ihr.editEmployee', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/editEmployee/:ID', {
    templateUrl: 'app/employee/employee-edit.html',
    //controller: 'EditEmployeeCtrl'
  });
}])

.controller('EditEmployeeCtrl', ['$scope', 'EmployeeService', 'MasterEntityService', '$routeParams', function($scope, EmployeeService, MasterEntityService, $routeParams) {
	
	var self = this;
    self.employee;
    self.genderList = [{GenderId: 1, GenderName:'Male'}, {GenderId: 2, GenderName:'Female'}];
    self.clientList=[];
    self.healthCarrierList=[];
    /*
    self.editEmployeeReq = [];
    self.editEmployeeReq.empAddress = {};
    self.editEmployeeReq.empClient = {};
    self.editEmployeeReq.empHealthPlan = {};
    self.editEmployeeReq.empImmigrationDtl = {};
    */
    
    // ----- Get All Clients -----
    self.getAllClients = function(){
    	MasterEntityService.getAllClients()
            .then(
					       function(d) {
						        self.clientList = d;
					       },
      					function(errResponse){
      						console.error('Error while fetching Client List');
      					}
			       );
    };
    
    // ----- Get All Health Carriers -----
    self.getAllHealthCarriers = function(){
    	MasterEntityService.getAllHealthCarriers()
            .then(
					       function(d) {
						        self.healthCarrierList = d;
					       },
      					function(errResponse){
      						console.error('Error while fetching Health Carrier');
      					}
			       );
    };
    
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

    self.editEmployee = function(){
    	/*
    	self.editEmployeeReq.employeeId = self.employee.employeeId;
    	self.editEmployeeReq.firstName = self.employee.firstName;
    	self.editEmployeeReq.middleName = self.employee.middleName;
    	self.editEmployeeReq.lastName = self.employee.lastName;
    	self.editEmployeeReq.email = self.employee.email;
    	self.editEmployeeReq.homePhone = self.employee.homePhone;
    	self.editEmployeeReq.mobilePhone = self.employee.mobilePhone;
    	self.editEmployeeReq.workPhone = self.employee.workPhone;
    	self.editEmployeeReq.ssn = self.employee.ssn;
    	self.editEmployeeReq.gender = self.employee.gender;
    	self.editEmployeeReq.birthDate = self.employee.birthDate;
    	self.editEmployeeReq.startDate = self.employee.startDate;
    	self.editEmployeeReq.endDate = self.employee.endDate;
    	self.editEmployeeReq.baseSalaryAmt = self.employee.baseSalaryAmt;
    	self.editEmployeeReq.bonusAmt = self.employee.bonusAmt;
    	self.editEmployeeReq.designation = self.employee.designation;
    	
    	self.editEmployeeReq.empAddress.addressId = self.employee.empAddress.addressId;
    	self.editEmployeeReq.empAddress.street1 = self.employee.empAddress.street1;
    	self.editEmployeeReq.empAddress.street2 = self.employee.empAddress.street2;
    	self.editEmployeeReq.empAddress.city = self.employee.empAddress.city;
    	self.editEmployeeReq.empAddress.state = self.employee.empAddress.state;
    	self.editEmployeeReq.empAddress.zipcode = self.employee.empAddress.zipcode;
    	self.editEmployeeReq.empAddress.country = self.employee.empAddress.country;
    	self.editEmployeeReq.empAddress.effectiveDate = self.employee.empAddress.effectiveDate;
    	self.editEmployeeReq.empAddress.termDate = self.employee.empAddress.termDate;
    	
    	self.editEmployeeReq.empClient.empClientId = self.employee.empClient.empClientId;
    	self.editEmployeeReq.empClient.startDate = self.employee.empClient.startDate;
    	self.editEmployeeReq.empClient.endDate = self.employee.empClient.endDate;
    	
    	self.editEmployeeReq.empHealthPlan.empPlanId = self.employee.empHealthPlan.empPlanId;
    	self.editEmployeeReq.empHealthPlan.planType = self.employee.empHealthPlan.planType;
    	self.editEmployeeReq.empHealthPlan.numCovdMembers = self.employee.empHealthPlan.numCovdMembers;
    	self.editEmployeeReq.empHealthPlan.effectiveDate = self.employee.empHealthPlan.effectiveDate;
    	self.editEmployeeReq.empHealthPlan.termDate = self.employee.empHealthPlan.termDate;
    	
    	self.editEmployeeReq.empImmigrationDtl.empImmDtlId = self.employee.empImmigrationDtl.empImmDtlId;
    	self.editEmployeeReq.empImmigrationDtl.immType = self.employee.empImmigrationDtl.immType;
    	self.editEmployeeReq.empImmigrationDtl.startDate = self.employee.empImmigrationDtl.startDate;
    	self.editEmployeeReq.empImmigrationDtl.endDate = self.employee.empImmigrationDtl.endDate;
    	*/
        EmployeeService.editEmployee(/*self.editEmployeeReq*/self.employee)
	              .then(
            		  function(d) {
					        alert('Employee details updated successfully');
				       },
					function(errResponse){
						console.error('Error while fetching Currencies');
					}	
            );
    };
    
    self.getAllClients();
    self.getAllHealthCarriers();
    self.viewEmployeeDetail($routeParams.ID);
    
}]);
'use strict';

angular.module('ihr.healthInsurance', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/healthInsurance', {
    templateUrl: 'app/master/health-insurance.html',
    //controller: 'HealthCarrierCtrl'
  });
}])

.controller('HealthCarrierCtrl', ['$scope', 'MasterEntityService', function($scope, MasterEntityService) {
	
	 var self = this;
     self.healthCarrier;
     self.healthCarriers=[];
     
     self.getAllHealthCarriers = function(){
    	 MasterEntityService.getAllHealthCarriers()
             .then(
 					       function(d) {
 						        self.healthCarriers = d;
 					       },
       					function(errResponse){
       						console.error('Error while fetching Health Carriers');
       					}
 			       );
     };
     
     self.addHealthCarrier = function(healthCarrier){
    	 MasterEntityService.addHealthCarrier(healthCarrier)
	              .then(
                 self.getAllHealthCarriers, 
			              function(errResponse){
				               console.error('Error while creating Health Carrier.');
			              }	
             );
     };
     
     self.updateHealthCarrier = function(healthCarrier, id){
    	 MasterEntityService.updateHealthCarrier(healthCarrier, id)
	              .then(
			              self.getAllHealthCarriers, 
			              function(errResponse){
				               console.error('Error while updating Health Carrier.');
			              }	
             );
     };
     
     self.submit = function() {
         if(self.healthCarrier.carrierId==null){
             console.log('Saving New Health Carrier', self.healthCarrier);    
             self.addHealthCarrier(self.healthCarrier);
         }else{
             self.updateHealthCarrier(self.healthCarrier, self.healthCarrier.carrierId);
             console.log('Health Carrier updated with id ', self.healthCarrier.carrierId);
         }
         self.reset();
     };
     
     self.edit = function(carrierId){
         console.log('id to be edited', carrierId);
         for(var i = 0; i < self.healthCarriers.length; i++){
             if(self.healthCarriers[i].carrierId == carrierId) {
                self.healthCarrier = angular.copy(self.healthCarriers[i]);
                break;
             }
         }
     };
     
     self.reset = function(){
    	self.healthCarrier = {};
     	$scope.addHealthCarrierForm.$setPristine();
     	$scope.addHealthCarrierForm.$setValidity();
     	$scope.addHealthCarrierForm.$setUntouched();
     };
     
     self.getAllHealthCarriers();
     
}]);
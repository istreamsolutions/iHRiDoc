'use strict';

angular.module('ihr.client', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/client', {
    templateUrl: 'app/master/client.html',
    //controller: 'ClientCtrl'
  });
}])

.controller('ClientCtrl', ['$scope', 'MasterEntityService', function($scope, MasterEntityService) {
	
	var self = this;
    self.client;
    self.clients=[];
    self.typeList = [{id: 1, name:'D'}, {id: 2, name:'T'}];
    
    self.getAllClients = function(){
   	 MasterEntityService.getAllClients()
            .then(
					       function(d) {
						        self.clients = d;
					       },
      					function(errResponse){
      						console.error('Error while fetching Client');
      					}
			       );
    };
    
    self.addClient = function(client){
   	 MasterEntityService.addClient(client)
	              .then(
                self.getAllClients, 
			              function(errResponse){
				               console.error('Error while creating Client.');
			              }	
            );
    };
    
    self.updateClient = function(client, id){
   	 MasterEntityService.updateClient(client, id)
	              .then(
			              self.getAllClients, 
			              function(errResponse){
				               console.error('Error while updating Client.');
			              }	
            );
    };
    
    self.submit = function() {
        if(self.client.clientId==null){
            console.log('Saving New Health Carrier', self.client);
            self.addClient(self.client);
        }else{
            self.updateClient(self.client, self.client.clientId);
            console.log('Client updated with id ', self.client.clientId);
        }
        self.reset();
    };
    
    self.edit = function(clientId){
        console.log('id to be edited', clientId);
        for(var i = 0; i < self.clients.length; i++){
            if(self.clients[i].clientId == clientId) {
               self.client = angular.copy(self.clients[i]);
               break;
            }
        }
    };
    
    self.reset = function(){
    	self.client = {};
    	$scope.clientForm.$setPristine();
    	$scope.clientForm.$setValidity();
    	$scope.clientForm.$setUntouched();
    };
    
    self.getAllClients();
}]);
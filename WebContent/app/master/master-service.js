'use strict';

angular.module('ihr.master', [])

.factory('MasterEntityService', [ '$http', '$q', function($http, $q) {

	return {

		getAllClients : function() {
			return $http({
				method : "GET",
				url : apiContext + "/master/allClients",
				headers : {
					'Access-Control-Allow-Origin' : 'Yes'
				}
			}).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching clients');
				return $q.reject(errResponse);
			});
		},
		
		addClient: function(client){
			return $http.post(apiContext + '/master/addClient', client)
					.then(
							function(response){
								return response.data;
							}, 
							function(errResponse){
								console.error('Error while creating client');
								return $q.reject(errResponse);
							}
					);
		},
		
		updateClient: function(client, id){
			return $http.post(apiContext + '/master/updateClient', client)
					.then(
							function(response){
								return response.data;
							}, 
							function(errResponse){
								console.error('Error while updating client');
								return $q.reject(errResponse);
							}
					);
		},
		
		getAllHealthCarriers : function() {
			return $http({
				method : "GET",
				url : apiContext + "/master/allHealthCarriers",
				headers : {
					'Access-Control-Allow-Origin' : 'Yes'
				}
			}).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching Health Carriers');
				return $q.reject(errResponse);
			});
		}, 
		
		addHealthCarrier: function(healthCarrier){
			return $http.post(apiContext + '/master/addHealthCarrier', healthCarrier)
					.then(
							function(response){
								return response.data;
							}, 
							function(errResponse){
								console.error('Error while creating health carrier');
								return $q.reject(errResponse);
							}
					);
		},
		
		updateHealthCarrier: function(healthCarrier, id){
			return $http.post(apiContext + '/master/updateHealthCarrier', healthCarrier)
					.then(
							function(response){
								return response.data;
							}, 
							function(errResponse){
								console.error('Error while updating user');
								return $q.reject(errResponse);
							}
					);
		},
		
		getAllProjects : function() {
			return $http({
				method : "GET",
				url : apiContext + "/master/allProjects",
				headers : {
					'Access-Control-Allow-Origin' : 'Yes'
				}
			}).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching Projects');
				return $q.reject(errResponse);
			});
		},
		
		addProject: function(project){
			return $http.post(apiContext + '/master/addProject', project)
					.then(
							function(response){
								return response.data;
							}, 
							function(errResponse){
								console.error('Error while creating project');
								return $q.reject(errResponse);
							}
					);
		},
		
		updateProject: function(project, id){
			return $http.post(apiContext + '/master/updateProject', project)
					.then(
							function(response){
								return response.data;
							}, 
							function(errResponse){
								console.error('Error while updating project');
								return $q.reject(errResponse);
							}
					);
		}
	};

} ]);
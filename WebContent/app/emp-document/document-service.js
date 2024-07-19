'use strict';

angular.module('idoc.document', [])

.service('DocumentService', [ '$http', '$q', '$rootScope', function($http, $q, $rootScope) {
	
	var responseData;
	
	this.uploadFileToUrl = function(document, uploadUrl) {
		
		var deffered = $q.defer();
		var fd = new FormData();
		
		fd.append('file', document.myFile);
		fd.append('documentTypeId', document.category.folder_ID);
		fd.append('documentType', document.category.folder_NAME);
		fd.append('immigrationType', 'H1B');
		fd.append('employeeId', document.employeeId);
		
		return $http.post(uploadUrl, fd, {
			transformRequest: angular.identity,
			headers: { 'Content-Type' : undefined}
		})
		
		.success(function(response) {
			
			/* $scope.errors = response.data.value; */
			console.log(response);
			responseData = response;
			deffered.resolve(response);
			
			return deffered.promise;
		})
		
		.error(function(error) {
			deffered.reject(error);
			return deffered.promise;
		});

	};
	
	this.uploadGenericFileToUrl = function(document, uploadUrl) {
		
		var deffered = $q.defer();
		var fd = new FormData();
		
		fd.append('file', document.myFile);
		//fd.append('documentTypeId', document.category.folder_ID);
		//fd.append('documentType', document.category.folder_NAME);
		//fd.append('immigrationType', 'H1B');
		fd.append('employeeId', $rootScope.userProfile.employeeId);
		
		return $http.post(uploadUrl, fd, {
			transformRequest: angular.identity,
			headers: { 'Content-Type' : undefined}
		})
		
		.success(function(response) {
			
			/* $scope.errors = response.data.value; */
			console.log(response);
			responseData = response;
			deffered.resolve(response);
			
			return deffered.promise;
		})
		
		.error(function(error) {
			deffered.reject(error);
			return deffered.promise;
		});

	};
	
	this.downloadFile = function(docResponse) {
		
		var deffered = $q.defer();
		var requestHeader = {'Accept': docResponse.fileType}
		
		$http({
			url: apiContext + '/employee/downloadFile',
			method: "GET",
			responseType:'arraybuffer',
			headers: requestHeader,
			params:{
				fileName: docResponse.fileName,
				uuid: docResponse.uuid,
				folderName: docResponse.folderName,
				categoryName: docResponse.categoryName,
				uploadDate: docResponse.uploadDate,
				fileType: docResponse.fileType
			}
		}).success(function (response) {
			
			deffered.resolve(response);			
		}).error(function(response,status ){
			
	    	deffered.reject('Error');
		});	
		
		return deffered.promise;
	};
	
	this.getResponse = function() {
		return responseData;
	};

}])


.factory('EmployeeDocumentService', ['$http', '$q', function($http, $q){

	return {
			getEmployeeDocuments: function(employeeId) {
				return $http.get(apiContext+'/employee/documents/'+employeeId)
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									console.error('Error while getting employee documents');
									return $q.reject(errResponse);
								}
						);
			},
			
			getDocumentsByCategory: function(immiTypeId,categoryId) {
				return $http.get(apiContext+'/category/documents/'+immiTypeId+'/'+categoryId)
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									console.error('Error while getting documents by category');
									return $q.reject(errResponse);
								}
						);
			},
			
			getGenericDocuments: function() {
				return $http.get(apiContext + '/generic/documents')
						.then(
								function(response){
									return response.data;
								}, 
								function(errResponse){
									console.error('Error while getting generic documents');
									return $q.reject(errResponse);
								}
						);
			},
			
			getFoldersByImmigrationType: function(immiTypeId) {
				return $http({
					method : "GET",
					url : apiContext + "/document/folders/"+immiTypeId,
					headers : {
						'Access-Control-Allow-Origin' : 'Yes'
					}
				}).then(function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while fetching Document Folders');
					return $q.reject(errResponse);
				});
			},
			
			deleteDocument: function(doc) {
				return $http({
					method : "POST",
					url : apiContext + "/employee/documents/delete",
					data: doc,
					headers : {
						'Access-Control-Allow-Origin' : 'Yes'
					}
				}).then(function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while deleting Document');
					return $q.reject(errResponse);
				});
			}
	};

}]);
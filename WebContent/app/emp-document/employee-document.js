'use strict';

angular.module('idoc.employeeDocument', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/employeeDocument/:ID/:firstName/:lastName', {
    templateUrl: 'app/emp-document/employee-document.html',
    //controller: 'EmployeeTimesheetCtrl'
  });
}])

.controller('EmployeeDocumentCtrl', ['$scope', 'DocumentService', 'EmployeeDocumentService', '$routeParams', '$q', '$window','$timeout', function($scope, DocumentService, EmployeeDocumentService, $routeParams, $q, $window,$timeout) {
	
	
	var self = this;
	
	self.firstName = $routeParams.firstName;
	self.lastName = $routeParams.lastName;
	
	//self.categoryList = [{id:1, name:'I-797'}, {id:2, name:'I-140'}];	
	self.categoryList = [];
	self.getFoldersByImmigrationType = function() {
		
		EmployeeDocumentService.getFoldersByImmigrationType(1).then(
				
				function(result) {
					self.categoryList = result;
				},
				
				function(error) {
					console.log('Error  getting document folders:' +error);
				}
		)
	};
	
	self.employeeDocumentList = [];
	self.getEmployeeDocuments = function() {
		
		EmployeeDocumentService.getEmployeeDocuments($routeParams.ID).then(
				function(result) {
					self.employeeDocumentList = result;
				},
				
				function(error) {
					console.log('Error getting employee documents' +error);
				}
		);
		
		//This method is to list all the documents for the given immigration type and category id
		/*EmployeeDocumentService.getDocumentsByCategory(1,1).then(
				function(result) {
					self.employeeDocumentList = result;
				},
				function(error) {
					console.log('Error getting employee documents' +error);
				}
		);*/
		
		//This method is to get Generic Documents, which dont belong to an employee
		/*EmployeeDocumentService.getGenericDocuments().then(
				function(result) {
					self.employeeDocumentList = result;
				},
				function(error) {
					console.log('Error getting employee documents' +error);
				}
		);*/
		
		
	};
	
	
	
	self.document = {};
	self.uploadFile = function() {
		//var file = self.document.myFile;
		self.document.employeeId = $routeParams.ID;
		
		var uploadUrl = apiContext + "/employee/attachFile";
		DocumentService.uploadFileToUrl(self.document, uploadUrl).then(function(result) {
			$scope.notification.show=true;
			$scope.notification.close(5000);
			
			self.resetForm();
			self.getEmployeeDocuments();
		},
		function(error) {
			alert('Error occurred while upload.'+ error);
		});
	};
	
	self.downloadFile = function(docRes) {
		
		var deferred = $q.defer();
		
		DocumentService.downloadFile(docRes).then(
		function (result) {
			
			var fileName = docRes.fileName;
	    	var contentType = docRes.fileType;
	    	
	    	if(result != null && result.byteLength > 0) {
	    		
	    		var file = new Blob([result], {type: contentType});
	            var fileURL = URL.createObjectURL(file);
	            var ua = navigator.userAgent.toLowerCase();
	  		  	var chromeIOS= ua.match(/crios/);
	  		  	
	    		if($window.navigator.msSaveOrOpenBlob) {
	    			 $window.navigator.msSaveOrOpenBlob(file,fileName);
	    		}
	    		else 
	    		{
	    			if(!chromeIOS) {                		
	        			var createDownload = document.createElement("a");
	        			document.body.appendChild(createDownload);
	        			createDownload.setAttribute('style', "display: none");
	        			createDownload.href = fileURL;
	        			createDownload.download = fileName;
	        			createDownload.click();
	    			}
	    			else 
	    			{
	    				var reader = new FileReader();
	            		reader.onload = function(e) {
		            		var bdata = btoa(reader.result);
		            		var datauri = 'data:'+contentType+';base64,' + bdata;
		            		var ua = navigator.userAgent.toLowerCase();						                  		 
		            		window.open(datauri);
	           	 		}
	            		reader.readAsBinaryString(file);
	            	}
	    		}
			}

	    },
		function (error){
			deferred.reject();
		});
		
		return deferred.promise;
    };
	
	self.deleteDocument = function(doc) {
		
		EmployeeDocumentService.deleteDocument(doc).then(function(result) {
			$scope.notification.show=true;
			$scope.notification.close(5000);
			self.getEmployeeDocuments();
		},
		function(error) {
			alert('Error while deleting document'+error);
		});
	};
	
	self.resetForm = function() {
    	
    	self.document = {};
    	$scope.documentForm.$setPristine();
    	$scope.documentForm.$setValidity();
    	$scope.documentForm.$setUntouched();
    };
    
    $scope.notification={
			show:false,
			key:"sent_success", //default
			display: function(nkey) {
				if(nkey){
					$scope.notification.key=nkey;
					$scope.notification.show=true;

					}
			},
			close: function(delay){
				if(delay){
					$timeout(function(){
						$scope.notification.show=false;},delay)
				} else {
					$scope.notification.show=true;
				}
			}
    };

	
    self.getFoldersByImmigrationType();
	self.getEmployeeDocuments();
}]);
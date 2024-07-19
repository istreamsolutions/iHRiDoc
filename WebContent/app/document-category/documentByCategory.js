'use strict';

angular.module('idoc.documentByCategory', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
	console.log('Inside Routeprovider');	
 
	$routeProvider.when('/idoc/category/documents', {
	    templateUrl: 'app/document-category/documentByCategory.html'
	 });
	
}])

.controller('DocumentByCategoryCtrl', ['$scope', 'DocumentService', 'EmployeeDocumentService', '$routeParams', '$q', '$window','$timeout', function($scope, DocumentService, EmployeeDocumentService, $routeParams, $q, $window,$timeout) {
	
	
	var self = this;
	
	self.categoryList = [];
	self.searchCategory = {};
	self.getFoldersByImmigrationType = function() {
		
		EmployeeDocumentService.getFoldersByImmigrationType(1).then(
				
				function(result) {
					self.categoryList = result;
					self.searchCategory = self.categoryList[0];
					self.getDocumentsByCategory();
				},
				
				function(error) {
					console.log('Error  getting document folders:' +error);
				}
		)
	};
	
	self.documentList = [];
	self.getDocumentsByCategory = function() {
		
		//This method is to list all the documents for the given immigration type and category id
		EmployeeDocumentService.getDocumentsByCategory(1,self.searchCategory.folder_ID).then(
				function(result) {
					self.documentList = result;
				},
				function(error) {
					console.log('Error getting employee documents' +error);
				}
		);
	};	
	
	self.deleteDocument = function(doc) {
		
		EmployeeDocumentService.deleteDocument(doc).then(function(result) {
			alert('Dcument deleted successfully');
			self.getDocumentsByCategory();
		},
		function(error) {
			alert('Error while deleting document'+error);
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
	
    self.getFoldersByImmigrationType();
}]);
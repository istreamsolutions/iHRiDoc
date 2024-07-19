'use strict';

angular.module('ihr.home', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
	console.log('Inside Routeprovider');	
 
	$routeProvider.when('/ihr/home', {
	    templateUrl: 'app/ihrhome/ihrHome.html'
	 });
	
	

	
}]);
'use strict';

angular.module('idoc.home', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
	console.log('Inside Routeprovider');	
 
	$routeProvider.when('/idoc/home', {
	    templateUrl: 'app/idochome/iDocHome.html'
	 });
	
}]);
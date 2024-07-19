'use strict';

var ihrApp = angular.module('ihr', [ 'ngRoute' ]);

ihrApp.config([ '$routeProvider', function($routeProvider) {

	$routeProvider.when('/home', {
		templateUrl : 'app/ihrhome/home.html',
		controller : 'HomeCtrl'
	})

	.otherwise({
		redirectTo : '/home'
	});

} ]);
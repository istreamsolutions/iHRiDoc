'use strict';

angular.module('ihr.timesheet', [])

.factory('TimesheetService', [ '$http', '$q', function($http, $q) {

	return {
		
		addTimesheet: function(timesheet){
			return $http.post(apiContext + '/timesheet/', timesheet)
					.then(
							function(response){
								return response.data;
							}, 
							function(errResponse){
								console.error('Error while add timesheet');
								return $q.reject(errResponse);
							}
					);
		},
		
		searchTimesheet: function(timesheet){
			return $http.post(apiContext + '/timesheet/search/', timesheet)
					.then(
							function(response){
								return response.data;
							}, 
							function(errResponse){
								console.error('Error while search timesheet');
								return $q.reject(errResponse);
							}
					);
		}
	};

} ]);
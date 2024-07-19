'use strict';

angular.module('ihr', [
  'ngRoute',
  'angular-loading-bar',
  'home',
  'ihr.home',
  'idoc.home',
  'idoc.employees',
  'ihr.addEmployee',
  'ihr.employee',
  'ihr.employeeDetail',
  'ihr.editEmployee',
  'ihr.master',
  'ihr.addTimesheet',
  'ihr.timesheet',
  'ihr.client',
  'ihr.healthInsurance',
  'ihr.project',
  'ihr.employeeTimesheet',
  'idoc.fileUpload',
  'idoc.employeeDocument',
  'idoc.document',
  'idoc.documentByCategory',
  'idoc.companyDocs'
  
  /*,
  'ihr.timesheetDetail'
  */
])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/home'});
}]);

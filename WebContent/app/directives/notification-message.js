'use strict';
angular.module('ihr').directive('notificationMessage',function(){
	return {
		restrict:'E',
		transclude:true,
		templateUrl: 'app/directives/notification-template.html',
		scope:{
			close:'&onClose'
		}
	}
});

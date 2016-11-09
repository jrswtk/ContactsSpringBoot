var app = angular.module('contactsApp', []);
		
		app.controller('contactsCtrl', function($scope, $http) {
			
			$scope.users = [];
			
			$scope.deleteUser = function(userId) {
				$http.post('http://localhost:8080/reg/rest/admin/delete/' + userId).success(function(response) {
					$scope.getUsers();
				});
			};
			
			$scope.getUsers = function() {
				$http.get('http://localhost:8080/reg/rest/admin/getall.json').success(function(response) {
					$scope.users = response;
				});
			};
			
		});
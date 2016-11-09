var app = angular.module('contactsApp', ['naif.base64']);
		
		app.controller('contactsCtrl', function($scope, $http, $window, $rootScope) {
			
			$scope.contact;
			$scope.contacts = [];
			$scope.contactTypes = [];
			
			$scope.getContactTypes = function() {
				$http.get('http://localhost:8080/reg/rest/contact/contactypes.json').success(function(response) {
					$scope.contactTypes = response;
				});
			};
			
			$scope.deleteContact = function(contactId, userId) {
				$http.post('http://localhost:8080/reg/rest/contact/delete/' + contactId).success(function(response) {
					$scope.getContacts();
				});
			};
			
			$scope.getContacts = function() {
				$http.get('http://localhost:8080/reg/rest/contact/contacts.json').success(function(response) {
					$scope.contacts = response;
				});
			};
			
			$scope.addContact = function(firstName, lastName, phoneNumber, email, contactType, imageEncode64) {
				$scope.createContactData(null, null, firstName, lastName, phoneNumber, email, contactType, imageEncode64)
				$http.put('http://localhost:8080/reg/rest/contact/add', JSON.stringify($scope.contact)).success(function(response) {
					$scope.getContacts();
					alert("Kontakt zostal dodany.");
				});
			};
			
			$scope.updateContact = function(id, firstName, lastName, phoneNumber, email, contactType, imageEncode64) {		
				$scope.createContactData(id, null, firstName, lastName, phoneNumber, email, contactType, imageEncode64)
				$http.put('http://localhost:8080/reg/rest/contact/update', JSON.stringify($scope.contact)).success(function(response) {
					$scope.getContacts();
					alert("Kontakt zostal zaktualizowany.");
				});
			};
			
			$scope.createContactData = function(id, ownerUserId, firstName, lastName, phoneNumber, email, contactType, imageEncode64) {
				$scope.contact = {
					id: id,
					ownerUserId: ownerUserId,
					firstName: firstName,
					lastName: lastName,
					phoneNumber: phoneNumber,
					email: email,
					contactType: contactType,
					imageEncode64: imageEncode64
				}
			};
		});
		
		app.directive('checkImage', function($http) {
			return {
				restrict: 'A',
				link: function(scope, element, attrs) {
					attrs.$observe('ngSrc', function(ngSrc) {
						$http.get(ngSrc).success(function() {}).error(function() {
							element.attr('src', 'http://localhost:8080/reg/images/default.png');
						});
					});
				}
			};
		});
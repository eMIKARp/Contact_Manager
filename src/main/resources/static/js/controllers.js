
app.controller('ContactController', function(appName, $http, $resource){
	var vm = this;
	var Contact = $resource('api/contacts/:contactId');
	vm.contact = new Contact();
	vm.appName = appName;
	
	function refreshData() {
		vm.contacts = Contact.query(
			function success(data, headers){
				console.log('Pobrano dane:'+ data);
				console.log(headers('Content-Type'));
			},
			function error(response){
				console.log(response.status);
			});
	}
	
	vm.addContact = function(contact){
		vm.contact.$save(function(data){
			refreshData();
			vm.contact = new Contact();
		});
	}
	
	vm.loadData = function(id){
		vm.details = Contact.get({contactId:id})
	}

	
	refreshData();

});
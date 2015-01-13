(function(){
    var fxApp = angular.module('fxApp', ['ngRoute', 'fxSideBar']);

    fxApp.config(['$routeProvider',
        function($routeProvider) {
            $routeProvider
            .when('/communication/new-message', {
                templateUrl: 'partials/new-message.html'
            })
            .when('/communication/sent-messages', {
                templateUrl: 'partials/sent-messages.html'
            })
            .when('/communication/contacts', {
                templateUrl: 'partials/contact-list.html'
            })
            .when('/communication/groups', {
                templateUrl: 'partials/group-list.html'
            });
            //.when('/phones/:phoneId', {
            //    templateUrl: 'partials/phone-detail.html',
            //    controller: 'PhoneDetailCtrl'
            //});
            //.otherwise({
            //    redirectTo: '/phones'
            //});
        }
    ]);
})();

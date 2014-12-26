(function(){
    var fenixApp = angular.module('fenixApp', ['ngRoute']);

    fenixApp.controller('SideBarSectionCtrl', function() {

        this.isOpen = false;

        this.toggle = function() {
            this.isOpen = !this.isOpen;
        };

    });

})();

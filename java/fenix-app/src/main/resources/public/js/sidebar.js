(function(){
    var fxSideBar = angular.module('fxSideBar', []);

    fxSideBar.directive('fxSideBarSection', function() {
        return {
            restrict: 'E',
            transclude: true,
            scope: {
                isOpen: '=?'
            },
            templateUrl: 'templates/fx-side-bar-section.html'
        };
    });

    fxSideBar.directive('fxSideBarSectionHeader', function() {
        return {
            restrict: 'E',
            transclude: true,
            link: function(scope, element, attrs) {
                scope.toggle = function() {
                    scope.isOpen = !scope.isOpen;
                };
            },
            templateUrl: 'templates/fx-side-bar-section-header.html'
        };
    });

    fxSideBar.directive('fxSideBarSectionContent', function() {
        return {
            restrict: 'E',
            transclude: true,
            templateUrl: 'templates/fx-side-bar-section-content.html'
        };
    });

})();

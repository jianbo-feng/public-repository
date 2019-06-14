angular.module('myApp', []).controller('userCtrl', function($scope) {
    $scope.fName = '';
    $scope.lName = '';
    $scope.passw1 = '';
    $scope.passw2 = '';
    $scope.users = [
        {id:1, fName:'Hege', lName:"Pege" },
        {id:2, fName:'Kim',  lName:"Pim" },
        {id:3, fName:'Sal',  lName:"Smith" },
        {id:4, fName:'Jack', lName:"Jones" },
        {id:5, fName:'John', lName:"Doe" },
        {id:6, fName:'Peter',lName:"Pan" }
    ];

    // 当用户点击创建用户时设置为true
    $scope.edit = true;
    // 如果 passw1 不等于 passw2 设置为 true
    $scope.error = false;
    // 如果每个字段都为空(length = 0)设置为 true
    $scope.incomplete = false;

    /**
     * 设置模型变量
     * @param id
     */
    $scope.editUser = function(id) {
        if (id == 'new') {
            $scope.edit = true;
            $scope.incomplete = true;
            $scope.fName = '';
            $scope.lName = '';
        } else {
            $scope.edit = false;
            $scope.fName = $scope.users[id-1].fName;
            $scope.lName = $scope.users[id-1].lName;
        }
    };

    /**
     * 监控模型变量
     */
    $scope.$watch('passw1',function() {$scope.test();});
    $scope.$watch('passw2',function() {$scope.test();});
    $scope.$watch('fName', function() {$scope.test();});
    $scope.$watch('lName', function() {$scope.test();});

    /**
     * 验证模型变量的错误和完整性
     */
    $scope.test = function() {
        if ($scope.passw1 !== $scope.passw2) {
            $scope.error = true;
        } else {
            $scope.error = false;
        }
        $scope.incomplete = false;
        if ($scope.edit && (!$scope.fName.length ||
            !$scope.lName.length ||
            !$scope.passw1.length || !$scope.passw2.length)) {
            $scope.incomplete = true;
        }
    };

});
$(function () {
    $("#jqGrid").jqGrid({
        url: 'sys/sysconfig',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', width: 100, key: true, hidden: true},
            {label: '配置code', name: 'configCode', width: 70},
            {label: '包名', name: 'packageName', width: 100},
            {label: '模块名', name: 'moduleName', width: 100},
            {label: '作者名称', name: 'author', width: 100},
            {label: '邮箱', name: 'email', width: 100},
            {label: '去除表前缀', name: 'tablePrefix', width: 100},
            {label: '是否使用 ', name: 'beUse', width: 100},
            {label: '创建时间', name: 'createTime', width: 100}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            beanName: null
        },
        showList: true,
        title: null,
        databaseConfig: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.databaseConfig = {};
        },
        update: function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }

            vm.showList = false;
            vm.title = "修改";
            $.get("sys/sysconfig/" + id, function (r) {
                vm.databaseConfig = r.data;
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.databaseConfig.id == null ? "sys/sysconfig/save" : "sys/sysconfig/update";
            $.ajax({
                type: "POST",
                url: url,
                contentType: "application/json",
                data: JSON.stringify(vm.databaseConfig),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var jobIds = getSelectedRows();
            if (jobIds == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "DELETE",
                    url: "sys/sysconfig",
                    contentType: "application/json",
                    data: JSON.stringify(jobIds),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                vm.reload();
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'beanName': vm.q.beanName},
                page: page
            }).trigger("reloadGrid");
        }
    }
});
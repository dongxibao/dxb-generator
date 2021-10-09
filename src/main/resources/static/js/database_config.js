$(function () {
    $("#jqGrid").jqGrid({
        url: 'sys/databaseconfig',
        datatype: "json",
        colModel: [
            {label: '数据库id', id: 'id', width: 60, key: true, hidden: true},
            {label: '数据库名称', name: 'name', width: 60},
            {label: '数据库连接地址', name: 'jdbcUrl', width: 100},
            {label: '数据库用户名', name: 'userName', width: 100},
            {label: '数据库密码 ', name: 'passwd', width: 100},
            {label: '是否使用 ', name: 'beUse', width: 100},
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
            $.get("sys/databaseconfig/" + id, function (r) {
                vm.databaseConfig = r.data;
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.databaseConfig.id == null ? "sys/databaseconfig/save" : "sys/databaseconfig/update";
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
            var id = getSelectedRows();
            if (id == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "DELETE",
                    url: "sys/databaseconfig",
                    contentType: "application/json",
                    data: JSON.stringify(id),
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
/*
 * 菜单管理列表页JavaScript
 */
$(document).ready(function() {
    // 查询参数
    var queryParams = {};
    // 列表对象
    var menuTable = $("#menuTable");
    menuTable.datagrid({
        toolbar: "#menuTableToolbar",
        method: "get",
        url: "./sys/menu/list",
        queryParams: queryParams,
        striped: true,
        idField: "id",
        loadMsg: "获取列表数据中...",
        columns: [[{
            field: 'id',
            checkbox: true
        },{
            field: 'menuText',
            title: '文本',
            width: 100
        },{
            field: 'menuType',
            title: '类型',
            width: 100
        },{
            field: 'menuUrl',
            title: '链接地址',
            width: 100
        }]],
        pagination: true,
        pagePosition: "bottom"
    });

    // 查询条件
    $("#menuSearchbox").searchbox({
        prompt: "名称查询",
        searcher: function(value, name) {}
    });
});
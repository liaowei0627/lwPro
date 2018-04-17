/*
 * 菜单管理列表页JavaScript
 */
$(document).ready(function() {
    // 查询参数
    var queryParams = new Object;
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
            title: '菜单文本',
            width: 100
        },{
            field: 'menuType',
            title: '菜单类型',
            width: 100
        },{
            field: 'menuUrl',
            title: '链接地址',
            width: 100
        }]],
        pagination: true,
        pagePosition: "bottom",
        fit: true
    });

    // 查询条件
    var menuSearchbox = $("#menuSearchbox");
    menuSearchbox.searchbox({
        prompt: "名称查询",
        menu: "#queryMenuType",
        searcher: function(value, name) {
            console.info("value=" + value + ";name=" + name);
//            queryParams.menuText = value;
//            queryParams.menuType = name;
        }
    });
    var queryMenuType = menuSearchbox.searchbox("menu");
    $.ajax({
        url: "./category/menu",
        type: "GET",
        cache: false,
        dataType: "json",
        data: {
            categoryType: "menu"
        },
        success: function(result, status) {
            
        },
        error: function(request, message, e) {
            engine.alert("登录失败！", "系统错误，请联系系统管理员！", "error");
        }
    });
    queryMenuType.menu("appendItem", {
        text: "分系统",
        name: "SYSTEM",
        onclick: function() {
            queryMenuType.menu("setIcon", {
                target: queryMenuType.menu("findItem", "分系统").target,
                iconCls: "icon-ok"
            });
        }
    });
    queryMenuType.menu("appendItem", {
        text: "菜单",
        name: "MENU"
    });
    queryMenuType.menu("appendItem", {
        text: "链接",
        name: "LINK"
    });
    queryMenuType.menu("appendItem", {
        text: "按钮",
        name: "BUTTON"
    });
});
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
            queryParams.menuText = value;
            queryParams.menuType = name;
            menuTable.datagrid("reload", queryParams);
        }
    });
    var queryMenuType = menuSearchbox.searchbox("menu");
    $.ajax({
        url: "./category/map_list",
        type: "GET",
        cache: false,
        dataType: "json",
        data: {category: "menu"},
        success: function(result, status) {
            if (1 == result.stat) {
                var data = result.data;
                var name;
                var text;
                for (var i = 0; i < data.length; i++) {
                    name = data[i].name;
                    text = data[i].text;
                    queryMenuType.menu("appendItem", {
                        text: text,
                        name: name
                    });
                };
            } else {
                engine.alert("错误！", result.msg, "error");
            };
        },
        error: function(request, message, e) {
            engine.alert("错误！", "系统错误，请联系系统管理员！", "error");
        }
    });
});
/*
 * 菜单管理列表页JavaScript
 */
$(document).ready(function() {

    // 编辑表单
    var menuForm = $("#menuForm");

    // 菜单编辑表单中的菜单类型下拉框
    var menuTypeCombo = menuForm.find("input[name='menuType']");
    menuTypeCombo.combobox({
        label: "菜单类型：",
        required: true,
        editable: false,
        valueField: "name",
        textField: "text",
        method: "get",
        url: "./category/map_list?category=menu&_=" +  + new Date().getTime(),
        loadFilter: function(result) {
            if (1 == result.stat) {
                return result.data;
            };
        }
    });

    // 菜单编辑表单中的上级菜单下拉树
    var parentIdComboTree = menuForm.find("input[name='parentId']");
    parentIdComboTree.combotree({
        label: "上级菜单：",
        editable: false,
        valueField: "name",
        textField: "text",
        method: "get",
        url: "./sys/menu/tree?_=" +  + new Date().getTime(),
        loadFilter: function(result) {
            if (1 == result.stat) {
                return result.data;
            };
        }
    });

    // 菜单编辑窗口
    var menuWindow = null
    var showMenuWindow = function(id) {
        menuForm.form("clear");
        if (!menuWindow) {
            menuWindow = $("#menuWindow").window({
                title: "编辑菜单",
                width: 400,
                height: 400,
                modal: true,
                collapsible: false,
                minimizable: false,
                maximizable: false,
                resizable: false,
                inline: true,
                closed: true
            });
        } else {
            menuWindow.window("open");
        };
        if (id && id.length == 32) {
            menuForm.form("load", "./sys/menu/load?id=" + id);
        };
    };

    // 查询参数
    var queryParams = {};
    var doQuery = function() {
        queryParams.keyword = menuSearchbox.searchbox("getValue");
        var menuType = menuSearchbox.searchbox("getName");
        if ("ALL" == menuType) {
            menuType = "";
        };
        queryParams.menuType = menuType;
        menuTable.datagrid("reload", queryParams);
    };

    // 打开新增窗口
    var doNew = function() {
        showMenuWindow();
    };

    // 打开编辑窗口
    var doEdit = function(id) {
        showMenuWindow(id);
    };

    // 打开编辑窗口
    var doCopy = function(id) {};

    // 打开编辑窗口
    var doDel = function(id) {};

    // 列表工具栏按钮功能
    $("#btnMenuAdd").click(function() {
        doNew();
    });
    $("#btnMenuDel").click(function() {
        doDel();
    });

    // 列表对象
    var menuTable = $("#menuTable");
    menuTable.datagrid({
        onLoadSuccess: function(data) {
            $("table.datagrid-btable").find("a.btnCopy").linkbutton({
                onClick: function() {
                    var id = $(this).attr("data-id");
                    doCopy(id);
                }
            });
            $("table.datagrid-btable").find("a.btnEdit").linkbutton({
                onClick: function() {
                    var id = $(this).attr("data-id");
                    doEdit(id);
                }
            });
            $("table.datagrid-btable").find("a.btnDel").linkbutton({
                onClick: function() {
                    var id = $(this).attr("data-id");
                    doDel(id);
                }
            });
        },
        method: "get",
        url: "./sys/menu/list",
        queryParams: queryParams,
        striped: true,
        idField: "id",
        loadMsg: "获取列表数据中...",
        frozenColumns: [[{
            title: "操作",
            field: "operator",
            halign: "center",
            align: "center",
            formatter: function(value, rowData, rowIndex) {
                var btns = "";
                btns += "<a class=\"btnCopy\" data-id=\"" + rowData.id + "\" href=\"javascript: void(0);\">复制</a>";
                btns += "<a class=\"btnEdit\" data-id=\"" + rowData.id + "\" href=\"javascript: void(0);\">编辑</a>";
                btns += "<a class=\"btnDel\" data-id=\"" + rowData.id + "\" href=\"javascript: void(0);\">删除</a>";
                return btns;
            },
            width: 150
        }]],
        columns: [[{
            field: "id",
            checkbox: true
        },{
            field: "text",
            title: "菜单文本",
            width: 100,
            halign: "center",
            align: "center"
        },{
            field: "menuType",
            title: "菜单类型",
            width: 100,
            halign: "center",
            align: "center"
        },{
            field: "menuUrl",
            title: "链接地址",
            width: 100,
            halign: "center",
            align: "center"
        }]],
        fitColumns: true,
        pagination: true,
        pagePosition: "bottom",
        pageNumber: 1,
        pageSize: 20,
        pageList: [20,30,40,50],
        fit: true
    });

    // 查询条件菜单文本输入框
    var menuSearchbox = $("#menuSearchbox");
    menuSearchbox.searchbox({
        prompt: "菜单文本",
        menu: "#queryMenuType",
        searcher: function(value, name) {
            doQuery();
        }
    });
    // 组装查询条件类型选项
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

    // 详情窗口按钮功能
    $("#btnMenuFormSave").click(function() {
        engine.progress("open")// 打开进度条
        menuForm.form("submit", {
            url: "./sys/menu/save?_=" +  + new Date().getTime(),
            onSubmit: function(param) {
                var isValid = $(this).form("validate");
                if (!isValid) {
                    engine.progress("close");// 隐藏进度条
                };
                return isValid; // 如果是false会阻止表单提交
            },
            success: function(data) {
                engine.progress("close");// 隐藏进度条
                $("#btnMenuFormCancel").click();
            },
            error: function() {
                engine.progress("close");// 隐藏进度条
                engine.alert("保存失败！", "系统错误，请联系系统管理员！", "error");
            }
        });
    });
    $("#btnMenuFormCancel").click(function() {
        menuForm.form("clear");
        menuWindow.window("close");
    });
});
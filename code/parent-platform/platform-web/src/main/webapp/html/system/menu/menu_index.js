/*
 * 菜单管理列表页JavaScript
 */
$(document).ready(function() {

    // 菜单编辑表单中的菜单类型下拉框
    var menuTypeCombo = $("#menuForm").find("input[name='menuType']");
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
    var parentIdComboTree = $("#menuForm").find("input[name='parentId']");
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
    $("#menuWindow").window({
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

    // 查询参数
    var queryParams = {};

    // 列表工具栏按钮功能
    $("#btnMenuAdd").click(function() {
        $("#menuWindow").window("open");
    });
    $("#btnMenuDel").click(function() {
    });

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
        frozenColumns: [[{
            title: "操作",
            field: "",
            formatter: function(value, rowData, rowIndex) {
                var btns = "<a class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-save',plain:true\" href=\"javascript: void(0);\">编辑</a>";
                btns += "<a class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-cancel',plain:true\" href=\"javascript: void(0);\">删除</a>";
            },
            width: 150
        }]],
        columns: [[{
            field: "id",
            checkbox: true
        },{
            field: "text",
            title: "菜单文本",
            width: 100
        },{
            field: "menuType",
            title: "菜单类型",
            width: 100
        },{
            field: "menuUrl",
            title: "链接地址",
            width: 100
        }]],
        pagination: true,
        pagePosition: "bottom",
        fit: true
    });

    // 查询条件菜单文本输入框
    var menuSearchbox = $("#menuSearchbox");
    menuSearchbox.searchbox({
        prompt: "菜单文本",
        menu: "#queryMenuType",
        searcher: function(value, name) {
            queryParams.text = value;
            queryParams.menuType = name;
            menuTable.datagrid("reload", queryParams);
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
        $("#menuForm").form("submit", {
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
        $("#menuForm").form("clear");
        $("#menuWindow").window("close");
    });
});
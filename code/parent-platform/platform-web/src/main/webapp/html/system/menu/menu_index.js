/*
 * 菜单管理页JavaScript
 */
$(document).ready(function() {
    "use strict";

    // 左侧菜单树
    var menuTree = $("#menuTree").tree({
        method: "get",
        url: "./sys/menu/tree",
        queryParams: {"_":new Date().getTime()},
        lines: true,
        loadFilter: function(result, parent) {
            if (1 == result.stat) {
                if (parent) {
                    return result.data;
                } else {
                    return [{id:"",text:"上级菜单",state:"open",children:result.data}];
                };
            };
        },
        onClick: function(node) {
            queryParams["parentId"] = node.id;
            queryParams["_"] = new Date().getTime();
            menuGrid.datagrid("reload", queryParams);
            menuTree.tree("expand", node.target);
        }
    });

    // 查询参数
    var queryParams = {parentId: ""};

    var doRefresh = function() {
        queryParams["_"] = new Date().getTime();
        menuTree.tree("reload", menuTree.tree("getRoot").target);
        menuGrid.datagrid("reload", queryParams);
    };

    var doQuery = function() {
        queryParams["keyword"] = menuSearchbox.searchbox("getValue");
        queryParams["_"] = new Date().getTime();
        menuGrid.datagrid("reload", queryParams);
    };

    var menuForm;
    // 详情窗口按钮功能
    var doSubmit = function() {
        menuForm.form("submit");
    };
    var doCancel = function() {
        menuForm.form("clear");
        detailDialog.dialog("close");
    }

    // 菜单编辑窗口
    var detailDialog = null;
    var showDetailDialog = function() {
        var url = "./html/system/menu/menu_detail.html";
        detailDialog = engine.showDialog({
            id: "menu_dialog",
            title: "编辑菜单",
            href: url,
            width: 400,
            height: 400,
            modal: true,
            buttons: [{
                text: "保存",
                iconCls: "icon-ok",
                handler: doSubmit
            }, {
                text: "取消",
                iconCls: "icon-clear",
                handler: doCancel
            }],
            onLoad: function() {
                menuForm = $("#menu_dialog").find("form");
            }
        });
        detailDialog.dialog("open");
    };

    // 打开新增窗口
    var doNew = function() {
        engine.setDialogParam();
        showDetailDialog();
    };

    // 打开编辑窗口
    var doEdit = function(id) {
        engine.setDialogParam({id: id});
        showDetailDialog();
    };

    // 打开编辑窗口
    var doCopy = function(id) {
        engine.setDialogParam({id: id, opt: "copy"});
        showDetailDialog();
    };

    // 打开编辑窗口
    var doDel = function(id) {
        var ids = [];
        if (id) {
            ids[0] = id;
        } else {
            var rows = menuGrid.datagrid("getSelections");
            var length = rows.length;
            if (rows && length > 0) {
                for (var i = 0; i < length; i++) {
                    ids.push(rows[i].id);
                };
            } else {
                engine.messager("警告", "必须至少选择一个要删除的菜单！");
            };
        };

        if (ids.length > 0) {
            $.ajax({
                url: "./sys/menu/del",
                type: "POST",
                cache: false,
                dataType: "json",
                data: {ids: ids},
                success: function(result, status) {
                    engine.messager("消息", result.msg);
                    doRefresh();
                },
                error: function(request, message, e) {
                    engine.alert("错误", "系统错误，请联系系统管理员！", "error");
                }
            });
        };
    };

    // 列表工具栏按钮功能
    $("#btnMenuAdd").click(function() {
        doNew();
    });
    $("#btnMenuDel").click(function() {
        doDel();
    });

    // 列表对象
    var menuGrid = $("#menuGrid");
    menuGrid.datagrid({
        onDblClickRow: function(index,row) {
            doEdit(row.id);
        },
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
        queryParams: $.extend({"_": new Date().getTime()}, queryParams),
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
            field: "code",
            title: "菜单编号",
            width: 100,
            halign: "center",
            align: "center"
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
            align: "left"
        },{
            field: "orderNum",
            title: "排序号",
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
        searcher: function(value, name) {
            doQuery();
        }
    });
});
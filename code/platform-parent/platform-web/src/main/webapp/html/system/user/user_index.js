/*
 * 用户管理页JavaScript
 */
$(document).ready(function() {
    "use strict";

    // 列表对象
    var userGrid = $("#userGrid");
    userGrid.datagrid({
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
        url: "./system/user/list",
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
    var menuSearchbox = $("#userSearchbox");
    menuSearchbox.searchbox({
        prompt: "用户名",
        searcher: function(value, name) {
            doQuery();
        }
    });
});
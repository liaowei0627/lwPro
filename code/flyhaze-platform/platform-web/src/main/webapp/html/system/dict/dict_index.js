/*
 * 字典管理页JavaScript
 */
$(document).ready(function() {
    "use strict";

    // 左侧字典树
    var dictionaryTree = $("#dictionaryTree").tree({
        method: "get",
        url: "./system/dict/tree",
        queryParams: {"_":new Date().getTime()},
        lines: true,
        loadFilter: function(result, parent) {
            if (1 == result.stat) {
                if (parent) {
                    return result.data;
                } else {
                    return [{id:"",text:"上级字典",state:"open",children:result.data}];
                };
            };
        },
        onClick: function(node) {
            queryParams["parentId"] = node.id;
            queryParams["_"] = new Date().getTime();
            dictionaryGrid.datagrid("reload", queryParams);
            dictionaryTree.tree("expand", node.target);
        }
    });

    // 查询参数
    var queryParams = {parentId: ""};

    var doRefresh = function() {
        queryParams["_"] = new Date().getTime();
        dictionaryTree.tree("reload", dictionaryTree.tree("getRoot").target);
        dictionaryGrid.datagrid("reload", queryParams);
    };

    var doQuery = function() {
        queryParams["keyword"] = dictionarySearchbox.searchbox("getValue");
        queryParams["_"] = new Date().getTime();
        dictionaryGrid.datagrid("reload", queryParams);
    };

    var dictionaryForm;
    // 详情窗口按钮功能
    var doSubmit = function() {
        engine.confirm("警告", "是否保存字典数据？", function(r) {
            if (r) {
                dictionaryForm.form("submit", {
                    url: "./system/dict/save",
                    queryParams: {"_":new Date().getTime()},
                    onSubmit: function(param) {
                        engine.progress("open")// 打开进度条
                        var isValid = dictionaryForm.form("validate");
                        if (!isValid) {
                            engine.progress("close");// 隐藏进度条
                        };
                        return isValid; // 如果是false会阻止表单提交
                    },
                    success: function(result, state) {
                        engine.progress("close");// 隐藏进度条
                        if (result) {
                            var data = JSON.parse(result);
                            if (1 == data.stat) {
                                engine.messager("消息", data.msg);
                                doRefresh();
                                doCancel();
                            } else {
                                engine.alert("操作失败", data.msg, "error");
                            };
                        };
                    },
                    error: function(result, state, e) {
                        engine.progress("close");// 隐藏进度条
                        engine.alert("操作失败", "系统错误，请联系系统管理员！", "error");
                    }
                });
            };
        });
    };
    var doCancel = function() {
        dictionaryForm.form("clear");
        detailDialog.dialog("close");
    }

    // 字典编辑窗口
    var detailDialog = null;
    var showDetailDialog = function() {
        var url = "./html/system/dict/dict_detail.html";
        detailDialog = engine.showDialog({
            id: "dictionary_dialog",
            title: "编辑字典",
            href: url,
            width: 300,
            height: 260,
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
                dictionaryForm = $("#dictionary_dialog").find("form");
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
        var parent = dictionaryTree.tree("getSelected");
        
        engine.setDialogParam({id: id});
        showDetailDialog();
    };

    // 打开复制窗口
    var doCopy = function(id) {
        engine.setDialogParam({id: id, opt: "copy"});
        showDetailDialog();
    };

    // 删除
    var doDel = function(id) {
        engine.confirm("警告", "是否删除字典数据？", function(r) {
            if (r) {
                var ids = [];
                if (id) {
                    ids[0] = id;
                } else {
                    var rows = dictionaryGrid.datagrid("getSelections");
                    var length = rows.length;
                    if (rows && length > 0) {
                        for (var i = 0; i < length; i++) {
                            ids.push(rows[i].id);
                        };
                    } else {
                        engine.messager("警告", "必须至少选择一个要删除的字典！");
                    };
                };

                if (ids.length > 0) {
                    $.ajax({
                        url: "./system/dict/del",
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
        });
    };

    // 列表工具栏按钮功能
    $("#btnDictionaryAdd").click(function() {
        doNew();
    });
    $("#btnDictionaryDel").click(function() {
        doDel();
    });

    // 列表对象
    var dictionaryGrid = $("#dictionaryGrid");
    dictionaryGrid.datagrid({
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
        url: "./system/dict/list",
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
            title: "编号",
            width: 100,
            halign: "center",
            align: "center"
        },{
            field: "text",
            title: "文本",
            width: 100,
            halign: "center",
            align: "center"
        },{
            field: "fullCode",
            title: "全路径编号",
            width: 100,
            halign: "center",
            align: "center"
        },{
            field: "fullText",
            title: "全路径文本",
            width: 100,
            halign: "center",
            align: "center"
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

    // 查询条件字典文本输入框
    var dictionarySearchbox = $("#dictionarySearchbox");
    dictionarySearchbox.searchbox({
        prompt: "字典文本",
        searcher: function(value, name) {
            doQuery();
        }
    });
});
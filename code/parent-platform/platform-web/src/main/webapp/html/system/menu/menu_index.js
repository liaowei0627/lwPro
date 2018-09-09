/*
 * 菜单管理列表页JavaScript
 */
//$(document).ready(function() {
engine.onload(document, function() {

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
        parentIdComboTree.combotree("tree").tree("reload", parentIdComboTree.combotree("tree").tree("getRoot").target);
        menuGrid.datagrid("reload", queryParams);
    };

    var doQuery = function() {
        queryParams["keyword"] = menuSearchbox.searchbox("getValue");
        var menuType = menuSearchbox.searchbox("getName");
        if ("ALL" == menuType) {
            menuType = "";
        };
        queryParams["menuType"] = menuType;
        queryParams["_"] = new Date().getTime();
        menuGrid.datagrid("reload", queryParams);
    };

    // 详情窗口按钮功能
    var doSubmit = function() {
        var id = menuForm.find(":hidden[name=id]").val();
        var code = codeText.textbox("getValue");
        if (code.length > 0) {
            $.ajax({
                url: "./sys/menu/checkCode",
                type: "GET",
                cache: false,
                dataType: "json",
                data: {id: id, code: code},
                success: function(result, status) {
                    if (1 == result.stat) {
                        engine.messager("消息", result.msg);
                        menuForm.form("submit");
                    } else {
                        engine.alert("警告", result.msg, "warning");
                    };
                },
                error: function(request, message, e) {
                    engine.alert("错误", "系统错误，请联系系统管理员！", "error");
                }
            });
        };
    };
    var doCancel = function() {
        menuForm.form("clear");
        detailDialog.dialog("close");
    }

    // 菜单编辑窗口
    var detailDialog = null;
    var showDetailDialog = function(id, opt) {
        if (detailDialog) {
            detailDialog.dialog("open");
        } else {
            detailDialog = engine.showDialog({
                id: "menu_dialog",
                title: "编辑菜单",
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
                }]
            });
            detailDialog.dialog("open");
        };
        if (id && id.length == 32 && opt) {
            menuForm.form("load", "./sys/menu/load?id=" + id + "&opt=" + opt);
        } else if (id && id.length == 32) {
            menuForm.form("load", "./sys/menu/load?id=" + id);
        };
    };

    // 打开新增窗口
    var doNew = function() {
        showDetailDialog();
    };

    // 打开编辑窗口
    var doEdit = function(id) {
        showDetailDialog(id);
    };

    // 打开编辑窗口
    var doCopy = function(id) {
        showDetailDialog(id, "copy");
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
                engine.alert("错误", result.msg, "error");
            };
        },
        error: function(request, message, e) {
            engine.alert("错误", "系统错误，请联系系统管理员！", "error");
        }
    });

    // 编辑表单
    var menuForm = $("#menuForm").form({
        url: "./sys/menu/save",
        queryParams: {"_":new Date().getTime()},
        onLoadSuccess: function(data) {
            var parent = data.parent;
            if (parent) {
                parentIdComboTree.combotree("setValue", {id:parent.id,text:parent.text});
            } else {
                parentIdComboTree.combotree("setValue", {id:"",text:"上级菜单"});
            };
        },
        onSubmit: function(param) {
            engine.progress("open")// 打开进度条
            var isValid = menuForm.form("validate");
            if (!isValid) {
                engine.progress("close");// 隐藏进度条
            };
            return isValid; // 如果是false会阻止表单提交
        },
        success: function(result) {
            engine.progress("close");// 隐藏进度条
            if (result) {
                var data = JSON.parse(result);
                engine.messager("消息", data.msg);
                doRefresh();
                doCancel();
            };
        },
        error: function() {
            engine.progress("close");// 隐藏进度条
            engine.alert("操作失败", "系统错误，请联系系统管理员！", "error");
        }
    });

    // 编号
    var codeText = menuForm.find("input[name=code]");
    codeText.textbox({
        required: true,
        validType: ["length[1,20]", "illeChar"],
        validateOnCreate:false,
        validateOnBlur:true
    });

    // 菜单编辑表单中的菜单类型下拉框
    var menuTypeCombo = menuForm.find("input[name=menuType]");
    menuTypeCombo.combobox({
        required: true,
        validateOnCreate:false,
        validateOnBlur:true,
        editable: false,
        valueField: "name",
        textField: "text",
        method: "get",
        url: "./category/map_list",
        queryParams: {category:"menu","_":new Date().getTime()},
        loadFilter: function(result) {
            if (1 == result.stat) {
                return result.data;
            };
        }
    });

    // 菜单编辑表单中的上级菜单下拉树
    var parentIdComboTree = menuForm.find("input[name=parentId]");
    parentIdComboTree.combotree({
        editable: false,
        valueField: "id",
        textField: "text",
        method: "get",
        url: "./sys/menu/tree",
        queryParams: {"_":new Date().getTime()},
        loadFilter: function(result, parent) {
            if (1 == result.stat) {
                if (parent) {
                    return result.data;
                } else {
                    return [{id:"",text:"上级菜单",state:"open",children:result.data}];
                };
            };
        }
    });
});
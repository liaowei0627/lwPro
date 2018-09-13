/*
 * 菜单编辑页JavaScript
 */
$(document).ready(function() {
    "use strict";

    var id = engine.getDialogParam("id");
    var opt = engine.getDialogParam("opt");

    // 编辑表单
    var curParent;
    var menuForm = $("#menuForm").form({
        url: "./sys/menu/save",
        queryParams: {"_":new Date().getTime()},
        onLoadSuccess: function(data) {
            curParent = null;
            var parent = data.parent;
            if (parent && parent != "") {
                curParent = parent;
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
        required: true,
        editable: false,
        validateOnCreate:false,
        validateOnBlur:true,
        valueField: "id",
        textField: "text",
        method: "get",
        url: "./sys/menu/tree",
        queryParams: {"_": new Date().getTime()},
        onBeforeLoad: function(node, param) {
            param["excId"] = id;
        },
        loadFilter: function(result, parent) {
            if (1 == result.stat) {
                if (parent) {
                    return result.data;
                } else {
                    return [{id: "", text: "上级菜单", state: "open", checked: true, children: result.data}];
                };
            };
        },
        onLoadSuccess: function(node, data) {
            if (curParent) {
                parentIdComboTree.combotree("setValue", {id: curParent.id, text: curParent.text});
            } else {
                parentIdComboTree.combotree("setValue", {id: "", text: "上级菜单"});
            };
        }
    });

    if (id && id.length == 32 && opt) {
        menuForm.form("load", "./sys/menu/load?id=" + id + "&opt=" + opt);
    } else if (id && id.length == 32) {console.info(id);
        menuForm.form("load", "./sys/menu/load?id=" + id);
    };
});
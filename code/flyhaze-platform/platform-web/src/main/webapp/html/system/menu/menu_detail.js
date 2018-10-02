/*
 * 菜单编辑页JavaScript
 */
$(document).ready(function() {
    "use strict";

    var id = engine.getDialogParam("id");
    var opt = engine.getDialogParam("opt");

    // 编辑表单
    var menuForm = $("#menuForm").form({
        onBeforeLoad: function(param) {
            param["id"] = id;
            if (opt) {
                param["opt"] = opt;
            };
            param["_"] = new Date().getTime();
        },
        onLoadSuccess: function(data) {
            if (data) {
                if (1 == data.stat) {
                    engine.messager("消息", data.msg);
                    var view = data.data;
                    menuForm.find(":hidden[name=id]").val(view.id);
                    menuForm.find("input#code").textbox("setValue", view.code);
                    menuForm.find("input#text").textbox("setValue", view.text);
                    menuTypeCombo.combobox("setValue", view.menuType);
                    subSystemCombo.combobox("setValue", view.subSystem);
                    var parent = view.parent;
                    if (parent) {
                        parentIdComboTree.combotree("setValue", {id: parent.id, text: parent.text});
                    } else {
                        parentIdComboTree.combotree("setValue", {id: "", text: "上级字典"});
                    };
                    menuForm.find("input#orderNum").numberbox("setValue", view.orderNum);
                    menuForm.find("textarea#remark").textbox("setValue", view.remark);
                } else if (0 == data.stat){
                    engine.alert("操作失败", data.msg, "error");
                } else {
                    engine.messager("警告", data.msg);
                };
            };
        }
    });

    // 菜单编辑表单中的菜单类型下拉框
    var menuTypeCombo = menuForm.find("select[name=menuType]");
    menuTypeCombo.combobox({
        required: true,
        validateOnCreate:false,
        validateOnBlur:true,
        editable: false,
        valueField: "name",
        textField: "text",
        method: "get",
        url: "./category/map_list",
        queryParams: {category: "menuType", "_": new Date().getTime()},
        loadFilter: function(result) {
            if (1 == result.stat) {
                return result.data;
            };
        }
    });

    // 菜单编辑表单中的分系统下拉框
    var subSystemCombo = menuForm.find("select[name=subSystem]");
    subSystemCombo.combobox({
        required: true,
        validateOnCreate:false,
        validateOnBlur:true,
        editable: false,
        valueField: "name",
        textField: "text",
        method: "get",
        url: "./category/map_list",
        queryParams: {category: "subSystem", "_": new Date().getTime()},
        loadFilter: function(result) {
            if (1 == result.stat) {
                return result.data;
            };
        }
    });

    // 菜单编辑表单中的上级菜单下拉树
    var parentIdComboTree = menuForm.find("select[name=parentId]");
    parentIdComboTree.combotree({
        required: true,
        editable: false,
        validateOnCreate:false,
        validateOnBlur:true,
        valueField: "id",
        textField: "text",
        method: "get",
        url: "./system/menu/tree",
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
        }
    });

    if (id && id.length == 32) {
        menuForm.form("load", "./system/menu/load");
    };
});
/*
 * 菜单编辑页JavaScript
 */
$(document).ready(function() {
    "use strict";

    var id = engine.getDialogParam("id");
    var curParent = engine.getDialogParam("parent");
    var opt = engine.getDialogParam("opt");

    // 编辑表单
    var menuForm = $("#menuForm");

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
        queryParams: {category: "menu", "_": new Date().getTime()},
        loadFilter: function(result) {
            if (1 == result.stat) {
                return result.data;
            };
        }
    });

    // 菜单编辑表单中的分系统下拉框
    var menuTypeCombo = menuForm.find("input[name=subSystem]");
    menuTypeCombo.combobox({
        required: true,
        validateOnCreate:false,
        validateOnBlur:true,
        editable: false,
        valueField: "name",
        textField: "text",
        method: "get",
        url: "./category/map_list",
        queryParams: {category: "system", "_": new Date().getTime()},
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
        },
        onLoadSuccess: function(node, data) {
            if (id && id.length == 32 && curParent) {
                parentIdComboTree.combotree("setValue", {id: curParent.id, text: curParent.text});
            } else {
                parentIdComboTree.combotree("setValue", {id: "", text: "上级菜单"});
            };
        }
    });

    if (id && id.length == 32 && opt) {
        menuForm.form("load", "./system/menu/load?id=" + id + "&opt=" + opt);
    } else if (id && id.length == 32) {
        menuForm.form("load", "./system/menu/load?id=" + id);
    };
});
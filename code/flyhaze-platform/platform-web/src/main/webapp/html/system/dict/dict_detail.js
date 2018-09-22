/*
 * 字典编辑页JavaScript
 */
$(document).ready(function() {
    "use strict";

    var id = engine.getDialogParam("id");
    var curParent = engine.getDialogParam("parent");
    var opt = engine.getDialogParam("opt");

    // 编辑表单
    var dictionaryForm = $("#dictionaryForm");

    // 字典编辑表单中的上级字典下拉树
    var parentIdComboTree = dictionaryForm.find("input[name=parentId]");
    parentIdComboTree.combotree({
        required: true,
        editable: false,
        validateOnCreate:false,
        validateOnBlur:true,
        valueField: "id",
        textField: "text",
        method: "get",
        url: "./system/dict/tree",
        queryParams: {"_": new Date().getTime()},
        onBeforeLoad: function(node, param) {
            param["excId"] = id;
        },
        loadFilter: function(result, parent) {
            if (1 == result.stat) {
                if (parent) {
                    return result.data;
                } else {
                    return [{id: "", text: "上级字典", state: "open", checked: true, children: result.data}];
                };
            };
        },
        onLoadSuccess: function(node, data) {
            if (id && id.length == 32 && curParent) {
                parentIdComboTree.combotree("setValue", {id: curParent.id, text: curParent.text});
            } else {
                parentIdComboTree.combotree("setValue", {id: "", text: "上级字典"});
            };
        }
    });

    if (id && id.length == 32 && opt) {
        dictionaryForm.form("load", "./system/dict/load?id=" + id + "&opt=" + opt);
    } else if (id && id.length == 32) {
        dictionaryForm.form("load", "./system/dict/load?id=" + id);
    };
});
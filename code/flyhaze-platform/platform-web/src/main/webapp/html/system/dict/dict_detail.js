/*
 * 字典编辑页JavaScript
 */
$(document).ready(function() {
    "use strict";

    var id = engine.getDialogParam("id");
    var opt = engine.getDialogParam("opt");

    // 编辑表单
    var dictionaryForm = $("#dictionaryForm").form({
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
                    dictionaryForm.find(":hidden[name=id]").val(view.id);
                    dictionaryForm.find("input#code").textbox("setValue", view.code);
                    dictionaryForm.find("input#text").textbox("setValue", view.text);
                    var parent = view.parent;
                    if (parent) {
                        parentIdComboTree.combotree("setValue", {id: parent.id, text: parent.text});
                    } else {
                        parentIdComboTree.combotree("setValue", {id: "", text: "上级字典"});
                    };
                    dictionaryForm.find("input#orderNum").numberbox("setValue", view.orderNum);
                    dictionaryForm.find("input#remark").textbox("setValue", view.remark);
                } else if (0 == data.stat){
                    engine.alert("操作失败", data.msg, "error");
                } else {
                    engine.messager("警告", data.msg);
                };
            };
        }
    });

    // 字典编辑表单中的上级字典下拉树
    var parentIdComboTree = dictionaryForm.find("select[name=parentId]");
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
        }
    });

    if (id && id.length == 32) {
        dictionaryForm.form("load", "./system/dict/load");
    };
});
/*
 * 权限编辑页JavaScript
 */
$(document).ready(function() {
    "use strict";

    var id = engine.getDialogParam("id");
    var opt = engine.getDialogParam("opt");

    // 编辑表单
    var authForm = $("#authForm").form({
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
                    authForm.find(":hidden[name=id]").val(view.id);
                    authForm.find("input#authCode").textbox("setValue", view.authCode);
                    authForm.find("input#authName").textbox("setValue", view.authName);
                    authTypeCombo.combobox("setValue", view.authType);
                    authForm.find("textarea#remark").textbox("setValue", view.remark);
                } else if (0 == data.stat){
                    engine.alert("操作失败", data.msg, "error");
                } else {
                    engine.messager("警告", data.msg);
                };
            };
        }
    });

    // 菜单类型下拉框
    var authTypeCombo = authForm.find("select[name=authType]");
    authTypeCombo.combobox({
        required: true,
        validateOnCreate:false,
        validateOnBlur:true,
        editable: false,
        valueField: "name",
        textField: "text",
        method: "get",
        url: "./category/map_list",
        queryParams: {category: "authType", "_": new Date().getTime()},
        loadFilter: function(result) {
            if (1 == result.stat) {
                return result.data;
            };
        }
    });

    if (id && id.length == 32) {
        authForm.form("load", "./system/auth/load");
    };
});
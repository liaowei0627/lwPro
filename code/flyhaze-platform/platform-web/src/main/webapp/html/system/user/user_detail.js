/*
 * 用户编辑页JavaScript
 */
$(document).ready(function() {
    "use strict";

    var id = engine.getDialogParam("id");
    var opt = engine.getDialogParam("opt");

    // 编辑表单
    var userForm = $("#userForm").form({
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
                    userForm.find(":hidden[name=id]").val(view.id);
                    userForm.find("input#userName").textbox("setValue", view.userName);
                    subSystemCombo.combobox("setValue", view.subSystem);
                    userForm.find("textarea#remark").textbox("setValue", view.remark);
                } else if (0 == data.stat){
                    engine.alert("操作失败", data.msg, "error");
                } else {
                    engine.messager("警告", data.msg);
                };
            };
        }
    });

    // 分系统下拉框
    var subSystemCombo = menuForm.find("select[name=subSystem]");
    subSystemCombo.combobox({
        validateOnCreate:false,
        validateOnBlur:true,
        editable: false,
        multiple: true,
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

    if (id && id.length == 32) {
        userForm.form("load", "./system/user/load");
    };
});
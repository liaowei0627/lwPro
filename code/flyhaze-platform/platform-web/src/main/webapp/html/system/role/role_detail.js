/*
 * 权限编辑页JavaScript
 */
$(document).ready(function() {
    "use strict";

    var id = engine.getDialogParam("id");
    var opt = engine.getDialogParam("opt");

    // 编辑表单
    var roleForm = $("#roleForm").form({
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
                    roleForm.find(":hidden[name=id]").val(view.id);
                    roleForm.find("input#roleCode").textbox("setValue", view.roleCode);
                    roleForm.find("input#roleName").textbox("setValue", view.roleName);
                    roleTypeCombo.combobox("setValue", view.roleType);
                    roleForm.find("textarea#remark").textbox("setValue", view.remark);
                } else if (0 == data.stat){
                    engine.alert("操作失败", data.msg, "error");
                } else {
                    engine.messager("警告", data.msg);
                };
            };
        }
    });

    // 菜单编辑表单中的菜单类型下拉框
    var roleTypeCombo = roleForm.find("select[name=roleType]");
    roleTypeCombo.combobox({
        required: true,
        validateOnCreate:false,
        validateOnBlur:true,
        editable: false,
        valueField: "name",
        textField: "text",
        method: "get",
        url: "./category/map_list",
        queryParams: {category: "roleType", "_": new Date().getTime()},
        loadFilter: function(result) {
            if (1 == result.stat) {
                return result.data;
            };
        }
    });

    if (id && id.length == 32) {
        roleForm.form("load", "./system/role/load");
    };
});
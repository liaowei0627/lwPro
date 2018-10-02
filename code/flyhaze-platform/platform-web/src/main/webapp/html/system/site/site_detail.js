/*
 * 站点编辑页JavaScript
 */
$(document).ready(function() {
    "use strict";

    var id = engine.getDialogParam("id");
    var opt = engine.getDialogParam("opt");

    // 编辑表单
    var siteForm = $("#siteForm").form({
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
                    siteForm.find(":hidden[name=id]").val(view.id);
                    siteForm.find("input#siteCode").textbox("setValue", view.siteCode);
                    siteForm.find("input#siteName").textbox("setValue", view.siteName);
                    siteForm.find("textarea#remark").textbox("setValue", view.remark);
                } else if (0 == data.stat){
                    engine.alert("操作失败", data.msg, "error");
                } else {
                    engine.messager("警告", data.msg);
                };
            };
        }
    });

    if (id && id.length == 32) {
        siteForm.form("load", "./system/site/load");
    };
});
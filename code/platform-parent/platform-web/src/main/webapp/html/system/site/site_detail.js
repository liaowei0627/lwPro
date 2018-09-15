/*
 * 站点编辑页JavaScript
 */
$(document).ready(function() {
    "use strict";

    var id = engine.getDialogParam("id");
    var opt = engine.getDialogParam("opt");

    // 编辑表单
    var siteForm = $("#siteForm").form({
        url: "./system/site/save",
        queryParams: {"_":new Date().getTime()},
        onSubmit: function(param) {
            engine.progress("open")// 打开进度条
            var isValid = siteForm.form("validate");
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

    if (id && id.length == 32 && opt) {
        siteForm.form("load", "./system/site/load?id=" + id + "&opt=" + opt);
    } else if (id && id.length == 32) {console.info(id);
        siteForm.form("load", "./system/site/load?id=" + id);
    };
});
/*
 * 站点编辑页JavaScript
 */
$(document).ready(function() {
    "use strict";

    var id = engine.getDialogParam("id");
    var opt = engine.getDialogParam("opt");

    // 编辑表单
    var siteForm = $("#siteForm");

    if (id && id.length == 32 && opt) {
        siteForm.form("load", "./system/site/load?id=" + id + "&opt=" + opt);
    } else if (id && id.length == 32) {console.info(id);
        siteForm.form("load", "./system/site/load?id=" + id);
    };
});
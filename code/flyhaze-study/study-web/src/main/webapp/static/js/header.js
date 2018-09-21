$(document).ready(function() {
    // 个人中心
    $("#menuPersonal").click(function() {
        // TODO
    });

    // 退出登录
    $("#menuLogout").click(function() {
        engine.progress("open")// 打开进度条
        $.ajax({
            url: contextPath + "/logout",
            type: "GET",
            cache: false,
            dataType: "json",
            success: function(result, status) {
                engine.progress("close");// 隐藏进度条
                engine.login();
            },
            error: function(request, message, e) {
                engine.progress("close");// 隐藏进度条
                engine.logged();
            }
        });
    });

    // 关于我们
    $("#menuAbout").click(function() {
        // TODO
    });
});
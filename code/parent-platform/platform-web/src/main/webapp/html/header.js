$(document).ready(function() {
    var navigationBar = $("#navigationBar");
    var sessionUserInfo = $("#sessionUserInfo");
    var btnUserName = sessionUserInfo.find("a#btnUserName");

    showUserInfo = function() {
        if (sessionUser) {
            btnUserName.text(sessionUser.userName);
            sessionUserInfo.css("display", "inline-block");
            navigationBar.css("display", "inline-block");
            btnUserName.splitbutton({
                menu: "#userMenus"
            });
        } else {
            btnUserName.text("");
            sessionUserInfo.css("display", "none");
            navigationBar.css("display", "none");
        };
    };
    showUserInfo();

    // 个人中心
    $("#menuPersonal").click(function() {
        // TODO
    });

    // 退出登录
    $("#menuLogout").click(function() {
        engine.progress("open")// 打开进度条
        $.ajax({
            url: "./logout",
            type: "GET",
            cache: false,
            dataType: "json",
            success: function(result, status) {
                engine.progress("close");// 隐藏进度条
                sessionUser = null;
                showUserInfo();
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
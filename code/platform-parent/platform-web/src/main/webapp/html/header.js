/*
 * 头部JavaScript
 */
$(document).ready(function() {
    "use strict";

    // 导航栏
    var navigationBar = $("#navigationBar");
    var loadNavigation = function() {
        if (sessionUser) {
            var navigationList = sessionUser.menuList;
            if (navigationList && navigationList.length > 0) {
                var navigation;
                var attributes;
                var navigationCode;
                var navigationText;
                var navigationUrl;
                var navigationLinkbutton;
                for (var i = 0; i < navigationList.length; i++) {
                    navigation = navigationList[i];
                    attributes = navigation.attributes;
                    navigationCode = attributes.fullCode;
                    navigationText = navigation.text;
                    navigationUrl = attributes.menuUrl;
                    navigationLinkbutton = $("<a id=\"sys_" + navigationCode + "\">" + navigationText + "</a>");
                    navigationBar.append(navigationLinkbutton);
                    if ("首页" == navigationText) {
                        navigationLinkbutton.linkbutton({
                            size: "large",
                            iconAlign: "top",
                            plain: true,
                            onClick: function() {
                                engine.logged();
                            }
                        });
                    } else {
                        navigationLinkbutton.linkbutton({
                            size: "large",
                            iconAlign: "top",
                            plain: true,
                            onClick: function() {
                                engine.loadSystem(navigationUrl, navigationText, navigationCode);
                            }
                        });
                    };
                };
            };
        };
    };

    var clearNavigation = function() {
        navigationBar.empty();
    };

    var sessionUserInfo = $("#sessionUserInfo");
    var btnUserName = sessionUserInfo.find("a#btnUserName");

    showUserInfo = function() {
        if (sessionUser) {
            btnUserName.text(sessionUser.userName);
            sessionUserInfo.css("display", "inline-block");
            loadNavigation();
            navigationBar.css("display", "inline-block");
            btnUserName.menubutton({
                size: "large",
                iconCls: "icon-man",
                menu: "#userMenus"
            });
        } else {
            btnUserName.text("");
            clearNavigation();
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
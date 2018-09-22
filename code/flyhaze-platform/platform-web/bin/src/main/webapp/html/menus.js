/*
 * 左侧菜单栏JavaScript
 */
$(document).ready(function() {
    "use strict";

    // 菜单栏设置
    $("#menubar").accordion({
        fit: true
    });

    // 菜单链接点击事件
    var doMenuClick = function(node) {
        var attributes = node.attributes;
        engine.loadModel(attributes.url, node.text, node.id);
    };

    var menuGroup;
    var menuGroupBody;
    // 加载菜单
    if (currentSystemId.length > 0) {
        var menubar = $("#menubar");

        var navigationVos = sessionUser.menuList;
        if (navigationVos && navigationVos.length > 0) {
            var navigationVo;
            var navigationAttributes;
            var menuGroupVos;
            var menuGroupVo;
            var menuGroupAttributes;
            var menuGroupCode;
            var menuGroupText;
            var menuVos;
            var menuVo;
            var menuAttributes;
            var menuCode;
            var menuText;
            var menuUrl;
            var menus;
            for (var i = 0; i < navigationVos.length; i++) {
                navigationVo = navigationVos[i];
                navigationAttributes = navigationVo.attributes;
                if (currentSystemId == navigationAttributes.fullCode) {
                    menuGroupVos = navigationVo.children;
                    if (menuGroupVos && menuGroupVos.length > 0) {
                        for (var j = 0; j < menuGroupVos.length; j++) {
                            menuGroupVo = menuGroupVos[j];
                            menuGroupAttributes = menuGroupVo.attributes;
                            menuGroupCode = menuGroupAttributes.fullCode;
                            menuGroupText = menuGroupVo.text;
                            menuVos = menuGroupVo.children;

                            menubar.accordion("add", {
                                title: menuGroupText
                            });
                            menuGroup = menubar.accordion("getPanel", menuGroupText);
                            menuGroupBody = menuGroup.panel("body");

                            if (menuVos && menuVos.length > 0) {
                                menus = [];
                                for (var k = 0; k < menuVos.length; k++) {
                                    menuVo = menuVos[k];
                                    menuAttributes = menuVo.attributes;
                                    menuCode = menuAttributes.fullCode;
                                    menuText = menuVo.text;
                                    menuUrl = menuAttributes.menuUrl;
                                    menus[k] = {
                                        id: menuCode,
                                        text: menuText,
                                        attributes: {
                                            url: menuUrl
                                        }
                                    };
                                };
                            };

                            menuGroupBody.append("<ul id=\"" + menuGroupCode + "\"></ul>");
                            menubar.find("ul#" + menuGroupCode).tree({
                                data: menus,
                                onClick: doMenuClick
                            });
                        };
                    };
                };
            };
        };
        menubar.accordion("select", 0);
    };
});
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
    var doMenuLink = function(node) {
        var data = node.attributes;
        engine.loadModel(data.url, node.text, node.id);
    };

    var menu;
    var menuBody;
    // 加载菜单
    if (currentSystemId.length > 0) {
        var menubar = $("#menubar");

        var systemVos = sessionUser.menuList;
        if (systemVos && systemVos.length > 0) {
            var systemVo;
            var menuVos;
            var menuVo;
            var menuCode;
            var menuText;
            var linkVos;
            var linkVo;
            var links;
            var linkCode;
            var linkText;
            var linkUrl;
            for (var i = 0; i < systemVos.length; i++) {
                systemVo = systemVos[i];
                if (currentSystemId == systemVo.fullCode) {
                    menuVos = systemVo.children;
                    if (menuVos && menuVos.length > 0) {
                        for (var j = 0; j < menuVos.length; j++) {
                            menuVo = menuVos[j];
                            menuCode = menuVo.fullCode;
                            menuText = menuVo.text;
                            linkVos = menuVo.children;

                            menubar.accordion("add", {
                                title: menuText
                            });
                            menu = menubar.accordion("getPanel", menuText);
                            menuBody = menu.panel("body");

                            if (linkVos && linkVos.length > 0) {
                                links = [];
                                for (var k = 0; k < linkVos.length; k++) {
                                    linkVo = linkVos[k];
                                    linkCode = linkVo.fullCode;
                                    linkText = linkVo.text;
                                    linkUrl = linkVo.menuUrl;
                                    links[k] = {
                                        id: linkCode,
                                        text: linkText,
                                        attributes: {
                                            url: linkUrl
                                        }
                                    };
                                };
                            };

                            menuBody.append("<ul id=\"" + menuCode + "\"></ul>");
                            menubar.find("ul#" + menuCode).tree({
                                data: links,
                                onClick: doMenuLink
                            });
                        };
                    };
                };
            };
        };
        menubar.accordion("select", 0);
    };
});
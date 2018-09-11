/*
 * 左侧菜单栏JavaScript
 */
engine.onload(document, function() {
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
    console.info(sessionUser);
    console.info(currentSystemId);
    if (currentSystemId.length > 0) {
        var menubar = $("#menubar");
        menubar.accordion("add", {
            title: "系统设置"
        });
        menu = menubar.accordion("getPanel", "系统设置");
        menuBody = menu.panel("body");
        menuBody.append("<ul id=\"abcde\"></ul>");
        $("ul#abcde").tree({
            data: [{
                id: "111",
                text: "数据字典管理",
                attributes: {
                    url: "./html/system/dict/dict_index.html"
                }
            },{
                id: "222",
                text: "站点管理",
                attributes: {
                    url: "./html/system/site/site_index.html"
                }
            },{
                id: "333",
                text: "菜单管理",
                attributes: {
                    url: "./html/system/menu/menu_index.html"
                }
            }],
            onClick: doMenuLink
        });

        menubar.accordion("add", {
            title: "系统安全"
        });
        menu = menubar.accordion("getPanel", "系统安全");
        menuBody = menu.panel("body");
        menuBody.append("<ul id=\"fghij\"></ul>");
        $("ul#fghij").tree({
            data: [{
                id: "444",
                text: "权限管理",
                attributes: {
                    url: "./html/system/auth/auth_index.html"
                }
            },{
                id: "555",
                text: "角色管理",
                attributes: {
                    url: "./html/system/role/role_index.html"
                }
            },{
                id: "666",
                text: "用户管理",
                attributes: {
                    url: "./html/system/user/user_index.html"
                }
            }],
            onClick: doMenuLink
        });
        menubar.accordion("select", 0);
    };
});
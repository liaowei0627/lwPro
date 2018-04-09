/**
 * 核心框架js
 */
var engine;
var isInLoginPage = false;
$(document).ready(function() {
    var mainPanel = $("#main_content");

    // 通用方法对象
    engine = new Object;
    // 打开首页
    engine.logged = function() {
        mainPanel.panel("setTitle", "首页");
        mainPanel.panel("refresh", contextPath + "/home.htm?_=" + new Date().getTime());
        isInLoginPage = false;
    };
    // 打开登录页
    engine.login = function() {
        mainPanel.panel("setTitle", "登录页");
        mainPanel.panel("refresh", contextPath + "/login.htm?_=" + new Date().getTime());
        isInLoginPage = true;
    };
    // 使engine对象不可被修改
    Object.freeze(engine);

    engine.login();

    // H5服务器事件，发现登录状态失效时自动前往登陆页
    if(typeof(EventSource) == "undefined") {// 判断浏览器是否支持
        // 不支持就无法使用此对象╮（╯＿╰）╭
    } else {
        // 服务器事件对象
        var eventSource = new EventSource(contextPath + "/checkLogged");
        // 添加message事件监听
        eventSource.addEventListener("message", function(e) {
            // 返回false时前往登陆页
            if ("false" == e.data) {
                if (!isInLoginPage) {
                    engine.login();
                };
            };
        });
    };
});
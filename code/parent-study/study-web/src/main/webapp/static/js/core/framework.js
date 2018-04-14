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
    // 进度条
    engine.progress = function(action) {
        if ("open" == action) {
            $.messager.progress();
        } else {
            $.messager.progress(action);
        };
    };
    // 对字符串md5编码，返回16进制字符串
    engine.md5 = function(plaintext) {
        return SparkMD5.hash(plaintext).toUpperCase();
    };
    // 将字符串转为16进制字符串
    engine.stringToHex = function(str){
        var val="";
        for(var i = 0; i < str.length; i++){
            if(val == "")
                val = str.charCodeAt(i).toString(16);
            else
                val += str.charCodeAt(i).toString(16);
        }
        return val.toUpperCase();
    }
    // 对字符串进行base64编码，返回16进制字符串
    engine.base64 = function(plaintext) {
        return this.stringToHex(window.btoa(window.encodeURIComponent(plaintext)));
    };
    /** 
     * 提示框
     * title 标题
     * content 内容
     * type  可选项，值可以是error、info、question、warning
     */
    engine.alert = function(title, content, type) {
        if (type && type.length > 0) {
            $.messager.alert(title, content, type);
        } else {
            $.messager.alert(title, content);
        };
    };
    // 使engine对象不可被修改
    Object.freeze(engine);

//    engine.login();

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
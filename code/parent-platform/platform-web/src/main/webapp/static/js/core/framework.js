/**
 * 核心框架js
 */
var engine;
var isInLoginPage = false;
var sessionUser = null;
var currentSystemId = "";
var showUserInfo = function() {};
$(document).ready(function() {
    var bodyPanel = $("#body_layout");
    var headerPanel = bodyPanel.layout("panel", "north");
    var footerPanel = bodyPanel.layout("panel", "south");
    var mainPanel = bodyPanel.layout("panel", "center");
    var menuPanel = null;

    // 通用方法对象
    engine = new Object;
    // 加载左侧菜单栏
    engine.loadMenus = function(title, sysId) {
        if (!menuPanel) {
            bodyPanel.layout("add", {
                region: "west",
                width: 180,
                split: true,
                title: title
            });
            menuPanel = bodyPanel.layout("panel", "west");
        };
        currentSystemId = sysId;
        menuPanel.panel("refresh", "./html/menus.html?_=" + new Date().getTime());
    };
    // 销毁菜单栏
    engine.removeMenus = function() {
        if (menuPanel) {
            bodyPanel.layout("remove", "west");
            menuPanel = null;
        };
        currentSystemId = "";
    };
    // 打开分系统首页
    engine.loadSystem = function(url, title, sysId) {
        this.loadMenus(title, sysId);
        mainPanel.panel("setTitle", title);
        mainPanel.panel("refresh", url + "?_=" + new Date().getTime());
    };
    // 打开模块首页
    engine.loadModel = function(url, title, modId) {
        mainPanel.panel("setTitle", title);
        mainPanel.panel("refresh", url + "?_=" + new Date().getTime());
    };
    // 加载header部分
    engine.loadHeader = function() {
        headerPanel.panel("refresh", "./html/header.html?_=" + new Date().getTime());
    };
    // 加载footer部分
    engine.loadFooter = function() {
        footerPanel.panel("refresh", "./html/footer.html?_" + new Date().getTime());
    };
    // 打开首页
    engine.logged = function() {
        engine.removeMenus();
        showUserInfo();
        mainPanel.panel("setTitle", "首页");
        mainPanel.panel("refresh", "./html/home.html?_=" + new Date().getTime());
        isInLoginPage = false;
    };
    // 打开登录页
    engine.login = function() {
        engine.removeMenus();
        mainPanel.panel("setTitle", "登录页");
        mainPanel.panel("refresh", "./html/login.html?_=" + new Date().getTime());
        isInLoginPage = true;
    };
    // 进度条：open / cloase
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

    // 加载默认页面
    engine.loadFooter();
    // 检查是否已登录
    $.ajax({
        url: "./checkLogged",
        type: "GET",
        cache: false,
        dataType: "json",
        success: function(result, status) {
            if (1 == result.stat) {
                sessionUser = result.data;
                engine.loadHeader();
                engine.logged();
            } else {
                engine.loadHeader();
                engine.login();
            };
        },
        error: function(request, message, e) {
            engine.progress("close");// 隐藏进度条
            engine.alert("登录失败！", "系统错误，请联系系统管理员！", "error");
        }
    });

    // H5服务器事件，发现登录状态失效时自动前往登陆页
    /*if(typeof(EventSource) == "undefined") {// 判断浏览器是否支持
        // 不支持就无法使用此对象╮（╯＿╰）╭
    } else {
        // 服务器事件对象
        var eventSource = new EventSource("./checkLogged");
        // 添加message事件监听
        eventSource.addEventListener("message", function(e) {
            // 返回false时前往登录页
            if ("false" == e.data) {
                if (!isInLoginPage) {
                    engine.login();
                };
            };
        });
        // 添加error事件监听
        eventSource.addEventListener("error", function(e) {
            // 请求出错时直接前往登录页
            //engine.login();
        });
    };*/
});
/**
 * 通用js
 */
var engine;
var isInLoginPage = false;
var sessionUser = null;
var currentSystemId = "";
var showUserInfo = function() {};
$(document).ready(function() {
    "use strict";

    // 通用方法对象
    var engineClass = function(bodyPanel) {
        /**
         * 主页layout对象
         */
        this.bodyPanel = bodyPanel;
        /**
         * 主页North Panel对象
         */
        this.headerPanel = bodyPanel.layout("panel", "north");
        /**
         * 主页South Panel对象
         */
        this.footerPanel = bodyPanel.layout("panel", "south");
        /**
         * 主页Center Panel对象
         */
        this.mainPanel = bodyPanel.layout("panel", "center");
        /**
         * 主页West Panel对象
         */
//        this.menuPanel = null;
        /**
         * 加载左侧菜单栏
         * title Panel标题
         * sysId 分系统Id
         */
        this.loadMenus = function(title, sysId) {
            this.removeMenus();
            this.bodyPanel.layout("add", {
                region: "west",
                width: 180,
                split: true,
                title: title
            });
            currentSystemId = sysId;
            var menuPanel = this.bodyPanel.layout("panel", "west");
            menuPanel.panel("refresh", "./html/menus.html?_=" + new Date().getTime());
        };
        /**
         * 销毁菜单栏
         */
        this.removeMenus = function() {
            this.bodyPanel.layout("remove", "west");
            currentSystemId = "";
        };
        /**
         * 打开分系统首页
         * url 链接地址
         * title West Panel标题
         * sysId 分系统Id
         */
        this.loadSystem = function(url, title, sysId) {
            this.loadMenus(title, sysId);
            this.mainPanel.panel("setTitle", title);
            this.mainPanel.panel("refresh", url + "?_=" + new Date().getTime());
        };
        /**
         * 打开模块首页
         * url 链接地址
         * title Center Panel标题
         * modId 链接Id
         */
        this.loadModel = function(url, title, modId) {
            this.mainPanel.panel("setTitle", title);
            this.mainPanel.panel("refresh", url + "?_=" + new Date().getTime());
        };
        /**
         * 加载header部分
         */
        this.loadHeader = function() {
            this.headerPanel.panel("refresh", "./html/header.html?_=" + new Date().getTime());
        };
        /**
         * 加载footer部分
         */
        this.loadFooter = function() {
            this.footerPanel.panel("refresh", "./html/footer.html?_" + new Date().getTime());
        };
        /**
         * 打开首页
         */
        this.logged = function() {
            this.removeMenus();
            showUserInfo();
            this.mainPanel.panel("setTitle", "首页");
            this.mainPanel.panel("refresh", "./html/home.html?_=" + new Date().getTime());
//            isInLoginPage = false;
        };
        /**
         * 打开登录页
         */
        this.login = function() {
            this.removeMenus();
            this.mainPanel.panel("setTitle", "登录页");
            this.mainPanel.panel("refresh", "./html/login.html?_=" + new Date().getTime());
//            isInLoginPage = true;
        };
        /**
         * 进度条
         * action 动作 open / close
         */
        this.progress = function(action) {
            if ("open" == action) {
                $.messager.progress();
            } else {
                $.messager.progress(action);
            };
        };
        /**
         * 对字符串md5编码，返回16进制字符串
         * plaintext 要编码的字符串
         */
        this.md5 = function(plaintext) {
            return SparkMD5.hash(plaintext).toUpperCase();
        };
        /**
         * 将字符串转为16进制字符串
         * str 要转换的字符串
         */
        this.stringToHex = function(str){
            var val="";
            for(var i = 0; i < str.length; i++){
                if(val == "")
                    val = str.charCodeAt(i).toString(16);
                else
                    val += str.charCodeAt(i).toString(16);
            }
            return val.toUpperCase();
        }
        /**
         * 对字符串进行base64编码，返回16进制字符串
         * plaintext 要编码的字符串
         */
        this.base64 = function(plaintext) {
            return this.stringToHex(window.btoa(window.encodeURIComponent(plaintext)));
        };
        /** 
         * 警告提示窗口
         * title 标题
         * content 内容
         * type  可选项，值可以是error、info、question、warning
         */
        this.messager = function(title, content) {
            $.messager.show({
                title: title,
                msg: content
            });
        };
        /** 
         * 警告提示窗口
         * title 标题
         * content 内容
         * type  可选项，值可以是error、info、question、warning
         */
        this.alert = function(title, content, type) {
            if (type && type.length > 0) {
                $.messager.alert(title, content, type);
            } else {
                $.messager.alert(title, content);
            };
        };
        /**
         * 弹窗
         * dialogProp 参数对象
         * {
         *     id: 弹窗div标签id,
         *     title: 弹窗title,
         *     href: 弹窗页面url,
         *     width: 弹窗宽,
         *     height: 弹窗高,
         *     modal: 是否模式弹窗,
         *     buttons: 底部按钮,
         *     onLoad: 弹窗加载后回调函数
         * }
         */
        this.showDialog = function(dialogProp) {
            var dialog = $("<div></div>");
            dialog.attr("id", dialogProp.id);
            dialog.dialog({
                title: dialogProp.title,
                href: dialogProp.href,
                queryParams: {"_": new Date().getTime()},
                width: dialogProp.width,
                height: dialogProp.height,
                closed: true,
                cache: false,
                modal: dialogProp.modal,
                buttons: dialogProp.buttons,
                onLoad: dialogProp.onLoad,
                onClose: function() {
                    $(this).dialog('destroy');
                    dialog.remove();
                }
            });
            return dialog;
        };

        var dialogParam = {};
        this.setDialogParam = function(param) {
            console.info(param);
            if (param) {
                dialogParam = param;
            } else {
                dialogParam = {};
            };
        };

        this.getDialogParam = function(key) {
            var param = dialogParam;
            console.info(key);
            console.info(param);
            console.info(param[key]);
            return param[key];
        };
    };
    var bodyPanel = $("#body_layout");
    engine = new engineClass(bodyPanel);
    // 使engine对象不可被修改
//    Object.seal(engine);
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
/**
 * 登录页js
 */
$(document).ready(function() {
    // 用户名
    var boxUserName = $("#login_form").find("input#userName");
    boxUserName.textbox({
        prompt: '在此输入用户名',
        label: '用户名：',
        labelAlign: 'right',
        width: 250,
        required: true,
        missingMessage: "请输入用户名！",
        tipPosition: "top"
    });

    // 密码
    var boxPassword = $("#login_form").find("input#password");
    boxPassword.passwordbox({
        prompt: '在此输入密码',
        label: '密码：',
        labelAlign: 'right',
        width: 250,
        required: true,
        missingMessage: "请输入密码！",
        tipPosition: "top"
    });

    // 验证码
    var boxKaptcha = $("#login_form").find("input#kaptcha");
    boxKaptcha.textbox({
        prompt: '在此输入验证码',
        label: '验证码：',
        labelAlign: 'right',
        width: 250,
        required: true,
        missingMessage: "请输入验证码！",
        validType: {
            remote: [contextPath + "/kaptcha/checkKaptchaCode", "kaptchaCode"]
        },
        invalidMessage: "验证码输入错误！",
        tipPosition: "top"
    });

    // 验证码图片
    var imgKaptcha = $("#login_form").find("img#imgKaptcha");
    imgKaptcha.attr("src", contextPath + "/kaptcha/getKaptchaCode?_=" + new Date().getTime());

    // 更换验证码
    var aChangeKaptcha = $("#login_form").find("a#changeKaptcha");
    aChangeKaptcha.click(function() {
        imgKaptcha.attr("src", contextPath + "/kaptcha/getKaptchaCode?_=" + new Date().getTime());
    });

    // 表单
    var loginForm = $("#login_form");
    loginForm.form("clear");

    var loginSubmit = function() {
        loginForm.form("submit", {
            url: contextPath + "/login",
            onSubmit: function(param) {
                $.messager.progress();// 打开进度条
                var isValid = $(this).form("validate");
                if (!isValid){
                    $.messager.progress('close');// 隐藏进度条
                };
                return isValid;// 返回数据验证结果，验证失败则中止提交
            },
            success: function(data) {
                $.messager.progress('close');// 隐藏进度条
                var rs = $.parseJSON(data);
                var stat = rs.stat;
                if (stat && 1 == stat) {
                    engine.logged();
                };
            }
        });
    };

    // 登录按钮
    $("#btnLogin").linkbutton({
        text: "登录",
        width: 80,
        onClick: loginSubmit
    });
});
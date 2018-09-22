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

    // 表单提交
    var loginSubmit = function() {
        engine.progress("open")// 打开进度条
        // 取得密码加密种子
        $.ajax({
            url: contextPath + "/seed",
            type: "GET",
            cache: false,
            dataType: "json",
            success: function(result, status) {
                if (1 == result.stat) {
                    var seed = result.data;
                    var pwd = $("#password").val();
                    console.info(pwd);
                    var pwdCiphertext = engine.md5(pwd);
                    console.info(pwdCiphertext);
                    pwdCiphertext = engine.md5(pwdCiphertext.concat(seed));
                    console.info(pwdCiphertext);
                    pwdCiphertext = engine.base64(pwdCiphertext);
                    console.info(pwdCiphertext);
                    $("#encryptedPassword").val(pwdCiphertext);
                    // 提交表单
                    loginForm.form("submit", {
                        url: contextPath + "/login",
                        onSubmit: function(param) {
                            var isValid = $(this).form("validate");
                            if (!isValid){
                                engine.progress("close");// 隐藏进度条
                                engine.alert("警告！", "表单输入校验失败！", "warning");
                            };
                            return isValid;// 返回数据验证结果，验证失败则中止提交
                        },
                        success: function(data) {
                            engine.progress("close");// 隐藏进度条
                            var rs = $.parseJSON(data);
                            var stat = rs.stat;
                            if (1 == stat) {
                                engine.logged();
                            } else {
                                aChangeKaptcha.click();
                                if (0 == stat) {
                                    engine.alert("登录失败！", "系统错误，请联系系统管理员！", "error");
                                } else {
                                    engine.alert("登录失败！", rs.msg, "warning");
                                };
                            }
                        },
                        error: function() {
                            engine.progress("close");// 隐藏进度条
                            engine.alert("登录失败！", "系统错误，请联系系统管理员！", "error");
                        }
                    });
                } else {
                    engine.progress("close");// 隐藏进度条
                    engine.alert("登录失败！", result.msg, "warning");
                };
            },
            error: function(request, message, e) {
                engine.progress("close");// 隐藏进度条
                engine.alert("登录失败！", "系统错误，请联系系统管理员！", "error");
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
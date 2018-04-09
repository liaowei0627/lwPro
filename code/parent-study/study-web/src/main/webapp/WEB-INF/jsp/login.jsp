<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/login.js"></script>
        <form id="login_form" class="easyui-form" method="post" style="text-align:center;">
            <table style="margin: 10% auto auto auto;">
                <tr><td><input id="userName" name="userName" /></td><td></td></tr>
                <tr>
                    <td><input id="password" name="password" /></td>
                    <td rowspan="2" style="text-align:center;">
                        <a id="changeKaptcha">
                            <img id="imgKaptcha" style="width: 125px;height: 45px;" />
                        </a>
                    </td>
                </tr>
                <tr>
                    <td><input id="kaptcha" /></td>
                </tr>
            </table>
        </form>
        <div style="text-align:center;padding:5px">
            <a id="btnLogin" href="javascript: void(0);" />
        </div>

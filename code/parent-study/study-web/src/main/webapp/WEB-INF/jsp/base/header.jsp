<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="width: 216px;height: 63px;;position: absolute;bottom: 0px;left: 0px;background-image: url(${pageContext.request.contextPath}/static/img/logo.png);">
</div>
<c:if test="${not empty sessionUser}">
    <div style="position: absolute;bottom: 0px;right: 70px;text-align: right;">
        <label>这是给导航栏留的地方</label>
    </div>
    <div style="position: absolute;bottom: 0px;right: 0px;">
        <a href="javascript: void(0);" class="easyui-splitbutton" data-options="menu:'#userMenus'">${sessionUser.userName}</a>
        <div id="userMenus" style="width:100px;">
            <div id="menuPersonal" data-options="iconCls:'icon-man'">个人中心</div>
            <div id="menuLogout" data-options="iconCls:'icon-cancel'">退出登录</div>
            <div id="menuAbout" data-options="iconCls:'icon-tip'">关于我们</div>
        </div>
    </div>
</c:if>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/header.js"></script>
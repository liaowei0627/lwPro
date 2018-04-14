<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>范例项目</title>
<%@ include file="/WEB-INF/jsp/base/import-css.jsp"%>
</head>
<body class="easyui-layout">
    <div data-options="region:'north'" style="height: 50px"></div>
    <div data-options="region:'south',split:true" style="height: 50px;"></div>
    <div data-options="region:'west',split:true" title="West" style="width: 20%;"></div>
    <div id="main_content" class="easyui-panel" data-options="region:'center',title:'Main Title'"></div>
</body>
</html>
<%@ include file="/WEB-INF/jsp/base/import-js.jsp"%>
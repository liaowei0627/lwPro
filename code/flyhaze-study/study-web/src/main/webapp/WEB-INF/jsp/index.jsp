<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>范例项目</title>
<%@ include file="/WEB-INF/jsp/base/import-css.jsp"%>
</head>
<body id="body_layout" class="easyui-layout">
    <div id="main_header" data-options="region:'north'" style="height: 63px;position: relative;"></div>
    <div id="main_content" class="easyui-panel" data-options="region:'center',title:'Main Title'"></div>
    <div data-options="region:'south'" style="height: 50px;">
        <%@ include file="/WEB-INF/jsp/base/footer.jsp"%>
    </div>
</body>
</html>
<%@ include file="/WEB-INF/jsp/base/import-js.jsp"%>
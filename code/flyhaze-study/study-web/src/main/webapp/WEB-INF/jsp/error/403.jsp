<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误信息</title>
</head>
<body>
<h1 style="color: red; font-weight: bold;">您没有该模块的访问权限！</h1>
<p>
<c:out value="${exception}"></c:out>
<c:out value="${message}"></c:out>
<%

	Enumeration<String> et = request.getAttributeNames();
	for(;et.hasMoreElements();){
		String k = et.nextElement();
		out.print("<br>"+k + "====");
		out.println(request.getAttribute(k));
	}
%>
</p>
</body>
</html>
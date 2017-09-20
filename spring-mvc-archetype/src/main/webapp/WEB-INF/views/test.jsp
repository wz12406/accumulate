<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>登录页</title>
	<script>
		$(document).ready(function() {
			$("#loginForm").validate();
		});
	</script>
</head>
<body>
	<c:if test="${not empty message}">
	<p>${message}</p>
	</c:if>
	<form id="loginForm" action="${ctx}/test/login" method="post" class="form-horizontal">
		<label for="loginName" class="control-label">用户名:</label>
		<input type="text" id="loginName" name="loginName"  value="${loginName}"/>
		<label for="password" class="control-label">密码:</label>
		<input type="password" id="password" name="password""/>
		<input id="submit_btn" type="submit" value="登录"/>
	 	<span>(管理员: <b>admin/admin</b>, 普通用户: <b>user/user</b>)</span>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>数据查询测试页面</title>
</head>
<body>
	<form action="${basePath}/test/test.do" method="post">
		姓名：<input type="text" name="sname" /> <input type="submit" value="查询" />
	</form>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>姓名</th>
			</tr>
		</thead>
		<c:forEach items="${students}" var="student">
			<tr>
				<td>${student.id}</td>
				<td>${student.sname}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
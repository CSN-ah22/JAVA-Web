<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	String name = "이순신"; //멤버변수
	public String getName(){ return name;} //멤버 메서드
%>
<%
String age = request.getParameter("age");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크립트릿 연습</title>
</head>
<body>
<h1>나이는 <%=age %>살입니다!!</h1>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<jsp:useBean id="m1" class="sec01.ex01.MemberBean" scope="page" />
<jsp:setProperty name="m1" property="name" value="이순신"/>
<jsp:useBean id="m2" class="java.util.ArrayList" scope="page"/> <!-- ArrayList객체를 빈으로 생성함 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현 언어의 여러가지 연산자들</title>
</head>
<body>
empty 연산자
<h2>
\${empty m1}:${empty m1 }<br><!-- m1에 값이 하나라도 있으니 false 반환 -->
\${not empty m1}:${not empty m1}<br><br><!-- true 반환 -->

\${empty m2}:${empty m2 }<br><!-- m2객체에 아무것도 없으니 true 반환 -->
\${not empty m2}:${not empty m2}<br><br><!-- false 반환 -->

\${empty "hello" }:${empty "hello"}<br> <!-- 문자열은 false반환 -->
\${empty null }:${empty null }<br> <!-- null은 true 반환 -->
\${empty "" }:${empty "" }<br> <!-- 빈 문자열은 true 반환 -->

</h2>
</body>
</html>
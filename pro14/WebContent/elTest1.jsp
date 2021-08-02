<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현 언어에서 사용되는 데이터들</title>
</head>
<body>
<h1>표현 언어로 여러 가지 데이터 출력하기</h1>
<h1>
\${100}: ${100}<br>
\${"안녕하세요"}: ${"안녕하세요"}<br>
\${10+1}: ${10+1}<br>
\${"10"+1}: ${"10"+1}<br> <!-- 숫자형 문자열 + 실제숫자 = 자동으로 숫자를 변해 더함 -->

<%-- \${null+10}:${null+10}<br> --%> <!-- null과 10을 더하면 10이 된다  -->
<%-- \${"안녕"+11}:${"안녕"+11}<br> --> <!-- 문자열과 숫자는 더할 수 없음 --%>
<%-- \${"hello"+"world"}:${"hello"+"world"}<br> --%> <!-- 문자열끼리는 더할 수 없음 -->
</h1>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여러 가지 비교 연산자</title>
</head>
<body>
<h2>여러 가지 비교 연산자</h2>
<h3>
\${10==10} : ${10==10}<br>
\${10 eq 10} : ${10 eq 10}<br><br>
\${"hello"=="hello }:${"hello"=="hello" }<br> <!-- 문자열이 서로 같은지를 비교할때 -->
\${"hello" eq "hello"}:${"hello" eq "hello" }<br><br> <!-- 문자열이 서로 같은지를 비교할때 -->
\${20!=10}:${20!=10 }<br>
\${20 ne 10}:${20 ne 10}<br><br>
\${"hello" != "apple}:${"hello"!="apple" }<br><!-- 문자열이 서로 다른지 비교할때 -->
<%-- \${"hello" ne "apple"}:${"hello" ne "apple" } --%><br><br><!-- 문자열이 서로 다른지 비교할때 -->
\${10 < 10}:${10<10 }<br>
\${10 lt 10}:${100 lt 10 } <br><br>
\${100>10}:${100>10 }<br>
\${100 gt 10}:${100 gt 10 }<br><br>
\${100<=10}:${100<=10 }<br>
\${100 le 10}:${100 le 10 } <br><br>
\${100>=10}:${100>=10 }<br>
\${100 ge 10}:${100 ge 10 } }<br><br>



</h3>
</body>
</html>
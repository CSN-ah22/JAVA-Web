<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여러 가지 논리 연산자</title>
</head>
<body>
<h2>여러 가지 논리 연산자</h2>
<h2>
\${(10==10) && (20==20) } : ${(10==10)&&(20==20) }<br>
\${(10==10) and (20==20)}:${(10==10)and(20==20) } <br><br>
\${(10==10) || (20!=30)}:${(10==10) || (20==20) }<br>
\${(10!=10) or (20!=20)}:${(10==10) or (20==20) } <br><br>
\${!(20==10)} : ${!(20==10) }<br>
\${not(20==10)}:${not(20==10) }<br><br>
\${!(20!=10)}:${!(20!=10) }<br><!-- 괄호의 값이 true임으로 false를 출력 -->
\${not(20!=10)}:${not(20!=10) } <br><br> <!-- 괄호의 값이 true임으로 false를 출력 -->


</h2>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
 request.setCharacterEncoding("utf-8");
 int score = Integer.parseInt(request.getParameter("score"));
 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>점수 출력창</title>
</head>
<body>
<h1>시험점수 <%=score %>점</h1>

<%
if(score>100 || score<0){

%>
<h1>0~100사이의 점수를 입력해주세요</h1>
<%
}else if(score>=90){
	

%>
<h1>A학점입니다</h1>
<%
}else if(score>=80 && score<90){

%>
<h1>B학점입니다</h1>
<%
}else if(score>=70 && score<80){
	
%>
<h1>C학점입니다</h1>
<%
}else{

%>
<h1>F학점입니다</h1>
<%
}
%>
<br>
<a href="scoreTest.html">시험점수입력</a>
</body>
</html>
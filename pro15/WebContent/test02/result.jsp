<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
								<!-- 웹 애플리케이션 경로 추출 -->
<% 
request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var="file1" value="${param.param1}" /> <!-- first.jsp에서 넘어온 값 할당 -->
<c:set var="file2" value="${param.param2}"/>
<title>이미지 파일 출력하기</title>
</head>
<body>
매개변수 1:
<c:out value="${file1}" /><br> <!-- 출력 -->
매개변수 2:
<c:out value="${file2}" /><br>

<!-- <c:if>태그: 테스트 속성 값이 true면 실행 -->
<!-- if: 개체의 값이 비어있지 않은상태라면 -->
<!-- 실행내용: 파일 이름으로 서블릿에서 이미지를 다운로드해 표시함 -->

<c:if test="${not empty file1}">
<img src="${contextPath }/download.do?fileName=${file1}" width=300 height=300 /><br>
</c:if>
<br>
<c:if test="${not empty file2 }">
<img src="${contextPath}/download.do?fileName=${file2}" width=300 height=300 /><br>
</c:if>
파일 내려받기 :<br>
<a href="${contextPath}/download.do?fileName=${file2}">파일 내려받기</a><br>
</body>
</html>
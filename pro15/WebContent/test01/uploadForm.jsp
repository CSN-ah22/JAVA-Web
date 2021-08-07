<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>첨부 업로드</title>
</head>
<body>
			   <!--  해당 애플리케이션의 경로명을 return  -->
      <form action="<%= application.getContextPath() %>/fileUpload"  method="post" enctype="multipart/form-data">
            파일 설명 : <input type="text" name="파일 설명"><br>
            파일1 : <input type="file" name="file1"><br>
            파일2 : <input type="file" name="file2"><br>
            <input type="submit" value="전송">
      </form>
</body>
</html>

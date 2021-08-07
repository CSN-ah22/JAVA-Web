+ 책의 본래의 코드에 대한 설명이 부족해 따로 인터넷을 참고하다가 완성했습니다
+ 본래의 코드와는 조금 다르지만 흐름은 비슷합니다
+ 그래도 본래의 코드도 함께 작성해서 붙이겠습니다
<details>
<summary>FileUpload.java</summary>

```jsx
package sec01.ex01;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/upload.do")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String encoding="utf-8";
		File currentDirPath = new File("C:\\file_repo");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath); //파일을 저장할곳 지정
		factory.setSizeThreshold(1024*1024); //최대 업로드 가능한 파일 크기 설정
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List items = upload.parseRequest(request);			
			for(int i=0; i<items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
				if(fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName()+"=" +fileItem.getString(encoding));
					
				}else {
					System.out.println("매개변수 이름:"+fileItem.getFieldName());
					System.out.println("파일 이름:"+fileItem.getName());
					System.out.println("파일크기:"+fileItem.getSize()+"bytes");
					if(fileItem.getSize()>0) {
						int idx=fileItem.getName().lastIndexOf("\\");
						if(idx ==-1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						String fileName = fileItem.getName().substring(idx+1);
						File uploadFile = new File(currentDirPath+"\\"+fileName);
						fileItem.write(uploadFile);
					}//end if
				}//end if
			}//end for
	
}catch(Exception e) {
	e.printStackTrace();
}
	}
}
```
</details>
  
<details>
<summary>uploadForm.jsp</summary>
  
```jsx
  
  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${contextPath}/upload.do" method="post" enctype="multipart/form-data">
<!-- enctype : 폼 데이터(form data)가 서버로 제출될 때 해당 데이터가 인코딩되는 방법을 명시합니다. -->
파일1: <input type="file" name="file1"> <br>
파일2: <input type="file" name="file2"> <br>
매개변수1: <input type="text" name="param1"> <br>
매개변수2: <input type="text" name="param2"> <br>
매개변수3: <input type="text" name="param3"> <br>
<input type="submit" value="업로드">
</form>
</body>
</html>
    
```
</details>

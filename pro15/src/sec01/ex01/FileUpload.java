package sec01.ex01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
 
 
@WebServlet("/fileUpload")
public class FileUpload extends HttpServlet {
 
 
    private static final String CHARSET = "utf-8";
    private static final String FILE_PATH = "C:\\file_repo"; //저장소 디렉터리
    private static final int LIMIT_SIZE_BYTES = 1024 * 1024;
 
 
    @Override
    protected void doPost(HttpServletRequest request,  HttpServletResponse response)
            throws ServletException, IOException {
 
 
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding(CHARSET);
        PrintWriter out = response.getWriter();
 
 
        File attachesDir = new File(FILE_PATH); //업로드할 파일 경로 지정
 
 
        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        fileItemFactory.setRepository(attachesDir);
        fileItemFactory.setSizeThreshold(LIMIT_SIZE_BYTES);
        ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
 
 
        try {
        	//multipart/form-data형식으로 넘어온 데이터를 라이브러리에서 제공해주는 FileItem 타입으로 변환
            List<FileItem> items = fileUpload.parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {//true 와 false 구분 ,일반 텍스트 데이터일시 if문 수행
                    System.out.printf("파라미터 명 : %s, 파라미터 값 :  %s \n", item.getFieldName(), item.getString(CHARSET));
                } else {//파일 데이터일시 수행
                    System.out.printf("파라미터 명 : %s, 파일 명 : %s,  파일 크기 : %s bytes \n", item.getFieldName(),
                            item.getName(), item.getSize());
                    if (item.getSize() > 0) {//파일 업로드 기능 수행
                        String separator = File.separator;
                        //File.separator는 운영체제별로 다른, 파일경로 구분자를 담고 있습니다 윈도우: \ 리눅스: /
                        //따라서 업로드한 파일 경로의 마지막 separator뒤에 오는 값이 실제 파일명이라 할 수 있습니다
                        //C:\\file_repo\test.txt
                        int index =  item.getName().lastIndexOf(separator);
                        String fileName = item.getName().substring(index  + 1);
                        File uploadFile = new File(FILE_PATH +  separator + fileName); //최종 파일 객체로 변환
                        item.write(uploadFile);
                    }
                }
            }
 
 
            out.println("<h1>파일 업로드 완료</h1>");
 
 
        } catch (Exception e) {
            // 파일 업로드 처리 중 오류가 발생하는 경우
            e.printStackTrace();
            out.println("<h1>파일 업로드 중 오류가  발생하였습니다.</h1>");
        }
    }
 
 
}

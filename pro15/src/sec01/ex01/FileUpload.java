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
    private static final String FILE_PATH = "C:\\file_repo"; //����� ���͸�
    private static final int LIMIT_SIZE_BYTES = 1024 * 1024;
 
 
    @Override
    protected void doPost(HttpServletRequest request,  HttpServletResponse response)
            throws ServletException, IOException {
 
 
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding(CHARSET);
        PrintWriter out = response.getWriter();
 
 
        File attachesDir = new File(FILE_PATH); //���ε��� ���� ��� ����
 
 
        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        fileItemFactory.setRepository(attachesDir);
        fileItemFactory.setSizeThreshold(LIMIT_SIZE_BYTES);
        ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
 
 
        try {
        	//multipart/form-data�������� �Ѿ�� �����͸� ���̺귯������ �������ִ� FileItem Ÿ������ ��ȯ
            List<FileItem> items = fileUpload.parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {//true �� false ���� ,�Ϲ� �ؽ�Ʈ �������Ͻ� if�� ����
                    System.out.printf("�Ķ���� �� : %s, �Ķ���� �� :  %s \n", item.getFieldName(), item.getString(CHARSET));
                } else {//���� �������Ͻ� ����
                    System.out.printf("�Ķ���� �� : %s, ���� �� : %s,  ���� ũ�� : %s bytes \n", item.getFieldName(),
                            item.getName(), item.getSize());
                    if (item.getSize() > 0) {//���� ���ε� ��� ����
                        String separator = File.separator;
                        //File.separator�� �ü������ �ٸ�, ���ϰ�� �����ڸ� ��� �ֽ��ϴ� ������: \ ������: /
                        //���� ���ε��� ���� ����� ������ separator�ڿ� ���� ���� ���� ���ϸ��̶� �� �� �ֽ��ϴ�
                        //C:\\file_repo\test.txt
                        int index =  item.getName().lastIndexOf(separator);
                        String fileName = item.getName().substring(index  + 1);
                        File uploadFile = new File(FILE_PATH +  separator + fileName); //���� ���� ��ü�� ��ȯ
                        item.write(uploadFile);
                    }
                }
            }
 
 
            out.println("<h1>���� ���ε� �Ϸ�</h1>");
 
 
        } catch (Exception e) {
            // ���� ���ε� ó�� �� ������ �߻��ϴ� ���
            e.printStackTrace();
            out.println("<h1>���� ���ε� �� ������  �߻��Ͽ����ϴ�.</h1>");
        }
    }
 
 
}

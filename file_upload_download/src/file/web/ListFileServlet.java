package file.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import file.domain.FileUploadDownload;
import file.service.FileService;

public class ListFileServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//查询显示所有的数据到页面
		FileService service=new FileService();
		List<FileUploadDownload> list=service.list();
		
		request.setAttribute("list", list);//保留查询结果
		request.getRequestDispatcher("/listFile.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}

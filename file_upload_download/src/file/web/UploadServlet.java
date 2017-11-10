package file.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import file.domain.FileUploadDownload;
import file.service.FileService;
import file.utils.WebUtils;

public class UploadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("message", "不是请求的信息表单，请确认表单属性是否正确");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
			return;
		}
		
		try {
			//调用工具类，获得上传文件信息
			FileUploadDownload fud=WebUtils.doFileUpload(request);
			FileService service=new FileService();
			service.insert(fud);//保存上传文件信息
			
			request.setAttribute("message", "上传文件成功");
			request.getRequestDispatcher("/message.jsp").forward(request,response);
		
		
		} catch (FileSizeLimitExceededException e) {
			request.setAttribute("message", "对不起，您上传的文件大小超过了大小的限制");
			request.getRequestDispatcher("/message.jsp").forward(request,response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}

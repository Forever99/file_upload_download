package file.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import file.domain.FileUploadDownload;
/*
 * 上传文件的工具类
 */
public class WebUtils {

	public static FileUploadDownload doFileUpload(HttpServletRequest request) throws FileSizeLimitExceededException {
		
		FileUploadDownload fud = new FileUploadDownload();// 文件信息对象

		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 临时文件夹temp
			factory.setRepository(new File(request.getSession().getServletContext().getContextPath()+"/temp"));
//			factory.setRepository(new File("/temp"));// 临时文件夹temp
			factory.setSizeThreshold(1024 * 1024);// 临时缓冲区大小为1M

			ServletFileUpload parse = new ServletFileUpload(factory);// 解析器
			//上传文件大小
			parse.setFileSizeMax(1024 * 1024 * 2);// 单个文件大小限制为2M
			parse.setSizeMax(1024 * 1024 * 20);// 总的文件大小限制为20M
			//解决中文文件名的乱码
			parse.setHeaderEncoding("utf-8");
			List<FileItem> list = parse.parseRequest(request);
			for (FileItem fileItem : list) {
				// 普通表单
				if (fileItem.isFormField()) {
					String fieldName = fileItem.getFieldName();
					// String value = fileItem.getString();
					String value = fileItem.getString("utf-8");// 解决字段的中文乱码问题
					System.out.println("fieldName:" + fieldName);
					System.out.println("value:" + value);
					// 将当前字段封装到fud对象中对应的字段中去
					
					//普通字段都通过这个保存到fud中
					BeanUtils.setProperty(fud, fieldName, value);
				}
				// 文件
				else {
					String filename = fileItem.getName();// 获取文件名
				
					//文件名：aa.txt 与c:\a\b\c\aa.txt的处理 统一
					int index=filename.lastIndexOf("\\");
					if(index!=-1){
						filename=filename.substring(index+1);
					}

					String realPath=request.getSession().getServletContext().getRealPath("/WEB_INF/upload");
					
					//生成随机文件夹
					String savePath=generateSavePath(realPath,filename);
					
					//生成唯一的文件名
					String uuidname=generateUUIDName(filename);
					
					// 上传文件
					InputStream in = fileItem.getInputStream();// 获取文件读取流

//					OutputStream out = new FileOutputStream("d:/" + name);
					//保存文件夹：savePath  唯一文件名：uuidname
					OutputStream out = new FileOutputStream(new File(savePath,uuidname));

					byte[] buf = new byte[1024];
					int len = 0;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					in.close();
					out.close();
					//删除临时文件
					fileItem.delete();
					fud.setFilename(filename);//文件名
					fud.setUuidname(uuidname);//唯一文件名
					fud.setSavepath(savePath);//保存路径
					fud.setId(UUID.randomUUID().toString());//id
				}
			}
			return fud;//返回文件信息封装对象

		} catch (FileUploadBase.FileSizeLimitExceededException e) {
//			e.printStackTrace();//仅仅只是打印异常错误信息
			
			//使用失败，因为此处并没有response
//			request.setAttribute("message", "对不起，您上传的文件大小超过了大小的限制");
//			request.getRequestDispatcher("/message.jsp").forward(request,response);
		
			//怎么办？
			//抛出一个异常出去  实际上异常也是一个返回值
			//抛异常【编译时异常  还是   运行时异常】 
			//编译时异常
			throw e;//记得抛出异常要在方法中进行声明
		}
		catch(Exception e){
			throw new RuntimeException(e);//抛出运行时异常
		}
	}

	//生成唯一的文件名
	private static String generateUUIDName(String filename) {
		return UUID.randomUUID().toString()+"_"+filename;
	}
	//生成随机文件夹
	private static String generateSavePath(String realPath, String filename) {

		int hashCode=filename.hashCode();
		//通过位运算，计算出一级和二级目录的数字
		int first=hashCode & (0xf);//以及目录
		int second=(hashCode>>4)&(0xf);//二级目录
		String savePath=realPath+"/"+first+"/"+second;
		File f=new File(savePath);
		if(!f.exists()){
			f.mkdirs();//创建多级目录
		}
		return savePath;//保存路径
	}
}

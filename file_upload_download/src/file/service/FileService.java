package file.service;

import java.util.List;

import file.dao.FileDao;
import file.daoFactory.FileDaoFactory;
import file.domain.FileUploadDownload;

//上传文件的业务层
public class FileService {

	//FileDaoImpl实现类实例
	FileDao f= FileDaoFactory.getInstance().createDao(FileDao.class);
	
	//上传文件
	public void insert(FileUploadDownload fud) {
		f.insert(fud);//调用dao层进行添加
	}

	//查询文件
	public List<FileUploadDownload> list() {
		
		 List<FileUploadDownload> list=f.list();
		return list;
	}

	//通过id获得文件对象的全部信息
	public FileUploadDownload select(String id) {

		FileUploadDownload fud=f.select(id);
		return fud;
	}

}

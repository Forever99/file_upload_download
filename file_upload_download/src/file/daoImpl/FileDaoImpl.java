package file.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import file.dao.FileDao;
import file.domain.FileUploadDownload;
import file.utils.JDBCUtils;

//dao实现类
public class FileDaoImpl implements FileDao {

	QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

	@Override
	public void insert(FileUploadDownload fud) {// 上传文件

		/*
		 * 
		 * private String id; private String uuidname; //上传文件的名称，文件的uuid名
		 * private String filename; //上传文件的真实名称 private String savepath;
		 * //记住文件的位置 private Date uploadtime; //文件的上传时间 private String
		 * description; //文件的描述 private String username;
		 */
		Object[] param = { fud.getId(), fud.getUuidname(), fud.getFilename(),
				fud.getSavepath(), fud.getDescription(), fud.getUsername() };// 没有时间：fud.getUploadtime()
		try {
			qr.update("insert into file values(?,?,?,?,null,?,?)", param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<FileUploadDownload> list() {// 查询文件
		try {
			List<FileUploadDownload> list = qr.query("select * from file",
					new BeanListHandler(FileUploadDownload.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public FileUploadDownload select(String id) {//通过id获得文件的全部信息
		try {
			FileUploadDownload fud=qr.query("select * from file where id=?",new BeanHandler(FileUploadDownload.class),id);
			return fud;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}

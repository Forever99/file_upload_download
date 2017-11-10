package file.daoFactory;

import java.util.ResourceBundle;

//dao工厂
public class FileDaoFactory {

	private FileDaoFactory(){};//不能构造
	private static FileDaoFactory factory=new FileDaoFactory();

	//获取工厂实例
	public static FileDaoFactory getInstance(){
		return factory;
	}
	
	//用于返回传进来的一个类的实例对象
	/*
	 * 1、传进来FileDao.class
	 * 2、读取dao.properties文件，获取FileDao对应的实现类
	 * 3、FileDao对应的实现类实例
	 */
	public<T> T createDao(Class<T> t){
		//1、FileDao.class-->FileDao
		String simpleName=t.getSimpleName();
		//2、读取配置文件，获取FileDao对应的实现类
		//FileDao=file.daoImpl.FileDaoImpl
		String calzzName=ResourceBundle.getBundle("dao").getString(simpleName);
		try {
			//3、FileDao对应的实现类实例
			T instance=(T) Class.forName(calzzName).newInstance();
			return instance;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

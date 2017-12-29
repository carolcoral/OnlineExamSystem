package cn.xdl.exam.dao;

/*
 * UserDao的工厂类
 */
public class UserDaoFactory {

	/**
	 * 创建一个UserDao的对象
	 * 
	 * @return
	 */
	public static UserDao instanceUserDao() {
		return new UserDaoImp();
	}
}

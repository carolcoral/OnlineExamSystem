package cn.xdl.exam.dao;

/*
 * UserDao�Ĺ�����
 */
public class UserDaoFactory {

	/**
	 * ����һ��UserDao�Ķ���
	 * 
	 * @return
	 */
	public static UserDao instanceUserDao() {
		return new UserDaoImp();
	}
}

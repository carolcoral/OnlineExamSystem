package cn.xdl.exam.dao;

import cn.xdl.exam.model.Grade;
import cn.xdl.exam.model.User;

public class UserDaoService {

	/**
	 * 验证用户登陆身份
	 * 
	 * @param user
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static boolean login(User user) {
		System.out.println("登陆的用户：" + user);
		User queryUser = UserDaoFactory.instanceUserDao().queryUserInfoByUserName(user.getUname());
		System.out.println("查询到的用户：" + queryUser);
		// 验证
		if (user.equals(queryUser)) {
			return true;// 登陆成功
		}
		return false;// 登陆失败
	}

	/**
	 * 管理员添加用户
	 * 
	 * @param insertUser
	 * @return
	 */
	public static boolean insertUser(User insertUser) {
		return UserDaoFactory.instanceUserDao().insertUserInfo(insertUser);
	}

	/**
	 * 删除用户信息
	 * 
	 * @param delteUser
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static boolean deleteUser(String deleteUser) {
		User deleteUser1 = UserDaoFactory.instanceUserDao().queryUserInfoByUserName(deleteUser);
		// 验证
		if (deleteUser1.getUname().equals(deleteUser)) {
			UserDaoFactory.instanceUserDao().deleteUser(deleteUser);
			return true;
		}
		return false;// 查询失败
	}

	/**
	 * 修改用户信息
	 * 
	 * @param updateUser
	 * @return
	 */
	public static boolean updateUser(User updateUser) {
		User updateUser1 = UserDaoFactory.instanceUserDao().updateUser(updateUser);
		// 验证
		if (updateUser1.equals(updateUser)) {
			updateUser(updateUser1);
			return true;// 登陆成功
		}
		return false;// 登陆失败
	}

	/**
	 * 查询用户信息
	 * 
	 * @param queryUser
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static User queryUser(String queryUser) {
		User queryUser1 = UserDaoFactory.instanceUserDao().queryUserInfoByUserName(queryUser);
		// 验证
		if (queryUser1.getUname().equals(queryUser)) {
			System.out.println("查询成功！");// 查询成功
			System.out.println("查询到的用户：" + queryUser1);
		}
		System.out.println("查询失败！");// 查询失败
		return queryUser1;
	}
	
	/**
	 * 查询用户信息
	 * 
	 * @param queryUser
	 * @return
	 */
	public static  boolean updateUserPasswd(String updateUserPasswd) {
		boolean updateUserPaswd = UserDaoFactory.instanceUserDao().updateUserPasswd(updateUserPasswd);
		// 验证
		if (updateUserPaswd) {
			System.out.println("修改密码成功！");// 查询成功
			return true;
		}
		System.out.println("修改密码失败！");// 查询失败
		return false;
	}
	
	/**
	 * 查询用户成绩
	 * 
	 * @param queryUserGrade
	 * @return
	 */
	public static  Grade queryUserGrade(int User_Grade_Id) {
		Grade queryUserGrade = UserDaoFactory.instanceUserDao().queryUserGrade(User_Grade_Id);
		// 验证
		if (User_Grade_Id == queryUserGrade.getGrade_id()) {
			System.out.println("查询成绩成功！");// 查询成功
			System.out.println(queryUserGrade);
		}
		System.out.println("查询成绩失败！");// 查询失败
		return new Grade();
	}

}

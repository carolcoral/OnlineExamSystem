package cn.xdl.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.xdl.exam.model.Exam;
import cn.xdl.exam.model.Grade;
import cn.xdl.exam.model.User;
import cn.xdl.exam.util.DBUtils;

public class UserDaoImp implements UserDao {

	/*
	 * 根据用户名查询验证用户信息 登录验证模块 (non-Javadoc)
	 * 
	 * @see cn.xdl.exam.dao.UserDao#queryUserInfoByUserName(java.lang.String)
	 */
	@Override
	public User queryUserInfoByUserName(String username) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		try {
			// 1.获取数据库连接对象
			conn = DBUtils.getConnection();

			// 2.执行sql语句
			pst = conn.prepareStatement(SQL_QUERY_USERINFO_BY_USERNAME);

			pst.setString(1, username);
			// 3.处理结果集
			rs = pst.executeQuery();
			if (rs.next()) {
				System.out.println("find");
				// 封装查询到的用户数据
				int id = rs.getInt("id");// 不用返回该数据展示给用户
				String uname = rs.getString("uname");
				String upwd = rs.getString("upwd");
				boolean isManager = rs.getBoolean("ismanager");
				return new User(id, uname, upwd, isManager);
			}
			// 4.释放资源
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pst, rs);
		}
		return null;
	}

	/*
	 * 插入新用户数据 管理员操作
	 */
	@SuppressWarnings("static-access")
	@Override
	public boolean insertUserInfo(User insertUser) {
		Connection conn = null;
		PreparedStatement pst = null;
		int resultNum = -1;
		try {
			// 1.获取数据库连接对象
			conn = DBUtils.getConnection();

			// 2.执行sql语句
			pst = conn.prepareStatement(SQL_INSERT_USERINFO);

			pst.setString(1, insertUser.getUname());
			pst.setString(2, insertUser.getUpwd());
			pst.setBoolean(3, insertUser.getIsManager());
			// 3.处理结果集
			resultNum = pst.executeUpdate();
			if (resultNum > 0) {
				return true;
			}
			// 4.释放资源
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pst, null);
		}
		return false;
	}

	/*
	 * (non-Javadoc) 管理员操作 从数据库中删除用户所有信息
	 * 
	 * @see cn.xdl.exam.dao.UserDao#deleteUser(cn.xdl.exam.model.User)
	 */
	@Override
	public boolean deleteUser(String deleteUser) {
		Connection conn = null;
		PreparedStatement pst = null;
		int resultNum = -1;
		try {
			// 1.获取数据库连接对象
			conn = DBUtils.getConnection();
			// 2.执行sql语句
			pst = conn.prepareStatement(SQL_DELETE_USERINFO);
			pst.setString(1, deleteUser);
			// 3.处理结果集
			resultNum = pst.executeUpdate();
			if (resultNum > 0) {
				return true;
			}
			// 4.释放资源
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pst, null);
		}
		return false;
	}

	/*
	 * (non-Javadoc) 根据获取到的用户名验证是否存在 若存在则进行修改操作 若不存在则返回错误信息并重新录入
	 * 
	 * @see cn.xdl.exam.dao.UserDao#updateUser(cn.xdl.exam.model.User)
	 */
	@SuppressWarnings({ "static-access", "unused" })
	@Override
	public User updateUser(User updateUser) {
		Connection conn = null;
		PreparedStatement pst = null;
		int resultNum = -1;
		try {
			// 1.获取数据库连接对象
			conn = DBUtils.getConnection();

			pst = conn.prepareStatement(SQL_UPDATE_USERINFO);
			String update_name = updateUser.getUname();
			pst.setString(1, updateUser.getUname());
			pst.setString(2, updateUser.getUpwd());
			pst.setBoolean(3, updateUser.getIsManager());
			pst.setString(4, update_name);
			// 3.处理结果集
			resultNum = pst.executeUpdate();
			// 4.释放资源
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pst, null);
		}
		return updateUser;
	}

	/*
	 * (non-Javadoc) 学生操作 修改当前用户的密码
	 * 
	 * @see cn.xdl.exam.dao.UserDao#updateUserPasswd(java.lang.String)
	 */
	@Override
	public boolean updateUserPasswd(String user_passwd) {
		Connection conn = null;
		PreparedStatement pst = null;
		@SuppressWarnings("unused")
		int resultNum = -1;
		try {
			// 1.获取数据库连接对象
			conn = DBUtils.getConnection();

			pst = conn.prepareStatement(SQL_UPDATE_USERPASSWD);
			String update_uname = User.getUname();
			pst.setString(1, user_passwd);
			pst.setString(2, update_uname);
			// 3.处理结果集
			resultNum = pst.executeUpdate();
			// 4.释放资源
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pst, null);
		}
		return false;
	}

	/*
	 * (non-Javadoc) 用户操作 输入成绩编号查询成绩 1.验证当前成绩的 grade_id 等于输入的值 2.验证当前用户的 user_id
	 * 等于数据库中成绩对应的 user_id
	 * 
	 * @see cn.xdl.exam.dao.UserDao#queryUserGrade(int)
	 */
	@Override
	public Grade queryUserGrade(int User_Grade_Id) {
		Connection conn = null;
		PreparedStatement pst = null;
		@SuppressWarnings("unused")
		int resultNum = -1;
		try {
			// 1.获取数据库连接对象
			conn = DBUtils.getConnection();
			int userid = UserDaoService.queryUser(User.getUname()).getUid();
			pst = conn.prepareStatement(SQL_QUERY_USERPGRADE);
			pst.setInt(1, User_Grade_Id);
			pst.setInt(1, userid);
			// 3.处理结果集
			resultNum = pst.executeUpdate();
			// 4.释放资源
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pst, null);
		}
		return new Grade();
	}

	/* (non-Javadoc)
	 * @see cn.xdl.exam.dao.UserDao#stratUserExam(cn.xdl.exam.model.Exam)
	 */
	@Override
	public Exam stratUserExam(Exam startUserExam) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}

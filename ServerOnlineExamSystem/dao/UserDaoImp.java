package cn.xdl.exam.dao;

import java.awt.Window.Type;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import cn.xdl.exam.main.ServerThread;
import cn.xdl.exam.model.Exam;
import cn.xdl.exam.model.Grade;
import cn.xdl.exam.model.Test;
import cn.xdl.exam.model.User;
import cn.xdl.exam.util.DBUtils;
import oracle.net.aso.a;

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
				System.out.println("查找成功！存在该数据！");
				// 封装查询到的用户数据
				int id = rs.getInt("user_id");// 不用返回该数据展示给用户
				String uname = rs.getString("user_name");
				String upwd = rs.getString("user_passwd");
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
			// String update_uname = UserDaoService.login(user);
			String update_uname = "user";
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
			int userid = UserDaoService.queryUser().getUid();
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

	/*
	 * (non-Javadoc) 考生考试 随机从数据库中获取10条试题数据 保存获取的试题数据的 test_id 在 test_ids 中
	 * 保存当前考试用户的 user_id 在 grade 成绩表中的 user_id 中 保存考试结束后计算的成绩结果在 grade 中的
	 * 获取当前保存后的成绩表中的 grade_id 保存在 exam 中的 grade_id 中 exam_name 的命名规则是：用户名
	 * user_name + 试卷的 id 值 exam_id
	 * 
	 * @see cn.xdl.exam.dao.UserDao#stratUserExam(cn.xdl.exam.model.Exam)
	 */
	@Override
	public String stratUserExam(String startUserExam) {
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			if (startUserExam.equals("startExam")) {
				// 1.获取数据库连接对象
				conn = DBUtils.getConnection();
				pst = conn.prepareStatement(SQL_QUERY_USER_EXAM_RAND);
				int Testdif = 3;
				int Testcount = 10;
				pst.setInt(1, Testdif);// 试题难度，为了便于测试，这里使用固定值3，表示简单（1：难，2：中等，3：简单）
				pst.setInt(2, Testcount);// 试题数量，需要随机查询出的试题的数量大小
				// 3.处理结果集
				ResultSet res = pst.executeQuery();
				while (res.next()) {
					int testId = res.getInt("test_id");
					String testContent = res.getString("testContent");
					String testAnswer = res.getString("testAnswer");
					int testDiffic = res.getInt("testDiffic");
					System.out.println("读取到的试题是：" + "[题号：" + testId + ",试题内容：" + testContent + ",试题答案：" + testAnswer + ",试题难度：" + testDiffic + "]");
					return testContent;// 返回试题的问题主体
				}
				// 4.释放资源
			} else {
				return ("参数错误，无法开始考试！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, pst, null);
		}
		return ("参数错误，无法开始考试！");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.xdl.exam.dao.UserDao#loginUserInfoByUserName(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public User loginUserInfoByUserName(String username, String userpasswd) {
		// TODO Auto-generated method stub
		return null;
	}

}

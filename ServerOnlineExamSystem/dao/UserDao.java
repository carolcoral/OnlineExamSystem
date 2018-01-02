package cn.xdl.exam.dao;

import cn.xdl.exam.model.Grade;
import cn.xdl.exam.model.User;

/*
 * dao 设计模式： data access object,和数据交互的设计模式
 * 		dao接口
 * 		dao实现类
 * 		dao的工厂类
 * 		dao的代理类
 * 		user实体类
 * 	
 * 
 * 定义对user表进行操作
 */
interface UserDao {

	/**
	 * 管理员操作 向数据库添加用户信息
	 * 
	 * @param user
	 *            需要添加的用户对象
	 * @return 查询结果，true:添加成功
	 */
	public static final String SQL_INSERT_USERINFO = "insert into user(user_name,user_passwd,isManager,user_addtime) values(?,?,?,sysdate())";

	public boolean insertUserInfo(User insertUser);

	/**
	 * 管理员操作 向数据库删除用户信息
	 * 
	 * @param user
	 *            需要添加的用户对象
	 * @return 查询结果，true:添加成功
	 */
	public static final String SQL_DELETE_USERINFO = "delete * from user where user_name = ?";

	public boolean deleteUser(String deleteUser);

	/**
	 * 管理员操作 向数据库修改用户信息
	 * 
	 * @param user
	 *            需要添加的用户对象
	 * @return 查询结果，true:添加成功
	 */
	public static final String SQL_UPDATE_USERINFO = "update user(user_name,user_passwd,isManager,user_addtime) set values(?,?,?,sysdate()) where user_name = ?";

	public User updateUser(User updateUser);

	/**
	 * 全员操作 通过用户名和密码验证登录信息
	 * 
	 * @param user_name
	 *            user_passwd 需要查询的用户的用户名和密码
	 * @return 查询结果，或者null
	 */
	public static final String SQL_LOGIN_USERINFO_BY_USERNAME = "select * from user where user_name = ? and user_passwd = ?";

	public User loginUserInfoByUserName(String username, String userpasswd);

	/**
	 * 管理员操作 通过用户名查询考生数据
	 * 
	 * @param username
	 *            需要查询的用户的用户名
	 * @return 查询结果，或者null
	 */
	public static final String SQL_QUERY_USERINFO_BY_USERNAME = "select * from user where user_name = ? ";

	public User queryUserInfoByUserName(String username);

	/**
	 * 考生操作 通过用户名修改考生密码
	 * 
	 * @param updateUserPasswd
	 *            需要查询的用户的用户名
	 * @return 查询结果，或者null
	 */
	public static final String SQL_UPDATE_USERPASSWD = "update user(user_passwd) set values(?) where user_name = ?";

	public boolean updateUserPasswd(String user_passwd);

	/**
	 * 考生操作 查询成绩
	 * 
	 * @param queryUserGrade
	 *            需要查询的成绩的编号查询 验证该成绩需要同时满足 1.grade_id 相等 2. user_id 等于当前用户的
	 *            user_id
	 * @return 查询结果，或者null
	 */
	public static final String SQL_QUERY_USERPGRADE = "select u.user_id,g.grade_id,g.grade from grade g where grade_id = ? join user u on (g.user_id = ?)";

	public Grade queryUserGrade(int User_Grade_Id);

	/**
	 * 考生操作 开始考试
	 * 
	 * @param stratUserExam
	 *            分别随机从题库中获取难度为1的题 数量为5条 分别随机从题库中获取难度为2的题 数量为10条
	 *            分别随机从题库中获取难度为3的题 数量为5条
	 * @return 查询结果，或者null
	 */
	// 获取题目
	public static final String SQL_QUERY_USER_EXAM_RAND = "select t.test_id,t.testContent,t.testAnswer,t.testDiffic from test t where t.test_id>=((select MAX(t.test_id) from test t) - (select MIN(t.test_id) from test t))* RAND() + (select MIN(t.test_id) from test t) and t.testDiffic = ? limit ?";

	// 把获取的题目的 test_id 插入到 exam 表中的test_ids 中
	public static final String SQL_INSERT_USER_EXAM_IDS = "";

	public String stratUserExam(String reString);

	/**
	 * @param resAnswer
	 * @return
	 */
	public String checkUserExamTest(String resAnswer);

}

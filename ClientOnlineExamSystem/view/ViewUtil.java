package cn.xdl.exam.view;

import java.util.Scanner;
import cn.xdl.exam.model.Test;
import cn.xdl.exam.model.User;

public class ViewUtil {
	/*
	 * 当输入用户名小于该值后重新返回输入界面 控制用户名长度的可操作大小 return USER_LENGTH_NUM
	 */
	public static final int USER_LENGTH_NUM = 4;
	/*
	 * 当输入试题内容小于该值后重新返回输入界面 控制内容长度的可操作大小 return TEST_LENGTH_NUM
	 */
	public static final int TEST_LENGTH_NUM = 10;

	static Scanner sc = new Scanner(System.in);

	/**
	 * 客户端欢迎页面视图，获取用户身份信息
	 * 
	 * @return 1. 考生登陆，2.管理员登陆 ， 3.退出
	 */
	public static int welcomeView() {
		System.out.println("**********************************************");
		System.out.println("\t欢迎您使用本考试系统 ");
		System.out.println("\t1.考生登陆");
		System.out.println("\t2.管理员登陆");
		System.out.println("\t3.退出");
		System.out.println("\t请根据提示进行相应的操作");
		System.out.println("**********************************************");
		int type = -1;
		try {
			type = sc.nextInt();
		} catch (Exception e) {
			return welcomeView();
		}
		if (type != 1 && type != 2 && type != 3) {
			return welcomeView();
		}
		return type;
	}

	// 管理员登录成功欢迎界面
	public static User managerLoginView() {
		System.out.println("**********************************************");
		System.out.println("\t欢迎管理员使用本考试系统 ");
		System.out.println("\t请输入用户名：");
		String uname = sc.next();

		System.out.println("\t请输入密码：");
		String upwd = sc.next();
		System.out.println("**********************************************");
		if (uname.length() < 6) {
			return managerLoginView();
		}
		User user = new User(uname, upwd, true);
		return user;
	}

	// 考生登录成功欢迎界面
	public static User studentLoginView() {
		System.out.println("---------------------------------------------");
		System.out.println("----\t欢迎考生使用本考试系统      \t--------------------");
		System.out.println("----\t请输入用户名：\t -------------------");
		String uname = sc.next();

		System.out.println("----\t请输入密码：\t -------------------");
		String upwd = sc.next();
		System.out.println("----\t请根据提示进行相应的操作\t-------------------");
		if (uname.length() < 6) {
			return managerLoginView();
		}
		User user = new User(uname, upwd, false);
		return user;
	}

	/**
	 * 管理员的功能列表视图
	 * 
	 * @return 管理员选择的功能模块
	 */
	public static int managerListView() {
		System.out.println("**********************************************");
		System.out.println("\t欢迎您使用本考试系统 ");
		System.out.println("\t0.exit 退出本系统（返回上级菜单）");
		System.out.println("\t1.add user 新增考生信息");
		System.out.println("\t2.del user 删除考生信息");
		System.out.println("\t3.update user 更新考生信息");
		System.out.println("\t4.query user 查询考生信息");
		System.out.println("\t5.add test 新建试题信息");
		System.out.println("\t6.del test 删除试题信息");
		System.out.println("\t7.update test 修改试题信息");
		System.out.println("\t8.query test  查询试题信息");
		System.out.println("\t9.ALLExport test  批量导出试题");
		System.out.println("\t请根据提示进行相应的操作");
		System.out.println("**********************************************");
		int type = -1;
		try {
			type = sc.nextInt();
		} catch (Exception e) {
			return welcomeView();
		}
		if (type != 1 && type != 2 && type != 3) {
			return welcomeView();
		}
		return type;
	}

	/**
	 * 考生的功能列表视图
	 * 
	 * @return 考生选择的功能模块
	 */
	public static int studentListView() {
		System.out.println("**********************************************");
		System.out.println("\t欢迎您使用本考试系统 ");
		System.out.println("\t0.exit 退出系统并返回上级菜单");
		System.out.println("\t1.update pwd 修改密码");
		System.out.println("\t2.start exam 开始考试");
		System.out.println("\t3.query grade 查询成绩");
		System.out.println("\t4.export grade 导出成绩");
		System.out.println("\t5.export test  导出试题");
		System.out.println("\t请根据提示进行相应的操作");
		System.out.println("**********************************************");
		int type = -1;
		try {
			type = sc.nextInt();
		} catch (Exception e) {
			return welcomeView();
		}
		if (type != 1 && type != 2 && type != 3) {
			return welcomeView();
		}
		return type;
	}

	/**
	 * 管理员的功能列表视图 添加新考生的信息
	 * 
	 * @return 管理员选择的功能模块
	 */
	public static User addStudentView() {
		System.out.println("**********************************************");
		System.out.println("\t欢迎管理员使用本考试系统");
		
		System.out.println("\t请输入新的用户名：");
		String uname = sc.next();

		System.out.println("\t请输入密码：");
		String upwd = sc.next();
		
		System.out.println("\t是否是管理员（true/false）：");
		boolean isManager = sc.nextBoolean();
		
		System.out.println("**********************************************");
		if (uname.length() < USER_LENGTH_NUM) {
			return addStudentView();
		}
		User user = new User(uname, upwd, isManager);
		return user;
	}

	/**
	 * 管理员的功能列表视图 删除考生信息
	 * 
	 * @return 管理员选择的功能模块
	 */
	public static String delStudentView() {
		System.out.println("**********************************************");
		System.out.println("\t欢迎管理员使用本考试系统");
		System.out.println("\t请输入需要删除的用户名：");
		String uname = sc.next();
		System.out.println("**********************************************");
		if (uname.length() < USER_LENGTH_NUM) {
			return delStudentView();
		}
		return uname;
	}

	/**
	 * 管理员的功能列表视图 修改考生信息
	 * 
	 * @return 管理员选择的功能模块
	 */
	public static String updateStudentView() {
		System.out.println("**********************************************");
		System.out.println("\t欢迎管理员使用本考试系统");
		System.out.println("\t请输入需要修改的用户名：");
		String uname = sc.next();
		System.out.println("**********************************************");
		if (uname.length() < USER_LENGTH_NUM) {
			return updateStudentView();
		}
		return uname;
	}

	/**
	 * 管理员的功能列表视图 查询考生信息
	 * 
	 * @return 管理员选择的功能模块
	 */
	public static String queryStudentView() {
		System.out.println("**********************************************");
		System.out.println("\t欢迎管理员使用本考试系统");
		System.out.println("\t请输入需要查询的用户名：");
		String uname = sc.next();
		System.out.println("**********************************************");
		if (uname.length() < USER_LENGTH_NUM) {
			return queryStudentView();
		}
		return uname;
	}

	/**
	 * 管理员的功能列表视图 添加试题信息
	 * 
	 * @return 管理员选择的功能模块
	 */
	public static Test addTestView() {
		System.out.println("**********************************************");
		System.out.println("\t欢迎管理员使用本考试系统");
		System.out.println("\t请输入试题的内容：");
		String testContent = sc.next();

		System.out.println("\t请输入试题的答案：");
		String testAnswer = sc.next();

		System.out.println("\t请输入试题的难易度：");
		String testDiffic = sc.next();
		System.out.println("**********************************************");
		if (testContent.length() < TEST_LENGTH_NUM) {
			return addTestView();
		}
		Test test = new Test(testContent, testAnswer, testDiffic);
		return test;
	}

	/**
	 * 管理员的功能列表视图 删除试题信息
	 * 
	 * @return 管理员选择的功能模块
	 */
	public static int deleteTestView() {
		System.out.println("**********************************************");
		System.out.println("\t欢迎管理员使用本考试系统");
		System.out.println("\t请输入要删除的试题序号：");
		int test_id = sc.nextInt() - '0';

		System.out.println("**********************************************");
		if (test_id < 0) {
			return deleteTestView();
		}
		return test_id;
	}

	/**
	 * 管理员的功能列表视图 修改试题信息
	 * 
	 * @return 管理员选择的功能模块
	 */
	public static int updateTestView() {
		System.out.println("**********************************************");
		System.out.println("\t欢迎管理员使用本考试系统");
		System.out.println("\t请输入要修改的试题序号：");
		int test_id = sc.nextInt() - '0';

		System.out.println("**********************************************");
		if (test_id < 0) {
			return updateTestView();
		}
		return test_id;
	}

	/**
	 * 管理员的功能列表视图 查询试题信息
	 * 
	 * @return 管理员选择的功能模块
	 */
	public static int queryTestView() {
		System.out.println("**********************************************");
		System.out.println("\t欢迎管理员使用本考试系统");
		System.out.println("\t请输入要查询的试题序号：");
		int test_id = sc.nextInt() - '0';

		System.out.println("**********************************************");
		if (test_id < 0) {
			return queryTestView();
		}
		return test_id;
	}

	/**
	 * 管理员的功能列表视图 批量导入试题 输入试题文件的路径传递 test_pathName 值给 FileInputStream
	 * 建议：后期使用文件打开功能直接选择文件
	 * 
	 * @return 管理员选择的功能模块
	 */
	public static String importTestView() {
		System.out.println("**********************************************");
		System.out.println("\t欢迎管理员使用本考试系统");
		System.out.println("\t请输入要导入的试题文件路径：");
		String test_pathName = sc.next();

		System.out.println("**********************************************");
		if (test_pathName.length() < 0) {
			return importTestView();
		}
		return test_pathName;
	}

	/**
	 * 考生的功能列表视图 考生修改当前用户的密码 1.查询当前登录用户的 user_name 2.根据当前用户的 user_name 进行修改操作
	 * 
	 * @return 管理员选择的功能模块
	 */
	public static String updateUserPasswd() {
		// String user_name = ClientMain.main(args);
		System.out.println("**********************************************");
		// System.out.println("\t欢迎"+user_name+"使用本考试系统");
		System.out.println("\t欢迎考生使用本考试系统");
		System.out.println("\t请输入要修改的密码是：");
		String user_passwd = sc.next();
		return user_passwd;
	}

	/**
	 * 考生的功能列表视图 开始考试
	 * 
	 * @return 考生选择的功能模块
	 */
	public static String startExam() {
		System.out.println("**********************************************");
		System.out.println("\t开始考试！");
		String start_exam = "startExam";// 返回一个值，当该值等于客户端的要求时执行程序
		return start_exam;
	}

	/**
	 * 考生的功能列表视图 查询成绩 根据每次考试产生的名称进行查询（由服务端自动生成并显示）
	 * 
	 * @return 考生选择的功能模块
	 */
	public static String queryUserGrade() {
		System.out.println("**********************************************");
		System.out.println("\t请输入要查询的成绩的考试的名称：");
		String queryUserGrade = sc.next();
		return queryUserGrade;
	}

	/**
	 * 考生的功能列表视图 导出当前用户的所有成绩 [exam_id]+[exam_name]+[grade]
	 * 
	 * @return 考生选择的功能模块
	 */
	public static String exportUserGrade() {
		System.out.println("**********************************************");
		String exportUserGrade = "exportUserGrade";
		return exportUserGrade;
	}

	/**
	 * 考生的功能列表视图 每次考生考试都会产生一个 exam 的 exam_id
	 * 在exam中存储该次考试的user_id，grade_id，test_ids
	 * test_ids是一个集合，存储该次考试说产生的所有试题的test_id 当考生选择导出试题的时候输入考试的编号 exam_id
	 * 则导出该次考试的全部试题test_Content 、答案 test_Answer 和该次考试的成绩 grade 成绩 grade 通过外表连接查询
	 * grade 表中的grade_id获取
	 * 
	 * @return 考生选择的功能模块
	 */
	public static String exportUserExam() {
		System.out.println("**********************************************");
		System.out.println("\t欢迎考生使用本考试系统");
		System.out.println("\t开始导出所有已考试试题的试卷");
		String exportUserExam = "exportUserExam";
		return exportUserExam;
	}

}

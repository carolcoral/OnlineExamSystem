package cn.xdl.exam.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import cn.xdl.exam.model.Test;
import cn.xdl.exam.model.User;
import cn.xdl.exam.view.ViewUtil;

public class ClientMain {
	private static Socket clientSocket;

	private static HashMap<String, Object> sendMsg = new HashMap<>();// 发送的结果数据
	private static HashMap<String, Object> receiveMsg = new HashMap<>();// 接收的请求数据

	private static ObjectInputStream ois;// 和客户端交互的输入流对象
	private static ObjectOutputStream oos;// 和客户端交互的输出流对象

	public static void main(String[] args) {
		try {
			// 1.创建socket对象，连接服务器
			clientSocket = new Socket("192.168.10.4", 8888);
			// 2.获取流对象
			oos = new ObjectOutputStream(clientSocket.getOutputStream());
			ois = new ObjectInputStream(clientSocket.getInputStream());

			// 3.交互（显示欢迎页面）
			int type = ViewUtil.welcomeView();
			switch (type) {
			case 1:// 考生登陆
				studentOper();
				break;

			case 2:// 管理员登陆
				managerOper();
				break;
			case 3:// 退出
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 管理员登录成功后欢迎界面
	private static void managerOper() {
		// 1.登陆页面
		User mUser = ViewUtil.managerLoginView();
		// System.out.println("mUser = " + mUser);
		// 2.封装管理员信息对象，封装请求对象sendMsg
		sendMsg.put("type", "1");
		sendMsg.put("userInfo", mUser);
		// 3.调用sendMessage
		sendMessage();
		// 4.调用receiveMessage方法接收结果
		receiveMessage();
		String resultType = (String) receiveMsg.get("type");
		// 5.判断结果
		switch (resultType) {
		case "1001":
			System.out.println("登陆成功");
			while (true) {
				// 1.登陆成功 显示管理员功能列表
				int selectNum = ViewUtil.managerListView();
				switch (selectNum) {
				case 0:// 退出
					System.exit(0);
					break;
				case 1:// add 添加学生信息
					User addStudent = ViewUtil.addStudentView();
					// 封装
					sendMsg.put("type", "11");
					sendMsg.put("userInfo", addStudent);
					break;
				case 2:// del 删除学生信息
					String deleteStudent = ViewUtil.delStudentView();
					// 封装
					sendMsg.put("type", "12");
					sendMsg.put("userInfo", deleteStudent);
					break;
				case 3:// update 修改学生信息
					String updateStudent = ViewUtil.updateStudentView();
					// 封装
					sendMsg.put("type", "141");// 先查询是否存在该学生，根据返回值进行下一步操作
					sendMsg.put("userInfo", updateStudent);
					break;
				case 4:// query 查询学生信息
					String queryStudent = ViewUtil.queryStudentView();
					// 封装
					sendMsg.put("type", "14");
					sendMsg.put("userInfo", queryStudent);
					break;
				case 5:// add test 添加试题
					Test addTest = ViewUtil.addTestView();
					// 封装
					sendMsg.put("type", "15");
					sendMsg.put("userInfo", addTest);
					break;
				case 6:// del test 删除试题
					int deleteTest = ViewUtil.deleteTestView();
					// 封装
					sendMsg.put("type", "16");
					sendMsg.put("userInfo", deleteTest);
					break;
				case 7:// update test 修改试题
					int updateTest = ViewUtil.updateTestView();
					// 封装
					sendMsg.put("type", "17");
					sendMsg.put("userInfo", updateTest);
					break;
				case 8:// query test 查找试题
					int queryTest = ViewUtil.queryTestView();
					// 封装
					sendMsg.put("type", "18");
					sendMsg.put("userInfo", queryTest);
					break;
				case 9:// import test 通过文件批量导入试题
					String importTest = ViewUtil.importTestView();
					// 封装
					sendMsg.put("type", "19");
					sendMsg.put("userInfo", importTest);
					break;
				}

				// 2.封装管理员信息对象，封装请求对象sendMsg
				// 3.调用sendMessagev
				sendMessage();
				// 4.调用receiveMessage方法接收结果 switch(){
				receiveMessage();
				String resultType2 = (String) receiveMsg.get("type");
				// 显示服务器端处理之后的处理结果
				switch (resultType2) {
				case "1003":// add
					System.out.println("添加考生成功!");
					break;
				case "1033":// add
					System.out.println("添加考生失败!");
					break;
				case "1004":// del
					System.out.println("删除考生成功!");
					break;
				case "1044":// del
					System.out.println("删除考生失败!");
					break;
				case "1005":// update 修改学生信息
					User update_Student = ViewUtil.addStudentView();
					sendMsg.put("type", "13");
					sendMsg.put("userInfo", update_Student);
					switch (resultType2) {
					case "10051":
						System.out.println("修改考生成功!");
						System.out.println("修改后的考生信息是：" + receiveMsg.get("userInfo"));
						break;
					case "10052":
						System.out.println("修改考生失败!");
						break;
					}
					break;
				case "1006":// query
					System.out.println("查询考生成功!");
					System.out.println("该考生的信息是：" + receiveMsg.get("userInfo"));
					break;
				case "1066":// query
					System.out.println("查询考生失败!");
					ViewUtil.queryStudentView();
					break;
				case "1007":// add 1
					System.out.println("添加试题成功!");
					break;
				case "1008":// del 1
					System.out.println("删除试题成功!");
					break;
				case "1009":// update 1
					System.out.println("修改试题成功!");
					break;
				case "1010":// query 1
					System.out.println("查询试题成功!");
					break;
				case "1011":// import test
					System.out.println("批量导入试题成功!");
					break;
				}

			}
		case "1002":
			System.out.println("登陆失败！");
			managerOper();
			break;
		}

	}

	// 学生用户登录成功后欢迎界面
	private static void studentOper() {
		// 1.登陆页面
		User mUser = ViewUtil.studentLoginView();
		// 2.封装学生信息对象，封装请求对象sendMsg
		sendMsg.put("type", "1");
		sendMsg.put("userInfo", mUser);
		// 3.调用sendMessage
		sendMessage();
		// 4.调用receiveMessage方法接收结果
		receiveMessage();
		String resultType = (String) receiveMsg.get("type");
		// String resultUserInfo = (String) receiveMsg.get("userInfo");
		// 5.判断结果
		switch (resultType) {
		case "1001":
			System.out.println("登陆成功");
			while (true) {
				// 1.登陆成功 显示管理员功能列表
				int selectNum = ViewUtil.studentListView();
				switch (selectNum) {
				case 0:// 退出
					System.exit(0);
					// ViewUtil.welcomeView();
					break;
				case 1:// updatePasswd 修改当前用户密码
					String updateUserPasswd = ViewUtil.updateUserPasswd();
					// 封装
					sendMsg.put("type", "21");
					sendMsg.put("userInfo", updateUserPasswd);
					break;
				case 2:// startUserExam 开始考试
					String startUserExam = ViewUtil.startExam();
					// 封装
					sendMsg.put("type", "22");
					sendMsg.put("userInfo", startUserExam);
					break;
				case 3:// queryUserGrade 查询用户成绩
					String queryUserGrade = ViewUtil.queryUserGrade();
					// 封装
					sendMsg.put("type", "23");
					sendMsg.put("userInfo", queryUserGrade);
					break;
				case 4:// exportUserGrade 导出用户所有成绩
					String exportUserGrade = ViewUtil.exportUserGrade();
					// 封装
					sendMsg.put("type", "24");
					sendMsg.put("userInfo", exportUserGrade);
					break;
				case 5:// exportUserExam 导出某次考试的所有试题
					String exportUserExam = ViewUtil.exportUserExam();
					// 封装
					sendMsg.put("type", "25");
					sendMsg.put("userInfo", exportUserExam);
					break;
				}

				// 2.封装管理员信息对象，封装请求对象sendMsg
				// 3.调用sendMessagev
				sendMessage();
				// 4.调用receiveMessage方法接收结果 switch(){
				receiveMessage();
				String resultType2 = (String) receiveMsg.get("type");
				// 显示服务器端处理之后的处理结果
				switch (resultType2) {
				case "2001":// 修改用户密码
					System.out.println("修改密码完成！");
					break;
				case "2011":// 修改用户密码
					System.out.println("修改密码失败！");
					break;
				case "2002":// 开始考试 简单考试版本，每完成一道题进行验证同时显示结果
					// for (int i = 1; i <= 10; i++) {
					String resQuestion = (String) receiveMsg.get("userInfo");
					System.out.println("第" + "1" + "题是：" + resQuestion);
					String resExamTest = ViewUtil.joinExam();
					sendMsg.put("type", "2222");// 发送类型，请求服务器验证答案
					sendMsg.put("userInfo", resExamTest);// 发送答案，在服务端验证答案并返回
					String startUserExam = ViewUtil.startExam();
					// 封装
					sendMsg.put("type", "22");
					sendMsg.put("userInfo", startUserExam);
					// }
					System.out.println("考试结束！");
					break;
				case "2022":// 开始考试失败
					System.out.println("开始考试失败！请联系管理员！");
					studentOper();
					break;
				case "2003":// 查询用户成绩成功
					System.out.println("查询成绩成功");
					System.out.println(receiveMsg.get("userInfo"));
					// 返回方法
					break;
				case "2033":// 查询用户成绩失败
					System.out.println("查询成绩失败");
					ViewUtil.queryUserGrade();
					// 返回方法
					break;
				case "2004":// 导出用户所有成绩
					System.out.println("导出成绩完成！");
					break;
				case "2044":// 导出用户所有成绩
					System.out.println("导出成绩失败！");
					break;
				case "2005":// 导出用户所有已考试试卷
					System.out.println("导出试卷完成！");
					break;
				case "2055":// 导出用户所有已考试试卷
					System.out.println("导出试卷失败！");
					break;
				}

			}
		case "1002":
			System.out.println("登陆失败！");
			managerOper();
			break;
		}

	}

	/**
	 * 发送处理结果
	 */
	private static void sendMessage() {
		try {
			oos.writeObject(sendMsg);
			oos.flush();
			sendMsg = new HashMap<>();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("sendMessage");
	}

	@SuppressWarnings("unchecked")
	private static void receiveMessage() {
		System.out.println("sendMessage");
		try {
			receiveMsg = new HashMap<>();
			receiveMsg = (HashMap<String, Object>) ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}

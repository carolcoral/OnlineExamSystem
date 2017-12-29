package cn.xdl.exam.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import cn.xdl.exam.dao.UserDaoService;
import cn.xdl.exam.model.Grade;
import cn.xdl.exam.model.User;

/*
 * 和客户端交互的线程类
 */
public class ServerThread implements Runnable {
	Socket clientSocket;

	private HashMap<String, Object> sendMsg = new HashMap<>();// 发送的结果数据
	private HashMap<String, Object> receiveMsg = new HashMap<>();// 接收的请求数据

	private ObjectInputStream ois;// 和客户端交互的输入流对象
	private ObjectOutputStream oos;// 和客户端交互的输出流对象
	private boolean flag = true;// 标识客户端是否在线的标记，true：在线

	public ServerThread(Socket clientSocket) {
		super();
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		// 1.获取流对象
		try {
			ois = new ObjectInputStream(clientSocket.getInputStream());
			oos = new ObjectOutputStream(clientSocket.getOutputStream());
			// 2.持续的和客户端交互
			while (flag) {
				receiveMessage();// 阻塞
				sendMessage();
			}
		} catch (IOException e) {
			e.printStackTrace();
			flag = false;
		}
	}

	/**
	 * 接收客户端请求，并且处理
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	private void receiveMessage() {
		System.out.println("receiveMessage");
		try {
			// 1.接收客户端发送的请求集合
			receiveMsg = (HashMap<String, Object>) ois.readObject();
			// 2.处理
			String type = (String) receiveMsg.get("type");
			System.out.println("type:" + type);
			switch (type) {

			// 公共部分
			case "0":// 退出
				System.exit(0);
				break;
			case "1":// 登陆
				// 1.获取用户登陆信息
				User user = (User) receiveMsg.get("userInfo");
				// 2.判断用户是否能登陆
				if (UserDaoService.login(user)) {
					// 3.封装结果数据
					sendMsg.put("type", "1001");
				} else {
					sendMsg.put("type", "1002");
				}

				break;

			// 用户操作返回值部分
			case "21":// 更改密码
				String updatePasswd = (String) receiveMsg.get("userInfo");
				boolean updatePasswdRes = UserDaoService.updateUserPasswd(updatePasswd);
				if (updatePasswdRes) {
					// 成功
					sendMsg.put("type", "2001");
				} else {
					// 失败
					sendMsg.put("type", "2011");
				}
				break;
			case "22":// 考试

				break;
			case "23":// 查询成绩
				int queryUserGradeId = (int) receiveMsg.get("userInfo");
				Grade query_User_Grade = UserDaoService.queryUserGrade(queryUserGradeId);
				if (queryUserGradeId == query_User_Grade.getGrade_id()) {
					// 成功
					sendMsg.put("type", "2003");
					sendMsg.put("userInfo", query_User_Grade);
				} else {
					// 失败
					sendMsg.put("type", "2033");
				}
				break;
			case "24":// 导出所有成绩

				break;
			case "25":// 导出所有试题

				break;

			// 管理员操作返回值部分
			case "11":// add user 添加用户
				// 1.获取用户登陆信息
				User insertUser = (User) receiveMsg.get("userInfo");
				boolean insertResult = UserDaoService.insertUser(insertUser);
				if (insertResult) {
					// 插入成功
					sendMsg.put("type", "1003");
				} else {
					// 插入失败
					sendMsg.put("type", "1033");
				}
				break;
			case "12":// del user
				String deleteUser = (String) receiveMsg.get("userInfo");
				boolean deleteUserResult = UserDaoService.deleteUser(deleteUser);
				if (deleteUserResult) {
					// success
					sendMsg.put("type", "1004");
				} else {
					// faild
					sendMsg.put("type", "1044");
				}

				break;
			case "13":// update user
				User update_User = (User) receiveMsg.get("userInfo");
				boolean update_Res = UserDaoService.updateUser(update_User);
				if (update_Res) {
					// 成功
					sendMsg.put("type", "10051");
					sendMsg.put("userInfo", update_User);
				} else {
					// 失败
					sendMsg.put("type", "10052");
				}
				break;
			case "14":// find user 查询学生信息
				String queryUser = (String) receiveMsg.get("userInfo");
				User queryUser1 = UserDaoService.queryUser(queryUser);
				if (queryUser.equals(queryUser1.getUname())) {
					// 查询成功
					sendMsg.put("type", "1006");
					sendMsg.put("userInfo", queryUser1);
				} else {
					// 查询失败
					sendMsg.put("type", "1066");
				}
				break;
			case "141":// find user 查询学生信息
				String queryUser2 = (String) receiveMsg.get("userInfo");
				User queryUser12 = UserDaoService.queryUser(queryUser2);
				if (queryUser2.equals(queryUser12.getUname())) {
					// 查询成功
					sendMsg.put("type", "1005");
					sendMsg.put("userInfo", queryUser12);
				} else {
					// 查询失败
					sendMsg.put("type", "10052");
				}
				break;
			case "15":// add test 添加试题

				break;
			case "16":// delete test 删除试题

				break;
			case "17":// update test修改试题

				break;
			case "18":// query test 查询试题

				break;
			case "19":// import test 批量导入试题

				break;
			}

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			flag = false;
		}

	}

	/**
	 * 发送处理结果
	 */
	private void sendMessage() {
		try {
			oos.writeObject(sendMsg);
			oos.flush();
			sendMsg = new HashMap<>();
		} catch (IOException e) {
			e.printStackTrace();
			flag = false;
		}
		System.out.println("sendMessage");
	}

}

# OnlineExamSystem
在线考试答题系统

# 服务端（ServerOnlineExamSystem）

  ## Main(入口)
     
  >1.[ServerMain.java](https://github.com/carolcoral/OnlineExamSystem/blob/master/ServerOnlineExamSystem/main/ServerMain.java)
          
  >2.[ServerThread.java](https://github.com/carolcoral/OnlineExamSystem/blob/master/ServerOnlineExamSystem/main/ServerThread.java)
  
  ## DAO(数据层)
  
  >1.[UserDao.java](https://github.com/carolcoral/OnlineExamSystem/blob/master/ServerOnlineExamSystem/dao/UserDao.java)
   
  >2.[UserDaoFactory.java](https://github.com/carolcoral/OnlineExamSystem/blob/master/ServerOnlineExamSystem/dao/UserDaoFactory.java)
   
  >3.[UserDaoImp.java](https://github.com/carolcoral/OnlineExamSystem/blob/master/ServerOnlineExamSystem/dao/UserDaoImp.java)
   
  >4.[UserDaoService.java](https://github.com/carolcoral/OnlineExamSystem/blob/master/ServerOnlineExamSystem/dao/UserDaoService.java)
  
  ## Model(模型层)
  
  >1.[Exam.java](https://github.com/carolcoral/OnlineExamSystem/blob/master/ServerOnlineExamSystem/model/Exam.java) 试卷实体类
  
  >2.[Grade.java](https://github.com/carolcoral/OnlineExamSystem/blob/master/ServerOnlineExamSystem/model/Grade.java) 成绩实体类，保存用户成绩
  
  >3.[Test.java](https://github.com/carolcoral/OnlineExamSystem/blob/master/ServerOnlineExamSystem/model/Test.java) 试题实体类，保存试题
  
  >4.[User.java](https://github.com/carolcoral/OnlineExamSystem/blob/master/ServerOnlineExamSystem/model/User.java) 用户实体类
  
  ## Util(工具层)
  
  >1.[DBUtils.java](https://github.com/carolcoral/OnlineExamSystem/blob/master/ServerOnlineExamSystem/util/DBUtils.java) 数据库链接文件
  
  >2.[db_config.properties](https://github.com/carolcoral/OnlineExamSystem/blob/master/ServerOnlineExamSystem/util/db_config.properties) 链接数据库的配置文件

# 客户端（ClientOnlineExamSystem）

  ## Main(入口)
  
  >1.[ClientMain.java](https://github.com/carolcoral/OnlineExamSystem/blob/master/ClientOnlineExamSystem/main/ClientMain.java) 客户端入口

  ## Model(模型层)
  
  >1.[Test.java](https://github.com/carolcoral/OnlineExamSystem/blob/master/ClientOnlineExamSystem/model/Test.java) 试题实体类
  
  >2.[User.java](https://github.com/carolcoral/OnlineExamSystem/blob/master/ClientOnlineExamSystem/model/User.java) 用户实体类（包含了考生和管理员
  
  ## View(视图层)
  
  >1.[ViewUtils.java](https://github.com/carolcoral/OnlineExamSystem/blob/master/ClientOnlineExamSystem/view/ViewUtil.java) 客户端的视图工具类，向用户展示功能界面和其他界面等
     

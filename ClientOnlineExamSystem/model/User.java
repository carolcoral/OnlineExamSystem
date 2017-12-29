package cn.xdl.exam.model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String uname;// 用户名称
	private String upwd;// 用户密码
	private Boolean isManager;// 判断该用户是否为管理员

	public User() {
		super();
	}

	public User(String uname, String upwd, Boolean isManager) {
		super();
		 setUname(uname);
		 setUpwd(upwd);
		 setIsManager(isManager);
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public Boolean getIsManager() {
		return isManager;
	}

	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isManager == null) ? 0 : isManager.hashCode());
		result = prime * result + ((uname == null) ? 0 : uname.hashCode());
		result = prime * result + ((upwd == null) ? 0 : upwd.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (isManager == null) {
			if (other.isManager != null)
				return false;
		} else if (!isManager.equals(other.isManager))
			return false;
		if (uname == null) {
			if (other.uname != null)
				return false;
		} else if (!uname.equals(other.uname))
			return false;
		if (upwd == null) {
			if (other.upwd != null)
				return false;
		} else if (!upwd.equals(other.upwd))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "用户信息 [用户名=" + uname + ", 密码=" + upwd + (isManager ? "是管理员" : "不是管理员") + "]";
	}

}

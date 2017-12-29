package cn.xdl.exam.model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private int uid;
	private static String uname;// �û�����
	private String upwd;// �û�����
	private Boolean isManager;// �жϸ��û��Ƿ�Ϊ����Ա

	public User() {
		super();
	}

	public User(int uid, String uname, String upwd, Boolean isManager) {
		super();
		setUid(uid);
		setUname(uname);
		setUpwd(upwd);
		setIsManager(isManager);
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public static String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		User.uname = uname;
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
		result = prime * result + uid;
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
		if (uid != other.uid)
			return false;
		if (uname == null) {
			if (User.uname != null)
				return false;
		} else if (!uname.equals(User.uname))
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
		return "�û���Ϣ�� [�û����=" + uid + ", �û���=" + uname + ", ����=" + upwd + (isManager ? "�ǹ���Ա" : "���ǹ���Ա");
	}

}

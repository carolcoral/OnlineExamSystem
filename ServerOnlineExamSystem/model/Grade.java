package cn.xdl.exam.model;

/**
 * 
 * ��Ŀ���ƣ�ServerOnlineExamSystem �����ƣ�Grade �������� �����ˣ�Carol ����ʱ�䣺2017��12��29��
 * ����11:56:38
 * 
 * @version
 */
public class Grade {
	private int grade_id;
	private int user_id;
	private int grade;
	public Grade() {
		super();
	}
	public Grade(int grade_id, int user_id, int grade) {
		super();
		setGrade(grade);
		setGrade_id(grade_id);
		setUser_id(user_id);
	}
	public int getGrade_id() {
		return grade_id;
	}
	public void setGrade_id(int grade_id) {
		this.grade_id = grade_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + grade;
		result = prime * result + grade_id;
		result = prime * result + user_id;
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
		Grade other = (Grade) obj;
		if (grade != other.grade)
			return false;
		if (grade_id != other.grade_id)
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "�ɼ��� [�ɼ����=" + grade_id + ", �û����=" + user_id + ", �ɼ�=" + grade + "]";
	}
}

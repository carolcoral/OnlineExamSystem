/**
 * 
 */
package cn.xdl.exam.model;

/**
 * 
 * 项目名称：ServerOnlineExamSystem 类名称：Exam 类描述： 创建人：Carol 创建时间：2017年12月29日
 * 下午1:52:08
 * 
 * @version
 */
public class Exam {
	private int exam_id;
	private String exam_name;
	private int grade_id;
	private int user_id;
	private String test_ids;
	public Exam() {
		super();
	}
	public Exam(int exam_id, String exam_name, int grade_id, int user_id, String test_ids) {
		super();
		setExam_id(exam_id);
		setExam_name(exam_name);
		setGrade_id(grade_id);
		setUser_id(user_id);
		setTest_ids(test_ids);
	}
	public int getExam_id() {
		return exam_id;
	}
	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}
	public String getExam_name() {
		return exam_name;
	}
	public void setExam_name(String exam_name) {
		this.exam_name = exam_name;
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
	public String getTest_ids() {
		return test_ids;
	}
	public void setTest_ids(String test_ids) {
		this.test_ids = test_ids;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + exam_id;
		result = prime * result + ((exam_name == null) ? 0 : exam_name.hashCode());
		result = prime * result + grade_id;
		result = prime * result + ((test_ids == null) ? 0 : test_ids.hashCode());
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
		Exam other = (Exam) obj;
		if (exam_id != other.exam_id)
			return false;
		if (exam_name == null) {
			if (other.exam_name != null)
				return false;
		} else if (!exam_name.equals(other.exam_name))
			return false;
		if (grade_id != other.grade_id)
			return false;
		if (test_ids == null) {
			if (other.test_ids != null)
				return false;
		} else if (!test_ids.equals(other.test_ids))
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Exam [exam_id=" + exam_id + ", exam_name=" + exam_name + ", grade_id=" + grade_id + ", user_id=" + user_id + ", test_ids=" + test_ids + "]";
	}
}

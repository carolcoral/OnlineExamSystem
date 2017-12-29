package cn.xdl.exam.model;

/**
 * 
 * 项目名称：ClientOnlineExamSystem 类名称：Test 类描述：试题：试题序号，试题内容，试题答案，试题难易度 创建人：Carol
 * 创建时间：2017年12月28日 下午4:18:06
 * 
 * @version
 */
public class Test {
	// private int Test_id;//试题序号
	private String testContent;// 试题内容
	private String testAnswer;// 试题答案
	private String testDiffic;// 试题难易度 1 难 2 中等 3 简单

	public Test(String testContent, String testAnswer, String testDiffic) {
		super();
		// setTest_id(test_id);
		setTestContent(testContent);
		setTestAnswer(testAnswer);
	}

	// public int getTest_id() {
	// return Test_id;
	// }
	// public void setTest_id(int test_id) {
	// Test_id = test_id;
	// }
	public String getTestContent() {
		return testContent;
	}

	public void setTestContent(String testContent) {
		this.testContent = testContent;
	}

	public String getTestAnswer() {
		return testAnswer;
	}

	public void setTestAnswer(String testAnswer) {
		this.testAnswer = testAnswer;
	}

	public String getTestDiffic() {
		return testDiffic;
	}

	public void setTestDiffic(String testDiffic) {
		this.testDiffic = testDiffic;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((testAnswer == null) ? 0 : testAnswer.hashCode());
		result = prime * result + ((testContent == null) ? 0 : testContent.hashCode());
		result = prime * result + ((testDiffic == null) ? 0 : testDiffic.hashCode());
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
		Test other = (Test) obj;
		if (testAnswer == null) {
			if (other.testAnswer != null)
				return false;
		} else if (!testAnswer.equals(other.testAnswer))
			return false;
		if (testContent == null) {
			if (other.testContent != null)
				return false;
		} else if (!testContent.equals(other.testContent))
			return false;
		if (testDiffic == null) {
			if (other.testDiffic != null)
				return false;
		} else if (!testDiffic.equals(other.testDiffic))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Test [testContent=" + testContent + ", testAnswer=" + testAnswer + ", testDiffic=" + testDiffic + "]";
	}

}

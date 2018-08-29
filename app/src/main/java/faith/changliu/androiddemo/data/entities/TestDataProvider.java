package faith.changliu.androiddemo.data.entities;

import java.util.Collections;
import java.util.List;

public class TestDataProvider {
	private int code;
	private List<Test> data;
	private String message;
	private int total;

	public TestDataProvider() {
		code = 0;
		data = Collections.emptyList();
		message = "";
		total = 0;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public List<Test> getData() {
		return data;
	}

	public void setData(List<Test> data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}

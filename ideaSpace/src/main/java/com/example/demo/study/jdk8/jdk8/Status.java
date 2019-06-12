package com.example.demo.study.jdk8.jdk8;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2018/8/9
 */
public enum  Status {
	处理成功("000","处理成功"),
	处理失败("001","处理失败"),
	异常("002","处理异常");

	private String code;

	private String msg;

	Status(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}

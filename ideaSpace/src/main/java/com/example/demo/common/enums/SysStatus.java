package com.example.demo.common.enums;

/**
 * 系统处理标识
 */
public enum SysStatus {
	
	/*系统级  统一S开头*/
	处理成功("000","处理成功","0"),
	系统错误("S101","系统错误","0"),
	URL不合法("S102","URL不合法","0"),
	连接超时("S103","连接超时","0"),
	内部错误("S104","内部错误","0"),
	解密错误("S105","加密错误","0"),
	设备号不匹配("S106","设备号不匹配","0"),
	参数校验失败("S107","参数校验失败","0"),
	参数解析失败("S120","参数解析失败","0"),
	参数错误("S122","参数错误","0"),
	缺少必填参数("S121","缺少必填参数","0"),
	设备已过有效期("S115","设备已过有效期","0"),
	调用子系统service服务异常("S107","调用子系统service服务异常","0"),

	/**
	 * GlobalResult返回值统一使用"处理成功"("000")，该枚举只用于长亮返回值的判断
	 */
	失败("999","失败" ,"0");






	private String code;
	private String msg;
	//0 统一处理为系统错误  1客户端直接显示
	private String flag;
	private SysStatus(String code,String msg,String flag){
		this.code = code;
		this.msg = msg;
		this.flag=flag;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
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

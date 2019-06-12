/**    
* 国美金控
* @Title: GlobalResult.java  
* @Package com.gome.precash.common.global  
* @Description: 全局结果返回公共定义类 
* @author Along.Yan    
* @date 2016年5月27日 上午11:55:12  
* @version V1.0    
*/
package com.example.demo.common;

import java.io.Serializable;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.enums.SysStatus;



/**
 * 
* @ClassName: GlobalResult  
* @Description: 全局结果返回公共定义类 
* @author Along.Yan  
* @date 2016年5月27日 上午11:51:52  
*
 */
public class GlobalResult implements Serializable{
	private static final long serialVersionUID = 3131808708432303266L;
	private String code;
	private String msg;
	private String serial;//请求流水号
	private JSONObject data = new JSONObject();

	public GlobalResult(){
	}
	
	public GlobalResult(String code,String msg){
		this.code=code;
		this.msg=msg;
	}
	/**
	 * <Strong>Description</Strong>   新建构造方法，封装业务数据集
	 * @param code 返回前台代码
	 * @param msg	返回前台信息
	 * @param data 业务数据集--JSONObject对象
	 * @author Caomr
	 * @update 2017.07.21
	 */
	public GlobalResult(String code,String msg,JSONObject data){
		this.code=code;
		this.msg=msg;
		this.data=data;
	}
	
	public GlobalResult(String code,String msg,String serial){
		this.code=code;
		this.msg=msg;
		this.serial=serial;
	}
	
	public static GlobalResult success(){
		GlobalResult globalResult = new GlobalResult();
		globalResult.setCode(SysStatus.处理成功.getCode());
		globalResult.setMsg(SysStatus.处理成功.getMsg());
		return globalResult;
	}
	
	public static GlobalResult failure(){
		GlobalResult globalResult = new GlobalResult();
		globalResult.setCode(SysStatus.失败.getCode());
		globalResult.setMsg(SysStatus.失败.getMsg());
		return globalResult;
	}
	
	/**
	 * Discription:  默认返回系统错误
	 * Created on: 2017/7/12 13:39
	 * @author: zuorenzhi
	 */
	public static GlobalResult systemError(){
		GlobalResult globalResult = new GlobalResult();
		globalResult.setCode(SysStatus.系统错误.getCode());
		globalResult.setMsg(SysStatus.系统错误.getMsg());
		return globalResult;
	}

	public GlobalResult(String serial){
		this.serial=serial;
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
	
	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	/**
	 * 获取业务数据集
	 * @return
	 * */
	public JSONObject getData() {
		return data;
	}

	/**
	 * @param data
	 * 设置业务数据集
	 * */
	public void setData(JSONObject data) {
		this.data = data;
	}



	/**
	 * 向业务数据集中添加数据
	 * @param key
	 * @param value
	 * */
	public void putData(String key,Object value){
		this.data.put(key, value);
	}
	
	/**
	 * 向业务数据集中添加数据
	 * @param key
	 * @param value
	 * */
	public void putData(Map<String, Object> map){
		this.data.putAll(map);
	}



	


	/**
	 * 从业务数据集中移除数据
	 * @param key
	 * */
	public void revData(Object key){
		this.data.remove(key);
	}
	
	/**
	 * 从业数据集中获取数据
	 * @param key
	 * @return
	 * */
	public Object obtainData(Object key){
		return this.data.get(key);
	}
	
	/**转换成json串*/
	public String toJSONString(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code",this.code);
		jsonObject.put("msg",this.msg);
		jsonObject.put("serial",this.serial);
		jsonObject.put("data",this.data);
		return jsonObject.toString();
	}
	
	public static void main(String[] args) {
		GlobalResult c=new GlobalResult();
		c.setCode("100");
		c.setMsg("test");
		c.putData("name", "1");
		c.putData("age", "2");
		System.out.println(c.toString());
	}
}

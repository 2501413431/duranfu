//package com.example.demo.common.utils;
//
//import java.text.SimpleDateFormat;
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//import com.taobao.api.ApiException;
//import com.taobao.api.DefaultTaobaoClient;
//import com.taobao.api.TaobaoClient;
//import com.taobao.api.request.AlibabaAliqinFcSmsNumQueryRequest;
//import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
//import com.taobao.api.response.AlibabaAliqinFcSmsNumQueryResponse;
//import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
///**
// * Copyright: Copyright (c) 2016 zhoudu
// *
// * @ClassName: TestCodeUtil.java
// * @Description: 验证码发送及校验工具类
// *
// * @version: v1.0.0
// * @author: 徐佑健
// * @date: 2016年8月5日 上午9:37:02
// *
// * Modification History:
// * Date         Author          Version            Description
// *---------------------------------------------------------*
// * 2016年8月5日     徐佑健           v1.0.0               修改原因
// */
//public class TestCodeUtil
//{
//	protected static Logger logger = LoggerFactory.getLogger(TestCodeUtil.class);
//
//	//官网的URL
//	private static String url="http://gw.api.taobao.com/router/rest";
//	//成为开发者，创建应用后阿里大鱼系统自动生成
//	private static String appkey="23425712";
//	//成为开发者，创建应用后阿里大鱼系统自动生成
//	private static String secret="1d07f4de2e48311a72f132098cd71e35";
//
//	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//	private static int tenMinutes=10*60*1000;
//
//	private static String maxNum="isv.BUSINESS_LIMIT_CONTROL";
//
//
//	public static final Integer SMS_AUTHCODE_SEND_TIMES_DAY_LIMIT = 20; // 短信验证码每天最大请求发送次数
//	public static final Integer SMS_INVALID_SECOND = 600; // 短信验证码有效时长（秒），10分钟内有效
//	public static final String SMS_REGISTER = "【包学习】验证码:{0}，十分钟内有效";
//    public static final String SMS_RESET_PASS = "【包学习】验证码:{0}，十分钟内有效";
//	/**
//	 *
//	 * @Function: TestCodeUtil::getTestCode
//	 * @Description: 通过阿里大鱼获取验证码
//	 * @param phone
//	 * @throws Exception
//	 * @version: v1.0.0
//	 * @author: 徐佑健
//	 * @param userCode_reg_updatePwd
//	 * @date: 2016年8月5日 上午11:46:35
//	 *
//	 * Modification History:
//	 * Date         Author          Version            Description
//	 *-------------------------------------------------------------
//	 */
//    public static void getTestCode(String phone, String code) throws Exception
//
//	{
//		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
//		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
//		req.setExtend(code);
//		req.setSmsType("normal");
//		req.setSmsFreeSignName("包学习");
//		req.setSmsParamString("{\"code\":\""+code+"\"}");
//		req.setRecNum(phone);
//		//SMS_13005066是信息模板编号
//		req.setSmsTemplateCode("SMS_137669652");
//
//		AlibabaAliqinFcSmsNumSendResponse rsp = null;
//		try {
//			rsp = client.execute(req);
//		} catch(ApiException e){
//			logger.error( e.getMessage());
//			logger.error("短信验证码发送失败");
//			throw new Exception("短信验证码发送失败");
//		}
//
//		Map<String, Object> resultMap =JsonUtil.toMap(rsp.getBody());
//		if(resultMap.containsKey("error_response"))
//		{
//			Map<String, Object> errorResponseMap=JsonUtil.toMap(JsonUtil.toJsonStr((resultMap.get("error_response"))));
//			String sub_code=(String)errorResponseMap.get("sub_code");
//			if (maxNum.equals(sub_code)) {
//				throw new Exception("您已超过每天/每小时最大可获取验证码次数");
//				}
//			else {
//				String subMsg=(String)errorResponseMap.get("sub_msg");
//				throw new Exception(subMsg);
//			}
//		}
//	}
//	/**
//	 *
//	 * @Function: TestCodeUtil::sendUserMessage
//	 * @Description: 用户组添加新用户后短信提醒初始密码
//	 * @param phone
//	 * @param keyPref
//	 * @throws Exception
//	 * @version: v1.0.0
//	 * @author: jiangwenye
//	 * @date: 2016年9月5日 下午2:36:03
//	 *
//	 * Modification History:
//	 * Date         Author          Version            Description
//	 *-------------------------------------------------------------
//	 */
//	public static void sendUserMessage(String userName, String phone, String password) throws Exception
//	{
//		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
//		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
//		req.setExtend(userName);
//		req.setSmsType("normal");
//		req.setSmsFreeSignName("包学习");
//		req.setSmsParamString("{\"name\":\""+userName+"\",\"password\":\""+password+"\"}");
//		req.setRecNum(phone);
//		//SMS_14246436是信息模板编号
//		req.setSmsTemplateCode("SMS_14246436");
//
//		AlibabaAliqinFcSmsNumSendResponse rsp = null;
//		try {
//			rsp = client.execute(req);
//		} catch(ApiException e){
//			logger.error( e.getMessage());
//			logger.error("短信提醒发送失败");
//			throw new Exception("短信提醒发送失败");
//		}
//	}
//	/**
//	 *
//	 * @Function: TestCodeUtil::isValidCheckCode
//	 * @Description: 验证验证码的正确性
//	 * @param phone
//	 * @param paramCode
//	 * @param type
//	 * @return
//	 * @throws Exception
//	 * @version: v1.0.0
//	 * @author: 徐佑健
//	 * @date: 2016年8月8日 下午4:04:39
//	 *
//	 * Modification History:
//	 * Date         Author          Version            Description
//	 *-------------------------------------------------------------
//	 */
//	public static boolean  isValidCheckCode(String phone, String code,Integer type)throws ApiException
//	{
//		//调用阿里大鱼接口
//		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
//		AlibabaAliqinFcSmsNumQueryRequest req = new AlibabaAliqinFcSmsNumQueryRequest();
//		req.setRecNum(phone);
//		String Date=DateUtils.getCurrrentDateStr();
//		Date=Date.replace("-", "");
//		req.setQueryDate(Date);
//		req.setCurrentPage(1L);
//		req.setPageSize(20L);
//
//		AlibabaAliqinFcSmsNumQueryResponse rsp = client.execute(req);
//		System.out.println(rsp.getBody());
//		try {
//			Map<String, Object> rspMap=JsonUtil.toMap(rsp.getBody());
//			Map<String, Object> aliResponse=JsonUtil.toMap(JsonUtil.toJsonStr((rspMap.get("alibaba_aliqin_fc_sms_num_query_response"))));
//			Map<String, Object> fc_dto=JsonUtil.toMap(JsonUtil.toJsonStr((aliResponse.get("values"))));
//			List<Map<String, Object>> resultList=JsonUtil.toMapList(JsonUtil.toJsonStr((fc_dto.get("fc_partner_sms_detail_dto"))));
//			int isExist =0;
//			String sendTime="";
//			for (Map<String, Object> resultMap : resultList)
//	        {
//	                if(resultMap.get("extend").equals(code))
//	                {
//	                	isExist=1;
//	                	sendTime=(String) resultMap.get("sms_receiver_time");
//	                	if (isTimeOut(sendTime)) {
//	                		return true;
//						}
//	                }
//	        }
//			if(isExist!=1)
//			{
//				return false ;
//			}
//
//		} catch (Exception e) {
//			logger.error(e.toString());
//			logger.error("短信验证码验证失败");
//			System.out.println("验证码验证失败");
//		}
//
//		return false;
//
//	}
///**
// *
// * @Function: TestCodeUtil::isTimeOut
// * @Description: 判断验证码是否过期
// * @param sendTime
// * @return
// * @throws Exception
// * @version: v1.0.0
// * @author: 徐佑健
// * @date: 2016年8月9日 上午10:01:31
// *
// * Modification History:
// * Date         Author          Version            Description
// *-------------------------------------------------------------
// */
//	private static boolean isTimeOut(String sendTime)throws Exception
//	{
//			String lastTime=DateUtils.getCurrrentTimeStr();
//			java.util.Date lastTimeDate = df.parse(lastTime);
//			java.util.Date sendTimeDate=df.parse(sendTime);
//		    long diff = lastTimeDate.getTime() - sendTimeDate.getTime();
//		    if(tenMinutes-diff>=0){
//		    	return true;
//		    }
//		    else {
//				return false;
//			}
//
//	}
//
//
//	// tds 测试一下查询接口
//	public static void main( String...argv) throws ApiException {
//		try {
//			getTestCode("","");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//}

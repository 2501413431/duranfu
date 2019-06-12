package com.example.demo.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.Exception.BusinessException;
import com.example.demo.common.annotation.LoginAuthType;
import com.example.demo.common.annotation.LoginAuthority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author Wen Jing
 * @date 2018/10/25 22:11
 */
@Component("validateLoginInterceptor")
@Slf4j
public class ValidateLoginInterceptor extends HandlerInterceptorAdapter {


	protected static final String CHARSET_NAME = "utf-8";

//	@Autowired
//	private RequestHelper requestHelper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		LoginAuthority loginAuthority = handlerMethod.getMethodAnnotation(LoginAuthority.class);
		if (loginAuthority == null) {
			throw new BusinessException("500", "方法不支持");
		}

		LoginAuthType loginAuthType = loginAuthority.authType();
		if (LoginAuthType.NO_AUTH.equals(loginAuthType)) {
			return true;
		} else if (LoginAuthType.OPTIONAL_AUTH.equals(loginAuthType)) {
			putLoginSession(request);
			return true;
		} else if (LoginAuthType.REQUEST_BODY_AUTH.equals(loginAuthType)) {
			boolean authed =  putLoginSession(request);
			if (!authed) {
				throw new BusinessException("L999","登录已失效，请重新登录");
			}
			return true;
		} else if (LoginAuthType.REQUEST_COOKIE_AUTH.equals(loginAuthType)) {
			return false;
		}

		return false;
	}

	private boolean putLoginSession(HttpServletRequest request) {
		boolean a = false;
//		byte[] body = null;
//		if (request instanceof BodyReaderHttpServletRequestWrapper) {
//			body = ((BodyReaderHttpServletRequestWrapper) request).getBody();
//		} else {
//			try {
//				InputStream in = request.getInputStream();
//				body = IOUtil.readInputStream(in);
//			} catch (Exception ex) {
//				LOGGER.error("", ex);
//			}
//		}
//		if (body == null || body.length == 0) {
//			return false;
//		}
//
//
//		GwcBaseRequest baseRequest = JSON
//				.parseObject(body, 0, body.length, Charset.forName(CHARSET_NAME), GwcBaseRequest.class);
//		RequestBodyThreadLocal.setRequestBody(body);
//		return requestHelper.putUserId(baseRequest.getSid(), request);
		return a;
	}
}

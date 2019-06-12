package com.example.demo.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2018/7/27.
 */
@Service
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

	private static ApplicationContext applicationContext;

	/**
	 * 取得存在静态变量中的applicationContext
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		assertContextInjected();
		return applicationContext;
	}


	/**
	 * 从静态变量applicationContextA中取得bean,自动转型为所赋值对象的类型
	 * @param name
	 * @param <T>
	 * @return
	 */
	public static <T>T getBean(String name) {
		assertContextInjected();
		return (T)applicationContext.getBean(name);
	}

	public static <T>T getBean(Class<T> requiredType) {
		assertContextInjected();
		return applicationContext.getBean(requiredType);
	}


	/**
	 * 实现ApplicationContextAware接口，注入context到静态变量中
	 * @param applicationContext
	 * @throws BeansException
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextHolder.applicationContext = applicationContext;
	}




	@Override
	public void destroy() throws Exception {
		SpringContextHolder.clearHolder();
	}

	/**
	 * 清除springContextHolder中的applicationContext
	 */
	public static void clearHolder() {
		applicationContext = null;
	}


	/**
	 * 检查applicationContext不为空
	 */
	private static void assertContextInjected() {
		if (applicationContext == null) {
			System.out.print("applicaitonContext属性未注入");
		}
	}
}

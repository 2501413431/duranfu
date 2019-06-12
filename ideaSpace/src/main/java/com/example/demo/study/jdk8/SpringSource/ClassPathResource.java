package com.example.demo.study.jdk8.SpringSource;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2018/10/19
 */
public class ClassPathResource {
	private final String path;
	private ClassLoader classLoader;
	private Class<?> clazz;

	public ClassPathResource(String path) {
		this(path, null);
	}

	public ClassPathResource(String path, @Nullable ClassLoader classLoader) {


		Assert.notNull(path, "Path must not be null");
		String pathToUse = StringUtils.cleanPath(path);
		if (pathToUse.startsWith("/")){
			pathToUse = pathToUse.substring(1);
		}
		this.path = pathToUse;
		this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
		this.clazz.getResourceAsStream(this.path);
	}


}

package org.rafael.util.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * 普通对象调用spring中的bean
 * @author Rafael
 *
 */
public class SpringContextUtil implements ApplicationContextAware,DisposableBean {
	private static Logger logger = LoggerFactory.getLogger(SpringContextUtil.class);
	private static ApplicationContext applicationContext = null;
	
	public static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    }  
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}
	/**
	 * 检查ApplicationContext不为空.
	 */
	private static void assertContextInjected() {
		if (applicationContext == null) {
			throw new RuntimeException("applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringUtil.");
		}
	}
	@Override
	public void destroy() throws Exception {
		if (logger.isDebugEnabled()){
			logger.debug("清除SpringUtil中的ApplicationContext:" + applicationContext);
		}
		applicationContext = null;
	}
	public static <T> T getBean(Class<T> name){
		assertContextInjected();
		return applicationContext.getBean(name);
	}
	public static <T> T getBean(String name){
		assertContextInjected();
		return (T) applicationContext.getBean(name);
	}

}

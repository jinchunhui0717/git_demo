package org.rafael.util.log;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 为日志文件的根目录设置环境变量
 * 
 * @author Rafael
 *
 */
public class Log4jListener implements ServletContextListener {
	public static final String log4jdirkey = "log4jdir";

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String log4jdir = sce.getServletContext().getRealPath("/");
		System.setProperty(log4jdirkey, log4jdir);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.getProperties().remove(log4jdirkey);
	}

}

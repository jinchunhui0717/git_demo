package org.rafael.modules.util.mvcbase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rafael.util.json.JsonMessage;
import org.rafael.util.json.JsonMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制器基础类
 * 
 * @author Rafael
 *
 */
public abstract class BaseController {
	/**
	 * 日志
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	

	public BaseController() {
	}

	/** 基于@ExceptionHandler异常处理 -统一异常处理 返回异常数据-一般用于json ajax返回 */
	@ExceptionHandler
	@ResponseBody
	public JsonMessage handleAndReturnData(HttpServletRequest request,
			HttpServletResponse response, Exception ex) {
		logger.error(ex.getMessage(),ex);
		return JsonMessageUtil.customFailureMsg(ex.getMessage());
	}
}

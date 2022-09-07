package org.rafael.util.i18n;

import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.util.WebUtils;

public class MessageI18n {
	public static String getTextValue(String key) {  
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		RequestContext requestContext = new RequestContext(request);
        return requestContext.getMessage(key);
    } 
	
	public static Locale getSessionLocale() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		RequestContext requestContext = new RequestContext(request);
        return requestContext.getLocale();
    } 
}

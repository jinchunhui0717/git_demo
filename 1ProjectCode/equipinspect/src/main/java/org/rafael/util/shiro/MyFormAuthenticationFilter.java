package org.rafael.util.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.rafael.modules.util.business.UserUtil;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		WebUtils.getAndClearSavedRequest(request);//清除保存的url，跳转到固定的url
		//是否手机端 标识 加入缓存，这样可以屏蔽掉浏览器f12模拟手机端的user-agent
		String mobileflag = request.getParameter("mobileflag");
		if (mobileflag.equals("yes")) {
			UserUtil.putCache(UserUtil.CACHE_MOBILE_FLAG, "yes");
		}
		
		return super.onLoginSuccess(token, subject, request, response);
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) {
		//解决 shiro当用户不先注销登陆而直接再次登陆 的问题
		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				//之前登陆的用户
				Subject subject = SecurityUtils.getSubject();
				Object oldAccount = subject.getPrincipal();
				//本次登录的用户
				String newAccount = this.getUsername(request);
				if (oldAccount!=null && newAccount.equals(oldAccount)) {
					subject.logout();
				}
			}
		}
		return super.isAccessAllowed(request, response, mappedValue);
	}
	
}

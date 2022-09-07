package org.rafael.modules.sys.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.rafael.modules.util.mvcbase.BaseController;
import org.rafael.util.i18n.MessageI18n;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/login")
public class LoginController extends BaseController {
	
	/**
	 * 这个方法是在登录失败的情况下才触发，真正的登陆验证是shiro中进行。
	 * @param req
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping
	public String login(HttpServletRequest req,HttpServletResponse resp, Model model) throws ServletException, IOException{
		String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = MessageI18n.getTextValue("msg_account_password_error");
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = MessageI18n.getTextValue("msg_account_password_error");
        }else if(LockedAccountException.class.getName().equals(exceptionClassName)) {
            error = MessageI18n.getTextValue("msg_account_locked");
        } else if(exceptionClassName != null) {
            error = MessageI18n.getTextValue("msg_other_error") + exceptionClassName;
        }
        model.addAttribute("error", error);
        return "login";
	}
}
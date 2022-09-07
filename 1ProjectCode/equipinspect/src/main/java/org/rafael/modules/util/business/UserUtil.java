package org.rafael.modules.util.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.rafael.modules.sys.dao.SysMenuDao;
import org.rafael.modules.sys.dao.SysUserDao;
import org.rafael.modules.sys.entity.SysMenu;
import org.rafael.modules.sys.entity.SysMenuExample;
import org.rafael.modules.sys.entity.SysUser;
import org.rafael.util.i18n.MessageI18n;
import org.rafael.util.spring.SpringContextUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 将常用的当前用户相关信息缓存下来以方便获取-这里的缓存用的是shiro session的attribute的方式
 * @author Rafael
 *
 */
public class UserUtil {
	private static SysUserDao sysUserDao = SpringContextUtil.getBean(SysUserDao.class);
	private static SysMenuDao sysMenuDao = SpringContextUtil.getBean(SysMenuDao.class);
	public static final String CACHE_MENU_LIST = "menuList";
	public static final String CACHE_SYS_USER = "sys_user";
	public static final String CACHE_MOBILE_FLAG = "mobileflag";

	public static boolean isMobileDevice(){
	     Object object = getCache(CACHE_MOBILE_FLAG);	
		if ( object!=null &&object.toString().equals("yes")) {
			return true;
		}else {
			return false;
		}
	}
	
	
	/**
	 * 获取当前用户授权菜单
	 * @return
	 */
	public static List<SysMenu> getMenuList(){
		List<SysMenu> menuList = (List<SysMenu>)getCache(CACHE_MENU_LIST);
		if (menuList == null){
			Subject subject = SecurityUtils.getSubject();
			Object userName = subject.getPrincipal();
			SysUser sysUser = new SysUser();
			if (userName!=null) {
				sysUser.setLoginName(userName.toString());
			}else {
				return null;
			}
			//系统管理员，内置程序，方便开发人员使用
			if (userName.toString().equals("sysadmin")) {
				SysMenuExample sysMenuExample = new SysMenuExample();
				sysMenuExample.setOrderByClause("sort asc");
				menuList = sysMenuDao.selectByExample(sysMenuExample);
			}else{
				menuList = sysMenuDao.selectBySysUser(sysUser);
			}
			
			List<SysMenu> newList = new ArrayList<SysMenu>();
			listOrder(menuList,newList,"");
			menuList = newList;
			//中英文
			Locale locale = MessageI18n.getSessionLocale();
			if (MessageI18n.getSessionLocale().getLanguage().equals(Locale.US.getLanguage())) {
				for (SysMenu sysMenu : newList) {
					sysMenu.setName(sysMenu.getEname());
				}
			}
			
			putCache(CACHE_MENU_LIST, menuList);
		}
		return menuList;
	}
	
	
	/**
	 * 获取当前用户所在页导航
	 * @return
	 */
	public static List<String> getCurNavigation(){
		List<SysMenu> menuList = getMenuList();
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String path = request.getServletPath();
		List<String> result = new ArrayList<String>();
		for (SysMenu sysMenu : menuList) {
			String strNonParams = sysMenu.getHref();
			if (sysMenu.getHref().indexOf("?")>-1) {
				strNonParams = sysMenu.getHref().substring(0, sysMenu.getHref().indexOf("?"));
			}
			if (strNonParams.equals(path)) {
				result.add(sysMenu.getName());
				getParentName(menuList,sysMenu.getParentId(),result);
				break;
			}
		}
		return result;
	}
	
	private static void getParentName(List<SysMenu> menuList,String parentid,List<String> result) {
		for (SysMenu sysMenu : menuList) {
			if (sysMenu.getId().equals(parentid)) {
				result.add(0,sysMenu.getName());
				getParentName(menuList, sysMenu.getParentId(), result);
				break;
			}
		}
	}
	
	
	/**
	 * 获取当前登陆用户名
	 * @return
	 */
	public static String getLoginName() {
		Subject subject = SecurityUtils.getSubject();
		Object userName = subject.getPrincipal();
		return userName.toString();
	}
	
	/**
	 * 获取当前登陆用户sys_user
	 * @return
	 */
	public static SysUser getSysUser() {
		SysUser sysUser = (SysUser)getCache(CACHE_SYS_USER);
		return sysUser;
	}
	
	private static void listOrder(List<SysMenu> list,List<SysMenu> newList,String parentId){
		List<SysMenu> subList = list.stream().filter(sm -> sm.getParentId().equals(parentId)).collect(Collectors.toList());
		if (subList.size()==0) {
			return;
		}
		for (SysMenu sysMenu : subList) {
			newList.add(sysMenu);
			
			listOrder(list,newList,sysMenu.getId());
		}
	}
	
	
	// user session cache 
	public static Session getSession(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null){
				session = subject.getSession();
			}
			if (session != null){
				return session;
			}
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	public static Object getCache(String key) {
		return getCache(key, null);
	}
	
	public static Object getCache(String key, Object defaultValue) {
		Object obj = getSession().getAttribute(key);
		return obj==null?defaultValue:obj;
	}

	public static void putCache(String key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static void removeCache(String key) {
		getSession().removeAttribute(key);
	}
}

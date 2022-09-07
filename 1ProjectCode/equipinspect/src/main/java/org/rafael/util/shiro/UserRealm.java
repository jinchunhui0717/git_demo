package org.rafael.util.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.rafael.modules.sys.entity.SysUser;
import org.rafael.modules.sys.entity.SysUserExample;
import org.rafael.modules.sys.service.SysUserService;
import org.rafael.modules.util.business.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 用户验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String)token.getPrincipal();
		SysUserExample sysUserExample = new SysUserExample();
		sysUserExample.or().andLoginNameEqualTo(username);
        List<SysUser> list = sysUserService.selectByExample(sysUserExample);
        if(list == null || list.size()==0) {
            throw new UnknownAccountException();//没找到帐号
        }
        SysUser user = list.get(0);
        if(user.getLoginLocked()==1) {
            throw new LockedAccountException(); //帐号锁定
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getLoginName(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getSalt()),//salt=username+salt
                getName()  //realm name
        );
        //将sysuser加入缓存
        UserUtil.putCache(UserUtil.CACHE_SYS_USER, user);
        return authenticationInfo;
	}
	/**
	 * 用户权限授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		
		String username = (String)principals.getPrimaryPrincipal();
		SysUser sysUser = new SysUser();
		sysUser.setLoginName(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //authorizationInfo.setRoles(sysResourceService.selectBySysUserAsStrings(sysUser));
        //authorizationInfo.setStringPermissions(sysPermissionService.selectBySysUserAsStrings(sysUser));
        return authorizationInfo;
	}
	

	

}

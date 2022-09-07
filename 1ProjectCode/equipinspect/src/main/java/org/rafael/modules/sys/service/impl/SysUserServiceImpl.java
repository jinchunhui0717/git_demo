package org.rafael.modules.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.rafael.modules.sys.dao.SysUserDao;
import org.rafael.modules.sys.dao.SysUserRoleDao;
import org.rafael.modules.sys.entity.SysUser;
import org.rafael.modules.sys.entity.SysUserExample;
import org.rafael.modules.sys.entity.SysUserRole;
import org.rafael.modules.sys.entity.SysUserRoleKey;
import org.rafael.modules.sys.service.SysUserService;
import org.rafael.modules.util.mvcbase.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, SysUserExample, String> implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	@Override
	public List<Map<String, Object>> selectUserAndRole(Map<String,Object> params) {
		return sysUserDao.selectUserAndRole(params);
	}

	@Override
	public int deleteUserRoleById(SysUserRoleKey entity) {
		return sysUserRoleDao.deleteByPrimaryKey(entity);
	}

	@Override
	public int insertSelective(SysUserRole sysUserRole) {
		return sysUserRoleDao.insertSelective(sysUserRole);
	}

	@Override
	public int selectCountUserAndRole(Map<String, Object> params) {
		return sysUserDao.selectCountUserAndRole(params);
	}
}
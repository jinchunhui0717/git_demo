package org.rafael.modules.sys.service.impl;

import org.rafael.modules.sys.entity.SysRole;
import org.rafael.modules.sys.entity.SysRoleExample;
import org.rafael.modules.sys.service.SysRoleService;
import org.rafael.modules.util.mvcbase.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, SysRoleExample, String> implements SysRoleService {
}
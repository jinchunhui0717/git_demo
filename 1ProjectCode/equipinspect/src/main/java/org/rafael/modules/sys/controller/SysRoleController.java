package org.rafael.modules.sys.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.rafael.modules.sys.entity.SysMenu;
import org.rafael.modules.sys.entity.SysMenuExample;
import org.rafael.modules.sys.entity.SysRole;
import org.rafael.modules.sys.entity.SysRoleExample;
import org.rafael.modules.sys.entity.SysRoleMenu;
import org.rafael.modules.sys.entity.SysRoleMenuExample;
import org.rafael.modules.sys.entity.SysUser;
import org.rafael.modules.sys.entity.SysUserRole;
import org.rafael.modules.sys.entity.SysUserRoleKey;
import org.rafael.modules.sys.service.SysMenuService;
import org.rafael.modules.sys.service.SysRoleMenuService;
import org.rafael.modules.sys.service.SysRoleService;
import org.rafael.modules.sys.service.SysUserService;
import org.rafael.modules.util.mvcbase.BaseController;
import org.rafael.util.entity.BootTablePageEntity;
import org.rafael.util.json.JsonMessage;
import org.rafael.util.json.JsonMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Controller
@RequestMapping(value="/sys/role")
public class SysRoleController extends BaseController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	@RequestMapping(value="list")
	public String list(){
		return "modules/sys/roleList";
	}
	@RequestMapping(value="listdata")
	@ResponseBody
	public BootTablePageEntity<SysRole> listdata(HttpServletRequest req, HttpServletResponse resp,Model model) {
		int offset = 0;
		int limit = 0;
		Page page = null;
		if (StringUtils.isNotEmpty(req.getParameter("offset"))) {
			offset = Integer.parseInt(req.getParameter("offset").toString());
			limit = Integer.parseInt(req.getParameter("limit").toString());
			page = PageHelper.offsetPage(offset, limit);
		};
		
		List<SysRole> list = sysRoleService.selectByExample(null);
		BootTablePageEntity<SysRole> bootTablePageEntity = new BootTablePageEntity<SysRole>();
		bootTablePageEntity.setRows(list);
		bootTablePageEntity.setTotal(page.getTotal());

		return bootTablePageEntity;
	}
	@RequestMapping(value="add")
	public String add(Model model){
		
		SysMenuExample example=new SysMenuExample();
		example.or().andParentIdNotEqualTo("");
		List<SysMenu> list = sysMenuService.selectByExample(example);
		model.addAttribute("sysmenu", list);
		return "modules/sys/roleAdd";
	}
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public JsonMessage add(@ModelAttribute SysRole sysRole) throws ServletException, IOException{
		if (sysRole==null || StringUtils.isBlank(sysRole.getName())) {
			return JsonMessageUtil.customFailureMsg("角色不能为空");
		}
		SysRoleExample example=new SysRoleExample();
		example.or().andNameEqualTo(sysRole.getName());
		List<SysRole> list = sysRoleService.selectByExample(example);
		if (list.size()>0) {
			return JsonMessageUtil.customFailureMsg("该角色已存在");
		}
		sysRole.setId(UUID.randomUUID().toString());
		sysRoleService.insertSelective(sysRole);
		return JsonMessageUtil.successMsg();
	}
	@RequestMapping(value="edit")
	public String edit(String id,Model model){
		SysRole sysRole = sysRoleService.selectByPrimaryKey(id);
		model.addAttribute("sysRole", sysRole);
		SysMenuExample example=new SysMenuExample();
		example.or().andParentIdNotEqualTo("");
		List<SysMenu> list = sysMenuService.selectByExample(example);
		model.addAttribute("sysmenu", list);
		
		return "modules/sys/roleEdit";
	}
	
	@RequestMapping(value="edit",method=RequestMethod.POST)
	@ResponseBody
	public JsonMessage edit(@ModelAttribute SysRole sysRole) throws ServletException, IOException{
		sysRoleService.updateByPrimaryKeySelective(sysRole);	
		return JsonMessageUtil.successMsg();
	}
	
	@RequestMapping(value="delete",method=RequestMethod.GET)
	@ResponseBody
	public JsonMessage delete(String id){
		if (StringUtils.isBlank(id)) {
			return JsonMessageUtil.customFailureMsg("id不能为空");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("login_locked", "0");
		map.put("role_id", id);
		List<Map<String, Object>> list = sysUserService.selectUserAndRole(map);
		if (list.size()>0) {
			return JsonMessageUtil.customFailureMsg("该角色包含有 未禁用的账户,无法删除");
		}
		sysRoleService.deleteByPrimaryKey(id);
		return JsonMessageUtil.successMsg();
	}
	
	@RequestMapping(value="menudata")
	@ResponseBody
	public List<SysRoleMenu> menudata(String roleId) {
		SysRoleMenuExample example = new SysRoleMenuExample();
		example.or().andRoleIdEqualTo(roleId);
		return sysRoleMenuService.selectByExample(example );
	}
	@Transactional
	@RequestMapping(value="menudata",method=RequestMethod.POST)
	@ResponseBody
	public JsonMessage menudata(@RequestBody List<SysRoleMenu> list,String roleid){
		if (StringUtils.isBlank(roleid)) {
			return JsonMessageUtil.customFailureMsg("roleid不能为空");
		}
		SysRoleMenuExample example = new SysRoleMenuExample();
		example.or().andRoleIdEqualTo(roleid);
		sysRoleMenuService.deleteByExample(example );
		
		for (SysRoleMenu sysRoleMenu : list) {
			sysRoleMenu.setRoleId(roleid);
			sysRoleMenuService.insertSelective(sysRoleMenu);
		}
		
		return JsonMessageUtil.successMsg();
	}
}

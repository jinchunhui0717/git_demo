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
import org.rafael.modules.alg.entity.AlgTaskDetail;
import org.rafael.modules.alg.entity.AlgTaskDetailExample;
import org.rafael.modules.alg.service.AlgTaskDetailService;
import org.rafael.modules.sys.entity.SysUser;
import org.rafael.modules.sys.entity.SysUserExample;
import org.rafael.modules.sys.entity.SysUserRole;
import org.rafael.modules.sys.entity.SysUserRoleKey;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Controller
@RequestMapping(value="/sys/user")
public class SysUserController extends BaseController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private AlgTaskDetailService algTaskDetailService;
	@RequestMapping(value="list")
	public String list(Model model){
		model.addAttribute("sysrole", sysRoleService.selectByExample(null));
		return "modules/sys/userList";
	}
	@RequestMapping(value="edit")
	public String edit(String id,Model model){
		if (StringUtils.isBlank(id)) {
			return "";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		List<Map<String, Object>> list = sysUserService.selectUserAndRole(map);
		if (list.size()>0) {
			model.addAttribute("sysUser", list.get(0));
		}
		model.addAttribute("sysRole", sysRoleService.selectByExample(null)); 
		
				
		return "modules/sys/userEdit";
	}
	@RequestMapping(value="changepwd")
	public String changePwd(String id,Model model){
		if (StringUtils.isBlank(id)) {
			return "";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		List<Map<String, Object>> list = sysUserService.selectUserAndRole(map);
		if (list.size()>0) {
			model.addAttribute("sysUser", list.get(0));
		}
		//model.addAttribute("sysRole", sysRoleService.selectByExample(null)); 
		
				
		return "modules/sys/userChangePwd";
	}
	
	@Transactional
	@RequestMapping(value="changepwd",method=RequestMethod.POST)
	@ResponseBody
	public JsonMessage changePwd(@ModelAttribute SysUser sysUser) throws ServletException, IOException{
		sysUserService.updateByPrimaryKeySelective(sysUser);		
		return JsonMessageUtil.successMsg();
	}
	
	@RequestMapping(value="add")
	public String add(Model model){
		model.addAttribute("sysRole", sysRoleService.selectByExample(null)); 
		return "modules/sys/userAdd";
	}
	
	
	@Transactional
	@RequestMapping(value="edit",method=RequestMethod.POST)
	@ResponseBody
	public JsonMessage edit(@ModelAttribute SysUser sysUser,String oldroleid,String newroleid) throws ServletException, IOException{
		//newroleid是新角色，oldroleid是原角色
		sysUserService.updateByPrimaryKeySelective(sysUser);
		if (!oldroleid.equals(newroleid)) {
			SysUserRoleKey entity = new SysUserRoleKey();
			entity.setRoleId(oldroleid);
			entity.setUserId(sysUser.getId());
			sysUserService.deleteUserRoleById(entity);
			
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setUserId(sysUser.getId());
			sysUserRole.setRoleId(newroleid);
			sysUserService.insertSelective(sysUserRole);
		}
		
		return JsonMessageUtil.successMsg();
	}
	
	@Transactional
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public JsonMessage add(@ModelAttribute SysUser sysUser,String newroleid) throws ServletException, IOException{
		if (sysUser==null||StringUtils.isBlank(sysUser.getLoginName())) {
			return JsonMessageUtil.customFailureMsg("账号不能为空");
		}
		SysUserExample example=new SysUserExample();
		example.or().andLoginNameEqualTo(sysUser.getLoginName());
		List<SysUser> list = sysUserService.selectByExample(example);
		if (list.size()>0) {
			return JsonMessageUtil.customFailureMsg("该账号已存在");
		}
		//newroleid是新角色
		sysUser.setId(UUID.randomUUID().toString());
		sysUser.setSalt("rafael");
		sysUserService.insertSelective(sysUser);
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setUserId(sysUser.getId());
		sysUserRole.setRoleId(newroleid);
		sysUserService.insertSelective(sysUserRole);
		
		return JsonMessageUtil.successMsg();
	}
	
	@RequestMapping(value="listiframe")
	public String listIframe(){
		return "modules/sys/userListIframe";
	}
	
	@RequestMapping(value="listdata")
	@ResponseBody
	public BootTablePageEntity<Map<String, Object>> listdata(@RequestParam Map<String, Object> params,Model model) {
		int offset = 0;
		int limit = 0;
		Page page = null;
		if (params.get("offset")!=null) {
			offset = Integer.parseInt(params.get("offset").toString());
			limit = Integer.parseInt(params.get("limit").toString());
			page = PageHelper.offsetPage(offset, limit);
		}
		List<Map<String, Object>> list = sysUserService.selectUserAndRole(params);		
		BootTablePageEntity<Map<String, Object>> bootTablePageEntity = new BootTablePageEntity<Map<String, Object>>();
		bootTablePageEntity.setRows(list);
		bootTablePageEntity.setTotal(page.getTotal());
		
		
		
		return bootTablePageEntity;
	}
	
	@RequestMapping(value="delete",method=RequestMethod.GET)
	@ResponseBody
	public JsonMessage delete(String id){
		if (StringUtils.isBlank(id)) {
			return JsonMessageUtil.customFailureMsg("id不能为空");
		}
		SysUser sysUser =  sysUserService.selectByPrimaryKey(id);
		if (sysUser==null) {
			return JsonMessageUtil.customFailureMsg("用户不存在");
		}
		AlgTaskDetailExample example=new AlgTaskDetailExample();
		example.or().andOperatorEqualTo(sysUser.getLoginName());
		List<AlgTaskDetail> list = algTaskDetailService.selectByExample(example);
		if (list.size()>0) {
			return JsonMessageUtil.customFailureMsg("已经有点检记录,无法删除");
		}
		sysUserService.deleteByPrimaryKey(id);
		return JsonMessageUtil.successMsg();
	}
}

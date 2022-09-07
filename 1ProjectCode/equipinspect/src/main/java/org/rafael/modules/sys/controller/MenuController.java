package org.rafael.modules.sys.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.rafael.modules.sys.entity.SysMenu;
import org.rafael.modules.sys.entity.SysMenuExample;
import org.rafael.modules.sys.service.SysMenuService;
import org.rafael.modules.util.business.UserUtil;
import org.rafael.modules.util.mvcbase.BaseController;
import org.rafael.util.json.JsonMessage;
import org.rafael.util.json.JsonMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping(value="/sys/menu/")
public class MenuController extends BaseController{
	@Autowired
	private SysMenuService sysMenuService;
	
	@RequestMapping(value="list")
	public String list(){
		return "modules/sys/menuList";
	}
	
	@RequestMapping(value="listdata")
	@ResponseBody
	public List<SysMenu> listData(HttpServletRequest req, HttpServletResponse resp) {
		
		SysMenuExample sysMenuExample = new SysMenuExample();
		sysMenuExample.setOrderByClause("sort");
		if (!UserUtil.getLoginName().equals("sysadmin")) {
			sysMenuExample.or().andIsShowEqualTo("1");
		}
		return sysMenuService.selectByExample(sysMenuExample);
	}
	
	@RequestMapping(value="adddata",method=RequestMethod.POST)
	@ResponseBody
	public JsonMessage addData(@RequestBody SysMenu sysMenu) {
		if (sysMenu==null) {
			return JsonMessageUtil.noDataMsg();
		}
		sysMenu.setCreateBy(UserUtil.getLoginName());
		sysMenu.setId(UUID.randomUUID().toString());
		sysMenu.setSort(1L);
		
		SysMenuExample sysMenuExample = new SysMenuExample();
		Page page = PageHelper.offsetPage(0, 1,false);
		sysMenuExample.setOrderByClause("sort desc");
		sysMenuExample.or().andParentIdEqualTo(sysMenu.getParentId());
		List<SysMenu> list =  sysMenuService.selectByExample(sysMenuExample);
		if (list.size()>0) {
			sysMenu.setSort(list.get(0).getSort()+1);
		}

		sysMenu.setUpdateBy(UserUtil.getLoginName());
		sysMenuService.insertSelective(sysMenu);
		JsonMessage jsonMessage = JsonMessageUtil.successMsg();
		jsonMessage.setExtend(sysMenu.getId());
		return jsonMessage;
	}
	
	@RequestMapping(value="delete")
	@ResponseBody
	public JsonMessage delete(String id) {
		if (StringUtils.isBlank(id)) {
			return JsonMessageUtil.noDataMsg();
		}
		
		SysMenuExample sysMenuExample = new SysMenuExample();
		sysMenuExample.or().andIdEqualTo(id);
		sysMenuExample.or().andParentIdEqualTo(id);
		sysMenuService.deleteByExample(sysMenuExample);
		return JsonMessageUtil.successMsg();
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@ResponseBody
	public JsonMessage update(@RequestBody SysMenu sysMenu) {
		if (StringUtils.isBlank(sysMenu.getId())) {
			return JsonMessageUtil.customFailureMsg("id不能为空");
		}
		sysMenuService.updateByPrimaryKeySelective(sysMenu);
		return JsonMessageUtil.successMsg();
	}
}

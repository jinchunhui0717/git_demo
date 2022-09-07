package org.rafael.modules.sys.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.rafael.modules.sys.service.SysUserService;
import org.rafael.modules.util.business.UserUtil;
import org.rafael.modules.util.mvcbase.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController{
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping({"/","/index"})
    public String index(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
		/*if (UserUtil.isMobileDevice()) {
			req.getRequestDispatcher("/alg/equip/inspect").forward(req, resp);
		}else {
			req.getRequestDispatcher("/alg/equip/monitor?posRegion=D2-2").forward(req, resp);
		}*/
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", UserUtil.getSysUser().getId());
		List<Map<String, Object>> list = sysUserService.selectUserAndRole(map);
		if (list.size()>0&&list.get(0).get("indexurl")!=null&&StringUtils.isNotBlank(list.get(0).get("indexurl").toString())) {
			req.getRequestDispatcher(list.get(0).get("indexurl").toString()).forward(req, resp);
		}
		
        return "index";
    }
}

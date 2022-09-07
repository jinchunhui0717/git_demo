package org.rafael.modules.alg.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.rafael.modules.alg.entity.AlgEquip;
import org.rafael.modules.alg.entity.AlgEquipExample;
import org.rafael.modules.alg.entity.AlgEquipOffice;
import org.rafael.modules.alg.entity.AlgEquipOfficeExample;
import org.rafael.modules.alg.service.AlgEquipOfficeService;
import org.rafael.modules.alg.service.AlgEquipRegionService;
import org.rafael.modules.alg.service.AlgEquipService;
import org.rafael.modules.util.business.UserUtil;
import org.rafael.modules.util.mvcbase.BaseController;
import org.rafael.util.entity.BootTablePageEntity;
import org.rafael.util.json.JsonMessage;
import org.rafael.util.json.JsonMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Controller
@RequestMapping(value="/alg/equipoffice/")
public class EquipOfficeController extends BaseController {
	@Autowired
	private AlgEquipOfficeService algEquipOfficeService;
	@Autowired
	private AlgEquipRegionService algEquipRegionService;
	@Autowired
	private AlgEquipService algEquipService;
	@RequestMapping(value="list")
	public String list(Model model){
		model.addAttribute("algEquipRegion", algEquipRegionService.selectByExample(null));
		return "modules/alg/equipofficeList";
	}
	
	@RequestMapping(value="add")
	public String add(Model model){
		AlgEquipOffice algEquipOffice = new AlgEquipOffice();
		algEquipOffice.setId(UUID.randomUUID().toString());
		model.addAttribute("algEquipOffice", algEquipOffice);
		model.addAttribute("algEquipRegion", algEquipRegionService.selectByExample(null));
		return "modules/alg/equipofficeAdd";
	}
	
	@RequestMapping(value="edit")
	public String edit(String id,Model model){
		//TODO:rafael-错误统一跳转页面，还没做
		/*if (StringUtils.isBlank(id)) {
			return JsonMessageUtil.customFailureMsg("id不能为空");
		}*/
		model.addAttribute("algEquipOffice", algEquipOfficeService.selectByPrimaryKey(id));
		model.addAttribute("algEquipRegion", algEquipRegionService.selectByExample(null));
		return "modules/alg/equipofficeEdit";
	}
	
	@RequestMapping(value="listdata",method=RequestMethod.GET)
	@ResponseBody
	public BootTablePageEntity<Map<String, Object>> listdata(@RequestParam Map<String, Object> map) {
		int offset = 0;
		int limit = 0;
		Page page = null;
		if (map.get("offset")!=null) {
			offset = Integer.parseInt(map.get("offset").toString());
			limit = Integer.parseInt(map.get("limit").toString());
			page = PageHelper.offsetPage(offset, limit);
		}
		List<Map<String, Object>> list = algEquipOfficeService.selectWithRegion(map);
		BootTablePageEntity<Map<String, Object>> bootTablePageEntity = new BootTablePageEntity<Map<String, Object>>();
		bootTablePageEntity.setRows(list);
		bootTablePageEntity.setTotal(page.getTotal());
		return bootTablePageEntity;
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public JsonMessage add(@ModelAttribute AlgEquipOffice algEquipOffice){
		if (algEquipOffice==null || StringUtils.isBlank(algEquipOffice.getName())) {
			return JsonMessageUtil.customFailureMsg("位置不能为空");
		}
		AlgEquipOfficeExample example=new AlgEquipOfficeExample();
		example.or().andNameEqualTo(algEquipOffice.getName());
		List<AlgEquipOffice>  list = algEquipOfficeService.selectByExample(example);
		if (list.size()>0) {
			return JsonMessageUtil.customFailureMsg("该位置已存在");
		}
		algEquipOfficeService.insertSelective(algEquipOffice);
		return JsonMessageUtil.successMsg();
	}
	
	@RequestMapping(value="edit",method=RequestMethod.POST)
	@ResponseBody
	public JsonMessage edit(@ModelAttribute AlgEquipOffice algEquipOffice){
		if (StringUtils.isBlank(algEquipOffice.getId())) {
			return JsonMessageUtil.customFailureMsg("id不能为空");
		}
		algEquipOffice.setUpdateBy(UserUtil.getLoginName());
		algEquipOfficeService.updateByPrimaryKeySelective(algEquipOffice);
		return JsonMessageUtil.successMsg();
	}
	
	@RequestMapping(value="delete",method=RequestMethod.GET)
	@ResponseBody
	public JsonMessage delete(String id){
		if (StringUtils.isBlank(id)) {
			return JsonMessageUtil.customFailureMsg("id不能为空");
		}
		AlgEquipExample example=new AlgEquipExample();
		example.or().andAlgEquipOfficeIdEqualTo(id);
		List<AlgEquip> list= algEquipService.selectByExample(example);
		if (list.size()>0) {
			return JsonMessageUtil.customFailureMsg("该位置正在使用 无法删除");
		}
		algEquipOfficeService.deleteByPrimaryKey(id);
		return JsonMessageUtil.successMsg();
	}
	
}

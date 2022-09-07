package org.rafael.modules.alg.controller;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.rafael.modules.alg.entity.AlgEquipOffice;
import org.rafael.modules.alg.entity.AlgEquipOfficeExample;
import org.rafael.modules.alg.entity.AlgEquipRegion;
import org.rafael.modules.alg.entity.AlgEquipRegionExample;
import org.rafael.modules.alg.service.AlgEquipOfficeService;
import org.rafael.modules.alg.service.AlgEquipRegionService;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Controller
@RequestMapping(value="/alg/equipregion/")
public class EquipRegionController extends BaseController {
	@Autowired
	private AlgEquipRegionService algEquipRegionService;
	@Autowired
	private AlgEquipOfficeService algEquipOfficeService;
	@RequestMapping(value="list")
	public String list(){
		return "modules/alg/equipregionList";
	}
	
	@RequestMapping(value="add")
	public String add(Model model){
		AlgEquipRegion algEquipRegion = new AlgEquipRegion();
		algEquipRegion.setId(UUID.randomUUID().toString());
		model.addAttribute("algEquipRegion", algEquipRegion);
		return "modules/alg/equipregionAdd";
	}
	
	@RequestMapping(value="edit")
	public String edit(String id,Model model){
		//TODO:rafael-错误统一跳转页面，还没做
		/*if (StringUtils.isBlank(id)) {
			return JsonMessageUtil.customFailureMsg("id不能为空");
		}*/
		model.addAttribute("algEquipRegion", algEquipRegionService.selectByPrimaryKey(id));
		return "modules/alg/equipregionEdit";
	}
	
	@RequestMapping(value="listdata",method=RequestMethod.GET)
	@ResponseBody
	public BootTablePageEntity<AlgEquipRegion> listdata(int offset,int limit,String ename) {
		AlgEquipRegionExample example = new AlgEquipRegionExample();
		if (StringUtils.isNotBlank(ename)) {
			example.or().andEnameEqualTo(ename); 
		}
		Page page = PageHelper.offsetPage(offset, limit);
		example.setOrderByClause("name asc");
		List<AlgEquipRegion> list = algEquipRegionService.selectByExample(example);
		
		BootTablePageEntity<AlgEquipRegion> bootTablePageEntity = new BootTablePageEntity<AlgEquipRegion>();
		bootTablePageEntity.setRows(list);
		bootTablePageEntity.setTotal(page.getTotal());
		return bootTablePageEntity;
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public JsonMessage add(@ModelAttribute AlgEquipRegion algEquipRegion){
		if (algEquipRegion==null || StringUtils.isBlank(algEquipRegion.getName())) {
			return JsonMessageUtil.customFailureMsg("区域不能为空");
		}
		AlgEquipRegionExample example=new AlgEquipRegionExample();
		example.or().andNameEqualTo(algEquipRegion.getName());
		List<AlgEquipRegion> list = algEquipRegionService.selectByExample(example);
		if (list.size()>0) {
			return JsonMessageUtil.customFailureMsg("该区域已存在");
		}
		
		algEquipRegionService.insertSelective(algEquipRegion);
		return JsonMessageUtil.successMsg();
	}
	
	@RequestMapping(value="edit",method=RequestMethod.POST)
	@ResponseBody
	public JsonMessage edit(@ModelAttribute AlgEquipRegion algEquipRegion){
		if (StringUtils.isBlank(algEquipRegion.getId())) {
			return JsonMessageUtil.customFailureMsg("id不能为空");
		}
		algEquipRegion.setUpdateBy(UserUtil.getLoginName());
		algEquipRegionService.updateByPrimaryKeySelective(algEquipRegion);
		return JsonMessageUtil.successMsg();
	}
	
	@RequestMapping(value="delete",method=RequestMethod.GET)
	@ResponseBody
	public JsonMessage delete(String id){
		if (StringUtils.isBlank(id)) {
			return JsonMessageUtil.customFailureMsg("id不能为空");
		}
		AlgEquipOfficeExample example=new AlgEquipOfficeExample();
		example.or().andAlgEquipRegionIdEqualTo(id);
		List<AlgEquipOffice> list = algEquipOfficeService.selectByExample(example);
		if (list.size()>0) {
			return JsonMessageUtil.customFailureMsg("该区域正在使用 无法删除");
		}
		algEquipRegionService.deleteByPrimaryKey(id);
		return JsonMessageUtil.successMsg();
	}
	
}

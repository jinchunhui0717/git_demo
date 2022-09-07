package org.rafael.modules.alg.controller;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.rafael.modules.alg.entity.AlgEquipTypeInspect;
import org.rafael.modules.alg.entity.AlgEquipTypeInspectExample;
import org.rafael.modules.alg.entity.AlgTaskDetail;
import org.rafael.modules.alg.entity.AlgTaskDetailInspect;
import org.rafael.modules.alg.service.AlgEquipTypeInspectService;
import org.rafael.modules.alg.service.AlgEquipTypeService;
import org.rafael.modules.alg.service.AlgTaskDetailInspectService;
import org.rafael.modules.alg.service.AlgTaskDetailService;
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
@RequestMapping(value="/alg/equiptype")
public class EquipTypeController extends BaseController {
	@Autowired
	private AlgEquipTypeInspectService algEquipTypeInspectService;
	@Autowired
	private AlgEquipTypeService algEquipTypeService;
	@Autowired
	private AlgTaskDetailInspectService algTaskDetailInspectService;
	@Autowired
	private AlgTaskDetailService algTaskDetailService;
	@RequestMapping(value="list")
	public String list(Model model){
		model.addAttribute("equipType", algEquipTypeService.selectByExample(null)); 
		return "modules/alg/equipTypeInspectList";
	}
	@RequestMapping(value = "listdata")
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
		List<Map<String, Object>> list = algEquipTypeInspectService.selectWithType(map);
		BootTablePageEntity<Map<String, Object>> bootTablePageEntity = new BootTablePageEntity<Map<String, Object>>();
		bootTablePageEntity.setRows(list);
		bootTablePageEntity.setTotal(page==null?list.size():page.getTotal());
		return bootTablePageEntity;
	}
	@RequestMapping(value="add")
	public String add(Model model){
		model.addAttribute("equipType", algEquipTypeService.selectByExample(null)); 
		return "modules/alg/equipTypeInspectAdd";
	}
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public JsonMessage add(@ModelAttribute AlgEquipTypeInspect algEquipTypeInspect) throws ServletException, IOException{

		algEquipTypeInspect.setId(UUID.randomUUID().toString());
		algEquipTypeInspectService.insertSelective(algEquipTypeInspect);
		
		return JsonMessageUtil.successMsg();
	}
	@RequestMapping(value="edit")
	public String edit(String id,Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		List<Map<String, Object>>  list = algEquipTypeInspectService.selectWithType(map);
		if (list.size()>0) {
			model.addAttribute("algEquipTypeInspect", list.get(0));
		}
		model.addAttribute("equipType", algEquipTypeService.selectByExample(null)); 
		
				
		return "modules/alg/equipTypeInspectEdit";
	}
	@RequestMapping(value="edit",method=RequestMethod.POST)
	@ResponseBody
	public JsonMessage edit(@ModelAttribute AlgEquipTypeInspect algEquipTypeInspect) throws ServletException, IOException{
		algEquipTypeInspectService.updateByPrimaryKeySelective(algEquipTypeInspect);
		return JsonMessageUtil.successMsg();
	}
	@RequestMapping(value="delete",method=RequestMethod.GET)
	@ResponseBody
	public JsonMessage delete(String id){
		if (StringUtils.isBlank(id)) {
			return JsonMessageUtil.customFailureMsg("id不能为空");
		}
		algEquipTypeInspectService.deleteByPrimaryKey(id);
		return JsonMessageUtil.successMsg();
	}
	/**
	 * 扫码界面
	 * @return
	 */
	@RequestMapping(value="scanner")
	public String scanner(Model model,String algequiptypeid,String taskdetailid){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("alg_equip_type_id", algequiptypeid);
		List<Map<String, Object>> list = algEquipTypeInspectService.selectWithType(map);
		model.addAttribute("alginspect", list);
		model.addAttribute("taskdetailid", taskdetailid);
		return "modules/alg/equipTypeInspectScanner";
	}
	
	@RequestMapping(value="scannerdata")
	@ResponseBody
	public List<Map<String, Object>> scannerdata(Model model,String algequiptypeid,String taskdetailid){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("alg_equip_type_id", algequiptypeid);
		List<Map<String, Object>> list = algEquipTypeInspectService.selectWithType(map);
		return list;
	}
	
	@RequestMapping(value = "scanner",method=RequestMethod.POST)
	@ResponseBody
	public JsonMessage scanner(@RequestParam Map<String, Object> map,String taskdetailid) {
		
		AlgEquipTypeInspectExample example = new AlgEquipTypeInspectExample();
		example.or().andIdIn(new ArrayList<String>(map.keySet()));
		List<AlgEquipTypeInspect> list = algEquipTypeInspectService
				.selectByExample(example);
		for (String key : map.keySet()) {

			for (AlgEquipTypeInspect algEquipTypeInspect : list) {
				if (key.equals(algEquipTypeInspect.getId())) {
					AlgTaskDetailInspect algTaskDetailInspect = new AlgTaskDetailInspect();
					algTaskDetailInspect.setId(UUID.randomUUID().toString());
					algTaskDetailInspect.setName(algEquipTypeInspect.getName());
					algTaskDetailInspect.setTaskDetailId(taskdetailid);
					algTaskDetailInspect.setType(algEquipTypeInspect.getType());
					algTaskDetailInspect.setValue(map.get(key).toString());
					algTaskDetailInspectService.insertSelective(algTaskDetailInspect);
					break;
				}
			}
		}
		AlgTaskDetail algtaskdetail = new AlgTaskDetail();
		algtaskdetail.setId(taskdetailid);
		algtaskdetail.setEquipState("g");
		algTaskDetailService.updateByPrimaryKeySelective(algtaskdetail);
		return JsonMessageUtil.successMsg();
	}

}

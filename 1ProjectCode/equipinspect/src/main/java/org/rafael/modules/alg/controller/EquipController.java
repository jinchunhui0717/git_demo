package org.rafael.modules.alg.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.rafael.modules.alg.entity.*;
import org.rafael.modules.alg.entity.extend.AlgEquipExtend;
import org.rafael.modules.alg.service.*;
import org.rafael.modules.sys.controller.FileController;
import org.rafael.modules.util.business.UserUtil;
import org.rafael.modules.util.mvcbase.BaseController;
import org.rafael.util.entity.BootTablePageEntity;
import org.rafael.util.i18n.MessageI18n;
import org.rafael.util.json.JsonMessage;
import org.rafael.util.json.JsonMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Controller
@RequestMapping(value="/alg/equip/")
public class EquipController extends BaseController{
	
	@Autowired
	private AlgEquipService algEquipService;
	@Autowired
	private AlgTaskService algTaskService;
	@Autowired
	private AlgTaskDetailService algTaskDetailService;
	@Autowired
	private AlgEquipTypeService algEquipTypeService;
	@Autowired
	private AlgEquipOfficeService algEquipOfficeService;
	@Autowired
	private AlgTaskDetailInspectService algTaskDetailInspectService;
	@Autowired
	private AlgEquipRegionService algEquipRegionService;
	/**
	 * 设备监控
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value="monitor")
	public String equipMonitor(HttpServletRequest req,HttpServletResponse resp,Model model){
		String posRegion = req.getParameter("posRegion");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pos_region", posRegion);
		
		List<Map<String, Object>>  list = algEquipService.selectWithAll(map);
		
		
		model.addAttribute("equipPos", list);
		model.addAttribute("posRegion", posRegion);
		return "modules/alg/equipMonitor";
	}
	
	@RequestMapping(value="monitordata")
	@ResponseBody
	public List<Map<String, Object>> equipMonitorData(HttpServletRequest req,HttpServletResponse resp,Model model){
		String posRegion = req.getParameter("posRegion");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pos_region", posRegion);
		List<Map<String, Object>>  list = algEquipService.selectWithAll(map);
		
		return list;
	}
	/**
	 * 设备监控配置
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value="monitoredit",method=RequestMethod.GET)
	public String monitoredit(HttpServletRequest req,HttpServletResponse resp,Model model){
		String posRegion = req.getParameter("posRegion");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pos_region", posRegion);
		List<Map<String, Object>>  list = algEquipService.selectWithAll(map);
		
		
		model.addAttribute("equipPos", list);
		model.addAttribute("posRegion", posRegion);
		return "modules/alg/equipMonitorEdit";
	}
	
	/**
	 * 设备可视化巡检点检（暂时不使用这个业务）
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value="check")
	public String equipCheck(HttpServletRequest req,HttpServletResponse resp,Model model){
		String posRegion = req.getParameter("posRegion");
		AlgEquipExample algEquipExample = new AlgEquipExample();
		if (!StringUtils.isBlank(posRegion)) {
			algEquipExample.or().andPosRegionEqualTo(posRegion);
		}
		Page page = PageHelper.offsetPage(0, 1,false);
		
		AlgTaskExample algTaskExample = new AlgTaskExample();
		algTaskExample.setOrderByClause("create_date desc");
		List<AlgTask> lAlgTasks = algTaskService.selectByExample(algTaskExample);
		
		List<AlgEquipExtend> list2 = new ArrayList<AlgEquipExtend>();
		if (lAlgTasks.size()==1) {
			AlgTaskDetailExample algTaskDetailExample = new AlgTaskDetailExample();
			algTaskDetailExample.or().andTaskIdEqualTo(lAlgTasks.get(0).getId()).andOperatorEqualTo(UserUtil.getLoginName());
			List<AlgTaskDetail> lAlgTaskDetails =  algTaskDetailService.selectByExample(algTaskDetailExample);
			if (lAlgTaskDetails.size()>0) {
				List<AlgEquip> list = algEquipService.selectByExample(algEquipExample);
				for (AlgTaskDetail algTaskDetail : lAlgTaskDetails) {
					for (AlgEquip item : list) {
						if (item.getId().equals(algTaskDetail.getEquipId())) {
							AlgEquipExtend extend =JSONObject.parseObject(JSONObject.toJSONString(item), AlgEquipExtend.class);
							extend.setOperator(algTaskDetail.getOperator());
							extend.setEquipState(algTaskDetail.getEquipState());
							list2.add(extend);
							break;
						}
					}
					
				}
			}
			
			
		}
		
		model.addAttribute("equipPos", list2);
		model.addAttribute("posRegion", posRegion);
		
		return "modules/alg/equipCheck";
	}
	/**
	 * 巡检
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(value="inspect")
	public String equipInspect(HttpServletRequest req,HttpServletResponse resp,Model model){
		return "modules/alg/equipInspect";
	}
	
	
	@RequestMapping(value = "inspectdata")
	@ResponseBody
	public BootTablePageEntity<Map<String, Object>> inspectdata(@RequestParam Map<String, Object> map) {

		if (map.containsKey("operator")) {
			
		}else{
			map.put("operator", UserUtil.getLoginName());
		}
		List<Map<String, Object>> list = algEquipService.selectWithAll(map);
		BootTablePageEntity<Map<String, Object>> bootTablePageEntity = new BootTablePageEntity<Map<String, Object>>();
		bootTablePageEntity.setRows(list);
		bootTablePageEntity.setTotal(list.size());
		return bootTablePageEntity;
	}
	
	@Transactional
	@RequestMapping(value = "inspectpost",method=RequestMethod.POST)
	@ResponseBody
	public JsonMessage inspectpost(HttpServletRequest request, @RequestBody JSONObject jsonObject){
		String uploadPath = request.getSession().getServletContext()
				.getRealPath("")
				+ "/upload/";
		String uploadPathThumb = uploadPath+"thumb/";
		String fileName = "";
		JSONArray jsonArray =  jsonObject.getJSONArray("equip");
		JSONArray jsonArray2 =  jsonObject.getJSONArray("type");
		
		Map<String, String> map = new HashMap<String, String>();
		
		
		for (Object object : jsonArray2) {
			JSONObject jsonObject2 = (JSONObject)object;
			String taskdeitailid = jsonObject2.getString("key");
			JSONObject jsonObject3 = jsonObject2.getJSONObject("value");
			AlgTaskDetailInspectExample example = new AlgTaskDetailInspectExample();
			example.or().andTaskDetailIdEqualTo(taskdeitailid);
			algTaskDetailInspectService.deleteByExample(example);
			for (String s : jsonObject3.keySet()) {
				
				AlgTaskDetailInspect algTaskDetailInspect = new AlgTaskDetailInspect();
				algTaskDetailInspect.setTaskDetailId(taskdeitailid);
				algTaskDetailInspect.setName(s);
				algTaskDetailInspect.setValue(jsonObject3.getString(s));
				algTaskDetailInspect.setId(UUID.randomUUID().toString());
				algTaskDetailInspectService.insertSelective(algTaskDetailInspect);
				if (s.equals("是否合格")) {
					map.put(taskdeitailid, jsonObject3.getString(s));
				}
			}
		}
		
		for (Object object : jsonArray) {
			JSONObject jsonObject2 = (JSONObject)object;
			AlgTaskDetail record = new AlgTaskDetail();
			record.setId(jsonObject2.getString("task_detail_id"));
			record.setOptDate(new Date());
			if (jsonObject2.getString("equip_state").equals("uncomitted")) {
				fileName = UUID.randomUUID().toString()+".jpg";
				FileController.GenerateImage(jsonObject2.getString("image"), uploadPath+fileName);
				FileController.GenerateImage(jsonObject2.getString("imageurl"), uploadPathThumb+fileName);
				record.setImageurl(fileName);
				if (map.containsKey(record.getId())) {
					if (map.get(record.getId()).equals("true")) {
						record.setEquipState("g");
					}
					else if (map.get(record.getId()).equals("false")) {
						record.setEquipState("r");
					}
				}
				
			}else {
				continue;
			}
			algTaskDetailService.updateByPrimaryKeySelective(record);
		}
		return JsonMessageUtil.successMsg();
	}
	
	@RequestMapping(value="list")
	public String list(Model model){
		model.addAttribute("equiptype", algEquipTypeService.selectByExample(null));
		model.addAttribute("equipoffice", algEquipOfficeService.selectByExample(null));
		model.addAttribute("equipregion", algEquipRegionService.selectByExample(null));
		return "modules/alg/equipList";
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
		List<Map<String, Object>> list = algEquipService.selectWithAll(map);
		BootTablePageEntity<Map<String, Object>> bootTablePageEntity = new BootTablePageEntity<Map<String, Object>>();
		bootTablePageEntity.setRows(list);
		bootTablePageEntity.setTotal(page.getTotal());
		return bootTablePageEntity;
	}
	
	@RequestMapping(value = "getofficebyregion")
	@ResponseBody
	public List<AlgEquipOffice> getOfficeByRegion(String regionid) {
		AlgEquipOfficeExample example = new AlgEquipOfficeExample();
		example.or().andAlgEquipRegionIdEqualTo(regionid);
		return algEquipOfficeService.selectByExample(example );
		
	}
	
	@RequestMapping(value = "export")
	public void export(HttpServletRequest req,HttpServletResponse response,@RequestParam Map<String, Object> map) throws IOException {
		List<Map<String, Object>> list = algEquipService.selectWithAll(map);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet hssfSheet = hssfWorkbook.createSheet();
		HSSFRow row = hssfSheet.createRow((int) 0);  
        HSSFCellStyle style = hssfWorkbook.createCellStyle();  
        style.setAlignment(HorizontalAlignment.CENTER);  
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        
        HSSFFont font = hssfWorkbook.createFont();  
        font.setFontName("微软雅黑");  
        font.setFontHeightInPoints((short) 12);//设置字体大小   
        //font.setBold(true); 

        style.setFont(font);
        List<String> excelHeader = new ArrayList<String>();
        excelHeader.add("设施编号");
        excelHeader.add("条码");
        excelHeader.add("设备类型");
        excelHeader.add("位置");
        excelHeader.add("区域");
        excelHeader.add("购买日期");
        excelHeader.add("厂商");
        excelHeader.add("维修期限");
        excelHeader.add("合格证编号");
        excelHeader.add("品类");
        excelHeader.add("规格");
        excelHeader.add("备注");
        excelHeader.add("状态");
        for (int i = 0; i < excelHeader.size(); i++) {  
            HSSFCell cell = row.createCell(i);  
            cell.setCellValue(excelHeader.get(i));  
            cell.setCellStyle(style);  
            hssfSheet.autoSizeColumn(i);   
        }  
  
        for (int i = 0; i < list.size(); i++) {  
            row = hssfSheet.createRow(i + 1);      
            row.createCell(0).setCellValue(ObjectUtils.toString(list.get(i).get("code")));  
            row.createCell(1).setCellValue(ObjectUtils.toString(list.get(i).get("barcode"))); 
            row.createCell(2).setCellValue(ObjectUtils.toString(list.get(i).get("equip_type_name"))); 
            row.createCell(3).setCellValue(ObjectUtils.toString(list.get(i).get("office_name"))); 
            row.createCell(4).setCellValue(ObjectUtils.toString(list.get(i).get("region_name")));   
            row.createCell(5).setCellValue(ObjectUtils.toString(list.get(i).get("extend1"))); 
            row.createCell(6).setCellValue(ObjectUtils.toString(list.get(i).get("extend2"))); 
            row.createCell(7).setCellValue(ObjectUtils.toString(list.get(i).get("extend3"))); 
            row.createCell(8).setCellValue(ObjectUtils.toString(list.get(i).get("extend4"))); 
            row.createCell(9).setCellValue(ObjectUtils.toString(list.get(i).get("extend6"))); 
            row.createCell(10).setCellValue(ObjectUtils.toString(list.get(i).get("extend7"))); 
            row.createCell(11).setCellValue(ObjectUtils.toString(list.get(i).get("extend8"))); 
            row.createCell(12).setCellValue(ObjectUtils.toString(list.get(i).get("extend5")).equals("1")?"使用中":"废弃"); 
           
        }  
        
        for (int i = 0; i < excelHeader.size(); i++) {  
            hssfSheet.autoSizeColumn(i);   
        }  
        
        response.setHeader("Content-disposition", "attachment;filename=export.xls");  
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        OutputStream ouputStream = response.getOutputStream();  
        hssfWorkbook.write(ouputStream);  
        ouputStream.flush();  
        ouputStream.close();  
	}
	
	@RequestMapping(value="add")
	public String add(Model model){
		model.addAttribute("equipType", algEquipTypeService.selectByExample(null)); 
		model.addAttribute("equipOfficeType", algEquipOfficeService.selectByExample(null)); 
		
		return "modules/alg/equipAdd";
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public JsonMessage add(@ModelAttribute AlgEquip item){
		item.setCode(item.getCode().trim());
		if (item==null) {
			return JsonMessageUtil.noDataMsg();
		}
		if (StringUtils.isBlank(item.getCode())) {
			return JsonMessageUtil.customFailureMsg(MessageI18n.getTextValue("code")+MessageI18n.getTextValue("msg_not_null"));
		}
		AlgEquipExample example = new AlgEquipExample();
		example.or().andCodeEqualTo(item.getCode());
		List<AlgEquip> list = algEquipService.selectByExample(example);
		if (list.size()>0) {
			return JsonMessageUtil.customFailureMsg(MessageI18n.getTextValue("code")+item.getCode()+MessageI18n.getTextValue("msg_has_exists"));
		}
		item.setId(UUID.randomUUID().toString());
		algEquipService.insertSelective(item);
		JsonMessage jsonMessage =  JsonMessageUtil.successMsg();
		jsonMessage.setExtend(item.getId());
		return jsonMessage;
	}
	
	@RequestMapping(value="edit")
	public String edit(Model model,String id){
		model.addAttribute("equipType", algEquipTypeService.selectByExample(null)); 
		model.addAttribute("equipOfficeType", algEquipOfficeService.selectByExample(null)); 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("extend5", "all");
		List<Map<String, Object>>  list = algEquipService.selectWithAll(map );
		if (list.size()==1) {
			model.addAttribute("equip",list.get(0));
		}else {
			//TODO:跳转到内部异常页
		}
		
		return "modules/alg/equipEdit";
	}
	
	@RequestMapping(value="edit",method=RequestMethod.POST)
	@ResponseBody
	public JsonMessage edit(@ModelAttribute AlgEquip item){
		if (item==null) {
			return JsonMessageUtil.noDataMsg();
		}
		algEquipService.updateByPrimaryKeySelective(item);
		return JsonMessageUtil.successMsg();
	}
	
	@RequestMapping(value="delete",method=RequestMethod.GET)
	@ResponseBody
	public JsonMessage delete(String id){
		if (StringUtils.isBlank(id)) {
			return JsonMessageUtil.noDataMsg();
		}
		AlgTaskDetailExample example=new AlgTaskDetailExample();
		example.or().andEquipIdEqualTo(id);
		List<AlgTaskDetail>  list = algTaskDetailService.selectByExample(example);
		if (list.size()>0) {
			return JsonMessageUtil.customFailureMsg("已经有点检记录的设备信息不允许删除");
		}
		algEquipService.deleteByPrimaryKey(id);
		return JsonMessageUtil.successMsg();
	}
	
	
}

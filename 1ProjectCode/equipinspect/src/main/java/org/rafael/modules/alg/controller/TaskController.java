package org.rafael.modules.alg.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.rafael.modules.alg.entity.AlgEquip;
import org.rafael.modules.alg.entity.AlgEquipExample;
import org.rafael.modules.alg.entity.AlgTask;
import org.rafael.modules.alg.entity.AlgTaskDetail;
import org.rafael.modules.alg.entity.AlgTaskDetailExample;
import org.rafael.modules.alg.entity.AlgTaskDetailInspect;
import org.rafael.modules.alg.entity.AlgTaskDetailInspectExample;
import org.rafael.modules.alg.entity.AlgTaskExample;
import org.rafael.modules.alg.entity.extend.AlgEquipExtend;
import org.rafael.modules.alg.service.AlgEquipOfficeService;
import org.rafael.modules.alg.service.AlgEquipRegionService;
import org.rafael.modules.alg.service.AlgEquipService;
import org.rafael.modules.alg.service.AlgEquipTypeService;
import org.rafael.modules.alg.service.AlgTaskDetailInspectService;
import org.rafael.modules.alg.service.AlgTaskDetailService;
import org.rafael.modules.alg.service.AlgTaskService;
import org.rafael.modules.sys.service.SysUserService;
import org.rafael.modules.util.business.UserUtil;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Controller
@RequestMapping(value="/alg/task/")
public class TaskController extends BaseController {
	@Autowired
	private AlgEquipService algEquipService;
	@Autowired
	private AlgEquipTypeService algEquipTypeService;
	@Autowired
	private AlgTaskService algTaskService;
	@Autowired
	private AlgTaskDetailService algTaskDetailService;
	@Autowired
	private AlgTaskDetailInspectService algTaskDetailInspectService;
	@Autowired
	private AlgEquipRegionService algEquipRegionService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private AlgEquipOfficeService algEquipOfficeService;

	
	@RequestMapping(value="add")
	public String add(HttpServletRequest req,HttpServletResponse resp,Model model){
		return "modules/alg/taskAdd";
	}
	
	@RequestMapping(value="report")
	public String report(HttpServletRequest req,HttpServletResponse resp,Model model){
		return "modules/alg/taskReport";
	}
	
	@RequestMapping(value="add2")
	public String add2(HttpServletRequest req,HttpServletResponse resp,Model model){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("role_name", "巡检人员");
		model.addAttribute("role", sysUserService.selectUserAndRole(params ));
		model.addAttribute("region", algEquipRegionService.selectByExample(null));
		return "modules/alg/taskAdd2";
	}
	
	@RequestMapping(value="edit")
	public String edit(HttpServletRequest req,HttpServletResponse resp,Model model){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("role_name", "巡检人员");
		model.addAttribute("role", sysUserService.selectUserAndRole(params ));
		model.addAttribute("region", algEquipRegionService.selectByExample(null));
		return "modules/alg/taskEdit";
	}
	
	@RequestMapping(value="adddata2")
	@ResponseBody
	public BootTablePageEntity<Map<String, Object>> addData2(@RequestParam Map<String, Object> map) {
		/*int offset = 0;
		int limit = 0;
		Page page = null;
		if (map.get("offset")!=null) {
			offset = Integer.parseInt(map.get("offset").toString());
			limit = Integer.parseInt(map.get("limit").toString());
			page = PageHelper.offsetPage(offset, limit);
		}*/
		List<Map<String, Object>> list = algEquipService.selectWithAll(map);
		BootTablePageEntity<Map<String, Object>> bootTablePageEntity = new BootTablePageEntity<Map<String, Object>>();
		bootTablePageEntity.setRows(list);
		bootTablePageEntity.setTotal(list.size());
		return bootTablePageEntity;
	}
	
	
	@RequestMapping(value="adddata")
	@ResponseBody
	public BootTablePageEntity<Map<String, Object>> addData(@RequestParam Map<String, Object> map) throws ParseException {
		
		List<Map<String, Object>> list = algEquipService.selectWithAll(map);
		
		/*Calendar cal = Calendar.getInstance(); 
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date nowDate = new Date();
		
		for (Map<String, Object> map2 : list) {
			
			if (map2.get("equip_opt_startdate")==null||map2.get("equip_opt_startdate").toString()=="") {
				nowDate.setDate(1);
			}else {
				int startdate = Integer.parseInt(map2.get("equip_opt_startdate").toString());
				if (startdate>lastDay) {
					nowDate.setDate(lastDay);
				}else {
					nowDate.setDate(startdate);
				}
			}
			String date1 = dateFormat.format(nowDate);
			map2.put("startdate", date1);
			
			if (map2.get("equip_opt_enddate")==null||map2.get("equip_opt_enddate").toString()=="") {
				nowDate.setDate(5);
			}else {
				int enddate = Integer.parseInt(map2.get("equip_opt_enddate").toString());
				if (enddate>lastDay) {
					nowDate.setDate(lastDay);
				}else {
					nowDate.setDate(enddate);
				}
			}
			
			String date2 = dateFormat.format(nowDate);
			
			map2.put("enddate", date2);
		}
		*/
		
		
		BootTablePageEntity<Map<String, Object>> bootTablePageEntity = new BootTablePageEntity<Map<String, Object>>();
		bootTablePageEntity.setRows(list);
		bootTablePageEntity.setTotal(list.size());
		return bootTablePageEntity;
	}
	
	
	@RequestMapping(value="editdata")
	@ResponseBody
	public BootTablePageEntity<Map<String, Object>> editdata(@RequestParam Map<String, Object> map) throws ParseException {
		int offset = 0;
		int limit = 0;
		if (map.get("offset")!=null) {
			offset = Integer.parseInt(map.get("offset").toString());
			limit = Integer.parseInt(map.get("limit").toString());
		}
		Page page = PageHelper.offsetPage(offset, limit);
		List<Map<String, Object>> list = algEquipService.selectWithTaskDetail(map);
		
		Calendar cal = Calendar.getInstance(); 
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date nowDate = new Date();
		
		for (Map<String, Object> map2 : list) {
			
			if (map2.get("equip_opt_startdate")==null||map2.get("equip_opt_startdate").toString()=="") {
				nowDate.setDate(1);
			}else {
				int startdate = Integer.parseInt(map2.get("equip_opt_startdate").toString());
				if (startdate>lastDay) {
					nowDate.setDate(lastDay);
				}else {
					nowDate.setDate(startdate);
				}
			}
			String date1 = dateFormat.format(nowDate);
			map2.put("startdate", date1);
			
			if (map2.get("equip_opt_enddate")==null||map2.get("equip_opt_enddate").toString()=="") {
				nowDate.setDate(5);
			}else {
				int enddate = Integer.parseInt(map2.get("equip_opt_enddate").toString());
				if (enddate>lastDay) {
					nowDate.setDate(lastDay);
				}else {
					nowDate.setDate(enddate);
				}
			}
			
			String date2 = dateFormat.format(nowDate);
			
			map2.put("enddate", date2);
		}
		
		
		
		BootTablePageEntity<Map<String, Object>> bootTablePageEntity = new BootTablePageEntity<Map<String, Object>>();
		bootTablePageEntity.setRows(list);
		bootTablePageEntity.setTotal(page.getTotal());
		return bootTablePageEntity;
	}
	
	
	
	
	
	@Transactional
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public JsonMessage add(@RequestBody JSONObject data) throws ParseException{
		if (data==null) {
			return JsonMessageUtil.noDataMsg();
		}
		JSONArray jsonArray = data.getJSONArray("data");
		AlgTask algTask = new AlgTask();
		algTask.setId(UUID.randomUUID().toString());
		algTask.setCreateBy(UserUtil.getLoginName());
		algTask.setUpdateBy(UserUtil.getLoginName());
		List<AlgTaskDetail> listAlgTaskDetails = new ArrayList<AlgTaskDetail>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Object object : jsonArray) {
			JSONObject jsonObject2 = (JSONObject)object;
			AlgTaskDetail algTaskDetail = new AlgTaskDetail();
			algTaskDetail.setId(UUID.randomUUID().toString());
			algTaskDetail.setCreateBy(UserUtil.getLoginName());
			algTaskDetail.setEquipId(jsonObject2.getString("id"));
			algTaskDetail.setOperator(jsonObject2.getString("equip_operator"));
			algTaskDetail.setTaskId(algTask.getId());
			algTaskDetail.setUpdateBy(UserUtil.getLoginName());
			algTaskDetail.setOptStartdate(sdf.parse(jsonObject2.getString("startdate")));
			algTaskDetail.setOptEnddate(sdf.parse(jsonObject2.getString("enddate")));
			listAlgTaskDetails.add(algTaskDetail);
		}
		//algTaskService.delCurMonth();
		//algTaskDetailService.delCurMonth();
		algTaskService.insertSelective(algTask);
		algTaskDetailService.insertBatch(listAlgTaskDetails);
		return JsonMessageUtil.successMsg();
	}
	
	
	@Transactional
	@RequestMapping(value="edit",method=RequestMethod.POST)
	@ResponseBody
	public JsonMessage edit(@RequestBody JSONObject data) throws ParseException{
		if (data==null) {
			return JsonMessageUtil.noDataMsg();
		}
		logger.debug(data.toJSONString());
		JSONArray jsonArray = data.getJSONArray("data");
		//List<AlgTaskDetail> listAlgTaskDetails = new ArrayList<AlgTaskDetail>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JSONArray insertJsonArray = new JSONArray();
		String taskid="";
		for (Object object : jsonArray) {
			JSONObject jsonObject2 = (JSONObject)object;
			AlgTaskDetail algTaskDetail = new AlgTaskDetail();
			if (jsonObject2.getString("task_detail_id")==null ||StringUtils.isBlank(jsonObject2.getString("task_detail_id"))) {
				insertJsonArray.add(object);
				continue;
			}
			if (jsonObject2.getString("task_id")!=null && StringUtils.isNotBlank(jsonObject2.getString("task_id"))) {
				taskid = jsonObject2.getString("task_id");
			}
			algTaskDetail.setId(jsonObject2.getString("task_detail_id"));
			algTaskDetail.setOperator(jsonObject2.getString("equip_operator"));
			algTaskDetail.setUpdateBy(UserUtil.getLoginName());
			algTaskDetail.setOptStartdate(sdf.parse(jsonObject2.getString("opt_startdate")));
			algTaskDetail.setOptEnddate(sdf.parse(jsonObject2.getString("opt_enddate")));
			algTaskDetailService.updateByPrimaryKeySelective(algTaskDetail);
			//listAlgTaskDetails.add(algTaskDetail);
		}
		if (taskid=="") {
			return JsonMessageUtil.customFailureMsg("请先新建任务");
		}
		for (Object object : insertJsonArray) {
			JSONObject jsonObject2 = (JSONObject)object;
			AlgTaskDetail algTaskDetail = new AlgTaskDetail();
			algTaskDetail.setId(UUID.randomUUID().toString());
			algTaskDetail.setOperator(jsonObject2.getString("equip_operator"));
			algTaskDetail.setUpdateBy(UserUtil.getLoginName());
			algTaskDetail.setOptStartdate(sdf.parse(jsonObject2.getString("startdate")));
			algTaskDetail.setOptEnddate(sdf.parse(jsonObject2.getString("enddate")));

			algTaskDetail.setCreateBy(UserUtil.getLoginName());
			algTaskDetail.setEquipId(jsonObject2.getString("id"));
			algTaskDetail.setTaskId(taskid);
			algTaskDetail.setUpdateBy(UserUtil.getLoginName());
			algTaskDetailService.insertSelective(algTaskDetail);
		}
		//algTaskService.delCurMonth();
		//algTaskDetailService.delCurMonth();
		//algTaskService.insertSelective(algTask);
		
		return JsonMessageUtil.successMsg();
	}
	
	@Transactional
	@RequestMapping(value="add2",method=RequestMethod.POST)
	@ResponseBody
	public JsonMessage add2(@RequestBody JSONObject data){
		if (data==null) {
			return JsonMessageUtil.noDataMsg();
		}
		JSONArray jsonArray = data.getJSONArray("data");
		for (Object object : jsonArray) {
			JSONObject jsonObject = (JSONObject)object;
			AlgEquip algEquip = new AlgEquip();
			algEquip.setId(jsonObject.getString("id"));
			algEquip.setEquipOperator(jsonObject.getString("equip_operator")==null?"":jsonObject.getString("equip_operator"));
			algEquip.setEquipOptStartdate(jsonObject.getInteger("equip_opt_startdate"));
			algEquip.setEquipOptEnddate(jsonObject.getInteger("equip_opt_enddate"));
			algEquipService.updateByPrimaryKeySelective(algEquip);
		}
		return JsonMessageUtil.successMsg();
	}
	
	@RequestMapping(value="tasklist")
	public String taskList(HttpServletRequest req,HttpServletResponse resp,Model model){
		return "modules/alg/taskList";
	}
	
	@RequestMapping(value="tasklistdata")
	@ResponseBody
	public BootTablePageEntity<AlgTask> taskListData(HttpServletRequest req, HttpServletResponse resp,Model model) {
		Integer offset =  Integer.parseInt(req.getParameter("offset"));
		Integer limit = Integer.parseInt(req.getParameter("limit"));
		String createdate = req.getParameter("createdate");
		
		AlgTaskExample algTaskExample  = new AlgTaskExample();
		if (StringUtils.isNotBlank(createdate)) {
			int year = Integer.parseInt(createdate);
			
			algTaskExample.or().andCreateDateBetween(getYearFirst(year), getYearLast(year));
		}
		algTaskExample.setOrderByClause("create_date desc");
		
		Page page = PageHelper.offsetPage(offset, limit);
	
		List<AlgTask> list = algTaskService.selectByExample(algTaskExample);
		
		BootTablePageEntity<AlgTask> bootTablePageEntity = new BootTablePageEntity<AlgTask>();
		bootTablePageEntity.setRows(list);
		bootTablePageEntity.setTotal(page.getTotal());
		return bootTablePageEntity;
	}
	public static Date getYearFirst(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}
	public static Date getYearLast(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		
		return currYearLast;
	}
	
	@RequestMapping(value="taskdetaillist")
	public String taskDetailList(HttpServletRequest req,HttpServletResponse resp,Model model){
		String id = req.getParameter("id");
		model.addAttribute("id", id);
		model.addAttribute("equiptype", algEquipTypeService.selectByExample(null));
		model.addAttribute("equipoffice", algEquipOfficeService.selectByExample(null));
		model.addAttribute("equipregion", algEquipRegionService.selectByExample(null));
		return "modules/alg/taskDetailList";
	}
	
	@RequestMapping(value="taskresult")
	public String taskresult(HttpServletRequest req,HttpServletResponse resp,Model model){
		model.addAttribute("equiptype", algEquipTypeService.selectByExample(null));
		model.addAttribute("region", algEquipRegionService.selectByExample(null));
		return "modules/alg/taskResult";
	}
	
	@RequestMapping(value="export")
	public void export(HttpServletRequest req,HttpServletResponse response,@RequestParam Map<String, Object> map) throws IOException{

		List<Map<String, Object>> list = algEquipService.selectWithTaskAll(map);
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
        excelHeader.add("点检周期");
        excelHeader.add("编号");
        excelHeader.add("条码");
        excelHeader.add("设备类型");
        excelHeader.add("位置");
        excelHeader.add("区域");
        excelHeader.add("巡检员");
        excelHeader.add("状态");
        for (int i = 0; i < excelHeader.size(); i++) {  
            HSSFCell cell = row.createCell(i);  
            cell.setCellValue(excelHeader.get(i));  
            cell.setCellStyle(style);  
            hssfSheet.autoSizeColumn(i);   
        }  
  
        for (int i = 0; i < list.size(); i++) {  
            row = hssfSheet.createRow(i + 1);    
            row.createCell(0).setCellValue(ObjectUtils.toString(list.get(i).get("task_create_date")).substring(0,7));  
            row.createCell(1).setCellValue(ObjectUtils.toString(list.get(i).get("code")));  
            row.createCell(2).setCellValue(ObjectUtils.toString(list.get(i).get("barcode"))); 
            row.createCell(3).setCellValue(ObjectUtils.toString(list.get(i).get("equip_type_name"))); 
            row.createCell(4).setCellValue(ObjectUtils.toString(list.get(i).get("office_name"))); 
            row.createCell(5).setCellValue(ObjectUtils.toString(list.get(i).get("region_name")));   
            row.createCell(6).setCellValue(ObjectUtils.toString(list.get(i).get("user_name"))); 
            String state = ObjectUtils.toString(list.get(i).get("equip_state"));
            if (state.equals("g")) {
            	row.createCell(7).setCellValue("巡检合格");
			}else if (state.equals("r")) {
				row.createCell(7).setCellValue("巡检不合格");
			}else {
				row.createCell(7).setCellValue("未巡检");
			}
               
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
	
	
	
	@RequestMapping(value="reportexport")
	public void reportexport(HttpServletRequest req,HttpServletResponse response,@RequestParam Map<String, Object> map) throws IOException{
		String task_year = map.get("task_year").toString();
		List<Map<String, Object>> list = algTaskService.selWanchengRate(map);
		List<Map<String, Object>> listHege = algTaskService.selHegeRate(map);
		String path;
		path = req.getServletContext().getRealPath("/static/file/消防器材点检趋势图.xls");

		FileInputStream fileInputStream = new FileInputStream(path);
		
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fileInputStream);
		fileInputStream.close();
		
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		
		HSSFRow row = hssfSheet.getRow(1);  
		HSSFCell cell = row.getCell(4);
		cell.setCellValue(task_year+"年点检合格LIST");
		
		DecimalFormat df = new DecimalFormat("#.##%");
		int excelposx = 4;
		int excelposy = 3;
		
		HSSFRow rowd11 = hssfSheet.getRow(excelposx); 
		HSSFRow rowd13 = hssfSheet.getRow(excelposx+2); 
		HSSFRow rowd22 = hssfSheet.getRow(excelposx+4); 
		for (int i = 1; i <= 12; i++) {
			for (int j = 0; j < listHege.size(); j++) {
				Map<String, Object> item = listHege.get(j);
				if (item.get("task_month").toString().equals(Integer.toString(i))) {
					if (item.get("pos_region").toString().equals("D1-1")) {
						rowd11.getCell(excelposy+i).setCellValue(Double.parseDouble(item.get("per").toString())/100);
					}
					else if (item.get("pos_region").toString().equals("D1-3")) {
						rowd13.getCell(excelposy+i).setCellValue(Double.parseDouble(item.get("per").toString())/100);
					}
					else if (item.get("pos_region").toString().equals("D2-2")) {
						rowd22.getCell(excelposy+i).setCellValue(Double.parseDouble(item.get("per").toString())/100);
					}
				}
			}
		}
        
		
		HSSFRow row2 = hssfSheet.getRow(41);  
		HSSFCell cell2 = row2.getCell(4);
		cell2.setCellValue(task_year+"年点检完成LIST");
		int excelposx2 = 44;
		
		rowd11 = hssfSheet.getRow(excelposx2); 
		rowd13 = hssfSheet.getRow(excelposx2+2); 
		rowd22 = hssfSheet.getRow(excelposx2+4); 
		for (int i = 1; i <= 12; i++) {
			for (int j = 0; j < list.size(); j++) {
				Map<String, Object> item = list.get(j);
				if (item.get("task_month").toString().equals(Integer.toString(i))) {
					if (item.get("pos_region").toString().equals("D1-1")) {
						rowd11.getCell(excelposy+i).setCellValue(Double.parseDouble(item.get("per").toString())/100);
					}
					else if (item.get("pos_region").toString().equals("D1-3")) {
						rowd13.getCell(excelposy+i).setCellValue(Double.parseDouble(item.get("per").toString())/100);
					}
					else if (item.get("pos_region").toString().equals("D2-2")) {
						rowd22.getCell(excelposy+i).setCellValue(Double.parseDouble(item.get("per").toString())/100);
					}
				}
			}
		}
        
        response.setHeader("Content-disposition", "attachment;filename=export.xls");  
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        OutputStream ouputStream = response.getOutputStream();  
        hssfWorkbook.write(ouputStream);  
        ouputStream.flush();  
        ouputStream.close();  
	}
	
	
	@RequestMapping(value = "taskdetaillistdata")
	@ResponseBody
	public BootTablePageEntity<Map<String, Object>> taskdetaillistdata(@RequestParam Map<String, Object> map) {
		int offset = 0;
		int limit = 0;
		if (map.get("offset")!=null) {
			offset = Integer.parseInt(map.get("offset").toString());
			limit = Integer.parseInt(map.get("limit").toString());
		}
		Page page = PageHelper.offsetPage(offset, limit);
		List<Map<String, Object>> list = algEquipService.selectWithTaskDetail(map);
		BootTablePageEntity<Map<String, Object>> bootTablePageEntity = new BootTablePageEntity<Map<String, Object>>();
		bootTablePageEntity.setRows(list);
		bootTablePageEntity.setTotal(page.getTotal());
		return bootTablePageEntity;
	}
	
	@RequestMapping(value = "taskresultdata")
	@ResponseBody
	public BootTablePageEntity<Map<String, Object>> taskresultdata(@RequestParam Map<String, Object> map) {
		int offset = 0;
		int limit = 0;
		if (map.get("offset")!=null) {
			offset = Integer.parseInt(map.get("offset").toString());
			limit = Integer.parseInt(map.get("limit").toString());
		}
		Page page = PageHelper.offsetPage(offset, limit);
		List<Map<String, Object>> list = algEquipService.selectWithTaskAll(map);
		BootTablePageEntity<Map<String, Object>> bootTablePageEntity = new BootTablePageEntity<Map<String, Object>>();
		bootTablePageEntity.setRows(list);
		bootTablePageEntity.setTotal(page.getTotal());
		return bootTablePageEntity;
	}
	
	@RequestMapping(value = "reporthegedata")
	@ResponseBody
	public List<Map<String, Object>> reporthegedata(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> list = algTaskService.selHegeRate(map);
		return list;
	}
	
	@RequestMapping(value = "reportwanchengedata")
	@ResponseBody
	public List<Map<String, Object>> reportwanchengedata(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> list = algTaskService.selWanchengRate(map);
		return list;
	}

	
	
	@RequestMapping(value="taskdetailupdate",method=RequestMethod.POST)
	@ResponseBody
	public JsonMessage taskdetailupdate(@RequestBody AlgTaskDetail item){
		if (item==null) {
			return JsonMessageUtil.noDataMsg();
		}
		algTaskDetailService.updateByPrimaryKeySelective(item);
		return JsonMessageUtil.successMsg();
	}
	
	@RequestMapping(value="taskdetailinspect")
	public String taskdetailinspect(HttpServletRequest req,HttpServletResponse resp,Model model){
		String id = req.getParameter("id");
		model.addAttribute("id", id);
		return "modules/alg/taskDetailInspect";
	}
	
	
	@RequestMapping(value = "taskdetailinspectdata")
	@ResponseBody
	public BootTablePageEntity<AlgTaskDetailInspect> taskdetailinspectdata(String taskDetailId) {
		AlgTaskDetailInspectExample example = new AlgTaskDetailInspectExample();
		example.or().andTaskDetailIdEqualTo(taskDetailId);
		example.setOrderByClause("name asc");
		List<AlgTaskDetailInspect> list = algTaskDetailInspectService.selectByExample(example );
		BootTablePageEntity<AlgTaskDetailInspect> bootTablePageEntity = new BootTablePageEntity<AlgTaskDetailInspect>();
		bootTablePageEntity.setRows(list);
		bootTablePageEntity.setTotal(list.size());
		return bootTablePageEntity;
	}
	
	
}

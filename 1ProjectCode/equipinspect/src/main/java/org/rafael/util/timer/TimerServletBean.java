package org.rafael.util.timer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.rafael.modules.alg.entity.AlgTask;
import org.rafael.modules.alg.entity.AlgTaskDetail;
import org.rafael.modules.alg.entity.AlgTaskExample;
import org.rafael.modules.alg.service.AlgEquipService;
import org.rafael.modules.alg.service.AlgTaskDetailService;
import org.rafael.modules.alg.service.AlgTaskService;
import org.rafael.modules.sys.controller.FileController;
import org.rafael.util.spring.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Component
public class TimerServletBean{
	@Autowired
	private AlgEquipService algEquipService;
	@Autowired
	private AlgTaskService algTaskService;
	@Autowired
	private AlgTaskDetailService algTaskDetailService;
	@Autowired
	private ServletContext servletContext;
	
	
	public void TimerTask() throws ParseException{
		PlatformTransactionManager platformTransactionManager = (PlatformTransactionManager) SpringContextUtil.getBean("transactionManager");
		DefaultTransactionDefinition def = new DefaultTransactionDefinition(TransactionDefinition.ISOLATION_DEFAULT);
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = platformTransactionManager.getTransaction(def);
		
		try {
			AlgTaskExample example = new AlgTaskExample();
			example.setOrderByClause("create_date desc");
			Page page = PageHelper.offsetPage(0, 1, false);
			List<AlgTask> listAlgTasks = algTaskService.selectByExample(example );
			if (listAlgTasks.size()>0) {
				Date date =  listAlgTasks.get(0).getCreateDate();
				Date nowdate = new Date();
				if(date.getMonth()==nowdate.getMonth()){
					log.debug("发现当月已经有任务，不再写入");
					return;
				}
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> list = algEquipService.selectWithAll(map);
			AlgTask algTask = new AlgTask();
			algTask.setId(UUID.randomUUID().toString());
			algTask.setCreateBy("admin");
			algTask.setUpdateBy("admin");
			algTaskService.insertSelective(algTask);
			
			Calendar cal = Calendar.getInstance(); 
			int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
			
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date nowDate = new Date();
			

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
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
				
				AlgTaskDetail algTaskDetail = new AlgTaskDetail();
				algTaskDetail.setId(UUID.randomUUID().toString());
				algTaskDetail.setCreateBy("admin");
				algTaskDetail.setEquipId(ObjectUtils.defaultIfNull(map2.get("id"), "").toString());
				algTaskDetail.setOperator(ObjectUtils.defaultIfNull(map2.get("equip_operator"), "").toString());
				algTaskDetail.setTaskId(algTask.getId());
				algTaskDetail.setUpdateBy("admin");
				algTaskDetail.setEquipState("");
				algTaskDetail.setOptStartdate(sdf.parse(ObjectUtils.defaultIfNull(map2.get("startdate"), "").toString()));
				algTaskDetail.setOptEnddate(sdf.parse(ObjectUtils.defaultIfNull(map2.get("enddate"), "").toString()));
				algTaskDetailService.insertSelective(algTaskDetail);
			}
			String file = servletContext.getRealPath("")+ "static/manifest/rafael.manifest";
			FileController.modifyFileContent(file,"#VERSION","#VERSION "+System.currentTimeMillis());
			platformTransactionManager.commit(status);
		} catch (Exception e) {
			platformTransactionManager.rollback(status);
			throw e;
		}
		
	}
	
	
	Timer timer = null;
	private Logger log = LoggerFactory.getLogger(TimerServletBean.class);

	@PreDestroy
	public void destroy() {
		timer.cancel();
		log.info("关闭定时任务线程");
	}

	@PostConstruct
	public void init(){
		log.info("启动定时任务线程");
		Long cycleTime = 3600000L;//1小时
		//Long cycleTime = 180000L;//3分钟
		timer = new Timer("timerServlet", false);
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				try {
					log.debug("开始写入task");
					TimerTask();
					log.debug("写入task结束");
				} catch (Exception e) {
					log.error("定时错误"+e.getMessage());
				}

			}
		}, 10000, cycleTime);
	}

}

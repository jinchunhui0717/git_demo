package org.rafael.modules.alg.service.impl;

import org.rafael.modules.alg.dao.AlgTaskDetailDao;
import org.rafael.modules.alg.entity.AlgTaskDetail;
import org.rafael.modules.alg.entity.AlgTaskDetailExample;
import org.rafael.modules.alg.service.AlgTaskDetailService;
import org.rafael.modules.util.mvcbase.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlgTaskDetailServiceImpl extends BaseServiceImpl<AlgTaskDetail, AlgTaskDetailExample, String> implements AlgTaskDetailService {
	@Autowired
	private AlgTaskDetailDao algTaskDetailDao;
	
	public void delCurMonth(){
		algTaskDetailDao.delCurMonth();
	}
}
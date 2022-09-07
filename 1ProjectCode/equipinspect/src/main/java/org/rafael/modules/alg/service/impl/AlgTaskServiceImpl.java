package org.rafael.modules.alg.service.impl;

import java.util.List;
import java.util.Map;

import org.rafael.modules.alg.dao.AlgTaskDao;
import org.rafael.modules.alg.entity.AlgTask;
import org.rafael.modules.alg.entity.AlgTaskExample;
import org.rafael.modules.alg.service.AlgTaskService;
import org.rafael.modules.util.mvcbase.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlgTaskServiceImpl extends BaseServiceImpl<AlgTask, AlgTaskExample, String> implements AlgTaskService {
	@Autowired
	private AlgTaskDao algTaskDao;
	@Override
	public void delCurMonth() {
		algTaskDao.delCurMonth();
	}
	@Override
	public List<Map<String, Object>> selHegeRate(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return algTaskDao.selHegeRate(map);
	}
	@Override
	public List<Map<String, Object>> selWanchengRate(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return algTaskDao.selWanchengRate(map);
	}
	
}
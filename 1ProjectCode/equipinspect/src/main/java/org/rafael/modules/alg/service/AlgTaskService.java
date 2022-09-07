package org.rafael.modules.alg.service;

import java.util.List;
import java.util.Map;

import org.rafael.modules.alg.entity.AlgTask;
import org.rafael.modules.alg.entity.AlgTaskExample;
import org.rafael.modules.util.mvcbase.BaseService;

public interface AlgTaskService extends BaseService<AlgTask, AlgTaskExample, String> {
	public void delCurMonth();
	public List<Map<String, Object>> selHegeRate(Map<String, Object> map);
	public List<Map<String, Object>> selWanchengRate(Map<String, Object> map);
}
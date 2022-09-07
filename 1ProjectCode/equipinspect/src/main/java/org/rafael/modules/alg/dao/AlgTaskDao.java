package org.rafael.modules.alg.dao;

import java.util.List;
import java.util.Map;

import org.rafael.modules.alg.entity.AlgTask;
import org.rafael.modules.alg.entity.AlgTaskExample;
import org.rafael.modules.util.mvcbase.BaseDao;

public interface AlgTaskDao extends BaseDao<AlgTask, AlgTaskExample, String> {
	public void delCurMonth();
	public List<Map<String, Object>> selHegeRate(Map<String, Object> map);
	public List<Map<String, Object>> selWanchengRate(Map<String, Object> map);
}
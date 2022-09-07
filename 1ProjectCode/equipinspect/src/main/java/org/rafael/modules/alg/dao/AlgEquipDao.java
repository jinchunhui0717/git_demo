package org.rafael.modules.alg.dao;

import java.util.List;
import java.util.Map;

import org.rafael.modules.alg.entity.AlgEquip;
import org.rafael.modules.alg.entity.AlgEquipExample;
import org.rafael.modules.util.mvcbase.BaseDao;

public interface AlgEquipDao extends BaseDao<AlgEquip, AlgEquipExample, String> {
	public List<Map<String, Object>> selectWithAll(Map<String, Object> map);
	public int selectCountWithAll(Map<String, Object> map);
	
	public List<Map<String, Object>> selectWithTaskDetail(Map<String, Object> map);
	public int selectCountWithTaskDetail(Map<String, Object> map);
	
	public List<Map<String, Object>> selectWithTaskAll(Map<String, Object> map);
	public int selectCountWithTaskAll(Map<String, Object> map);
	
}
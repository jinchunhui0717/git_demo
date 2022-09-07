package org.rafael.modules.alg.dao;

import java.util.List;
import java.util.Map;

import org.rafael.modules.alg.entity.AlgEquipTypeInspect;
import org.rafael.modules.alg.entity.AlgEquipTypeInspectExample;
import org.rafael.modules.util.mvcbase.BaseDao;

public interface AlgEquipTypeInspectDao extends BaseDao<AlgEquipTypeInspect, AlgEquipTypeInspectExample, String> {
	public List<Map<String, Object>> selectWithType(Map<String, Object> map);
	public int selectCountWithType(Map<String, Object> map);
}
package org.rafael.modules.alg.dao;

import java.util.List;
import java.util.Map;

import org.rafael.modules.alg.entity.AlgEquipOffice;
import org.rafael.modules.alg.entity.AlgEquipOfficeExample;
import org.rafael.modules.util.mvcbase.BaseDao;

public interface AlgEquipOfficeDao extends BaseDao<AlgEquipOffice, AlgEquipOfficeExample, String> {
	public List<Map<String,Object>>  selectWithRegion(Map<String, Object> map);
	public int  selectCountWithRegion(Map<String, Object> map);
}
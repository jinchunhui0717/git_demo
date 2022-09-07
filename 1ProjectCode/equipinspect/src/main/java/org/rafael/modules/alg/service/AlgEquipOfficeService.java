package org.rafael.modules.alg.service;

import java.util.List;
import java.util.Map;
import org.rafael.modules.alg.entity.AlgEquipOffice;
import org.rafael.modules.alg.entity.AlgEquipOfficeExample;
import org.rafael.modules.util.mvcbase.BaseService;

public interface AlgEquipOfficeService extends BaseService<AlgEquipOffice, AlgEquipOfficeExample, String> {
	public List<Map<String,Object>>  selectWithRegion(Map<String, Object> map);
	public int  selectCountWithRegion(Map<String, Object> map);
}
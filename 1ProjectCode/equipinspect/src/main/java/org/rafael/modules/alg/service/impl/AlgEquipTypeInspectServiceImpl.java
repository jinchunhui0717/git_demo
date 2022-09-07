package org.rafael.modules.alg.service.impl;

import java.util.List;
import java.util.Map;

import org.rafael.modules.alg.dao.AlgEquipTypeInspectDao;
import org.rafael.modules.alg.entity.AlgEquipTypeInspect;
import org.rafael.modules.alg.entity.AlgEquipTypeInspectExample;
import org.rafael.modules.alg.service.AlgEquipTypeInspectService;
import org.rafael.modules.util.mvcbase.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlgEquipTypeInspectServiceImpl extends BaseServiceImpl<AlgEquipTypeInspect, AlgEquipTypeInspectExample, String> implements AlgEquipTypeInspectService {
	@Autowired
	private AlgEquipTypeInspectDao algEquipTypeInspectDao;
	
	@Override
	public List<Map<String, Object>> selectWithType(Map<String, Object> map) {
		return algEquipTypeInspectDao.selectWithType(map);
	}

	@Override
	public int selectCountWithType(Map<String, Object> map) {
		return algEquipTypeInspectDao.selectCountWithType(map);
	}
}
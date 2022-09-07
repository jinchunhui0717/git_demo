package org.rafael.modules.alg.service.impl;

import java.util.List;
import java.util.Map;

import org.rafael.modules.alg.dao.AlgEquipOfficeDao;
import org.rafael.modules.alg.entity.AlgEquipOffice;
import org.rafael.modules.alg.entity.AlgEquipOfficeExample;
import org.rafael.modules.alg.service.AlgEquipOfficeService;
import org.rafael.modules.util.mvcbase.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlgEquipOfficeServiceImpl extends BaseServiceImpl<AlgEquipOffice, AlgEquipOfficeExample, String> implements AlgEquipOfficeService {
	@Autowired
	private AlgEquipOfficeDao algEquipOfficeDao;
	
	@Override
	public List<Map<String, Object>> selectWithRegion(
			Map<String, Object> map) {
		return algEquipOfficeDao.selectWithRegion(map);
	}

	@Override
	public int selectCountWithRegion(Map<String, Object> map) {
		return algEquipOfficeDao.selectCountWithRegion(map);
	}

}
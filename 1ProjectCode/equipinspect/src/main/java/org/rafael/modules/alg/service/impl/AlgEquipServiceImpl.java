package org.rafael.modules.alg.service.impl;

import java.util.List;
import java.util.Map;

import org.rafael.modules.alg.dao.AlgEquipDao;
import org.rafael.modules.alg.entity.AlgEquip;
import org.rafael.modules.alg.entity.AlgEquipExample;
import org.rafael.modules.alg.service.AlgEquipService;
import org.rafael.modules.util.mvcbase.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlgEquipServiceImpl extends BaseServiceImpl<AlgEquip, AlgEquipExample, String> implements AlgEquipService {

	@Autowired
	private AlgEquipDao algEquipDao;
	@Override
	public List<Map<String, Object>> selectWithAll(Map<String, Object> map) {
		return algEquipDao.selectWithAll(map);
	}
	@Override
	public int selectCountWithAll(Map<String, Object> map) {
		return algEquipDao.selectCountWithAll(map);
	}
	@Override
	public List<Map<String, Object>> selectWithTaskDetail(
			Map<String, Object> map) {
		return algEquipDao.selectWithTaskDetail(map);
	}
	@Override
	public int selectCountWithTaskDetail(Map<String, Object> map) {
		return algEquipDao.selectCountWithTaskDetail(map);
	}
	@Override
	public List<Map<String, Object>> selectWithTaskAll(Map<String, Object> map) {
		return algEquipDao.selectWithTaskAll(map);
	}
	@Override
	public int selectCountWithTaskAll(Map<String, Object> map) {
		return algEquipDao.selectCountWithTaskAll(map);
	}
}
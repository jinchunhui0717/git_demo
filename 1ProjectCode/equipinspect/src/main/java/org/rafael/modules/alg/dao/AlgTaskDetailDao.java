package org.rafael.modules.alg.dao;

import org.rafael.modules.alg.entity.AlgTaskDetail;
import org.rafael.modules.alg.entity.AlgTaskDetailExample;
import org.rafael.modules.util.mvcbase.BaseDao;

public interface AlgTaskDetailDao extends BaseDao<AlgTaskDetail, AlgTaskDetailExample, String> {
	public void delCurMonth();
}
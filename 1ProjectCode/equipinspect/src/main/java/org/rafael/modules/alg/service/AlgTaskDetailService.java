package org.rafael.modules.alg.service;

import org.rafael.modules.alg.entity.AlgTaskDetail;
import org.rafael.modules.alg.entity.AlgTaskDetailExample;
import org.rafael.modules.util.mvcbase.BaseService;

public interface AlgTaskDetailService extends BaseService<AlgTaskDetail, AlgTaskDetailExample, String> {
	public void delCurMonth();
}
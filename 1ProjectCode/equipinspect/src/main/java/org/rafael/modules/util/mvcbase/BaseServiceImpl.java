package org.rafael.modules.util.mvcbase;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseServiceImpl<T, E, PK extends Serializable> implements BaseService<T, E, PK>{
	/**
	 * spring4.0以上的泛型注入
	 */
	@Autowired
	protected BaseDao<T, E, PK> baseDao;

	@Override
	public int countByExample(E example) {
		return baseDao.countByExample(example);
	}

	@Override
	public int deleteByExample(E example) {
		return baseDao.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(PK id) {
		return baseDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(T record) {
		return baseDao.insert(record);
	}

	@Override
	public int insertSelective(T record) {
		return baseDao.insertSelective(record);
	}

	@Override
	public int insertBatch(List<T> record) {
		return baseDao.insertBatch(record);
	}

	@Override
	public List<T> selectByExample(E example) {
		return baseDao.selectByExample(example);
	}

	@Override
	public T selectByPrimaryKey(PK id) {
		return baseDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(T record, E example) {
		return baseDao.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(T record, E example) {
		return baseDao.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		return baseDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(T record) {
		return baseDao.updateByPrimaryKey(record);
	}
	
	
	
}

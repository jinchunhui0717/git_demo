package org.rafael.modules.util.mvcbase;
import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.annotations.Param;
public interface BaseDao<T, E, PK extends Serializable> {
	/**
     *  根据指定的条件获取数据库记录数
     *
     * @param example
     */
    int countByExample(E example);
    /**
     *  根据指定的条件删除数据库符合条件的记录
     *
     * @param example
     */
    int deleteByExample(E example);   
    /**
     *  根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(PK id);
    /**
     *  新写入数据库记录
     *
     * @param record
     */
    int insert(T record);
    /**
     *  批量写入数据库记录
     *
     * @param record
     */
    int insertBatch(List<T> record);
    /**
     *  动态字段,写入数据库记录
     *
     * @param record
     */
    int insertSelective(T record);
    
    /**
     *  根据指定的条件查询符合条件的数据库记录
     *
     * @param example
     */
    List<T> selectByExample(E example);
    /**
     *  根据指定主键获取一条数据库记录:sys_area
     *
     * @param id
     */
    T selectByPrimaryKey(PK id);
    /**
     *  动态根据指定的条件来更新符合条件的数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") T record, @Param("example") E example);
    /**
     *  根据指定的条件来更新符合条件的数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") T record, @Param("example") E example);
    /**
     *  动态字段,根据主键来更新符合条件的数据库记录
     *
     * @param record
     */
	int updateByPrimaryKeySelective(T record);
	/**
     *  根据主键来更新符合条件的数据库记录
     *
     * @param record
     */
	int updateByPrimaryKey(T record);
}
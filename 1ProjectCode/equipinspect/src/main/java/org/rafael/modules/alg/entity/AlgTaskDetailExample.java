/*
* AlgTaskDetailExample.java
* Copyright(C) 2018-2025 org.rafael
* @date 2018-07-20
*/
package org.rafael.modules.alg.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AlgTaskDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     *  构造查询条件:dbo.alg_task_detail
     */
    public AlgTaskDetailExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     *  设置排序字段:dbo.alg_task_detail
     *
     * @param orderByClause 排序字段
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     *  获取排序字段:dbo.alg_task_detail
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     *  设置过滤重复数据:dbo.alg_task_detail
     *
     * @param distinct 是否过滤重复数据
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     *  是否过滤重复数据:dbo.alg_task_detail
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     *  获取当前的查询条件实例:dbo.alg_task_detail
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * :dbo.alg_task_detail
     *
     * @param criteria 过滤条件实例
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * :dbo.alg_task_detail
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *  创建一个查询条件:dbo.alg_task_detail
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     *  内部构建查询条件对象:dbo.alg_task_detail
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *  清除查询条件:dbo.alg_task_detail
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * @Title dbo.alg_task_detail表的实体类
     * @version 1.0
     * @Author rafael
     * @Date 2018-07-20 13:33:42
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("task_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("task_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(String value) {
            addCriterion("task_id =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(String value) {
            addCriterion("task_id <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(String value) {
            addCriterion("task_id >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(String value) {
            addCriterion("task_id >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(String value) {
            addCriterion("task_id <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(String value) {
            addCriterion("task_id <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLike(String value) {
            addCriterion("task_id like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotLike(String value) {
            addCriterion("task_id not like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<String> values) {
            addCriterion("task_id in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<String> values) {
            addCriterion("task_id not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(String value1, String value2) {
            addCriterion("task_id between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(String value1, String value2) {
            addCriterion("task_id not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andEquipIdIsNull() {
            addCriterion("equip_id is null");
            return (Criteria) this;
        }

        public Criteria andEquipIdIsNotNull() {
            addCriterion("equip_id is not null");
            return (Criteria) this;
        }

        public Criteria andEquipIdEqualTo(String value) {
            addCriterion("equip_id =", value, "equipId");
            return (Criteria) this;
        }

        public Criteria andEquipIdNotEqualTo(String value) {
            addCriterion("equip_id <>", value, "equipId");
            return (Criteria) this;
        }

        public Criteria andEquipIdGreaterThan(String value) {
            addCriterion("equip_id >", value, "equipId");
            return (Criteria) this;
        }

        public Criteria andEquipIdGreaterThanOrEqualTo(String value) {
            addCriterion("equip_id >=", value, "equipId");
            return (Criteria) this;
        }

        public Criteria andEquipIdLessThan(String value) {
            addCriterion("equip_id <", value, "equipId");
            return (Criteria) this;
        }

        public Criteria andEquipIdLessThanOrEqualTo(String value) {
            addCriterion("equip_id <=", value, "equipId");
            return (Criteria) this;
        }

        public Criteria andEquipIdLike(String value) {
            addCriterion("equip_id like", value, "equipId");
            return (Criteria) this;
        }

        public Criteria andEquipIdNotLike(String value) {
            addCriterion("equip_id not like", value, "equipId");
            return (Criteria) this;
        }

        public Criteria andEquipIdIn(List<String> values) {
            addCriterion("equip_id in", values, "equipId");
            return (Criteria) this;
        }

        public Criteria andEquipIdNotIn(List<String> values) {
            addCriterion("equip_id not in", values, "equipId");
            return (Criteria) this;
        }

        public Criteria andEquipIdBetween(String value1, String value2) {
            addCriterion("equip_id between", value1, value2, "equipId");
            return (Criteria) this;
        }

        public Criteria andEquipIdNotBetween(String value1, String value2) {
            addCriterion("equip_id not between", value1, value2, "equipId");
            return (Criteria) this;
        }

        public Criteria andEquipStateIsNull() {
            addCriterion("equip_state is null");
            return (Criteria) this;
        }

        public Criteria andEquipStateIsNotNull() {
            addCriterion("equip_state is not null");
            return (Criteria) this;
        }

        public Criteria andEquipStateEqualTo(String value) {
            addCriterion("equip_state =", value, "equipState");
            return (Criteria) this;
        }

        public Criteria andEquipStateNotEqualTo(String value) {
            addCriterion("equip_state <>", value, "equipState");
            return (Criteria) this;
        }

        public Criteria andEquipStateGreaterThan(String value) {
            addCriterion("equip_state >", value, "equipState");
            return (Criteria) this;
        }

        public Criteria andEquipStateGreaterThanOrEqualTo(String value) {
            addCriterion("equip_state >=", value, "equipState");
            return (Criteria) this;
        }

        public Criteria andEquipStateLessThan(String value) {
            addCriterion("equip_state <", value, "equipState");
            return (Criteria) this;
        }

        public Criteria andEquipStateLessThanOrEqualTo(String value) {
            addCriterion("equip_state <=", value, "equipState");
            return (Criteria) this;
        }

        public Criteria andEquipStateLike(String value) {
            addCriterion("equip_state like", value, "equipState");
            return (Criteria) this;
        }

        public Criteria andEquipStateNotLike(String value) {
            addCriterion("equip_state not like", value, "equipState");
            return (Criteria) this;
        }

        public Criteria andEquipStateIn(List<String> values) {
            addCriterion("equip_state in", values, "equipState");
            return (Criteria) this;
        }

        public Criteria andEquipStateNotIn(List<String> values) {
            addCriterion("equip_state not in", values, "equipState");
            return (Criteria) this;
        }

        public Criteria andEquipStateBetween(String value1, String value2) {
            addCriterion("equip_state between", value1, value2, "equipState");
            return (Criteria) this;
        }

        public Criteria andEquipStateNotBetween(String value1, String value2) {
            addCriterion("equip_state not between", value1, value2, "equipState");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(String value) {
            addCriterion("operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(String value) {
            addCriterion("operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(String value) {
            addCriterion("operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(String value) {
            addCriterion("operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(String value) {
            addCriterion("operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLike(String value) {
            addCriterion("operator like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotLike(String value) {
            addCriterion("operator not like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<String> values) {
            addCriterion("operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<String> values) {
            addCriterion("operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(String value1, String value2) {
            addCriterion("operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(String value1, String value2) {
            addCriterion("operator not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andOptStartdateIsNull() {
            addCriterion("opt_startdate is null");
            return (Criteria) this;
        }

        public Criteria andOptStartdateIsNotNull() {
            addCriterion("opt_startdate is not null");
            return (Criteria) this;
        }

        public Criteria andOptStartdateEqualTo(Date value) {
            addCriterionForJDBCDate("opt_startdate =", value, "optStartdate");
            return (Criteria) this;
        }

        public Criteria andOptStartdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("opt_startdate <>", value, "optStartdate");
            return (Criteria) this;
        }

        public Criteria andOptStartdateGreaterThan(Date value) {
            addCriterionForJDBCDate("opt_startdate >", value, "optStartdate");
            return (Criteria) this;
        }

        public Criteria andOptStartdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("opt_startdate >=", value, "optStartdate");
            return (Criteria) this;
        }

        public Criteria andOptStartdateLessThan(Date value) {
            addCriterionForJDBCDate("opt_startdate <", value, "optStartdate");
            return (Criteria) this;
        }

        public Criteria andOptStartdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("opt_startdate <=", value, "optStartdate");
            return (Criteria) this;
        }

        public Criteria andOptStartdateIn(List<Date> values) {
            addCriterionForJDBCDate("opt_startdate in", values, "optStartdate");
            return (Criteria) this;
        }

        public Criteria andOptStartdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("opt_startdate not in", values, "optStartdate");
            return (Criteria) this;
        }

        public Criteria andOptStartdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("opt_startdate between", value1, value2, "optStartdate");
            return (Criteria) this;
        }

        public Criteria andOptStartdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("opt_startdate not between", value1, value2, "optStartdate");
            return (Criteria) this;
        }

        public Criteria andOptEnddateIsNull() {
            addCriterion("opt_enddate is null");
            return (Criteria) this;
        }

        public Criteria andOptEnddateIsNotNull() {
            addCriterion("opt_enddate is not null");
            return (Criteria) this;
        }

        public Criteria andOptEnddateEqualTo(Date value) {
            addCriterionForJDBCDate("opt_enddate =", value, "optEnddate");
            return (Criteria) this;
        }

        public Criteria andOptEnddateNotEqualTo(Date value) {
            addCriterionForJDBCDate("opt_enddate <>", value, "optEnddate");
            return (Criteria) this;
        }

        public Criteria andOptEnddateGreaterThan(Date value) {
            addCriterionForJDBCDate("opt_enddate >", value, "optEnddate");
            return (Criteria) this;
        }

        public Criteria andOptEnddateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("opt_enddate >=", value, "optEnddate");
            return (Criteria) this;
        }

        public Criteria andOptEnddateLessThan(Date value) {
            addCriterionForJDBCDate("opt_enddate <", value, "optEnddate");
            return (Criteria) this;
        }

        public Criteria andOptEnddateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("opt_enddate <=", value, "optEnddate");
            return (Criteria) this;
        }

        public Criteria andOptEnddateIn(List<Date> values) {
            addCriterionForJDBCDate("opt_enddate in", values, "optEnddate");
            return (Criteria) this;
        }

        public Criteria andOptEnddateNotIn(List<Date> values) {
            addCriterionForJDBCDate("opt_enddate not in", values, "optEnddate");
            return (Criteria) this;
        }

        public Criteria andOptEnddateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("opt_enddate between", value1, value2, "optEnddate");
            return (Criteria) this;
        }

        public Criteria andOptEnddateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("opt_enddate not between", value1, value2, "optEnddate");
            return (Criteria) this;
        }

        public Criteria andImageurlIsNull() {
            addCriterion("imageurl is null");
            return (Criteria) this;
        }

        public Criteria andImageurlIsNotNull() {
            addCriterion("imageurl is not null");
            return (Criteria) this;
        }

        public Criteria andImageurlEqualTo(String value) {
            addCriterion("imageurl =", value, "imageurl");
            return (Criteria) this;
        }

        public Criteria andImageurlNotEqualTo(String value) {
            addCriterion("imageurl <>", value, "imageurl");
            return (Criteria) this;
        }

        public Criteria andImageurlGreaterThan(String value) {
            addCriterion("imageurl >", value, "imageurl");
            return (Criteria) this;
        }

        public Criteria andImageurlGreaterThanOrEqualTo(String value) {
            addCriterion("imageurl >=", value, "imageurl");
            return (Criteria) this;
        }

        public Criteria andImageurlLessThan(String value) {
            addCriterion("imageurl <", value, "imageurl");
            return (Criteria) this;
        }

        public Criteria andImageurlLessThanOrEqualTo(String value) {
            addCriterion("imageurl <=", value, "imageurl");
            return (Criteria) this;
        }

        public Criteria andImageurlLike(String value) {
            addCriterion("imageurl like", value, "imageurl");
            return (Criteria) this;
        }

        public Criteria andImageurlNotLike(String value) {
            addCriterion("imageurl not like", value, "imageurl");
            return (Criteria) this;
        }

        public Criteria andImageurlIn(List<String> values) {
            addCriterion("imageurl in", values, "imageurl");
            return (Criteria) this;
        }

        public Criteria andImageurlNotIn(List<String> values) {
            addCriterion("imageurl not in", values, "imageurl");
            return (Criteria) this;
        }

        public Criteria andImageurlBetween(String value1, String value2) {
            addCriterion("imageurl between", value1, value2, "imageurl");
            return (Criteria) this;
        }

        public Criteria andImageurlNotBetween(String value1, String value2) {
            addCriterion("imageurl not between", value1, value2, "imageurl");
            return (Criteria) this;
        }

        public Criteria andOptDateIsNull() {
            addCriterion("opt_date is null");
            return (Criteria) this;
        }

        public Criteria andOptDateIsNotNull() {
            addCriterion("opt_date is not null");
            return (Criteria) this;
        }

        public Criteria andOptDateEqualTo(Date value) {
            addCriterion("opt_date =", value, "optDate");
            return (Criteria) this;
        }

        public Criteria andOptDateNotEqualTo(Date value) {
            addCriterion("opt_date <>", value, "optDate");
            return (Criteria) this;
        }

        public Criteria andOptDateGreaterThan(Date value) {
            addCriterion("opt_date >", value, "optDate");
            return (Criteria) this;
        }

        public Criteria andOptDateGreaterThanOrEqualTo(Date value) {
            addCriterion("opt_date >=", value, "optDate");
            return (Criteria) this;
        }

        public Criteria andOptDateLessThan(Date value) {
            addCriterion("opt_date <", value, "optDate");
            return (Criteria) this;
        }

        public Criteria andOptDateLessThanOrEqualTo(Date value) {
            addCriterion("opt_date <=", value, "optDate");
            return (Criteria) this;
        }

        public Criteria andOptDateIn(List<Date> values) {
            addCriterion("opt_date in", values, "optDate");
            return (Criteria) this;
        }

        public Criteria andOptDateNotIn(List<Date> values) {
            addCriterion("opt_date not in", values, "optDate");
            return (Criteria) this;
        }

        public Criteria andOptDateBetween(Date value1, Date value2) {
            addCriterion("opt_date between", value1, value2, "optDate");
            return (Criteria) this;
        }

        public Criteria andOptDateNotBetween(Date value1, Date value2) {
            addCriterion("opt_date not between", value1, value2, "optDate");
            return (Criteria) this;
        }
    }

    /**
     * @Title dbo.alg_task_detail表的实体类
     * @version 1.0
     * @Author fendo
     * @Date 2018-07-20 13:33:42
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * @Title dbo.alg_task_detail表的实体类
     * @version 1.0
     * @Author rafael
     * @Date 2018-07-20 13:33:42
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
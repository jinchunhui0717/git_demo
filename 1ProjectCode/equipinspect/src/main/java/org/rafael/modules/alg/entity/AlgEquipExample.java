/*
* AlgEquipExample.java
* Copyright(C) 2018-2025 org.rafael
* @date 2018-07-20
*/
package org.rafael.modules.alg.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlgEquipExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     *  构造查询条件:dbo.alg_equip
     */
    public AlgEquipExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     *  设置排序字段:dbo.alg_equip
     *
     * @param orderByClause 排序字段
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     *  获取排序字段:dbo.alg_equip
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     *  设置过滤重复数据:dbo.alg_equip
     *
     * @param distinct 是否过滤重复数据
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     *  是否过滤重复数据:dbo.alg_equip
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     *  获取当前的查询条件实例:dbo.alg_equip
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * :dbo.alg_equip
     *
     * @param criteria 过滤条件实例
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * :dbo.alg_equip
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *  创建一个查询条件:dbo.alg_equip
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     *  内部构建查询条件对象:dbo.alg_equip
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *  清除查询条件:dbo.alg_equip
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * @Title dbo.alg_equip表的实体类
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

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andBarcodeIsNull() {
            addCriterion("barcode is null");
            return (Criteria) this;
        }

        public Criteria andBarcodeIsNotNull() {
            addCriterion("barcode is not null");
            return (Criteria) this;
        }

        public Criteria andBarcodeEqualTo(String value) {
            addCriterion("barcode =", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotEqualTo(String value) {
            addCriterion("barcode <>", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeGreaterThan(String value) {
            addCriterion("barcode >", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeGreaterThanOrEqualTo(String value) {
            addCriterion("barcode >=", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLessThan(String value) {
            addCriterion("barcode <", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLessThanOrEqualTo(String value) {
            addCriterion("barcode <=", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLike(String value) {
            addCriterion("barcode like", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotLike(String value) {
            addCriterion("barcode not like", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeIn(List<String> values) {
            addCriterion("barcode in", values, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotIn(List<String> values) {
            addCriterion("barcode not in", values, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeBetween(String value1, String value2) {
            addCriterion("barcode between", value1, value2, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotBetween(String value1, String value2) {
            addCriterion("barcode not between", value1, value2, "barcode");
            return (Criteria) this;
        }

        public Criteria andImageIsNull() {
            addCriterion("image is null");
            return (Criteria) this;
        }

        public Criteria andImageIsNotNull() {
            addCriterion("image is not null");
            return (Criteria) this;
        }

        public Criteria andImageEqualTo(String value) {
            addCriterion("image =", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotEqualTo(String value) {
            addCriterion("image <>", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThan(String value) {
            addCriterion("image >", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThanOrEqualTo(String value) {
            addCriterion("image >=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThan(String value) {
            addCriterion("image <", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThanOrEqualTo(String value) {
            addCriterion("image <=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLike(String value) {
            addCriterion("image like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotLike(String value) {
            addCriterion("image not like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageIn(List<String> values) {
            addCriterion("image in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotIn(List<String> values) {
            addCriterion("image not in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageBetween(String value1, String value2) {
            addCriterion("image between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotBetween(String value1, String value2) {
            addCriterion("image not between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andPosxIsNull() {
            addCriterion("posx is null");
            return (Criteria) this;
        }

        public Criteria andPosxIsNotNull() {
            addCriterion("posx is not null");
            return (Criteria) this;
        }

        public Criteria andPosxEqualTo(Integer value) {
            addCriterion("posx =", value, "posx");
            return (Criteria) this;
        }

        public Criteria andPosxNotEqualTo(Integer value) {
            addCriterion("posx <>", value, "posx");
            return (Criteria) this;
        }

        public Criteria andPosxGreaterThan(Integer value) {
            addCriterion("posx >", value, "posx");
            return (Criteria) this;
        }

        public Criteria andPosxGreaterThanOrEqualTo(Integer value) {
            addCriterion("posx >=", value, "posx");
            return (Criteria) this;
        }

        public Criteria andPosxLessThan(Integer value) {
            addCriterion("posx <", value, "posx");
            return (Criteria) this;
        }

        public Criteria andPosxLessThanOrEqualTo(Integer value) {
            addCriterion("posx <=", value, "posx");
            return (Criteria) this;
        }

        public Criteria andPosxIn(List<Integer> values) {
            addCriterion("posx in", values, "posx");
            return (Criteria) this;
        }

        public Criteria andPosxNotIn(List<Integer> values) {
            addCriterion("posx not in", values, "posx");
            return (Criteria) this;
        }

        public Criteria andPosxBetween(Integer value1, Integer value2) {
            addCriterion("posx between", value1, value2, "posx");
            return (Criteria) this;
        }

        public Criteria andPosxNotBetween(Integer value1, Integer value2) {
            addCriterion("posx not between", value1, value2, "posx");
            return (Criteria) this;
        }

        public Criteria andPosyIsNull() {
            addCriterion("posy is null");
            return (Criteria) this;
        }

        public Criteria andPosyIsNotNull() {
            addCriterion("posy is not null");
            return (Criteria) this;
        }

        public Criteria andPosyEqualTo(Integer value) {
            addCriterion("posy =", value, "posy");
            return (Criteria) this;
        }

        public Criteria andPosyNotEqualTo(Integer value) {
            addCriterion("posy <>", value, "posy");
            return (Criteria) this;
        }

        public Criteria andPosyGreaterThan(Integer value) {
            addCriterion("posy >", value, "posy");
            return (Criteria) this;
        }

        public Criteria andPosyGreaterThanOrEqualTo(Integer value) {
            addCriterion("posy >=", value, "posy");
            return (Criteria) this;
        }

        public Criteria andPosyLessThan(Integer value) {
            addCriterion("posy <", value, "posy");
            return (Criteria) this;
        }

        public Criteria andPosyLessThanOrEqualTo(Integer value) {
            addCriterion("posy <=", value, "posy");
            return (Criteria) this;
        }

        public Criteria andPosyIn(List<Integer> values) {
            addCriterion("posy in", values, "posy");
            return (Criteria) this;
        }

        public Criteria andPosyNotIn(List<Integer> values) {
            addCriterion("posy not in", values, "posy");
            return (Criteria) this;
        }

        public Criteria andPosyBetween(Integer value1, Integer value2) {
            addCriterion("posy between", value1, value2, "posy");
            return (Criteria) this;
        }

        public Criteria andPosyNotBetween(Integer value1, Integer value2) {
            addCriterion("posy not between", value1, value2, "posy");
            return (Criteria) this;
        }

        public Criteria andPosRegionIsNull() {
            addCriterion("pos_region is null");
            return (Criteria) this;
        }

        public Criteria andPosRegionIsNotNull() {
            addCriterion("pos_region is not null");
            return (Criteria) this;
        }

        public Criteria andPosRegionEqualTo(String value) {
            addCriterion("pos_region =", value, "posRegion");
            return (Criteria) this;
        }

        public Criteria andPosRegionNotEqualTo(String value) {
            addCriterion("pos_region <>", value, "posRegion");
            return (Criteria) this;
        }

        public Criteria andPosRegionGreaterThan(String value) {
            addCriterion("pos_region >", value, "posRegion");
            return (Criteria) this;
        }

        public Criteria andPosRegionGreaterThanOrEqualTo(String value) {
            addCriterion("pos_region >=", value, "posRegion");
            return (Criteria) this;
        }

        public Criteria andPosRegionLessThan(String value) {
            addCriterion("pos_region <", value, "posRegion");
            return (Criteria) this;
        }

        public Criteria andPosRegionLessThanOrEqualTo(String value) {
            addCriterion("pos_region <=", value, "posRegion");
            return (Criteria) this;
        }

        public Criteria andPosRegionLike(String value) {
            addCriterion("pos_region like", value, "posRegion");
            return (Criteria) this;
        }

        public Criteria andPosRegionNotLike(String value) {
            addCriterion("pos_region not like", value, "posRegion");
            return (Criteria) this;
        }

        public Criteria andPosRegionIn(List<String> values) {
            addCriterion("pos_region in", values, "posRegion");
            return (Criteria) this;
        }

        public Criteria andPosRegionNotIn(List<String> values) {
            addCriterion("pos_region not in", values, "posRegion");
            return (Criteria) this;
        }

        public Criteria andPosRegionBetween(String value1, String value2) {
            addCriterion("pos_region between", value1, value2, "posRegion");
            return (Criteria) this;
        }

        public Criteria andPosRegionNotBetween(String value1, String value2) {
            addCriterion("pos_region not between", value1, value2, "posRegion");
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

        public Criteria andAlgEquipOfficeIdIsNull() {
            addCriterion("alg_equip_office_id is null");
            return (Criteria) this;
        }

        public Criteria andAlgEquipOfficeIdIsNotNull() {
            addCriterion("alg_equip_office_id is not null");
            return (Criteria) this;
        }

        public Criteria andAlgEquipOfficeIdEqualTo(String value) {
            addCriterion("alg_equip_office_id =", value, "algEquipOfficeId");
            return (Criteria) this;
        }

        public Criteria andAlgEquipOfficeIdNotEqualTo(String value) {
            addCriterion("alg_equip_office_id <>", value, "algEquipOfficeId");
            return (Criteria) this;
        }

        public Criteria andAlgEquipOfficeIdGreaterThan(String value) {
            addCriterion("alg_equip_office_id >", value, "algEquipOfficeId");
            return (Criteria) this;
        }

        public Criteria andAlgEquipOfficeIdGreaterThanOrEqualTo(String value) {
            addCriterion("alg_equip_office_id >=", value, "algEquipOfficeId");
            return (Criteria) this;
        }

        public Criteria andAlgEquipOfficeIdLessThan(String value) {
            addCriterion("alg_equip_office_id <", value, "algEquipOfficeId");
            return (Criteria) this;
        }

        public Criteria andAlgEquipOfficeIdLessThanOrEqualTo(String value) {
            addCriterion("alg_equip_office_id <=", value, "algEquipOfficeId");
            return (Criteria) this;
        }

        public Criteria andAlgEquipOfficeIdLike(String value) {
            addCriterion("alg_equip_office_id like", value, "algEquipOfficeId");
            return (Criteria) this;
        }

        public Criteria andAlgEquipOfficeIdNotLike(String value) {
            addCriterion("alg_equip_office_id not like", value, "algEquipOfficeId");
            return (Criteria) this;
        }

        public Criteria andAlgEquipOfficeIdIn(List<String> values) {
            addCriterion("alg_equip_office_id in", values, "algEquipOfficeId");
            return (Criteria) this;
        }

        public Criteria andAlgEquipOfficeIdNotIn(List<String> values) {
            addCriterion("alg_equip_office_id not in", values, "algEquipOfficeId");
            return (Criteria) this;
        }

        public Criteria andAlgEquipOfficeIdBetween(String value1, String value2) {
            addCriterion("alg_equip_office_id between", value1, value2, "algEquipOfficeId");
            return (Criteria) this;
        }

        public Criteria andAlgEquipOfficeIdNotBetween(String value1, String value2) {
            addCriterion("alg_equip_office_id not between", value1, value2, "algEquipOfficeId");
            return (Criteria) this;
        }

        public Criteria andEquipOperatorIsNull() {
            addCriterion("equip_operator is null");
            return (Criteria) this;
        }

        public Criteria andEquipOperatorIsNotNull() {
            addCriterion("equip_operator is not null");
            return (Criteria) this;
        }

        public Criteria andEquipOperatorEqualTo(String value) {
            addCriterion("equip_operator =", value, "equipOperator");
            return (Criteria) this;
        }

        public Criteria andEquipOperatorNotEqualTo(String value) {
            addCriterion("equip_operator <>", value, "equipOperator");
            return (Criteria) this;
        }

        public Criteria andEquipOperatorGreaterThan(String value) {
            addCriterion("equip_operator >", value, "equipOperator");
            return (Criteria) this;
        }

        public Criteria andEquipOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("equip_operator >=", value, "equipOperator");
            return (Criteria) this;
        }

        public Criteria andEquipOperatorLessThan(String value) {
            addCriterion("equip_operator <", value, "equipOperator");
            return (Criteria) this;
        }

        public Criteria andEquipOperatorLessThanOrEqualTo(String value) {
            addCriterion("equip_operator <=", value, "equipOperator");
            return (Criteria) this;
        }

        public Criteria andEquipOperatorLike(String value) {
            addCriterion("equip_operator like", value, "equipOperator");
            return (Criteria) this;
        }

        public Criteria andEquipOperatorNotLike(String value) {
            addCriterion("equip_operator not like", value, "equipOperator");
            return (Criteria) this;
        }

        public Criteria andEquipOperatorIn(List<String> values) {
            addCriterion("equip_operator in", values, "equipOperator");
            return (Criteria) this;
        }

        public Criteria andEquipOperatorNotIn(List<String> values) {
            addCriterion("equip_operator not in", values, "equipOperator");
            return (Criteria) this;
        }

        public Criteria andEquipOperatorBetween(String value1, String value2) {
            addCriterion("equip_operator between", value1, value2, "equipOperator");
            return (Criteria) this;
        }

        public Criteria andEquipOperatorNotBetween(String value1, String value2) {
            addCriterion("equip_operator not between", value1, value2, "equipOperator");
            return (Criteria) this;
        }

        public Criteria andEquipOptStartdateIsNull() {
            addCriterion("equip_opt_startdate is null");
            return (Criteria) this;
        }

        public Criteria andEquipOptStartdateIsNotNull() {
            addCriterion("equip_opt_startdate is not null");
            return (Criteria) this;
        }

        public Criteria andEquipOptStartdateEqualTo(Integer value) {
            addCriterion("equip_opt_startdate =", value, "equipOptStartdate");
            return (Criteria) this;
        }

        public Criteria andEquipOptStartdateNotEqualTo(Integer value) {
            addCriterion("equip_opt_startdate <>", value, "equipOptStartdate");
            return (Criteria) this;
        }

        public Criteria andEquipOptStartdateGreaterThan(Integer value) {
            addCriterion("equip_opt_startdate >", value, "equipOptStartdate");
            return (Criteria) this;
        }

        public Criteria andEquipOptStartdateGreaterThanOrEqualTo(Integer value) {
            addCriterion("equip_opt_startdate >=", value, "equipOptStartdate");
            return (Criteria) this;
        }

        public Criteria andEquipOptStartdateLessThan(Integer value) {
            addCriterion("equip_opt_startdate <", value, "equipOptStartdate");
            return (Criteria) this;
        }

        public Criteria andEquipOptStartdateLessThanOrEqualTo(Integer value) {
            addCriterion("equip_opt_startdate <=", value, "equipOptStartdate");
            return (Criteria) this;
        }

        public Criteria andEquipOptStartdateIn(List<Integer> values) {
            addCriterion("equip_opt_startdate in", values, "equipOptStartdate");
            return (Criteria) this;
        }

        public Criteria andEquipOptStartdateNotIn(List<Integer> values) {
            addCriterion("equip_opt_startdate not in", values, "equipOptStartdate");
            return (Criteria) this;
        }

        public Criteria andEquipOptStartdateBetween(Integer value1, Integer value2) {
            addCriterion("equip_opt_startdate between", value1, value2, "equipOptStartdate");
            return (Criteria) this;
        }

        public Criteria andEquipOptStartdateNotBetween(Integer value1, Integer value2) {
            addCriterion("equip_opt_startdate not between", value1, value2, "equipOptStartdate");
            return (Criteria) this;
        }

        public Criteria andEquipOptEnddateIsNull() {
            addCriterion("equip_opt_enddate is null");
            return (Criteria) this;
        }

        public Criteria andEquipOptEnddateIsNotNull() {
            addCriterion("equip_opt_enddate is not null");
            return (Criteria) this;
        }

        public Criteria andEquipOptEnddateEqualTo(Integer value) {
            addCriterion("equip_opt_enddate =", value, "equipOptEnddate");
            return (Criteria) this;
        }

        public Criteria andEquipOptEnddateNotEqualTo(Integer value) {
            addCriterion("equip_opt_enddate <>", value, "equipOptEnddate");
            return (Criteria) this;
        }

        public Criteria andEquipOptEnddateGreaterThan(Integer value) {
            addCriterion("equip_opt_enddate >", value, "equipOptEnddate");
            return (Criteria) this;
        }

        public Criteria andEquipOptEnddateGreaterThanOrEqualTo(Integer value) {
            addCriterion("equip_opt_enddate >=", value, "equipOptEnddate");
            return (Criteria) this;
        }

        public Criteria andEquipOptEnddateLessThan(Integer value) {
            addCriterion("equip_opt_enddate <", value, "equipOptEnddate");
            return (Criteria) this;
        }

        public Criteria andEquipOptEnddateLessThanOrEqualTo(Integer value) {
            addCriterion("equip_opt_enddate <=", value, "equipOptEnddate");
            return (Criteria) this;
        }

        public Criteria andEquipOptEnddateIn(List<Integer> values) {
            addCriterion("equip_opt_enddate in", values, "equipOptEnddate");
            return (Criteria) this;
        }

        public Criteria andEquipOptEnddateNotIn(List<Integer> values) {
            addCriterion("equip_opt_enddate not in", values, "equipOptEnddate");
            return (Criteria) this;
        }

        public Criteria andEquipOptEnddateBetween(Integer value1, Integer value2) {
            addCriterion("equip_opt_enddate between", value1, value2, "equipOptEnddate");
            return (Criteria) this;
        }

        public Criteria andEquipOptEnddateNotBetween(Integer value1, Integer value2) {
            addCriterion("equip_opt_enddate not between", value1, value2, "equipOptEnddate");
            return (Criteria) this;
        }

        public Criteria andExtend1IsNull() {
            addCriterion("extend1 is null");
            return (Criteria) this;
        }

        public Criteria andExtend1IsNotNull() {
            addCriterion("extend1 is not null");
            return (Criteria) this;
        }

        public Criteria andExtend1EqualTo(String value) {
            addCriterion("extend1 =", value, "extend1");
            return (Criteria) this;
        }

        public Criteria andExtend1NotEqualTo(String value) {
            addCriterion("extend1 <>", value, "extend1");
            return (Criteria) this;
        }

        public Criteria andExtend1GreaterThan(String value) {
            addCriterion("extend1 >", value, "extend1");
            return (Criteria) this;
        }

        public Criteria andExtend1GreaterThanOrEqualTo(String value) {
            addCriterion("extend1 >=", value, "extend1");
            return (Criteria) this;
        }

        public Criteria andExtend1LessThan(String value) {
            addCriterion("extend1 <", value, "extend1");
            return (Criteria) this;
        }

        public Criteria andExtend1LessThanOrEqualTo(String value) {
            addCriterion("extend1 <=", value, "extend1");
            return (Criteria) this;
        }

        public Criteria andExtend1Like(String value) {
            addCriterion("extend1 like", value, "extend1");
            return (Criteria) this;
        }

        public Criteria andExtend1NotLike(String value) {
            addCriterion("extend1 not like", value, "extend1");
            return (Criteria) this;
        }

        public Criteria andExtend1In(List<String> values) {
            addCriterion("extend1 in", values, "extend1");
            return (Criteria) this;
        }

        public Criteria andExtend1NotIn(List<String> values) {
            addCriterion("extend1 not in", values, "extend1");
            return (Criteria) this;
        }

        public Criteria andExtend1Between(String value1, String value2) {
            addCriterion("extend1 between", value1, value2, "extend1");
            return (Criteria) this;
        }

        public Criteria andExtend1NotBetween(String value1, String value2) {
            addCriterion("extend1 not between", value1, value2, "extend1");
            return (Criteria) this;
        }

        public Criteria andExtend2IsNull() {
            addCriterion("extend2 is null");
            return (Criteria) this;
        }

        public Criteria andExtend2IsNotNull() {
            addCriterion("extend2 is not null");
            return (Criteria) this;
        }

        public Criteria andExtend2EqualTo(String value) {
            addCriterion("extend2 =", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andExtend2NotEqualTo(String value) {
            addCriterion("extend2 <>", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andExtend2GreaterThan(String value) {
            addCriterion("extend2 >", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andExtend2GreaterThanOrEqualTo(String value) {
            addCriterion("extend2 >=", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andExtend2LessThan(String value) {
            addCriterion("extend2 <", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andExtend2LessThanOrEqualTo(String value) {
            addCriterion("extend2 <=", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andExtend2Like(String value) {
            addCriterion("extend2 like", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andExtend2NotLike(String value) {
            addCriterion("extend2 not like", value, "extend2");
            return (Criteria) this;
        }

        public Criteria andExtend2In(List<String> values) {
            addCriterion("extend2 in", values, "extend2");
            return (Criteria) this;
        }

        public Criteria andExtend2NotIn(List<String> values) {
            addCriterion("extend2 not in", values, "extend2");
            return (Criteria) this;
        }

        public Criteria andExtend2Between(String value1, String value2) {
            addCriterion("extend2 between", value1, value2, "extend2");
            return (Criteria) this;
        }

        public Criteria andExtend2NotBetween(String value1, String value2) {
            addCriterion("extend2 not between", value1, value2, "extend2");
            return (Criteria) this;
        }

        public Criteria andExtend3IsNull() {
            addCriterion("extend3 is null");
            return (Criteria) this;
        }

        public Criteria andExtend3IsNotNull() {
            addCriterion("extend3 is not null");
            return (Criteria) this;
        }

        public Criteria andExtend3EqualTo(String value) {
            addCriterion("extend3 =", value, "extend3");
            return (Criteria) this;
        }

        public Criteria andExtend3NotEqualTo(String value) {
            addCriterion("extend3 <>", value, "extend3");
            return (Criteria) this;
        }

        public Criteria andExtend3GreaterThan(String value) {
            addCriterion("extend3 >", value, "extend3");
            return (Criteria) this;
        }

        public Criteria andExtend3GreaterThanOrEqualTo(String value) {
            addCriterion("extend3 >=", value, "extend3");
            return (Criteria) this;
        }

        public Criteria andExtend3LessThan(String value) {
            addCriterion("extend3 <", value, "extend3");
            return (Criteria) this;
        }

        public Criteria andExtend3LessThanOrEqualTo(String value) {
            addCriterion("extend3 <=", value, "extend3");
            return (Criteria) this;
        }

        public Criteria andExtend3Like(String value) {
            addCriterion("extend3 like", value, "extend3");
            return (Criteria) this;
        }

        public Criteria andExtend3NotLike(String value) {
            addCriterion("extend3 not like", value, "extend3");
            return (Criteria) this;
        }

        public Criteria andExtend3In(List<String> values) {
            addCriterion("extend3 in", values, "extend3");
            return (Criteria) this;
        }

        public Criteria andExtend3NotIn(List<String> values) {
            addCriterion("extend3 not in", values, "extend3");
            return (Criteria) this;
        }

        public Criteria andExtend3Between(String value1, String value2) {
            addCriterion("extend3 between", value1, value2, "extend3");
            return (Criteria) this;
        }

        public Criteria andExtend3NotBetween(String value1, String value2) {
            addCriterion("extend3 not between", value1, value2, "extend3");
            return (Criteria) this;
        }

        public Criteria andExtend4IsNull() {
            addCriterion("extend4 is null");
            return (Criteria) this;
        }

        public Criteria andExtend4IsNotNull() {
            addCriterion("extend4 is not null");
            return (Criteria) this;
        }

        public Criteria andExtend4EqualTo(String value) {
            addCriterion("extend4 =", value, "extend4");
            return (Criteria) this;
        }

        public Criteria andExtend4NotEqualTo(String value) {
            addCriterion("extend4 <>", value, "extend4");
            return (Criteria) this;
        }

        public Criteria andExtend4GreaterThan(String value) {
            addCriterion("extend4 >", value, "extend4");
            return (Criteria) this;
        }

        public Criteria andExtend4GreaterThanOrEqualTo(String value) {
            addCriterion("extend4 >=", value, "extend4");
            return (Criteria) this;
        }

        public Criteria andExtend4LessThan(String value) {
            addCriterion("extend4 <", value, "extend4");
            return (Criteria) this;
        }

        public Criteria andExtend4LessThanOrEqualTo(String value) {
            addCriterion("extend4 <=", value, "extend4");
            return (Criteria) this;
        }

        public Criteria andExtend4Like(String value) {
            addCriterion("extend4 like", value, "extend4");
            return (Criteria) this;
        }

        public Criteria andExtend4NotLike(String value) {
            addCriterion("extend4 not like", value, "extend4");
            return (Criteria) this;
        }

        public Criteria andExtend4In(List<String> values) {
            addCriterion("extend4 in", values, "extend4");
            return (Criteria) this;
        }

        public Criteria andExtend4NotIn(List<String> values) {
            addCriterion("extend4 not in", values, "extend4");
            return (Criteria) this;
        }

        public Criteria andExtend4Between(String value1, String value2) {
            addCriterion("extend4 between", value1, value2, "extend4");
            return (Criteria) this;
        }

        public Criteria andExtend4NotBetween(String value1, String value2) {
            addCriterion("extend4 not between", value1, value2, "extend4");
            return (Criteria) this;
        }

        public Criteria andExtend5IsNull() {
            addCriterion("extend5 is null");
            return (Criteria) this;
        }

        public Criteria andExtend5IsNotNull() {
            addCriterion("extend5 is not null");
            return (Criteria) this;
        }

        public Criteria andExtend5EqualTo(String value) {
            addCriterion("extend5 =", value, "extend5");
            return (Criteria) this;
        }

        public Criteria andExtend5NotEqualTo(String value) {
            addCriterion("extend5 <>", value, "extend5");
            return (Criteria) this;
        }

        public Criteria andExtend5GreaterThan(String value) {
            addCriterion("extend5 >", value, "extend5");
            return (Criteria) this;
        }

        public Criteria andExtend5GreaterThanOrEqualTo(String value) {
            addCriterion("extend5 >=", value, "extend5");
            return (Criteria) this;
        }

        public Criteria andExtend5LessThan(String value) {
            addCriterion("extend5 <", value, "extend5");
            return (Criteria) this;
        }

        public Criteria andExtend5LessThanOrEqualTo(String value) {
            addCriterion("extend5 <=", value, "extend5");
            return (Criteria) this;
        }

        public Criteria andExtend5Like(String value) {
            addCriterion("extend5 like", value, "extend5");
            return (Criteria) this;
        }

        public Criteria andExtend5NotLike(String value) {
            addCriterion("extend5 not like", value, "extend5");
            return (Criteria) this;
        }

        public Criteria andExtend5In(List<String> values) {
            addCriterion("extend5 in", values, "extend5");
            return (Criteria) this;
        }

        public Criteria andExtend5NotIn(List<String> values) {
            addCriterion("extend5 not in", values, "extend5");
            return (Criteria) this;
        }

        public Criteria andExtend5Between(String value1, String value2) {
            addCriterion("extend5 between", value1, value2, "extend5");
            return (Criteria) this;
        }

        public Criteria andExtend5NotBetween(String value1, String value2) {
            addCriterion("extend5 not between", value1, value2, "extend5");
            return (Criteria) this;
        }

        public Criteria andExtend6IsNull() {
            addCriterion("extend6 is null");
            return (Criteria) this;
        }

        public Criteria andExtend6IsNotNull() {
            addCriterion("extend6 is not null");
            return (Criteria) this;
        }

        public Criteria andExtend6EqualTo(String value) {
            addCriterion("extend6 =", value, "extend6");
            return (Criteria) this;
        }

        public Criteria andExtend6NotEqualTo(String value) {
            addCriterion("extend6 <>", value, "extend6");
            return (Criteria) this;
        }

        public Criteria andExtend6GreaterThan(String value) {
            addCriterion("extend6 >", value, "extend6");
            return (Criteria) this;
        }

        public Criteria andExtend6GreaterThanOrEqualTo(String value) {
            addCriterion("extend6 >=", value, "extend6");
            return (Criteria) this;
        }

        public Criteria andExtend6LessThan(String value) {
            addCriterion("extend6 <", value, "extend6");
            return (Criteria) this;
        }

        public Criteria andExtend6LessThanOrEqualTo(String value) {
            addCriterion("extend6 <=", value, "extend6");
            return (Criteria) this;
        }

        public Criteria andExtend6Like(String value) {
            addCriterion("extend6 like", value, "extend6");
            return (Criteria) this;
        }

        public Criteria andExtend6NotLike(String value) {
            addCriterion("extend6 not like", value, "extend6");
            return (Criteria) this;
        }

        public Criteria andExtend6In(List<String> values) {
            addCriterion("extend6 in", values, "extend6");
            return (Criteria) this;
        }

        public Criteria andExtend6NotIn(List<String> values) {
            addCriterion("extend6 not in", values, "extend6");
            return (Criteria) this;
        }

        public Criteria andExtend6Between(String value1, String value2) {
            addCriterion("extend6 between", value1, value2, "extend6");
            return (Criteria) this;
        }

        public Criteria andExtend6NotBetween(String value1, String value2) {
            addCriterion("extend6 not between", value1, value2, "extend6");
            return (Criteria) this;
        }

        public Criteria andExtend7IsNull() {
            addCriterion("extend7 is null");
            return (Criteria) this;
        }

        public Criteria andExtend7IsNotNull() {
            addCriterion("extend7 is not null");
            return (Criteria) this;
        }

        public Criteria andExtend7EqualTo(String value) {
            addCriterion("extend7 =", value, "extend7");
            return (Criteria) this;
        }

        public Criteria andExtend7NotEqualTo(String value) {
            addCriterion("extend7 <>", value, "extend7");
            return (Criteria) this;
        }

        public Criteria andExtend7GreaterThan(String value) {
            addCriterion("extend7 >", value, "extend7");
            return (Criteria) this;
        }

        public Criteria andExtend7GreaterThanOrEqualTo(String value) {
            addCriterion("extend7 >=", value, "extend7");
            return (Criteria) this;
        }

        public Criteria andExtend7LessThan(String value) {
            addCriterion("extend7 <", value, "extend7");
            return (Criteria) this;
        }

        public Criteria andExtend7LessThanOrEqualTo(String value) {
            addCriterion("extend7 <=", value, "extend7");
            return (Criteria) this;
        }

        public Criteria andExtend7Like(String value) {
            addCriterion("extend7 like", value, "extend7");
            return (Criteria) this;
        }

        public Criteria andExtend7NotLike(String value) {
            addCriterion("extend7 not like", value, "extend7");
            return (Criteria) this;
        }

        public Criteria andExtend7In(List<String> values) {
            addCriterion("extend7 in", values, "extend7");
            return (Criteria) this;
        }

        public Criteria andExtend7NotIn(List<String> values) {
            addCriterion("extend7 not in", values, "extend7");
            return (Criteria) this;
        }

        public Criteria andExtend7Between(String value1, String value2) {
            addCriterion("extend7 between", value1, value2, "extend7");
            return (Criteria) this;
        }

        public Criteria andExtend7NotBetween(String value1, String value2) {
            addCriterion("extend7 not between", value1, value2, "extend7");
            return (Criteria) this;
        }

        public Criteria andExtend8IsNull() {
            addCriterion("extend8 is null");
            return (Criteria) this;
        }

        public Criteria andExtend8IsNotNull() {
            addCriterion("extend8 is not null");
            return (Criteria) this;
        }

        public Criteria andExtend8EqualTo(String value) {
            addCriterion("extend8 =", value, "extend8");
            return (Criteria) this;
        }

        public Criteria andExtend8NotEqualTo(String value) {
            addCriterion("extend8 <>", value, "extend8");
            return (Criteria) this;
        }

        public Criteria andExtend8GreaterThan(String value) {
            addCriterion("extend8 >", value, "extend8");
            return (Criteria) this;
        }

        public Criteria andExtend8GreaterThanOrEqualTo(String value) {
            addCriterion("extend8 >=", value, "extend8");
            return (Criteria) this;
        }

        public Criteria andExtend8LessThan(String value) {
            addCriterion("extend8 <", value, "extend8");
            return (Criteria) this;
        }

        public Criteria andExtend8LessThanOrEqualTo(String value) {
            addCriterion("extend8 <=", value, "extend8");
            return (Criteria) this;
        }

        public Criteria andExtend8Like(String value) {
            addCriterion("extend8 like", value, "extend8");
            return (Criteria) this;
        }

        public Criteria andExtend8NotLike(String value) {
            addCriterion("extend8 not like", value, "extend8");
            return (Criteria) this;
        }

        public Criteria andExtend8In(List<String> values) {
            addCriterion("extend8 in", values, "extend8");
            return (Criteria) this;
        }

        public Criteria andExtend8NotIn(List<String> values) {
            addCriterion("extend8 not in", values, "extend8");
            return (Criteria) this;
        }

        public Criteria andExtend8Between(String value1, String value2) {
            addCriterion("extend8 between", value1, value2, "extend8");
            return (Criteria) this;
        }

        public Criteria andExtend8NotBetween(String value1, String value2) {
            addCriterion("extend8 not between", value1, value2, "extend8");
            return (Criteria) this;
        }
    }

    /**
     * @Title dbo.alg_equip表的实体类
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
     * @Title dbo.alg_equip表的实体类
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
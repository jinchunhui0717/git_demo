package org.rafael.modules.alg.entity.extend;

import java.util.Date;

import org.rafael.modules.alg.entity.AlgEquip;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlgEquipExtend extends AlgEquip{
	/**
	 * 指派人
	 */
	private String operator;
	private String taskDetailId;
	
	/**
     * @Fields imageurl 
     */
    private String imageurl;
	
	 /**
     * @Fields optStartdate 
     */
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date optStartdate;

    /**
     * @Fields optEnddate 
     */
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date optEnddate;
	/**
	 * 设备状态
	 */
	private String equipState;
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public Date getOptStartdate() {
		return optStartdate;
	}
	public void setOptStartdate(Date optStartdate) {
		this.optStartdate = optStartdate;
	}
	public Date getOptEnddate() {
		return optEnddate;
	}
	public void setOptEnddate(Date optEnddate) {
		this.optEnddate = optEnddate;
	}
	public String getEquipState() {
		return equipState;
	}
	public void setEquipState(String equipState) {
		this.equipState = equipState;
	}
	public String getTaskDetailId() {
		return taskDetailId;
	}
	public void setTaskDetailId(String taskDetailId) {
		this.taskDetailId = taskDetailId;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
}

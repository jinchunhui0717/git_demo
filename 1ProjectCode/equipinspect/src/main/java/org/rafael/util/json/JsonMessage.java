package org.rafael.util.json;
/**
 * 返回json消息格式类
 * @author Rafael
 *
 */
public class JsonMessage {
	private String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	private String text;
	
	private Object extend;
	public Object getExtend() {
		return extend;
	}
	public void setExtend(Object extend) {
		this.extend = extend;
	}
	
}

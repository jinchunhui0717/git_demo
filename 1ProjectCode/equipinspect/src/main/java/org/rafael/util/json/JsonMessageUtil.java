package org.rafael.util.json;

import org.rafael.util.i18n.MessageI18n;


/**
 * 消息字符串统一
 * @author Rafael
 *
 */
public class JsonMessageUtil {
	private static final String successCode = "success";
	private static final String failureCode = "failure";
	
	private static  String successText = MessageI18n.getTextValue("success");
	private static  String failureText = MessageI18n.getTextValue("failure");
	private static  String noDataText = MessageI18n.getTextValue("commit_noany");
	
	public static JsonMessage successMsg(){
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setCode(successCode);
		jsonMessage.setText(successText);
		return jsonMessage;
	}
	
	public static JsonMessage failureMsg(){
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setCode(failureCode);
		jsonMessage.setText(failureText);
		return jsonMessage;
	}
	
	public static JsonMessage noDataMsg(){
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setCode(failureCode);
		jsonMessage.setText(noDataText);
		return jsonMessage;
	}
	/**
	 * 自定义错误信息
	 * @return
	 */
	public static JsonMessage customFailureMsg(String errorText){
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setCode(failureCode);
		jsonMessage.setText(errorText);
		return jsonMessage;
	}
}

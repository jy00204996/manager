package com.zero.system.util;

import java.io.Serializable;

public class ResultBean<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int SUCCESS=1;
	public static final int ERROR=0;
	
	private int state;
	private T data;
	private String message;
	
	public ResultBean(){
		// TODO Auto-generated constructor stub
	}

	public ResultBean(int state, T data, String message) {
		super();
		this.state = state;
		this.data = data;
		this.message = message;
	}
	
//	如果查询出正确的数据，就把这个正确的数据丢给前台 state=1 data就是正确的数据 message就是空
	public ResultBean(T t){
		state = SUCCESS;
		data = t;
		message = "";
	}
	
//	直接抛异常，返回给前台的state就是0 data=null message就是抛出的异常内容
	public ResultBean(Throwable e){
		state = ERROR;
		data=null;
		message = e.getMessage();		
	}
//	通过传制定状态的异常 第一个参数是状态0/1 第二个是获取的异常
	public ResultBean(int state, Throwable e) {
		this.state = state;
		this.message = e.getMessage();
		this.data = null;
	}	
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static int getSuccess() {
		return SUCCESS;
	}

	public static int getError() {
		return ERROR;
	}

	@Override
	public String toString() {
		return "ResultBean [state=" + state + ", data=" + data + ", message=" + message + "]";
	}

	
	
}

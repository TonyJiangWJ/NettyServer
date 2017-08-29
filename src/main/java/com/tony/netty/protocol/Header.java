package com.tony.netty.protocol;


public class Header implements Cloneable{

	private int type;					//消息类型
	private String sessionId;	//回话ID
	private int length;				//数据包长度
	private String userCode; 	//用户编码
	@Override
	public Header clone() {
		try {
			return (Header)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Header(int type, String sessionId, int length, String userCode) {
		this.type = type;
		this.sessionId = sessionId;
		this.length = length;
		this.userCode = userCode;
	}

	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}


	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Override
	public String toString() {
		return "Header [type=" + type + ", sessionId=" + sessionId + ", length=" + length + ", userCode=" + userCode
				+ "]";
	}


	
	
}

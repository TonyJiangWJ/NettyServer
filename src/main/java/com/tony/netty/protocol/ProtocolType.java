package com.tony.netty.protocol;
/**
 * 请求类别
 * @author tonyjiang
 *
 */

public class ProtocolType {
	public final static int PING = 0;						// ping消息
	public final static int PONG = 1;						// 返回确认ping消息
	
	public final static int NEW_CONNECTION = 2;				// 新连接
	public final static int NEW_CONNECION_CONFIRM = 3;		// 确认新连接 客户端同时发USERCODE
	
	public final static int DIS_CONNECTION = 4;				// 断开连接
	public final static int DIS_CONNECTION_CONFIRM = 5;		// 确认断开连接

	public final static int NEW_MESSAGE = 6;				// 新消息请求 这里的消息就是推送的消息
	public final static int NEW_MESSAGE_CONFIRM = 7;		// 确认得到新消息
	
	public final static int RE_CONNECT = 8;					// 请求重新连接
	public final static int RE_CONNECT_CONFIRM = 9;			// 确认已经重新建立连接

	public final static int ON_LINE_CHECK = 11;				// 	查看教室在线用户
	public final static int ON_LINE_CHECK_CONFIRM = 12;		//	查看教室在线用户确认信息

	public final static int UNSIGNED_CLIENT = 13;			// 	未登录客户端
}

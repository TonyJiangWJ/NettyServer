package com.tony.netty.protocol;


public class StandardContent {
    public final static String NEW_CONNECTION_CONTENT = "欢迎！！";
    public final static String NEW_CONNECTION_CONFIRM_CONTENT = "确认已经拿到UserCode";

    public final static String NEW_MESSAGE_CONTENT = "新消息";
    public final static String NEW_MESSAGE_CONFIRM_CONTENT = "新消息";

    public final static String DISCONNECTION_CONTENT = "断开连接";
    public final static String DISCONNECTION_CONFIRM_CONTENT = "确定断开连接";

    public final static String PING_CONTENT = " PING REQUEST ";
    public final static String PONG_CONTENT = " PONG RESPONSE ";

    public final static String RE_CONNECT_CONTENT = "请求重新连接";
    public final static String RE_CONNECT_CONFIRM_CONTENT = "确定重新连接";

    public final static String UNSIGNED_CLIENT = "客户端未登陆";
}

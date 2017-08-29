package com.tony.netty.server;

import com.alibaba.fastjson.JSONObject;
import com.tony.netty.protocol.Header;
import com.tony.netty.protocol.MessageBody;
import com.tony.netty.protocol.ProtocolType;
import com.tony.netty.protocol.StandardContent;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        MessageBody messageBody = (MessageBody) msg;
        int type = messageBody.getHeader().getType();
        switch (type) {
            case ProtocolType.PING:
                pongBack(ctx, messageBody);
                break;
            case ProtocolType.PONG:
                pingSend(ctx, messageBody);
                break;
            case ProtocolType.DIS_CONNECTION:
                dropConnection(ctx, messageBody);
                break;
            case ProtocolType.NEW_MESSAGE_CONFIRM:
                confirmMessage(messageBody.getContent());
                break;
            case ProtocolType.RE_CONNECT:
                reConnect(ctx, messageBody);
                break;
            case ProtocolType.NEW_CONNECION_CONFIRM:
                confirmConnection(ctx, messageBody);
                break;
            case ProtocolType.ON_LINE_CHECK_CONFIRM:
                confirmMessage(messageBody.getContent());
                break;
            default:
                break;
        }
    }

    private void pingSend(ChannelHandlerContext ctx, MessageBody messageBody) {

    }

    private void pongBack(ChannelHandlerContext ctx, MessageBody messageBody) {

    }


    private void dropConnection(ChannelHandlerContext ctx, MessageBody messageBody) {
        logger.info("dropConnection");
    }

    private void confirmMessage(String content) {
    }


    private void updateNewChannel(ChannelHandlerContext ctx, String sessionId, String userCode) {
    }


    private void reConnect(ChannelHandlerContext ctx, MessageBody messageBody) {

        logger.info("重连客户端：{} userCode:{}", ctx.channel().remoteAddress(), messageBody.getHeader().getUserCode());

        updateNewChannel(ctx, messageBody.getHeader().getSessionId(), messageBody.getHeader().getUserCode());
        sendMessage(ctx, ProtocolType.RE_CONNECT_CONFIRM, messageBody.getHeader().getSessionId(),
                StandardContent.RE_CONNECT_CONFIRM_CONTENT, messageBody.getHeader().getUserCode());
    }


    private void confirmConnection(ChannelHandlerContext ctx, MessageBody messageBody) {
        logger.info("新连接的客户端:{} userCode:{}", ctx.channel().remoteAddress(),
                messageBody.getHeader().getUserCode());
        updateNewChannel(ctx, messageBody.getHeader().getSessionId(), messageBody.getHeader().getUserCode());
        sendMessage(ctx, ProtocolType.NEW_CONNECION_CONFIRM, messageBody.getHeader().getSessionId(),
                StandardContent.NEW_CONNECTION_CONFIRM_CONTENT, messageBody.getHeader().getUserCode());
    }


    private MessageBody generatorMsg(int proType, String sessionId, String content, String userCode) {
        Header header = new Header(proType, sessionId, content.getBytes().length, userCode);
        return new MessageBody(header, content);
    }

    private void sendMessage(ChannelHandlerContext ctx, int proType, String sessionId, String content, String userCode) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", content);
        ctx.writeAndFlush(generatorMsg(proType, sessionId, jsonObject.toJSONString(), userCode));
    }

    private MessageBody generatorJMSG(int proType, String sessionId, String content, String userCode) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", content);
        return generatorMsg(proType, sessionId, jsonObject.toJSONString(), userCode);
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);

        logger.info("新客户端连接：{}", ctx.channel().remoteAddress());
        String sessionId = UUID.randomUUID().toString();
        sendMessage(ctx, ProtocolType.NEW_CONNECTION, sessionId, StandardContent.NEW_CONNECTION_CONTENT,
                "11111111112222222222");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        logger.info("客户端离线:{}", ctx.channel().remoteAddress());
        super.handlerRemoved(ctx);
        ctx.close();

    }


}

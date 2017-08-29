package com.tony.netty.codec;

import com.tony.netty.protocol.MessageBody;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewMessageEncoder extends MessageToByteEncoder<MessageBody> {

	private Logger logger = LoggerFactory.getLogger(NewMessageEncoder.class);

	@Override
	protected void encode(ChannelHandlerContext ctx, MessageBody msg, ByteBuf out) throws Exception {

		try {
			//消息头
			out.writeInt(msg.getHeader().getType());
			out.writeBytes(msg.getHeader().getSessionId().getBytes());
			out.writeInt(msg.getHeader().getLength());
			out.writeBytes(msg.getHeader().getUserCode().getBytes());
			//消息内容
			out.writeBytes(msg.getContent().getBytes());
			String ends = "\r\n";
			out.writeBytes(ends.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

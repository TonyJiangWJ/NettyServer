package com.tony.netty.codec;

import com.tony.netty.protocol.Header;
import com.tony.netty.protocol.MessageBody;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class NewMessageDecoder extends ByteToMessageDecoder{

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		try {
			//消息头
			int type = in.readInt();
			byte[] sessionBytes = new byte[36];
			in.readBytes(sessionBytes);
			String sessionId = new String(sessionBytes);
			int length = in.readInt();
			byte[] userCodeBytes = new byte[20];
			in.readBytes(userCodeBytes);
			Header header = new Header(type, sessionId, length,new String(userCodeBytes));
			//消息内容
			byte[] contentBytes = in.readBytes(length).array();
			MessageBody message = new MessageBody(header, new String(contentBytes));

			out.add(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

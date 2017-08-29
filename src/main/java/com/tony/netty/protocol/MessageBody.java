package com.tony.netty.protocol;

import java.io.Serializable;

public class MessageBody implements Serializable {

    private static final long serialVersionUID = 1L;
    private Header header;
    private String content;

    public MessageBody() {
    }

    public MessageBody(Header header, String content) {
        super();
        this.header = header;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message [ " + header.toString() + ", content=" + content + "]";
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

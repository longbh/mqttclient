package com.fomaly.tcp.fragment;

/**
 * Created by longbh on 2020/1/9.
 */
public class BytesIFrame implements IFrame<byte[]> {

    byte[] content;
    private String name;

    public BytesIFrame(byte[] content, String name) {
        this.content = content;
        this.name = name;
    }

    public byte[] encode() {
        return content;
    }

    public byte[] decode() {
        return content;
    }

    @Override
    public String name() {
        return name;
    }
}

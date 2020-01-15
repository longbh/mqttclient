package com.fomaly.tcp.fragment;

import java.io.UnsupportedEncodingException;

/**
 * Created by longbh on 2020/1/9.
 */
public class Utf8IFrame implements IFrame<String> {

    String content;
    String name;
    byte[] frameBytes;

    public Utf8IFrame(String content, String name) {
        this.content = content;
        this.name = name;
        try {
            frameBytes = content.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public byte[] encode() {
        return frameBytes;
    }

    public String decode() {
        return new String(frameBytes);
    }

    @Override
    public String name() {
        return name;
    }
}

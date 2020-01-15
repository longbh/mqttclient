package com.fomaly.tcp.fragment;

import com.fomaly.tcp.utils.ByteReader;

/**
 * Created by longbh on 2020/1/9.
 */
public class HexIFrame implements IFrame<String> {

    String content;
    private String name;

    public HexIFrame(String hex, String name) {
        this.content = hex;
        this.name = name;
    }

    public byte[] encode() {
        return ByteReader.hexStringToByte(content);
    }

    public String decode() {
        return content;
    }

    @Override
    public String name() {
        return name;
    }
}

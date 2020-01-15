package com.fomaly.mqtt.code;

import com.fomaly.mqtt.common.Message;

/**
 * Created by longbh on 2019/12/30.
 */
public class Connect implements Message {
    public int messageType() {
        return 0;
    }

    public int messageId() {
        return 0;
    }

    public Message decode() {
        return null;
    }

    @Override
    public void decode(byte[] bytes) {

    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }
}

package com.fomaly.mqtt.code;

import com.fomaly.mqtt.common.Message;
import com.fomaly.mqtt.enume.Qos;

/**
 * Created by longbh on 2020/1/8.
 */
public class Topic implements Message {

    public String name;
    public Qos qos;

    public void decode(byte[] bytes) {

    }

    public byte[] encode() {
        return new byte[0];
    }
}

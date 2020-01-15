package com.fomaly.mqtt.code;

import com.fomaly.mqtt.common.Message;
import com.fomaly.mqtt.enume.MessageType;
import com.fomaly.mqtt.enume.Qos;

/**
 * 固定头
 * Created by longbh on 2019/12/29.
 */
public class FixHeader implements Message {

    //消息类型
    public MessageType messageType;
    //是否重发
    public Boolean dup;
    //发送类型
    public Qos qos;
    //是否存储
    public Boolean retain;
    //剩余长度
    public Integer remainingLength;

    public void decode(byte[] bytes) {

    }

    public byte[] encode() {

        return new byte[0];
    }

}

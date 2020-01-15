package com.fomaly.mqtt.common;

/**
 * Created by longbh on 2019/12/29.
 */
public interface Message {

    void decode(byte[] bytes);

    byte[] encode();

}

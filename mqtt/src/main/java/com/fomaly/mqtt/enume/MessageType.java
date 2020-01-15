package com.fomaly.mqtt.enume;

/**
 * Created by longbh on 2019/12/29.
 */
public enum MessageType {

    Connect(1, "Connect"),
    Connack(2, "Connack"),
    Publish(3, "Publish"),
    Puback(4, "Puback"),
    Pubrec(5, "Pubrec"),
    Pubrel(6, "Pubrel"),
    Pubcomp(7, "Pubcomp"),
    Subscribe(8, "Subscribe"),
    Suback(9, "Suback"),
    Unsubscribe(10, "Unsubscribe"),
    Unsuback(11, "Unsuback"),
    Pingreq(12, "Pingreq"),
    Pingresp(13, "Pingresp"),
    Disconnect(14, "Disconnect");

    private short type;
    private String name;

    MessageType(int type, String name) {
        this.type = (short) type;
        this.name = name;
    }

}

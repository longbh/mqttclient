package com.fomaly.tcp.enume;

/**
 * Created by longbh on 2020/1/8.
 */
public enum ConnectState {

    DEFAULT(0, "DEFAULT"),
    CONNECTING(1, "CONNECTING"),
    CONNECTED(2, "CONNECTED"),
    DISCONNECT(3, "DISCONNECT"),
    CLOSE(4, "CLOSE");

    public int state;
    public String name;

    ConnectState(int state, String name) {
        this.state = state;
        this.name = name;
    }

}

package com.fomaly.tcp.provider;

import com.fomaly.tcp.Options;
import com.fomaly.tcp.listener.MessageListener;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by longbh on 2020/1/9.
 */
public abstract class AbstractProvider implements Runnable {

    protected InetAddress localhost;
    protected int receiveBufferSize = 65536;
    protected int sendBufferSize = 65536;
    protected int trafficClass = 8;

    public abstract void init(Options options) throws IOException;

    public abstract void connect() throws IOException;

    public abstract void disconnect() throws IOException;

    public abstract void close() throws IOException;

    public abstract int send(byte[] content) throws IOException;

    public abstract void setMessageListener(MessageListener messageListener);

    public abstract void start();

    public synchronized InetAddress getLocalHost() throws UnknownHostException {
        if (localhost == null) {
            localhost = InetAddress.getLocalHost();
        }
        return localhost;
    }

}

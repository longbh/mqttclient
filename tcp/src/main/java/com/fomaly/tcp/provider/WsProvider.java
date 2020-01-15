package com.fomaly.tcp.provider;

import com.fomaly.tcp.Options;
import com.fomaly.tcp.listener.MessageListener;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;

/**
 * Created by longbh on 2020/1/8.
 */
public class WsProvider extends AbstractProvider {

    public void init(Options options) throws IOException {

    }

    public void connect() throws IOException {

    }

    public void disconnect() throws IOException {

    }

    public void close() throws IOException {

    }

    public byte[] receive() throws IOException {
        return new byte[0];
    }

    public int send(byte[] content) throws IOException {

        return -1;
    }

    @Override
    public void setMessageListener(MessageListener messageListener) {

    }

    @Override
    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {

    }
}

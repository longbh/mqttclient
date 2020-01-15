package com.fomaly.tcp.provider;

import com.fomaly.tcp.Options;
import com.fomaly.tcp.listener.MessageListener;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by longbh on 2020/1/8.
 */
public class UdpProvider extends AbstractProvider{

    protected DatagramChannel channel;
    private Options options;

    public UdpProvider() {
        try {
            this.channel = DatagramChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init(Options options) throws IOException {
        //非阻塞
        this.options = options;
        this.channel.configureBlocking(false);
        DatagramSocket socket = this.channel.socket();
        socket.bind(new InetSocketAddress(options.getClientPort()));
        socket.setReuseAddress(true);
        socket.setTrafficClass(this.trafficClass);
        //缓冲区设置
        socket.setReceiveBufferSize(this.receiveBufferSize);
        socket.setSendBufferSize(this.sendBufferSize);
    }

    public void connect() throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress(options.getIp(), options.getPort());
        this.channel.connect(socketAddress);
    }

    public void disconnect() throws IOException {
        this.channel.disconnect();
    }

    public void close() throws IOException {

    }

    public byte[] receive() {
        ByteBuffer buffer = ByteBuffer.allocate(options.getReceiveBufferSize());
        try {
            int bytesRead = channel.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int send(byte[] content) {

        return -1;
    }

    @Override
    public void setMessageListener(MessageListener messageListener) {

    }

    public synchronized InetAddress getLocalHost() throws UnknownHostException {
        if (localhost == null) {
            localhost = InetAddress.getLocalHost();
        }
        return localhost;
    }

    @Override
    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {

    }
}

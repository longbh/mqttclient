package com.fomaly.tcp.dispatch;

import com.fomaly.tcp.Options;
import com.fomaly.tcp.fragment.IFrame;
import com.fomaly.tcp.listener.ConnectListener;
import com.fomaly.tcp.listener.MessageListener;
import com.fomaly.tcp.listener.SendListener;
import com.fomaly.tcp.provider.AbstractProvider;
import com.fomaly.tcp.provider.TcpProvider;
import com.fomaly.tcp.provider.UdpProvider;
import com.fomaly.tcp.provider.WsProvider;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 消息调度器
 * Created by longbh on 2020/1/12.
 */
public class Dispatcher {

    private ExecutorService executorService = Executors.newFixedThreadPool(3);
    private Queue<IFrame> iFrameQueue = new ConcurrentLinkedDeque<>();
    //连接处理器
    private AbstractProvider abstractProvider;

    public void tcp() {
        abstractProvider = new TcpProvider();
    }

    public void udp() {
        abstractProvider = new UdpProvider();
    }

    public void ws() {
        abstractProvider = new WsProvider();
    }

    public void options(Options options) throws IOException {
        abstractProvider.init(options);
    }

    public void connect() throws IOException {
        abstractProvider.connect();
    }

    public void start() throws IOException {
        executorService.submit(abstractProvider);
    }

    public void connect(ConnectListener connectListener) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    abstractProvider.connect();
                    connectListener.success();
                } catch (IOException e) {
                    e.printStackTrace();
                    connectListener.fail();
                }
            }
        });
    }

    public void disConnect() throws IOException {
        abstractProvider.disconnect();
    }

    public int send(IFrame iFrame) throws IOException {
        return abstractProvider.send(iFrame.encode());
    }

    public void send(IFrame iFrame, SendListener sendListener) {

    }

    public void setMessageListener(MessageListener messageListener) {
        abstractProvider.setMessageListener(messageListener);
    }
}

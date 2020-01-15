package com.fomaly.tcp;

import com.fomaly.tcp.dispatch.Dispatcher;
import com.fomaly.tcp.fragment.BytesIFrame;
import com.fomaly.tcp.fragment.IFrame;
import com.fomaly.tcp.listener.ConnectListener;
import com.fomaly.tcp.listener.DisConnectListener;
import com.fomaly.tcp.listener.MessageListener;
import com.fomaly.tcp.listener.SendListener;
import com.fomaly.tcp.provider.AbstractProvider;
import com.fomaly.tcp.provider.TcpProvider;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by longbh on 2020/1/8.
 */
public class FSocket {

    private static FSocket fSocket;

    public static FSocket init() {
        if (fSocket == null) {
            fSocket = new FSocket();
        }
        return fSocket;
    }

    private Options options;
    private Dispatcher dispatcher;

    public FSocket() {
        this.options = new Options();
    }

    public FSocket options(Options options) throws IOException {
        this.options = options;
        dispatcher.options(options);
        return this;
    }

    public FSocket tcp() {
        dispatcher = new Dispatcher();
        dispatcher.tcp();
        return this;
    }

    public FSocket udp() {
        dispatcher = new Dispatcher();
        dispatcher.udp();
        return this;
    }

    public FSocket ws() {
        dispatcher = new Dispatcher();
        dispatcher.ws();
        return this;
    }

    public FSocket connect() throws IOException {
        dispatcher.connect();
        dispatcher.start();
        return this;
    }

    public FSocket connect(ConnectListener connectListener) {
        return this;
    }

    public Boolean disConnect() throws IOException {
        dispatcher.disConnect();
        return false;
    }

    public void disConnect(DisConnectListener disConnectListener) {

    }

    public int send(IFrame iFrame) throws IOException {
        return dispatcher.send(iFrame);
    }

    public void receive(MessageListener messageListener) {
        dispatcher.setMessageListener(messageListener);
    }

    public void send(IFrame iFrame, SendListener sendListener) {

    }

}

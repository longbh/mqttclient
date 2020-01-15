package com.fomaly.tcp.provider;

import com.fomaly.tcp.Options;
import com.fomaly.tcp.enume.ConnectState;
import com.fomaly.tcp.listener.MessageListener;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by longbh on 2020/1/8.
 */
public class TcpProvider extends AbstractProvider {

    protected SocketChannel channel;
    protected Selector selector;
    private Options options;
    private ConnectState connectState = ConnectState.DEFAULT;
    private MessageListener messageListener;

    public TcpProvider() {
        try {
            this.channel = SocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init(Options options) throws IOException {
        //非阻塞
        this.options = options;
        this.channel.configureBlocking(false);
        Socket socket = this.channel.socket();
        socket.setReuseAddress(true);
        socket.setSoLinger(true, 0);
        socket.setTrafficClass(this.trafficClass);
        socket.setKeepAlive(options.getKeepAlive());

        socket.setTcpNoDelay(true);
        //缓冲区设置
        socket.setReceiveBufferSize(this.receiveBufferSize);
        socket.setSendBufferSize(this.sendBufferSize);
    }

    public void connect() throws IOException {
        connectState = ConnectState.CONNECTING;
        this.selector = Selector.open();
        this.channel.register(selector, SelectionKey.OP_CONNECT);
        InetSocketAddress socketAddress = new InetSocketAddress(options.getIp(), options.getPort());
        this.channel.connect(socketAddress);
    }

    public void disconnect() throws IOException {
        this.channel.finishConnect();
    }

    public void close() throws IOException {
        this.channel.close();
        connectState = ConnectState.CLOSE;
    }

    public int send(byte[] content) {
        ByteBuffer buffer = ByteBuffer.allocate(options.getSendBufferSize());
        buffer.put(content);
        try {
            if (channel.isConnectionPending()) {
                channel.finishConnect();
            }
            int result = channel.write(buffer);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (connectState != ConnectState.CLOSE) {
            try {
                selector.select();

                Iterator ite = this.selector.selectedKeys().iterator();
                while (ite.hasNext()) {
                    SelectionKey key = (SelectionKey) ite.next();
                    // 删除已选的key,以防重复处理
                    ite.remove();
                    // 连接事件发生
                    if (key.isConnectable()) {
                        // 如果正在连接，则完成连接
                        if (channel.isConnectionPending()) {
                            channel.finishConnect();
                        }
                        // 获得了可读的事件
                        channel.register(this.selector, SelectionKey.OP_READ);
                        connectState = ConnectState.CONNECTED;
                    } else if (key.isReadable()) {
                        ByteBuffer buffer = ByteBuffer.allocate(options.getReceiveBufferSize());
                        channel.read(buffer);
                        if (messageListener != null) {
                            messageListener.message(buffer.array());
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setMessageListener(MessageListener messageListener){
        this.messageListener = messageListener;
    }
}

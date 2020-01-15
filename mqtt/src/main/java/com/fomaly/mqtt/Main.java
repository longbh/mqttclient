package com.fomaly.mqtt;

import com.fomaly.tcp.FSocket;
import com.fomaly.tcp.Options;
import com.fomaly.tcp.fragment.Utf8IFrame;
import com.fomaly.tcp.listener.MessageListener;
import com.fomaly.tcp.utils.ByteReader;

import java.io.IOException;

/**
 * start mqtt
 * Created by longbh on 2019/12/29.
 */
public class Main {

    public static void main(String[] args) {
        Options options = new Options();
        options.setIp("127.0.0.1");
        options.setPort(1883);
        try {
            FSocket socket = FSocket.init().tcp().options(options).connect();
            socket.receive(new MessageListener() {
                @Override
                public void message(byte[] message) {
                    System.out.print(new String(message));
                }
            });

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        try {
                            socket.send(new Utf8IFrame("hellooooo","hello"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //socket.disConnect();
    }

}

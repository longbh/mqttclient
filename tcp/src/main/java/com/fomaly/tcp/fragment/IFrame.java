package com.fomaly.tcp.fragment;

/**
 * Created by longbh on 2020/1/9.
 */
public interface IFrame<T> {

    byte[] encode();

    T decode();

    String name();

}

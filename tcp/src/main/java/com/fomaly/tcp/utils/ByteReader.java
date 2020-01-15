package com.fomaly.tcp.utils;

/**
 * Created by longbh on 2020/1/9.
 */
public class ByteReader {

    /**
     * 把16进制字符串转换成字节数组
     *
     * @return byte[]
     */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        for (int i = 0, l = hex.length(); i < l; i += 2) {
            byte b = (byte) Integer.parseInt(hex.charAt(i) + "" + hex.charAt(i + 1), 16);
            result[i / 2] = b;
        }
        return result;
    }

    /**
     * 反转byte数组
     *
     * @param data
     * @return
     */
    public static byte[] reverseBytes(byte[] data) {
        if (data == null) {
            return null;
        }
        byte[] rData = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            rData[rData.length - 1 - i] = data[i];
        }
        return rData;
    }

    /**
     * @param b 字节数组
     * @return 16进制字符串
     * @throws
     * @Title:bytes2HexString
     * @Description:字节数组转16进制字符串
     */
    public static String bytes2HexString(byte[] b) {
        StringBuffer result = new StringBuffer();
        String hex;
        for (int i = 0; i < b.length; i++) {
            hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            result.append(hex.toUpperCase());
        }
        return result.toString();
    }

}

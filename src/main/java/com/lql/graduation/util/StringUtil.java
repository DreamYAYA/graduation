package com.lql.graduation.util;

import java.io.UnsupportedEncodingException;

public class StringUtil {

    public static String byteToString(String byteString) throws UnsupportedEncodingException {

        String[] bytesStr = byteString.split(",");
        byte[] a = new byte[bytesStr.length];
        for(int i=0;i<bytesStr.length;i++){
            a[i]=Byte.parseByte(bytesStr[i]);
        }
        return new String(a,"utf-8").replace("`","\"");
    }
}

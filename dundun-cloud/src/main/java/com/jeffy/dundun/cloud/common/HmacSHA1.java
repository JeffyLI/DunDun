package com.jeffy.dundun.cloud.common;

import org.springframework.stereotype.Repository;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Repository
public class HmacSHA1 {
//    生成128位md5加密
//    public String MD5(String input){
//        String newstr=null;
//        try {
//            MessageDigest md5 = MessageDigest.getInstance("MD5");
//            BASE64Encoder base64en = new BASE64Encoder();
//            newstr = base64en.encode(md5.digest(input.getBytes("utf-8")));
//        }catch (NoSuchAlgorithmException|UnsupportedEncodingException e){
//            e.printStackTrace();
//        }
//        return newstr;
//    }

    public String MD5(String input){
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(input.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String hamcSha1(String data, String key)
    {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            return byte2Hex(mac.doFinal(data.getBytes()));
        } catch (NoSuchAlgorithmException |InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String byte2Hex(byte[] b)
    {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b!=null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }
}

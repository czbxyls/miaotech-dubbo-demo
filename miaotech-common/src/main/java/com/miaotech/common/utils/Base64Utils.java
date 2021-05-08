package com.miaotech.common.utils;

import java.nio.ByteBuffer;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * BASE64工具类
 *
 */
public class Base64Utils {
    
    /**
     * 按UTF-8编码encode该字符串
     * @param input 输入字符串
     * 
     * @return String
     */
    public static String encodeUTF8(String input) {
        return encode(input, CommonUtils.UTF8) ;
    }
    
    /**
     * 按charset编码encode该字符串
     * @param input 输入字符串
     * @param charset 编码
     * 
     * @return String
     */
    public static String encode(String input, String charset) {
        try
        {
        	return encode(input.getBytes(charset)) ;
        } catch(Exception e){throw CommonUtils.illegalStateException(e);}
    }
    
    /**
     * 对字节数组进行encode
     * @param bytes 字节数组
     * @return String 编码后的字符串
     */
    public static String encode(byte[] bytes) {
        return new BASE64Encoder().encode(bytes);
    }
    
    
    /**
     * 对ByteBuffer进行encode
     * @param buf 字节缓存对象
     * @return String 编码后的字符串
     */
    public static String encode(ByteBuffer buf) {
        return new BASE64Encoder().encode(buf);
    }

    
    /**
     * 对输入字符串进行解码，默认UTF8
     * @param input 输入字符串
     * @return 解码后的字符串
     */
    public static String decodeUTF8(String input) {
        try
        {
            return decode(input, CommonUtils.UTF8);
        } catch(Exception e){throw CommonUtils.illegalStateException(e);}
    }
    
    
    /**
     * 对输入字符串进行解码，可以指定编码
     * @param input 输入字符串
     * @param charset 编码
     * @return 解码后的字符串
     */
    public static String decode(String input, String charset) {
        try
        {
            return new String(decode(input), charset) ;
        } catch(Exception e){throw CommonUtils.illegalStateException(e);}
    }
    
    
    /**
     * 对BASE64的字符串进行decode
     * @param input 输入字符串
     * @return byte[] 失败，则返回null
     */
    public static byte[] decode(String input) {
        try
        {
            return new BASE64Decoder().decodeBuffer(input);
        } catch(Exception e){throw CommonUtils.illegalStateException(e);}
    }

    /**
     * 按照UTF-8的方式先对字符串进行Base64加密, 再进行URLEncode
     *
     * @param input
     * @return
     */
    public static String urlEncodeBase64(String input) {
        if(input==null) return null ;
        return CommonUtils.urlEncodeUTF8(Base64Utils.encodeUTF8(input)) ;
    }


    /**
     * 按照UTF-8的方式先对字符串进行URLDecode, 再进行Base64解密
     * @param input
     * @return
     */
    public static String urlDecodeBase64(String input) {
        if(input==null) return null ;
        input = CommonUtils.urlDecodeUTF8(input) ;
        return Base64Utils.decodeUTF8(input) ;
    }

    public static void main(String[] args) {
		String input = "众妙之门" ;
		String output = encodeUTF8(input) ;
		System.out.println("encode input=" + output);
		System.out.println("decode output=" + decodeUTF8(output));
	}
    
}

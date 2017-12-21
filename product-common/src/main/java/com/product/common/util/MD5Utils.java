package com.product.common.util;
import java.io.UnsupportedEncodingException;  
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  
/**
 * 
 *<p>Title: MD5Utils</p>
 * @author yuanst
 * <p>Company:</p>
 * @date 2017年12月21日,上午11:29:42
 * @version 1.0
 */
public class MD5Utils {
	 private static final String MD5_SUFFIX = "_yuansongtao";  
     
	    /** 
	     * 字符加密 
	     * @param str 明文 
	     * @return 返回加了后缀的加密字
	     */  
	    public static String encryptByMD5(String str) {  
	        try {  
	            if (str == null || str.length() < 1 || "0".equals(str))  
	                str = "0";  
	            String tmp = md5(str + MD5_SUFFIX, "UTF-8");  
	           if (null != tmp) {  
	               return replace(tmp, ":", "", -1).toLowerCase();  
	           }  
	        } catch (NoSuchAlgorithmException e) {  
	            e.printStackTrace();  
	        } catch (UnsupportedEncodingException e) {  
	            e.printStackTrace();  
	        }  
	        return "";  
	    }  
	      
	    /** 
	     * md5加密方法，不可转 
	     * @param str 明文 
	     * @param charset 字符编码 
	     * @return 
	     * @throws NoSuchAlgorithmException 
	     * @throws UnsupportedEncodingException 
	     */  
	    public static String md5(String str, String charset)   
	            throws NoSuchAlgorithmException, UnsupportedEncodingException {  
	        byte[] tmpInput = null;  
	        if (null != str) {  
	            if (null != charset) {  
	                tmpInput = str.getBytes(charset);  
	            } else {  
	                tmpInput = str.getBytes();  
	            }  
	        } else {  
	            return null;  
	        }  
	        MessageDigest alg = MessageDigest.getInstance("MD5"); // or "SHA-1"  
	        alg.update(tmpInput);  
	        return byte1hex(alg.digest());  
	    }  
	      
	    /** 
	     * 字节码转换成16进制字符
	     *  
	     * @param inputByte 
	     * @return 
	     */  
	    public static String byte1hex(byte[] inputByte) {  
	        if (null == inputByte) {  
	            return null;  
	        }  
	        String resultStr = "";  
	        String tmpStr = "";  
	        for (int n = 0; n < inputByte.length; n++) {  
	            tmpStr = (Integer.toHexString(inputByte[n] & 0XFF));  
	            if (tmpStr.length() == 1)  
	                resultStr = resultStr + "0" + tmpStr;  
	            else  
	                resultStr = resultStr + tmpStr;  
	            if (n < inputByte.length - 1)  
	                resultStr = resultStr + ":";  
	        }  
	        return resultStr.toUpperCase();  
	    }  
	  
	    public static String replace(String text, String repl, String with, int max) {  
	        if (isEmpty(text) || isEmpty(repl) || with == null || max == 0) {  
	            return text;  
	        }  
	        int start = 0;  
	        int end = text.indexOf(repl, start);  
	        if (end == -1) {  
	            return text;  
	        }  
	        int replLength = repl.length();  
	        int increase = with.length() - replLength;  
	        increase = (increase < 0 ? 0 : increase);  
	        increase *= (max < 0 ? 16 : (max > 64 ? 64 : max));  
	        StringBuffer buf = new StringBuffer(text.length() + increase);  
	        while (end != -1) {  
	            buf.append(text.substring(start, end)).append(with);  
	            start = end + replLength;  
	            if (--max == 0) {  
	                break;  
	            }  
	            end = text.indexOf(repl, start);  
	        }  
	        buf.append(text.substring(start));  
	        return buf.toString();  
	    }  
	      
	    public static boolean isEmpty(String str) {  
	        return str == null || str.length() == 0;  
	}  
}

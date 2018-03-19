package com.product.common.util;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang.StringUtils;

//import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;
/**

 * 工具类
 * @author yuanst
 * 2017-9-15
 */
public class Util {

	/**
	 * yuanst
	 * 判断是否为空值ֵ
	 * @param obj
	 * @return
	 */
	public static  boolean checkNull(Object obj) {
			if (obj == null) {
				return true;
			} else if (obj instanceof String && ("".equals(obj))) {
				return true;
			} else if (obj instanceof Number && ((Number) obj).doubleValue() == 0) {
				return true;
			} else if (obj instanceof Boolean && !((Boolean) obj)) {
				return true;
			} else if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
				return true;
			} else if (obj instanceof Map && ((Map) obj).isEmpty()) {
				return true;
			} else if (obj instanceof Object[] && ((Object[]) obj).length == 0) {
				return true;
			}
			return false;
	}
	
	/**
	 * yuanst
	 * 时间转换（去连接符）  例如：2015-04-03||2015/04/03 =>> 20150403
	 * @param dateString
	 * @return
	 */
	public static String fmtDateRemoveSymbol(String dateString) {
		String fmtdateString = dateString.replaceAll("-", "").replaceAll("/", "");
		return fmtdateString;
	}
	
	/**
	 * yuanst
	 * 时间转换（月年=>年月）  例如：03-2015||04/2015||04/15 =>> 201504
	 * @param dateString
	 * @return
	 */
	public static String transdateMY(String dateString) {
		String result = "";
		if (dateString.contains("/")||dateString.contains("-")) {
			result = dateString.substring(3, dateString.length())
					+ dateString.substring(0, 2);
			return result;
		} else {
			return dateString;
		}
	}
	
	/**
	 * yuanst
	 * 时间转换（日月年=>年月日）  例如：03-04-2015||03/04/2015 =>> 20150403
	 * @param dateString
	 * @return
	 */
	public static String transdateDMY(String dateString) {
		String result = "";
		if (dateString.contains("/")||dateString.contains("-")) {
			result = dateString.substring(6, dateString.length())
					+ dateString.substring(3, 5) + dateString.substring(0, 2);
			return result;
		} else {
			return dateString;
		}
	}
	
	/**
	 * yuanst
	 * 获取时间N天前的日期：例如2天前：2015-03-03=>2015-03-01
	 * @param dateString
	 * @param beforeday
	 * @return
	 */
	public static String getDateNdaysBefore(String dateString,int beforeday) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - beforeday);
		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayBefore;
	}

	
	/**
	 * yuanst
	 * 获取系统当前时间：日月年 时分秒
	 * @return
	 */
	public static String getCurrentDateYMDHMS() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String result = df.format(new Date());
		return result;
	}
	
	
	/**
	 * @author yuanst
	 * 将时间字符串转换成DATE类型
	 * time： 2015-10-10
	 * @param dateString
	 * @return
	 */
	public static Date stringDatetoDate(String dateString) {
		Calendar c = Calendar.getInstance();
		if (dateString.contains("/")||dateString.contains("-")) {
			dateString = dateString.replaceAll("-", "").replaceAll("/", "").replaceAll(" ", "").replaceAll(":", "");
		}
		int year = Integer.valueOf(dateString.substring(0, 4));
		int month = Integer.valueOf(dateString.substring(4, 6));
		int day = Integer.valueOf(dateString.substring(6, 8));
		int hour = Integer.valueOf(dateString.substring(8, 10));
		int min = Integer.valueOf(dateString.substring(10, 12));
		c.set(year, month, day, hour, min);
		return c.getTime();
	}
	
	
	/**
	 * yuanst
	 * 获取系统当前时间：日月年
	 * @return
	 */
	public static String getCurrentDateYMD() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String result = df.format(new Date());
		return result;
	}
	
	/**
	 * 四舍五入保留两位小数
	 * @param d
	 * @return
	 */
	public static Double toround(Double d) {
		BigDecimal b = new BigDecimal(d);  
		d = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		return d;
	}
	
	public static Double toround3(Double d) {
		BigDecimal b = new BigDecimal(d);  
		d = b.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();
		return d;
	}
	
	/**  
     * 设置下载文件中文件的名称  
     * @author haifengwang  
     * @param filename  
     * @param request  
     * @return  
     */    
    public static String encodeFilename(String filename, HttpServletRequest request) {    
      /**  
       * 获取客户端浏览器和操作系统信息  
       * 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)  
       * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6  
       */    
      String agent = request.getHeader("USER-AGENT");    
      try {    
        if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {    
          String newFileName = URLEncoder.encode(filename, "UTF-8");    
          newFileName = StringUtils.replace(newFileName, "+", "%20");    
          if (newFileName.length() > 150) {    
            newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");    
            newFileName = StringUtils.replace(newFileName, " ", "%20");    
          }    
          return newFileName;    
        }    
        if ((agent != null) && (-1 != agent.indexOf("Mozilla")))    
//          return MimeUtility.encodeText(filename, "UTF-8", "B");    
            return filename;    
        return filename;    
      } catch (Exception ex) {    
        return filename;    
      }    
    } 
    
    
/*    *//**
    * Description: 向FTP服务器上传文件
    * @Version1.0 Jul 27, 2008 4:31:09 PM by 崔红保（cuihongbao@d-heaven.com）创建
    * @param url FTP服务器hostname
    * @param port FTP服务器端口
    * @param username FTP登录账号
    * @param password FTP登录密码
    * @param path FTP服务器保存目录
    * @param filename 上传到FTP服务器上的文件名
    * @param input 输入流
    * @return 成功返回true，否则返回false
    *//* 
    public static boolean uploadFile(String url,int port,String username, String password, String path, String filename, InputStream input) { 
        boolean success = false; 
        FTPClient ftp = new FTPClient(); 
        try { 
            int reply; 
            ftp.connect(url, port);//连接FTP服务器 
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器 
            ftp.login(username, password);//登录 
            reply = ftp.getReplyCode(); 
            if (!FTPReply.isPositiveCompletion(reply)) { 
                ftp.disconnect(); 
                return success; 
            } 
            ftp.changeWorkingDirectory(path); 
            ftp.storeFile(filename, input);          
             
            input.close(); 
            ftp.logout(); 
            success = true; 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } finally { 
            if (ftp.isConnected()) { 
                try { 
                    ftp.disconnect(); 
                } catch (IOException ioe) { 
                } 
            } 
        } 
        return success; 
    }*/
    
    
    /**
	 * 判断数值是否为空，方便计算
	 * @param data
	 * @return
	 */
	public static Double checkDouble(Double data) {
		Double rtn = 0.00D;
		if (!Util.checkNull(data)) {
			rtn = data;
		}
		return rtn;
	}
	
	/**
	 * 转为千分位
	 * @param doub
	 * @return
	 */
	public static String qianfenwei(Double doub) {
		DecimalFormat decimalFormat =new DecimalFormat("#,##0.##;(#)");
		return decimalFormat.format(doub);
	}
	
	/**
	 * @author yuanst
	 * 将String转为List
	 * @param dataString
	 * @return
	 */
	public static List<String> stringToList(String dataString) {
		List<String> rtnList = new ArrayList<String>();
		if (dataString != null) {
			for (String part : dataString.split(",")) {
				if (!"".equals(part)) {
					rtnList.add(part);
				}
			}
		}
		return rtnList;
	}
	
	
	/**
	 * @author yuanst
	 * 对以‘，’分隔的字符串去重
	 * @return
	 */
	public static String string_unique(String phoneNumbers,String split) {
		String[] str = phoneNumbers.split(split);
		List<String> list = new ArrayList<String>();  
        for (int i=0; i<str.length; i++) {  
             if(!list.contains(str[i])) {  
                 list.add(str[i]);  
             }  
        }
        //将list转为string。split分隔
        String rtnString = "";//定义返回字符串
        if (list.size()>0) {
        	 for (int j=0; j<list.size(); j++) {  
                 if(!checkNull(list.get(j))) {  
                	 rtnString += list.get(j)+split;
                 }  
             }
		}
	    return rtnString;
	}
	
	
	public static Double rtnValue(Double val) {
		if (Util.checkNull(val)) {
			val = 0D;
		}
		return val;
	}
	/**
	 * 数据保留两位小数不用科学算数
	 */
	public static String formatMath(Double doub){
	    DecimalFormat df1 = new DecimalFormat("0.00");
        return df1.format(doub);  
	}
	/**
	 * 对map进行排序并返回ListOrderedMap
	 */
	 public static Map<String, Object> getMap(Map<String, Object> map){
	       Map<String, Object> orderedMap=(Map<String, Object>)new ListOrderedMap();
	       List<Integer> list=new ArrayList<Integer>();
	       for (Map.Entry<String, Object> entry : map.entrySet()) {  
	           list.add(Integer.valueOf(entry.getKey()));
	       }  
	       Collections.sort(list);
	       for (int i = 0; i < list.size(); i++) {
	           orderedMap.put(String.valueOf(list.get(i)), map.get(String.valueOf(list.get(i))));
	    }
	    return orderedMap;
	       
	   }
}

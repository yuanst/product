package com.product.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;
/**
 * 异常通用类
 *<p>Title: ExceptionUtil</p>
 * @author yuanst
 * <p>Company:</p>
 * @date 2018年1月16日,下午3:52:07
 * @version 1.0
 */
public class ExceptionUtil {

	/**
	 * 获取异常的堆栈信息
	 * 
	 * @param t
	 * @return
	 */
	public static String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		try {
			t.printStackTrace(pw);
			return sw.toString();
		} finally {
			pw.close();
		}
	}
}

package com.product.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.product.common.util.MD5Utils;
import com.product.common.util.Util;
/**
 * 
 *<p>Title: LoginController</p>
 * @author yuanst
 * <p>Company:</p>
 * @date 2017年12月21日,上午11:18:05
 * @version 1.0
 */

@Controller
public class LoginController {
	/**
     * 日志记录器
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    
    @Value("#{config['SEARCH_BASE_URL']}")
    private String SEARCH_BASE_URL;
    
	@RequestMapping("index")
	public String index(){
		return "login";
	}
	 @RequestMapping(value="/login")  
		public String login(HttpServletRequest request) throws Exception{
			String loginName=request.getParameter("loginName");
			if(!Util.checkNull(loginName)){
				loginName=new String(loginName.getBytes("ISO-8859-1"),"UTF-8");
			}
			String password=request.getParameter("password");
			//String passwordMD5=MD5Utils.encryptByMD5(password);
			UsernamePasswordToken token = new UsernamePasswordToken(loginName, password); 
		        Subject subject = SecurityUtils.getSubject();  
		        try {
					subject.login(token);
				} catch(UnknownAccountException uae){  
					SecurityUtils.getSubject().logout();
		            LOGGER.warn("对用户[" + loginName + "]进行登录验证..验证未通过,未知账户");
		            request.getSession().setAttribute("message_login", "未知账户");  
		        }catch(IncorrectCredentialsException ice){  
		        	SecurityUtils.getSubject().logout();
		        	LOGGER.warn("对用户[" + loginName + "]进行登录验证..验证未通过,错误的凭证");
		            request.getSession().setAttribute("message_login", "密码不正确");  
		        }catch(ExcessiveAttemptsException eae){  
		        	SecurityUtils.getSubject().logout();
		        	LOGGER.warn("对用户[" + loginName + "]进行登录验证..验证未通过,错误次数过多");
		        	
		            request.getSession().setAttribute("message_login", "用户名或密码错误次数过多");  
		        }catch(AuthenticationException ae){  
		        	//通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
		        	LOGGER.warn("对用户[" + loginName + "]进行登录验证..验证未通过,堆栈轨迹如下");
		            ae.printStackTrace();  
		            request.getSession().setAttribute("message_login", "用户名或密码不正确");  
		        }  
				if(subject.isAuthenticated()){  
		        	System.out.println("认证通过");
		            return "index";
		        }else{  
		            token.clear();
		            return "login";
		        }  
		}
	 
	 @RequestMapping("/ces")
		public String showCart(HttpServletRequest request) {
			return "ces";
		}
		
		@RequestMapping("/cs")
		public String cs( HttpServletRequest request) {
			System.out.println(SEARCH_BASE_URL);
			return "redirect:/ces.html";
		}
}

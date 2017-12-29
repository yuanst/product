<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>login</title>

</head>
<body background="blue">
  
	   
		 <div class="Login" align="center">	
          <form action="${ctx}/token" method="post">
       	   用户：<input value="请输入用户名" name="loginName"><br>
         	 密码：<input type="password" name="password"><br><br>
          <input type="reset" value="重置">&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="submit" value="提交">
          </form>
         </div>
	
	
</body>
</html>
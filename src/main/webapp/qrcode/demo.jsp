<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<title>二维码测试</title>
</head>

<body>
	<form action="<%=basePath %>servlet/qrcode" method="get">
    	<h1>二维码生成测试</h1>
    	请输入需要转换为二维码的文本：
		  <input type="text" name="message" value="http://baike.baidu.com">
		<input type="submit" value="生成二维码">
	</form>
    <br />
    <p><h1>系统集成方法:</h1>直接使用这个链接作为图片src链接即可，如下所示：</p>
    <p><img src="http://localhost:8080/etoma/servlet/qrcode?message=baike.baidu.com" /></p>
</body>
</html>

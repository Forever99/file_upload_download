<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>

<!DOCTYPE HTML PUBLIC "-//W3C//Dth HTML 4.01 Transitional//EN">
<html>
  <head>

  </head>
  
  <body>
<table align="center" border="1">
<tr>
<th>上传者</th><th>上传文件名</th><th>上传时间</th><th>文件位置</th><th>文件描述</th><th>下载</th>
</tr>
<c:forEach var="file" items="${list }">
<tr>
<td>${file.username }</td>
<td>${file.filename }</td>
<td>${file.uploadtime }</td>
<td>${file.savepath }</td>
<td>${file.description }</td>
<td><font color="red"><a href="${pageContext.request.contextPath }/download?id=${file.id}">下载</a></font></td>
</tr>
</c:forEach>

</table>

  </body>
</html>

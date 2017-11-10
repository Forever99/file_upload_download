<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
<!-- 写法错误 -->
<!--       <a href="${PageContext.request.contextPath }/upload.jsp">上传文件</a><br/> -->
      <a href="${pageContext.request.contextPath }/upload.jsp">上传文件</a><br/>
<!--     <a href="/file_upload_download/upload.jsp">上传文件</a><br/> -->
    <a href="${pageContext.request.contextPath }/listFile">查询文件</a><br/>
  </body>
</html>

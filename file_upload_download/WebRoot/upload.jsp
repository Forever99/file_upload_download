<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

  </head>
  
  <body>
 <h1> 文件上传</h1>
 <!-- 

private String savepath;     //记住文件的位置
private Date uploadtime;     //文件的上传时间
private String description;  //文件的描述
 
  -->
 <form action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data" method="post">
 <table>
 <tr>
 <td>上传文件名</td><td><input type="file" name="filename"></td>
 </tr>
  <tr>
 <td>上传者</td><td><input type="text" name="username"></td>
 </tr>
  <tr>
 <td>上传描述</td><td><textarea name="description" rows="5" cols="30"></textarea></td>
 </tr>
  <tr>
  
  <tr>
 <td colspan="2" align="center"><input type="submit" value="提交" > <input type="reset" value="重置"></td>
 </tr>
 
 </table>
 </form>
 
  </body>
</html>

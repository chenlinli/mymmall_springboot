<%--
  Created by IntelliJ IDEA.
  User: CL
  Date: 2019/8/13
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

springmvc上传文件
<form name="form1" action="/manage/product/upload.do" method="post" enctype="multipart/form-data">
    <input name="upload_file" type="file"/><br>
    <input type="submit" value="upload"><br>
</form>
<hr>
富文本文件上传
<form name="form1" action="/manage/product/richtext_img_upload.do" method="post" enctype="multipart/form-data">
    <input name="upload_file" type="file"/><br>
    <input type="submit" value="upload"><br>
</form>
</body>
</html>

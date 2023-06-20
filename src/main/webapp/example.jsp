<%@page pageEncoding="UTF-8" contentType="text/html"%>
<html>
<head>
  <!-- meta charset="utf-8" -->
  <title>JSP Example</title>
  <link rel="stylesheet" href="./styles.css" type="text/css"/>
</head>
<body>
<div class="box"> Текущее время <%=new java.util.Date() %> </div>
<div> Host <%= request.getRemoteHost() %> </div>
</body>
</html>
<%@page pageEncoding="UTF-8" contentType="text/html"%>
<html>
<head>
  <title>JSP Example as index</title>
  <link rel="stylesheet" href="./static/styles.css" type="text/css"/>
</head>
<body>
<div class="box"> Текущее время <%=new java.util.Date() %> </div>
<div> Host <%= request.getRemoteHost() %> </div>
 <div class="main">
  <a href="./today"> Заполнить журнал событий сегодня </a>
 </div>
</body>
</html>
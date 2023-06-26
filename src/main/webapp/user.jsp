<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
  <meta charset="utf-8">
  <title>Backing bean example</title>
  </head>
<body>
<jsp:useBean id="u" class="ru.inspired.model.User"></jsp:useBean>
<jsp:setProperty property="*" name="u"/>

Record:<br>
<jsp:getProperty property="name" name="u"/><br>
<jsp:getProperty property="password" name="u"/><br>

</body>
</html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@page import="ru.inspired.model.MotivationEvent"%>
<%@page errorPage="error.jsp" %>
<html>
<head>
  <title>Current results</title>
  <link rel="stylesheet" href="./static/styles.css" type="text/css"/>
  </head>
<body>
    <h1> Pезультат на <%=new java.util.Date() %> </h1>

    <c:if test="${yesterday == null} or {$now ==null}">
          <c:redirect url="/error.jsp"/>
    </c:if>
    <div> Вчера было <c:out value="${yesterday}"/></div>
    <div class="main">  Баланс на сегодня: <c:out value="${now}"/></div>

</body>
</html>
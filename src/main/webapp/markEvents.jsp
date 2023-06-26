<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@page import="ru.inspired.model.MotivationEvent"%>
<%@page errorPage="error.jsp" %>
<html>
<head>
  <meta charset="utf-8">
  <title>JSTL Example</title>
  </head>
<body>
<form method="post" action='<c:url value="/today/jsp" />'>
    <h1> Заполняем результат за <%=new java.util.Date() %> </h1>
    <ul>
        <c:if test="${events == null}">
              <c:redirect url="/error.jsp"/>
        </c:if>
        <c:forEach var="event" items="${events}">
                    <li> <c:out value="${event.description}"/> <input type="checkbox" name="${event.id}"/></li>
        </c:forEach>
    </ul>
    <input type="submit"/>
</form>
</body>
</html>
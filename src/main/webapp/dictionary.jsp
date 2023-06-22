<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@page import="ru.inspired.dictionary.model.Person"%>
<html>
<head>
  <meta charset="utf-8">
  <title>Dictionary</title>
  <link rel="stylesheet" href="./styles.css" type="text/css"/>
</head>
<body>
<form method="post" action="jsp">
    <h1> Заполняем результат за <%=new java.util.Date() %> </h1>
    <ul>
        <c:forEach var="event" items="${records}">
                    <li> <c:out value="${event.description}"/> <input type="checkbox" name="${event.id}"/></li>
        </c:forEach>
    </ul>
    <input type="submit"/>
</form>
</body>
</html>
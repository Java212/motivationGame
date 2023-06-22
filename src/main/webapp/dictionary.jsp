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
    <h1> Таблица </h1>
    <table border="1" cellspacing="0" cellpadding="2">
       <tr>
         <th>Name</th>
         <th>Surname</th>
         <th>Phone</th>
       </tr>
       <c:forEach var="record" items="${records}">
           <tr>
                  <td> <c:out value="${record.getName()}"/> </td>
                  <td> <c:out value="${record.getSurname()}"/> </td>
                  <td> <c:out value="${record.getPhone()}"/> </td>
           </tr>
       </c:forEach>
    </table>
</body>
</html>
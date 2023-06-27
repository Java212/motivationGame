<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ru.inspired.web.ListUser"%>

<html>
<head>
  <meta charset="utf-8">
  <title>JSTL Example</title>
  </head>
<body>
    <table border="1" cellspacing="0" cellpadding="3">
        <thead>
            <tr>
               <td>Name </td>
               <td>Surname</td>
                <td>Telefon</td>
            </tr>
        </thead>
            <tbody>
                    <c:forEach var="lineData" items="${data}" >
                        <tr>
                              <td>${lineData.name} </td>
                               <td>${lineData.surname} </td>
                               <td>${lineData.telefon} </td>
                         </tr>
                    </c:forEach>
            </tbody>
     </table>
</body>
</html>
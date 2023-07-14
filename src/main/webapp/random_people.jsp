<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
  <meta charset="utf-8">
  <title>HomeWork JSTL + JSP</title>
   <link rel="stylesheet" href="./main.css" type="text/css"/>

</head>
<body>
   <c:forEach var="people" items="${peoples}">
           <div class = "table">
                       <div class="table_content">
                            <c:out value="${people}" />
                       </div>

           </div>
           </c:forEach>
</body>
</html>
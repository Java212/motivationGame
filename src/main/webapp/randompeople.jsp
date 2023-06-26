<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
  <meta charset="utf-8">
  <title>HomeWork JSTL + JSP</title>
   <link rel="stylesheet" href="./main.css" type="text/css"/>

</head>
<body>
<jsp:useBean id="random" class="java.util.Random" scope="application" />



        <c:forEach var="user" items="${users}">
        <div class = "table">
                    <div class="table_content">
                         <c:out value="${user}" />
                    </div>
                    <div class="table_content">
                         <c:out value=" тел. " />
                         <c:out value="${random.nextInt(100, 1000)}-" />
                         <c:out value="${random.nextInt(10, 100)}-" />
                         <c:out value="${random.nextInt(10, 100)}" />
                    </div>
        </div>
        </c:forEach>


</form>
</body>
</html>
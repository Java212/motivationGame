<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.inspired.model.Note" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<head>
    <title>Notebook</title>
    <link rel="stylesheet" href="./static/styles.css" type="text/css"/>
</head>
<body>

<h1>Заметки</h1>
<form action="notes" method="post">
  <input type="text" name="text" placeholder="Текст для заметок" />
  <input type="submit" value="Добавить заметку" />
</form>

<h2>Список заметок:</h2>
 <ul>
        <c:forEach var="note" items="${notes}">
          <li>
              <div class="box">
                <c:out value="${note.text}"/>
              </div>
              <div class="small">
                <c:out value="${note.createdTimeAsStr}"/>
              </div>
          </li>
        </c:forEach>
    </ul>
</body>
</html>

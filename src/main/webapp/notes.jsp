<%@ page import="ru.inspired.model.Notes" %>
<%@ page import="ru.inspired.web.NotesController" %><%--
  Created by IntelliJ IDEA.
  User: mrfro
  Date: 06.07.2023
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Title</title>
</head>
<body>
<h1>Заметки</h1>
<form action="notes" method="post">
  <input type="text" name="text" placeholder="Текст для заметок" required>
  <input type="submit" value="Добавить заметку">
</form>
<h2>Список заметок:</h2>
<ul>
  <% for (Notes note : NotesController.notes) { %>
    <li><%= note.getText() %> - <%= note.getTime() %></li>
  <% } %>
</ul>
</body>
</html>

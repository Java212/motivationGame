<%@ page import="Notes.model.Notes" %>
<%@ page import="Notes.NotesController" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

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


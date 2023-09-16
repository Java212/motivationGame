<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Случайные люди</title>
</head>
<body>
    <h1>Случайные люди</h1>
    <table>
        <thead>
            <tr>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Телефон</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="person" items="${peopleList}">
                <tr>
                    <td>${person.name}</td>
                    <td>${person.surname}</td>
                    <td>${person.phoneNumber}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>

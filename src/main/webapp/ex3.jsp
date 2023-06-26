<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="ru.inspired.MotivationScoreCalc, ru.inspired.model.MotivationEvent, ru.inspired.model.DailyLog, ru.inspired.model.CompletionStatus"%>
<html>
<head>
  <meta charset="utf-8">
  <title>JSP useBean example</title>
  </head>
<body>
<jsp:useBean id="calc" class="ru.inspired.MotivationScoreCalc"/>
<%! java.util.List<DailyLog> list = java.util.Arrays.asList(new DailyLog(new MotivationEvent(1,"smth",1,1),CompletionStatus.DONE)); %>
<%
  int result = calc.calculateScore(list);
  out.println(result);
%>
</body>
</html>
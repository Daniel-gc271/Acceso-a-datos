<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%! String val="HOLA MUNDO";%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Hola Mundo</h1>
<p>La fecha actual en el servidor es <%=new java.util.Date() %></p>
<h2><%=val%></h2>
</body>
</html>

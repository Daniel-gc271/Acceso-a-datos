<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  
    
    
    
    
    
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<%
Integer accesos =(Integer) session.getAttribute("accesos");
if (accesos==null) {
    accesos=0;
}
accesos++;
session.setAttribute("accesos", accesos);

%>  
<form action="">
	<input type="submit" name="invalidaSesion" value="Invalidar sesion"/>
	<input type="submit" name="Recargar" value="Recargar pagina"/>


</form>
<br>Contador: <%=accesos.intValue() %>

</body>
</html>
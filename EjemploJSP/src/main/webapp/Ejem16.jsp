<!DOCTYPE html>
<!-- Ejemplo16.html -->
<%@page import="java.util.Date"%>
<%!


int numero=0;
public String fecha() {
	return new Date().toGMTString();
	
}

%>
<html>
<head>
 <meta charset="utf-8">
 <title>EJEMPLO 16</title>
</head>

<body>
<h1>Tabka contdor</h1>

<table border =2>
<% for (int i = numero; i<numero+10;i++) {%>
<tr>
<td>Numero</td>
<td><%= i %> </td>
</tr>
<%}
numero +=10;
%>

</table>

<p></p> La fecha actual es: <%= fecha() %> 


</body>

</html>
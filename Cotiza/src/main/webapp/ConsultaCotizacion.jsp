<%@page import="java.util.Map"%>
<%@page import="entity.Cotizacion"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>COTIZA GF</h1>

<table border="1">
<tr>
				<td>Ciclo</td>
				<td>Valor Anterior</td>
				<td>Valor Actual</td>
				<td>Condicion</td>
			</tr>
<%
HashMap<String, Cotizacion> cotizaciones = (HashMap<String, Cotizacion>) application.getAttribute("listaCotizacion");

if (cotizaciones != null) {
    for (Map.Entry<String, Cotizacion> c : cotizaciones.entrySet()) {
%>
    <tr>
        <td><%= c.getKey() %></td>
        <td><%= c.getValue().getAnterior() %></td>
        <td><%= c.getValue().getActual() %></td>
        <td><%= c.getValue().getEvaluacion() %></td>
    </tr>
<%
    }
}
%>
		</table>
</body>
</html>
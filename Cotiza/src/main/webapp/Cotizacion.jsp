
<%@page import="entity.Cotizacion"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cotización GF</title>
</head>
<%
String fechaActual = application.getAttribute("fechaActual") != null ? (String)application.getAttribute("fechaActual") : "";
String deshabilitar = application.getAttribute("acceso_" + fechaActual) != null
		&& (Integer) application.getAttribute("acceso_" + fechaActual) > 0 ? "disabled" : "";
%>
<body>
	<form action="ServletCotizacion" method="post">
		<h1>COTIZAGF S.L.</h1>
		<h2>La sesión está activa por el usuario: <%= session.getAttribute("user") %></h2>
		<h2>Fecha nueva cotización: <%= fechaActual %></h2>
		
		<table border="1">
			<tr>
				<td>Ciclo</td>
				<td>Valor Anterior</td>
				<td>Valor Nuevo</td>
			</tr>
<%
HashMap<String, Cotizacion> cotizaciones = (HashMap<String, Cotizacion>) application.getAttribute("listaCotizacion"); 
if (cotizaciones != null) {
	for (String key : cotizaciones.keySet()) {
%>
			<tr>
				<td><%= key %></td>
				<td><%= cotizaciones.get(key).getAnterior() %></td>
				<td><input type="number" step="0.01" name="<%= key %>" value="" <%= deshabilitar %>></td>
			</tr>
<%
	}
}
%>
		</table>
		<br>

		<p>La bolsa de cotización GF está cerrada en el día de hoy con los
			valores indicados en la tabla hasta nueva actualización.</p>
	
		<input type="submit" name="boton" value="Enviar" <%= deshabilitar %>>
		<input type="submit" name="boton" value="Volver">
	</form>
</body>
</html>

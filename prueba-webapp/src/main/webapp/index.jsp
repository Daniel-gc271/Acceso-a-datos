<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Demostración Maven + JSTL</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 40px;
	background-color: #f4f4f9;
}

.box {
	background: white;
	padding: 25px;
	border-radius: 8px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
	width: 400px;
}

h2 {
	color: #2c3e50;
}

ul {
	list-style: square;
	color: #3498db;
}

li span {
	color: #333;
}
</style>
</head>
<body>
	<div class="box">
		<h2>Prueba de JSTL con Maven</h2>
		<p>
			Lista de tecnologías procesada mediante
			<code>&lt;c:forEach&gt;</code>
			:
		</p>

		<%-- Definición de una lista en el ámbito de la página mediante JSTL --%>
		<c:set var="listaTexto"
	value="Apache Maven,Jakarta EE,JSP & JSTL,Apache Tomcat" />
<c:set var="tecnologias" value="${fn:split(listaTexto, ',')}" />

		<ul>
			<c:forEach var="tech" items="${tecnologias}">
				<li><span><c:out value="${tech}" /></span></li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>
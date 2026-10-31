<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="ServletDespachador" method="post">
		<table>
			<tr>
				<td colspan="2">
					<h2>Pagina de Acceso</h2>
				</td>
			</tr>
			<tr>
				<td><b>Usuario</b></td>
				<td><input type="text" name="user" /></td>
			</tr>
			<tr>
				<td><b>Clave</b></td>
				<td><input type="password" name="pass" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Entrar" /></td>
			</tr>
		</table>
	</form>
</body>
</html>





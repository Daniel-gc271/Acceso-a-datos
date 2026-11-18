<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Registro" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Resultados - Acceso a Datos</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f4f4f9; }
        h2 { color: #333; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; background: white; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        th, td { padding: 12px; border: 1px solid #ddd; text-align: left; }
        th { background-color: #007bff; color: white; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        .btn-volver { display: inline-block; margin-top: 20px; padding: 10px 15px; background: #28a745; color: white; text-decoration: none; border-radius: 4px; }
        .btn-volver:hover { background: #218838; }
    </style>
</head>
<body>
    <h2>Listado de Registros (Vista Dinámica)</h2>
    
    <table>
        <thead>
            <tr>
                <th>Dato 1</th>
                <th>Dato 2</th>
                <th>Dato 3</th>
                <th>Dato 4</th>
                <th>Dato 5</th>
                <th>Dato 6</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Registro> lista = (List<Registro>) request.getAttribute("listaRegistros");
                if (lista != null && !lista.isEmpty()) {
                    for (Registro reg : lista) {
            %>
            <tr>
                <td><%= reg.getDato1() %></td>
                <td><%= reg.getDato2() %></td>
                <td><%= reg.getDato3() %></td>
                <td><%= reg.getDato4() %></td>
                <td><%= reg.getDato5() %></td>
                <td><%= reg.getDato6() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="6" style="text-align: center;">No hay registros disponibles o el fichero está vacío.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
<div style="margin-top: 20px; display: flex; gap: 15px;">
        <%
            String nombreFichero = (String) request.getAttribute("nombreFichero");
            if (nombreFichero != null) {
        %>
            <!-- El atributo 'download' fuerza la descarga en el navegador -->
            <a href="ficheros/<%= nombreFichero %>" download="<%= nombreFichero %>" class="btn-descargar" style="padding: 10px 15px; background: #007bff; color: white; text-decoration: none; border-radius: 4px;">
                Descargar Fichero (<%= nombreFichero %>)
            </a>
        <%
            }
        %>
        <a href="TratamientoFich.jsp" class="btn-volver" style="padding: 10px 15px; background: #28a745; color: white; text-decoration: none; border-radius: 4px;">
            Volver al Formulario
        </a>
    </div>
</body>
</html>
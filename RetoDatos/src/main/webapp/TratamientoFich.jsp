<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Ficheros - Acceso a Datos</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f4f4f9; }
        .container { max-width: 600px; margin: 0 auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); }
        h2 { color: #333; text-align: center; }
        .form-group { margin-bottom: 20px; }
        label { display: block; margin-bottom: 8px; font-weight: bold; color: #555; }
        input[type="text"], select { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        .radio-group { display: flex; gap: 20px; margin-top: 5px; }
        .radio-group label { font-weight: normal; cursor: pointer; }
        button { width: 100%; background-color: #007bff; color: white; padding: 12px; border: none; border-radius: 4px; font-size: 16px; cursor: pointer; }
        button:hover { background-color: #0056b3; }
        .nota { font-size: 12px; color: red; margin-top: 5px; }
    </style>
</head>
<body>

<div class="container">
    <h2>TRATAMIENTO FICHEROS</h2>
    
    <!-- Petición estándar hacia el Servlet -->
    <form action="ServletFichero" method="POST">
        
        <div class="form-group">
            <label for="formato">Formato del fichero:</label>
            <select id="formato" name="formato" required>
                <option value="XLS">XLS / XLSX</option>
                <option value="CSV">CSV</option>
                <option value="JSON">JSON</option>
                <option value="XML">XML</option>
                <option value="RDF">RDF</option>
            </select>
        </div>

        <div class="form-group">
            <label>¿Qué quiere hacer con el fichero?</label>
            <div class="radio-group">
                <label><input type="radio" name="accion" value="lectura"> Lectura</label>
                <label><input type="radio" name="accion" value="escritura" checked> Escritura</label>
            </div>
            <div class="nota">(*) Los campos no pueden estar vacios si selecciona escritura</div>
        </div>

        <hr style="border: 0; border-top: 1px solid #eee; margin: 20px 0;">

        <div class="form-group"><label for="dato1">DATO1:</label><input type="text" id="dato1" name="dato1"></div>
        <div class="form-group"><label for="dato2">DATO2:</label><input type="text" id="dato2" name="dato2"></div>
        <div class="form-group"><label for="dato3">DATO3:</label><input type="text" id="dato3" name="dato3"></div>
        <div class="form-group"><label for="dato4">DATO4:</label><input type="text" id="dato4" name="dato4"></div>
        <div class="form-group"><label for="dato5">DATO5:</label><input type="text" id="dato5" name="dato5"></div>
        <div class="form-group"><label for="dato6">DATO6:</label><input type="text" id="dato6" name="dato6"></div>

        <button type="submit">Enviar</button>
    </form>
</div>

</body>
</html>
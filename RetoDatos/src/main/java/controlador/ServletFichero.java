package controlador;



import modelo.Registro;
import servicios.IGestorFicheros;
import servicios.GestorCSV;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class ServletFichero
 */
@WebServlet("/ServletFichero")
public class ServletFichero extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Configurar codificación de caracteres para evitar problemas con tildes
        request.setCharacterEncoding("UTF-8");

        String formato = request.getParameter("formato");
        String accion = request.getParameter("accion"); // "lectura" o "escritura"[cite: 3]
        
        String dato1 = request.getParameter("dato1");
        String dato2 = request.getParameter("dato2");
        String dato3 = request.getParameter("dato3");
        String dato4 = request.getParameter("dato4");
        String dato5 = request.getParameter("dato5");
        String dato6 = request.getParameter("dato6");

        // LÓGICA EN JAVA: Validar campos vacíos exclusivamente si la acción es ESCRITURA[cite: 1, 3]
        if ("escritura".equals(accion)) {
            if (esVacio(dato1) || esVacio(dato2) || esVacio(dato3) || 
                esVacio(dato4) || esVacio(dato5) || esVacio(dato6)) {
                // Redirige a la página de error si falta algún campo[cite: 3]
                response.sendRedirect("Error.jsp");
                return;
            }
        }

        try {
            IGestorFicheros gestor = obtenerGestor(formato);
            String nombreFichero = obtenerNombreFichero(formato);
            
            // Ruta física dentro del servidor Tomcat
            File carpeta = new File(getServletContext().getRealPath("/") + "ficheros");
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }
            String rutaAbsoluta = carpeta.getAbsolutePath() + File.separator + nombreFichero;

            // Si es ESCRITURA, creamos el objeto y lo añadimos al fichero mediante Java
            if ("escritura".equals(accion)) {
                Registro nuevoReg = new Registro(dato1, dato2, dato3, dato4, dato5, dato6);
                gestor.escribir(rutaAbsoluta, nuevoReg);
            }

            // Para ambos casos (lectura o post-escritura), leemos el contenido para mostrarlo en la tabla[cite: 3]
            List<Registro> lista = gestor.leer(rutaAbsoluta);
            
            // Adjuntamos los atributos necesarios para la vista JSP de resultados
            request.setAttribute("listaRegistros", lista);
            request.setAttribute("nombreFichero", nombreFichero);
            
            // Redirigimos (forward) a la vista AccesoDatosA.jsp[cite: 2, 3]
            request.getRequestDispatcher("AccesoDatosA.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // Control de excepciones en Java redirigiendo a error[cite: 3]
            response.sendRedirect("Error.jsp");
        }
    }

    private boolean esVacio(String str) {
        return str == null || str.trim().isEmpty();
    }

    private IGestorFicheros obtenerGestor(String formato) {
        // En función del formato devorvemos su gestor correspondiente[cite: 1]
        switch (formato != null ? formato.toUpperCase() : "") {
            case "CSV":
            default:
                return new GestorCSV(); // Ampliable a GestorJSON, GestorXLS, etc.
        }
    }

    private String obtenerNombreFichero(String formato) {
        switch (formato != null ? formato.toUpperCase() : "") {
            case "CSV": return "datos.csv";
            case "JSON": return "datos.json";
            case "XLS":
            case "XLSX": return "datos.xlsx";
            case "XML": return "datos.xml";
            case "RDF": return "datos.rdf";
            default: return "datos.csv";
        }
    }
}

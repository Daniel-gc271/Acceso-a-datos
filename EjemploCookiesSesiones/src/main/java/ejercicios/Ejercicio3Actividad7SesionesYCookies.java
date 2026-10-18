package ejercicios;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Ejercicio3Actividad7SesionesYCookies
 */
@WebServlet("/Ejercicio3Actividad7SesionesYCookies")
public class Ejercicio3Actividad7SesionesYCookies extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private ServletContext contextoServer;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Ejercicio3Actividad7SesionesYCookies() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		this.contextoServer = config.getServletContext();
		System.out.println(contextoServer.getInitParameter("DBConnection"));

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
	    // Configurar codificación para evitar problemas con acentos y caracteres especiales
	    request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html;charset=UTF-8");
	    
	    try (PrintWriter responseWriter = response.getWriter()) {
	        
	        // 1. Validar que la petición venga realmente del botón de enviar del formulario
	        // Usamos getParameter en lugar de getAttribute
	        String enviar = request.getParameter("Enviar");
	        
	        if (enviar != null && enviar.equals("Enviar")) {
	            
	            // 2. Obtener y validar los síntomas (si no se marcan, el navegador envía null)
	            String fiebre = request.getParameter("Fiebre") != null ? "Presente" : "Ausente";
	            String tosSeca = request.getParameter("Tos Seca") != null ? "Presente" : "Ausente";
	            String diarrea = request.getParameter("Diarrea") != null ? "Presente" : "Ausente";
	            String perdidaOlfato = request.getParameter("Perdida de Olfato") != null ? "Presente" : "Ausente";
	            String dolorMuscular = request.getParameter("Dolor Muscular") != null ? "Presente" : "Ausente";
	            
	            // 3. Generar la cabecera de la tabla HTML (Corregido el cierre de etiquetas <tr>)
	            responseWriter.append("<table border='1'>");
	            responseWriter.append("<tr>");
	            responseWriter.append("<th>Sintoma</th>");
	            responseWriter.append("<th>Resultado</th>");
	            responseWriter.append("</tr>");
	            
	            // 4. Filas dinámicas con los resultados
	            agregarFilaSintoma(responseWriter, "Fiebre", fiebre);
	            agregarFilaSintoma(responseWriter, "Tos Seca", tosSeca);
	            agregarFilaSintoma(responseWriter, "Diarrea", diarrea);
	            agregarFilaSintoma(responseWriter, "Pérdida de Olfato", perdidaOlfato);
	            agregarFilaSintoma(responseWriter, "Dolor Muscular", dolorMuscular);
	            
	            // Cierre de la tabla
	            responseWriter.append("</table>");
	        } else {
	            responseWriter.append("<p>Error: No se ha enviado el formulario correctamente.</p>");
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	// Método auxiliar opcional para no repetir código HTML al escribir filas
	private void agregarFilaSintoma(PrintWriter writer, String nombreSintoma, String resultado) {
	    writer.append("<tr>");
	    writer.append("<td>").append(nombreSintoma).append("</td>");
	    writer.append("<td>").append(resultado).append("</td>");
	    writer.append("</tr>");
	}


}

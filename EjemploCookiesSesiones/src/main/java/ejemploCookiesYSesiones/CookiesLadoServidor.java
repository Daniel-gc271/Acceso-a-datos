package ejemploCookiesYSesiones;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Servlet que gestiona una sesión en el servidor y muestra metadatos de la conexión.
 */
@WebServlet("/CookiesLadoServidor")
public class CookiesLadoServidor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public CookiesLadoServidor() {
        super();
    }

	/**
	 * Procesa las peticiones GET. Gestiona los datos guardados en la sesión del servidor.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Establece el tipo de contenido de la respuesta como texto plano
		response.setContentType("text/plain");
		
		// Obtiene la sesión actual. Al pasar 'true', si no existe una sesión previa, crea una nueva.
		HttpSession sesion = request.getSession(true);
		
		// Bloque try-with-resources para gestionar el flujo de salida hacia el cliente
		try (PrintWriter responseWriter = response.getWriter()) {
			
			// Recupera el objeto almacenado bajo la clave "counter" en la sesión
			Object atributoCounter = sesion.getAttribute("counter");
			
			// Si el atributo es nulo significa que es el primer acceso en esta sesión
			responseWriter.append(atributoCounter == null ? "Bienvenido a dam\n" : "Bienvenido de nuevo\n");
			
			// Si es nulo asigna 1 de base, de lo contrario realiza un casting del objeto a primitivo int
			int counter = atributoCounter == null ? 1 : (int) atributoCounter;
			
			// Incrementa el valor en 1 y lo guarda (o actualiza) nuevamente en los atributos de la sesión
			sesion.setAttribute("counter", ++counter);
			
			// Imprime el número de acceso actual en la sesión
			responseWriter.append("Acceso de sesion #" + counter + "\n");
			
			// Obtiene la fecha y hora en la que se creó la sesión en el servidor
			responseWriter.append("Fecha de acceso: " + new Date(sesion.getCreationTime()) + "\n");
			
			// Muestra el identificador único (ID de sesión) asignado a este cliente
			responseWriter.append("El identificador de la sesion fue:" + sesion.getId() + "\n");
			
			// Muestra la fecha y hora de la última interacción del cliente antes de esta petición
			responseWriter.append("El ultimo acceso de la sesion fue:" + new Date(sesion.getLastAccessedTime()) + "\n");
			
			// Muestra el tiempo máximo de inactividad permitido (en segundos) antes de que la sesión expire
			// Nota: El código divide entre 60 pero el texto dice "seg", manteniendo el comportamiento original.
			responseWriter.append("Una sesion puede permanecer inactiva por:" + sesion.getMaxInactiveInterval() / 60 + " seg \n");
			
		} catch (Exception e) {
			// Manejo silencioso de excepciones en la escritura
		}
	}

	/**
	 * Redirige las peticiones POST para que sean procesadas con la misma lógica que GET.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

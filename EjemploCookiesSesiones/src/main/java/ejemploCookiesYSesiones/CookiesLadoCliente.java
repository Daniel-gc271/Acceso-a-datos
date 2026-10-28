package ejemploCookiesYSesiones;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet que gestiona un contador de accesos utilizando Cookies en el cliente.
 */
@WebServlet("/CookiesLadoCliente")
public class CookiesLadoCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

    public CookiesLadoCliente() {
        super();
    }

	/**
	 * Procesa las peticiones GET. Recupera la cookie del contador, la incrementa y la reenvía.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Variable bandera para detener la búsqueda una vez encontrada la cookie
		boolean flag = false;
		
		// Valor inicial por defecto si es la primera vez que se accede (se incrementará a 68)
		int counter = 67;
		
		// Establece el tipo de contenido de la respuesta como texto plano
		response.setContentType("text/plain");
		
		// Recupera el array con todas las cookies enviadas por el navegador del cliente
		Cookie[] cookies = request.getCookies();
		
		// Si existen cookies, recorre el array buscando la cookie específica del contador
		if (cookies != null) {
			for (int i = 0; i < cookies.length && !flag; i++) {
				// Compara el nombre de la cookie actual con "contador"
				if (cookies[i].getName().equals("contador")) {
					// Si la encuentra, obtiene su valor actual, lo convierte a entero y le suma 1
					counter = Integer.parseInt(cookies[i].getValue()) + 1;
					// Cambia la bandera a true para romper el ciclo en la siguiente iteración
					flag = true;
				}
			}
		}
		
		// Crea una nueva instancia de Cookie (o actualiza la existente) con el nuevo valor del contador
		Cookie cookie = new Cookie("contador", String.valueOf(counter));
		
		// Define el tiempo de vida de la cookie en segundos (60s * 60m * 24h * 365d = 1 año)
		cookie.setMaxAge(60 * 60 * 24 * 365);
		
		// Añade la cookie a la cabecera de la respuesta HTTP para que el navegador la almacene
		response.addCookie(cookie);
		
		// Bloque try-with-resources para asegurar el cierre automático del PrintWriter
		try (PrintWriter responseWriter = response.getWriter()) {
			// Escribe en el cuerpo de la respuesta el número de acceso actual
			responseWriter.append("Acceso de sesion #" + counter + "\n");
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

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Servlet implementation class Ejer2Personas
 */
@WebServlet("/Ejer2Personas")
public class Ejer2Personas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ejer2Personas() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	// ❌ SE ELIMINÓ LA DECLARACIÓN DE AQUÍ PARA EVITAR DUPLICADOS Y ERRORES DE COMPILACIÓN

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
        // 1. Obtener la lista compartida en el contexto de la aplicación
        ServletContext contexto = getServletContext();
        ArrayList<Persona> lstPersonas = (ArrayList<Persona>) contexto.getAttribute("listaGlobalPersonas");
        if (lstPersonas == null) {
            lstPersonas = new ArrayList<>();
            contexto.setAttribute("listaGlobalPersonas", lstPersonas);
        }

        // 2. CASO A: Se pulsó el segundo botón (exec = 1) -> Mostrar la tabla
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("exec") != null) {
        	try (PrintWriter responseWriter = response.getWriter()) {
        	    responseWriter.append("<!DOCTYPE html><html><head><title>Tabla de Personas</title>")
        	                  .append("<link rel='stylesheet' type='text/css' href='estilos.css'>")
        	                  .append("</head><body>");
        	    
        	    responseWriter.append("<div class='contenedor-tabla'>")
        	                  .append("<h2>Listado Completo de Personas</h2>");

        	    if (lstPersonas.isEmpty()) {
        	        responseWriter.append("<p class='lista-vacia'>La lista está vacía actualmente.</p>");
        	    } else {
        	        responseWriter.append("<table>")
        	                      .append("<thead><tr>")
        	                      .append("<th>Nombre</th>")
        	                      .append("<th>Primer apellido</th>")
        	                      .append("<th>Segundo apellido</th>")
        	                      .append("<th>Contacto</th>")
        	                      .append("<th>Edad</th>")
        	                      .append("</tr></thead>")
        	                      .append("<tbody>");
        	        
        	        for (Persona p : lstPersonas) {
        	            responseWriter.append("<tr>")
        	                          .append("<td>").append(p.getNombre()).append("</td>")
        	                          .append("<td>").append(p.getApellido1()).append("</td>")
        	                          .append("<td>").append(p.getApellido2()).append("</td>")
        	                          .append("<td>").append(p.getContacto()).append("</td>")
        	                          .append("<td>").append(String.valueOf(p.getEdad())).append("</td>")
        	                          .append("</tr>");
        	        }
        	        responseWriter.append("</tbody></table>");
        	    }
        	    
        	    responseWriter.append("<div class='enlaces-retorno'>")
        	                  .append("<a href='javascript:history.back()'>← Volver al Formulario</a>")
        	                  .append("</div>");
        	    
        	    responseWriter.append("</div>") // Fin contenedor-tabla
        	                  .append("</body></html>");
        	}           
        	return; 
        }

        // 3. CASO B: Se pulsó "Persona a la lista" -> Recoger parámetros y almacenar
        String nombre = request.getParameter("nombre");
        String apellido1 = request.getParameter("ap1");
        String apellido2 = request.getParameter("ap2");
        String contacto = request.getParameter("cont");
        
        int edad = 0;
        if (request.getParameter("edad") != null && !request.getParameter("edad").isEmpty()) {
            edad = Integer.parseInt(request.getParameter("edad"));
        }
        
        if (nombre != null && !nombre.trim().isEmpty() &&
        	    apellido1 != null && !apellido1.trim().isEmpty() &&
        	    contacto != null && !contacto.trim().isEmpty()) {
        	    
        	    lstPersonas.add(new Persona(nombre, apellido1, apellido2, contacto, edad));
        	    System.out.println("Persona agregada correctamente en consola.");
        	} else {
        	    System.out.println("Error: Faltan campos obligatorios.");
        	}

        // Respuesta al agregar
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter responseWriter = response.getWriter()) {
            responseWriter.println("<!DOCTYPE html><html><head><title>Éxito</title>");
            responseWriter.println("<link rel='stylesheet' type='text/css' href='css/estilos.css'>");
            responseWriter.println("</head><body>");
            responseWriter.println("<div class='contenedor-tabla' style='max-width:500px; text-align:center;'>");
            responseWriter.println("<h3 style='color:#2ecc71;'>¡Persona agregada correctamente!</h3>");
            responseWriter.println("<p>Se ha guardado en la lista interna global.</p>");
            responseWriter.println("<div class='enlaces-retorno' style='justify-content:center;'>");
            responseWriter.println("<a href='javascript:history.back()'>Volver atrás</a>");
            responseWriter.println("</div>");
            responseWriter.println("</div>");
            responseWriter.println("</body></html>");
        }
    }

    private static class Persona {
        private final String nombre, apellido1, apellido2, contacto;
        private final int edad;
        
        public Persona(String nombre, String apellido1, String apellido2, String contacto, int edad) {
            this.nombre = nombre;
            this.apellido1 = apellido1;
            this.apellido2 = apellido2;
            this.contacto = contacto;
            this.edad = edad;
        }

        public String getNombre() { return nombre; }
        public String getApellido1() { return apellido1; }
        public String getApellido2() { return apellido2; }
        public String getContacto() { return contacto; }
        public int getEdad() { return edad; }

        @Override
        public String toString() {
            return "Persona [Nombre: " + nombre + " " + apellido1 + " " + apellido2 + 
                   ", Contacto: " + contacto + ", Edad: " + edad + "]";
        }
    }
}


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/Ejer2Personas")
public class Ejer2Personas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ejer2Personas() {
        super();
    }

    // Redirige al mismo flujo para poder pintar el formulario inicial si entran por URL
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 1. Inicializar el contexto y la lista global
        ServletContext contexto = getServletContext();
        @SuppressWarnings("unchecked")
        ArrayList<Persona2> lstPersonas = (ArrayList<Persona2>) contexto.getAttribute("listaGlobalPersonas");
        if (lstPersonas == null) {
            lstPersonas = new ArrayList<>();
            contexto.setAttribute("listaGlobalPersonas", lstPersonas);
        }

        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");

        // =========================================================================
        // ESTADO 1: Mostrar la tabla de resultados (accion = "ver")
        // =========================================================================
        if ("ver".equals(accion)) {
        	try (PrintWriter responseWriter = response.getWriter()) {
        	    responseWriter.append("<!DOCTYPE html><html><head><title>Tabla de Personas</title>")
        	                  .append("<link rel='stylesheet' type='text/css' href='css/estilos.css'>")
        	                  .append("</head><body>")
        	                  .append("<div class='contenedor-tabla'>")
        	                  .append("<h2>Listado Completo de Personas</h2>");

        	    if (lstPersonas.isEmpty()) {
        	        responseWriter.append("<p class='lista-vacia'>La lista está vacía actualmente.</p>");
        	    } else {
        	        responseWriter.append("<table>")
        	                      .append("<thead><tr>")
        	                      .append("<th>Nombre</th><th>Primer apellido</th><th>Segundo apellido</th><th>Contacto</th><th>Edad</th>")
        	                      .append("</tr></thead>")
        	                      .append("<tbody>");
        	        
        	        for (Persona2 p : lstPersonas) {
        	            responseWriter.append("<tr>")
        	                          .append("<td>").append(p.nombre()).append("</td>")
        	                          .append("<td>").append(p.apellido1()).append("</td>")
        	                          .append("<td>").append(p.apellido2()).append("</td>")
        	                          .append("<td>").append(p.contacto()).append("</td>")
        	                          .append("<td>").append(String.valueOf(p.edad())).append("</td>")
        	                          .append("</tr>");
        	        }
        	        responseWriter.append("</tbody></table>");
        	    }
        	    
        	    responseWriter.append("<div class='enlaces-retorno'>")
        	                  .append("<a href='Ejer2Personas'>← Volver al Formulario</a>")
        	                  .append("</div></div></body></html>");
        	}           
        	return; 
        }

        // =========================================================================
        // ESTADO 2: Procesar la inserción de una persona (accion = "guardar")
        // =========================================================================
        if ("guardar".equals(accion)) {
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
        	    
        	    lstPersonas.add(new Persona2(nombre, apellido1, apellido2, contacto, edad));
        	    
                // Mostramos mensaje de éxito controlado
                try (PrintWriter responseWriter = response.getWriter()) {
                    responseWriter.println("<!DOCTYPE html><html><head><title>Éxito</title>");
                    responseWriter.println("<link rel='stylesheet' type='text/css' href='css/estilos.css'>");
                    responseWriter.println("</head><body>");
                    responseWriter.println("<div class='contenedor-tabla' style='max-width:500px; text-align:center;'>");
                    responseWriter.println("<h3 style='color:#2ecc71;'>¡Persona agregada correctamente!</h3>");
                    responseWriter.println("<p>Se ha guardado en la lista interna global.</p>");
                    responseWriter.println("<div class='enlaces-retorno' style='justify-content:center;'>");
                    responseWriter.println("<a href='Ejer2Personas'>Volver al formulario</a>");
                    responseWriter.println("</div></div></body></html>");
                }
        	} else {
                // Si falla la validación, volvemos a mostrar el formulario con aviso
                try (PrintWriter responseWriter = response.getWriter()) {
                    responseWriter.println("<script>alert('Error: Faltan campos obligatorios.'); window.location='Ejer2Personas';</script>");
                }
            }
            return;
        }

        // =========================================================================
        // ESTADO 3: Entrada inicial por URL o reset (Muestra el formulario integrado)
        // =========================================================================
        try (PrintWriter responseWriter = response.getWriter()) {
            responseWriter.append("<!DOCTYPE html><html><head><title>Registro</title>")
                          .append("<link rel='stylesheet' type='text/css' href='css/estilos.css'></head><body>")
                          .append("<div class='contenedor-tabla' style='max-width: 500px;'>")
                          .append("<h2>Añadir Nueva Persona</h2>")
                          
                          // Formulario de inserción
                          .append("<form action='Ejer2Personas' method='post'>")
                          .append("<input type='hidden' name='accion' value='guardar'>")
                          .append("<label>Nombre:</label><br><input type='text' name='nombre' required><br><br>")
                          .append("<label>Primer Apellido:</label><br><input type='text' name='ap1' required><br><br>")
                          .append("<label>Segundo Apellido:</label><br><input type='text' name='ap2'><br><br>")
                          .append("<label>Contacto (Email/Tlf):</label><br><input type='text' name='cont' required><br><br>")
                          .append("<label>Edad:</label><br><input type='number' name='edad' min='0'><br><br>")
                          .append("<button type='submit'>Persona a la lista</button></form>")
                          
                          .append("<hr style='margin: 20px 0; border: 0; border-top: 1px solid #ccc;'>")
                          
                          // Formulario para ver tabla
                          .append("<form action='Ejer2Personas' method='post'>")
                          .append("<input type='hidden' name='accion' value='ver'>")
                          .append("<button type='submit' style='background-color: #34495e;'>Ver Listado de Personas</button></form>")
                          .append("</div></body></html>");
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
    }
    private static record Persona2(String nombre, String apellido1, String apellido2, String contacto, int edad) {}
}

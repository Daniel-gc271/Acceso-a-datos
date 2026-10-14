package ejercicios;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Servlet implementation class Ejercicio3SesionesYCookies
 */
@WebServlet("/Ejercicio3SesionesYCookies")

public class Ejercicio3SesionesYCookies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ejercicio3SesionesYCookies() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Establecer encoding de la respuesta y la solicitud
		response.setContentType("text/plain;charset = utf-8");
		request.setCharacterEncoding("utf-8");
		
		
		
		try  (PrintWriter responseWriter = response.getWriter()) {
			
			//responseWriter.append("Served atewrewr: ").append(request.getContextPath()).append('\n');
			//Coger todas las cookies
			Cookie[] cookies= request.getCookies();
			//Obtener el valor del radio button groupCookie
			String groupCookie =  request.getParameter("groupCookie");
			switch (groupCookie) {
				case "muestra": {
					System.out.println("muestra - Mostrar cookies");
					//Recorrer el array de cookies recuperados para mostrar sus nombres
					for (Cookie cookie : cookies) {
						 responseWriter.append(cookie.getName()+'\n');
						System.out.println(cookie.getName());
					}
					break;
				}
				case "cocina": {
					//Crear 5 cookies para pruebas
//					for (int i = 0; i < 5; i++) {
//						Cookie cookie = new Cookie("Cookie_"+String.valueOf(i), String.valueOf(i));
//						cookie.setMaxAge(60*60);
//						response.addCookie(cookie);
//					}
					//Recibir el nombre de la galleta que quiere "cocinar"
					String galletaCocinar = request.getParameter("galleta");
					galletaCocinar = galletaCocinar.trim();
					System.out.println("cocina - Crear cookie '"+galletaCocinar+"' con el nombre seleccionado");
					//Asegurar que no trato de crer una cookie con caracteres prohibidos
					String caracteresProhibidos= ".*[\\s=,;\\\\\"/?@:<>;].*";
					
					if (galletaCocinar.matches(caracteresProhibidos)) {
						//Invalido, no crear cookie
					    responseWriter.append("El nombre de la cookie contiene caracteres prohibidos.\n");
					    responseWriter.printf("'"+caracteresProhibidos+"'");
					    
					} else {
					    // Es válido, procedemos a crear la cookie
						response.addCookie(new Cookie(galletaCocinar, "_no_contiene_caracteres_que_estan_prohibidos"));
						responseWriter.append("El nombre de la cookie se ha creado correctamentes.\n");
					}
					break;
					
				}
				case "come": {
					System.out.println("come - Eliminar cookie con el nombre seleccionado");
					//recorrer el listado de cookies
					for (Cookie cookie : cookies) {
						String galletaCocinar = request.getParameter("galleta");
						galletaCocinar = galletaCocinar.trim();
						if (cookie.getName().equals(galletaCocinar)) {
							cookie.setMaxAge(0);
							response.addCookie(cookie);
							//Sale del bucle porque ya ha encontrado la cookie que desea eliminar
							break;
						}
					}
					
					break;
					}
				default: {
					System.out.println("Default");
					break;
				}	
				
			}
			
			
			responseWriter.append('\n'+groupCookie);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

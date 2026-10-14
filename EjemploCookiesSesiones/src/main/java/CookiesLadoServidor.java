

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
 * Servlet implementation class CookiesLadoServidor
 */
@WebServlet("/CookiesLadoServidor")
public class CookiesLadoServidor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookiesLadoServidor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/plain");
		//Recuperar y crear la sesion
		//true obliga a que tenga sesion si o si
		HttpSession sesion = request.getSession(true);
		//Diferenciar lo que reciba si es o no nulo
		
		try (PrintWriter responseWriter = response.getWriter()){
			Object atributoCounter = sesion.getAttribute("counter");
			//Comprobar si existe el atributo en la sesion
			responseWriter.append(atributoCounter==null?"Bienvenido a dam":"Bienvenido de nuevo");
			int counter= atributoCounter ==null?1:(int) atributoCounter;
			//Incrementar el valor
			sesion.setAttribute("counter", ++counter);
			responseWriter.append("Acceso de sesion #"+ counter + "\n");
			responseWriter.append("Fecha de acceso: "+ new Date(sesion.getCreationTime())+"\n");
			responseWriter.append("El identificador de la sesion fue:"+sesion.getId()+"\n");
			responseWriter.append("El ultimo acceso de la sesion fue:"+ new Date(sesion.getLastAccessedTime())+"\n");
			responseWriter.append("Una sesion puede permanecer inactiva por:"+ sesion.getMaxInactiveInterval()/60+" seg \n");
		} catch (Exception e) {
			// TODO: handle exception
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

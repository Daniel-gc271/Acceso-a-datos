

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
 * Servlet implementation class CookiesLadoCliente
 */
@WebServlet("/CookiesLadoCliente")
public class CookiesLadoCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public CookiesLadoCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean flag=false;
		int counter=67;
		response.setContentType("text/plain");
		//recuperar las cookies del navegador
		Cookie[] cookies = request.getCookies();
		//Recuperar una cookie del navegador
		if(cookies!=null) {
			for (int i = 0; i < cookies.length && !flag; i++) {
				//Buscar cookie cont en la lista de cookies
				if(cookies[i].getName().equals("contador")) {
					counter = Integer.parseInt(cookies[i].getValue())+1;
					flag= true;
				 	
				}
					
			}
			
		}
		//crear la cookie
		Cookie cookie = new Cookie("contador", String.valueOf(counter));
		cookie.setMaxAge(60*60*24*365);
		//Enviar la cookie al navegador
		response.addCookie(cookie);
		try (PrintWriter responseWriter = response.getWriter()) {
		
			responseWriter.append("Acceso de sesion #"+ counter + "\n");
			
			
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

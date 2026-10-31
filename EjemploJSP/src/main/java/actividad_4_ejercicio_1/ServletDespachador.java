package actividad_4_ejercicio_1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ServletDespachador
 */
@WebServlet("/ServletDespachador")
public class ServletDespachador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDespachador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password=request.getParameter("pass");
		String user=request.getParameter("user");
		System.out.printf("User: '%s'\nPassword:'%s'\n", user, password);
		RequestDispatcher dispatcherError = request.getRequestDispatcher("ServletError");
		RequestDispatcher dispatcherBienvenida = request.getRequestDispatcher("ServletBienvenida");
		if (password != null && !password.isBlank() && user != null && !user.isBlank()) {
		   
			
			if(!password.equals("admin")) {dispatcherError.forward(request, response);
			System.out.println("Contraseña incorrecta");
			} else{
				System.out.println("Todo Correcto "+ user);
				dispatcherBienvenida.forward(request, response);
			}
		} else {
			
			dispatcherError.forward(request, response);
			System.out.println("Error");
			
		}
		
	}

}

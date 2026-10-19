package hojaEjercicios2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Ejer1b
 */
@WebServlet("/Ejer1b")
public class Ejer1b extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ejer1b() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try (PrintWriter responseWriter = response.getWriter()){
			responseWriter.append("<html><body style= 'background-color:"+(String)getServletContext().getAttribute("colorAttrib")+"'> <form action'eje1b' method='get'>\r\n"
					+ "        <h2>Formulario Ejer2B</h2>\r\n"
					+ "        <button type=\"submit\" name='enviar'value='enviar'>Enviar datos</button>\r\n"
					+ "    </form></html>");
			
			
			responseWriter.append("Served at: ").append(request.getContextPath());
		} catch (Exception e) {
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

package ejemploDispatcher;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ejem10b
 */
@WebServlet("/ejem10b")
public class ejem10b extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ejem10b() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try (
				PrintWriter responseWriter= response.getWriter();
				
				) { 
			
			responseWriter.append(
					  "<html>"
					+ "<p>Inicio del dispatcher</p>"
					+ "<h1>Inicio del dispatcher</h1>"
					+ "Por el método: "+ request.getParameter("metodo")+"<br>"
					+ (String) request.getAttribute("attribDispatch")
					+ "<p>Fin del despachador</p>");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

}

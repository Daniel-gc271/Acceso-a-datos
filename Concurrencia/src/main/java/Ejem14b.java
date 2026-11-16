

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Ejem14b
 */
@WebServlet("/Ejem14b")
public class Ejem14b extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ejem14b() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("text/html");
		PrintWriter responseWriter= response.getWriter();
		responseWriter
		.append("<html><body>")
		.append("<form action='Ejem14b'method='post'>")
		.append("Filtro2: <input type='text'name='filtro2'/><br>")
		.append("<input type='submit'name='Enviar1'value='Enviar1'/>")
		.append("</html></body></html>").close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain");
		response.getWriter().append("Filtro2: "+request.getParameter("filtro2")).close();
		
	}

}

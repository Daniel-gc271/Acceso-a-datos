package hojaEjercicios2;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Ejer1
 */
@WebServlet("/Ejer1")
public class Ejer1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServletContext servletContext;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ejer1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		this.servletContext = config.getServletContext();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		String color= request.getParameter("colorParam")!=null?request.getParameter("colorParam"):(String)servletContext.getInitParameter("colorContext");
		servletContext.setAttribute("colorAttrib", color);
		try (PrintWriter responseWriter = response.getWriter()){
			responseWriter.append("<html><body style= 'background-color:"+color+"'> <form action='/eje1b' method='get'>\r\n"
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

package ejemploDispatcher;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ejem10a
 */
@WebServlet("/ejem10a")
public class ejem10a extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ejem10a() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		try (PrintWriter responseWriter= response.getWriter();) {
			responseWriter.append("<html>"
					+ "<body>"
					+ "<form action='ejem10a'method='post'>"
					+ "<h1>probando el despachador</h1>"
					+ "<input type='submit' name='metodo'value='include'/>"
					+ "<input type='submit' name='metodo'value='forward'/>"
					+ "</form>"
					+ "</body>"
					+ "</html>");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * Obtenemos el dispachador de la request
		 * Admite rutas relativas
		 * 
		 */
		RequestDispatcher dispatcher = request.getRequestDispatcher("ejem10b");
		
		//peticion por contexto, solo de la aplicacion web, rutas absolutas
		//RequestDispatcher dispatcher2 = getServletContext().getRequestDispatcher("/ejem10b");
		response.setContentType("text/html");
		
			PrintWriter responseWriter= response.getWriter();
			/**
			 * el objeto out desde forward no se ve
			 * */
			
			responseWriter.append(
					  "<html>"
					+ "<body style ='background-color : #AAFF9F'>"
					+ "<h3>Ejemplo request dispatcher</h3>"
					+ "<p>Este servlet usa un despachador que nos lleva a un servlet despachhado</p>");
					
			
			
			
			if(dispatcher!=null) {
				
				
				//Puedo crear atributos desde la request ya que la despacho desde el dispatcher
				
				//si se ve desde forward
				request.setAttribute("attribDispatch", "Llego el atributo");
				
				
				
				if(request.getParameter("metodo").equals("include")) {
					dispatcher.include(request, response);
					
				} else {
					dispatcher.forward(request, response);
					}
				/**
				 * el objeto out desde forward no se ve
				 * */
				responseWriter.append("<p>Vuelvo del servlet despachador</p>");
			} else {responseWriter.append("<p>No se ha encontrado el despachador</p>");}
			responseWriter.append(
			  "</body>"
			+ "</html>");
		//responseWriter.close() No se cierra porque es el otro servlet quien retoma la respuesta
			
			
		
		
		
	}

}

package controller;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import entity.Cotizacion;

/**
 * Servlet implementation class ServletCotizacion
 */
@WebServlet("/ServletCotizacion")
public class ServletCotizacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCotizacion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	Object lock = new Object();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page="",msj="";
		ServletContext sc=getServletContext();
		HashMap<String, Cotizacion> cotizaciones = sc.getAttribute("listaCotizacion")!=null? 
				(HashMap<String, Cotizacion>)sc.getAttribute("listaCotizacion"):
					new HashMap<String, Cotizacion>();
		HttpSession sesion = request.getSession(true);
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("password");
		
		
		switch (request.getParameter("boton")) {
		case "Acceso":
			if(nombre.isBlank()|| pass.isBlank()) {
				msj="(.) el nombre y codigo son obligatorios";
				page="/Acceso.jsp";
			} else if(pass.equalsIgnoreCase("123456")) {
				msj="(.) Credenciales incorrectas";
			} else {
				synchronized (lock) {
					sesion.setAttribute("user", nombre);
				}
				page="/Cotizacion.jsp";			
			}
			
			break;
		case "Cerrar":
			sesion.invalidate();
			msj = "(.)La sesion ha expirado";
			page="/Acceso.jsp";
			break;
			
		case "Enviar":
			Enumeration<String> params = request.getParameterNames();
			while (params.hasMoreElements()) {
				String type= params.nextElement();
				String valor=(request.getParameter(type)!=null &&
						!request.getParameter(type).isBlank()
						? request.getParameter(type).replace(",", "."):
							"0.0");
				Cotizacion c;
				if (!type.equals("boton")) {
					c = new Cotizacion(type, cotizaciones.get(type).getAnterior(),Double.valueOf(valor));
					c.setEvaluacion();
					cotizaciones.put(type, c);
				}
			}
			synchronized (lock) {
				sc.setAttribute("listaCotizacion", cotizaciones);
			}
			page="/ConsultaCotizacion.jsp";
			break;
			
		case "Volver":
			page="/Acceso.jsp";
			break;
		default:
			break;
		}
		request.setAttribute("mensaje", msj);
		request.getRequestDispatcher(page).forward(request, response);
		
		


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

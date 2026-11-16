

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.classfile.instruction.NewMultiArrayInstruction;

/**
 * Servlet implementation class Ejercicio11
 */
@WebServlet("/Ejercicio11")
public class Ejercicio11 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int val=0;
    /**
     * Default constructor. 
     */
    public Ejercicio11() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private final Object lock= new Object();
	protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			synchronized (lock) {
				val= Integer.parseInt(request.getParameter("param"));
				System.out.println("Thread: "+Thread.currentThread().getName()+" USA EL VALOR: "+val);
				Thread.sleep(10000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Thread: "+Thread.currentThread().getName()+" USA EL VALOR: "+val);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

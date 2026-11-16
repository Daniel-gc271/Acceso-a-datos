import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ListenerContexto
 *
 */
@WebListener
public class ListenerContexto implements jakarta.servlet.ServletContextListener {

    /**
     * Default constructor. 
     */
    public ListenerContexto() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
		System.out.println("App parada en el contexto..."+ sce.getServletContext().getContextPath());
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextInitialized(sce);
		System.out.println("App arrancacada en el contexto..."+ sce.getServletContext().getContextPath());
	}
    
    
    
	
}

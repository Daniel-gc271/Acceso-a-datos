package listener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import entity.Cotizacion;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class EListener implements ServletContextListener {

    public EListener() {
    }

	private Object lock = new Object();

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		HashMap<String, Cotizacion> cotizacion = new HashMap<String, Cotizacion>();
		cotizacion.put("DAM", new Cotizacion("DAM", 46.5d));
		cotizacion.put("SMR", new Cotizacion("SMR", 23.5d));
		cotizacion.put("APSD", new Cotizacion("APSD", 80.5d));
		
		String fechaActual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		synchronized (lock) {
			sce.getServletContext().setAttribute("listaCotizacion", cotizacion);
			sce.getServletContext().setAttribute("fechaActual", fechaActual);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContextListener.super.contextDestroyed(sce);
	}
}

import java.io.IOException;
import java.util.Map;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

//@WebFilter("/*")
//@WebFilter(urlPatterns = {"Ejem14"})
@WebFilter("/Ejem14")
public class EFilter implements Filter {

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		StringBuilder buffer = new StringBuilder();
		Map<String, String[]> params = arg0.getParameterMap();

		for (Map.Entry<String, String[]> entry : params.entrySet()) {
		    String key = entry.getKey();
		    buffer.append(key);
		    buffer.append("=");
		    String[] val = entry.getValue();
		    buffer.append(String.join("| ", val));
		    
		    
		    
		}
		if(!buffer.toString().equals("")) {
			System.out.println("La peticion tiene los parametros: "+ buffer );
		}
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Filter.super.init(filterConfig);
	}

	

}

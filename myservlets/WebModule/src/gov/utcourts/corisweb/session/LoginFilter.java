package gov.utcourts.corisweb.session;

import gov.utcourts.coriscommon.constants.Constants;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.json.java.JSONObject;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(
	filterName = "/LoginFilter", 
	urlPatterns = {"/*"}
)
public class LoginFilter implements Filter {

	public static final String LOGIN_PAGE = Constants.WEB_ROOT + "login.jsp";
	private static Set<String> excluded;
	 
    /**
     * Default constructor. 
     */
    public LoginFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	private boolean isExcluded(HttpServletRequest request) {
        String path = request.getRequestURI();
        String extension = path.substring(path.indexOf('.', path.lastIndexOf('/')) + 1).toLowerCase();
        return excluded.contains(path) || excluded.contains(extension);
    }
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		excluded = new HashSet<String>();
		
		// paths
		excluded.add(Constants.WEB_ROOT + "login.jsp");
		excluded.add(Constants.WEB_ROOT + "logout.jsp");
		excluded.add(Constants.WEB_ROOT + "LoginServlet");
		excluded.add(Constants.WEB_ROOT + "PasswordServlet");
		excluded.add(Constants.WEB_ROOT + "sso");
		
		// file extensions
		excluded.add("css");
		excluded.add("js");
		excluded.add("min.js");
		excluded.add("slim.js");
		excluded.add("slim.min.js");
		excluded.add("jpg");
		excluded.add("jpeg");
		excluded.add("gif");
		excluded.add("png");
		excluded.add("pdf");
		excluded.add("map");
	}
	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	if (request instanceof HttpServletRequest) {
    		HttpServletRequest httpRequest = (HttpServletRequest) request;     
    		HttpServletResponse httpResponse = (HttpServletResponse) response;  
    		
    		HttpSession session = ((HttpServletRequest) request).getSession(false);
    		if (isExcluded(httpRequest) || (session != null && session.getAttribute(SessionVariables.LOG_NAME) != null))
    			try {
    				chain.doFilter(request, response);
    			} catch(Exception e) {
    				System.out.println(".doFilter Exception " + e.getMessage());
    				// e.printStackTrace();
    			}
    		else {
    			if ("XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With"))) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("redirectTo", LOGIN_PAGE);
					
					httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					httpResponse.setContentType("application/json; charset=UTF-8");
					httpResponse.getWriter().write(jsonObject.toString());
					jsonObject = null;
				} else {
					httpResponse.sendRedirect(LOGIN_PAGE);
				}	
    		}
    		session = null;
    		httpRequest = null;
    		httpResponse = null;
        }		
	}
}

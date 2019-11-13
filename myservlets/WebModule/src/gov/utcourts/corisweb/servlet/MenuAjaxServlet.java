package gov.utcourts.corisweb.servlet;

import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.MenuUtil;
import gov.utcourts.corisweb.util.URLEncryption;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONObject;

/**
 * Servlet implementation class MenuAjaxServlet
 */
@WebServlet("/MenuAjaxServlet")
public class MenuAjaxServlet extends BaseServlet {
	private static final long serialVersionUID = -2114512454339L;
	
	private static final Logger logger = Logger.getLogger(MenuAjaxServlet.class);
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenuAjaxServlet() {
		super();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String action = URLEncryption.getParamAsString(request, "action");
			
			JSONObject retValObj = new JSONObject();
			String errorMessage = "";
			if ("retrieveAppNavTree".equalsIgnoreCase(action)) {
				try {
					List<Integer> roles = (List<Integer>) request.getSession().getAttribute(SessionVariables.USER_ROLE);
					String menuString = MenuUtil.createMenu("D", roles);      // NOTE: the menu is the same in District and Justice

					retValObj = JSONObject.parse(menuString);				 		// FROM DB      
					//retValObj = JSONObject.parse(buildAppNavTreeFromFile()); 	// FROM FILE
					
				} catch (Exception e) {
					logger.error(e);
					errorMessage = "Error retrieving navigation data: " + e.getMessage();
				}
			}
			
			if (!TextUtil.isEmpty(errorMessage)) {
				retValObj = new JSONObject();
				retValObj.put("error", true);
				retValObj.put("errorMessage", errorMessage);
			}
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "0");
			response.getWriter().write(retValObj.toString());
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	/**
	 * Build stringified app nav tree JSON data
	 * 
	 * @param retValObj JSONObject
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private String buildAppNavTreeFromFile() throws Exception {
		File appNavTreeFile = new File(getServletContext().getRealPath("json/app-nav-tree.json"));
		
		BufferedReader br = null;
		StringBuilder sb = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(appNavTreeFile), "UTF-8"));
			sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append('\n');
			}
		} finally {
			if (br != null) {
				br.close();
			}
		}
		
		return sb.toString();
	}
	
}

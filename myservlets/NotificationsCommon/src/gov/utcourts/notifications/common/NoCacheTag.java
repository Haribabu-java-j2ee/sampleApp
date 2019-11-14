package gov.utcourts.notifications.common;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Taglib class used to ouput a line of HTML metatags to request that the page not be cached.
 * 
 */
public class NoCacheTag extends TagSupport {
	public static final long serialVersionUID = 5565411;
	
	/** the logger for this class. */
	private static org.apache.log4j.Logger log =
		org.apache.log4j.Logger.getLogger(NoCacheTag.class.getName());

	/**
	 * Implements the Tag method.
	 * @return int as specified in Tag#doEndTag()
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	/**
	 * Outputs the no-cache HTML.
	 * 
	 * @return int as specified in Tag#doStartTag()
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		String value =
			"<HEAD>"
				+ "<META HTTP-EQUIV='expires' CONTENT='0'>"
				+ "<META HTTP-EQUIV='Pragma' CONTENT='no-cache'>"
				+ "<META HTTP-EQUIV='Cache-Control' CONTENT='no-store'>"
				+ "</HEAD>";
				try{
				pageContext.getOut().print(value);
				}catch(Exception e){
						log.error(e);
				}
		return EVAL_PAGE;
	}

}

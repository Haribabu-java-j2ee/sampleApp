package gov.utcourts.corisweb.tablecompare;

import gov.utcourts.coriscommon.constants.ConstantsConnectionProperties;
import gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.dataaccess.connection.ConnectionProperties;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
/**
 * Servlet implementation class AttorneyTableCompareProcess
 */
public class AttorneyTableCompareProcess {
	
	
	private static final long serialVersionUID = 8751358626902358760L;
	
	private static Logger logger = Logger.getLogger(AttorneyTableCompareProcess.class);
	
	 /**
     * @see HttpServlet#HttpServlet()
     */
    public AttorneyTableCompareProcess() {
        super();
    }
    
	
	/**
	 * Handle Table Compare Attorney
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public static StringBuilder TableCompareAttorney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean productionJustice = URLEncryption.getParamFromCheckBox(request,"productionJustice");
		boolean testDistrict = URLEncryption.getParamFromCheckBox(request,"testDistrict");
		boolean testJustice = URLEncryption.getParamFromCheckBox(request,"testJustice");
		boolean verify = URLEncryption.getParamFromCheckBox(request,"verify");
		boolean training = URLEncryption.getParamFromCheckBox(request,"training");
		boolean development = URLEncryption.getParamFromCheckBox(request,"development"); 
		StringBuilder strBuilder = new StringBuilder("");
		
    	Connection conn = null;
    	List<AttorneyBO> attorneyListBO = null;
		//////////////////////////////////////////////////////////////////////////////////
		// Production District is the Primary Data Source
		//////////////////////////////////////////////////////////////////////////////////
		conn = TableCompareProcessUtils.connectToDatabase(ConstantsConnectionProperties.CORIS_PRODUCTION_DISTRICT_DB);
    	try {
    		attorneyListBO = new AttorneyBO("D").setUseConnection(conn).search();
		} catch (Exception e) {
			logger.info("Process Table Compare CORIS_PRODUCTION_DISTRCIT_DB connection fail");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				logger.error("CORIS_PRODUCTION_DISTRICT_DB Connection Close", e);
			}
			conn = null;
		}
    	
		//////////////////////////////////////////////////////////////////////////////////
		// Each connection gets it own try so if its fails the other's still get updated
		//////////////////////////////////////////////////////////////////////////////////
		if (productionJustice){
	    	try {
				conn = TableCompareProcessUtils.connectToDatabase(ConstantsConnectionProperties.CORIS_PRODUCTION_JUSTICE_DB);
				strBuilder.append(dataBaseInsertUpdateAttorneyInformation(conn, "J", attorneyListBO, "Production Justice"));
				conn.close();
			} catch (Exception e) {
				logger.info("Process Table Compare CORIS_PRODUCTION_JUSTICE_DB connection fail");
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					logger.error("CORIS_PRODUCTION_JUSTICE_DB Connection Close", e);
				}
				conn = null;
			}
		}
		//////////////////////////////////////////////////////////////////////////////////
		// Null out Attorney Email Address in non production boxes, so email do not go out
		//////////////////////////////////////////////////////////////////////////////////
		if (testDistrict || testJustice || verify || training  ||development){
			for (AttorneyBO attorneyBO :attorneyListBO){
				attorneyListBO.set(attorneyListBO.indexOf(attorneyBO),attorneyBO.setEmailAddress(null));
			}
		}

		//////////////////////////////////////////////////////////////////////////////////
		// Each connection gets it own try so if its fails the other's still get updated
		//////////////////////////////////////////////////////////////////////////////////
		if (testDistrict){
	    	try {
				conn = TableCompareProcessUtils.connectToDatabase(ConstantsConnectionProperties.CORIS_TEST_DISTRICT_DB);
				strBuilder.append(dataBaseInsertUpdateAttorneyInformation(conn, "D", attorneyListBO, "Test District"));
			} catch (Exception e) {
				logger.error("Process Table Compare CORIS_TEST_DISTRCIT_DB connection fail");
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					logger.error("CORIS_TEST_DISTRICT_DB Connection Close", e);
				}
				conn = null;
			}
		}
		
		//////////////////////////////////////////////////////////////////////////////////
		// Each connection gets it own try so if its fails the other's still get updated
		//////////////////////////////////////////////////////////////////////////////////
		if (testJustice){
	    	try {
				conn = TableCompareProcessUtils.connectToDatabase(ConstantsConnectionProperties.CORIS_TEST_JUSTICE_DB);
				strBuilder.append(dataBaseInsertUpdateAttorneyInformation(conn, "J", attorneyListBO, "Test Justice"));
			} catch (Exception e) {
				logger.info("Process Table Compare CORIS_TEST_JUSTICE_DB connection fail");
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					logger.error("CORIS_TEST_JUSTICE_DB Connection Close", e);
				}
				conn = null;
			}
		}
		
		//////////////////////////////////////////////////////////////////////////////////
		// Each connection gets it own try so if its fails the other's still get updated
		//////////////////////////////////////////////////////////////////////////////////
		if (training){
	    	try {
				conn = TableCompareProcessUtils.connectToDatabase(ConstantsConnectionProperties.CORIS_TRAINING_DB);
				strBuilder.append(dataBaseInsertUpdateAttorneyInformation(conn, "D", attorneyListBO, "Training"));
			} catch (Exception e) {
				logger.info("Process Table Compare CORIS_TRAINING_JUSTICE_DB connection fail");
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					logger.error("CORIS_TRAINING_DB Connection Close", e);
				}
				conn = null;
			}
		}
		//////////////////////////////////////////////////////////////////////////////////
		// Each connection gets it own try so if its fails the other's still get updated
		//////////////////////////////////////////////////////////////////////////////////
		if (verify){
	    	try {
				conn = TableCompareProcessUtils.connectToDatabase(ConstantsConnectionProperties.CORIS_VERIFY_DB);
				strBuilder.append(dataBaseInsertUpdateAttorneyInformation(conn, "D", attorneyListBO, "Verify"));
				conn.close();
			} catch (Exception e) {
				logger.info("Process Table Compare CORIS_TRAINING_JUSTICE_DB verify fail");
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					logger.error("CORIS_VERIFY_DB Connection Close", e);
				}
				conn = null;
			}
		}		
		//////////////////////////////////////////////////////////////////////////////////
		// Each connection gets it own try so if its fails the other's still get updated
		//////////////////////////////////////////////////////////////////////////////////
		if (development){
	    	try {
				conn = TableCompareProcessUtils.connectToDatabase(ConstantsConnectionProperties.CORIS_DEVELOPMENT_DB);
				strBuilder.append(dataBaseInsertUpdateAttorneyInformation(conn, "D", attorneyListBO, "Development"));
				conn.close();
			} catch (Exception e) {
				logger.info("Process Table Compare CORIS_DEVELOPMENT_JUSTICE_DB verify fail");
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					logger.error("CORIS_DEVELOPMENT_DB Connection Close", e);
				}
				conn = null;
			}
		}
		attorneyListBO = null;
		return strBuilder; 
	}

    public static StringBuilder dataBaseInsertUpdateAttorneyInformation(Connection conn, String courtType, List<AttorneyBO> attorneyListBO, String dataBaseName) {
		StringBuilder strBuilder = new StringBuilder("");
    	int correct = 0;
		int insert = 0;
		int update = 0;
		
    	for (AttorneyBO attorneyPrimaryBO: attorneyListBO){
    		
    		if (!TextUtil.isEmpty(attorneyPrimaryBO.getBarState())){
				//////////////////////////////////////////////////////////////////////////
				// Does Attorney Exists Update else Insert
				//////////////////////////////////////////////////////////////////////////
				try {
					AttorneyBO attorneyExistsBO = new AttorneyBO(courtType)
					.where(AttorneyBO.BARNUM,  attorneyPrimaryBO.getBarNum())
					.where(AttorneyBO.BARSTATE,  attorneyPrimaryBO.getBarState())
					.setUseConnection(conn)
					.find();
					
					if (attorneyExistsBO.getBarNum() > 0){
						if (attorneyExistsBO.getBarNum() == attorneyPrimaryBO.getBarNum() &&
							TextUtil.stringEqualsSpecial(attorneyExistsBO.getBarState(), attorneyPrimaryBO.getBarState()) &&
						    TextUtil.stringEqualsSpecial(attorneyExistsBO.getPrefix(), attorneyPrimaryBO.getPrefix()) &&
						    TextUtil.stringEqualsSpecial(attorneyExistsBO.getLastName(), attorneyPrimaryBO.getLastName()) &&
						    TextUtil.stringEqualsSpecial(attorneyExistsBO.getMiddleInitial(), attorneyPrimaryBO.getMiddleInitial()) &&
						    TextUtil.stringEqualsSpecial(attorneyExistsBO.getFirstName(), attorneyPrimaryBO.getFirstName()) &&
				    		TextUtil.stringEqualsSpecial(attorneyExistsBO.getOrganization(), attorneyPrimaryBO.getOrganization()) &&
		    				TextUtil.stringEqualsSpecial(attorneyExistsBO.getAddress1(), attorneyPrimaryBO.getAddress1())&&
    						TextUtil.stringEqualsSpecial(attorneyExistsBO.getAddress2(), attorneyPrimaryBO.getAddress2())&&
							TextUtil.stringEqualsSpecial(attorneyExistsBO.getAddress3(), attorneyPrimaryBO.getAddress3())&&
							TextUtil.stringEqualsSpecial(attorneyExistsBO.getCity(), attorneyPrimaryBO.getCity())&&
							TextUtil.stringEqualsSpecial(attorneyExistsBO.getStateCode(), attorneyPrimaryBO.getStateCode())&&
							TextUtil.stringEqualsSpecial(attorneyExistsBO.getZipCode(), attorneyPrimaryBO.getZipCode()) &&
							TextUtil.stringEqualsSpecial(attorneyExistsBO.getCountry(), attorneyPrimaryBO.getCountry()) &&
							TextUtil.stringEqualsSpecial(attorneyExistsBO.getHomePhone(), attorneyPrimaryBO.getHomePhone()) &&
							TextUtil.stringEqualsSpecial(attorneyExistsBO.getBusPhone(), attorneyPrimaryBO.getBusPhone()) &&
							TextUtil.stringEqualsSpecial(attorneyExistsBO.getHomePhone(), attorneyPrimaryBO.getHomePhone()) &&
							TextUtil.stringEqualsSpecial(attorneyExistsBO.getCellPhone(), attorneyPrimaryBO.getCellPhone()) &&
							TextUtil.stringEqualsSpecial(attorneyExistsBO.getBarStatus(), attorneyPrimaryBO.getBarStatus()) &&
							TextUtil.stringEqualsSpecial(attorneyExistsBO.getEmailAddress(), attorneyPrimaryBO.getEmailAddress())){
							
							correct += 1;
							
						} else {
							update += 1 ;
							
							new AttorneyBO(courtType)
								.where(attorneyPrimaryBO.BARNUM,  attorneyPrimaryBO.getBarNum())
								.where(attorneyPrimaryBO.BARSTATE,  attorneyPrimaryBO.getBarState())
								.setBarNum(attorneyPrimaryBO.getBarNum())
								.setBarState(attorneyPrimaryBO.getBarState())
								.setPrefix(attorneyPrimaryBO.getPrefix())
								.setLastName(attorneyPrimaryBO.getLastName())
								.setMiddleInitial(attorneyPrimaryBO.getMiddleInitial())
								.setFirstName(attorneyPrimaryBO.getFirstName())
								.setOrganization(attorneyPrimaryBO.getOrganization())
								.setAddress1(attorneyPrimaryBO.getAddress1())
								.setAddress2(attorneyPrimaryBO.getAddress2())
								.setAddress3(attorneyPrimaryBO.getAddress3())
								.setCity(attorneyPrimaryBO.getCity())
								.setStateCode(attorneyPrimaryBO.getStateCode())
								.setZipCode(attorneyPrimaryBO.getZipCode())
								.setCountry(attorneyPrimaryBO.getCountry())
								.setHomePhone(attorneyPrimaryBO.getHomePhone())
								.setBusPhone(attorneyPrimaryBO.getBusPhone())
								.setFaxNum(attorneyPrimaryBO.getFaxNum())
								.setCellPhone(attorneyPrimaryBO.getCellPhone())
								.setBarStatus(attorneyPrimaryBO.getBarStatus())
								.setEmailAddress(attorneyPrimaryBO.getEmailAddress())
								.setUseConnection(conn)
								.update();
						}
					} else {
						insert += 1;
						
						new AttorneyBO(courtType)
						.setBarNum(attorneyPrimaryBO.getBarNum())
						.setBarState(attorneyPrimaryBO.getBarState())
						.setPrefix(attorneyPrimaryBO.getPrefix())
						.setLastName(attorneyPrimaryBO.getLastName())
						.setMiddleInitial(attorneyPrimaryBO.getMiddleInitial())
						.setFirstName(attorneyPrimaryBO.getFirstName())
						.setOrganization(attorneyPrimaryBO.getOrganization())
						.setAddress1(attorneyPrimaryBO.getAddress1())
						.setAddress2(attorneyPrimaryBO.getAddress2())
						.setAddress3(attorneyPrimaryBO.getAddress3())
						.setCity(attorneyPrimaryBO.getCity())
						.setStateCode(attorneyPrimaryBO.getStateCode())
						.setZipCode(attorneyPrimaryBO.getZipCode())
						.setCountry(attorneyPrimaryBO.getCountry())
						.setHomePhone(attorneyPrimaryBO.getHomePhone())
						.setBusPhone(attorneyPrimaryBO.getBusPhone())
						.setFaxNum(attorneyPrimaryBO.getFaxNum())
						.setCellPhone(attorneyPrimaryBO.getCellPhone())
						.setBarStatus(attorneyPrimaryBO.getBarStatus())
						.setEmailAddress(attorneyPrimaryBO.getEmailAddress())
						.setUseConnection(conn)
						.insert();
					}
					attorneyExistsBO = null;
				} catch (Exception e) {
					logger.error("dataBaseInsertUpdateAttorneyInformation", e);
				}
    		}	
			attorneyPrimaryBO = null;
    	}
    	
		strBuilder.append("<br>");
		strBuilder.append("Data Base Name: " + dataBaseName + "<br>");
		strBuilder.append("		Processed: " + attorneyListBO.size() + "<br>");
		strBuilder.append("		  Correct: " + correct + "<br>");
		strBuilder.append("		   Insert: " + insert + "<br>");
		strBuilder.append("		   Update: " + update + "<br>");
		strBuilder.append("<br>");
		
		correct = 0;
		insert = 0;
		update = 0;

	    return strBuilder;
		
    }		
}

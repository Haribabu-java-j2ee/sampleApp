package gov.utcourts.corisweb.tablecompare;

import gov.utcourts.coriscommon.constants.ConstantsConnectionProperties;
import gov.utcourts.coriscommon.dataaccess.country.CountryBO;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.util.URLEncryption;
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
 * Servlet implementation class CountryTableCompareProcess
 */
public class CountryTableCompareProcess {
	
	
	private static final long serialVersionUID = 8751358626902358760L;
	
	private static Logger logger = Logger.getLogger(AttorneyTableCompareProcess.class);
	
	 /**
     * @see HttpServlet#HttpServlet()
     */
    public CountryTableCompareProcess() {
        super();
    }
    
	
	/**
	 * Handle Table Compare Attorney
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public static StringBuilder TableCompareCountry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean productionJustice = URLEncryption.getParamFromCheckBox(request,"productionJustice");
		boolean testDistrict = URLEncryption.getParamFromCheckBox(request,"testDistrict");
		boolean testJustice = URLEncryption.getParamFromCheckBox(request,"testJustice");
		boolean verify = URLEncryption.getParamFromCheckBox(request,"verify");
		boolean training = URLEncryption.getParamFromCheckBox(request,"training");
		boolean development = URLEncryption.getParamFromCheckBox(request,"development"); 
		StringBuilder strBuilder = new StringBuilder("");
		
		strBuilder.append("<br>");
		strBuilder.append("Country Table Compare Process<br>");
    	Connection conn = null;
    	List<CountryBO> countryListBO = null;
		//////////////////////////////////////////////////////////////////////////////////
		// Production District is the Primary Data Source
		//////////////////////////////////////////////////////////////////////////////////
		conn = TableCompareProcessUtils.connectToDatabase(ConstantsConnectionProperties.CORIS_PRODUCTION_DISTRICT_DB);
    	try {
    		countryListBO = new CountryBO("D").setUseConnection(conn).search();
		} catch (Exception e) {
			logger.info("Process Table Compare CORIS_PRODUCTION_DISTRCIT_DB connection fail");
		} finally {
			try {
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
				strBuilder.append(dataBaseInsertUpdateCountryInformation(conn, "J", countryListBO, "Production Justice"));
				conn.close();
			} catch (Exception e) {
				logger.info("Process Table Compare CORIS_PRODUCTION_JUSTICE_DB connection fail");
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("CORIS_PRODUCTION_JUSTICE_DB Connection Close", e);
				}
				conn = null;
			}
		}	
		//////////////////////////////////////////////////////////////////////////////////
		// Each connection gets it own try so if its fails the other's still get updated
		//////////////////////////////////////////////////////////////////////////////////
		if (testDistrict){
	    	try {
	    		conn = TableCompareProcessUtils.connectToDatabase(ConstantsConnectionProperties.CORIS_TEST_DISTRICT_DB);
				strBuilder.append(dataBaseInsertUpdateCountryInformation(conn, "D", countryListBO,"Test District"));
			} catch (Exception e) {
				logger.error("Process Table Compare CORIS_TEST_DISTRCIT_DB connection fail");
			} finally {
				try {
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
				strBuilder.append(dataBaseInsertUpdateCountryInformation(conn, "J", countryListBO, "Test Justice"));
			} catch (Exception e) {
				logger.info("Process Table Compare CORIS_TEST_JUSTICE_DB connection fail");
			} finally {
				try {
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
				strBuilder.append(dataBaseInsertUpdateCountryInformation(conn, "D", countryListBO, "Training"));
			} catch (Exception e) {
				logger.info("Process Table Compare CORIS_TRAINING_JUSTICE_DB connection fail");
			} finally {
				try {
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
				strBuilder.append(dataBaseInsertUpdateCountryInformation(conn, "D", countryListBO, "Verify"));
				conn.close();
			} catch (Exception e) {
				logger.info("Process Table Compare CORIS_TRAINING_JUSTICE_DB verify fail");
			} finally {
				try {
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
				strBuilder.append(dataBaseInsertUpdateCountryInformation(conn, "D", countryListBO, "Development"));
				conn.close();
			} catch (Exception e) {
				logger.info("Process Table Compare CORIS_DEVELOPMENT_JUSTICE_DB verify fail");
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("CORIS_DEVELOPMENT_DB Connection Close", e);
				}
				conn = null;
			}
		}
		
		countryListBO = null;
		
		strBuilder.append("<br>");
		
		return strBuilder; 
	}

    public static StringBuilder dataBaseInsertUpdateCountryInformation(Connection conn, String courtType, List<CountryBO> countryListBO, String dataBaseName) {
		StringBuilder strBuilder = new StringBuilder("");
    	int correct = 0;
		int insert = 0;
		int update = 0;
		
    	for (CountryBO countryPrimaryBO: countryListBO){
    		
    		if (countryPrimaryBO.getCountryId() > 0){
				//////////////////////////////////////////////////////////////////////////
				// Does Country Exists Update else Insert
				//////////////////////////////////////////////////////////////////////////
				try {
					CountryBO countryExistsBO = new CountryBO(courtType)
					.where(CountryBO.COUNTRYID, countryPrimaryBO.getCountryId())
					.setUseConnection(conn)
					.find();
					
					if (countryExistsBO.getCountryId() > 0){
						if (TextUtil.stringEqualsSpecial(countryExistsBO.getCountryName(), countryPrimaryBO.getCountryName()) &&
							TextUtil.stringEqualsSpecial(countryExistsBO.getPsCountryCode(), countryPrimaryBO.getPsCountryCode()) &&
							TextUtil.stringEqualsSpecial(countryExistsBO.getUnCountryCode(), countryPrimaryBO.getUnCountryCode())){
							
							correct += 1;
							
						} else {
							update += 1 ;
							
							new CountryBO(courtType)
								.where(CountryBO.COUNTRYID, countryPrimaryBO.getCountryId())
								.setCountryName(countryPrimaryBO.getCountryName())
								.setPsCountryCode(countryPrimaryBO.getPsCountryCode())
								.setUnCountryCode(countryPrimaryBO.getUnCountryCode())
								.setDisabledDate(countryPrimaryBO.getDisabledDate())
								.setUseConnection(conn)
								.update();
						}
					} else {
						insert += 1;
						
						new CountryBO(courtType)
						.setCountryId(countryPrimaryBO.getCountryId())
						.setCountryName(countryPrimaryBO.getCountryName())
						.setPsCountryCode(countryPrimaryBO.getPsCountryCode())
						.setUnCountryCode(countryPrimaryBO.getUnCountryCode())
						.setDisabledDate(countryPrimaryBO.getDisabledDate())
						.setUseConnection(conn)
						.setUseConnection(conn)
						.insert();
					}
					countryExistsBO = null;
				} catch (Exception e) {
					logger.error("dataBaseInsertUpdateCountryInformation", e);
				}
    		}	
			countryPrimaryBO = null;
    	}
		strBuilder.append("<br>");
		strBuilder.append("Data Base Name: " + dataBaseName + "<br>");
		strBuilder.append("		Processed: " + countryListBO.size() + "<br>");
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

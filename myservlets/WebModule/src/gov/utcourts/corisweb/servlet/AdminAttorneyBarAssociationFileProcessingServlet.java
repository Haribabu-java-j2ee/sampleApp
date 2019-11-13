package gov.utcourts.corisweb.servlet;

import gov.utcourts.carecommon.dataaccess.attorneybarload.AttorneybarloadBO;
import gov.utcourts.coriscommon.constants.ConstantsConnectionProperties;
import gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.session.SessionVariables;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xml.utils.XMLChar;

/**
 * Servlet implementation class AdminAttorneyBarAssociationFileProcessingServlet
 */
@WebServlet("/AdminAttorneyBarAssociationFileProcessingServlet")
public class AdminAttorneyBarAssociationFileProcessingServlet extends BaseServlet implements BaseConstants {
	
	
	private static final long serialVersionUID = 8751358626902358760L;
	
	private static Logger logger = Logger.getLogger(AdminAttorneyBarAssociationFileProcessingServlet.class);
	
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 2; // 2MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 2; // 2MB
	
	private static final String FIND_PAGE = "/jsp/adminAttorneyBarAssociationFileProcessing.jsp";
	private static final String	CLOSE_PAGE = "/jsp/ClosePopin.jsp";
	private static final int DEFAULT_BUFFER_SIZE = 10240;
	 
	private boolean productionDistrict = false;
	private boolean productionJustice = false;
	private boolean testDistrict = false;
	private boolean testJustice = false;
	private boolean verify = false;
	private boolean training = false;
	private boolean development = false;
	private boolean prodCare = false;
	private boolean testCare = false;
	private boolean devCare = false;
	 /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAttorneyBarAssociationFileProcessingServlet() {
        super();
    }
    
	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if (isMultipart) {
			uploadFile(request, response);
		}else{
			forward(FIND_PAGE, request, response);
		}
	}
	
	/**
	 * Handle uploadJuryTermFile request
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	private void uploadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FileItem> items;
		FileItem itemFile = null;
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute(SessionVariables.LOG_NAME);
		String mode = URLEncryption.getParamAsString(request, "mode");

		// checks if the request contains the upload file
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		logger.info("<----- Process Attorney Bar Association File Start ----->");
		
		if (isMultipart) {

			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// maximum size that will be stored in memory
			factory.setSizeThreshold(MEMORY_THRESHOLD);
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			// sets maximum size of upload file
			upload.setFileSizeMax(MAX_FILE_SIZE);
			
			try {
				productionDistrict = false;
				productionJustice = false;
				testDistrict = false;
				testJustice = false;
				verify = false;
				training = false;
				development = false;
				prodCare = false;
				testCare = false;
				devCare = false;
								
				items = upload.parseRequest(request);
				if (items != null && items.size() > 0) {
					for (FileItem item : items) {
						if (item.isFormField()) {
						    // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
							if (item.getFieldName().equals("productionDistrict")){
								productionDistrict = true;
							}else if (item.getFieldName().equals("productionJustice")){
								productionJustice = true;
							} else if (item.getFieldName().equals("testDistrict")){
								testDistrict = true;
							}else if (item.getFieldName().equals("testJustice")){
								testJustice = true;
							}else if (item.getFieldName().equals("verify")){
								verify = true;
							}else if (item.getFieldName().equals("training")){
								training = true;
							}else if (item.getFieldName().equals("development")){
								development = true;
							}else if (item.getFieldName().equals("productionCare")){
								prodCare = true;
							}else if (item.getFieldName().equals("testCare")){
								testCare = true;
							}else if (item.getFieldName().equals("developmentCare")){
								devCare = true;
							}
						} else {
							// Process form file field (input type="file").
							itemFile = item;
						}
					}
					if (itemFile.getName().toLowerCase().contains("xlsx")){
						processAttorneyBarAssociationXLSXFile(userName, itemFile);
					} else if (itemFile.getName().toLowerCase().contains("xls")){
						processAttorneyBarAssociationXLSFile(userName, itemFile);
					}
					request.setAttribute("process","SUCCESS");
				}	
			} catch (FileUploadException e) {
				request.setAttribute("process","FAILURE");
				logger.error("Process Attorney Item File Name Parsing", e);
			}
			forward(FIND_PAGE , request, response);
			session = null;
			userName = null;
			itemFile = null;
			items = null;
		}
		logger.info("<----- Processing Attorney Bar Association File Complete ----->");
	}
	
 	private void processAttorneyBarAssociationXLSFile(String userName, FileItem item) throws ServletException, IOException {
        //Read the Excel Workbook in a instance object    
 		HSSFWorkbook workbook = new HSSFWorkbook(item.getInputStream());
        //This will read the sheet for us into another object
 		HSSFSheet sheet = workbook.getSheetAt(1);
        // Create iterator object
 		Iterator<Row> rowIterator = sheet.iterator(); 
        // Process
 		processExcelSheet(rowIterator);
        // Clean Up
        workbook = null; 
        sheet = null; 
	}

 	private void processAttorneyBarAssociationXLSXFile(String userName, FileItem item) throws ServletException, IOException {
        //Read the Excel Workbook in a instance object    
        XSSFWorkbook workbook = new XSSFWorkbook(item.getInputStream()); 
        //This will read the sheet for us into another object
        XSSFSheet sheet = workbook.getSheetAt(1); 
        // Create iterator object
        Iterator<Row> rowIterator = sheet.iterator(); 
        // Process
        processExcelSheet(rowIterator);
        // Clean Up
        workbook = null; 
        sheet = null; 
 	}
        
 	private void processExcelSheet(Iterator<Row> rowIterator) throws ServletException, IOException {
		List<AttorneyBO> attorneyListBO = new ArrayList<AttorneyBO>();
		while(rowIterator.hasNext()) {
			//Read Rows from Excel document       
			Row row = rowIterator.next(); 
			//Read every column for every row that is READ
			Iterator<Cell> cellIterator = row.cellIterator();
			
			String[] attorneyData = new String[100];
			while(cellIterator.hasNext()) {
				//Fetch CELL
				Cell cell = cellIterator.next(); 
				
				//Bar Number	Prefix	Lastname	Middleinitial	Firstname	Orgname	Address1	Address2	Address3	City	Statecd	Zip	Workphone	Faxphone	Current Type	Email	Past Type	Past Status	Status Change Date

				
				switch(cell.getCellType()) { //Identify CELL type
					case Cell.CELL_TYPE_STRING:
						if (cell.getColumnIndex() != 17){
							attorneyData[cell.getColumnIndex()] = convertBadCharacters(cell.getRichStringCellValue().getString());
						} else {
							attorneyData[cell.getColumnIndex()] = cell.getRichStringCellValue().getString();
						}
						break;
					case Cell.CELL_TYPE_NUMERIC:
                		Double d = cell.getNumericCellValue();
                		Integer number =  d.intValue();
                		attorneyData[cell.getColumnIndex()] = convertBadCharacters(String.valueOf(number));
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						attorneyData[cell.getColumnIndex()] = convertBadCharacters(String.valueOf(cell.getNumericCellValue()));
					case Cell.CELL_TYPE_BLANK:
						attorneyData[cell.getColumnIndex()] = "";
                	default:
                		attorneyData[cell.getColumnIndex()] = "";
				}
    		}
			
			if (TextUtil.isEmpty(attorneyData[0])){
				break;
			}

			if (!attorneyData[0].toUpperCase().contains("BAR NUMBER")){
				AttorneyBO attorneyBO = new AttorneyBO("");
				attorneyBO.setBarState("UT");
				if (!TextUtil.isEmpty(attorneyData[0])){
					attorneyBO.setBarNum(Integer.valueOf(attorneyData[0]));
				}
				if (!TextUtil.isEmpty(attorneyData[1])){
					attorneyBO.setPrefix(attorneyData[1]);
				}
				if (!TextUtil.isEmpty(attorneyData[2])){
					attorneyBO.setLastName(attorneyData[2]);
				}
				if (!TextUtil.isEmpty(attorneyData[3])){
					attorneyBO.setMiddleInitial(attorneyData[3]);
				}
				if (!TextUtil.isEmpty(attorneyData[4])){
					attorneyBO.setFirstName(attorneyData[4]);
				}
				if (!TextUtil.isEmpty(attorneyData[5])){
					attorneyBO.setOrganization(attorneyData[5]);
				}
				
				if (!TextUtil.isEmpty(attorneyData[6])){
					attorneyBO.setAddress1(validateAddress(attorneyData[6]));
				}
				
				if (!TextUtil.isEmpty(attorneyData[7])){
					attorneyBO.setAddress2(validateAddress(attorneyData[7]));
				}
				if (!TextUtil.isEmpty(attorneyData[8])){
					attorneyBO.setAddress3(validateAddress(attorneyData[8]));
				}
				if (!TextUtil.isEmpty(attorneyData[9])){
					attorneyBO.setCity(attorneyData[9]);
				}
				
				if (!TextUtil.isEmpty(attorneyData[10])){
					attorneyBO.setStateCode(attorneyData[10].toUpperCase());
				}
				
				if (!TextUtil.isEmpty(attorneyData[11])){
					attorneyBO.setZipCode(attorneyData[11]);
				}
				if (!TextUtil.isEmpty(attorneyData[12])){
					attorneyBO.setCountry(attorneyData[12]);
				}
				
				if (!TextUtil.isEmpty(attorneyData[13])){
					attorneyBO.setBusPhone(attorneyData[13].replaceAll("[^0-9.]", ""));
				}
				if (!TextUtil.isEmpty(attorneyData[14])){
					attorneyBO.setFaxNum(attorneyData[14].replaceAll("[^0-9.]", ""));
				}
				
				if (!TextUtil.isEmpty(attorneyData[15])){
					attorneyBO.setCellPhone(attorneyData[15].replaceAll("[^0-9.]", ""));
				}
				
				if (TextUtil.isEmpty(attorneyData[16])){
					attorneyBO.setBarStatus("N");
				} else if ("ACTIVE".equals(attorneyData[16].toUpperCase().trim())){
					attorneyBO.setBarStatus("A");
				} else if ("DECEASED".equals(attorneyData[16].toUpperCase().trim())){
					attorneyBO.setBarStatus("D");
				} else if ("INACTIVE".equals(attorneyData[16].toUpperCase().trim())){
					attorneyBO.setBarStatus("N");
				} else if ("SUSPENDED".equals(attorneyData[16].toUpperCase().trim())){
					attorneyBO.setBarStatus("S");
				}
				
				if (!TextUtil.isEmpty(attorneyData[17])){
					/////////////////////////////////////////////////////
					// email_address only
					// If @ is removed by space replace spce with @
					/////////////////////////////////////////////////////
					attorneyData[17]=attorneyData[17].replace(" ","@");
					attorneyBO.setEmailAddress(attorneyData[17]);
				}
				
				attorneyListBO.add(attorneyBO);
				
				attorneyBO = null;
			}
			attorneyData = null;
		}
		
		if (attorneyListBO.size() > 0 ){
	    	Connection conn = null;
    		//////////////////////////////////////////////////////////////////////////////////
    		// Each connection gets it own try so if its fails the other's still get updated
    		//////////////////////////////////////////////////////////////////////////////////
			if (productionDistrict){
		    	try {
		    		System.out.println("CORIS_PRODUCTION_DISTRICT_DB");
					conn = DatabaseConnection.getConnection(ConstantsConnectionProperties.CORIS_PRODUCTION_DISTRICT_DB);
					dataBaseInsertUpdateAttorneyInformation(conn, "D", attorneyListBO);
					conn.close();
				} catch (Exception e) {
					logger.info("Process Attorney Bar Association File CORIS_PRODUCTION_DISTRCIT_DB Connection fail");
				} finally {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("CORIS_PRODUCTION_DISTRICT_DB Connection Close", e);
					}
					conn = null;
				}
			}
    		//////////////////////////////////////////////////////////////////////////////////
    		// Each connection gets it own try so if its fails the other's still get updated
    		//////////////////////////////////////////////////////////////////////////////////
			if (productionJustice){
		    	try {
		    		System.out.println("CORIS_PRODUCTION_JUSTICE_DB");
					conn = DatabaseConnection.getConnection(ConstantsConnectionProperties.CORIS_PRODUCTION_JUSTICE_DB);
					dataBaseInsertUpdateAttorneyInformation(conn, "J", attorneyListBO);
					conn.close();
				} catch (Exception e) {
					logger.info("Process Attorney Bar Association File CORIS_PRODUCTION_JUSTICE_DB Connection fail");
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
			if (prodCare){
		    	try {
		    		System.out.println("CARE_PRODUCTION_DB");
					conn = DatabaseConnection.getConnection(ConstantsConnectionProperties.CARE_PRODUCTION_DB);
					dataBaseCareInsertAttorneyBarLoadInformation(conn, attorneyListBO);
					conn.close();
				} catch (Exception e) {
					logger.info("Process Attorney Bar Association File CARE_PRODUCTION_DB Connection fail");
				} finally {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("CARE_PRODUCTION_DB Connection Close", e);
					}
					conn = null;
				}
			}
			//////////////////////////////////////////////////////////////////////////////////
    		// No Email Address on Test or Development Server
    		//////////////////////////////////////////////////////////////////////////////////
			
			for (AttorneyBO attorneyBO :attorneyListBO){
				attorneyListBO.set(attorneyListBO.indexOf(attorneyBO),attorneyBO.setEmailAddress(null));
			}
			
			//////////////////////////////////////////////////////////////////////////////////
    		// Each connection gets it own try so if its fails the other's still get updated
    		//////////////////////////////////////////////////////////////////////////////////
			if (testDistrict){
		    	try {
		    		System.out.println("CORIS_TEST_DISTRICT_DB");
					conn = DatabaseConnection.getConnection(ConstantsConnectionProperties.CORIS_TEST_DISTRICT_DB);
					dataBaseInsertUpdateAttorneyInformation(conn, "D", attorneyListBO);
				} catch (Exception e) {
					logger.error("Process Attorney Bar Association File CORIS_TEST_DISTRCIT_DB Connection fail");
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
		    		System.out.println("CORIS_TEST_JUSTICE_DB");
					conn = DatabaseConnection.getConnection(ConstantsConnectionProperties.CORIS_TEST_JUSTICE_DB);
					dataBaseInsertUpdateAttorneyInformation(conn, "J", attorneyListBO);
				} catch (Exception e) {
					logger.info("Process Attorney Bar Association File CORIS_TEST_JUSTICE_DB Connection fail");
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
		    		System.out.println("CORIS_TRAINING_DB");
					conn = DatabaseConnection.getConnection(ConstantsConnectionProperties.CORIS_TRAINING_DB);
					dataBaseInsertUpdateAttorneyInformation(conn, "D", attorneyListBO);
				} catch (Exception e) {
					logger.info("Process Attorney Bar Association File CORIS_TRAINING_JUSTICE_DB Connection fail");
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
		    		System.out.println("CORIS_VERIFY_DB");
					conn = DatabaseConnection.getConnection(ConstantsConnectionProperties.CORIS_VERIFY_DB);
					dataBaseInsertUpdateAttorneyInformation(conn, "D", attorneyListBO);
					conn.close();
				} catch (Exception e) {
					logger.info("Process Attorney Bar Association File CORIS_VERIFY_JUSTICE_DB fail");
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
		    		System.out.println("CORIS_DEVELOPMENT_DB");
					conn = DatabaseConnection.getConnection(ConstantsConnectionProperties.CORIS_DEVELOPMENT_DB);
					dataBaseInsertUpdateAttorneyInformation(conn, "D", attorneyListBO);
					conn.close();
				} catch (Exception e) {
					logger.info("Process Attorney Bar Association File CORIS_DEVELOPMENT_DB fail");
				} finally {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("CORIS_DEVELOPMENT_DB Connection Close", e);
					}
					conn = null;
				}
			}
			
    		//////////////////////////////////////////////////////////////////////////////////
    		// Each connection gets it own try so if its fails the other's still get updated
    		//////////////////////////////////////////////////////////////////////////////////
			if (testCare){
		    	try {
		    		System.out.println("CARE_TEST_DB");
					conn = DatabaseConnection.getConnection(ConstantsConnectionProperties.CARE_TEST_DB);
					dataBaseCareInsertAttorneyBarLoadInformation(conn, attorneyListBO);
					conn.close();
				} catch (Exception e) {
					logger.info("Process Attorney Bar Association File CARE_TEST_DB Connection fail");
				} finally {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("CARE_TEST_DB Connection Close", e);
					}
					conn = null;
				}
			}
			
    		//////////////////////////////////////////////////////////////////////////////////
    		// Each connection gets it own try so if its fails the other's still get updated
    		//////////////////////////////////////////////////////////////////////////////////
			if (devCare){
		    	try {
		    		System.out.println("CARE_DEVELOPMENT_DB");
					conn = DatabaseConnection.getConnection(ConstantsConnectionProperties.CARE_DEVELOPMENT_DB);
					dataBaseCareInsertAttorneyBarLoadInformation(conn, attorneyListBO);
					conn.close();
				} catch (Exception e) {
					logger.info("Process Attorney Bar Association File CARE_DEVLOPMENT_DB Connection fail");
				} finally {
					try {
						conn.close();
					} catch (SQLException e) {
						logger.error("CARE_DEVEVELOPMENT_DB Connection Close", e);
					}
					conn = null;
				}
			}
			
		}
 	}
 	
    public static String convertBadCharacters(String processValue) {
  	  try {
  	    
  	    // Delete All ASCII Values < 32 or > 125
  		processValue = processValue.replaceAll("[^\\x20-\\x7d]", "");
   		
   		// Delete the &
  		processValue = processValue.replaceAll("[\\x26]", "");
   		
   		// Delete the *
  		processValue = processValue.replaceAll("[\\x2a]", "");
   		
   		// Delete the .
  		processValue = processValue.replaceAll("[\\x2e]", "");
  		
   		// Delete the .
  		processValue = processValue.replaceAll("[\\x2c]", "");
  		
   		//Final Check
   		for (int i = 0; i < processValue.length(); i++) {
  	      if (!XMLChar.isValid(processValue.charAt(i))) {
  	    	  processValue = processValue.replace(processValue.charAt(i), ' ');
  	      }
   		}
   		
   		processValue=TextUtil.filterIllegalChars(processValue);
   		
  	  } catch (Exception e) {
  	    e.printStackTrace();
  	  }
  	  return processValue;
  	}
    
    public static void dataBaseInsertUpdateAttorneyInformation(Connection conn, String courtType, List<AttorneyBO> attorneyListBO) {
    	int attorneyFound = 0;
    	
    	for (AttorneyBO attorneyBO: attorneyListBO){
    		
    		if (!TextUtil.isEmpty(attorneyBO.getBarState())){
				//////////////////////////////////////////////////////////////////////////
				// Does Attorney Exists Update else Insert
				//////////////////////////////////////////////////////////////////////////
				try {
					AttorneyBO attorneyCountBO = new AttorneyBO(courtType)
					.where(AttorneyBO.BARNUM,  attorneyBO.getBarNum())
					.where(AttorneyBO.BARSTATE,  attorneyBO.getBarState())
					.count(AttorneyBO.BARNUM)
					.setUseConnection(conn)
					.find();
					
					attorneyFound = attorneyCountBO.getCount();
					
					if (!TextUtil.isEmpty(attorneyBO.getBarState())){
						attorneyBO.setBarState(attorneyBO.getBarState().toUpperCase());
					}
					
					if (!TextUtil.isEmpty(attorneyBO.getPrefix())){
						attorneyBO.setPrefix(attorneyBO.getPrefix().toUpperCase());
					}
					
					if (!TextUtil.isEmpty(attorneyBO.getLastName())){
						attorneyBO.setLastName(attorneyBO.getLastName().toUpperCase());
					}
					
					if (!TextUtil.isEmpty(attorneyBO.getMiddleInitial())){
						attorneyBO.setMiddleInitial(attorneyBO.getMiddleInitial().toUpperCase());
					}
					
					if (!TextUtil.isEmpty(attorneyBO.getFirstName())){
						attorneyBO.setFirstName(attorneyBO.getFirstName().toUpperCase());
					}
					
					if (!TextUtil.isEmpty(attorneyBO.getOrganization())){
						attorneyBO.setOrganization(attorneyBO.getOrganization().toUpperCase());
					}
					
					if (!TextUtil.isEmpty(attorneyBO.getAddress1())){
						attorneyBO.setAddress1(attorneyBO.getAddress1().toUpperCase());
					}
					if (!TextUtil.isEmpty(attorneyBO.getAddress2())){
						attorneyBO.setAddress2(attorneyBO.getAddress2().toUpperCase());
					}
					if (!TextUtil.isEmpty(attorneyBO.getAddress3())){
						attorneyBO.setAddress3(attorneyBO.getAddress3().toUpperCase());
					}
					if (!TextUtil.isEmpty(attorneyBO.getCity())){
						attorneyBO.setCity(attorneyBO.getCity().toUpperCase());
					}
					if (!TextUtil.isEmpty(attorneyBO.getStateCode())){
						attorneyBO.setStateCode(attorneyBO.getStateCode().toUpperCase());
					}
					if (!TextUtil.isEmpty(attorneyBO.getCountry())){
						attorneyBO.setCountry(attorneyBO.getCountry().toUpperCase());
					}
					if (!TextUtil.isEmpty(attorneyBO.getEmailAddress())){
						attorneyBO.setEmailAddress(attorneyBO.getEmailAddress().toUpperCase());
					}
					
					if (attorneyFound > 0){
						new AttorneyBO(courtType)
							.where(AttorneyBO.BARNUM,  attorneyBO.getBarNum())
							.where(AttorneyBO.BARSTATE,  attorneyBO.getBarState())
							.setPrefix(attorneyBO.getPrefix())
							.setLastName(attorneyBO.getLastName())
							.setMiddleInitial(attorneyBO.getMiddleInitial())
							.setFirstName(attorneyBO.getFirstName())
							.setOrganization(attorneyBO.getOrganization())
							.setAddress1(attorneyBO.getAddress1())
							.setAddress2(attorneyBO.getAddress2())
							.setAddress3(attorneyBO.getAddress3())
							.setCity(attorneyBO.getCity())
							.setStateCode(attorneyBO.getStateCode())
							.setZipCode(attorneyBO.getZipCode())
							.setCountry(attorneyBO.getCountry())
							.setHomePhone(attorneyBO.getHomePhone())
							.setBusPhone(attorneyBO.getBusPhone())
							.setFaxNum(attorneyBO.getFaxNum())
							.setCellPhone(attorneyBO.getCellPhone())
							.setBarStatus(attorneyBO.getBarStatus())
							.setEmailAddress(attorneyBO.getEmailAddress())
							.setUseConnection(conn)
							.update();
					} else {
						new AttorneyBO(courtType)
						.setBarNum(attorneyBO.getBarNum())
						.setBarState(attorneyBO.getBarState())
						.setPrefix(attorneyBO.getPrefix())
						.setLastName(attorneyBO.getLastName())
						.setMiddleInitial(attorneyBO.getMiddleInitial())
						.setFirstName(attorneyBO.getFirstName())
						.setOrganization(attorneyBO.getOrganization())
						.setAddress1(attorneyBO.getAddress1())
						.setAddress2(attorneyBO.getAddress2())
						.setAddress3(attorneyBO.getAddress3())
						.setCity(attorneyBO.getCity())
						.setStateCode(attorneyBO.getStateCode())
						.setZipCode(attorneyBO.getZipCode())
						.setCountry(attorneyBO.getCountry())
						.setHomePhone(attorneyBO.getHomePhone())
						.setBusPhone(attorneyBO.getBusPhone())
						.setFaxNum(attorneyBO.getFaxNum())
						.setCellPhone(attorneyBO.getCellPhone())
						.setBarStatus(attorneyBO.getBarStatus())
						.setEmailAddress(attorneyBO.getEmailAddress())
						.setUseConnection(conn)
						.insert();
					}
				
					attorneyCountBO = null;
					attorneyFound = 0;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}	
			attorneyBO = null;
    	}
    }		
    public static void dataBaseCareInsertAttorneyBarLoadInformation(Connection conn, List<AttorneyBO> attorneyListBO) {
    	for (AttorneyBO attorneyBO: attorneyListBO){
    		if (!TextUtil.isEmpty(attorneyBO.getBarState())){
				//////////////////////////////////////////////////////////////////////////
				// Does Attorney Exists Update else Insert
				//////////////////////////////////////////////////////////////////////////
				try {
					new AttorneybarloadBO()
					.where(AttorneybarloadBO.BARNUMBER,  String.valueOf(attorneyBO.getBarNum()))
					.setUseConnection(conn)
					.delete();

					
					
					new AttorneybarloadBO()
					.setBarnumber(String.valueOf(attorneyBO.getBarNum()))
					.setPrefix(attorneyBO.getPrefix())
					.setLastname(attorneyBO.getLastName())
					.setMiddlename(attorneyBO.getMiddleInitial())
					.setFirstname(attorneyBO.getFirstName())
					.setFirmtitle(attorneyBO.getOrganization())
					.setAddrline1(attorneyBO.getAddress1())
					.setAddrline2(attorneyBO.getAddress2())
					.setCity(attorneyBO.getCity())
					.setState(attorneyBO.getStateCode())
					.setZip((attorneyBO.getZipCode()))
					.setPhone(attorneyBO.getBusPhone())
					.setFax(attorneyBO.getFaxNum())
					.setStatus(attorneyBO.getBarStatus())
					.setEmail(attorneyBO.getEmailAddress())
					.setUseConnection(conn)
					.insert();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}	
			attorneyBO = null;
    	}
    }		
	private static String validateAddress(String address){
		address = address.replaceAll(" ALLEY "," ALY "); 
		address = address.replaceAll(" ALLY "," ALY "); 
		address = address.replaceAll(" ANEX "," ANX "); 
		address = address.replaceAll(" ANNEX "," ANX "); 
		address = address.replaceAll(" ANNX "," ANX "); 
		address = address.replaceAll(" ARCADE "," ARC "); 
		address = address.replaceAll(" AV "," AVE "); 
		address = address.replaceAll(" AVEN "," AVE "); 
		address = address.replaceAll(" AVENU "," AVE "); 
		address = address.replaceAll(" AVENUE "," AVE "); 
		address = address.replaceAll(" AVN "," AVE "); 
		address = address.replaceAll(" AVNUE "," AVE "); 
		address = address.replaceAll(" BAYOO "," BYU "); 
		address = address.replaceAll(" BAYOU "," BYU "); 
		address = address.replaceAll(" BEACH "," BCH "); 
		address = address.replaceAll(" BEND "," BND "); 
		address = address.replaceAll(" BLUF "," BLF "); 
		address = address.replaceAll(" BLUFF "," BLF "); 
		address = address.replaceAll(" BLUFFS "," BLFS "); 
		address = address.replaceAll(" BOT "," BTM "); 
		address = address.replaceAll(" BOTTM "," BTM "); 
		address = address.replaceAll(" BOTTOM "," BTM "); 
		address = address.replaceAll(" BOUL "," BLVD "); 
		address = address.replaceAll(" BOULEVARD "," BLVD "); 
		address = address.replaceAll(" BOULV "," BLVD "); 
		address = address.replaceAll(" BRANCH "," BR "); 
		address = address.replaceAll(" BRDGE "," BRG "); 
		address = address.replaceAll(" BRIDGE "," BRG "); 
		address = address.replaceAll(" BRNCH "," BR "); 
		address = address.replaceAll(" BROOK "," BRK "); 
		address = address.replaceAll(" BROOKS "," BRKS "); 
		address = address.replaceAll(" BURG "," BG "); 
		address = address.replaceAll(" BURGS "," BGS "); 
		address = address.replaceAll(" BYPA "," BYP "); 
		address = address.replaceAll(" BYPAS "," BYP "); 
		address = address.replaceAll(" BYPASS "," BYP "); 
		address = address.replaceAll(" BYPS "," BYP "); 
		address = address.replaceAll(" CAMP "," CP "); 
		address = address.replaceAll(" CANYN "," CYN "); 
		address = address.replaceAll(" CANYON "," CYN "); 
		address = address.replaceAll(" CAPE "," CPE "); 
		address = address.replaceAll(" CAUSEWAY "," CSWY "); 
		address = address.replaceAll(" CAUSWAY "," CSWY "); 
		address = address.replaceAll(" CEN "," CTR "); 
		address = address.replaceAll(" CENT "," CTR "); 
		address = address.replaceAll(" CENTER "," CTR "); 
		address = address.replaceAll(" CENTERS "," CTRS "); 
		address = address.replaceAll(" CENTR "," CTR "); 
		address = address.replaceAll(" CENTRE "," CTR "); 
		address = address.replaceAll(" CIRC "," CIR "); 
		address = address.replaceAll(" CIRCL "," CIR "); 
		address = address.replaceAll(" CIRCLE "," CIR "); 
		address = address.replaceAll(" CIRCLES "," CIRS "); 
		address = address.replaceAll(" CK "," CRK "); 
		address = address.replaceAll(" CLIFF "," CLF "); 
		address = address.replaceAll(" CLIFFS "," CLFS "); 
		address = address.replaceAll(" CLUB "," CLB "); 
		address = address.replaceAll(" CMP "," CP "); 
		address = address.replaceAll(" CNTER "," CTR "); 
		address = address.replaceAll(" CNTR "," CTR "); 
		address = address.replaceAll(" CNYN "," CYN "); 
		address = address.replaceAll(" COMMON "," CMN "); 
		address = address.replaceAll(" CORNER "," COR "); 
		address = address.replaceAll(" CORNERS "," CORS "); 
		address = address.replaceAll(" COURSE "," CRSE "); 
		address = address.replaceAll(" COURT "," CT "); 
		address = address.replaceAll(" COURTS "," CTS "); 
		address = address.replaceAll(" COVE "," CV "); 
		address = address.replaceAll(" COVES "," CVS "); 
		address = address.replaceAll(" CR "," CRK "); 
		address = address.replaceAll(" CRCL "," CIR "); 
		address = address.replaceAll(" CRCLE "," CIR "); 
		address = address.replaceAll(" CRECENT "," CRES "); 
		address = address.replaceAll(" CREEK "," CRK "); 
		address = address.replaceAll(" CRESCENT "," CRES "); 
		address = address.replaceAll(" CRESENT "," CRES "); 
		address = address.replaceAll(" CREST "," CRST "); 
		address = address.replaceAll(" CROSSING "," XING "); 
		address = address.replaceAll(" CROSSROAD "," XRD "); 
		address = address.replaceAll(" CRSCNT "," CRES "); 
		address = address.replaceAll(" CRSENT "," CRES "); 
		address = address.replaceAll(" CRSNT "," CRES "); 
		address = address.replaceAll(" CRSSING "," XING "); 
		address = address.replaceAll(" CRSSNG "," XING "); 
		address = address.replaceAll(" CT "," CTS "); 
		address = address.replaceAll(" CRT "," CT "); 
		address = address.replaceAll(" CURVE "," CURV "); 
		address = address.replaceAll(" DALE "," DL "); 
		address = address.replaceAll(" DAM "," DM "); 
		address = address.replaceAll(" DIV "," DV "); 
		address = address.replaceAll(" DIVIDE "," DV "); 
		address = address.replaceAll(" DRIV "," DR "); 
		address = address.replaceAll(" DRIVE "," DR "); 
		address = address.replaceAll(" DRIVES "," DRS "); 
		address = address.replaceAll(" DRV "," DR "); 
		address = address.replaceAll(" DVD "," DV "); 
		address = address.replaceAll(" ESTATE "," EST "); 
		address = address.replaceAll(" ESTATES "," ESTS "); 
		address = address.replaceAll(" EXP "," EXPY "); 
		address = address.replaceAll(" EXPR "," EXPY "); 
		address = address.replaceAll(" EXPRESS "," EXPY "); 
		address = address.replaceAll(" EXPRESSWAY "," EXPY "); 
		address = address.replaceAll(" EXPW "," EXPY "); 
		address = address.replaceAll(" EXTENSION "," EXT "); 
		address = address.replaceAll(" EXTENSIONS "," EXTS "); 
		address = address.replaceAll(" EXTN "," EXT "); 
		address = address.replaceAll(" EXTNSN "," EXT "); 
		address = address.replaceAll(" FALLS "," FLS "); 
		address = address.replaceAll(" FERRY "," FRY "); 
		address = address.replaceAll(" FIELD "," FLD "); 
		address = address.replaceAll(" FIELDS "," FLDS "); 
		address = address.replaceAll(" FLAT "," FLT "); 
		address = address.replaceAll(" FLATS "," FLTS "); 
		address = address.replaceAll(" FORD "," FRD "); 
		address = address.replaceAll(" FORDS "," FRDS "); 
		address = address.replaceAll(" FOREST "," FRST "); 
		address = address.replaceAll(" FORESTS "," FRST "); 
		address = address.replaceAll(" FORG "," FRG "); 
		address = address.replaceAll(" FORGE "," FRG "); 
		address = address.replaceAll(" FORGES "," FRGS "); 
		address = address.replaceAll(" FORK "," FRK "); 
		address = address.replaceAll(" FORKS "," FRKS "); 
		address = address.replaceAll(" FORT "," FT "); 
		address = address.replaceAll(" FREEWAY "," FWY "); 
		address = address.replaceAll(" FREEWY "," FWY "); 
		address = address.replaceAll(" FRRY "," FRY "); 
		address = address.replaceAll(" FRT "," FT "); 
		address = address.replaceAll(" FRWAY "," FWY "); 
		address = address.replaceAll(" FRWY "," FWY "); 
		address = address.replaceAll(" GARDEN "," GDN "); 
		address = address.replaceAll(" GARDENS "," GDNS "); 
		address = address.replaceAll(" GARDN "," GDN "); 
		address = address.replaceAll(" GATEWAY "," GTWY "); 
		address = address.replaceAll(" GATEWY "," GTWY "); 
		address = address.replaceAll(" GATWAY "," GTWY "); 
		address = address.replaceAll(" GLEN "," GLN "); 
		address = address.replaceAll(" GLENS "," GLNS "); 
		address = address.replaceAll(" GRDEN "," GDN "); 
		address = address.replaceAll(" GRDN "," GDN "); 
		address = address.replaceAll(" GRDNS "," GDNS "); 
		address = address.replaceAll(" GREEN "," GRN "); 
		address = address.replaceAll(" GREENS "," GRNS "); 
		address = address.replaceAll(" GROV "," GRV "); 
		address = address.replaceAll(" GROVE "," GRV "); 
		address = address.replaceAll(" GROVES "," GRVS "); 
		address = address.replaceAll(" GTWAY "," GTWY "); 
		address = address.replaceAll(" HARB "," HBR "); 
		address = address.replaceAll(" HARBOR "," HBR "); 
		address = address.replaceAll(" HARBORS "," HBRS "); 
		address = address.replaceAll(" HARBR "," HBR "); 
		address = address.replaceAll(" HAVEN "," HVN "); 
		address = address.replaceAll(" HAVN "," HVN "); 
		address = address.replaceAll(" HEIGHT "," HTS "); 
		address = address.replaceAll(" HEIGHTS "," HTS "); 
		address = address.replaceAll(" HGTS "," HTS "); 
		address = address.replaceAll(" HIGHWAY "," HWY "); 
		address = address.replaceAll(" HIGHWY "," HWY "); 
		address = address.replaceAll(" HILL "," HL "); 
		address = address.replaceAll(" HILLS "," HLS "); 
		address = address.replaceAll(" HIWAY "," HWY "); 
		address = address.replaceAll(" HIWY "," HWY "); 
		address = address.replaceAll(" HLLW "," HOLW "); 
		address = address.replaceAll(" HOLLOW "," HOLW "); 
		address = address.replaceAll(" HOLLOWS "," HOLW "); 
		address = address.replaceAll(" HOLWS "," HOLW "); 
		address = address.replaceAll(" HRBOR "," HBR "); 
		address = address.replaceAll(" HT "," HTS "); 
		address = address.replaceAll(" HWAY "," HWY "); 
		address = address.replaceAll(" INLET "," INLT "); 
		address = address.replaceAll(" ISLAND "," IS "); 
		address = address.replaceAll(" ISLANDS "," ISS "); 
		address = address.replaceAll(" ISLES "," ISLE "); 
		address = address.replaceAll(" ISLND "," IS "); 
		address = address.replaceAll(" ISLNDS "," ISS "); 
		address = address.replaceAll(" JCTION "," JCT "); 
		address = address.replaceAll(" JCTN "," JCT "); 
		address = address.replaceAll(" JCTNS "," JCTS "); 
		address = address.replaceAll(" JUNCTION "," JCT "); 
		address = address.replaceAll(" JUNCTIONS "," JCTS "); 
		address = address.replaceAll(" JUNCTN "," JCT "); 
		address = address.replaceAll(" JUNCTON "," JCT "); 
		address = address.replaceAll(" KEY "," KY "); 
		address = address.replaceAll(" KEYS "," KYS "); 
		address = address.replaceAll(" KNOL "," KNL "); 
		address = address.replaceAll(" KNOLL "," KNL "); 
		address = address.replaceAll(" KNOLLS "," KNLS "); 
		address = address.replaceAll(" LA "," LN "); 
		address = address.replaceAll(" LAKE "," LK "); 
		address = address.replaceAll(" LAKES "," LKS "); 
		address = address.replaceAll(" LANDING "," LNDG "); 
		address = address.replaceAll(" LANE "," LN "); 
		address = address.replaceAll(" LANES "," LN "); 
		address = address.replaceAll(" LDGE "," LDG "); 
		address = address.replaceAll(" LIGHT "," LGT "); 
		address = address.replaceAll(" LIGHTS "," LGTS "); 
		address = address.replaceAll(" LNDNG "," LNDG "); 
		address = address.replaceAll(" LOAF "," LF "); 
		address = address.replaceAll(" LOCK "," LCK "); 
		address = address.replaceAll(" LOCKS "," LCKS "); 
		address = address.replaceAll(" LODG "," LDG "); 
		address = address.replaceAll(" LODGE "," LDG "); 
		address = address.replaceAll(" LOOPS "," LOOP "); 
		address = address.replaceAll(" MANOR "," MNR "); 
		address = address.replaceAll(" MANORS "," MNRS "); 
		address = address.replaceAll(" MEADOW "," MDW "); 
		address = address.replaceAll(" MEADOWS "," MDWS "); 
		address = address.replaceAll(" MEDOWS "," MDWS "); 
		address = address.replaceAll(" MILL "," ML "); 
		address = address.replaceAll(" MILLS "," MLS "); 
		address = address.replaceAll(" MISSION "," MSN "); 
		address = address.replaceAll(" MISSN "," MSN "); 
		address = address.replaceAll(" MNT "," MT "); 
		address = address.replaceAll(" MNTAIN "," MTN "); 
		address = address.replaceAll(" MNTN "," MTN "); 
		address = address.replaceAll(" MNTNS "," MTNS "); 
		address = address.replaceAll(" MOTORWAY "," MTWY "); 
		address = address.replaceAll(" MOUNT "," MT "); 
		address = address.replaceAll(" MOUNTAIN "," MTN "); 
		address = address.replaceAll(" MOUNTAINS "," MTNS "); 
		address = address.replaceAll(" MOUNTIN "," MTN "); 
		address = address.replaceAll(" MSSN "," MSN "); 
		address = address.replaceAll(" MTIN "," MTN "); 
		address = address.replaceAll(" NECK "," NCK "); 
		address = address.replaceAll(" ORCHARD "," ORCH "); 
		address = address.replaceAll(" ORCHRD "," ORCH "); 
		address = address.replaceAll(" OVERPASS "," OPAS "); 
		address = address.replaceAll(" OVL "," OVAL "); 
		address = address.replaceAll(" PARKS "," PARK "); 
		address = address.replaceAll(" PARKWAY "," PKWY "); 
		address = address.replaceAll(" PARKWAYS "," PKWY "); 
		address = address.replaceAll(" PARKWY "," PKWY "); 
		address = address.replaceAll(" PASSAGE "," PSGE "); 
		address = address.replaceAll(" PATHS "," PATH "); 
		address = address.replaceAll(" PIKES "," PIKE "); 
		address = address.replaceAll(" PINE "," PNE "); 
		address = address.replaceAll(" PINES "," PNES "); 
		address = address.replaceAll(" PK "," PARK "); 
		address = address.replaceAll(" PKWAY "," PKWY "); 
		address = address.replaceAll(" PKWYS "," PKWY "); 
		address = address.replaceAll(" PKY "," PKWY "); 
		address = address.replaceAll(" PLACE "," PL "); 
		address = address.replaceAll(" PLAIN "," PLN "); 
		address = address.replaceAll(" PLAINES "," PLNS "); 
		address = address.replaceAll(" PLAINS "," PLNS "); 
		address = address.replaceAll(" PLAZA "," PLZ "); 
		address = address.replaceAll(" PLZA "," PLZ "); 
		address = address.replaceAll(" POINT "," PT "); 
		address = address.replaceAll(" POINTS "," PTS "); 
		address = address.replaceAll(" PORT "," PRT "); 
		address = address.replaceAll(" PORTS "," PRTS "); 
		address = address.replaceAll(" PRAIRIE "," PR "); 
		address = address.replaceAll(" PRARIE "," PR "); 
		address = address.replaceAll(" PRK "," PARK "); 
		address = address.replaceAll(" PRR "," PR "); 
		address = address.replaceAll(" RAD "," RADL "); 
		address = address.replaceAll(" RADIAL "," RADL "); 
		address = address.replaceAll(" RADIEL "," RADL "); 
		address = address.replaceAll(" RANCH "," RNCH "); 
		address = address.replaceAll(" RANCHES "," RNCH "); 
		address = address.replaceAll(" RAPID "," RPD "); 
		address = address.replaceAll(" RAPIDS "," RPDS "); 
		address = address.replaceAll(" RDGE "," RDG "); 
		address = address.replaceAll(" REST "," RST "); 
		address = address.replaceAll(" RIDGE "," RDG "); 
		address = address.replaceAll(" RIDGES "," RDGS "); 
		address = address.replaceAll(" RIVER "," RIV "); 
		address = address.replaceAll(" RIVR "," RIV "); 
		address = address.replaceAll(" RNCHS "," RNCH "); 
		address = address.replaceAll(" ROAD "," RD "); 
		address = address.replaceAll(" ROADS "," RDS "); 
		address = address.replaceAll(" ROUTE "," RTE "); 
		address = address.replaceAll(" RVR "," RIV "); 
		address = address.replaceAll(" SHOAL "," SHL "); 
		address = address.replaceAll(" SHOALS "," SHLS "); 
		address = address.replaceAll(" SHOAR "," SHR "); 
		address = address.replaceAll(" SHOARS "," SHRS "); 
		address = address.replaceAll(" SHORE "," SHR "); 
		address = address.replaceAll(" SHORES "," SHRS "); 
		address = address.replaceAll(" SKYWAY "," SKWY "); 
		address = address.replaceAll(" SPNG "," SPG "); 
		address = address.replaceAll(" SPNGS "," SPGS "); 
		address = address.replaceAll(" SPRING "," SPG "); 
		address = address.replaceAll(" SPRINGS "," SPGS "); 
		address = address.replaceAll(" SPRNG "," SPG "); 
		address = address.replaceAll(" SPRNGS "," SPGS "); 
		address = address.replaceAll(" SPURS "," SPUR "); 
		address = address.replaceAll(" SQR "," SQ "); 
		address = address.replaceAll(" SQRE "," SQ "); 
		address = address.replaceAll(" SQRS "," SQS "); 
		address = address.replaceAll(" SQU "," SQ "); 
		address = address.replaceAll(" SQUARE "," SQ "); 
		address = address.replaceAll(" SQUARES "," SQS "); 
		address = address.replaceAll(" STATION "," STA "); 
		address = address.replaceAll(" STATN "," STA "); 
		address = address.replaceAll(" STN "," STA "); 
		address = address.replaceAll(" STR "," ST "); 
		address = address.replaceAll(" STRAV "," STRA "); 
		address = address.replaceAll(" STRAVE "," STRA "); 
		address = address.replaceAll(" STRAVEN "," STRA "); 
		address = address.replaceAll(" STRAVENUE "," STRA "); 
		address = address.replaceAll(" STRAVN "," STRA "); 
		address = address.replaceAll(" STREAM "," STRM "); 
		address = address.replaceAll(" STREET "," ST "); 
		address = address.replaceAll(" STREETS "," STS "); 
		address = address.replaceAll(" STREME "," STRM "); 
		address = address.replaceAll(" STRT "," ST "); 
		address = address.replaceAll(" STRVN "," STRA "); 
		address = address.replaceAll(" STRVNUE "," STRA "); 
		address = address.replaceAll(" SUMIT "," SMT "); 
		address = address.replaceAll(" SUMITT "," SMT "); 
		address = address.replaceAll(" SUMMIT "," SMT "); 
		address = address.replaceAll(" TERR "," TER "); 
		address = address.replaceAll(" TERRACE "," TER "); 
		address = address.replaceAll(" THROUGHWAY "," TRWY "); 
		address = address.replaceAll(" TPK "," TPKE "); 
		address = address.replaceAll(" TR "," TRL "); 
		address = address.replaceAll(" TRACE "," TRCE "); 
		address = address.replaceAll(" TRACES "," TRCE "); 
		address = address.replaceAll(" TRACK "," TRAK "); 
		address = address.replaceAll(" TRACKS "," TRAK "); 
		address = address.replaceAll(" TRAFFICWAY "," TRFY "); 
		address = address.replaceAll(" TRAIL "," TRL "); 
		address = address.replaceAll(" TRAILS "," TRL "); 
		address = address.replaceAll(" TRK "," TRAK "); 
		address = address.replaceAll(" TRKS "," TRAK "); 
		address = address.replaceAll(" TRLS "," TRL "); 
		address = address.replaceAll(" TRNPK "," TPKE "); 
		address = address.replaceAll(" TRPK "," TPKE "); 
		address = address.replaceAll(" TUNEL "," TUNL "); 
		address = address.replaceAll(" TUNLS "," TUNL "); 
		address = address.replaceAll(" TUNNEL "," TUNL "); 
		address = address.replaceAll(" TUNNELS "," TUNL "); 
		address = address.replaceAll(" TUNNL "," TUNL "); 
		address = address.replaceAll(" TURNPIKE "," TPKE "); 
		address = address.replaceAll(" TURNPK "," TPKE "); 
		address = address.replaceAll(" UNDERPASS "," UPAS "); 
		address = address.replaceAll(" UNION "," UN "); 
		address = address.replaceAll(" UNIONS "," UNS "); 
		address = address.replaceAll(" VALLEY "," VLY "); 
		address = address.replaceAll(" VALLEYS "," VLYS "); 
		address = address.replaceAll(" VALLY "," VLY "); 
		address = address.replaceAll(" VDCT "," VIA "); 
		address = address.replaceAll(" VIADCT "," VIA "); 
		address = address.replaceAll(" VIADUCT "," VIA "); 
		address = address.replaceAll(" VIEW "," VW "); 
		address = address.replaceAll(" VIEWS "," VWS "); 
		address = address.replaceAll(" VILL "," VLG "); 
		address = address.replaceAll(" VILLAG "," VLG "); 
		address = address.replaceAll(" VILLAGE "," VLG "); 
		address = address.replaceAll(" VILLAGES "," VLGS "); 
		address = address.replaceAll(" VILLE "," VL "); 
		address = address.replaceAll(" VILLG "," VLG "); 
		address = address.replaceAll(" VILLIAGE "," VLG "); 
		address = address.replaceAll(" VIST "," VIS "); 
		address = address.replaceAll(" VISTA "," VIS "); 
		address = address.replaceAll(" VLLY "," VLY "); 
		address = address.replaceAll(" VST "," VIS "); 
		address = address.replaceAll(" VSTA "," VIS "); 
		address = address.replaceAll(" WALKS "," WALK "); 
		address = address.replaceAll(" WELL "," WL "); 
		address = address.replaceAll(" WELLS "," WLS "); 
		address = address.replaceAll(" WY "," WAY "); 
		address = address.replaceAll(" APARTMENT "," APT ");
		address = address.replaceAll(" BASEMENT "," BSMT ");
		address = address.replaceAll(" BUILDING "," BLDG ");
		address = address.replaceAll(" DEPARTMENT "," DEPT ");
		address = address.replaceAll(" FLOOR "," FL ");
		address = address.replaceAll(" FRONT "," FRNT ");
		address = address.replaceAll(" HANGAR "," HNGR ");
		address = address.replaceAll(" LOBBY "," LBBY ");
		address = address.replaceAll(" LOWER "," LOWR ");
		address = address.replaceAll(" OFFICE "," OFC ");
		address = address.replaceAll(" PENTHOUSE "," PH ");
		address = address.replaceAll(" ROOM "," RM ");
		address = address.replaceAll(" SPACE "," SPC ");
		address = address.replaceAll(" SUITE "," STE ");
		address = address.replaceAll(" TRAILER "," TRLR ");
		address = address.replaceAll(" UPPER "," UPPR ");
		address = address.replaceAll(" NO "," N ");
		address = address.replaceAll(" NOR "," N ");
		address = address.replaceAll(" NORT "," N ");
		address = address.replaceAll(" NORTH "," N ");
		address = address.replaceAll(" NTH "," N ");
		address = address.replaceAll(" SO "," S ");
		address = address.replaceAll(" SOU "," S ");
		address = address.replaceAll(" SOUT "," S ");
		address = address.replaceAll(" SOUTH "," S ");
		address = address.replaceAll(" STH "," S ");
		address = address.replaceAll(" WE "," W ");
		address = address.replaceAll(" WES "," W ");
		address = address.replaceAll(" WEST "," W ");
		address = address.replaceAll(" WST "," W ");
		address = address.replaceAll(" EA "," E ");
		address = address.replaceAll(" EAS "," E ");
		address = address.replaceAll(" EAST "," E ");
		address = address.replaceAll(" EST "," E ");
		address = address.replaceAll(" P O "," PO ");
		address = address.replaceAll(" BX "," BOX ");
		address = address.replaceAll(" STREE "," ST ");
		address = address.replaceAll(" STRE "," ST ");
		address = address.replaceAll(" STR "," ST ");
		address = address.replaceAll(" DRV "," DR ");
		address = address.replaceAll(" AV "," AVE ");
		address = address.replaceAll(" ROA "," RD ");
		address = address.replaceAll(" RO "," RD ");
		address = address.replaceAll(" APARTMEN "," APT ");
		address = address.replaceAll(" APARTME "," APT ");
		address = address.replaceAll(" APARTM "," APT ");
		address = address.replaceAll(" APART "," APT ");
		address = address.replaceAll(" APAR "," APT ");
		address = address.replaceAll(" STREE "," ST ");
		address = address.replaceAll(" STRE "," ST ");
		address = address.replaceAll(" STR "," ST ");
		address = address.replaceAll("P O ","PO ");
		return address;
	}
}

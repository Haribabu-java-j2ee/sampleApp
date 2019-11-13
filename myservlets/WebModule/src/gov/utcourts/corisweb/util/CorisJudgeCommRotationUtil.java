package gov.utcourts.corisweb.util;

import gov.utcourts.coriscommon.dataaccess.calendar.CalendarBO;
import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
import gov.utcourts.coriscommon.dataaccess.judgehist.JudgeHistBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StoredProcedureDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;
import gov.utcourts.courtscommon.dispatcher.StoredProcedureDispatcher;
import gov.utcourts.courtscommon.dispatcher.parameters.InputParameters;
import gov.utcourts.courtscommon.dispatcher.worker.OutputContainer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

import javax.servlet.ServletException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class CorisJudgeCommRotationUtil {

	static final Font reportFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
	static final Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.NORMAL);
	
	public interface CJCRConstants {
		/** Used to format dates for display.*/
		public static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		public static final int commitAmount = 1000;
	}
	
	static class reportMyHeaderFooter extends PdfPageEventHelper {
		
		public void onStartPage(PdfWriter writer, Document document) {
			float[] widths = new float[5];
			widths[0] = 20.0f;
			widths[1] = 20.0f;
			widths[2] = 20.0f;
			widths[3] = 20.0f;
			widths[4] = 20.0f;
			PdfPTable table = new PdfPTable(5);
			table.setWidthPercentage(100);
			try {
				table.setWidths(widths);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			addCell(table, "Case Number", reportFont, Element.ALIGN_LEFT, Rectangle.BOTTOM, 1);
			addCell(table, "Case Type", reportFont, Element.ALIGN_LEFT, Rectangle.BOTTOM, 1);
			addCell(table, "From", reportFont, Element.ALIGN_LEFT, Rectangle.BOTTOM, 1);
			addCell(table, "To", reportFont, Element.ALIGN_LEFT, Rectangle.BOTTOM, 1);
			addCell(table, "User", reportFont, Element.ALIGN_LEFT, Rectangle.BOTTOM, 1);

			try {
				document.add(new Paragraph("\n\n"));
				document.add(table);
			} catch (DocumentException e) {
				e.printStackTrace();
			}

			PdfContentByte cb = writer.getDirectContent();
			Phrase header = new Phrase("Judge Commissioner Change Report", titleFont);
			ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, header, (document.right() - document.left()) / 2 + document.leftMargin(), document.top() - 15, 0);
		}
		
		public void onEndPage(PdfWriter writer, Document document) {
			Date today = Calendar.getInstance().getTime();
			String printedeDate = CJCRConstants.dateFormat.format(today);
			PdfContentByte cb = writer.getDirectContent();
			Phrase footer = new Phrase("Printed " + printedeDate , reportFont);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, footer, (document.left()) + document.leftMargin(), document.bottom() - 10, 0);
			Phrase footer1 = new Phrase(" Page " + document.getPageNumber(), reportFont);
			ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer1, (document.right() - document.left()) / 2 + document.leftMargin(), document.bottom() - 10,0);
		}

		public void onCloseDocument(PdfWriter writer, Document document) {
			Date today = Calendar.getInstance().getTime();
			String printedeDate  = CJCRConstants.dateFormat.format(today);
			PdfContentByte cb = writer.getDirectContent();
			Phrase footer = new Phrase("Printed " + printedeDate , reportFont);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, footer, (document.left()) + document.leftMargin(), document.bottom() - 10, 0);
			Phrase footer1 = new Phrase(" Page (last) " + (document.getPageNumber() - 1), reportFont);
			ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer1, (document.right() - document.left()) / 2 + document.leftMargin(), document.bottom() - 10,0);
		}
	}
	
	public static byte[] processRotation(int useridSrl, String type, int fromJudgeCommId, int toJudgeCommId, String locnCode, String courtType, String caseCategory, String caseType, String disposedCases, int rotateAmount) throws Exception { 
		byte[] report = null;
		List<Integer> selectCases = null;
	    List<KaseBO>  casesData = null;
	    String clerkLogName = null;
	    String toJudgeCommLogName = null;
		long endTime = 0;
		long startTime = 0;
		String elapsedTime = null;
	    
		System.out.println("Coris Judge Comm Rotation Start");
		
		///////////////////////////////////////////////////////////////////
		// Start Timing
		///////////////////////////////////////////////////////////////////
		startTime = System.currentTimeMillis();

		if ("--".equals(caseCategory)){
			caseCategory = null;
		}
		
		if ("--".equals(caseType)){
			caseType = null;
		}
		
        ///////////////////////////////////////////////////////////////
		// Output command line
		///////////////////////////////////////////////////////////////
//		printMessage("CorisJudgeCommRotation " + args[0] + " " + args[1] + " " + args[2] + " " + args[3] + " " + args[4] + " " + args[5] + " " + args[6] + " " + args[7] + " " + args[8] + " " + args[9], "Y");
		
        ///////////////////////////////////////////////////////////////
		// Get User Logname 
		///////////////////////////////////////////////////////////////
        try {
    	    clerkLogName = getLogName(useridSrl, locnCode, courtType);
		} catch (Exception e) {
            e.printStackTrace();
		}
		
		if ("".equals(clerkLogName) || clerkLogName == null){
			printMessage("Bad Clerk Logname", "Y");			
		}
	    
        ///////////////////////////////////////////////////////////////
		// Get From Judge Log name Name 
		///////////////////////////////////////////////////////////////
        try {
        	toJudgeCommLogName = getLogName(toJudgeCommId, locnCode, courtType);
		} catch (Exception e) {
            e.printStackTrace();
			printException(e);
		}
		
		if ("".equals(toJudgeCommLogName) || toJudgeCommLogName == null){
			printMessage("Bad to Judge Logname", "Y");			
		}
		
		System.out.println("Get Cases");
		if (!"".equals(caseCategory) && caseCategory != null){
			////////////////////////////////////////////////////////////////////
			// Get Available Cases by Case Category
			////////////////////////////////////////////////////////////////////
			selectCases = getCasesByCategory(fromJudgeCommId, toJudgeCommId, type, locnCode, courtType, caseCategory, disposedCases, useridSrl);
		} else {
			////////////////////////////////////////////////////////////////////
			// Get Available Cases by Case Type
			////////////////////////////////////////////////////////////////////
			selectCases = getCasesByCaseType(fromJudgeCommId, toJudgeCommId, type, locnCode, courtType, caseType, disposedCases, useridSrl);
		}
		System.out.println("Case Size " + selectCases.size());
		
		////////////////////////////////////////////////////////////////////
		// Randomly Select Cases
		////////////////////////////////////////////////////////////////////
		System.out.println("Randomly Select Cases");
		casesData = selectRandomCases(selectCases, rotateAmount, useridSrl, courtType);
		selectCases = null;
		////////////////////////////////////////////////////////////////////
		// Get Current Case Data
		////////////////////////////////////////////////////////////////////
		System.out.println("Get Case Data");
		casesData = getCurrentCaseData(casesData, type, courtType);
		System.out.println("Case Selected " + casesData.size());
		
		////////////////////////////////////////////////////////////////////
		// Change Judge/Comm Assignment
		////////////////////////////////////////////////////////////////////
		System.out.println("Change Judge/Comm Assignment");
		changeJudgeAssignment(casesData, useridSrl, type, fromJudgeCommId, toJudgeCommId, courtType);
		
		try {
			System.out.println("Generate Judge/Comm Reassignment Report");
			report = createReport(casesData, toJudgeCommLogName, clerkLogName, useridSrl, locnCode, courtType);
		} catch (DocumentException e) {
			e.printStackTrace();
			printException(e);
		} catch (IOException e) {
			e.printStackTrace();
			printException(e);
		}
		
		selectCases = null;
	    casesData = null;
	    clerkLogName = null;
	    toJudgeCommLogName = null;
		
		///////////////////////////////////////////////////////////////////
		// Calculate Elapsed Time
		///////////////////////////////////////////////////////////////////
		endTime = System.currentTimeMillis();
		elapsedTime = calculateElapsedTime(endTime, startTime);
		printMessage("Operation took " + elapsedTime , "Y");
		System.out.println("Coris Judge Comm Rotation Complete");
		
		///////////////////////////////////////////////////////////////////
		// Clean Up
		///////////////////////////////////////////////////////////////////
		selectCases = null;
	    casesData = null;
	    clerkLogName = null;
	    toJudgeCommLogName = null;
		endTime = 0;
		startTime = 0;
		elapsedTime = null;
		
		return report;
	}
	
    /**
     * This writes the same message to two files, the first being a running log of errors
     * @param error the message you wish to log 
     * 
     */
    static void logMessage(String message) {
        System.out.println(new java.util.Date().toString() + " " + message);
    }
    
    public static void printException(Exception exc) {
        if (exc.getMessage() != null) {
            logMessage(exc.getMessage());
        }
    }

    /**
	 * Used to manually write message to a file.  
	 * 
	 */
    public static void printMessage(String message, String writeToFile) {
		if ("Y".equals(writeToFile.toUpperCase())){
			if (message != null) {
				logMessage(message);
				logMessage("----------------------------------");
			}
		}
    }
    
    /**
	 * Used to build the middle part of a sql statement.  
	 * The pre sql (the select statement) must be added first and then any "order by" is added after this method.
	 * 
	 * @param preSql the string representing the select statement (select + items to be selected).
	 * @param tables a collection of strings representing the tables in the "from" clause -- "from" 
	 * is added by this method.
	 * @param where a collection of strings, each representing a condition in the "where" clause.  This method
	 * adds "where" before this list and "and" between each condition.
	 * @return the completed sql statement constructed from the parameters.
	 * 
	 */
	protected static String buildSQL(String preSql, Collection<String> tables, Collection<String> where){
		String retVal;
		Collection<String> uniqueTables = new TreeSet<String>(tables);
		Iterator<String> it = uniqueTables.iterator();
		String table = it.next();
		StringBuilder work = new StringBuilder(preSql);		
		work.append(" from ");
		work.append(table);
		while (it.hasNext()) {
			table = it.next();
			work.append(", ");
			work.append(table);
		}
		it = where.iterator();
		String temp;
		if (it.hasNext()) {
			temp = it.next();
			work.append(" where ");
			work.append(temp);
			while (it.hasNext()) {
				temp = it.next();
				work.append(" and ");
				work.append(temp);
			}
		}
		retVal = work.toString();
		return retVal;
	}
	
    /**
	 * Get case by case type.
	 * @parm fromJudgeCommId  
	 * @parm toJudgeCommId  
	 * @parm type  
	 * @parm Location Code  
	 * @parm Court Type  
	 * @parm Case Type  
	 * @parm Disposed Cases  
	 * @parm Clerk Id Requesting the change
	 * @return listing of available cases   
	 */
	public static List<Integer> getCasesByCaseType(int fromJudgeCommId, int toJudgeCommId, String type, String locnCode, String courtType, String caseType, String disposedCases, int useridSrl) throws SQLException{
		List<Integer> retVal = new ArrayList<Integer>();
        try {
    		KaseBO kaseBO = new KaseBO(courtType);
    	
    		if (fromJudgeCommId > 0){
    			if ("J".equals(type)){
    				kaseBO.where(KaseBO.ASSNJUDGEID, fromJudgeCommId);
    			} else {
    				kaseBO.where(KaseBO.ASSNCOMMID, fromJudgeCommId);
    			}
    		}
        	
    		if (toJudgeCommId > 0){
    			if ("J".equals(type)){
    				kaseBO.where(KaseBO.ASSNJUDGEID, Exp.NOT_EQUALS, toJudgeCommId);
    			} else{
    				kaseBO.where(KaseBO.ASSNCOMMID, Exp.NOT_EQUALS, toJudgeCommId);
    			}
    		}
        	
       		if (!"".equals(locnCode) && locnCode != null){
       			kaseBO.where(KaseBO.LOCNCODE, locnCode);
       		}
        	
       		if (!"".equals(courtType) && courtType != null){
    			kaseBO.where(KaseBO.COURTTYPE, courtType);
    		}
        	
       		if (!"".equals(caseType) && caseType != null){
       			kaseBO.where(KaseBO.CASETYPE, caseType);
       		}
        	
       		if (!"".equals(disposedCases) && disposedCases != null){
       			if ("N".equals(disposedCases)){
       				kaseBO.where(KaseBO.DISPDATE, Exp.IS_NULL, Exp.AND, KaseBO.DISPCODE, Exp.IS_NULL);
       			}
       		}
        	
       		kaseBO.where(KaseBO.INTCASENUM, 
       			Exp.NOT_IN, Exp.select(
       				new JudgeHistBO(courtType).includeFields(JudgeHistBO.INTCASENUM)
       				.where(JudgeHistBO.JUDGEID, toJudgeCommId)
       				.where(JudgeHistBO.STARTDATE, Exp.EQUALS, Exp.literal("today"))
       				.where(JudgeHistBO.CLERKID, useridSrl)
       			)
       		);
        	
    		List<KaseBO> results = kaseBO.search(KaseBO.INTCASENUM);
    		for (KaseBO kase : results) {
    			retVal.add(kase.getIntCaseNum());
    		}
        		
		} catch (Exception e) {
            e.printStackTrace();
            printException(e);
		}
		
    	/////////////////////////////////////////
		// shuffle the entries in an array
    	/////////////////////////////////////////
		Collections.shuffle(retVal);
		
		return retVal;
    }

    /**
	 * Randomly select the cases.
	 * @parm Listing if cases by int_case_num 
	 * @parm how may cases to rotate  
	 * @parm Clerk Id Requesting the change
	 * @return selected cases  
	 */
	public static List<KaseBO> selectRandomCases(List<Integer> cases, int rotateAmount, int useridSrl, String courtType) {
	    List<KaseBO> retValList = new ArrayList<KaseBO>();;
	    KaseBO kaseBO = null;
		int selected = 0;
		int amountAvailable = 0;
		int randomNumber = 0;
		Random randomNumberGenerator;		

		/////////////////////////////////////////////////////////////
		// If the amount of cases == rotate amount or
		// If rotate amount is greater than the amount of cases
		// Do all of them
		/////////////////////////////////////////////////////////////
		if (cases.size() == rotateAmount || rotateAmount > cases.size()){
			for (int i=0; i < cases.size(); i++){
				kaseBO = new KaseBO(courtType);
				kaseBO.setIntCaseNum(cases.get(i));
				retValList.add(kaseBO);
				cases.set(i, 0);
				kaseBO = null;
			}
			
			/////////////////////////////////////////////////////////////
			// Print Requested and Available Amounts
			/////////////////////////////////////////////////////////////
			printMessage("Requested " + rotateAmount + " Avaialble " + cases.size(), "Y");

		} else {
			/////////////////////////////////////////////////////////////////////////////
			// create random object
			/////////////////////////////////////////////////////////////////////////////
			randomNumberGenerator = new Random();
			
			/////////////////////////////////////////////////////////////////////////////
			// setting the seed by the time
			/////////////////////////////////////////////////////////////////////////////
			randomNumberGenerator.setSeed(System.currentTimeMillis());
	
			/////////////////////////////////////////////////////////////////////////////
			// Set to one selected
			/////////////////////////////////////////////////////////////////////////////
			selected=1;
			
			/////////////////////////////////////////////////////////////////////////////
			// Amount Available
			/////////////////////////////////////////////////////////////////////////////
			amountAvailable =  cases.size();
			
			/////////////////////////////////////////////////////////////////////////////
			// Random Selected a case to the Amount Requested
			/////////////////////////////////////////////////////////////////////////////
			while (selected <= rotateAmount){
				/////////////////////////////////////////////////////////////////////////////
				// Generate a random number up to the amount available
				/////////////////////////////////////////////////////////////////////////////
				randomNumber = randomNumberGenerator.nextInt(amountAvailable);
				
				/////////////////////////////////////////////////////////////////////////////
				// If already Selected Skip
				/////////////////////////////////////////////////////////////////////////////
				if (cases.get(randomNumber) != 0 ){
					kaseBO = new KaseBO(courtType);
					kaseBO.setIntCaseNum(cases.get(randomNumber));
					retValList.add(kaseBO);
					kaseBO = null;
					cases.set(randomNumber, 0);
					selected++;
				}
				
			}
		}
		selected = 0;
		amountAvailable = 0;
		randomNumberGenerator = null;		
		randomNumber = 0;
		return retValList;
	}
	
    /**
	 * Get case by category.
	 * @parm fromJudgeCommId  
	 * @parm toJudgeCommId  
	 * @parm type  
	 * @parm Location Code  
	 * @parm Court Type  
	 * @parm Category  
	 * @parm Disposed Cases  
	 * @parm Clerk Id Requesting the change
	 * @return listing of available cases   
	 */
	public static List<Integer> getCasesByCategory(int fromJudgeCommId, int toJudgeCommId, String type, String locnCode, String courtType, String caseCategory, String disposedCases, int useridSrl){
		List<Integer> retVal = new ArrayList<Integer>();
        try {
			List<CaseTypeBO> results = new CaseTypeBO(courtType).where(CaseTypeBO.CATEGORY, caseCategory).search(CaseTypeBO.CASETYPE);
			for (CaseTypeBO result : results) {
				retVal.addAll(getCasesByCaseType(fromJudgeCommId, toJudgeCommId, type, locnCode, courtType, result.getCaseType(), disposedCases, useridSrl));
				System.out.println(result.getCaseType());
			}
			
		} catch (Exception e) {
            e.printStackTrace();
			printException(e);
		}
		
    	/////////////////////////////////////////
		// shuffle the entries in an array
    	/////////////////////////////////////////
		Collections.shuffle(retVal);
		return retVal;
    }
	
    /**
	 * Update kase assignment
	 * @parm connection  
	 * @parm Clerk Id Requesting the change
	 * @parm Case by int_case_num  
	 * @parm Type  
	 * @parm toJudgeCommId  
	 * @return status of update True/False   
	 */
	public static boolean updateKaseAssigment(int useridSrl, int intCaseNum, String type, int toJudgeCommId, String courtType) {
		boolean retVal = false;

//      PreparedStatement pStmt = null;
//		
//    	String sql = null;
//        try {
//        	sql = "execute procedure upd_case_assn(?,?,?,?)";
//          pStmt = conn.prepareStatement(sql);
//    		pStmt.setInt(1, useridSrl);
//    		pStmt.setInt(2, intCaseNum);
//    		pStmt.setString(3, type);
//    		pStmt.setInt(4, toJudgeCommId);
//			retVal = pStmt.execute();
//		} catch (SQLException e) {
//          e.printStackTrace();
//			printException(e);
//			System.exit(e.getErrorCode());
//		}
		
		try {
			StoredProcedureDescriptor storedProcedure = new StoredProcedureDescriptor (
				"upd_case_assn",
				"D".equals(courtType) ? KaseBO.CORIS_DISTRICT_DB : KaseBO.CORIS_JUSTICE_DB,
				new InputParameters().addParameters(
					new TypeInteger().setValue(useridSrl),
					new TypeInteger().setValue(intCaseNum),
					new TypeString().setValue(type),
					new TypeInteger().setValue(toJudgeCommId)
				),
				new OutputContainer().addResultTypes(
					new TypeInteger()		
				)
			);
			new StoredProcedureDispatcher(storedProcedure).executeQuery();
			
			retVal = true;
		} catch (Exception e) {
			e.printStackTrace();
			printException(e);
		}
		return retVal;
	}	

    /**
	 * Update calendar assignment
	 * @parm connection  
	 * @parm Case by int_case_num  
	 * @parm toJudgeCommId  
	 * @parm fromJudgeCommId  
	 * @return status of update True/False   
	 */
	public static boolean updateCalendarAssigment(int intCaseNum, int toJudgeCommId, int fromJudgeCommId, String courtType) {
		boolean retVal = false;
		int rowsUpdates = 0;

		try {
        	CalendarBO calendarBO = null;
        	if (fromJudgeCommId > 0) {
        		calendarBO = new CalendarBO(courtType)
        		.setJudgeId(toJudgeCommId)
        		.where(CalendarBO.JUDGEID, fromJudgeCommId)
        		.where(CalendarBO.INTCASENUM, intCaseNum)
        		.where(CalendarBO.APPEARDATE, Exp.GREATER_THAN_OR_EQUAL_TO, Exp.literal("today"))
        		.where(CalendarBO.CANCELDATETIME, Exp.IS_NULL);
        	} else {
        		calendarBO = new CalendarBO(courtType)
        		.setJudgeId(toJudgeCommId)
        		.where(CalendarBO.INTCASENUM, intCaseNum)
        		.where(CalendarBO.APPEARDATE, Exp.GREATER_THAN_OR_EQUAL_TO, Exp.literal("today"))
        		.where(CalendarBO.CANCELDATETIME, Exp.IS_NULL);
        	}

        	calendarBO.update();
        	rowsUpdates = (Integer) calendarBO.get(BaseConstants.UPDATE_COUNT);
        	if (rowsUpdates == 0){
        		retVal = false;
        	}else{
        		retVal = true;
        	}
        	
		} catch (Exception e) {
            e.printStackTrace();
			printException(e);
		}

		return retVal;
	}	

    /**
	 * Main update case/calendar assignment
	 * @parm caseData  
	 * @parm Case by int_case_num
	 * @parm clerk requesting the change  
	 * @parm judge or commissioner  
	 * @parm fromJudgeCommId  
	 * @parm toJudgeCommId  
	 * @return void   
	 */
	public static void changeJudgeAssignment(List<KaseBO> casesData, int useridSrl, String type, int fromJudgeCommId, int toJudgeCommId, String courtType){
		boolean success = false;
		for (int i = 0; i < casesData.size(); i++){
			//////////////////////////////////////////////////
			// Update Kase assignment
			//////////////////////////////////////////////////
			success = updateKaseAssigment(Integer.valueOf(useridSrl), casesData.get(i).getIntCaseNum(), type, Integer.valueOf(toJudgeCommId), courtType);
			if (success){
				//////////////////////////////////////////////////
				// Update Calendar assignment
				//////////////////////////////////////////////////
				success = updateCalendarAssigment(casesData.get(i).getIntCaseNum(), toJudgeCommId, fromJudgeCommId, courtType);					
			}
		}
	}
	
	/**
	 * Handle add a cell
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void addCell(PdfPTable table, String remarks, Font fontStyle, int alignment, int border, int colSpan) {
		PdfPCell theCell = new PdfPCell();
		theCell.setColspan(colSpan);
		AddParagraphCell(theCell, remarks, fontStyle, alignment);
		theCell.setBorder(border);
		table.addCell(theCell);
		theCell = null;
	}
	
	/**
	 * Handle add a paragraph cell
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void AddParagraphCell(PdfPCell cell, String remarks, Font fontStyle, int alignment) {
		Paragraph paragraph = new Paragraph(16, remarks, fontStyle);
		paragraph.setAlignment(alignment);
		cell.addElement(paragraph);
		paragraph = null;
	}
	
	public static byte[] createReport(List<KaseBO> casesData, String toJudgeCommLogName, String clerkLogName, int userIdSrl, String locnCode, String courtType) throws DocumentException, IOException{
		String dest = null;
		
        ///////////////////////////////////////////////////////////////
		// Create table
		///////////////////////////////////////////////////////////////
		float[] widths = new float[5];
		widths[0] = 20.0f;
		widths[1] = 20.0f;
		widths[2] = 20.0f;
		widths[3] = 20.0f;
		widths[4] = 20.0f;
        
		/////////////////////////////////////////////////////////////////
		//Create pdf table
		/////////////////////////////////////////////////////////////////
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100);
		try {
			table.setWidths(widths);
		} catch (DocumentException e) {
            e.printStackTrace();
			printException(e);
		}
		
		/////////////////////////////////////////////////////////////////
		//Create new document
		/////////////////////////////////////////////////////////////////
		Document document = new Document(PageSize.LETTER);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter writer = PdfWriter.getInstance(document, baos);
		
		reportMyHeaderFooter event = new reportMyHeaderFooter();

		writer.setPageEvent(event);
		document.setMargins(30f, 30f, 30f, 30f);

		document.open();		
        
		try {
			document.add(table);
		} catch (DocumentException e) {
            e.printStackTrace();
			printException(e);
		}
	    
		//for(KaseVO caseData : casesData){
	    for(int i=0; i < casesData.size(); i++){
			addCell(table, casesData.get(i).getCaseNum(), reportFont, Element.ALIGN_LEFT, Rectangle.NO_BORDER, 1);
			addCell(table, casesData.get(i).getCaseType(), reportFont, Element.ALIGN_LEFT, Rectangle.NO_BORDER, 1);
			addCell(table, (String) casesData.get(i).get(PersonnelBO.LOGNAME), reportFont, Element.ALIGN_LEFT, Rectangle.NO_BORDER, 1);
			addCell(table, toJudgeCommLogName, reportFont, Element.ALIGN_LEFT, Rectangle.NO_BORDER, 1);
			addCell(table, clerkLogName, reportFont, Element.ALIGN_LEFT, Rectangle.NO_BORDER, 1);
	    }

		document.add(table);
	    
		///////////////////////////////////////////////////////////////
		// Just in case we don't have enough cells
		// tell the table to "fill in the blanks"
		///////////////////////////////////////////////////////////////
		table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		table.completeRow();

		///////////////////////////////////////////////////////////////
		// Clean Up
		///////////////////////////////////////////////////////////////
		document.close();
		writer.close();
		
		return baos.toByteArray();
	}
	
    /**
	 * Get Log Name
	 * @parm user id  
	 * @parm location code
	 * @parm court type  
	 * @return string   
	 */
	public static String getLogName(int useridSrl, String locnCode, String courtType) throws Exception {
		return new PersonnelBO(courtType).where(PersonnelBO.USERIDSRL, useridSrl).where(PersonnelBO.LOCNCODE, locnCode).where(PersonnelBO.COURTTYPE, courtType).find().getLogname();
	}

	public static int getUseridSrl(String logName, String locnCode, String courtType) throws Exception {
		return new PersonnelBO(courtType).where(PersonnelBO.LOGNAME, logName).where(PersonnelBO.LOCNCODE, locnCode).where(PersonnelBO.COURTTYPE, courtType).find().getUseridSrl();
	}

    /**
	 * Get Case Information
	 * @parm connection
	 * @parm case by int_casse_num
	 * @parm type
	 * @return KaseVO   
	 */
	public static KaseBO getCaseInformation(int intCaseNum, String type, String courtType) throws Exception {
        KaseBO kaseBO = new KaseBO(courtType);
        if ("J".equals(type)){
        	kaseBO.includeFields(KaseBO.CASENUM, KaseBO.CASETYPE, KaseBO.INTCASENUM)
        	.where(KaseBO.INTCASENUM, intCaseNum)
        	.includeTables(new PersonnelBO(courtType).includeFields(PersonnelBO.LOGNAME).setOuter())
        	.addForeignKey(KaseBO.ASSNJUDGEID, PersonnelBO.USERIDSRL);
        } else{
        	kaseBO.includeFields(KaseBO.CASENUM, KaseBO.CASETYPE, KaseBO.INTCASENUM)
        	.where(KaseBO.INTCASENUM, intCaseNum)
        	.includeTables(new PersonnelBO(courtType).includeFields(PersonnelBO.LOGNAME).setOuter())
        	.addForeignKey(KaseBO.ASSNCOMMID, PersonnelBO.USERIDSRL);
        }
		return kaseBO.find();	
	}
	
    /**
	 * Get Current Case Information
	 * @parm Listing of selected cases
	 * @parm type
	 * @return List KaseBO   
	 */
	public static List<KaseBO> getCurrentCaseData(List<KaseBO> casesdata, String type, String courtType) {
	    KaseBO kaseBO = new KaseBO(courtType);
	    List<KaseBO> retValList = new ArrayList<KaseBO>();;

		///////////////////////////////////////////////////////////////
		// Get From Case Information  
		///////////////////////////////////////////////////////////////
    	for(int i=0; i < casesdata.size(); i++){
        	try {
				kaseBO = getCaseInformation(casesdata.get(i).getIntCaseNum(), type, courtType);
			} catch (Exception e1) {
	            e1.printStackTrace();
				printException(e1);
			}
        	retValList.add(kaseBO);
    	}
	    kaseBO = null;
	    return retValList;
	}
	
    /**
	 * Calucalte elsaped timeInsert into CORIS Print Index
	 * @parm endTime
	 * @parm startTime
	 * @return formated elapsed time in hh:mm:ss   
	 */
	public static String calculateElapsedTime(long endTime, long startTime){
		long elapsedTime = 0;
		String caculatedTime = null;
		String format = null;
	    String seconds = null;
	    String minutes = null;
	    String hours = null;
		
		elapsedTime = endTime - startTime;
		
		format = String.format("%%0%dd", 2);
	    seconds = String.format(format, (elapsedTime / 1000) % 60);
	    minutes = String.format(format, (elapsedTime / (1000 * 60)) % 60);
	    hours = String.format(format, (elapsedTime / (1000 * 60 * 60) % 24));
	    caculatedTime =  hours + ":" + minutes + ":" + seconds;		
		
		elapsedTime = 0;
		format = null;
	    seconds = null;
	    minutes = null;
	    hours = null;
		
		return caculatedTime;
	}
	
	public static void main(String[] args){
		
//		args = new String[13];
//		args[0] = "29083"; 			// Clerk ID
//		args[1] = "C"; 				// Type Judge or Commissioner
//		args[2] = "29606"; 			// From Judge/Commissioner Id
//		args[3] = "3698"; 			// To Judge/Commissioner Id
//		args[4] = "1868";			// Location Code
//		args[5] = "D";  			// Court Type
//		args[6] = "--";   			// Case Category
//		args[7] = "--";				// Case Type
//		args[8] = "N";  			// Disposed Cases
//		args[9] = "10";  			// Amount of Case/Hearing to Change
//		args[10] = "c:\\tmp\\";  	// fileDirectory for local testing
//		args[11] = "c:\\tmp\\";  	// dbIniDirectory
//		args[12] = "c:\\tmp\\";  	// reportDirectory
		
		DatabaseConnection.setUseJdbc();
		
		try {
			byte[] byteArray = processRotation(29083, "C", 29606, 3698, "1868", "D", "--", "--", "N", 10);
			System.out.println("bytes -- " + Arrays.toString(byteArray));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
			
	}
}


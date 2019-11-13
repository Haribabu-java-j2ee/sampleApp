package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FindDescriptor;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class PersonnelXO implements BaseConstants {
	private static final Logger log = Logger.getLogger(PersonnelXO.class);
	
	public static int PRINT_SQL = RUN;
	
	public static String getPassword(String username, String courtType) throws Exception {
	   	   	return new PersonnelBO(courtType)
	   	   			.where(PersonnelBO.LOGNAME,username)
	   	   			.toString(PRINT_SQL)
	   	   			.find(PersonnelBO.USERPASSWD).getUserPasswd();
	}
	
	public static boolean updatePassword(String username, String password, Date passwordExpirationDate, String courtType) throws Exception {
		
			new PersonnelBO(courtType)
				.setUserPasswd(password)
				.setPasswordExpDate(passwordExpirationDate)
				.setLastLogin(Calendar.getInstance().getTime())
				.where(new FindDescriptor(PersonnelBO.LOGNAME, username))
				.toString(PRINT_SQL).update();
			
			return true;
	}
	
	public static boolean isPasswordExpired(String username, String courtType) throws Exception {
		PersonnelBO personnel = new PersonnelBO(courtType)
			.where(PersonnelBO.LOGNAME,username)
			.where(new FindDescriptor(PersonnelBO.PASSWORDEXPDATE).setCustomSearch("< " + Calendar.getInstance().getTime()))
			.toString(PRINT_SQL).setReturnNull(true).find(PersonnelBO.PASSWORDEXPDATE);
		return personnel != null;
	}
	
	/**
	 * Get active judges for a specific location code and court type
	 * @param String locnCode
	 * @param String courtType
	 * @param Boolean useStatic
	 * @return List<PersonnelBO>
	 * @throws Exception 
	 */
	public static List<PersonnelBO> getActiveJudgesByLocation(String locationCode, String courtType) throws Exception {
		try{
			return new PersonnelBO(courtType)
					.where(PersonnelBO.LOCNCODE,locationCode)
					.where(PersonnelBO.TYPE,"J")
					.where(PersonnelBO.REMOVEDFLAG,"N")
					.orderBy(PersonnelBO.LASTNAME,PersonnelBO.FIRSTNAME)
					.toString(PRINT_SQL).search();
		} catch(Exception e) {
			log.error("Exception in PersonnelFacade getPersonnel(int useridSrl, String courtType, Boolean useStatic) ",e);
			throw e;
		} 
	}
	
	public static PersonnelBO getPersonnelByUserIdSrl(int useridSrl, String courtType) throws Exception {
		
		log.debug("<< PersonnelFacade getPersonnelUserIdSrl(int useridSrl, String courtType, Boolean useStatic) Start >>");
		try{
			return new PersonnelBO(courtType).where(PersonnelBO.USERIDSRL,useridSrl).toString(PRINT_SQL).find();
			
		} catch(Exception e) {
			log.error("Exception in PersonnelFacade getPersonnelUserIdSrl(int useridSrl, String courtType, Boolean useStatic) ",e);
			throw e;
		} 
		
	}
	
	public static PersonnelBO getPersonnelByLogNameLocnCodeCourtType(String logName, String locnCode, String courtType) throws Exception {
		
		log.debug("<< PersonnelFacade getPersonnelByLogNameLocnCodeCourtType(String logName, String locnCode, String courtType, Boolean useStatic) Start >>");
		try{
			return new PersonnelBO(courtType)
			.where(PersonnelBO.LOGNAME, logName)
			.where(PersonnelBO.LOCNCODE,locnCode)
			.where(PersonnelBO.COURTTYPE,courtType)
			.toString(PRINT_SQL).find();
		} catch(Exception e) {
			log.error("Exception in PersonnelFacade getPersonnelByLogNameLocnCodeCourtType(String logName, String locnCode, String courtType, Boolean useStatic) ",e);
			throw e;
		} 
	}
	
	public static PersonnelBO getPersonnelByLogNameLocnCodeCourtType(String logName, String locnCode, String courtType,Connection conn) throws Exception {
		
		log.debug("<< PersonnelFacade getPersonnelByLogNameLocnCodeCourtType(String logName, String locnCode, String courtType, Connection conn) Start >>");
		try{
			return new PersonnelBO(courtType)
			.where(PersonnelBO.LOGNAME, logName)
			.where(PersonnelBO.LOCNCODE,locnCode)
			.where(PersonnelBO.COURTTYPE,courtType).setUseConnection(conn)
			.toString(PRINT_SQL).find();
		} catch(Exception e) {
			log.error("Exception in PersonnelFacade getPersonnelByLogNameLocnCodeCourtType(String logName, String locnCode, String courtType, Connection conn) ",e);
			throw e;
		} 
	}
}

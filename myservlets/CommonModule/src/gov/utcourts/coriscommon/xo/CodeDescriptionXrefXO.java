package gov.utcourts.coriscommon.xo;

import java.sql.Connection;
import java.util.List;

import gov.utcourts.coriscommon.dataaccess.codedescriptionxref.CodeDescriptionXrefBO;
import gov.utcourts.courtscommon.constants.BaseConstants;

import org.apache.log4j.Logger;


public class CodeDescriptionXrefXO implements BaseConstants {
	
	private static final Logger log = Logger.getLogger(CodeDescriptionXrefXO.class);
	public static int PRINT_SQL = RUN;
	
	public static CodeDescriptionXrefBO getDescriptionByCode(String code, String tableName, String courtType) throws Exception {
		log.debug("<< Entering CodeDescriptionXrefXO.getDescriptionByCode(String code, String tableName, String courtType)  >>");
		try {
			CodeDescriptionXrefBO codeDescrBO = new CodeDescriptionXrefBO(courtType)
			.where(CodeDescriptionXrefBO.CODE, code)
			.where(CodeDescriptionXrefBO.TABLENAME, tableName)
			.toString(PRINT_SQL).find(CodeDescriptionXrefBO.DESCRIPTION);
			return codeDescrBO;
		} catch(Exception e) {
			log.error("Exception in CodeDescriptionXrefXO.getDescriptionByCode(String code, String tableName, String courtType) ",e);
			throw e;
		} 
	}
	
	public static CodeDescriptionXrefBO getDescriptionByCode(String code, String tableName, String courtType, Connection conn) throws Exception {
		log.debug("<< Entering CodeDescriptionXrefXO.getDescriptionByCode(String code, String tableName, String courtType)  >>");
		try {
			CodeDescriptionXrefBO codeDescrBO = new CodeDescriptionXrefBO(courtType)
			.where(CodeDescriptionXrefBO.CODE, code)
			.where(CodeDescriptionXrefBO.TABLENAME, tableName).setUseConnection(conn)
			.toString(PRINT_SQL).find(CodeDescriptionXrefBO.DESCRIPTION);
			return codeDescrBO;
		} catch(Exception e) {
			log.error("Exception in CodeDescriptionXrefXO.getDescriptionByCode(String code, String tableName, String courtType) ",e);
			throw e;
		} 
	}
	
	public static List<CodeDescriptionXrefBO> getDescriptionListByTableName( String tableName, String courtType) throws Exception {
		log.debug("<< Entering CodeDescriptionXrefXO.getDescriptionListByTableName( String tableName, String courtType)  >>");
		try {
			return new CodeDescriptionXrefBO(courtType)			 
			.where(CodeDescriptionXrefBO.TABLENAME, tableName)
			.toString(PRINT_SQL).search();
		 
		} catch(Exception e) {
			log.error("Exception in CodeDescriptionXrefXO.getDescriptionListByTableName( String tableName, String courtType) ",e);
			throw e;
		} 
	}
	
}

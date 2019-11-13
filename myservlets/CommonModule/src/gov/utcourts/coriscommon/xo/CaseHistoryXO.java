package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.common.XMLConfig;

import gov.utcourts.coriscommon.util.SSHClient;
 

import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;


import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import ch.ethz.ssh2.SCPClient;

 

public class CaseHistoryXO {
	private static final Logger logger = Logger.getLogger(CaseHistoryXO.class);
	public static boolean PRINT_SQL = false;
	
	public static void main(String args[]) throws Exception
	{
		DatabaseConnection.setUseJdbc();
		 
		
	}
	
	public static byte[] getCaseHistory(String courtType, int intCaseNum) throws Exception {
		boolean errorThrown = false;
		SSHClient sshClient = new SSHClient();
		String reportName = "";
		try {		
			String serverName = "D".equalsIgnoreCase(courtType)? "scripts_dist":"scripts_just";
			String hostName = XMLConfig.getProperty("PROPERTY."+serverName+".hostName");
			String osUserId = XMLConfig.getProperty("PROPERTY."+serverName+".username");
			SCPClient client = sshClient.connect(hostName,osUserId);
			sshClient.executeCommandLinux("/work/coris/bin/case_history_all "+XMLConfig.getProperty("PROPERTY."+serverName+".corisUserId")+" "+ intCaseNum +" N");
			byte[] bytes = SSHClient.getFile("/work/coris/print/xch_" + intCaseNum, client);
			reportName = "/work/coris/print/" + getFileName(new String(bytes))+".pdf";
			bytes = SSHClient.getFile(reportName, client);
			return bytes;
		} catch (Exception e) {
			logger.error( "	Error getting case history (intcasenum= " + intCaseNum );
			errorThrown = true;
			throw e;
		} finally {
			if (errorThrown == false) {
				sshClient.deleteFile("/work/coris/print/xch_"+intCaseNum);
				sshClient.deleteFile(reportName);
				sshClient.disconnect();
			}
			sshClient.disconnect();
			sshClient = null;
		}
	}
	
	private static String getFileName(String printStatus) {
		String retVal = null;
		StringTokenizer strTok = new StringTokenizer(printStatus);
		String status = strTok.nextToken(); 
	
		logger.debug( "	status = " + status );
		if ( status != null && status.equals( "C" ) ) {
			String pid = strTok.nextToken();
			logger.debug( "	pid = " + pid );
			if ( pid != null && pid.length() > 0 ) {
				retVal = pid; 				
				logger.debug( "	case history file name = '" + retVal + "'" );
			}
		}
		
		if (retVal == null)
			throw new IllegalStateException("Unable to get output file name.");
	
		strTok = null;
		
		return retVal;
	}
	
	
}

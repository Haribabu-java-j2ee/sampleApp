package gov.utcourts.coriscommon.util;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import gov.utcourts.sftpUtil.SFTPManager;

public class FTPUtil  {
	
	private static final Logger logger = Logger.getLogger(FTPUtil.class);
	
	/**
	 * Used exclusively for TaxIntercept FTP batch process.
	 * @param localFileWithFullPath
	 * @param destFileName
	 * @param server
	 * @param user
	 * @param pass
	 * @param dir - the destination directory
	 * @return
	 * @throws IOException
	 */
	public static String ftpFile(String localFileWithFullPath, String destFileName, String server, 
			String user, String pass, String dir) throws IOException 
	{
		String retVal = "";

		try {
			SFTPManager ftp = new SFTPManager(server, user, pass);
			logger.debug("Connected to " + server);
			try {
				ftp.connect();
				ftp.changeRemoteDir(dir);
				String osName = System.getProperty("os.name");
				logger.debug("OS: " + osName + ", Initial lpwd: "
						+ ftp.channelSftp.lpwd());
				if (osName != null && osName.indexOf("Windows") > -1) {
					System.out.println(ftp.channelSftp.lpwd());
					// set relative path to root dir on Windows 
					String localPath = ftp.channelSftp.lpwd();
				    for(int i=0; i < localPath.length(); i++)
				    {    if(localPath.charAt(i) == '\\')
				    		ftp.channelSftp.lcd("../");
				    }
				} else {
					ftp.channelSftp.lcd("/"); // absolute path to root on Linux
				}
				ftp.transferFileToSFTPServer(localFileWithFullPath, ".", true);
			} catch (Exception ftpe) {
				logger.error("Exception thrown in FTPUtil.ftpFile: ", ftpe);
				logger.error("Local dir is :" + ftp.channelSftp.lpwd());
				logger.error("Remote dir is : " + ftp.channelSftp.pwd());
				throw ftpe;
			} finally {
				ftp.disconnect();
				retVal = "success";
			}
		} catch (JSchException j) {
			logger.error("JSCH Exception Thrown: server=" + server + ", file="
					+ localFileWithFullPath + ", dest Dir=" + dir, j);
		} catch (SftpException s) {
			logger.error("SFTP Exception Thrown: server=" + server + ", file="
					+ localFileWithFullPath + ", dest Dir=" + dir, s);
		} catch (Exception e) {
			logger.error("Unable to connect to " + server, e);
		}
		return retVal;
	}
	
	 /**
     * Upload all files from local directory to a FTP server.
     * 
     * @param server
     *           
     * @param user
     *           
     * @param password
     *            
     * @param localParentDir
     *            Path of the local directory being uploaded.
     * @param remoteParentDir
     *            Path of the parent directory of the current directory on the server (used by recursive calls).
     * @throws IOException
     *             if any network or IO error occurred.
     */
    public static void uploadFilesFromLocalDirectory( String server, 
			String user, String password, String localDirPath, String remoteDirPath) throws IOException {
    	
    	System.out.println("Start of uploadFilesFromLocalDirectory method");
    	logger.info("Start of uploadFilesFromLocalDirectory method"); 
    	SFTPManager sftp = null;    	
    	try
    	{   sftp = new SFTPManager(server, user, password); 
    	     
    	    if(sftp!=null)
    	    {
    	    	logger.info("Connected to " + server);    		
    	    	sftp.connect();
    	    	sftp.changeRemoteDir(remoteDirPath);
    			String osName = System.getProperty("os.name");
    			logger.info("OS: " + osName + ", Initial lpwd: "
    					+ sftp.channelSftp.lpwd());
    			System.out.println("OS: " + osName + ", Initial lpwd: "
    					+ sftp.channelSftp.lpwd());
    			if (osName != null && osName.indexOf("Windows") > -1) {
    				System.out.println(sftp.channelSftp.lpwd());
    				logger.info(sftp.channelSftp.lpwd());
    				// set relative path to root dir on Windows 
    				String localPath = sftp.channelSftp.lpwd();
    			    for(int i=0; i < localPath.length(); i++)
    			    {    if(localPath.charAt(i) == '\\')
    			    	sftp.channelSftp.lcd("../");
    			    }
    			} else {
    				sftp.channelSftp.lcd("/"); // absolute path to root on Linux
    			}
    			
    			 File localDir = new File(localDirPath);
    	         File[] subFiles = localDir.listFiles();
    	         if (subFiles != null && subFiles.length > 0) {
    	        	 for (File item : subFiles) {
    	        		    System.out.println("File uploading "+item.getAbsolutePath());
    	        		    logger.info("File uploading "+item.getAbsolutePath());
    	        		    sftp.transferFileToSFTPServer(item.getAbsolutePath(), ".", true);
    	        		 
    	        	 }
    	        	 
    	         }  
    	    }    	         
           
    	}
    	
    	catch(Exception e)
    	{
    		logger.error("Exception while uploading files in uploadFilesFromLocalDirectory method");    
    		System.out.println("Exception while uploading files in uploadFilesFromLocalDirectory method");
    		e.printStackTrace();
    	}
    	finally {			 
			if (sftp != null) {
				sftp.disconnect();
				logger.info("Finally sftp disconnected");    
	    		System.out.println("Finally sftp disconnected");
	    		sftp = null;
			}
		}
    	logger.info("End of uploadFilesFromLocalDirectory method");     	
    	System.out.println("End of uploadFilesFromLocalDirectory method");
    }
}

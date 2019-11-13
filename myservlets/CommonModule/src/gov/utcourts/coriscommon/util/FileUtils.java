package gov.utcourts.coriscommon.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

public class FileUtils {

    private static final String EXCEPTION_SOCKET = "SocketException in writing file to ftp server.";
    private static final String EXCEPTION_IO = "IOException in writing file to ftp server.";
    private static final Logger logger = Logger.getLogger(FileUtils.class);

    /*
     * Private Constructor
     */
    private FileUtils() {
    }

    /**
     * Close the FileInputStream
     * 
     * @param is
     *            The FileInputStream
     */
    public static void closeFileInputStream(FileInputStream fis) {
        if (fis != null) {
            try {
                fis.close();
            } catch (IOException e) {
                logger.info(e);
            }
        }
    }

    /**
     * Close the FileOutputStream
     * 
     * @param is
     *            The FileOutputStream
     */
    public static void closeFileOutputStream(FileOutputStream fos) {
        if (fos != null) {
            try {
                fos.close();
            } catch (IOException e) {
                logger.info(e);
            }
        }
    }

    /**
     * Close the InputStream
     * 
     * @param is
     *            The InputStream
     */
    public static void closeInputStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                logger.info(e);
            }
        }
    }

    /**
     * Close the DataInputStream
     * 
     * @param dis
     *            The DataInputStream
     */
    public static void closeDataInputStream(DataInputStream dis) {
        if (dis != null) {
            try {
                dis.close();
            } catch (IOException e) {
                logger.info(e);
            }
        }
    }

    /**
     * Close the FTPClient
     * 
     * @param fc
     *            The FTPClient
     */
    public static void closeFTPClient(FTPClient fc) {
        if (fc != null) {
            try {
                fc.logout();
                fc.disconnect();
            } catch (IOException e) {
                logger.info(e);
            }
        }
    }

    /**
     * Writes the input stream to FTP Server.
     * 
     * @param remoteFilename
     *            file name to which input stream will be written
     * @param input
     *            InputStream of the given file to write to FTP server.
     * @throws Exception
     *             Exception
     */
    public static void transferToFtpServer(FTPClient ftpClient, InputStream is, String remoteFilename) {

        try {
            ftpClient.storeFile(remoteFilename, is);
        } catch (Exception e) {
            logger.info(e);
        }
    }

    /**
     * Upload a whole directory (including its nested sub directories and files) to a FTP server.
     * 
     * @param ftpClient
     *            an instance of org.apache.commons.net.ftp.FTPClient class.
     * @param remoteDirPath
     *            Path of the destination directory on the server.
     * @param localParentDir
     *            Path of the local directory being uploaded.
     * @param remoteParentDir
     *            Path of the parent directory of the current directory on the server (used by recursive calls).
     * @throws IOException
     *             if any network or IO error occurred.
     */
    public static void uploadDirectory(FTPClient ftpClient, String remoteDirPath, String localParentDir, String remoteParentDir) throws IOException {

        logger.info("Uploading directory: " + localParentDir);

        File localDir = new File(localParentDir);
        File[] subFiles = localDir.listFiles();
        if (subFiles != null && subFiles.length > 0) {
            for (File item : subFiles) {
                String remoteFilePath = remoteDirPath + "/" + remoteParentDir + "/" + item.getName();
                if (remoteParentDir.equals("")) {
                    remoteFilePath = remoteDirPath + "/" + item.getName();
                }

                if (item.isFile()) {
                    // upload the file
                    String localFilePath = item.getAbsolutePath();
                    logger.info("About to upload the file: " + localFilePath);
                    boolean uploaded = transferFileToFtpServer(ftpClient, localFilePath, remoteFilePath);
                    if (uploaded) {
                        logger.info("Uploaded a file to: " + remoteFilePath);
                    } else {
                        logger.info("Could not upload the file: " + localFilePath);
                    }
                } else {
                    // create directory on the server
                    boolean created = ftpClient.makeDirectory(remoteFilePath);
                    if (created) {
                        logger.info("Created the directory: " + remoteFilePath);
                    } else {
                        logger.info("Could not create the directory: " + remoteFilePath);
                    }

                    // upload the sub directory
                    String parent = remoteParentDir + "/" + item.getName();
                    if (remoteParentDir.equals("")) {
                        parent = item.getName();
                    }

                    localParentDir = item.getAbsolutePath();
                    uploadDirectory(ftpClient, remoteDirPath, localParentDir, parent);
                }
            }
        }
    }

    /**
     * Upload a single file to the FTP server.
     * 
     * @param ftpClient
     *            an instance of org.apache.commons.net.ftp.FTPClient class.
     * @param localFilePath
     *            Path of the file on local computer
     * @param remoteFilePath
     *            Path of the file on remote the server
     * @return true if the file was uploaded successfully, false otherwise
     * @throws IOException
     *             if any network or IO error occurred.
     */
    public static boolean transferFileToFtpServer(FTPClient ftpClient, String localFilePath, String fileName) throws IOException {
        File localFile = new File(localFilePath);

        InputStream inputStream = new FileInputStream(localFile);
        try {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            return ftpClient.storeFile(fileName, inputStream);
        } finally {
            inputStream.close();
        }
    }

    public static FTPClient openFTPClient(String server, int port, String directory, String userName, String password) throws SystemException {

        FTPClient ftpClient = new FTPClient();

        try {
            int reply;
            ftpClient.connect(server, port);
            ftpClient.login(userName, password);
            ftpClient.changeWorkingDirectory(directory);

            logger.info("Connected to " + server + ".");
            logger.info(ftpClient.getReplyString());

            reply = ftpClient.getReplyCode();

            if (FTPReply.isPositiveCompletion(reply)) {
                // Set Binary Transfer mode
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                ftpClient.enterLocalActiveMode();
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("FTP server '");
                sb.append(server);
                sb.append("' refused connection.");
                logger.error(sb.toString());
            }
        } catch (SocketException e) {
            throw new SystemException(EXCEPTION_SOCKET + e);
        } catch (IOException e) {
            throw new SystemException(EXCEPTION_IO + e);
        }

        return ftpClient;
    }
}

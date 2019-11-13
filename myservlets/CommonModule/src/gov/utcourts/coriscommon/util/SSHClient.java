package gov.utcourts.coriscommon.util;



import gov.utcourts.coriscommon.common.XMLConfig;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;


public class SSHClient {
    
    private Connection conn;
    private SCPClient client = null;

    public SCPClient connect(String hostname,String username) throws Exception {
      
    	// Inlined the private key info
        String rsakey = "-----BEGIN RSA PRIVATE KEY-----\nMIIEoQIBAAKCAQEAtMxUmqLFmAvDjBBLFEWNqUHCPh1jf07wEw6JAy2+LBb5OWsT7JW3FiIaZCCYbfb1t3DWarepQ+qZGj6rdUg0av/XL33/aE3lX5QO+y08eP49DYg1fyyLcAU5q90iJhb7Fn44Z3em04coC3ezUoKjrNwixmYq6JSWDWrm7Ahoj45xqmst0KM7X2G+g1cadPBxvMnTgJxBEJFgRcc8qbfOCOKEiyPg773TDAk5xb/p4H7QUxhmK0FzUTrqCvbKI/v2R+yyNGIdAypjg5xhLSNAn33eeikTURvDGYv+3Z+SHsWZjkwYpk9+2XpXxtSkue9V/tDLo2QtTY46b54TtB/e9QIBIwKCAQA9/OnOnjUeL+tF9vUrhY+nvsZBLqXFP6LEtIa/QuIdvrvZLAbUxZ3bs+vKjtUtBDb9EL6K/SQXSR6Mp8XBz5zxfEnHI+KKKVX04k5HfTlODgZNyEzbJTcfF7wAaRMFvrx8vY+uciqRqq6k2JVA3FVf1nJSpq+gMvGeM0fb5aAxN5NuFjapeW0BH9Lnt2NIeYtufSrjbBiTuRzvPELz0N42r+8qYDqqNqXo+ap3XcNF3b/KEv6ktDW/2LamIkAt8BDwAB1yej/KRC3nrq7bKfW4S/CVrUmv/NMC4ztLjWGi9ZyVunV1eoluIoDaaE/CbCiJZ/RLCw2B8EMTzsajeOmLAoGBANqEW/98jbGDVMh2Lcbd//u9s9ZGyK2zVb6hGb9QLBpZynagSz/2J55O9HSWG6vavtICnfIEtbN4TeMA6ammR+sdwdRXnQuFAnQhlFQk351GbwVFGpwfBZsnEg+ABmOMZkL/ZP1OscOK0tRYBN+ep4zSislwHusKrhHs9jHlPG9VAoGBANPPo7mQc2FDcCQ+p8dY40n805V32EuuX7fq6GS9FSuPlumvP/XedtDVkelTfW8ko+RDGGLGKM2+tPJQKTqiYoRuKof5iyzFi77Tn+ReXpaqMVuAf7WbdMB/Yy0WkichvaMvh0TA+FUiwuDxyjHO4ahGLCRYZQWH42TeN3PHbXEhAoGBAJXXGoNOGASx07yozu7EHT7oe1EamDyYOsvcLumWD50KXvJCB7bUrXPeXnvUpUKkoBr6eu8ZLCNLLhgAoDnRG19k3K7c/flFQ4LVMoLXdMOeAvxMpIhPyVRykAqg7nAlwncVhxQYtGjUOMwfGUjh0fop+MSkp3yZm+8I41Vp/Y4rAoGAZuEyRDA4Cqu6IDuwlAaaSIIdoF7IJMJpAY9a7xoC9+aoYuAXwJCgHEp6Ej5+wPSKHmm7Yy0MgTC+Sc8p9+h4/n6m8ZZ2zJp3E4tcSlm46hgmmih4i2jDrfS7JIdOTYVqvPnT/NLBwvOge+MnscriojC2WsvZS9RJ4Ikw4HbOwesCgYBUjkgGWfvEiGGlR+a/Pfz6p8vg6jDcAuwBbC/wErPoaIKv78O/kzOCYXfajI6shd+fVSXJMnoYqKBPedJoH1t9LCp2J2aV/ELE98CRqHcumHxiVbMx2nxWX8GwG3Aq55ByP4t5Rpx7Bksho7oTiMhWAFDQlTAqTOmXK1YoGeNFqg==\n-----END RSA PRIVATE KEY-----\n";
       
        try {
            conn = new Connection(hostname);
            conn.connect();

            /* Authenticate with our key */
            boolean isAuthenticated = conn.authenticateWithPublicKey(username, rsakey.toCharArray(), null);

            if (!isAuthenticated) {
                throw new IOException("Authentication failed.");
            }

            client = new SCPClient(conn);

            
        } catch (IOException e) {
            e.printStackTrace();
            disconnect();
            throw new Exception("Unable to connect.", e);
            //System.exit(2);
        }
        return client;
    }
    
    public SCPClient connect(String hostname,String username,String password) throws Exception {
        
    	try {
            conn = new Connection(hostname);
            conn.connect();

            /* Authenticate with our key */
            boolean isAuthenticated = conn.authenticateWithPassword(username, password);

            if (!isAuthenticated) {
                throw new IOException("Authentication failed.");
            }

            client = new SCPClient(conn);

            
        } catch (IOException e) {
            e.printStackTrace();
            disconnect();
            throw new Exception("Unable to connect.", e);
            //System.exit(2);
        }
        return client;
    }

    public String executeCommandLinux(String command) throws Exception { 
    	Session session = conn.openSession(); 
    	try { 
    		if ("Y".equalsIgnoreCase(XMLConfig.getProperty("PROPERTY.ssh.timeout.use")))
    			session.execCommand("timeout " + XMLConfig.getProperty("PROPERTY.ssh.timeout.seconds") + "s " + command);
    		else 
    			session.execCommand(command);
	    	
	    	// Read the results 
	    	StringBuilder sb = new StringBuilder(); 
	    	InputStream stdout = new StreamGobbler(session.getStdout());
	    	BufferedReader br = new BufferedReader( new InputStreamReader(stdout)); 
	    	String line = br.readLine(); 
	    	while (line != null) { 
	    		sb.append(line + "\n"); 
	    		line = br.readLine(); 
	    	} 
	    	// System.out.println("ExitCode: " + session.getExitStatus());
	    	if (session.getExitStatus()==null) 
		       	Thread.sleep(1000);
		     
	    	if (session.getExitStatus() != 0)
	    		if (session.getExitStatus() == 124)   // time out
	    			throw new TimeoutException();
	    		else 
	    			throw new Exception("An exception occurred while executing the following command: "	+ command + ". Exit Status = " + session.getExitStatus());
	    	
	    	session.close(); 
	    	return sb.toString(); 
    	} catch (Exception e) { 
    		session.close();
    		throw e;
    	} 
    } 
    
    public String executeCommandAIX(String command) throws Exception { 
    	Session session = conn.openSession(); 
    	try { 
   			session.execCommand(command);
	    	
	    	// Read the results 
	    	StringBuilder sb = new StringBuilder(); 
	    	InputStream stdout = new StreamGobbler(session.getStdout());
	    	BufferedReader br = new BufferedReader( new InputStreamReader(stdout)); 
	    	String line = br.readLine(); 
	    	while (line != null) { 
	    		sb.append(line + "\n"); 
	    		line = br.readLine(); 
	    	} 
	        if (session.getExitStatus()==null) 
	        	Thread.sleep(1000);
	        	//System.out.println("ExitCode: " + session.getExitStatus());
	        //}
	    	if (session.getExitStatus() != 0)
	    		if (session.getExitStatus() == 124)   // time out
	    			throw new TimeoutException();
	    		else 
	    			throw new Exception("An exception occurred while executing the following command: "	+ command + ". Exit Status = " + session.getExitStatus());
	    	
	    	session.close(); 
	    	return sb.toString(); 
    	} catch (Exception e) { 
    		session.close();
    		throw e;
    	} 
    } 

    public void disconnect() {
       
        if (conn != null) {
            conn.close();
        }
        conn = null;
        client = null;
    }

    public boolean isConnected() {
        if (conn == null) {
            return false;
        }
        return conn.isAuthenticationComplete();
    }

    
    public static byte[] getFile(String filename,SCPClient scpClient) throws IOException {
        byte [] bytes = null;
    	if (scpClient == null) {
            throw new IllegalStateException("Cannot attempt to retrieve a file without a connection");
        }        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        scpClient.get(filename, out);
        bytes = out.toByteArray();
        out.flush();
        out.close();
        return bytes;
    }
    
    public void deleteFile(String docPath) throws Exception {
 	   Session session = null;
        try {
        		session = conn.openSession();
        		session.execCommand("rm -f " + docPath);
        		session.waitForCondition(ChannelCondition.CLOSED | ChannelCondition.EOF |ChannelCondition.EXIT_STATUS, 10000);
         	} catch (IOException ie) { 
        			ie.printStackTrace();
        			throw new Exception("Error deleting file");
        		} finally {
        			try {
        				session.close();
        				} catch (NullPointerException ne) {
        				}
        			}
 	}


    public static void main(String[] args){
    	long start = System.currentTimeMillis();
    	SSHClient conn = new SSHClient();
    	try {
			
			conn.connect("test-slc-dist-scripts01", "xchjava");
    		//conn.connect("jctest2", "xchjava","coris!");
			String fileName = conn.executeCommandAIX("/work/coris/bin/case_history_all 66821 9219201 N");
			//String fileName = conn.executeCommandAIX("/work/coris/bin/case_history_all 121873 11225711 N");
			//String fileName = conn.executeCommandAIX("INFORMIXSERVER=jctst2_coris /work/coris/bin/case_history_all 121873 11181189 N");
			//String fileName = conn.executeCommandAIX("env");
			System.out.println(fileName); 
			conn.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
			conn.disconnect();
		}
		conn.disconnect();
		long time = System.currentTimeMillis() - start;
		System.out.println("Time passed: " + time);
    }
}


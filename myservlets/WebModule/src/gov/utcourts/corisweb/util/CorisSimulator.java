package gov.utcourts.corisweb.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ch.ethz.ssh2.crypto.Base64;

public class CorisSimulator {
	static JComboBox cmbServlet;
	static JTextField[] tName = new JTextField[10];
	static JTextField[] tValue = new JTextField[10];
	
	static String[] servletStrings = {
			"",
			"http://localhost:9080/CorisWEB/CreateJudgmentServlet",
			"http://localhost:9080/CorisWEB/OsdcEligibleLookupServlet"
	};
	
	static String[][] parmNames = {
		{},
		{"logName", "courtType", "intCaseNumber", "isAPP", "isPrison", "acctNumbers"},
		{"userId", "courtType", "locationCode", "intCaseNumber"}
	};
	
	static String[][] parmValues = {
		{},
		{"fangz", "D", "8085763", "Y", "Y", "59144|59160|59161"},
		{"86203", "D", "1868", "7046311"}
	};

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JFrame frame = new JFrame("CORIS SIMULATOR");
			
			JPanel mainPanel = new JPanel();
			mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

			JPanel servletPanel = new JPanel();
			JLabel servletlabel = new JLabel("Servlet URL", JLabel.TRAILING);
			servletPanel.add(servletlabel);
			cmbServlet = new JComboBox(servletStrings);
			cmbServlet.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            servletlabel.setLabelFor(cmbServlet);
            servletPanel.add(cmbServlet);
            
			JPanel parmPanel = new JPanel();
			parmPanel.setLayout(new GridLayout(10, 4));
			
		    for (int i=0; i<10; i++){
		        JLabel lName = new JLabel("Parameter Name: ", JLabel.TRAILING);
		        parmPanel.add(lName);
		        tName[i] = new JTextField(10);
		        parmPanel.add(tName[i]);
		        
		
		        JLabel lValue = new JLabel("Parameter Value: ", JLabel.TRAILING);
		        parmPanel.add(lValue);
		        tValue[i] = new JTextField(10);
		        parmPanel.add(tValue[i]);
		    }
			
		    cmbServlet.addActionListener(new ActionListener()
	        {
	          public void actionPerformed(ActionEvent e)
	          {
	        	  JComboBox cb = (JComboBox)e.getSource();
	              int index = cb.getSelectedIndex();
	              for (int i = 0; i<parmNames[index].length; i++){
	            	  tName[i].setText(parmNames[index][i]);
	            	  tValue[i].setText(parmValues[index][i]);
	              }
	              
	              for (int i = parmNames[index].length; i<10; i++){
	            	  tName[i].setText("");
	            	  tValue[i].setText("");
	              }
	              
	          }
	        });
		    
		    JPanel buttonPanel = new JPanel(new BorderLayout());
	        JButton buttonExit = new JButton("Exit");
	        buttonPanel.add(buttonExit, BorderLayout.EAST);
	        
	        buttonExit.addActionListener(new ActionListener()
	        {
	          public void actionPerformed(ActionEvent e)
	          {
	        	  System.exit(0);
	          }
	        });
	        
	        JButton button = new JButton("Submit");
	        buttonPanel.add(button, BorderLayout.CENTER);
	        
	        button.addActionListener(new ActionListener()
	        {
	          public void actionPerformed(ActionEvent e)
	          {
	        	  String parameters = "";
	        	  for (int i=0; i<10; i++){
	            	if (!"".equals(tName[i].getText())){
	            		parameters = parameters + "&" + tName[i].getText() + "=" + tValue[i].getText();
	            	}
	        	  }
	            
	        	  try {
	        		  String servlet = (String) cmbServlet.getSelectedItem();
	        		  if (!"".equals(servlet.trim()))
	        			  testURL((String) servlet, parameters.substring(1));
	        	  } catch (Exception e1) {
					e1.printStackTrace();
	        	  }
	          }
	        });
	        
	       
	        
	        mainPanel.add(servletPanel);
	        mainPanel.add(parmPanel);
	        mainPanel.add(buttonPanel);
	        
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        frame.setContentPane(mainPanel);

	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);

	     } catch (Exception e) {
		    e.printStackTrace();
	     }

	}
	
	private static void testURL(String servlet, String parameters) throws IOException, URISyntaxException{
		String encodedParm = new String(Base64.encode(parameters.getBytes()));
		URI oURL = new URI(servlet + "?e=Y&_=" + encodedParm);
		Desktop.getDesktop().browse(oURL);
	
	}

}

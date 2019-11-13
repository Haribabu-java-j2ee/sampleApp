package gov.utcourts.coriscommon.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author subba.kondabala
 *
 */ 
@XmlRootElement(name = "ExpungementResponse") 
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlType(propOrder={"caseNumber","courtLocation","ori","firstName","lastName","prosecutingCaseNumber"})
public class ExpungementResponseDTO implements Serializable{
	
	private String caseNumber;
	private String courtLocation;
	private String ori;
	private String firstName;
	private String lastName;	
	private String prosecutingCaseNumber; 

	public String getCaseNumber() {
		return caseNumber;
	}
	@XmlElement(name="CaseNumber")
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public String getCourtLocation() {
		return courtLocation;
	}
	@XmlElement(name="CourtLocation")
	public void setCourtLocation(String courtLocation) {
		this.courtLocation = courtLocation;
	}
	public String getOri() {
		return ori;
	}
	@XmlElement(name="ORI")
	public void setOri(String ori) {
		this.ori = ori;
	}
	public String getFirstName() {
		return firstName;
	}
	@XmlElement(name="FirstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	@XmlElement(name="LastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getProsecutingCaseNumber() {
		return prosecutingCaseNumber;
	}
	@XmlElement(name="ProsecutingCaseNumber")
	public void setProsecutingCaseNumber(String prosecutingCaseNumber) {
		this.prosecutingCaseNumber = prosecutingCaseNumber;
	}
	@Override
	public String toString() {
		return  new StringBuilder().append(caseNumber).append(",").append(courtLocation).append(",").append(ori).append(",")
		                          .append(firstName).append(",").append(lastName).append(",").append(prosecutingCaseNumber).toString();
	}

	
 
	
}

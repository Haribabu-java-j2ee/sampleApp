package gov.utcourts.coriscommon.dto;

import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;

import java.util.Date;

public class CalPartyDTO extends PartyCaseBO {

	String court;
	public CalPartyDTO(String court) {
		super(court);
		this.court = court;
	}

	private String lastName;
	private String firstName;
	private Date birthDate;
	private String caseType;
	private String caseSecurity;
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public String getCaseSecurity() {
		return caseSecurity;
	}
	public void setCaseSecurity(String caseSecurity) {
		this.caseSecurity = caseSecurity;
	}
	public String getCourt() {
		return court;
	}
	public void setCourt(String court) {
		this.court = court;
	}
	
	
}

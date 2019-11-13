package gov.utcourts.coriscommon.util;

import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;

public class PersonnelObject {
	private PersonnelBO personnelBO;
	
	public PersonnelObject (PersonnelBO personnelBO){
		this.personnelBO = personnelBO; 
	}
	public int getUseridSrl() {
		return this.personnelBO.getUseridSrl();
	}
	public String getLogName() {
		return this.personnelBO.getLogname();
	}
	public String getType() {
		return this.personnelBO.getType();
	}
	public String getLastName() {
		return this.personnelBO.getLastName();
	}
	public String getFirstName() {
		return this.personnelBO.getFirstName();
	}
}

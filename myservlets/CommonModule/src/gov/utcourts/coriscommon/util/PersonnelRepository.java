package gov.utcourts.coriscommon.util;

import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;

import java.util.HashMap;

import org.apache.log4j.Logger;

public class PersonnelRepository {
	
	private static Logger logger = Logger.getLogger(PersonnelRepository.class);
	
	/*
	 * used for storing miscellaneous page objects
	 * 
	 */
	protected HashMap<Integer, PersonnelObject> personnel = new HashMap<Integer, PersonnelObject>();
	
	public PersonnelRepository addPerson(int useridSrl, PersonnelObject person) {
		personnel.put(useridSrl, person);
		return this;
	}
	
	public PersonnelRepository addPerson(PersonnelBO personnelBO){
		PersonnelObject newPerson = new PersonnelObject(personnelBO);
		personnel.put(newPerson.getUseridSrl(), newPerson);
		return this;
	}
	
	public PersonnelObject getPerson(int key) {
		return personnel.get(key);
	}
	
}

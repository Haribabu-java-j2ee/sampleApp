package gov.utcourts.coriscommon.util;

import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;

import java.util.ArrayList;
import java.util.List;

public class LocationUtil {

	public static List<String> getLocationsByLogname(String logname) throws Exception {
		List<String> locations = new ArrayList<String>();
		try {
			List<PersonnelBO> results = new PersonnelBO("D").where(PersonnelBO.LOGNAME, logname).search(PersonnelBO.LOCNCODE);
			results.addAll(new PersonnelBO("J").where(PersonnelBO.LOGNAME, logname).search(PersonnelBO.LOCNCODE));
			for (PersonnelBO result : results) {
				locations.add(result.getLocnCode());
			}
		} catch(Exception e) {
			System.out.println("getLocationsByLogname LocationUtil Exception: " + e.getMessage());
			throw e;
		}
		return locations;
	}
	
}

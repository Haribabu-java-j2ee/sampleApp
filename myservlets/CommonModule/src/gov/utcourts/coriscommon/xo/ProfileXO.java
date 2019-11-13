package gov.utcourts.coriscommon.xo;

import java.util.List;

import gov.utcourts.coriscommon.dataaccess.profile.ProfileBO;
import gov.utcourts.coriscommon.util.SystemException;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import org.apache.log4j.Logger;

public class ProfileXO implements BaseConstants {

	private static final Logger logger = Logger.getLogger(ProfileXO.class);
	public static int PRINT_SQL = RUN;

	public static List<ProfileBO> findProfiles(String courtType) throws SystemException {
		try {
			return new ProfileBO(courtType).toString(PRINT_SQL).search(
					ProfileBO.LOCNCODE, ProfileBO.COURTTYPE,
					ProfileBO.COURTTITLE, ProfileBO.ADDRESS1,
					ProfileBO.ADDRESS2, ProfileBO.CITY);

		} catch (Exception e) {
			StringBuilder sb = new StringBuilder(
					"Exception in ProfileXO.findProfiles");
			sb.append(e.getMessage());
			logger.error(e);
			throw new SystemException(e, "Exception in ProfileXO.findProfiles");
		}
	}
	
	public static void main(String[] args){
		DatabaseConnection.setUseJdbc();
		try {
			List<ProfileBO> pfs = findProfiles("J");
			System.out.println("profiles size = " + pfs.size());
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

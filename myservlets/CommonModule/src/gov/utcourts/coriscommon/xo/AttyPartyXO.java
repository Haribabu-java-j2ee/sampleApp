package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO;
import gov.utcourts.coriscommon.dataaccess.attyparty.AttyPartyBO;
import gov.utcourts.coriscommon.dataaccess.profile.ProfileBO;
import gov.utcourts.coriscommon.dataaccess.workcalcase.WorkCalCaseBO;
import gov.utcourts.coriscommon.dto.AttyPartyDTO;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldConstant;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

public class AttyPartyXO implements BaseConstants {
	
	private static final Logger log = Logger.getLogger(AttyPartyXO.class);
	public static int PRINT_SQL = RUN;
	
	public static void main(String[] args) {
		DatabaseConnection.setUseJdbc();
		ProfileBO profileBO = new ProfileBO("D").setLocnCode("1868").setCourtType("D");
		
		Connection conn = null;
		try {
			conn = DatabaseConnection.getConnection(AttorneyBO.CORIS_DISTRICT_DB);
			findAttorneys(profileBO, "D", conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<AttyPartyDTO> findAttorneys(ProfileBO profile, String courtType, Connection conn) throws Exception {
		try {
//			SearchDescriptor s1 = new SearchDescriptor(new AttyPartyBO(courtType));
//			SearchDescriptor s2 = new SearchDescriptor(new WorkCalCaseBO(courtType)
//				.where(WorkCalCaseBO.LOCNCODE, profile.getLocnCode()));
//			SearchDescriptor s3 = new SearchDescriptor(new AttorneyBO(courtType));
//
//			BaseSearchDispatcher searchFactory = new SearchDispatcher(s1, s2, s3).setUseConnection(conn)
//			.addForeignKey(AttyPartyBO.INTCASENUM, WorkCalCaseBO.INTCASENUM)
//			.addForeignKey(AttyPartyBO.BARNUM, AttorneyBO.BARNUM)
//			.addForeignKey(AttyPartyBO.BARSTATE, AttorneyBO.BARSTATE)
//			.setResultContainer(new AttyPartyDTO(courtType))
//			.toString(PRINT_SQL)
//			.search(AttyPartyBO.INTCASENUM, AttyPartyBO.PARTYNUM, AttyPartyBO.PARTYCODE, AttyPartyBO.BARNUM, AttyPartyBO.BARSTATE, AttorneyBO.FIRSTNAME, AttorneyBO.LASTNAME);
//			
//			return (List<AttyPartyDTO>) searchFactory.getResults();
		
    		return new AttyPartyBO(courtType)
    		.includeTables(
    			new WorkCalCaseBO(courtType).where(WorkCalCaseBO.LOCNCODE, profile.getLocnCode()),
    			new AttorneyBO(courtType)
    		)
    		.addForeignKey(AttyPartyBO.INTCASENUM, WorkCalCaseBO.INTCASENUM)
			.addForeignKey(AttyPartyBO.BARNUM, AttorneyBO.BARNUM)
			.addForeignKey(AttyPartyBO.BARSTATE, AttorneyBO.BARSTATE)
			.setResultContainer(new AttyPartyDTO(courtType))
			.toString(PRINT_SQL)
			.searchAndGetResults(AttyPartyBO.INTCASENUM, AttyPartyBO.PARTYNUM, AttyPartyBO.PARTYCODE, AttyPartyBO.BARNUM, AttyPartyBO.BARSTATE, AttorneyBO.FIRSTNAME, AttorneyBO.LASTNAME);
			
		} catch(Exception e){
			System.out.println("Exception: " + e.getMessage());
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<AttyPartyDTO> findAttorneys(ProfileBO profile, String courtType, Connection conn, int skipRows, int limitRows) throws Exception {
		try {
//			SearchDescriptor s1 = new SearchDescriptor(new AttyPartyBO(courtType));
//			SearchDescriptor s2 = new SearchDescriptor(new WorkCalCaseBO(courtType)
//				.where(WorkCalCaseBO.LOCNCODE, profile.getLocnCode()));
//			SearchDescriptor s3 = new SearchDescriptor(new AttorneyBO(courtType));
//
//			BaseSearchDispatcher searchFactory = new SearchDispatcher(s1, s2, s3).setUseConnection(conn)
//			.addForeignKey(AttyPartyBO.INTCASENUM, WorkCalCaseBO.INTCASENUM)
//			.addForeignKey(AttyPartyBO.BARNUM, AttorneyBO.BARNUM)
//			.addForeignKey(AttyPartyBO.BARSTATE, AttorneyBO.BARSTATE)
//			.setResultContainer(new AttyPartyDTO(courtType))
//			.toString(PRINT_SQL)
//			.search(AttyPartyBO.INTCASENUM, AttyPartyBO.PARTYNUM, AttyPartyBO.PARTYCODE, AttyPartyBO.BARNUM, AttyPartyBO.BARSTATE, AttorneyBO.FIRSTNAME, AttorneyBO.LASTNAME);
//			
//			return (List<AttyPartyDTO>) searchFactory.getResults();
		
    		return new AttyPartyBO(courtType).setIsolationDirtyRead(true)
    		.includeTables(
    			new WorkCalCaseBO(courtType).where(WorkCalCaseBO.LOCNCODE, profile.getLocnCode()),
    			new AttorneyBO(courtType)
    		).offset(skipRows).limit(limitRows)
    		.addForeignKey(AttyPartyBO.INTCASENUM, WorkCalCaseBO.INTCASENUM)
			.addForeignKey(AttyPartyBO.BARNUM, AttorneyBO.BARNUM)
			.addForeignKey(AttyPartyBO.BARSTATE, AttorneyBO.BARSTATE)
			.setResultContainer(new AttyPartyDTO(courtType))
			.toString(PRINT_SQL)
			.searchAndGetResults(AttyPartyBO.INTCASENUM, AttyPartyBO.PARTYNUM, AttyPartyBO.PARTYCODE, AttyPartyBO.BARNUM, AttyPartyBO.BARSTATE, AttorneyBO.FIRSTNAME, AttorneyBO.LASTNAME);
			
		} catch(Exception e){
			System.out.println("Exception: " + e.getMessage());
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static int findAttorneysCount(ProfileBO profile, String courtType, Connection conn) throws Exception {
		try {
//			SearchDescriptor s1 = new SearchDescriptor(new AttyPartyBO(courtType));
//			SearchDescriptor s2 = new SearchDescriptor(new WorkCalCaseBO(courtType)
//				.where(WorkCalCaseBO.LOCNCODE, profile.getLocnCode()));
//			SearchDescriptor s3 = new SearchDescriptor(new AttorneyBO(courtType));
//
//			BaseSearchDispatcher searchFactory = new SearchDispatcher(s1, s2, s3).setUseConnection(conn)
//			.addForeignKey(AttyPartyBO.INTCASENUM, WorkCalCaseBO.INTCASENUM)
//			.addForeignKey(AttyPartyBO.BARNUM, AttorneyBO.BARNUM)
//			.addForeignKey(AttyPartyBO.BARSTATE, AttorneyBO.BARSTATE)
//			.setResultContainer(new AttyPartyDTO(courtType))
//			.toString(PRINT_SQL)
//			.search(AttyPartyBO.INTCASENUM, AttyPartyBO.PARTYNUM, AttyPartyBO.PARTYCODE, AttyPartyBO.BARNUM, AttyPartyBO.BARSTATE, AttorneyBO.FIRSTNAME, AttorneyBO.LASTNAME);
//			
//			return (List<AttyPartyDTO>) searchFactory.getResults();
		
    		List<AttyPartyBO> attyPartyCount= new AttyPartyBO(courtType).setIsolationDirtyRead(true)
    		.includeTables(
    			new WorkCalCaseBO(courtType).where(WorkCalCaseBO.LOCNCODE, profile.getLocnCode()),
    			new AttorneyBO(courtType)
    		)
    		.addForeignKey(AttyPartyBO.INTCASENUM, WorkCalCaseBO.INTCASENUM)
			.addForeignKey(AttyPartyBO.BARNUM, AttorneyBO.BARNUM)
			.addForeignKey(AttyPartyBO.BARSTATE, AttorneyBO.BARSTATE)		 
			.addSearchWrapDescriptor("select count(*) as count from (", ")", new FieldConstant("count").setFieldAlias("count"))
			.toString(PRINT_SQL)
			.searchAndGetResults(AttyPartyBO.INTCASENUM, AttyPartyBO.PARTYNUM, AttyPartyBO.PARTYCODE, AttyPartyBO.BARNUM, AttyPartyBO.BARSTATE, AttorneyBO.FIRSTNAME, AttorneyBO.LASTNAME);
    		
    		System.out.println("attyPartyCount-----------"+attyPartyCount.get(0).get("count"));
    		
    		return Integer.parseInt((String)attyPartyCount.get(0).get("count"));
			
		} catch(Exception e){
			System.out.println("Exception: " + e.getMessage());
			throw e;
		}
	}
	
	
	public static void updateAttorney(AttorneyBO attorney, Connection conn) throws Exception {
		  new AttorneyBO("").setUseConnection(conn)
		  					.where(AttorneyBO.BARNUM, attorney.getBarNum())
		  					.where(AttorneyBO.BARSTATE, attorney.getBarState())
							.setAddress1(attorney.getAddress1())
							.setAddress2(attorney.getAddress2())
							.setAddress3(attorney.getAddress3())
							.setBusPhone(attorney.getBusPhone())
							.setBarStatus(attorney.getBarStatus())
							.setCellPhone(attorney.getCellPhone())
							.setCity(attorney.getCity())
							.setCountry(attorney.getCountry())
							.setEmailAddress(attorney.getEmailAddress())
							.setFaxNum(attorney.getFaxNum())
							.setFirstName(attorney.getFirstName())
							.setHomePhone(attorney.getHomePhone())
							.setLastName(attorney.getLastName())
							.setMiddleInitial(attorney.getMiddleInitial())
							.setOrganization(attorney.getOrganization())
							.setPrefix(attorney.getPrefix())
							.setStateCode(attorney.getStateCode())
							.setZipCode(attorney.getZipCode())
							.update(AttorneyBO.ADDRESS1,
									AttorneyBO.ADDRESS2,
									AttorneyBO.ADDRESS3,
									AttorneyBO.BUSPHONE,
									AttorneyBO.BARSTATUS,
									AttorneyBO.CELLPHONE,
									AttorneyBO.CITY,
									AttorneyBO.COUNTRY,
									AttorneyBO.EMAILADDRESS,
									AttorneyBO.FAXNUM,
									AttorneyBO.FIRSTNAME,
									AttorneyBO.HOMEPHONE,
									AttorneyBO.LASTNAME,
									AttorneyBO.MIDDLEINITIAL,
									AttorneyBO.ORGANIZATION,
									AttorneyBO.PREFIX,
									AttorneyBO.STATECODE,
									AttorneyBO.ZIPCODE);
						  
		
	}
}

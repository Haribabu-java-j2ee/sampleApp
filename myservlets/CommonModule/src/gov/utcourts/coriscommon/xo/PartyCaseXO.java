package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.profile.ProfileBO;
import gov.utcourts.coriscommon.dataaccess.workcalcase.WorkCalCaseBO;
import gov.utcourts.courtscommon.dispatcher.SearchDispatcher;
import gov.utcourts.coriscommon.dto.CalPartyDTO;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.BaseBO;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.Expression;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldOperationDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FindDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.SearchDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringArrayDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.ConditionalType;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.FieldOperationType;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;
import gov.utcourts.courtscommon.dispatcher.BaseSearchDispatcher;

import java.sql.Connection;
import java.util.List;

public class PartyCaseXO implements BaseConstants {
	
	public static int PRINT_SQL = RUN;
	
	public static void main(String[] args) throws Exception{
		
		
		DatabaseConnection.setUseJdbc();
		Connection conn = DatabaseConnection.getConnection(PartyCaseBO.CORIS_DISTRICT_DB);
		System.out.println(findCasePartyNumForStateOfUtah(conn, 37694, "D"));
		System.out.println(findCasePartyCodeForStateOfUtah(conn, 37694, 4, "D"));
		System.out.println(findCasePartyCode(conn, 37694, 4, "D"));
		System.out.println(findCasePartyByLastName(conn, 7458511, "STATE DEBT COLLECT", "D"));
		
		conn.close();
		
		/*
		ProfileBO profileBO = new ProfileBO("D");
		
		profileBO.setLocnCode("1868");
		profileBO.setCourtType("D");
		
		Connection conn = null;
		try {
			conn = DatabaseConnection.getConnection(PartyCaseBO.CORIS_DISTRICT_DB);
			findPartyCases(profileBO, "D",conn);
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
		*/
	}

	@SuppressWarnings("unchecked")
	public static List<CalPartyDTO> findPartyCases(ProfileBO profileBO, String courtType, Connection conn) throws Exception {
		
//		SearchDescriptor pc = new SearchDescriptor(new PartyCaseBO(courtType)
//				.where(new FindDescriptor(PartyCaseBO.PARTYCODE, Exp.IN, new StringArrayDescriptor("DEF","RES","PLA","PET","AKA"))));
//		SearchDescriptor wcc = new SearchDescriptor(new WorkCalCaseBO(courtType)
//			.where(new FindDescriptor(WorkCalCaseBO.LOCNCODE,profileBO.getLocnCode())));
//		
//		BaseSearchDispatcher searchFactory = new SearchDispatcher(pc, wcc).setUseConnection(conn)
//			.setResultContainer(new CalPartyDTO(courtType))
//			.addForeignKey(PartyCaseBO.INTCASENUM,WorkCalCaseBO.INTCASENUM)
//			.toString(PRINT_SQL).search(
//					PartyCaseBO.INTCASENUM,
//					PartyCaseBO.PARTYNUM,
//					PartyCaseBO.PARTYCODE, 
//					PartyCaseBO.RELATEDPARTYNUM);
//
//		return (List<CalPartyDTO>)searchFactory.getResults();
		
		return new PartyCaseBO(courtType)
		.includeTables(new WorkCalCaseBO(courtType).where(WorkCalCaseBO.LOCNCODE, profileBO.getLocnCode()))
		.where(PartyCaseBO.PARTYCODE, Exp.IN, new StringArrayDescriptor("DEF","RES","PLA","PET","AKA"))
		.addForeignKey(PartyCaseBO.INTCASENUM,WorkCalCaseBO.INTCASENUM)
		.setResultContainer(new CalPartyDTO(courtType))
		.searchAndGetResults(PartyCaseBO.INTCASENUM, PartyCaseBO.PARTYNUM, PartyCaseBO.PARTYCODE, PartyCaseBO.RELATEDPARTYNUM);
		
	}

	public static List<PartyCaseBO> findCasePartyNum(Connection conn, int intCaseNum, String partyCode, String courtType) throws Exception {
		List<PartyCaseBO> pcs = new PartyCaseBO(courtType)
		.setUseConnection(conn)
		.where(PartyCaseBO.INTCASENUM, intCaseNum)
		.where(PartyCaseBO.PARTYCODE, partyCode)
		.toString(BaseConstants.PRINT + BaseConstants.RUN)
		.search();
		
		return pcs;
	}
	
	public static List<PartyCaseBO> findCasePartyNumAndName(Connection conn, int intCaseNum, String partyCode, String courtType) throws Exception {
		return  new PartyCaseBO(courtType).setUseConnection(conn)
							.includeFields(PartyCaseBO.ALL_FIELDS)
							.where(PartyCaseBO.INTCASENUM, intCaseNum)
							.where(PartyCaseBO.PARTYCODE, partyCode)
							.includeTables(new PartyBO(courtType).includeFields(PartyBO.FIRSTNAME, PartyBO.LASTNAME))
							.addForeignKey(PartyCaseBO.PARTYNUM, PartyBO.PARTYNUM)
							.search();
	}
	
	public static int findCasePartyNumForStateOfUtah(Connection conn, int intCaseNum, String courtType) throws Exception {
		Integer partyNum;
		
//		FieldOperationDescriptor min = new FieldOperationDescriptor(PartyCaseBO.PARTYNUM, FieldOperationType.MIN, new TypeInteger());
//		
//		SearchDescriptor pc = new SearchDescriptor(new PartyCaseBO(courtType).setFieldOperations(min)
//			.where(PartyCaseBO.INTCASENUM, intCaseNum));
//		
//		SearchDescriptor p = new SearchDescriptor(new PartyBO(courtType)
//			.where(PartyBO.LASTNAME, "STATE OF UTAH")
//			.where(new FindDescriptor("(", PartyBO.FIRSTNAME, Exp.EQUALS, ""),
//			           new FindDescriptor("", PartyBO.FIRSTNAME, Exp.IS_NULL, ")").set(ConditionalType.OR)));
//		
//		BaseSearchDispatcher searchFactory = new SearchDispatcher(pc, p).setUseConnection(conn)
//		.addForeignKey(PartyCaseBO.PARTYNUM,PartyBO.PARTYNUM)
//		.toString(PRINT_SQL).search(min);
//
//		List<PartyCaseBO> vos = (List<PartyCaseBO>) pc.getResults();
//		
//		if (vos == null ||vos.size() ==  0)
//			return 0;
//		else
//			return (Integer) vos.get(0).get(min);
		
		partyNum = (Integer) new PartyCaseBO(courtType)
		.min(PartyCaseBO.PARTYNUM)
		.where(PartyCaseBO.INTCASENUM, intCaseNum)
		.includeTables(
			new PartyBO(courtType)
			.includeFields(PartyBO.NO_FIELDS)
    		.where(PartyBO.LASTNAME, "STATE OF UTAH")
    		.where(
    			Exp.LEFT_PARENTHESIS, 
    				PartyBO.FIRSTNAME, Exp.EQUALS, "",
    				Exp.OR,
    			    PartyBO.FIRSTNAME, Exp.IS_NULL,
    			Exp.RIGHT_PARENTHESIS
    		)
		)
		.addForeignKey(PartyCaseBO.PARTYNUM,PartyBO.PARTYNUM)
		.find(PartyCaseBO.NO_FIELDS)
		.getMin();

		// Added a check for null.  Selart 06/03/2019  Piv #166389551
		if (partyNum == null) {
			return 0;
		}
		return partyNum.intValue();
	}
	
	public static String findCasePartyCodeForStateOfUtah(Connection conn, int intCaseNum, int partyNum, String courtType) throws Exception {
		
//		FieldOperationDescriptor min = new FieldOperationDescriptor(PartyCaseBO.PARTYCODE, FieldOperationType.MIN, new TypeString());
//		
//		SearchDescriptor pc = new SearchDescriptor(new PartyCaseBO(courtType).setFieldOperations(min)
//			.where(PartyCaseBO.INTCASENUM, intCaseNum).where(PartyCaseBO.PARTYNUM, partyNum));
//		
//		SearchDescriptor p = new SearchDescriptor(new PartyBO(courtType)
//			.where(PartyBO.LASTNAME, "STATE OF UTAH")
//			.where(new FindDescriptor("(", PartyBO.FIRSTNAME, Exp.EQUALS, ""),
//			           new FindDescriptor("", PartyBO.FIRSTNAME, Exp.IS_NULL, ")").set(ConditionalType.OR)));
//		
//		BaseSearchDispatcher searchFactory = new SearchDispatcher(pc, p).setUseConnection(conn)
//		.addForeignKey(PartyCaseBO.PARTYNUM,PartyBO.PARTYNUM)
//		.toString(PRINT_SQL).search(min);
//
//		List<PartyCaseBO> vos = (List<PartyCaseBO>) pc.getResults();
//		
//		if (vos == null ||vos.size() ==  0)
//			return null;
//		else
//			return (String) vos.get(0).get(min);
		
		return (String) new PartyCaseBO(courtType)
		.min(PartyCaseBO.PARTYCODE.as("party_code_min"))
		.where(PartyCaseBO.INTCASENUM, intCaseNum)
		.where(PartyCaseBO.PARTYNUM, partyNum)
		.includeTables(
			new PartyBO(courtType)
			.includeFields(PartyBO.NO_FIELDS)
			.where(PartyBO.LASTNAME, "STATE OF UTAH")
			.where(
				Exp.LEFT_PARENTHESIS,  
					PartyBO.FIRSTNAME, Exp.EQUALS, "",
					Exp.OR,
			        PartyBO.FIRSTNAME, Exp.IS_NULL,
			    Exp.RIGHT_PARENTHESIS
			)
		)
		.addForeignKey(PartyCaseBO.PARTYNUM,PartyBO.PARTYNUM)
		.find()
		.getMin();
		
	}
	
	public static int findCasePartyByLastName(Connection conn, int intCaseNum, String lastName, String courtType) throws Exception {
		Integer partyNum;
//		FieldOperationDescriptor min = new FieldOperationDescriptor(PartyCaseBO.PARTYNUM, FieldOperationType.MIN, new TypeInteger());
//		
//		SearchDescriptor pc = new SearchDescriptor(new PartyCaseBO(courtType).setFieldOperations(min)
//				.where(PartyCaseBO.INTCASENUM, intCaseNum));
//
//		SearchDescriptor p = new SearchDescriptor(new PartyBO(courtType).where
//			(
//				new FindDescriptor(
//						new Expression(
//							Exp.LEFT_PARENTHESIS,
//								PartyBO.LASTNAME, 
//								Exp.MATCHES, 
//								"'" + lastName + "*'",
//							Exp.RIGHT_PARENTHESIS
//						)
//					)
//				)
//			);
//		
//		new SearchDispatcher(pc, p).setUseConnection(conn)
//			.addForeignKey(PartyCaseBO.PARTYNUM, PartyBO.PARTYNUM)
//			.search(PartyCaseBO.NO_FIELDS);
//		
//		List<PartyCaseBO> vos = (List<PartyCaseBO>) pc.getResults();
//		
//		if (vos == null ||vos.size() ==  0)
//			return 0;
//		else
//			return (Integer) vos.get(0).get(min);
		
		partyNum = (Integer) new PartyCaseBO(courtType).setUseConnection(conn)
		.min(PartyCaseBO.PARTYNUM)
		.where(PartyCaseBO.INTCASENUM, intCaseNum)
		.includeTables(new PartyBO(courtType).where(Exp.LEFT_PARENTHESIS, PartyBO.LASTNAME, Exp.MATCHES, "'" + lastName + "*'", Exp.RIGHT_PARENTHESIS))
		.addForeignKey(PartyCaseBO.PARTYNUM, PartyBO.PARTYNUM)
		.toString(PRINT_SQL)
		.find(PartyCaseBO.NO_FIELDS)
		.getMin();
		
		// Added a check for null.  Selart 06/03/2019  Piv #166389551
		if (partyNum == null) {
			return 0;
		}
		return partyNum.intValue();
		
	}
	
	
	public static String findCasePartyCode(Connection conn, int intCaseNum, int partyNum, String courtType) throws Exception {
		
		return (String) new PartyCaseBO(courtType)
		.setUseConnection(conn)
		.min(PartyCaseBO.PARTYCODE)
		.where(PartyCaseBO.INTCASENUM, intCaseNum)
		.where(PartyCaseBO.PARTYNUM, partyNum)
		.find()
		.getMin();

	}
	
	public static PartyCaseBO findCaseParty(Connection conn, int intCaseNum, int partyNum, String partyCode, String courtType) throws Exception {
		PartyCaseBO bo = null;
		if (partyCode == null) {
			// Added .setReturnNull(true)  Selart 06/10/2019  Piv #166551299
			bo = new PartyCaseBO(courtType)
			.setUseConnection(conn)
			.where(PartyCaseBO.INTCASENUM, intCaseNum)
			.where(PartyCaseBO.PARTYNUM, partyNum)
			.setReturnNull(true)
			.find();
			
		} else {
			// Added .setReturnNull(true)  Selart 06/10/2019  Piv #166551299
			bo = new PartyCaseBO(courtType)
			.setUseConnection(conn)
			.where(PartyCaseBO.INTCASENUM, intCaseNum)
			.where(PartyCaseBO.PARTYNUM, partyNum)
			.where(PartyCaseBO.PARTYCODE, partyCode)
			.setReturnNull(true)
			.find();
			
		}
		return bo;
	}
	
	public static void insertPartyCase(Connection conn, int intCaseNum, int partyNum, String partyCode, String courtType, String cashBailFlag) throws Exception {
		new PartyCaseBO("D")
		.setUseConnection(conn)
		.setIntCaseNum(intCaseNum)
		.setPartyNum(partyNum)
		.setPartyCode(partyCode)
		.setCashBailFlag(cashBailFlag)
		.toString(PRINT_SQL)
		.insert();
	}

}

package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.commonparty.CommonPartyBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.sql.Connection;

public class CommonPartyXO {
	
	public static int findCasePartyByLastName(Connection conn, String locnCode, String lastName, String courtType) throws Exception{
		
//		FieldOperationDescriptor min = new FieldOperationDescriptor(CommonPartyBO.PARTYNUM, FieldOperationType.MIN, new TypeInteger());
//
//		SearchDescriptor cp = new SearchDescriptor(new CommonPartyBO(courtType).setFieldOperations(min)
//			.where(CommonPartyBO.LOCNCODE, locnCode));
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
//		new SearchDispatcher(cp, p).setUseConnection(conn)
//			.addForeignKey(CommonPartyBO.PARTYNUM, PartyBO.PARTYNUM)
//			.search(PartyBO.NO_FIELDS);
//		
//		CommonPartyBO cpBO = (CommonPartyBO) cp.getResults().get(0);
//		return (Integer) cpBO.get(min);

		return (Integer) new CommonPartyBO(courtType).min(CommonPartyBO.PARTYNUM)
		.setUseConnection(conn)
		.where(CommonPartyBO.LOCNCODE, locnCode)
		.includeTables(new PartyBO(courtType).where(Exp.LEFT_PARENTHESIS, PartyBO.LASTNAME, Exp.MATCHES, lastName + "*", Exp.RIGHT_PARENTHESIS))
		.addForeignKey(CommonPartyBO.PARTYNUM, PartyBO.PARTYNUM)
		.find(PartyBO.NO_FIELDS)
		.getMin();

	}
	
	public static void main(String[] args) throws Exception{
		
		
		DatabaseConnection.setUseJdbc();
		Connection conn = DatabaseConnection.getConnection(CommonPartyBO.CORIS_DISTRICT_DB);
		System.out.println(findCasePartyByLastName(conn, "1868", "ADULT P", "D"));
		System.out.println(findCasePartyByLastName(conn, "1868", "STATE DEBT COLLECT", "D"));
		System.out.println(findCasePartyByLastName(conn, "1411", "STATE DEBT COLLECT", "J"));
		
		conn.close();
		
		
	}

}

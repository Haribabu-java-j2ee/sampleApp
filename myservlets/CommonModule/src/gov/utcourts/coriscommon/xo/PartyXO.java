package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO;
import gov.utcourts.coriscommon.dataaccess.profile.ProfileBO;
import gov.utcourts.coriscommon.dataaccess.workcalcase.WorkCalCaseBO;
import gov.utcourts.coriscommon.dto.CalPartyDTO;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldConstant;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringArrayDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartyXO implements BaseConstants {

	public static int PRINT_SQL = RUN;

	@SuppressWarnings("unchecked")
	public static Map<Integer, CalPartyDTO> findParties(ProfileBO profileBO,
			String courtType, Connection conn) throws Exception {

		List<CalPartyDTO> results = new PartyCaseBO(courtType).setIsolationDirtyRead(true)
				.as("pc")
				.setDistinct()
				.includeFields(PartyCaseBO.PARTYCODE,
						PartyCaseBO.RELATEDPARTYNUM)
				.where(PartyCaseBO.PARTYCODE,
						Exp.IN,
						new StringArrayDescriptor("AKA", "ASI", "BEN", "BON",
								"CLA", "COC", "COM", "CON", "CRC", "DBA",
								"DEC", "DEF", "EXM", "FDB", "FKA", "GAL",
								"GCN", "GDF", "GUA", "HER", "INT", "ITP",
								"NKA", "OF1", "OF2", "OTH", "PET", "PLA",
								"PRP", "REP", "RES", "SAP", "TN", "TPC", 
								"TPD", "TRE", "TRR", "VRP"))
				.where(Exp.LEFT_PARENTHESIS, PartyCaseBO.SAFEGUARDED,
						Exp.EQUALS, "N", Exp.OR, PartyCaseBO.SAFEGUARDED,
						Exp.IS_NULL, Exp.RIGHT_PARENTHESIS)
				.includeTables(
						new PartyBO(courtType).as("p").includeFields(
								PartyBO.PARTYNUM, PartyBO.LASTNAME,
								PartyBO.FIRSTNAME, PartyBO.BIRTHDATE),
						new WorkCalCaseBO(courtType)
								.as("wcc")
								.includeFields(WorkCalCaseBO.INTCASENUM)
								.where(WorkCalCaseBO.CASESECURITY,
										Exp.NOT_EQUALS, "S")
								.where(WorkCalCaseBO.CASETYPE,
										Exp.NOT_IN,
										new StringArrayDescriptor("AD", "GA",
												"IC", "IS", "OU"))
								.where(WorkCalCaseBO.LOCNCODE,
										profileBO.getLocnCode()))
				.setResultContainer(new CalPartyDTO(courtType))
				.addForeignKey(PartyCaseBO.PARTYNUM, PartyBO.PARTYNUM)
				.addForeignKey(PartyCaseBO.INTCASENUM, WorkCalCaseBO.INTCASENUM)
				.toString(PRINT_SQL).searchAndGetResults();

		Map<Integer, CalPartyDTO> partyMap = new HashMap<Integer, CalPartyDTO>();
		for (CalPartyDTO pt : results) {
			partyMap.put(pt.getPartyNum(), pt);
		}

		return partyMap;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<Integer, CalPartyDTO> findParties(ProfileBO profileBO,
			String courtType, Connection conn,int skipRows, int limitRows) throws Exception {

		List<CalPartyDTO> results = new PartyCaseBO(courtType).setIsolationDirtyRead(true)
				.as("pc")
				.setDistinct()
				.includeFields(PartyCaseBO.PARTYCODE,
						PartyCaseBO.RELATEDPARTYNUM)
				.where(PartyCaseBO.PARTYCODE,
						Exp.IN,
						new StringArrayDescriptor("AKA", "ASI", "BEN", "BON",
								"CLA", "COC", "COM", "CON", "CRC", "DBA",
								"DEC", "DEF", "EXM", "FDB", "FKA", "GAL",
								"GCN", "GDF", "GUA", "HER", "INT", "ITP",
								"NKA", "OF1", "OF2", "OTH", "PET", "PLA",
								"PRP", "REP", "RES", "SAP", "TN", "TPC", 
								"TPD", "TRE", "TRR", "VRP"))
				.where(Exp.LEFT_PARENTHESIS, PartyCaseBO.SAFEGUARDED,
						Exp.EQUALS, "N", Exp.OR, PartyCaseBO.SAFEGUARDED,
						Exp.IS_NULL, Exp.RIGHT_PARENTHESIS)
				.includeTables(
						new PartyBO(courtType).as("p").includeFields(
								PartyBO.PARTYNUM, PartyBO.LASTNAME,
								PartyBO.FIRSTNAME, PartyBO.BIRTHDATE),
						new WorkCalCaseBO(courtType)
								.as("wcc")
								.includeFields(WorkCalCaseBO.INTCASENUM)
								.where(WorkCalCaseBO.CASESECURITY,
										Exp.NOT_EQUALS, "S")
								.where(WorkCalCaseBO.CASETYPE,
										Exp.NOT_IN,
										new StringArrayDescriptor("AD", "GA",
												"IC", "IS", "OU"))
								.where(WorkCalCaseBO.LOCNCODE,
										profileBO.getLocnCode()))
				.setResultContainer(new CalPartyDTO(courtType))
				.offset(skipRows).limit(limitRows)
				.addForeignKey(PartyCaseBO.PARTYNUM, PartyBO.PARTYNUM)
				.addForeignKey(PartyCaseBO.INTCASENUM, WorkCalCaseBO.INTCASENUM)
				.toString(PRINT_SQL).searchAndGetResults();

		Map<Integer, CalPartyDTO> partyMap = new HashMap<Integer, CalPartyDTO>();
		for (CalPartyDTO pt : results) {
			partyMap.put(pt.getPartyNum(), pt);
		}

		return partyMap;
	}
	
	
	@SuppressWarnings("unchecked")
	public static int findPartiesCount(ProfileBO profileBO,
			String courtType, Connection conn) throws Exception {

		List<PartyCaseBO> results = new PartyCaseBO(courtType).setIsolationDirtyRead(true)
				.as("pc")
				.setDistinct()
				.includeFields(PartyCaseBO.PARTYCODE,
						PartyCaseBO.RELATEDPARTYNUM)
				.where(PartyCaseBO.PARTYCODE,
						Exp.IN,
						new StringArrayDescriptor("AKA", "ASI", "BEN", "BON",
								"CLA", "COC", "COM", "CON", "CRC", "DBA",
								"DEC", "DEF", "EXM", "FDB", "FKA", "GAL",
								"GCN", "GDF", "GUA", "HER", "INT", "ITP",
								"NKA", "OF1", "OF2", "OTH", "PET", "PLA",
								"PRP", "REP", "RES", "SAP", "TN", "TPC", 
								"TPD", "TRE", "TRR", "VRP"))
				.where(Exp.LEFT_PARENTHESIS, PartyCaseBO.SAFEGUARDED,
						Exp.EQUALS, "N", Exp.OR, PartyCaseBO.SAFEGUARDED,
						Exp.IS_NULL, Exp.RIGHT_PARENTHESIS)
				.includeTables(
						new PartyBO(courtType).as("p").includeFields(
								PartyBO.PARTYNUM, PartyBO.LASTNAME,
								PartyBO.FIRSTNAME, PartyBO.BIRTHDATE),
						new WorkCalCaseBO(courtType)
								.as("wcc")
								.includeFields(WorkCalCaseBO.INTCASENUM)
								.where(WorkCalCaseBO.CASESECURITY,
										Exp.NOT_EQUALS, "S")
								.where(WorkCalCaseBO.CASETYPE,
										Exp.NOT_IN,
										new StringArrayDescriptor("AD", "GA",
												"IC", "IS", "OU"))
								.where(WorkCalCaseBO.LOCNCODE,
										profileBO.getLocnCode()))
				
				.addForeignKey(PartyCaseBO.PARTYNUM, PartyBO.PARTYNUM)
				.addForeignKey(PartyCaseBO.INTCASENUM, WorkCalCaseBO.INTCASENUM)
				.addSearchWrapDescriptor("select count(*) as count from (", ")", new FieldConstant("count").setFieldAlias("count"))
				.toString(PRINT_SQL).search();
		

				System.out.println(results.get(0).get("count"));
		
				
				return Integer.parseInt((String)results.get(0).get("count"));

		 
	}

	@SuppressWarnings("unchecked")
	public static CalPartyDTO findParty(int partNum, String courtType) {
		try {

			List<CalPartyDTO> results = new PartyBO(courtType)
					.where(PartyBO.PARTYNUM, partNum)
					.setResultContainer(new CalPartyDTO(courtType))
					.searchAndGetResults(PartyBO.PARTYNUM, PartyBO.LASTNAME,
							PartyBO.FIRSTNAME, PartyBO.BIRTHDATE);

			if (results != null && results.size() > 0) {
				return results.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new CalPartyDTO(courtType);
	}

	public static int findPartyNumByCourtTitle(Connection conn,
			String courtType, String title) throws Exception {
		PartyBO partyBO = new PartyBO(courtType).setUseConnection(conn)
				.min(PartyBO.PARTYNUM).where(PartyBO.LASTNAME, title)
				.setReturnNull(true).find();
		if (partyBO == null)
			return 0;
		else
			return (Integer) partyBO.getMin();
	}

	public static int insertPartyForCourt(Connection conn, String courtType,
			String lastName) throws Exception {
		PartyBO partyBO = new PartyBO(courtType).setLastName(lastName)
				.setDisabled("N").setUseConnection(conn).insert();
		return partyBO.getPartyNum();
	}

	public static void main(String[] args) throws Exception {

		DatabaseConnection.setUseJdbc();
		Connection conn = DatabaseConnection
				.getConnection(PartyBO.CORIS_DISTRICT_DB);
		System.out.println(findPartyNumByCourtTitle(conn, "D",
				"ALBERTA - PRICE COURT"));
		conn.close();

	}

}

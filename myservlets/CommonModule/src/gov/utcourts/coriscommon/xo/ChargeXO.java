package gov.utcourts.coriscommon.xo;

import gov.utcourts.coriscommon.dataaccess.account.AccountBO;
import gov.utcourts.coriscommon.dataaccess.acctfee.AcctFeeBO;
import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;
import gov.utcourts.coriscommon.dataaccess.offense.OffenseBO;
import gov.utcourts.coriscommon.dataaccess.profile.ProfileBO;
import gov.utcourts.coriscommon.dataaccess.severitytype.SeverityTypeBO;
import gov.utcourts.coriscommon.dataaccess.workcalcase.WorkCalCaseBO;
import gov.utcourts.coriscommon.dto.WorkCalCaseChargeDTO;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.Expression;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldConstant;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FindDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringArrayDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.TableAndFieldDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.WhereSelectDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ChargeXO implements BaseConstants {
	private static Logger logger = Logger.getLogger(ChargeXO.class);
	private static int PRINT_SQL = RUN;

	/**
	 * Return a count for the number of charges that have the judgment codes
	 * passed in.
	 * 
	 * @param String
	 *            courtType
	 * @param String
	 *            judgmentCodes These should be encased in single quotes comma
	 *            separated.
	 * @param int intCaseNum
	 * @return int
	 */
	public static int getCountByIntCaseAndJdmtCodes(String courtType,
			String judgmentCodes, int intCaseNum) {
		int returnValue = 0;

		try {
			List<String> judgmentCodesList = new ArrayList<String>();
			if (judgmentCodes != null && judgmentCodes.length() > 0) {
				for (String judgmentCode : judgmentCodes.replace("'", "")
						.split(",")) {
					judgmentCodesList.add(judgmentCode.trim());
				}
			}

			StringArrayDescriptor sad = null;
			if (judgmentCodesList.size() > 0) {
				sad = new StringArrayDescriptor(judgmentCodesList);
			} else {
				sad = new StringArrayDescriptor("");
			}

			ChargeBO chargeBO = new ChargeBO(courtType).count(ChargeBO.SEQ)
					.where(ChargeBO.INTCASENUM, intCaseNum)
					.where(new FindDescriptor(ChargeBO.JDMTCODE, Exp.IN, sad))
					.toString(PRINT_SQL).find(ChargeBO.SEQ);

			returnValue = chargeBO.getCount();

			judgmentCodesList = null;
		} catch (Exception e) {
			logger.error("Exception in getCountByIntCaseAndJdmtCodes: "
					.concat(judgmentCodes), e);
		}
		return returnValue;
	}

	public static int getPAFeeCountByIntCase(String courtType, int intCaseNum) {
		int returnValue = 0;
		int returnValue2 = 0;
		try {
			returnValue = (Integer) new ChargeBO(courtType)
					.count(ChargeBO.SEQ)
					.where(ChargeBO.INTCASENUM, intCaseNum)
					.where(ChargeBO.JDMTCODE, Exp.IN,
							new StringArrayDescriptor("PA", "PB"))
					.toString(PRINT_SQL).find(ChargeBO.SEQ).getCount();

			if (returnValue == 0) {

				returnValue2 = (Integer) new AccountBO(courtType)
						.where(AccountBO.INTCASENUM, intCaseNum)
						.count(AccountBO.ACCTNUM)
						.where(Exp.LEFT_PARENTHESIS, AccountBO.AMTDUE,
								Exp.GREATER_THAN, AccountBO.AMTPAID, Exp.PLUS,
								AccountBO.AMTCREDIT, Exp.RIGHT_PARENTHESIS)
						.includeTables(
								new AcctFeeBO(courtType).where(
										AcctFeeBO.FEECODE, Exp.IN,
										new StringArrayDescriptor("PN", "PS")))
						.addForeignKey(AccountBO.ACCTNUM, AcctFeeBO.ACCTNUM)
						.toString(PRINT_SQL).find(AccountBO.NO_FIELDS)
						.getCount();

				if (returnValue2 > 0) {
					return 2;
				}

			} else {
				return 1;
			}
		} catch (Exception e) {
			logger.error("Exception in getPAFeeCountByIntCase: ".concat(Integer
					.toString(intCaseNum)), e);
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	public static List<WorkCalCaseChargeDTO> findCharges(ProfileBO profileBO,
			String courtType, Connection conn) throws Exception {

		return new WorkCalCaseBO(courtType).setIsolationDirtyRead(true)
	              .includeFields(WorkCalCaseBO.COURTTYPE)
				  .where(WorkCalCaseBO.LOCNCODE, profileBO.getLocnCode())				
			      .where(new Expression(Exp.LEFT_PARENTHESIS,ChargeBO.JDMTCODE, Exp.IS_NULL,Exp.OR,ChargeBO.JDMTCODE,Exp.NOT_EQUALS,"D",Exp.RIGHT_PARENTHESIS))
	              .where(new Expression(Exp.LEFT_PARENTHESIS,ChargeBO.JDMTCODE, Exp.IS_NULL,Exp.OR,ChargeBO.JDMTCODE,Exp.NOT_EQUALS,"NB",Exp.RIGHT_PARENTHESIS))
	              .where(new Expression(Exp.LEFT_PARENTHESIS,ChargeBO.JDMTCODE, Exp.IS_NULL,Exp.OR,ChargeBO.JDMTCODE,Exp.NOT_EQUALS,"NG",Exp.RIGHT_PARENTHESIS))
	              .where(new Expression(Exp.LEFT_PARENTHESIS,ChargeBO.JDMTCODE, Exp.IS_NULL,Exp.OR,ChargeBO.JDMTCODE,Exp.NOT_EQUALS,"NJ",Exp.RIGHT_PARENTHESIS))
	              .where(new Expression(Exp.LEFT_PARENTHESIS,ChargeBO.JDMTCODE, Exp.IS_NULL,Exp.OR,ChargeBO.JDMTCODE,Exp.NOT_EQUALS,"DI",Exp.RIGHT_PARENTHESIS))
				.includeTables(
						new ChargeBO(courtType).includeFields(
								ChargeBO.INTCASENUM, ChargeBO.SEQ,
								ChargeBO.VIOLCODE, ChargeBO.VIOLDATETIME),
						new OffenseBO(courtType).includeFields(
								OffenseBO.DESCR.as("offense_descr")).where(
								new FindDescriptor(OffenseBO.LASTEFFECTDATE,
										Exp.IS_NOT_NULL)).addWhereDescriptors(new WhereSelectDescriptor(
												new TableAndFieldDescriptor(OffenseBO.LASTEFFECTDATE),
												new OffenseBO(courtType).min(OffenseBO.LASTEFFECTDATE)
												.where(new FindDescriptor(OffenseBO.OFFSVIOLCODE).setCustomSearch("= t2."+ChargeBO.OFFSVIOLCODE.getDbFieldName()))
												.where(new FindDescriptor(OffenseBO.LASTEFFECTDATE).setCustomSearch(">= t2."+ChargeBO.OFFSEFFECTDATE.getDbFieldName())),
												Exp.EQUALS		
										 )),
						new SeverityTypeBO(courtType)
								.includeFields(SeverityTypeBO.DESCR
										.as("severityDescr")))
				.addForeignKey(ChargeBO.INTCASENUM, WorkCalCaseBO.INTCASENUM)
				.addForeignKey(ChargeBO.OFFSVIOLCODE, OffenseBO.OFFSVIOLCODE)
				.addForeignKey(ChargeBO.SEVERITY, SeverityTypeBO.SEVERITYCODE)
				.setResultContainer(new WorkCalCaseChargeDTO(courtType))
				.setDistinct().toString(PRINT_SQL).searchAndGetResults();

	}

	@SuppressWarnings("unchecked")
	public static List<WorkCalCaseChargeDTO> findCharges(ProfileBO profileBO,
			String courtType, Connection conn, int skipRows, int limitRows)
			throws Exception {

		return new WorkCalCaseBO(courtType)
			      .setIsolationDirtyRead(true)
				  .includeFields(WorkCalCaseBO.COURTTYPE)
				  .where(WorkCalCaseBO.LOCNCODE, profileBO.getLocnCode())				
			      .where(new Expression(Exp.LEFT_PARENTHESIS,ChargeBO.JDMTCODE, Exp.IS_NULL,Exp.OR,ChargeBO.JDMTCODE,Exp.NOT_EQUALS,"D",Exp.RIGHT_PARENTHESIS))
	              .where(new Expression(Exp.LEFT_PARENTHESIS,ChargeBO.JDMTCODE, Exp.IS_NULL,Exp.OR,ChargeBO.JDMTCODE,Exp.NOT_EQUALS,"NB",Exp.RIGHT_PARENTHESIS))
	              .where(new Expression(Exp.LEFT_PARENTHESIS,ChargeBO.JDMTCODE, Exp.IS_NULL,Exp.OR,ChargeBO.JDMTCODE,Exp.NOT_EQUALS,"NG",Exp.RIGHT_PARENTHESIS))
	              .where(new Expression(Exp.LEFT_PARENTHESIS,ChargeBO.JDMTCODE, Exp.IS_NULL,Exp.OR,ChargeBO.JDMTCODE,Exp.NOT_EQUALS,"NJ",Exp.RIGHT_PARENTHESIS))
	              .where(new Expression(Exp.LEFT_PARENTHESIS,ChargeBO.JDMTCODE, Exp.IS_NULL,Exp.OR,ChargeBO.JDMTCODE,Exp.NOT_EQUALS,"DI",Exp.RIGHT_PARENTHESIS))
				.includeTables(
						new ChargeBO(courtType).includeFields(
								ChargeBO.INTCASENUM, ChargeBO.SEQ,
								ChargeBO.VIOLCODE, ChargeBO.VIOLDATETIME),
						new OffenseBO(courtType).includeFields(
								OffenseBO.DESCR.as("offense_descr")).where(
								new FindDescriptor(OffenseBO.LASTEFFECTDATE,
										Exp.IS_NOT_NULL)).addWhereDescriptors(new WhereSelectDescriptor(
												new TableAndFieldDescriptor(OffenseBO.LASTEFFECTDATE),
												new OffenseBO(courtType).min(OffenseBO.LASTEFFECTDATE)
												.where(new FindDescriptor(OffenseBO.OFFSVIOLCODE).setCustomSearch("= t2."+ChargeBO.OFFSVIOLCODE.getDbFieldName()))
												.where(new FindDescriptor(OffenseBO.LASTEFFECTDATE).setCustomSearch(">= t2."+ChargeBO.OFFSEFFECTDATE.getDbFieldName())),
												Exp.EQUALS		
										 )),
						new SeverityTypeBO(courtType)
								.includeFields(SeverityTypeBO.DESCR
										.as("severityDescr"))).offset(skipRows)
				.limit(limitRows)
				.addForeignKey(ChargeBO.INTCASENUM, WorkCalCaseBO.INTCASENUM)
				.addForeignKey(ChargeBO.OFFSVIOLCODE, OffenseBO.OFFSVIOLCODE)
				.addForeignKey(ChargeBO.SEVERITY, SeverityTypeBO.SEVERITYCODE)
				.setResultContainer(new WorkCalCaseChargeDTO(courtType))
				.setDistinct().toString(PRINT_SQL).searchAndGetResults();
	}

	@SuppressWarnings("unchecked")
	public static int findChargesCount(ProfileBO profileBO, String courtType,
			Connection conn) throws Exception {

		List<WorkCalCaseBO> workCalCaseBOList = new WorkCalCaseBO(courtType)
				  .setIsolationDirtyRead(true)
				  .includeFields(WorkCalCaseBO.NO_FIELDS)
				  .where(WorkCalCaseBO.LOCNCODE, profileBO.getLocnCode())			
			      .where(new Expression(Exp.LEFT_PARENTHESIS,ChargeBO.JDMTCODE, Exp.IS_NULL,Exp.OR,ChargeBO.JDMTCODE,Exp.NOT_EQUALS,"D",Exp.RIGHT_PARENTHESIS))
	              .where(new Expression(Exp.LEFT_PARENTHESIS,ChargeBO.JDMTCODE, Exp.IS_NULL,Exp.OR,ChargeBO.JDMTCODE,Exp.NOT_EQUALS,"NB",Exp.RIGHT_PARENTHESIS))
	              .where(new Expression(Exp.LEFT_PARENTHESIS,ChargeBO.JDMTCODE, Exp.IS_NULL,Exp.OR,ChargeBO.JDMTCODE,Exp.NOT_EQUALS,"NG",Exp.RIGHT_PARENTHESIS))
	              .where(new Expression(Exp.LEFT_PARENTHESIS,ChargeBO.JDMTCODE, Exp.IS_NULL,Exp.OR,ChargeBO.JDMTCODE,Exp.NOT_EQUALS,"NJ",Exp.RIGHT_PARENTHESIS))
	              .where(new Expression(Exp.LEFT_PARENTHESIS,ChargeBO.JDMTCODE, Exp.IS_NULL,Exp.OR,ChargeBO.JDMTCODE,Exp.NOT_EQUALS,"DI",Exp.RIGHT_PARENTHESIS))
				.includeTables(
						new ChargeBO(courtType).includeFields(
								ChargeBO.INTCASENUM, ChargeBO.SEQ,
								ChargeBO.VIOLCODE, ChargeBO.VIOLDATETIME),
						new OffenseBO(courtType).includeFields(
								OffenseBO.DESCR.as("offense_descr")).where(
								new FindDescriptor(OffenseBO.LASTEFFECTDATE,
										Exp.IS_NOT_NULL)).addWhereDescriptors(new WhereSelectDescriptor(
												new TableAndFieldDescriptor(OffenseBO.LASTEFFECTDATE),
												new OffenseBO(courtType).min(OffenseBO.LASTEFFECTDATE)
												.where(new FindDescriptor(OffenseBO.OFFSVIOLCODE).setCustomSearch("= t2."+ChargeBO.OFFSVIOLCODE.getDbFieldName()))
												.where(new FindDescriptor(OffenseBO.LASTEFFECTDATE).setCustomSearch(">= t2."+ChargeBO.OFFSEFFECTDATE.getDbFieldName())),
												Exp.EQUALS		
										 )),
						new SeverityTypeBO(courtType)
								.includeFields(SeverityTypeBO.DESCR
										.as("severityDescr")))
				.addForeignKey(ChargeBO.INTCASENUM, WorkCalCaseBO.INTCASENUM)
				.addForeignKey(ChargeBO.OFFSVIOLCODE, OffenseBO.OFFSVIOLCODE)
				.addForeignKey(ChargeBO.SEVERITY, SeverityTypeBO.SEVERITYCODE)
				.setDistinct()
				.addSearchWrapDescriptor("select count(*) as count from (",
						")", new FieldConstant("count").setFieldAlias("count"))
				.toString(PRINT_SQL).search();

		System.out.println(workCalCaseBOList.get(0).get("count"));

		return Integer.parseInt((String) workCalCaseBOList.get(0).get("count"));
	}

	public static void main(String[] args) {
		DatabaseConnection.setUseJdbc();
		ProfileBO profileBO = new ProfileBO("D");
		profileBO.setLocnCode("1868");
		profileBO.setCourtType("D");
		Connection conn = null;
		try {
			conn = DatabaseConnection.getConnection(ChargeBO.CORIS_DISTRICT_DB);
			 //findCharges(profileBO, "D",conn);
			 //findChargesCount(profileBO, "D",conn);
			// isPleaInAbeyance(174032, "D");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @param intCaseNum
	 * @param courtType
	 * @return
	 * @throws Exception
	 */
	public static boolean isPleaInAbeyance(int intCaseNum, String courtType)
			throws Exception {

		List<ChargeBO> charges = new ChargeBO(courtType)
				.where(ChargeBO.INTCASENUM, intCaseNum)
				.where(new FindDescriptor(ChargeBO.JDMTCODE, Exp.EQUALS,
						new StringArrayDescriptor("PA"))).toString(PRINT_SQL)
				.search();

		if (charges != null && charges.size() > 0) {
			return true;
		}

		return false;
	}
}

package gov.utcourts.coriscommon.dto;

import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;
import gov.utcourts.coriscommon.dataaccess.crimcase.CrimCaseBO;
import gov.utcourts.coriscommon.dataaccess.jdmttype.JdmtTypeBO;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FindDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringArrayDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.util.Date;

public class DebtCollectionSearchDTO implements BaseConstants {
	private int intCaseNum;
	private String caseNumber;
	private String locationCode;
	private String courtType;
	private int countLimit;
	private int judgeId;
	private double amountLimit;
	private int overDueLimit;
	private String accountTypes;
	private String debtStatus;
	private String firstName;
	private String lastName;
	private String caseType;
	private Date fromSentDate;
	private Date toSentDate;
	private int sortType;
	private double balance;
	private boolean ignoreOverDueLimit;
	
	public int getIntCaseNum() {
		return intCaseNum;
	}
	public void setIntCaseNum(int intCaseNum) {
		this.intCaseNum = intCaseNum;
	}
	public String getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public String getCourtType() {
		return courtType;
	}
	public void setCourtType(String courtType) {
		this.courtType = courtType;
	}
	public int getCountLimit() {
		return countLimit;
	}
	public void setCountLimit(int countLimit) {
		this.countLimit = countLimit;
	}
	public int getJudgeId() {
		return judgeId;
	}
	public void setJudgeId(int judgeId) {
		this.judgeId = judgeId;
	}
	public double getAmountLimit() {
		return amountLimit;
	}
	public void setAmountLimit(double amountLimit) {
		this.amountLimit = amountLimit;
	}
	public int getOverDueLimit() {
		return overDueLimit;
	}
	public void setOverDueLimit(int overDueLimit) {
		this.overDueLimit = overDueLimit;
	}
	public String getAccountTypes() {
		return accountTypes;
	}
	public void setAccountTypes(String accountTypes) {
		this.accountTypes = accountTypes;
	}
	public String getDebtStatus() {
		return debtStatus;
	}
	public void setDebtStatus(String debtStatus) {
		this.debtStatus = debtStatus;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public Date getFromSentDate() {
		return fromSentDate;
	}
	public void setFromSentDate(Date fromSentDate) {
		this.fromSentDate = fromSentDate;
	}
	public Date getToSentDate() {
		return toSentDate;
	}
	public void setToSentDate(Date toSentDate) {
		this.toSentDate = toSentDate;
	}
	public int getSortType() {
		return sortType;
	}
	public void setSortType(int sortType) {
		this.sortType = sortType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	/**
	 * Runs a check for a count  
	 * @param courtType
	 * @param intCaseNum
	 * @return
	 * @throws Exception
	 */
	public static int getChargesByCourtTypeAndIntCaseNum(String courtType, int intCaseNum) throws Exception {
		
//		FieldOperationDescriptor count = new FieldOperationDescriptor(ChargeBO.SEQ, FieldOperationType.COUNT, new TypeInteger());
//		SearchDescriptor s1 = new SearchDescriptor(new ChargeBO(courtType).where(ChargeBO.INTCASENUM, intCaseNum)
//				.setFieldOperations(count));
//		SearchDescriptor s2 = new SearchDescriptor(new JdmtTypeBO(courtType)
//				.where(JdmtTypeBO.TYPE, Exp.EQUALS, "G"));
//		new SearchDispatcher(s1, s2)
//		.addForeignKey(ChargeBO.JDMTCODE, JdmtTypeBO.JDMTCODE)
//		.toString(RUN)
//		.search(ChargeBO.NO_FIELDS);
		
		int x = new ChargeBO(courtType)
		.count(ChargeBO.SEQ)
		.where(ChargeBO.INTCASENUM, intCaseNum)
		.includeTables(new JdmtTypeBO(courtType).where(JdmtTypeBO.TYPE, Exp.EQUALS, "G"))
		.addForeignKey(ChargeBO.JDMTCODE, JdmtTypeBO.JDMTCODE)
		.find(ChargeBO.NO_FIELDS)
		.getCount();
		
		int y = 0;
		int z = 0;
		System.out.println(x);
		
		if (x < 1) {			
			y = new CrimCaseBO(courtType)
					.count(CrimCaseBO.INTCASENUM)
					.where(CrimCaseBO.INTCASENUM, intCaseNum)
					.where(new FindDescriptor(CrimCaseBO.TRIALDENOVO, Exp.IN,new StringArrayDescriptor("H","T")))
					.toString(RUN)
					.find(CrimCaseBO.INTCASENUM)
					.getCount();
			
			System.out.println(y);
		
			if (y < 1) {
				
				z = new ChargeBO(courtType)
						.count(ChargeBO.INTCASENUM)
						.where(ChargeBO.INTCASENUM, intCaseNum)
						.where(ChargeBO.PARTYCODE, "DEF")
						.where(ChargeBO.VIOLCODE, "78A-7-118")
						.toString(RUN)
						.find(ChargeBO.INTCASENUM)
						.getCount();
				
				System.out.println(z);
			}
		}
		return x+y+z;
	}    
	
	
	
	public boolean isIgnoreOverDueLimit() {
		return ignoreOverDueLimit;
	}
	public void setIgnoreOverDueLimit(boolean ignoreOverDueLimit) {
		this.ignoreOverDueLimit = ignoreOverDueLimit;
	}
	public static void main(String[] args){
		try {
			DatabaseConnection.setUseJdbc();
			DebtCollectionSearchDTO.getChargesByCourtTypeAndIntCaseNum("D", 5516002);
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}

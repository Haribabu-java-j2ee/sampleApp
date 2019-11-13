package gov.utcourts.coriscommon.dataaccess.bankadj;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class BankAdjVO extends BaseVO { 

	private TypeString bankAcctNum = new TypeString("bank_acct_num").setFieldDescriptor(BankAdjBO.BANKACCTNUM.clear()).setAsPrimaryKey();
	private TypeDate adjDatetime = new TypeDate("adj_datetime").setFieldDescriptor(BankAdjBO.ADJDATETIME.clear()).setAsPrimaryKey();
	private TypeString type = new TypeString("type").setFieldDescriptor(BankAdjBO.TYPE.clear()).addForeignKey("bank_adj_type","type",false);
	private TypeBigDecimal adjAmt = new TypeBigDecimal("adj_amt").setFieldDescriptor(BankAdjBO.ADJAMT.clear());
	private TypeInteger adjUserid = new TypeInteger("adj_userid").setFieldDescriptor(BankAdjBO.ADJUSERID.clear()).addForeignKey("personnel","userid_srl",false);
	private TypeString reason = new TypeString("reason").setFieldDescriptor(BankAdjBO.REASON.clear());
	private TypeDate reconDate = new TypeDate("recon_date").setFieldDescriptor(BankAdjBO.RECONDATE.clear()).setNullable();
	private TypeInteger reconUserid = new TypeInteger("recon_userid").setFieldDescriptor(BankAdjBO.RECONUSERID.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeInteger reconStatus = new TypeInteger("recon_status").setFieldDescriptor(BankAdjBO.RECONSTATUS.clear()).setNullable();
	private TypeInteger intJournalNum = new TypeInteger("int_journal_num").setFieldDescriptor(BankAdjBO.INTJOURNALNUM.clear()).addForeignKey("journal","int_journal_num",false).setNullable();

	public BankAdjVO() {
		super(BankAdjBO.TABLE, BankAdjBO.SYSTEM, BankAdjBO.CORIS_DISTRICT_DB.setSource("D"), BankAdjBO.CORIS_JUSTICE_DB.setSource("J"), BankAdjBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BankAdjBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public BankAdjVO(String source) {
		super(BankAdjBO.TABLE, BankAdjBO.SYSTEM, BankAdjBO.CORIS_DISTRICT_DB.setSource("D"), BankAdjBO.CORIS_JUSTICE_DB.setSource("J"), BankAdjBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BankAdjBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(bankAcctNum);
		this.getPropertyList().add(adjDatetime);
		this.getPropertyList().add(type);
		this.getPropertyList().add(adjAmt);
		this.getPropertyList().add(adjUserid);
		this.getPropertyList().add(reason);
		this.getPropertyList().add(reconDate);
		this.getPropertyList().add(reconUserid);
		this.getPropertyList().add(reconStatus);
		this.getPropertyList().add(intJournalNum);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(BankAdjBO.ADJDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(BankAdjBO.ADJDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(BankAdjBO.RECONDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(BankAdjBO.RECONDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
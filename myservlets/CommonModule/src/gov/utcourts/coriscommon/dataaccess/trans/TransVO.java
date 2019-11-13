package gov.utcourts.coriscommon.dataaccess.trans;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class TransVO extends BaseVO { 

	private TypeInteger intJournalNum = new TypeInteger("int_journal_num").setFieldDescriptor(TransBO.INTJOURNALNUM.clear()).addForeignKey("check_detl","int_journal_num",false).addForeignKey("trans_acct","int_journal_num",false).addForeignKey("trans_detl","int_journal_num",false).addForeignKey("trans_dist","int_journal_num",false).addForeignKey("trans_revrs","int_journal_num",false).addForeignKey("trans_revrs","revrs_journal_num",false).addForeignKey("journal","int_journal_num",false).setAsPrimaryKey();
	private TypeInteger transNum = new TypeInteger("trans_num").setFieldDescriptor(TransBO.TRANSNUM.clear()).addForeignKey("check_detl","trans_num",false).addForeignKey("trans_acct","trans_num",false).addForeignKey("trans_detl","trans_num",false).addForeignKey("trans_dist","trans_num",false).addForeignKey("trans_revrs","trans_num",false).addForeignKey("trans_revrs","revrs_trans_num",false).setAsPrimaryKey();
	private TypeDate transDatetime = new TypeDate("trans_datetime").setFieldDescriptor(TransBO.TRANSDATETIME.clear());
	private TypeBigDecimal transAmt = new TypeBigDecimal("trans_amt").setFieldDescriptor(TransBO.TRANSAMT.clear());
	private TypeInteger payorPartyNum = new TypeInteger("payor_party_num").setFieldDescriptor(TransBO.PAYORPARTYNUM.clear()).addForeignKey("party","party_num",false).setNullable();
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(TransBO.USERIDSRL.clear()).addForeignKey("personnel","userid_srl",false);
	private TypeString maillogFlag = new TypeString("maillog_flag").setFieldDescriptor(TransBO.MAILLOGFLAG.clear());
	private TypeString posted = new TypeString("posted").setFieldDescriptor(TransBO.POSTED.clear());
	private TypeString outType = new TypeString("out_type").setFieldDescriptor(TransBO.OUTTYPE.clear()).setNullable();
	private TypeString note = new TypeString("note").setFieldDescriptor(TransBO.NOTE.clear()).setNullable();
	private TypeInteger supervisorId = new TypeInteger("supervisor_id").setFieldDescriptor(TransBO.SUPERVISORID.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeDate effectiveDate = new TypeDate("effective_date").setFieldDescriptor(TransBO.EFFECTIVEDATE.clear()).setNullable();

	public TransVO() {
		super(TransBO.TABLE, TransBO.SYSTEM, TransBO.CORIS_DISTRICT_DB.setSource("D"), TransBO.CORIS_JUSTICE_DB.setSource("J"), TransBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TransBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public TransVO(String source) {
		super(TransBO.TABLE, TransBO.SYSTEM, TransBO.CORIS_DISTRICT_DB.setSource("D"), TransBO.CORIS_JUSTICE_DB.setSource("J"), TransBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TransBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intJournalNum);
		this.getPropertyList().add(transNum);
		this.getPropertyList().add(transDatetime);
		this.getPropertyList().add(transAmt);
		this.getPropertyList().add(payorPartyNum);
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(maillogFlag);
		this.getPropertyList().add(posted);
		this.getPropertyList().add(outType);
		this.getPropertyList().add(note);
		this.getPropertyList().add(supervisorId);
		this.getPropertyList().add(effectiveDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(TransBO.TRANSDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(TransBO.TRANSDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(TransBO.EFFECTIVEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(TransBO.EFFECTIVEDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
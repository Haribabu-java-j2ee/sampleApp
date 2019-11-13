package gov.utcourts.coriscommon.dataaccess.osdcaccthistory;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class OsdcAcctHistoryVO extends BaseVO { 

	private TypeInteger osdcAcctSeq = new TypeInteger("osdc_acct_seq").setFieldDescriptor(OsdcAcctHistoryBO.OSDCACCTSEQ.clear()).setAsPrimaryKey();
	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(OsdcAcctHistoryBO.ACCTNUM.clear()).addForeignKey("account","acct_num",false);
	private TypeInteger osdcCreateUserid = new TypeInteger("osdc_create_userid").setFieldDescriptor(OsdcAcctHistoryBO.OSDCCREATEUSERID.clear()).addForeignKey("personnel","userid_srl",false);
	private TypeString osdcStatus = new TypeString("osdc_status").setFieldDescriptor(OsdcAcctHistoryBO.OSDCSTATUS.clear());
	private TypeDate osdcCreateDatetime = new TypeDate("osdc_create_datetime").setFieldDescriptor(OsdcAcctHistoryBO.OSDCCREATEDATETIME.clear());
	private TypeInteger osdcRecallUserid = new TypeInteger("osdc_recall_userid").setFieldDescriptor(OsdcAcctHistoryBO.OSDCRECALLUSERID.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeDate osdcRecallDatetime = new TypeDate("osdc_recall_datetime").setFieldDescriptor(OsdcAcctHistoryBO.OSDCRECALLDATETIME.clear()).setNullable();
	private TypeDate osdcSentDatetime = new TypeDate("osdc_sent_datetime").setFieldDescriptor(OsdcAcctHistoryBO.OSDCSENTDATETIME.clear()).setNullable();
	private TypeString debtCollNote = new TypeString("debt_coll_note").setFieldDescriptor(OsdcAcctHistoryBO.DEBTCOLLNOTE.clear()).setNullable();

	public OsdcAcctHistoryVO() {
		super(OsdcAcctHistoryBO.TABLE, OsdcAcctHistoryBO.SYSTEM, OsdcAcctHistoryBO.CORIS_DISTRICT_DB.setSource("D"), OsdcAcctHistoryBO.CORIS_JUSTICE_DB.setSource("J"), OsdcAcctHistoryBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OsdcAcctHistoryBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public OsdcAcctHistoryVO(String source) {
		super(OsdcAcctHistoryBO.TABLE, OsdcAcctHistoryBO.SYSTEM, OsdcAcctHistoryBO.CORIS_DISTRICT_DB.setSource("D"), OsdcAcctHistoryBO.CORIS_JUSTICE_DB.setSource("J"), OsdcAcctHistoryBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OsdcAcctHistoryBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(osdcAcctSeq);
		this.getPropertyList().add(acctNum);
		this.getPropertyList().add(osdcCreateUserid);
		this.getPropertyList().add(osdcStatus);
		this.getPropertyList().add(osdcCreateDatetime);
		this.getPropertyList().add(osdcRecallUserid);
		this.getPropertyList().add(osdcRecallDatetime);
		this.getPropertyList().add(osdcSentDatetime);
		this.getPropertyList().add(debtCollNote);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(OsdcAcctHistoryBO.OSDCCREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(OsdcAcctHistoryBO.OSDCCREATEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(OsdcAcctHistoryBO.OSDCRECALLDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(OsdcAcctHistoryBO.OSDCRECALLDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(OsdcAcctHistoryBO.OSDCSENTDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(OsdcAcctHistoryBO.OSDCSENTDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
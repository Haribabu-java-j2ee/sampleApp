package gov.utcourts.coriscommon.dataaccess.journal;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class JournalVO extends BaseVO { 

	private TypeInteger intJournalNum = new TypeInteger("int_journal_num").setFieldDescriptor(JournalBO.INTJOURNALNUM.clear()).addForeignKey("bank_adj","int_journal_num",true).addForeignKey("cashier","int_journal_num",true).addForeignKey("cashier_cnt","int_journal_num",false).addForeignKey("trans","int_journal_num",false).setAsPrimaryKey();
	private TypeInteger journalNum = new TypeInteger("journal_num").setFieldDescriptor(JournalBO.JOURNALNUM.clear());
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(JournalBO.LOCNCODE.clear());
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(JournalBO.COURTTYPE.clear());
	private TypeDate startDatetime = new TypeDate("start_datetime").setFieldDescriptor(JournalBO.STARTDATETIME.clear());
	private TypeDate endDatetime = new TypeDate("end_datetime").setFieldDescriptor(JournalBO.ENDDATETIME.clear()).setNullable();
	private TypeInteger nextTransNum = new TypeInteger("next_trans_num").setFieldDescriptor(JournalBO.NEXTTRANSNUM.clear());
	private TypeString cutoffFlag = new TypeString("cutoff_flag").setFieldDescriptor(JournalBO.CUTOFFFLAG.clear());
	private TypeDate journalDate = new TypeDate("journal_date").setFieldDescriptor(JournalBO.JOURNALDATE.clear()).setNullable();
	private TypeInteger cutoffUseridSrl = new TypeInteger("cutoff_userid_srl").setFieldDescriptor(JournalBO.CUTOFFUSERIDSRL.clear()).setNullable();

	public JournalVO() {
		super(JournalBO.TABLE, JournalBO.SYSTEM, JournalBO.CORIS_DISTRICT_DB.setSource("D"), JournalBO.CORIS_JUSTICE_DB.setSource("J"), JournalBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JournalBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public JournalVO(String source) {
		super(JournalBO.TABLE, JournalBO.SYSTEM, JournalBO.CORIS_DISTRICT_DB.setSource("D"), JournalBO.CORIS_JUSTICE_DB.setSource("J"), JournalBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JournalBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intJournalNum);
		this.getPropertyList().add(journalNum);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(startDatetime);
		this.getPropertyList().add(endDatetime);
		this.getPropertyList().add(nextTransNum);
		this.getPropertyList().add(cutoffFlag);
		this.getPropertyList().add(journalDate);
		this.getPropertyList().add(cutoffUseridSrl);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(JournalBO.STARTDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(JournalBO.STARTDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(JournalBO.ENDDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(JournalBO.ENDDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(JournalBO.JOURNALDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(JournalBO.JOURNALDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
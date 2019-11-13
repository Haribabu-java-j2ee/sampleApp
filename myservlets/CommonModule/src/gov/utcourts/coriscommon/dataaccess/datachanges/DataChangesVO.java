package gov.utcourts.coriscommon.dataaccess.datachanges;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DataChangesVO extends BaseVO { 

	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(DataChangesBO.USERIDSRL.clear()).addForeignKey("personnel","userid_srl",false);
	private TypeDate modDatetime = new TypeDate("mod_datetime").setFieldDescriptor(DataChangesBO.MODDATETIME.clear());
	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(DataChangesBO.ACCTNUM.clear()).setNullable();
	private TypeInteger intJournalNum = new TypeInteger("int_journal_num").setFieldDescriptor(DataChangesBO.INTJOURNALNUM.clear()).setNullable();
	private TypeInteger transNum = new TypeInteger("trans_num").setFieldDescriptor(DataChangesBO.TRANSNUM.clear()).setNullable();
	private TypeString note = new TypeString("note").setFieldDescriptor(DataChangesBO.NOTE.clear()).setNullable();

	public DataChangesVO() {
		super(DataChangesBO.TABLE, DataChangesBO.SYSTEM, DataChangesBO.CORIS_DISTRICT_DB.setSource("D"), DataChangesBO.CORIS_JUSTICE_DB.setSource("J"), DataChangesBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DataChangesBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DataChangesVO(String source) {
		super(DataChangesBO.TABLE, DataChangesBO.SYSTEM, DataChangesBO.CORIS_DISTRICT_DB.setSource("D"), DataChangesBO.CORIS_JUSTICE_DB.setSource("J"), DataChangesBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DataChangesBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(modDatetime);
		this.getPropertyList().add(acctNum);
		this.getPropertyList().add(intJournalNum);
		this.getPropertyList().add(transNum);
		this.getPropertyList().add(note);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(DataChangesBO.MODDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(DataChangesBO.MODDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
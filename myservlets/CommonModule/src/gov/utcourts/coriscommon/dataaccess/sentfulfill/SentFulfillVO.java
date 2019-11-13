package gov.utcourts.coriscommon.dataaccess.sentfulfill;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SentFulfillVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(SentFulfillBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeInteger sequence = new TypeInteger("sequence").setFieldDescriptor(SentFulfillBO.SEQUENCE.clear()).setAsPrimaryKey();
	private TypeString sentFulfillCode = new TypeString("sent_fulfill_code").setFieldDescriptor(SentFulfillBO.SENTFULFILLCODE.clear()).addForeignKey("sent_fulfill_type","sent_fulfill_code",false);
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(SentFulfillBO.USERIDSRL.clear()).addForeignKey("personnel","userid_srl",false);
	private TypeDate modDatetime = new TypeDate("mod_datetime").setFieldDescriptor(SentFulfillBO.MODDATETIME.clear());
	private TypeString description = new TypeString("description").setFieldDescriptor(SentFulfillBO.DESCRIPTION.clear()).setNullable();
	private TypeDate dueDate = new TypeDate("due_date").setFieldDescriptor(SentFulfillBO.DUEDATE.clear()).setNullable();
	private TypeDate completionDate = new TypeDate("completion_date").setFieldDescriptor(SentFulfillBO.COMPLETIONDATE.clear()).setNullable();
	private TypeString statusNote = new TypeString("status_note").setFieldDescriptor(SentFulfillBO.STATUSNOTE.clear()).setNullable();

	public SentFulfillVO() {
		super(SentFulfillBO.TABLE, SentFulfillBO.SYSTEM, SentFulfillBO.CORIS_DISTRICT_DB.setSource("D"), SentFulfillBO.CORIS_JUSTICE_DB.setSource("J"), SentFulfillBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SentFulfillBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SentFulfillVO(String source) {
		super(SentFulfillBO.TABLE, SentFulfillBO.SYSTEM, SentFulfillBO.CORIS_DISTRICT_DB.setSource("D"), SentFulfillBO.CORIS_JUSTICE_DB.setSource("J"), SentFulfillBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SentFulfillBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(sequence);
		this.getPropertyList().add(sentFulfillCode);
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(modDatetime);
		this.getPropertyList().add(description);
		this.getPropertyList().add(dueDate);
		this.getPropertyList().add(completionDate);
		this.getPropertyList().add(statusNote);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(SentFulfillBO.MODDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SentFulfillBO.MODDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SentFulfillBO.DUEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SentFulfillBO.DUEDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SentFulfillBO.COMPLETIONDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SentFulfillBO.COMPLETIONDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
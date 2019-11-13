package gov.utcourts.coriscommon.dataaccess.odrcaseprocess;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class OdrCaseProcessVO extends BaseVO { 

	private TypeInteger processId = new TypeInteger("process_id").setFieldDescriptor(OdrCaseProcessBO.PROCESSID.clear());
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(OdrCaseProcessBO.INTCASENUM.clear());
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(OdrCaseProcessBO.PARTYNUM.clear());
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(OdrCaseProcessBO.USERIDSRL.clear());
	private TypeDate eventDatetime = new TypeDate("event_datetime").setFieldDescriptor(OdrCaseProcessBO.EVENTDATETIME.clear());
	private TypeString action = new TypeString("action").setFieldDescriptor(OdrCaseProcessBO.ACTION.clear());
	private TypeString process = new TypeString("process").setFieldDescriptor(OdrCaseProcessBO.PROCESS.clear());

	public OdrCaseProcessVO() {
		super(OdrCaseProcessBO.TABLE, OdrCaseProcessBO.SYSTEM, OdrCaseProcessBO.CORIS_DISTRICT_DB.setSource("D"), OdrCaseProcessBO.CORIS_JUSTICE_DB.setSource("J"), OdrCaseProcessBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OdrCaseProcessBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public OdrCaseProcessVO(String source) {
		super(OdrCaseProcessBO.TABLE, OdrCaseProcessBO.SYSTEM, OdrCaseProcessBO.CORIS_DISTRICT_DB.setSource("D"), OdrCaseProcessBO.CORIS_JUSTICE_DB.setSource("J"), OdrCaseProcessBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OdrCaseProcessBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(processId);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(partyNum);
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(eventDatetime);
		this.getPropertyList().add(action);
		this.getPropertyList().add(process);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(OdrCaseProcessBO.EVENTDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(OdrCaseProcessBO.EVENTDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
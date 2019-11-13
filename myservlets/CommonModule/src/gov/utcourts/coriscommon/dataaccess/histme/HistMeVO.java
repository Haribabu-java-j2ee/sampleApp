package gov.utcourts.coriscommon.dataaccess.histme;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class HistMeVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(HistMeBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false);
	private TypeDate eventDatetime = new TypeDate("event_datetime").setFieldDescriptor(HistMeBO.EVENTDATETIME.clear());
	private TypeInteger meId = new TypeInteger("me_id").setFieldDescriptor(HistMeBO.MEID.clear());
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(HistMeBO.USERIDSRL.clear()).addForeignKey("personnel","userid_srl",false);

	public HistMeVO() {
		super(HistMeBO.TABLE, HistMeBO.SYSTEM, HistMeBO.CORIS_DISTRICT_DB.setSource("D"), HistMeBO.CORIS_JUSTICE_DB.setSource("J"), HistMeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), HistMeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public HistMeVO(String source) {
		super(HistMeBO.TABLE, HistMeBO.SYSTEM, HistMeBO.CORIS_DISTRICT_DB.setSource("D"), HistMeBO.CORIS_JUSTICE_DB.setSource("J"), HistMeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), HistMeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(eventDatetime);
		this.getPropertyList().add(meId);
		this.getPropertyList().add(useridSrl);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(HistMeBO.EVENTDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(HistMeBO.EVENTDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
package gov.utcourts.coriscommon.dataaccess.sstay;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SStayVO extends BaseVO { 

	private TypeInteger sIntCaseNum = new TypeInteger("s_int_case_num").setFieldDescriptor(SStayBO.SINTCASENUM.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger sUseridSrl = new TypeInteger("s_userid_srl").setFieldDescriptor(SStayBO.SUSERIDSRL.clear()).setNullable();
	private TypeDate sDatetime = new TypeDate("s_datetime").setFieldDescriptor(SStayBO.SDATETIME.clear()).setNullable();
	private TypeInteger sMeId = new TypeInteger("s_me_id").setFieldDescriptor(SStayBO.SMEID.clear()).setNullable();
	private TypeString sOperation = new TypeString("s_operation").setFieldDescriptor(SStayBO.SOPERATION.clear()).setNullable();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(SStayBO.INTCASENUM.clear()).setNullable();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(SStayBO.SEQ.clear()).setNullable().setAsPrimaryKey();
	private TypeString stayCode = new TypeString("stay_code").setFieldDescriptor(SStayBO.STAYCODE.clear()).setNullable();
	private TypeDate beginDate = new TypeDate("begin_date").setFieldDescriptor(SStayBO.BEGINDATE.clear()).setNullable();
	private TypeDate endDate = new TypeDate("end_date").setFieldDescriptor(SStayBO.ENDDATE.clear()).setNullable();
	private TypeString noteText = new TypeString("note_text").setFieldDescriptor(SStayBO.NOTETEXT.clear()).setNullable();

	public SStayVO() {
		super(SStayBO.TABLE, SStayBO.SYSTEM, SStayBO.CORIS_DISTRICT_DB.setSource("D"), SStayBO.CORIS_JUSTICE_DB.setSource("J"), SStayBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SStayBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SStayVO(String source) {
		super(SStayBO.TABLE, SStayBO.SYSTEM, SStayBO.CORIS_DISTRICT_DB.setSource("D"), SStayBO.CORIS_JUSTICE_DB.setSource("J"), SStayBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SStayBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(sIntCaseNum);
		this.getPropertyList().add(sUseridSrl);
		this.getPropertyList().add(sDatetime);
		this.getPropertyList().add(sMeId);
		this.getPropertyList().add(sOperation);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(stayCode);
		this.getPropertyList().add(beginDate);
		this.getPropertyList().add(endDate);
		this.getPropertyList().add(noteText);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(SStayBO.SDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SStayBO.SDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SStayBO.BEGINDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SStayBO.BEGINDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SStayBO.ENDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SStayBO.ENDDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
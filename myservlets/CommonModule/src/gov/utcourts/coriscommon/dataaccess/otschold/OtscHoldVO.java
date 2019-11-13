package gov.utcourts.coriscommon.dataaccess.otschold;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class OtscHoldVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(OtscHoldBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setNullable().setAsPrimaryKey();
	private TypeDate endDate = new TypeDate("end_date").setFieldDescriptor(OtscHoldBO.ENDDATE.clear()).setNullable();
	private TypeDate startDate = new TypeDate("start_date").setFieldDescriptor(OtscHoldBO.STARTDATE.clear()).setNullable();
	private TypeString reason = new TypeString("reason").setFieldDescriptor(OtscHoldBO.REASON.clear()).setNullable();

	public OtscHoldVO() {
		super(OtscHoldBO.TABLE, OtscHoldBO.SYSTEM, OtscHoldBO.CORIS_DISTRICT_DB.setSource("D"), OtscHoldBO.CORIS_JUSTICE_DB.setSource("J"), OtscHoldBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OtscHoldBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public OtscHoldVO(String source) {
		super(OtscHoldBO.TABLE, OtscHoldBO.SYSTEM, OtscHoldBO.CORIS_DISTRICT_DB.setSource("D"), OtscHoldBO.CORIS_JUSTICE_DB.setSource("J"), OtscHoldBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OtscHoldBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(endDate);
		this.getPropertyList().add(startDate);
		this.getPropertyList().add(reason);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(OtscHoldBO.ENDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(OtscHoldBO.ENDDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(OtscHoldBO.STARTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(OtscHoldBO.STARTDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
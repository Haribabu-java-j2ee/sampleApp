package gov.utcourts.coriscommon.dataaccess.mentalsafety;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class MentalSafetyVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(MentalSafetyBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeDate reportDate = new TypeDate("report_date").setFieldDescriptor(MentalSafetyBO.REPORTDATE.clear()).setNullable();
	private TypeString status = new TypeString("status").setFieldDescriptor(MentalSafetyBO.STATUS.clear()).setNullable();

	public MentalSafetyVO() {
		super(MentalSafetyBO.TABLE, MentalSafetyBO.SYSTEM, MentalSafetyBO.CORIS_DISTRICT_DB.setSource("D"), MentalSafetyBO.CORIS_JUSTICE_DB.setSource("J"), MentalSafetyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), MentalSafetyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public MentalSafetyVO(String source) {
		super(MentalSafetyBO.TABLE, MentalSafetyBO.SYSTEM, MentalSafetyBO.CORIS_DISTRICT_DB.setSource("D"), MentalSafetyBO.CORIS_JUSTICE_DB.setSource("J"), MentalSafetyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), MentalSafetyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(reportDate);
		this.getPropertyList().add(status);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(MentalSafetyBO.REPORTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(MentalSafetyBO.REPORTDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
package gov.utcourts.coriscommon.dataaccess.daystodisp;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DaysToDispVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(DaysToDispBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeDate computeDate = new TypeDate("compute_date").setFieldDescriptor(DaysToDispBO.COMPUTEDATE.clear());
	private TypeInteger status = new TypeInteger("status").setFieldDescriptor(DaysToDispBO.STATUS.clear());
	private TypeInteger stayDays = new TypeInteger("stay_days").setFieldDescriptor(DaysToDispBO.STAYDAYS.clear());
	private TypeInteger trueDispDays = new TypeInteger("true_disp_days").setFieldDescriptor(DaysToDispBO.TRUEDISPDAYS.clear()).setNullable();
	private TypeDate trueDispDate = new TypeDate("true_disp_date").setFieldDescriptor(DaysToDispBO.TRUEDISPDATE.clear()).setNullable();
	private TypeInteger origDispDays = new TypeInteger("orig_disp_days").setFieldDescriptor(DaysToDispBO.ORIGDISPDAYS.clear()).setNullable();
	private TypeDate origDispDate = new TypeDate("orig_disp_date").setFieldDescriptor(DaysToDispBO.ORIGDISPDATE.clear()).setNullable();

	public DaysToDispVO() {
		super(DaysToDispBO.TABLE, DaysToDispBO.SYSTEM, DaysToDispBO.CORIS_DISTRICT_DB.setSource("D"), DaysToDispBO.CORIS_JUSTICE_DB.setSource("J"), DaysToDispBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DaysToDispBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DaysToDispVO(String source) {
		super(DaysToDispBO.TABLE, DaysToDispBO.SYSTEM, DaysToDispBO.CORIS_DISTRICT_DB.setSource("D"), DaysToDispBO.CORIS_JUSTICE_DB.setSource("J"), DaysToDispBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DaysToDispBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(computeDate);
		this.getPropertyList().add(status);
		this.getPropertyList().add(stayDays);
		this.getPropertyList().add(trueDispDays);
		this.getPropertyList().add(trueDispDate);
		this.getPropertyList().add(origDispDays);
		this.getPropertyList().add(origDispDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(DaysToDispBO.COMPUTEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DaysToDispBO.COMPUTEDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(DaysToDispBO.TRUEDISPDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DaysToDispBO.TRUEDISPDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(DaysToDispBO.ORIGDISPDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DaysToDispBO.ORIGDISPDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
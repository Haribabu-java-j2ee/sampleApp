package gov.utcourts.coriscommon.dataaccess.correctionsdata;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CorrectionsDataVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(CorrectionsDataBO.INTCASENUM.clear()).addForeignKey("crim_case","int_case_num",false).setAsPrimaryKey();
	private TypeInteger offenderNum = new TypeInteger("offender_num").setFieldDescriptor(CorrectionsDataBO.OFFENDERNUM.clear()).setNullable();
	private TypeDate sentRptDate = new TypeDate("sent_rpt_date").setFieldDescriptor(CorrectionsDataBO.SENTRPTDATE.clear()).setNullable();
	private TypeString sentRptStatus = new TypeString("sent_rpt_status").setFieldDescriptor(CorrectionsDataBO.SENTRPTSTATUS.clear()).setNullable();
	private TypeString sentRptAction = new TypeString("sent_rpt_action").setFieldDescriptor(CorrectionsDataBO.SENTRPTACTION.clear()).setNullable();

	public CorrectionsDataVO() {
		super(CorrectionsDataBO.TABLE, CorrectionsDataBO.SYSTEM, CorrectionsDataBO.CORIS_DISTRICT_DB.setSource("D"), CorrectionsDataBO.CORIS_JUSTICE_DB.setSource("J"), CorrectionsDataBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CorrectionsDataBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CorrectionsDataVO(String source) {
		super(CorrectionsDataBO.TABLE, CorrectionsDataBO.SYSTEM, CorrectionsDataBO.CORIS_DISTRICT_DB.setSource("D"), CorrectionsDataBO.CORIS_JUSTICE_DB.setSource("J"), CorrectionsDataBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CorrectionsDataBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(offenderNum);
		this.getPropertyList().add(sentRptDate);
		this.getPropertyList().add(sentRptStatus);
		this.getPropertyList().add(sentRptAction);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(CorrectionsDataBO.SENTRPTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CorrectionsDataBO.SENTRPTDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
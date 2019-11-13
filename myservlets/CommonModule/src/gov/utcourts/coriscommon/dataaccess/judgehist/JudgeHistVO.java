package gov.utcourts.coriscommon.dataaccess.judgehist;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class JudgeHistVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(JudgeHistBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false);
	private TypeInteger judgeId = new TypeInteger("judge_id").setFieldDescriptor(JudgeHistBO.JUDGEID.clear()).addForeignKey("personnel","userid_srl",false);
	private TypeDate startDate = new TypeDate("start_date").setFieldDescriptor(JudgeHistBO.STARTDATE.clear());
	private TypeDate endDate = new TypeDate("end_date").setFieldDescriptor(JudgeHistBO.ENDDATE.clear()).setNullable();
	private TypeInteger clerkId = new TypeInteger("clerk_id").setFieldDescriptor(JudgeHistBO.CLERKID.clear()).addForeignKey("personnel","userid_srl",false);

	public JudgeHistVO() {
		super(JudgeHistBO.TABLE, JudgeHistBO.SYSTEM, JudgeHistBO.CORIS_DISTRICT_DB.setSource("D"), JudgeHistBO.CORIS_JUSTICE_DB.setSource("J"), JudgeHistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JudgeHistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public JudgeHistVO(String source) {
		super(JudgeHistBO.TABLE, JudgeHistBO.SYSTEM, JudgeHistBO.CORIS_DISTRICT_DB.setSource("D"), JudgeHistBO.CORIS_JUSTICE_DB.setSource("J"), JudgeHistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JudgeHistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(judgeId);
		this.getPropertyList().add(startDate);
		this.getPropertyList().add(endDate);
		this.getPropertyList().add(clerkId);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(JudgeHistBO.STARTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(JudgeHistBO.STARTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(JudgeHistBO.ENDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(JudgeHistBO.ENDDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
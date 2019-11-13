package gov.utcourts.coriscommon.dataaccess.judgeaway;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class JudgeAwayVO extends BaseVO { 

	private TypeInteger judgeId = new TypeInteger("judge_id").setFieldDescriptor(JudgeAwayBO.JUDGEID.clear()).addForeignKey("personnel","userid_srl",false).setAsPrimaryKey();
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(JudgeAwayBO.CREATEDATETIME.clear()).setAsPrimaryKey();
	private TypeDate beginDatetime = new TypeDate("begin_datetime").setFieldDescriptor(JudgeAwayBO.BEGINDATETIME.clear());
	private TypeDate endDatetime = new TypeDate("end_datetime").setFieldDescriptor(JudgeAwayBO.ENDDATETIME.clear());
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(JudgeAwayBO.USERIDSRL.clear());
	private TypeString scheduleReason = new TypeString("schedule_reason").setFieldDescriptor(JudgeAwayBO.SCHEDULEREASON.clear()).setNullable();
	private TypeInteger cancelUseridSrl = new TypeInteger("cancel_userid_srl").setFieldDescriptor(JudgeAwayBO.CANCELUSERIDSRL.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeString cancelReason = new TypeString("cancel_reason").setFieldDescriptor(JudgeAwayBO.CANCELREASON.clear()).setNullable();

	public JudgeAwayVO() {
		super(JudgeAwayBO.TABLE, JudgeAwayBO.SYSTEM, JudgeAwayBO.CORIS_DISTRICT_DB.setSource("D"), JudgeAwayBO.CORIS_JUSTICE_DB.setSource("J"), JudgeAwayBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JudgeAwayBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public JudgeAwayVO(String source) {
		super(JudgeAwayBO.TABLE, JudgeAwayBO.SYSTEM, JudgeAwayBO.CORIS_DISTRICT_DB.setSource("D"), JudgeAwayBO.CORIS_JUSTICE_DB.setSource("J"), JudgeAwayBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JudgeAwayBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(judgeId);
		this.getPropertyList().add(createDatetime);
		this.getPropertyList().add(beginDatetime);
		this.getPropertyList().add(endDatetime);
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(scheduleReason);
		this.getPropertyList().add(cancelUseridSrl);
		this.getPropertyList().add(cancelReason);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(JudgeAwayBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(JudgeAwayBO.CREATEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(JudgeAwayBO.BEGINDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(JudgeAwayBO.BEGINDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(JudgeAwayBO.ENDDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(JudgeAwayBO.ENDDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
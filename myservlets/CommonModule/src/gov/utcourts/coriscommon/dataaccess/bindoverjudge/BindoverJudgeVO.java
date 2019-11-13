package gov.utcourts.coriscommon.dataaccess.bindoverjudge;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class BindoverJudgeVO extends BaseVO { 

	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(BindoverJudgeBO.USERIDSRL.clear()).addForeignKey("personnel","userid_srl",false).setAsPrimaryKey();
	private TypeInteger bindoverDay = new TypeInteger("bindover_day").setFieldDescriptor(BindoverJudgeBO.BINDOVERDAY.clear()).setNullable();
	private TypeDate bindoverTime = new TypeDate("bindover_time").setFieldDescriptor(BindoverJudgeBO.BINDOVERTIME.clear());
	private TypeString bindoverAmpm = new TypeString("bindover_ampm").setFieldDescriptor(BindoverJudgeBO.BINDOVERAMPM.clear()).setNullable();
	private TypeString bindoverRoom = new TypeString("bindover_room").setFieldDescriptor(BindoverJudgeBO.BINDOVERROOM.clear()).setNullable();

	public BindoverJudgeVO() {
		super(BindoverJudgeBO.TABLE, BindoverJudgeBO.SYSTEM, BindoverJudgeBO.CORIS_DISTRICT_DB.setSource("D"), BindoverJudgeBO.CORIS_JUSTICE_DB.setSource("J"), BindoverJudgeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BindoverJudgeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public BindoverJudgeVO(String source) {
		super(BindoverJudgeBO.TABLE, BindoverJudgeBO.SYSTEM, BindoverJudgeBO.CORIS_DISTRICT_DB.setSource("D"), BindoverJudgeBO.CORIS_JUSTICE_DB.setSource("J"), BindoverJudgeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BindoverJudgeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(bindoverDay);
		this.getPropertyList().add(bindoverTime);
		this.getPropertyList().add(bindoverAmpm);
		this.getPropertyList().add(bindoverRoom);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(BindoverJudgeBO.BINDOVERTIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(BindoverJudgeBO.BINDOVERTIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
package gov.utcourts.coriscommon.dataaccess.rotatejudges;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class RotateJudgesVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(RotateJudgesBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeInteger oldJudgeId = new TypeInteger("old_judge_id").setFieldDescriptor(RotateJudgesBO.OLDJUDGEID.clear());
	private TypeInteger newJudgeId = new TypeInteger("new_judge_id").setFieldDescriptor(RotateJudgesBO.NEWJUDGEID.clear());
	private TypeString newRoom = new TypeString("new_room").setFieldDescriptor(RotateJudgesBO.NEWROOM.clear()).setNullable();

	public RotateJudgesVO() {
		super(RotateJudgesBO.TABLE, RotateJudgesBO.SYSTEM, RotateJudgesBO.CORIS_DISTRICT_DB.setSource("D"), RotateJudgesBO.CORIS_JUSTICE_DB.setSource("J"), RotateJudgesBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), RotateJudgesBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public RotateJudgesVO(String source) {
		super(RotateJudgesBO.TABLE, RotateJudgesBO.SYSTEM, RotateJudgesBO.CORIS_DISTRICT_DB.setSource("D"), RotateJudgesBO.CORIS_JUSTICE_DB.setSource("J"), RotateJudgesBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), RotateJudgesBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(oldJudgeId);
		this.getPropertyList().add(newJudgeId);
		this.getPropertyList().add(newRoom);
	}

}
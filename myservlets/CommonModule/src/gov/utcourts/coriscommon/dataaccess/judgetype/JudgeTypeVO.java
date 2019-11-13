package gov.utcourts.coriscommon.dataaccess.judgetype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class JudgeTypeVO extends BaseVO { 

	private TypeString type = new TypeString("type").setFieldDescriptor(JudgeTypeBO.TYPE.clear()).addForeignKey("judge_type_profile","type",true).setNullable().setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(JudgeTypeBO.DESCR.clear()).setNullable();

	public JudgeTypeVO() {
		super(JudgeTypeBO.TABLE, JudgeTypeBO.SYSTEM, JudgeTypeBO.CORIS_DISTRICT_DB.setSource("D"), JudgeTypeBO.CORIS_JUSTICE_DB.setSource("J"), JudgeTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JudgeTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public JudgeTypeVO(String source) {
		super(JudgeTypeBO.TABLE, JudgeTypeBO.SYSTEM, JudgeTypeBO.CORIS_DISTRICT_DB.setSource("D"), JudgeTypeBO.CORIS_JUSTICE_DB.setSource("J"), JudgeTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JudgeTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(type);
		this.getPropertyList().add(descr);
	}

}
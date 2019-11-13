package gov.utcourts.coriscommon.dataaccess.judgetypeprofile;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class JudgeTypeProfileVO extends BaseVO { 

	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(JudgeTypeProfileBO.USERIDSRL.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeString type = new TypeString("type").setFieldDescriptor(JudgeTypeProfileBO.TYPE.clear()).addForeignKey("judge_type","type",true).setNullable();

	public JudgeTypeProfileVO() {
		super(JudgeTypeProfileBO.TABLE, JudgeTypeProfileBO.SYSTEM, JudgeTypeProfileBO.CORIS_DISTRICT_DB.setSource("D"), JudgeTypeProfileBO.CORIS_JUSTICE_DB.setSource("J"), JudgeTypeProfileBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JudgeTypeProfileBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public JudgeTypeProfileVO(String source) {
		super(JudgeTypeProfileBO.TABLE, JudgeTypeProfileBO.SYSTEM, JudgeTypeProfileBO.CORIS_DISTRICT_DB.setSource("D"), JudgeTypeProfileBO.CORIS_JUSTICE_DB.setSource("J"), JudgeTypeProfileBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JudgeTypeProfileBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(type);
	}

}
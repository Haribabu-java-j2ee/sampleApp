package gov.utcourts.coriscommon.dataaccess.seniorjudges;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SeniorJudgesVO extends BaseVO { 

	private TypeString lastName = new TypeString("last_name").setFieldDescriptor(SeniorJudgesBO.LASTNAME.clear());
	private TypeString firstName = new TypeString("first_name").setFieldDescriptor(SeniorJudgesBO.FIRSTNAME.clear());
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(SeniorJudgesBO.USERIDSRL.clear()).addForeignKey("personnel","userid_srl",false);

	public SeniorJudgesVO() {
		super(SeniorJudgesBO.TABLE, SeniorJudgesBO.SYSTEM, SeniorJudgesBO.CORIS_DISTRICT_DB.setSource("D"), SeniorJudgesBO.CORIS_JUSTICE_DB.setSource("J"), SeniorJudgesBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SeniorJudgesBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SeniorJudgesVO(String source) {
		super(SeniorJudgesBO.TABLE, SeniorJudgesBO.SYSTEM, SeniorJudgesBO.CORIS_DISTRICT_DB.setSource("D"), SeniorJudgesBO.CORIS_JUSTICE_DB.setSource("J"), SeniorJudgesBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SeniorJudgesBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(lastName);
		this.getPropertyList().add(firstName);
		this.getPropertyList().add(useridSrl);
	}

}
package gov.utcourts.coriscommon.dataaccess.autoexpungementsigningjudge;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AutoExpungementSigningJudgeVO extends BaseVO { 

	private TypeInteger autoExpungementSigningJudgeId = new TypeInteger("auto_expungement_signing_judge_id").setFieldDescriptor(AutoExpungementSigningJudgeBO.AUTOEXPUNGEMENTSIGNINGJUDGEID.clear()).setAsPrimaryKey();
	private TypeInteger judgeId = new TypeInteger("judge_id").setFieldDescriptor(AutoExpungementSigningJudgeBO.JUDGEID.clear()).addForeignKey("personnel","userid_srl",false);
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(AutoExpungementSigningJudgeBO.LOCNCODE.clear()).addForeignKey("profile","locn_code",false);
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(AutoExpungementSigningJudgeBO.COURTTYPE.clear()).addForeignKey("profile","court_type",false);

	public AutoExpungementSigningJudgeVO() {
		super(AutoExpungementSigningJudgeBO.TABLE, AutoExpungementSigningJudgeBO.SYSTEM, AutoExpungementSigningJudgeBO.CORIS_DISTRICT_DB.setSource("D"), AutoExpungementSigningJudgeBO.CORIS_JUSTICE_DB.setSource("J"), AutoExpungementSigningJudgeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AutoExpungementSigningJudgeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AutoExpungementSigningJudgeVO(String source) {
		super(AutoExpungementSigningJudgeBO.TABLE, AutoExpungementSigningJudgeBO.SYSTEM, AutoExpungementSigningJudgeBO.CORIS_DISTRICT_DB.setSource("D"), AutoExpungementSigningJudgeBO.CORIS_JUSTICE_DB.setSource("J"), AutoExpungementSigningJudgeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AutoExpungementSigningJudgeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(autoExpungementSigningJudgeId);
		this.getPropertyList().add(judgeId);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
	}

}
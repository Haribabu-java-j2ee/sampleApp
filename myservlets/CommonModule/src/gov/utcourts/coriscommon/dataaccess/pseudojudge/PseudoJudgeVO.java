package gov.utcourts.coriscommon.dataaccess.pseudojudge;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PseudoJudgeVO extends BaseVO { 

	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(PseudoJudgeBO.USERIDSRL.clear()).addForeignKey("personnel","userid_srl",false);
	private TypeString logname = new TypeString("logname").setFieldDescriptor(PseudoJudgeBO.LOGNAME.clear());

	public PseudoJudgeVO() {
		super(PseudoJudgeBO.TABLE, PseudoJudgeBO.SYSTEM, PseudoJudgeBO.CORIS_DISTRICT_DB.setSource("D"), PseudoJudgeBO.CORIS_JUSTICE_DB.setSource("J"), PseudoJudgeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PseudoJudgeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PseudoJudgeVO(String source) {
		super(PseudoJudgeBO.TABLE, PseudoJudgeBO.SYSTEM, PseudoJudgeBO.CORIS_DISTRICT_DB.setSource("D"), PseudoJudgeBO.CORIS_JUSTICE_DB.setSource("J"), PseudoJudgeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PseudoJudgeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(logname);
	}

}
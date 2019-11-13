package gov.utcourts.coriscommon.dataaccess.jointsev;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class JointSevVO extends BaseVO { 

	private TypeInteger jointSevNum = new TypeInteger("joint_sev_num").setFieldDescriptor(JointSevBO.JOINTSEVNUM.clear()).addForeignKey("acct_trust","joint_sev_num",true).addForeignKey("dc_acct_trust","joint_sev_num",true).setAsPrimaryKey();
	private TypeBigDecimal amtDue = new TypeBigDecimal("amt_due").setFieldDescriptor(JointSevBO.AMTDUE.clear());
	private TypeInteger payeePartyNum = new TypeInteger("payee_party_num").setFieldDescriptor(JointSevBO.PAYEEPARTYNUM.clear()).addForeignKey("party","party_num",false);
	private TypeString jointSevNote = new TypeString("joint_sev_note").setFieldDescriptor(JointSevBO.JOINTSEVNOTE.clear()).setNullable();

	public JointSevVO() {
		super(JointSevBO.TABLE, JointSevBO.SYSTEM, JointSevBO.CORIS_DISTRICT_DB.setSource("D"), JointSevBO.CORIS_JUSTICE_DB.setSource("J"), JointSevBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JointSevBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public JointSevVO(String source) {
		super(JointSevBO.TABLE, JointSevBO.SYSTEM, JointSevBO.CORIS_DISTRICT_DB.setSource("D"), JointSevBO.CORIS_JUSTICE_DB.setSource("J"), JointSevBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JointSevBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(jointSevNum);
		this.getPropertyList().add(amtDue);
		this.getPropertyList().add(payeePartyNum);
		this.getPropertyList().add(jointSevNote);
	}

}
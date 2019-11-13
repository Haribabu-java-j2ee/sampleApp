package gov.utcourts.coriscommon.dataaccess.previouscases;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PreviousCasesVO extends BaseVO { 

	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(PreviousCasesBO.USERIDSRL.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeString caseNum = new TypeString("case_num").setFieldDescriptor(PreviousCasesBO.CASENUM.clear()).setNullable();
	private TypeInteger position = new TypeInteger("position").setFieldDescriptor(PreviousCasesBO.POSITION.clear());

	public PreviousCasesVO() {
		super(PreviousCasesBO.TABLE, PreviousCasesBO.SYSTEM, PreviousCasesBO.CORIS_DISTRICT_DB.setSource("D"), PreviousCasesBO.CORIS_JUSTICE_DB.setSource("J"), PreviousCasesBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PreviousCasesBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PreviousCasesVO(String source) {
		super(PreviousCasesBO.TABLE, PreviousCasesBO.SYSTEM, PreviousCasesBO.CORIS_DISTRICT_DB.setSource("D"), PreviousCasesBO.CORIS_JUSTICE_DB.setSource("J"), PreviousCasesBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PreviousCasesBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(caseNum);
		this.getPropertyList().add(position);
	}

}
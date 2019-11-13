package gov.utcourts.coriscommon.dataaccess.mediationoutcome;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class MediationOutcomeVO extends BaseVO { 

	private TypeString type = new TypeString("type").setFieldDescriptor(MediationOutcomeBO.TYPE.clear()).addForeignKey("mediation","mediation_outcome",true).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(MediationOutcomeBO.DESCR.clear());

	public MediationOutcomeVO() {
		super(MediationOutcomeBO.TABLE, MediationOutcomeBO.SYSTEM, MediationOutcomeBO.CORIS_DISTRICT_DB.setSource("D"), MediationOutcomeBO.CORIS_JUSTICE_DB.setSource("J"), MediationOutcomeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), MediationOutcomeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public MediationOutcomeVO(String source) {
		super(MediationOutcomeBO.TABLE, MediationOutcomeBO.SYSTEM, MediationOutcomeBO.CORIS_DISTRICT_DB.setSource("D"), MediationOutcomeBO.CORIS_JUSTICE_DB.setSource("J"), MediationOutcomeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), MediationOutcomeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(type);
		this.getPropertyList().add(descr);
	}

}
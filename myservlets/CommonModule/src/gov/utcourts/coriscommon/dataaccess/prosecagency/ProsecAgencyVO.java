package gov.utcourts.coriscommon.dataaccess.prosecagency;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ProsecAgencyVO extends BaseVO { 

	private TypeString prosecAgency = new TypeString("prosec_agency").setFieldDescriptor(ProsecAgencyBO.PROSECAGENCY.clear()).setAsPrimaryKey();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(ProsecAgencyBO.PARTYNUM.clear()).addForeignKey("party","party_num",false);

	public ProsecAgencyVO() {
		super(ProsecAgencyBO.TABLE, ProsecAgencyBO.SYSTEM, ProsecAgencyBO.CORIS_DISTRICT_DB.setSource("D"), ProsecAgencyBO.CORIS_JUSTICE_DB.setSource("J"), ProsecAgencyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ProsecAgencyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public ProsecAgencyVO(String source) {
		super(ProsecAgencyBO.TABLE, ProsecAgencyBO.SYSTEM, ProsecAgencyBO.CORIS_DISTRICT_DB.setSource("D"), ProsecAgencyBO.CORIS_JUSTICE_DB.setSource("J"), ProsecAgencyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ProsecAgencyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(prosecAgency);
		this.getPropertyList().add(partyNum);
	}

}
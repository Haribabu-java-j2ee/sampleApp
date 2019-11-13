package gov.utcourts.coriscommon.dataaccess.prosecagencyatty;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ProsecAgencyAttyVO extends BaseVO { 

	private TypeString govCode = new TypeString("gov_code").setFieldDescriptor(ProsecAgencyAttyBO.GOVCODE.clear()).addForeignKey("gov_type","gov_code",false).setAsPrimaryKey();
	private TypeInteger barNum = new TypeInteger("bar_num").setFieldDescriptor(ProsecAgencyAttyBO.BARNUM.clear()).addForeignKey("attorney","bar_num",false).setAsPrimaryKey();
	private TypeString barState = new TypeString("bar_state").setFieldDescriptor(ProsecAgencyAttyBO.BARSTATE.clear()).addForeignKey("attorney","bar_state",false).setAsPrimaryKey();

	public ProsecAgencyAttyVO() {
		super(ProsecAgencyAttyBO.TABLE, ProsecAgencyAttyBO.SYSTEM, ProsecAgencyAttyBO.CORIS_DISTRICT_DB.setSource("D"), ProsecAgencyAttyBO.CORIS_JUSTICE_DB.setSource("J"), ProsecAgencyAttyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ProsecAgencyAttyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public ProsecAgencyAttyVO(String source) {
		super(ProsecAgencyAttyBO.TABLE, ProsecAgencyAttyBO.SYSTEM, ProsecAgencyAttyBO.CORIS_DISTRICT_DB.setSource("D"), ProsecAgencyAttyBO.CORIS_JUSTICE_DB.setSource("J"), ProsecAgencyAttyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ProsecAgencyAttyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(govCode);
		this.getPropertyList().add(barNum);
		this.getPropertyList().add(barState);
	}

}
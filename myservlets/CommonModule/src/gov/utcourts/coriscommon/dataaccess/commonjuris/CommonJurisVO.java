package gov.utcourts.coriscommon.dataaccess.commonjuris;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CommonJurisVO extends BaseVO { 

	private TypeString govCode = new TypeString("gov_code").setFieldDescriptor(CommonJurisBO.GOVCODE.clear()).setAsPrimaryKey();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(CommonJurisBO.LOCNCODE.clear()).setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(CommonJurisBO.COURTTYPE.clear()).setAsPrimaryKey();

	public CommonJurisVO() {
		super(CommonJurisBO.TABLE, CommonJurisBO.SYSTEM, CommonJurisBO.CORIS_DISTRICT_DB.setSource("D"), CommonJurisBO.CORIS_JUSTICE_DB.setSource("J"), CommonJurisBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CommonJurisBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CommonJurisVO(String source) {
		super(CommonJurisBO.TABLE, CommonJurisBO.SYSTEM, CommonJurisBO.CORIS_DISTRICT_DB.setSource("D"), CommonJurisBO.CORIS_JUSTICE_DB.setSource("J"), CommonJurisBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CommonJurisBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(govCode);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
	}

}
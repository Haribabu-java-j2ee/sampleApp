package gov.utcourts.coriscommon.dataaccess.govlocation;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class GovLocationVO extends BaseVO { 

	private TypeString cityCntyCode = new TypeString("city_cnty_code").setFieldDescriptor(GovLocationBO.CITYCNTYCODE.clear()).setAsPrimaryKey();
	private TypeString cityCntyName = new TypeString("city_cnty_name").setFieldDescriptor(GovLocationBO.CITYCNTYNAME.clear());
	private TypeString govCode = new TypeString("gov_code").setFieldDescriptor(GovLocationBO.GOVCODE.clear()).addForeignKey("gov_type","gov_code",false).setNullable();
	private TypeString campusCode = new TypeString("campus_code").setFieldDescriptor(GovLocationBO.CAMPUSCODE.clear()).setNullable();

	public GovLocationVO() {
		super(GovLocationBO.TABLE, GovLocationBO.SYSTEM, GovLocationBO.CORIS_DISTRICT_DB.setSource("D"), GovLocationBO.CORIS_JUSTICE_DB.setSource("J"), GovLocationBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), GovLocationBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public GovLocationVO(String source) {
		super(GovLocationBO.TABLE, GovLocationBO.SYSTEM, GovLocationBO.CORIS_DISTRICT_DB.setSource("D"), GovLocationBO.CORIS_JUSTICE_DB.setSource("J"), GovLocationBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), GovLocationBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(cityCntyCode);
		this.getPropertyList().add(cityCntyName);
		this.getPropertyList().add(govCode);
		this.getPropertyList().add(campusCode);
	}

}
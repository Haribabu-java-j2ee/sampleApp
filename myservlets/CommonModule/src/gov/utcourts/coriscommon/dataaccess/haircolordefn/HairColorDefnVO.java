package gov.utcourts.coriscommon.dataaccess.haircolordefn;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class HairColorDefnVO extends BaseVO { 

	private TypeString colorCode = new TypeString("color_code").setFieldDescriptor(HairColorDefnBO.COLORCODE.clear()).setAsPrimaryKey();
	private TypeString hairColorDescr = new TypeString("hair_color_descr").setFieldDescriptor(HairColorDefnBO.HAIRCOLORDESCR.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(HairColorDefnBO.REMOVEDFLAG.clear());

	public HairColorDefnVO() {
		super(HairColorDefnBO.TABLE, HairColorDefnBO.SYSTEM, HairColorDefnBO.CORIS_DISTRICT_DB.setSource("D"), HairColorDefnBO.CORIS_JUSTICE_DB.setSource("J"), HairColorDefnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), HairColorDefnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public HairColorDefnVO(String source) {
		super(HairColorDefnBO.TABLE, HairColorDefnBO.SYSTEM, HairColorDefnBO.CORIS_DISTRICT_DB.setSource("D"), HairColorDefnBO.CORIS_JUSTICE_DB.setSource("J"), HairColorDefnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), HairColorDefnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(colorCode);
		this.getPropertyList().add(hairColorDescr);
		this.getPropertyList().add(removedFlag);
	}

}
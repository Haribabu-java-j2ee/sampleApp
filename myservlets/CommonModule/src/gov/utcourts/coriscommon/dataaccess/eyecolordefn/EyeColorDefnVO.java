package gov.utcourts.coriscommon.dataaccess.eyecolordefn;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class EyeColorDefnVO extends BaseVO { 

	private TypeString colorCode = new TypeString("color_code").setFieldDescriptor(EyeColorDefnBO.COLORCODE.clear()).setAsPrimaryKey();
	private TypeString eyeColorDescr = new TypeString("eye_color_descr").setFieldDescriptor(EyeColorDefnBO.EYECOLORDESCR.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(EyeColorDefnBO.REMOVEDFLAG.clear());

	public EyeColorDefnVO() {
		super(EyeColorDefnBO.TABLE, EyeColorDefnBO.SYSTEM, EyeColorDefnBO.CORIS_DISTRICT_DB.setSource("D"), EyeColorDefnBO.CORIS_JUSTICE_DB.setSource("J"), EyeColorDefnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EyeColorDefnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public EyeColorDefnVO(String source) {
		super(EyeColorDefnBO.TABLE, EyeColorDefnBO.SYSTEM, EyeColorDefnBO.CORIS_DISTRICT_DB.setSource("D"), EyeColorDefnBO.CORIS_JUSTICE_DB.setSource("J"), EyeColorDefnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EyeColorDefnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(colorCode);
		this.getPropertyList().add(eyeColorDescr);
		this.getPropertyList().add(removedFlag);
	}

}
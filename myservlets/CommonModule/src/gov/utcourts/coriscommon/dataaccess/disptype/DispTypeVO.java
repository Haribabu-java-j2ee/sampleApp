package gov.utcourts.coriscommon.dataaccess.disptype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DispTypeVO extends BaseVO { 

	private TypeString dispCode = new TypeString("disp_code").setFieldDescriptor(DispTypeBO.DISPCODE.clear()).addForeignKey("judgment","disp_code",true).addForeignKey("judgment_detl","disp_code",true).addForeignKey("kase","disp_code",true).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(DispTypeBO.DESCR.clear());
	private TypeString category = new TypeString("category").setFieldDescriptor(DispTypeBO.CATEGORY.clear()).setNullable();
	private TypeString validCourt = new TypeString("valid_court").setFieldDescriptor(DispTypeBO.VALIDCOURT.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(DispTypeBO.REMOVEDFLAG.clear());
	private TypeString type = new TypeString("type").setFieldDescriptor(DispTypeBO.TYPE.clear()).setNullable();

	public DispTypeVO() {
		super(DispTypeBO.TABLE, DispTypeBO.SYSTEM, DispTypeBO.CORIS_DISTRICT_DB.setSource("D"), DispTypeBO.CORIS_JUSTICE_DB.setSource("J"), DispTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DispTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DispTypeVO(String source) {
		super(DispTypeBO.TABLE, DispTypeBO.SYSTEM, DispTypeBO.CORIS_DISTRICT_DB.setSource("D"), DispTypeBO.CORIS_JUSTICE_DB.setSource("J"), DispTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DispTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(dispCode);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(category);
		this.getPropertyList().add(validCourt);
		this.getPropertyList().add(removedFlag);
		this.getPropertyList().add(type);
	}

}
package gov.utcourts.coriscommon.dataaccess.attrtype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AttrTypeVO extends BaseVO { 

	private TypeString attrCode = new TypeString("attr_code").setFieldDescriptor(AttrTypeBO.ATTRCODE.clear()).addForeignKey("amend_info_attr","attr_code",false).addForeignKey("chrg_attr","attr_code",false).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(AttrTypeBO.DESCR.clear()).setNullable();
	private TypeString validCourt = new TypeString("valid_court").setFieldDescriptor(AttrTypeBO.VALIDCOURT.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(AttrTypeBO.REMOVEDFLAG.clear());

	public AttrTypeVO() {
		super(AttrTypeBO.TABLE, AttrTypeBO.SYSTEM, AttrTypeBO.CORIS_DISTRICT_DB.setSource("D"), AttrTypeBO.CORIS_JUSTICE_DB.setSource("J"), AttrTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AttrTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AttrTypeVO(String source) {
		super(AttrTypeBO.TABLE, AttrTypeBO.SYSTEM, AttrTypeBO.CORIS_DISTRICT_DB.setSource("D"), AttrTypeBO.CORIS_JUSTICE_DB.setSource("J"), AttrTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AttrTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(attrCode);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(validCourt);
		this.getPropertyList().add(removedFlag);
	}

}
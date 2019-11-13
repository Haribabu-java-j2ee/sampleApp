package gov.utcourts.coriscommon.dataaccess.compdatetype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CompDateTypeVO extends BaseVO { 

	private TypeString compDateCode = new TypeString("comp_date_code").setFieldDescriptor(CompDateTypeBO.COMPDATECODE.clear()).addForeignKey("comp_date","comp_date_code",false).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(CompDateTypeBO.DESCR.clear()).setNullable();
	private TypeString caseTypeCat = new TypeString("case_type_cat").setFieldDescriptor(CompDateTypeBO.CASETYPECAT.clear());
	private TypeString compDateCat = new TypeString("comp_date_cat").setFieldDescriptor(CompDateTypeBO.COMPDATECAT.clear()).setNullable();
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(CompDateTypeBO.REMOVEDFLAG.clear());
	private TypeInteger sortOrder = new TypeInteger("sort_order").setFieldDescriptor(CompDateTypeBO.SORTORDER.clear()).setNullable();

	public CompDateTypeVO() {
		super(CompDateTypeBO.TABLE, CompDateTypeBO.SYSTEM, CompDateTypeBO.CORIS_DISTRICT_DB.setSource("D"), CompDateTypeBO.CORIS_JUSTICE_DB.setSource("J"), CompDateTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CompDateTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CompDateTypeVO(String source) {
		super(CompDateTypeBO.TABLE, CompDateTypeBO.SYSTEM, CompDateTypeBO.CORIS_DISTRICT_DB.setSource("D"), CompDateTypeBO.CORIS_JUSTICE_DB.setSource("J"), CompDateTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CompDateTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(compDateCode);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(caseTypeCat);
		this.getPropertyList().add(compDateCat);
		this.getPropertyList().add(removedFlag);
		this.getPropertyList().add(sortOrder);
	}

}
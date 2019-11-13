package gov.utcourts.coriscommon.dataaccess.reasontype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ReasonTypeVO extends BaseVO { 

	private TypeInteger reasonTypeId = new TypeInteger("reason_type_id").setFieldDescriptor(ReasonTypeBO.REASONTYPEID.clear()).setAsPrimaryKey();
	private TypeString reasonCategory = new TypeString("reason_category").setFieldDescriptor(ReasonTypeBO.REASONCATEGORY.clear());
	private TypeString reasonCode = new TypeString("reason_code").setFieldDescriptor(ReasonTypeBO.REASONCODE.clear());
	private TypeString descr = new TypeString("descr").setFieldDescriptor(ReasonTypeBO.DESCR.clear());

	public ReasonTypeVO() {
		super(ReasonTypeBO.TABLE, ReasonTypeBO.SYSTEM, ReasonTypeBO.CORIS_DISTRICT_DB.setSource("D"), ReasonTypeBO.CORIS_JUSTICE_DB.setSource("J"), ReasonTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ReasonTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public ReasonTypeVO(String source) {
		super(ReasonTypeBO.TABLE, ReasonTypeBO.SYSTEM, ReasonTypeBO.CORIS_DISTRICT_DB.setSource("D"), ReasonTypeBO.CORIS_JUSTICE_DB.setSource("J"), ReasonTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ReasonTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(reasonTypeId);
		this.getPropertyList().add(reasonCategory);
		this.getPropertyList().add(reasonCode);
		this.getPropertyList().add(descr);
	}

}
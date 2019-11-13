package gov.utcourts.coriscommon.dataaccess.courtcodetype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CourtCodeTypeVO extends BaseVO { 

	private TypeString codeId = new TypeString("code_id").setFieldDescriptor(CourtCodeTypeBO.CODEID.clear()).setNullable().setAsPrimaryKey();
	private TypeString codeType = new TypeString("code_type").setFieldDescriptor(CourtCodeTypeBO.CODETYPE.clear());
	private TypeString codeDescr = new TypeString("code_descr").setFieldDescriptor(CourtCodeTypeBO.CODEDESCR.clear()).setNullable();
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(CourtCodeTypeBO.REMOVEDFLAG.clear()).setNullable();

	public CourtCodeTypeVO() {
		super(CourtCodeTypeBO.TABLE, CourtCodeTypeBO.SYSTEM, CourtCodeTypeBO.CORIS_DISTRICT_DB.setSource("D"), CourtCodeTypeBO.CORIS_JUSTICE_DB.setSource("J"), CourtCodeTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CourtCodeTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CourtCodeTypeVO(String source) {
		super(CourtCodeTypeBO.TABLE, CourtCodeTypeBO.SYSTEM, CourtCodeTypeBO.CORIS_DISTRICT_DB.setSource("D"), CourtCodeTypeBO.CORIS_JUSTICE_DB.setSource("J"), CourtCodeTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CourtCodeTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(codeId);
		this.getPropertyList().add(codeType);
		this.getPropertyList().add(codeDescr);
		this.getPropertyList().add(removedFlag);
	}

}
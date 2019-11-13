package gov.utcourts.coriscommon.dataaccess.codedescriptionxref;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CodeDescriptionXrefVO extends BaseVO { 

	private TypeInteger codeDescriptionXref = new TypeInteger("code_description_xref").setFieldDescriptor(CodeDescriptionXrefBO.CODEDESCRIPTIONXREF.clear()).setAsPrimaryKey();
	private TypeString code = new TypeString("code").setFieldDescriptor(CodeDescriptionXrefBO.CODE.clear());
	private TypeString description = new TypeString("description").setFieldDescriptor(CodeDescriptionXrefBO.DESCRIPTION.clear());
	private TypeString tablename = new TypeString("table_name").setFieldDescriptor(CodeDescriptionXrefBO.TABLENAME.clear());

	public CodeDescriptionXrefVO() {
		super(CodeDescriptionXrefBO.TABLE, CodeDescriptionXrefBO.SYSTEM, CodeDescriptionXrefBO.CORIS_DISTRICT_DB.setSource("D"), CodeDescriptionXrefBO.CORIS_JUSTICE_DB.setSource("J"), CodeDescriptionXrefBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CodeDescriptionXrefBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CodeDescriptionXrefVO(String source) {
		super(CodeDescriptionXrefBO.TABLE, CodeDescriptionXrefBO.SYSTEM, CodeDescriptionXrefBO.CORIS_DISTRICT_DB.setSource("D"), CodeDescriptionXrefBO.CORIS_JUSTICE_DB.setSource("J"), CodeDescriptionXrefBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CodeDescriptionXrefBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(codeDescriptionXref);
		this.getPropertyList().add(code);
		this.getPropertyList().add(description);
		this.getPropertyList().add(tablename);
	}

}
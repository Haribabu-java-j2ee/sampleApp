package gov.utcourts.coriscommon.dataaccess.dynamicdocumentscreen;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DynamicDocumentScreenVO extends BaseVO { 

	private TypeInteger ddsId = new TypeInteger("dds_id").setFieldDescriptor(DynamicDocumentScreenBO.DDSID.clear()).setAsPrimaryKey();
	private TypeString docFormCode = new TypeString("doc_form_code").setFieldDescriptor(DynamicDocumentScreenBO.DOCFORMCODE.clear()).setNullable();
	private TypeString objectText = new TypeString("object_text").setFieldDescriptor(DynamicDocumentScreenBO.OBJECTTEXT.clear()).setNullable();
	private TypeString objectStyle = new TypeString("object_style").setFieldDescriptor(DynamicDocumentScreenBO.OBJECTSTYLE.clear()).setNullable();
	private TypeString objectType = new TypeString("object_type").setFieldDescriptor(DynamicDocumentScreenBO.OBJECTTYPE.clear()).setNullable();
	private TypeString objectLength = new TypeString("object_length").setFieldDescriptor(DynamicDocumentScreenBO.OBJECTLENGTH.clear()).setNullable();
	private TypeString objectRequired = new TypeString("object_required").setFieldDescriptor(DynamicDocumentScreenBO.OBJECTREQUIRED.clear()).setNullable();
	private TypeString objectName = new TypeString("object_name").setFieldDescriptor(DynamicDocumentScreenBO.OBJECTNAME.clear()).setNullable();

	public DynamicDocumentScreenVO() {
		super(DynamicDocumentScreenBO.TABLE, DynamicDocumentScreenBO.SYSTEM, DynamicDocumentScreenBO.CORIS_DISTRICT_DB.setSource("D"), DynamicDocumentScreenBO.CORIS_JUSTICE_DB.setSource("J"), DynamicDocumentScreenBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DynamicDocumentScreenBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DynamicDocumentScreenVO(String source) {
		super(DynamicDocumentScreenBO.TABLE, DynamicDocumentScreenBO.SYSTEM, DynamicDocumentScreenBO.CORIS_DISTRICT_DB.setSource("D"), DynamicDocumentScreenBO.CORIS_JUSTICE_DB.setSource("J"), DynamicDocumentScreenBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DynamicDocumentScreenBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(ddsId);
		this.getPropertyList().add(docFormCode);
		this.getPropertyList().add(objectText);
		this.getPropertyList().add(objectStyle);
		this.getPropertyList().add(objectType);
		this.getPropertyList().add(objectLength);
		this.getPropertyList().add(objectRequired);
		this.getPropertyList().add(objectName);
	}

}
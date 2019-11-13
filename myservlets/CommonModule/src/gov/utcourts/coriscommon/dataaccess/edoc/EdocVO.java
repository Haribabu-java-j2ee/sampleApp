package gov.utcourts.coriscommon.dataaccess.edoc;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class EdocVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(EdocBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(EdocBO.SEQ.clear()).setAsPrimaryKey();
	private TypeString edocOrigin = new TypeString("edoc_origin").setFieldDescriptor(EdocBO.EDOCORIGIN.clear());
	private TypeString edocCode = new TypeString("edoc_code").setFieldDescriptor(EdocBO.EDOCCODE.clear());
	private TypeString edocId = new TypeString("edoc_id").setFieldDescriptor(EdocBO.EDOCID.clear()).setNullable();
	private TypeInteger documentNum = new TypeInteger("document_num").setFieldDescriptor(EdocBO.DOCUMENTNUM.clear()).addForeignKey("document","document_num",false).setNullable();

	public EdocVO() {
		super(EdocBO.TABLE, EdocBO.SYSTEM, EdocBO.CORIS_DISTRICT_DB.setSource("D"), EdocBO.CORIS_JUSTICE_DB.setSource("J"), EdocBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EdocBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public EdocVO(String source) {
		super(EdocBO.TABLE, EdocBO.SYSTEM, EdocBO.CORIS_DISTRICT_DB.setSource("D"), EdocBO.CORIS_JUSTICE_DB.setSource("J"), EdocBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EdocBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(edocOrigin);
		this.getPropertyList().add(edocCode);
		this.getPropertyList().add(edocId);
		this.getPropertyList().add(documentNum);
	}

}
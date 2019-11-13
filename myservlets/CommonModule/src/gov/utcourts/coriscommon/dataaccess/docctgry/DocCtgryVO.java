package gov.utcourts.coriscommon.dataaccess.docctgry;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DocCtgryVO extends BaseVO { 

	private TypeString docCatCode = new TypeString("doc_cat_code").setFieldDescriptor(DocCtgryBO.DOCCATCODE.clear()).setAsPrimaryKey();
	private TypeString codeType = new TypeString("code_type").setFieldDescriptor(DocCtgryBO.CODETYPE.clear()).setAsPrimaryKey();
	private TypeString category = new TypeString("category").setFieldDescriptor(DocCtgryBO.CATEGORY.clear()).setNullable();
	private TypeString title = new TypeString("title").setFieldDescriptor(DocCtgryBO.TITLE.clear()).setNullable();

	public DocCtgryVO() {
		super(DocCtgryBO.TABLE, DocCtgryBO.SYSTEM, DocCtgryBO.CORIS_DISTRICT_DB.setSource("D"), DocCtgryBO.CORIS_JUSTICE_DB.setSource("J"), DocCtgryBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocCtgryBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DocCtgryVO(String source) {
		super(DocCtgryBO.TABLE, DocCtgryBO.SYSTEM, DocCtgryBO.CORIS_DISTRICT_DB.setSource("D"), DocCtgryBO.CORIS_JUSTICE_DB.setSource("J"), DocCtgryBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocCtgryBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(docCatCode);
		this.getPropertyList().add(codeType);
		this.getPropertyList().add(category);
		this.getPropertyList().add(title);
	}

}
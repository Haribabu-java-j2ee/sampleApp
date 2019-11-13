package gov.utcourts.coriscommon.dataaccess.incourtclerktext;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class IncourtClerkTextVO extends BaseVO { 

	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(IncourtClerkTextBO.USERIDSRL.clear()).addForeignKey("personnel","userid_srl",false).setAsPrimaryKey();
	private TypeString title = new TypeString("title").setFieldDescriptor(IncourtClerkTextBO.TITLE.clear()).setAsPrimaryKey();
	private TypeString incourtText = new TypeString("incourt_text").setFieldDescriptor(IncourtClerkTextBO.INCOURTTEXT.clear()).setNullable();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(IncourtClerkTextBO.SEQ.clear()).setAsPrimaryKey();

	public IncourtClerkTextVO() {
		super(IncourtClerkTextBO.TABLE, IncourtClerkTextBO.SYSTEM, IncourtClerkTextBO.CORIS_DISTRICT_DB.setSource("D"), IncourtClerkTextBO.CORIS_JUSTICE_DB.setSource("J"), IncourtClerkTextBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), IncourtClerkTextBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public IncourtClerkTextVO(String source) {
		super(IncourtClerkTextBO.TABLE, IncourtClerkTextBO.SYSTEM, IncourtClerkTextBO.CORIS_DISTRICT_DB.setSource("D"), IncourtClerkTextBO.CORIS_JUSTICE_DB.setSource("J"), IncourtClerkTextBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), IncourtClerkTextBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(title);
		this.getPropertyList().add(incourtText);
		this.getPropertyList().add(seq);
	}

}
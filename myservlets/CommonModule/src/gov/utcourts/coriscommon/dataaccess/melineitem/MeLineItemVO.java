package gov.utcourts.coriscommon.dataaccess.melineitem;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class MeLineItemVO extends BaseVO { 

	private TypeString meTypeCode = new TypeString("me_type_code").setFieldDescriptor(MeLineItemBO.METYPECODE.clear()).addForeignKey("me_type","me_type_code",false).setAsPrimaryKey();
	private TypeString meLineItem = new TypeString("me_line_item").setFieldDescriptor(MeLineItemBO.MELINEITEM.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger meSeq = new TypeInteger("me_seq").setFieldDescriptor(MeLineItemBO.MESEQ.clear()).setNullable().setAsPrimaryKey();
	private TypeString always = new TypeString("always").setFieldDescriptor(MeLineItemBO.ALWAYS.clear()).setNullable();
	private TypeString justify = new TypeString("justify").setFieldDescriptor(MeLineItemBO.JUSTIFY.clear()).setNullable();
	private TypeString dblspace = new TypeString("dblspace").setFieldDescriptor(MeLineItemBO.DBLSPACE.clear()).setNullable();
	private TypeString underscore = new TypeString("underscore").setFieldDescriptor(MeLineItemBO.UNDERSCORE.clear()).setNullable();
	private TypeString newpage = new TypeString("newpage").setFieldDescriptor(MeLineItemBO.NEWPAGE.clear()).setNullable();
	private TypeString groupHeader = new TypeString("group_header").setFieldDescriptor(MeLineItemBO.GROUPHEADER.clear()).setNullable();

	public MeLineItemVO() {
		super(MeLineItemBO.TABLE, MeLineItemBO.SYSTEM, MeLineItemBO.CORIS_DISTRICT_DB.setSource("D"), MeLineItemBO.CORIS_JUSTICE_DB.setSource("J"), MeLineItemBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), MeLineItemBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public MeLineItemVO(String source) {
		super(MeLineItemBO.TABLE, MeLineItemBO.SYSTEM, MeLineItemBO.CORIS_DISTRICT_DB.setSource("D"), MeLineItemBO.CORIS_JUSTICE_DB.setSource("J"), MeLineItemBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), MeLineItemBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(meTypeCode);
		this.getPropertyList().add(meLineItem);
		this.getPropertyList().add(meSeq);
		this.getPropertyList().add(always);
		this.getPropertyList().add(justify);
		this.getPropertyList().add(dblspace);
		this.getPropertyList().add(underscore);
		this.getPropertyList().add(newpage);
		this.getPropertyList().add(groupHeader);
	}

}
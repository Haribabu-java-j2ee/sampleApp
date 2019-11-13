package gov.utcourts.coriscommon.dataaccess.link;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class LinkVO extends BaseVO { 

	private TypeInteger linkedId = new TypeInteger("linked_id").setFieldDescriptor(LinkBO.LINKEDID.clear()).addForeignKey("kase","linked_case_id",true).addForeignKey("kase","linked_def_id",true).setAsPrimaryKey();
	private TypeString linkedType = new TypeString("linked_type").setFieldDescriptor(LinkBO.LINKEDTYPE.clear());
	private TypeString linkedReason = new TypeString("linked_reason").setFieldDescriptor(LinkBO.LINKEDREASON.clear());

	public LinkVO() {
		super(LinkBO.TABLE, LinkBO.SYSTEM, LinkBO.CORIS_DISTRICT_DB.setSource("D"), LinkBO.CORIS_JUSTICE_DB.setSource("J"), LinkBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), LinkBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public LinkVO(String source) {
		super(LinkBO.TABLE, LinkBO.SYSTEM, LinkBO.CORIS_DISTRICT_DB.setSource("D"), LinkBO.CORIS_JUSTICE_DB.setSource("J"), LinkBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), LinkBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(linkedId);
		this.getPropertyList().add(linkedType);
		this.getPropertyList().add(linkedReason);
	}

}
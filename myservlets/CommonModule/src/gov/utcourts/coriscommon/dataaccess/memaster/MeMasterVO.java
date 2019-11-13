package gov.utcourts.coriscommon.dataaccess.memaster;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class MeMasterVO extends BaseVO { 

	private TypeString meLineItem = new TypeString("me_line_item").setFieldDescriptor(MeMasterBO.MELINEITEM.clear()).setAsPrimaryKey();
	private TypeInteger meSegment = new TypeInteger("me_segment").setFieldDescriptor(MeMasterBO.MESEGMENT.clear()).setAsPrimaryKey();
	private TypeString meText = new TypeString("me_text").setFieldDescriptor(MeMasterBO.METEXT.clear()).setNullable();
	private TypeString meScreenId = new TypeString("me_screen_id").setFieldDescriptor(MeMasterBO.MESCREENID.clear()).setNullable();
	private TypeString meFieldId = new TypeString("me_field_id").setFieldDescriptor(MeMasterBO.MEFIELDID.clear()).setNullable();
	private TypeString align = new TypeString("align").setFieldDescriptor(MeMasterBO.ALIGN.clear()).setNullable();
	private TypeString newline = new TypeString("newline").setFieldDescriptor(MeMasterBO.NEWLINE.clear()).setNullable();
	private TypeString always = new TypeString("always").setFieldDescriptor(MeMasterBO.ALWAYS.clear()).setNullable();
	private TypeString multivalueText = new TypeString("multivalue_text").setFieldDescriptor(MeMasterBO.MULTIVALUETEXT.clear()).setNullable();
	private TypeString textOnly = new TypeString("text_only").setFieldDescriptor(MeMasterBO.TEXTONLY.clear()).setNullable();

	public MeMasterVO() {
		super(MeMasterBO.TABLE, MeMasterBO.SYSTEM, MeMasterBO.CORIS_DISTRICT_DB.setSource("D"), MeMasterBO.CORIS_JUSTICE_DB.setSource("J"), MeMasterBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), MeMasterBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public MeMasterVO(String source) {
		super(MeMasterBO.TABLE, MeMasterBO.SYSTEM, MeMasterBO.CORIS_DISTRICT_DB.setSource("D"), MeMasterBO.CORIS_JUSTICE_DB.setSource("J"), MeMasterBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), MeMasterBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(meLineItem);
		this.getPropertyList().add(meSegment);
		this.getPropertyList().add(meText);
		this.getPropertyList().add(meScreenId);
		this.getPropertyList().add(meFieldId);
		this.getPropertyList().add(align);
		this.getPropertyList().add(newline);
		this.getPropertyList().add(always);
		this.getPropertyList().add(multivalueText);
		this.getPropertyList().add(textOnly);
	}

}
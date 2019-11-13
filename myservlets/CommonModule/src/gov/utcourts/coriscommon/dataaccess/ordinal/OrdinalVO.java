package gov.utcourts.coriscommon.dataaccess.ordinal;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class OrdinalVO extends BaseVO { 

	private TypeInteger ordinalId = new TypeInteger("ordinal_id").setFieldDescriptor(OrdinalBO.ORDINALID.clear()).addForeignKey("dui_info","charged_as",true).addForeignKey("dui_info","actually_was",true).addForeignKey("dui_info","sentenced_as",true).setAsPrimaryKey();
	private TypeString ordinalDescr = new TypeString("ordinal_descr").setFieldDescriptor(OrdinalBO.ORDINALDESCR.clear());

	public OrdinalVO() {
		super(OrdinalBO.TABLE, OrdinalBO.SYSTEM, OrdinalBO.CORIS_DISTRICT_DB.setSource("D"), OrdinalBO.CORIS_JUSTICE_DB.setSource("J"), OrdinalBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OrdinalBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public OrdinalVO(String source) {
		super(OrdinalBO.TABLE, OrdinalBO.SYSTEM, OrdinalBO.CORIS_DISTRICT_DB.setSource("D"), OrdinalBO.CORIS_JUSTICE_DB.setSource("J"), OrdinalBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OrdinalBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(ordinalId);
		this.getPropertyList().add(ordinalDescr);
	}

}
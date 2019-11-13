package gov.utcourts.coriscommon.dataaccess.pagerolexref;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PageroleXrefVO extends BaseVO { 

	private TypeInteger pagerolexrefid = new TypeInteger("pagerolexrefid").setFieldDescriptor(PageroleXrefBO.PAGEROLEXREFID.clear()).setAsPrimaryKey();
	private TypeInteger accesslevelid = new TypeInteger("accesslevelid").setFieldDescriptor(PageroleXrefBO.ACCESSLEVELID.clear()).addForeignKey("accesslevel_defn","accesslevelid",false);
	private TypeInteger pageid = new TypeInteger("pageid").setFieldDescriptor(PageroleXrefBO.PAGEID.clear()).addForeignKey("page_defn","pageid",false);
	private TypeInteger roleId = new TypeInteger("role_id").setFieldDescriptor(PageroleXrefBO.ROLEID.clear()).addForeignKey("role_defn","role_id",false);

	public PageroleXrefVO() {
		super(PageroleXrefBO.TABLE, PageroleXrefBO.SYSTEM, PageroleXrefBO.CORIS_DISTRICT_DB.setSource("D"), PageroleXrefBO.CORIS_JUSTICE_DB.setSource("J"), PageroleXrefBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PageroleXrefBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PageroleXrefVO(String source) {
		super(PageroleXrefBO.TABLE, PageroleXrefBO.SYSTEM, PageroleXrefBO.CORIS_DISTRICT_DB.setSource("D"), PageroleXrefBO.CORIS_JUSTICE_DB.setSource("J"), PageroleXrefBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PageroleXrefBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(pagerolexrefid);
		this.getPropertyList().add(accesslevelid);
		this.getPropertyList().add(pageid);
		this.getPropertyList().add(roleId);
	}

}
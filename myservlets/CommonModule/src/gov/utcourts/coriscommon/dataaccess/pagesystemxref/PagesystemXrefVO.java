package gov.utcourts.coriscommon.dataaccess.pagesystemxref;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PagesystemXrefVO extends BaseVO { 

	private TypeInteger pagesystemxrefid = new TypeInteger("pagesystemxrefid").setFieldDescriptor(PagesystemXrefBO.PAGESYSTEMXREFID.clear()).setAsPrimaryKey();
	private TypeInteger areaid = new TypeInteger("areaid").setFieldDescriptor(PagesystemXrefBO.AREAID.clear()).addForeignKey("systemarea_defn","areaid",false);
	private TypeBigDecimal displayorder = new TypeBigDecimal("displayorder").setFieldDescriptor(PagesystemXrefBO.DISPLAYORDER.clear());
	private TypeInteger pageid = new TypeInteger("pageid").setFieldDescriptor(PagesystemXrefBO.PAGEID.clear()).addForeignKey("page_defn","pageid",false);
	private TypeInteger parentpagesystemxrefid = new TypeInteger("parentpagesystemxrefid").setFieldDescriptor(PagesystemXrefBO.PARENTPAGESYSTEMXREFID.clear()).setNullable();

	public PagesystemXrefVO() {
		super(PagesystemXrefBO.TABLE, PagesystemXrefBO.SYSTEM, PagesystemXrefBO.CORIS_DISTRICT_DB.setSource("D"), PagesystemXrefBO.CORIS_JUSTICE_DB.setSource("J"), PagesystemXrefBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PagesystemXrefBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PagesystemXrefVO(String source) {
		super(PagesystemXrefBO.TABLE, PagesystemXrefBO.SYSTEM, PagesystemXrefBO.CORIS_DISTRICT_DB.setSource("D"), PagesystemXrefBO.CORIS_JUSTICE_DB.setSource("J"), PagesystemXrefBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PagesystemXrefBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(pagesystemxrefid);
		this.getPropertyList().add(areaid);
		this.getPropertyList().add(displayorder);
		this.getPropertyList().add(pageid);
		this.getPropertyList().add(parentpagesystemxrefid);
	}

}
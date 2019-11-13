package gov.utcourts.coriscommon.dataaccess.pagedefn;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PageDefnVO extends BaseVO { 

	private TypeInteger pageid = new TypeInteger("pageid").setFieldDescriptor(PageDefnBO.PAGEID.clear()).addForeignKey("page_whitelist","pageid",false).addForeignKey("pagerole_xref","pageid",false).addForeignKey("pagesystem_xref","pageid",false).setAsPrimaryKey();
	private TypeString description = new TypeString("description").setFieldDescriptor(PageDefnBO.DESCRIPTION.clear());
	private TypeString pageurl = new TypeString("pageurl").setFieldDescriptor(PageDefnBO.PAGEURL.clear());
	private TypeString isavailable = new TypeString("isavailable").setFieldDescriptor(PageDefnBO.ISAVAILABLE.clear());
	private TypeDate versiondate = new TypeDate("versiondate").setFieldDescriptor(PageDefnBO.VERSIONDATE.clear());
	private TypeString pagetype = new TypeString("pagetype").setFieldDescriptor(PageDefnBO.PAGETYPE.clear()).setNullable();

	public PageDefnVO() {
		super(PageDefnBO.TABLE, PageDefnBO.SYSTEM, PageDefnBO.CORIS_DISTRICT_DB.setSource("D"), PageDefnBO.CORIS_JUSTICE_DB.setSource("J"), PageDefnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PageDefnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PageDefnVO(String source) {
		super(PageDefnBO.TABLE, PageDefnBO.SYSTEM, PageDefnBO.CORIS_DISTRICT_DB.setSource("D"), PageDefnBO.CORIS_JUSTICE_DB.setSource("J"), PageDefnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PageDefnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(pageid);
		this.getPropertyList().add(description);
		this.getPropertyList().add(pageurl);
		this.getPropertyList().add(isavailable);
		this.getPropertyList().add(versiondate);
		this.getPropertyList().add(pagetype);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(PageDefnBO.VERSIONDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(PageDefnBO.VERSIONDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
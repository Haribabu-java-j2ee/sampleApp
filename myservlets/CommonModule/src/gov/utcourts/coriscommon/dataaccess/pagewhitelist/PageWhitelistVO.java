package gov.utcourts.coriscommon.dataaccess.pagewhitelist;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PageWhitelistVO extends BaseVO { 

	private TypeInteger whitelistid = new TypeInteger("whitelistid").setFieldDescriptor(PageWhitelistBO.WHITELISTID.clear()).setAsPrimaryKey();
	private TypeInteger pageid = new TypeInteger("pageid").setFieldDescriptor(PageWhitelistBO.PAGEID.clear()).addForeignKey("page_defn","pageid",false);
	private TypeString logname = new TypeString("logname").setFieldDescriptor(PageWhitelistBO.LOGNAME.clear());

	public PageWhitelistVO() {
		super(PageWhitelistBO.TABLE, PageWhitelistBO.SYSTEM, PageWhitelistBO.CORIS_DISTRICT_DB.setSource("D"), PageWhitelistBO.CORIS_JUSTICE_DB.setSource("J"), PageWhitelistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PageWhitelistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PageWhitelistVO(String source) {
		super(PageWhitelistBO.TABLE, PageWhitelistBO.SYSTEM, PageWhitelistBO.CORIS_DISTRICT_DB.setSource("D"), PageWhitelistBO.CORIS_JUSTICE_DB.setSource("J"), PageWhitelistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PageWhitelistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(whitelistid);
		this.getPropertyList().add(pageid);
		this.getPropertyList().add(logname);
	}

}
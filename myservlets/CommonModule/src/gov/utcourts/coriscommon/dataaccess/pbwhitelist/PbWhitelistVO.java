package gov.utcourts.coriscommon.dataaccess.pbwhitelist;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PbWhitelistVO extends BaseVO { 

	private TypeInteger whitelistid = new TypeInteger("whitelistid").setFieldDescriptor(PbWhitelistBO.WHITELISTID.clear()).setAsPrimaryKey();
	private TypeString area = new TypeString("area").setFieldDescriptor(PbWhitelistBO.AREA.clear());
	private TypeString logname = new TypeString("logname").setFieldDescriptor(PbWhitelistBO.LOGNAME.clear());

	public PbWhitelistVO() {
		super(PbWhitelistBO.TABLE, PbWhitelistBO.SYSTEM, PbWhitelistBO.CORIS_DISTRICT_DB.setSource("D"), PbWhitelistBO.CORIS_JUSTICE_DB.setSource("J"), PbWhitelistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PbWhitelistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PbWhitelistVO(String source) {
		super(PbWhitelistBO.TABLE, PbWhitelistBO.SYSTEM, PbWhitelistBO.CORIS_DISTRICT_DB.setSource("D"), PbWhitelistBO.CORIS_JUSTICE_DB.setSource("J"), PbWhitelistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PbWhitelistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(whitelistid);
		this.getPropertyList().add(area);
		this.getPropertyList().add(logname);
	}

}
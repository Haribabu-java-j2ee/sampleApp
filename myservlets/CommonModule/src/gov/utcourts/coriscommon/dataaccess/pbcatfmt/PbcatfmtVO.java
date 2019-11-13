package gov.utcourts.coriscommon.dataaccess.pbcatfmt;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PbcatfmtVO extends BaseVO { 

	private TypeString pbfName = new TypeString("pbf_name").setFieldDescriptor(PbcatfmtBO.PBFNAME.clear());
	private TypeString pbfFrmt = new TypeString("pbf_frmt").setFieldDescriptor(PbcatfmtBO.PBFFRMT.clear());
	private TypeInteger pbfType = new TypeInteger("pbf_type").setFieldDescriptor(PbcatfmtBO.PBFTYPE.clear());
	private TypeInteger pbfCntr = new TypeInteger("pbf_cntr").setFieldDescriptor(PbcatfmtBO.PBFCNTR.clear());

	public PbcatfmtVO() {
		super(PbcatfmtBO.TABLE, PbcatfmtBO.SYSTEM, PbcatfmtBO.CORIS_DISTRICT_DB.setSource("D"), PbcatfmtBO.CORIS_JUSTICE_DB.setSource("J"), PbcatfmtBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PbcatfmtBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PbcatfmtVO(String source) {
		super(PbcatfmtBO.TABLE, PbcatfmtBO.SYSTEM, PbcatfmtBO.CORIS_DISTRICT_DB.setSource("D"), PbcatfmtBO.CORIS_JUSTICE_DB.setSource("J"), PbcatfmtBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PbcatfmtBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(pbfName);
		this.getPropertyList().add(pbfFrmt);
		this.getPropertyList().add(pbfType);
		this.getPropertyList().add(pbfCntr);
	}

}
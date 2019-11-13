package gov.utcourts.coriscommon.dataaccess.pbcatvld;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PbcatvldVO extends BaseVO { 

	private TypeString pbvName = new TypeString("pbv_name").setFieldDescriptor(PbcatvldBO.PBVNAME.clear());
	private TypeString pbvVald = new TypeString("pbv_vald").setFieldDescriptor(PbcatvldBO.PBVVALD.clear());
	private TypeInteger pbvType = new TypeInteger("pbv_type").setFieldDescriptor(PbcatvldBO.PBVTYPE.clear());
	private TypeInteger pbvCntr = new TypeInteger("pbv_cntr").setFieldDescriptor(PbcatvldBO.PBVCNTR.clear());
	private TypeString pbvMsg = new TypeString("pbv_msg").setFieldDescriptor(PbcatvldBO.PBVMSG.clear()).setNullable();

	public PbcatvldVO() {
		super(PbcatvldBO.TABLE, PbcatvldBO.SYSTEM, PbcatvldBO.CORIS_DISTRICT_DB.setSource("D"), PbcatvldBO.CORIS_JUSTICE_DB.setSource("J"), PbcatvldBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PbcatvldBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PbcatvldVO(String source) {
		super(PbcatvldBO.TABLE, PbcatvldBO.SYSTEM, PbcatvldBO.CORIS_DISTRICT_DB.setSource("D"), PbcatvldBO.CORIS_JUSTICE_DB.setSource("J"), PbcatvldBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PbcatvldBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(pbvName);
		this.getPropertyList().add(pbvVald);
		this.getPropertyList().add(pbvType);
		this.getPropertyList().add(pbvCntr);
		this.getPropertyList().add(pbvMsg);
	}

}
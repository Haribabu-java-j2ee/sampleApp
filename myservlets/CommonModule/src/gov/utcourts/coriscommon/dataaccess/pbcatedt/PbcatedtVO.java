package gov.utcourts.coriscommon.dataaccess.pbcatedt;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PbcatedtVO extends BaseVO { 

	private TypeString pbeName = new TypeString("pbe_name").setFieldDescriptor(PbcatedtBO.PBENAME.clear());
	private TypeString pbeEdit = new TypeString("pbe_edit").setFieldDescriptor(PbcatedtBO.PBEEDIT.clear()).setNullable();
	private TypeInteger pbeType = new TypeInteger("pbe_type").setFieldDescriptor(PbcatedtBO.PBETYPE.clear()).setNullable();
	private TypeInteger pbeCntr = new TypeInteger("pbe_cntr").setFieldDescriptor(PbcatedtBO.PBECNTR.clear()).setNullable();
	private TypeInteger pbeSeqn = new TypeInteger("pbe_seqn").setFieldDescriptor(PbcatedtBO.PBESEQN.clear()).setNullable();
	private TypeInteger pbeFlag = new TypeInteger("pbe_flag").setFieldDescriptor(PbcatedtBO.PBEFLAG.clear()).setNullable();
	private TypeString pbeWork = new TypeString("pbe_work").setFieldDescriptor(PbcatedtBO.PBEWORK.clear()).setNullable();

	public PbcatedtVO() {
		super(PbcatedtBO.TABLE, PbcatedtBO.SYSTEM, PbcatedtBO.CORIS_DISTRICT_DB.setSource("D"), PbcatedtBO.CORIS_JUSTICE_DB.setSource("J"), PbcatedtBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PbcatedtBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PbcatedtVO(String source) {
		super(PbcatedtBO.TABLE, PbcatedtBO.SYSTEM, PbcatedtBO.CORIS_DISTRICT_DB.setSource("D"), PbcatedtBO.CORIS_JUSTICE_DB.setSource("J"), PbcatedtBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PbcatedtBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(pbeName);
		this.getPropertyList().add(pbeEdit);
		this.getPropertyList().add(pbeType);
		this.getPropertyList().add(pbeCntr);
		this.getPropertyList().add(pbeSeqn);
		this.getPropertyList().add(pbeFlag);
		this.getPropertyList().add(pbeWork);
	}

}
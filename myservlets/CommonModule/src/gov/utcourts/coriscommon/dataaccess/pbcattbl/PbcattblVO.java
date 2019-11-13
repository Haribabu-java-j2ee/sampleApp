package gov.utcourts.coriscommon.dataaccess.pbcattbl;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PbcattblVO extends BaseVO { 

	private TypeString pbtTnam = new TypeString("pbt_tnam").setFieldDescriptor(PbcattblBO.PBTTNAM.clear());
	private TypeInteger pbtTid = new TypeInteger("pbt_tid").setFieldDescriptor(PbcattblBO.PBTTID.clear()).setNullable();
	private TypeString pbtOwnr = new TypeString("pbt_ownr").setFieldDescriptor(PbcattblBO.PBTOWNR.clear());
	private TypeInteger pbdFhgt = new TypeInteger("pbd_fhgt").setFieldDescriptor(PbcattblBO.PBDFHGT.clear()).setNullable();
	private TypeInteger pbdFwgt = new TypeInteger("pbd_fwgt").setFieldDescriptor(PbcattblBO.PBDFWGT.clear()).setNullable();
	private TypeString pbdFitl = new TypeString("pbd_fitl").setFieldDescriptor(PbcattblBO.PBDFITL.clear()).setNullable();
	private TypeString pbdFunl = new TypeString("pbd_funl").setFieldDescriptor(PbcattblBO.PBDFUNL.clear()).setNullable();
	private TypeInteger pbdFchr = new TypeInteger("pbd_fchr").setFieldDescriptor(PbcattblBO.PBDFCHR.clear()).setNullable();
	private TypeInteger pbdFptc = new TypeInteger("pbd_fptc").setFieldDescriptor(PbcattblBO.PBDFPTC.clear()).setNullable();
	private TypeString pbdFfce = new TypeString("pbd_ffce").setFieldDescriptor(PbcattblBO.PBDFFCE.clear()).setNullable();
	private TypeInteger pbhFhgt = new TypeInteger("pbh_fhgt").setFieldDescriptor(PbcattblBO.PBHFHGT.clear()).setNullable();
	private TypeInteger pbhFwgt = new TypeInteger("pbh_fwgt").setFieldDescriptor(PbcattblBO.PBHFWGT.clear()).setNullable();
	private TypeString pbhFitl = new TypeString("pbh_fitl").setFieldDescriptor(PbcattblBO.PBHFITL.clear()).setNullable();
	private TypeString pbhFunl = new TypeString("pbh_funl").setFieldDescriptor(PbcattblBO.PBHFUNL.clear()).setNullable();
	private TypeInteger pbhFchr = new TypeInteger("pbh_fchr").setFieldDescriptor(PbcattblBO.PBHFCHR.clear()).setNullable();
	private TypeInteger pbhFptc = new TypeInteger("pbh_fptc").setFieldDescriptor(PbcattblBO.PBHFPTC.clear()).setNullable();
	private TypeString pbhFfce = new TypeString("pbh_ffce").setFieldDescriptor(PbcattblBO.PBHFFCE.clear()).setNullable();
	private TypeInteger pblFhgt = new TypeInteger("pbl_fhgt").setFieldDescriptor(PbcattblBO.PBLFHGT.clear()).setNullable();
	private TypeInteger pblFwgt = new TypeInteger("pbl_fwgt").setFieldDescriptor(PbcattblBO.PBLFWGT.clear()).setNullable();
	private TypeString pblFitl = new TypeString("pbl_fitl").setFieldDescriptor(PbcattblBO.PBLFITL.clear()).setNullable();
	private TypeString pblFunl = new TypeString("pbl_funl").setFieldDescriptor(PbcattblBO.PBLFUNL.clear()).setNullable();
	private TypeInteger pblFchr = new TypeInteger("pbl_fchr").setFieldDescriptor(PbcattblBO.PBLFCHR.clear()).setNullable();
	private TypeInteger pblFptc = new TypeInteger("pbl_fptc").setFieldDescriptor(PbcattblBO.PBLFPTC.clear()).setNullable();
	private TypeString pblFfce = new TypeString("pbl_ffce").setFieldDescriptor(PbcattblBO.PBLFFCE.clear()).setNullable();
	private TypeString pbtCmnt = new TypeString("pbt_cmnt").setFieldDescriptor(PbcattblBO.PBTCMNT.clear()).setNullable();

	public PbcattblVO() {
		super(PbcattblBO.TABLE, PbcattblBO.SYSTEM, PbcattblBO.CORIS_DISTRICT_DB.setSource("D"), PbcattblBO.CORIS_JUSTICE_DB.setSource("J"), PbcattblBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PbcattblBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PbcattblVO(String source) {
		super(PbcattblBO.TABLE, PbcattblBO.SYSTEM, PbcattblBO.CORIS_DISTRICT_DB.setSource("D"), PbcattblBO.CORIS_JUSTICE_DB.setSource("J"), PbcattblBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PbcattblBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(pbtTnam);
		this.getPropertyList().add(pbtTid);
		this.getPropertyList().add(pbtOwnr);
		this.getPropertyList().add(pbdFhgt);
		this.getPropertyList().add(pbdFwgt);
		this.getPropertyList().add(pbdFitl);
		this.getPropertyList().add(pbdFunl);
		this.getPropertyList().add(pbdFchr);
		this.getPropertyList().add(pbdFptc);
		this.getPropertyList().add(pbdFfce);
		this.getPropertyList().add(pbhFhgt);
		this.getPropertyList().add(pbhFwgt);
		this.getPropertyList().add(pbhFitl);
		this.getPropertyList().add(pbhFunl);
		this.getPropertyList().add(pbhFchr);
		this.getPropertyList().add(pbhFptc);
		this.getPropertyList().add(pbhFfce);
		this.getPropertyList().add(pblFhgt);
		this.getPropertyList().add(pblFwgt);
		this.getPropertyList().add(pblFitl);
		this.getPropertyList().add(pblFunl);
		this.getPropertyList().add(pblFchr);
		this.getPropertyList().add(pblFptc);
		this.getPropertyList().add(pblFfce);
		this.getPropertyList().add(pbtCmnt);
	}

}
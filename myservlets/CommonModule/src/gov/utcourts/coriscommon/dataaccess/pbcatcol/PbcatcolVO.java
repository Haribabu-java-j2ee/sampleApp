package gov.utcourts.coriscommon.dataaccess.pbcatcol;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PbcatcolVO extends BaseVO { 

	private TypeString pbcTnam = new TypeString("pbc_tnam").setFieldDescriptor(PbcatcolBO.PBCTNAM.clear());
	private TypeInteger pbcTid = new TypeInteger("pbc_tid").setFieldDescriptor(PbcatcolBO.PBCTID.clear()).setNullable();
	private TypeString pbcOwnr = new TypeString("pbc_ownr").setFieldDescriptor(PbcatcolBO.PBCOWNR.clear());
	private TypeString pbcCnam = new TypeString("pbc_cnam").setFieldDescriptor(PbcatcolBO.PBCCNAM.clear());
	private TypeString pbcLabl = new TypeString("pbc_labl").setFieldDescriptor(PbcatcolBO.PBCLABL.clear()).setNullable();
	private TypeInteger pbcLpos = new TypeInteger("pbc_lpos").setFieldDescriptor(PbcatcolBO.PBCLPOS.clear()).setNullable();
	private TypeString pbcHdr = new TypeString("pbc_hdr").setFieldDescriptor(PbcatcolBO.PBCHDR.clear()).setNullable();
	private TypeInteger pbcHpos = new TypeInteger("pbc_hpos").setFieldDescriptor(PbcatcolBO.PBCHPOS.clear()).setNullable();
	private TypeInteger pbcJtfy = new TypeInteger("pbc_jtfy").setFieldDescriptor(PbcatcolBO.PBCJTFY.clear()).setNullable();
	private TypeString pbcMask = new TypeString("pbc_mask").setFieldDescriptor(PbcatcolBO.PBCMASK.clear()).setNullable();
	private TypeInteger pbcCase = new TypeInteger("pbc_case").setFieldDescriptor(PbcatcolBO.PBCCASE.clear()).setNullable();
	private TypeInteger pbcHght = new TypeInteger("pbc_hght").setFieldDescriptor(PbcatcolBO.PBCHGHT.clear()).setNullable();
	private TypeInteger pbcWdth = new TypeInteger("pbc_wdth").setFieldDescriptor(PbcatcolBO.PBCWDTH.clear()).setNullable();
	private TypeString pbcPtrn = new TypeString("pbc_ptrn").setFieldDescriptor(PbcatcolBO.PBCPTRN.clear()).setNullable();
	private TypeString pbcBmap = new TypeString("pbc_bmap").setFieldDescriptor(PbcatcolBO.PBCBMAP.clear()).setNullable();
	private TypeString pbcInit = new TypeString("pbc_init").setFieldDescriptor(PbcatcolBO.PBCINIT.clear()).setNullable();
	private TypeString pbcEdit = new TypeString("pbc_edit").setFieldDescriptor(PbcatcolBO.PBCEDIT.clear()).setNullable();
	private TypeString pbcCmnt = new TypeString("pbc_cmnt").setFieldDescriptor(PbcatcolBO.PBCCMNT.clear()).setNullable();
	private TypeString pbcTag = new TypeString("pbc_tag").setFieldDescriptor(PbcatcolBO.PBCTAG.clear()).setNullable();

	public PbcatcolVO() {
		super(PbcatcolBO.TABLE, PbcatcolBO.SYSTEM, PbcatcolBO.CORIS_DISTRICT_DB.setSource("D"), PbcatcolBO.CORIS_JUSTICE_DB.setSource("J"), PbcatcolBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PbcatcolBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PbcatcolVO(String source) {
		super(PbcatcolBO.TABLE, PbcatcolBO.SYSTEM, PbcatcolBO.CORIS_DISTRICT_DB.setSource("D"), PbcatcolBO.CORIS_JUSTICE_DB.setSource("J"), PbcatcolBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PbcatcolBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(pbcTnam);
		this.getPropertyList().add(pbcTid);
		this.getPropertyList().add(pbcOwnr);
		this.getPropertyList().add(pbcCnam);
		this.getPropertyList().add(pbcLabl);
		this.getPropertyList().add(pbcLpos);
		this.getPropertyList().add(pbcHdr);
		this.getPropertyList().add(pbcHpos);
		this.getPropertyList().add(pbcJtfy);
		this.getPropertyList().add(pbcMask);
		this.getPropertyList().add(pbcCase);
		this.getPropertyList().add(pbcHght);
		this.getPropertyList().add(pbcWdth);
		this.getPropertyList().add(pbcPtrn);
		this.getPropertyList().add(pbcBmap);
		this.getPropertyList().add(pbcInit);
		this.getPropertyList().add(pbcEdit);
		this.getPropertyList().add(pbcCmnt);
		this.getPropertyList().add(pbcTag);
	}

}
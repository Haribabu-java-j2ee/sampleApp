package gov.utcourts.coriscommon.dataaccess.form;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class FormVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(FormBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeInteger clerkId = new TypeInteger("clerk_id").setFieldDescriptor(FormBO.CLERKID.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeString lblPrinted = new TypeString("lbl_printed").setFieldDescriptor(FormBO.LBLPRINTED.clear()).setNullable();
	private TypeString convPrinted = new TypeString("conv_printed").setFieldDescriptor(FormBO.CONVPRINTED.clear()).setNullable();
	private TypeString cbnPrinted = new TypeString("cbn_printed").setFieldDescriptor(FormBO.CBNPRINTED.clear()).setNullable();

	public FormVO() {
		super(FormBO.TABLE, FormBO.SYSTEM, FormBO.CORIS_DISTRICT_DB.setSource("D"), FormBO.CORIS_JUSTICE_DB.setSource("J"), FormBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), FormBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public FormVO(String source) {
		super(FormBO.TABLE, FormBO.SYSTEM, FormBO.CORIS_DISTRICT_DB.setSource("D"), FormBO.CORIS_JUSTICE_DB.setSource("J"), FormBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), FormBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(clerkId);
		this.getPropertyList().add(lblPrinted);
		this.getPropertyList().add(convPrinted);
		this.getPropertyList().add(cbnPrinted);
	}

}
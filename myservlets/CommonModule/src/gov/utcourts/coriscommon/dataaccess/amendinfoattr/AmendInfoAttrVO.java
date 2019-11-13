package gov.utcourts.coriscommon.dataaccess.amendinfoattr;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AmendInfoAttrVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(AmendInfoAttrBO.INTCASENUM.clear()).addForeignKey("amend_info","int_case_num",false).setAsPrimaryKey();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(AmendInfoAttrBO.PARTYNUM.clear()).addForeignKey("amend_info","party_num",false).setAsPrimaryKey();
	private TypeString partyCode = new TypeString("party_code").setFieldDescriptor(AmendInfoAttrBO.PARTYCODE.clear()).addForeignKey("amend_info","party_code",false).setAsPrimaryKey();
	private TypeInteger infoNum = new TypeInteger("info_num").setFieldDescriptor(AmendInfoAttrBO.INFONUM.clear()).addForeignKey("amend_info","info_num",false).setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(AmendInfoAttrBO.SEQ.clear()).addForeignKey("amend_info","seq",false).setAsPrimaryKey();
	private TypeString attrType = new TypeString("attr_type").setFieldDescriptor(AmendInfoAttrBO.ATTRTYPE.clear()).setAsPrimaryKey();
	private TypeString attrCode = new TypeString("attr_code").setFieldDescriptor(AmendInfoAttrBO.ATTRCODE.clear()).addForeignKey("attr_type","attr_code",false).setAsPrimaryKey();

	public AmendInfoAttrVO() {
		super(AmendInfoAttrBO.TABLE, AmendInfoAttrBO.SYSTEM, AmendInfoAttrBO.CORIS_DISTRICT_DB.setSource("D"), AmendInfoAttrBO.CORIS_JUSTICE_DB.setSource("J"), AmendInfoAttrBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AmendInfoAttrBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AmendInfoAttrVO(String source) {
		super(AmendInfoAttrBO.TABLE, AmendInfoAttrBO.SYSTEM, AmendInfoAttrBO.CORIS_DISTRICT_DB.setSource("D"), AmendInfoAttrBO.CORIS_JUSTICE_DB.setSource("J"), AmendInfoAttrBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AmendInfoAttrBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(partyNum);
		this.getPropertyList().add(partyCode);
		this.getPropertyList().add(infoNum);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(attrType);
		this.getPropertyList().add(attrCode);
	}

}
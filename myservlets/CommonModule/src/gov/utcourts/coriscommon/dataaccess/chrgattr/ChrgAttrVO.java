package gov.utcourts.coriscommon.dataaccess.chrgattr;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ChrgAttrVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(ChrgAttrBO.INTCASENUM.clear()).addForeignKey("charge","int_case_num",false).setAsPrimaryKey();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(ChrgAttrBO.PARTYNUM.clear()).addForeignKey("charge","party_num",false).setAsPrimaryKey();
	private TypeString partyCode = new TypeString("party_code").setFieldDescriptor(ChrgAttrBO.PARTYCODE.clear()).addForeignKey("charge","party_code",false).setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(ChrgAttrBO.SEQ.clear()).addForeignKey("charge","seq",false).setAsPrimaryKey();
	private TypeString attrType = new TypeString("attr_type").setFieldDescriptor(ChrgAttrBO.ATTRTYPE.clear()).setAsPrimaryKey();
	private TypeString attrCode = new TypeString("attr_code").setFieldDescriptor(ChrgAttrBO.ATTRCODE.clear()).addForeignKey("attr_type","attr_code",false).setAsPrimaryKey();

	public ChrgAttrVO() {
		super(ChrgAttrBO.TABLE, ChrgAttrBO.SYSTEM, ChrgAttrBO.CORIS_DISTRICT_DB.setSource("D"), ChrgAttrBO.CORIS_JUSTICE_DB.setSource("J"), ChrgAttrBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ChrgAttrBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public ChrgAttrVO(String source) {
		super(ChrgAttrBO.TABLE, ChrgAttrBO.SYSTEM, ChrgAttrBO.CORIS_DISTRICT_DB.setSource("D"), ChrgAttrBO.CORIS_JUSTICE_DB.setSource("J"), ChrgAttrBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ChrgAttrBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(partyNum);
		this.getPropertyList().add(partyCode);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(attrType);
		this.getPropertyList().add(attrCode);
	}

}
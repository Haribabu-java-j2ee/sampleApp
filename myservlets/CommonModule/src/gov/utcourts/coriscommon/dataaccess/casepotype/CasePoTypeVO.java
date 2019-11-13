package gov.utcourts.coriscommon.dataaccess.casepotype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CasePoTypeVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(CasePoTypeBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeString casePoType = new TypeString("case_po_type").setFieldDescriptor(CasePoTypeBO.CASEPOTYPE.clear());

	public CasePoTypeVO() {
		super(CasePoTypeBO.TABLE, CasePoTypeBO.SYSTEM, CasePoTypeBO.CORIS_DISTRICT_DB.setSource("D"), CasePoTypeBO.CORIS_JUSTICE_DB.setSource("J"), CasePoTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CasePoTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CasePoTypeVO(String source) {
		super(CasePoTypeBO.TABLE, CasePoTypeBO.SYSTEM, CasePoTypeBO.CORIS_DISTRICT_DB.setSource("D"), CasePoTypeBO.CORIS_JUSTICE_DB.setSource("J"), CasePoTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CasePoTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(casePoType);
	}

}
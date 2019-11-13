package gov.utcourts.coriscommon.dataaccess.domviollink;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DomViolLinkVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(DomViolLinkBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false);
	private TypeString dvCaseNum = new TypeString("dv_case_num").setFieldDescriptor(DomViolLinkBO.DVCASENUM.clear());
	private TypeString locnDescr = new TypeString("locn_descr").setFieldDescriptor(DomViolLinkBO.LOCNDESCR.clear());
	private TypeString petitionerName = new TypeString("petitioner_name").setFieldDescriptor(DomViolLinkBO.PETITIONERNAME.clear()).setNullable();
	private TypeString respondentName = new TypeString("respondent_name").setFieldDescriptor(DomViolLinkBO.RESPONDENTNAME.clear()).setNullable();

	public DomViolLinkVO() {
		super(DomViolLinkBO.TABLE, DomViolLinkBO.SYSTEM, DomViolLinkBO.CORIS_DISTRICT_DB.setSource("D"), DomViolLinkBO.CORIS_JUSTICE_DB.setSource("J"), DomViolLinkBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DomViolLinkBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DomViolLinkVO(String source) {
		super(DomViolLinkBO.TABLE, DomViolLinkBO.SYSTEM, DomViolLinkBO.CORIS_DISTRICT_DB.setSource("D"), DomViolLinkBO.CORIS_JUSTICE_DB.setSource("J"), DomViolLinkBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DomViolLinkBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(dvCaseNum);
		this.getPropertyList().add(locnDescr);
		this.getPropertyList().add(petitionerName);
		this.getPropertyList().add(respondentName);
	}

}
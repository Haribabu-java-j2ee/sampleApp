package gov.utcourts.coriscommon.dataaccess.appellate;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AppellateVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(AppellateBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false);
	private TypeString appelCaseNum = new TypeString("appel_case_num").setFieldDescriptor(AppellateBO.APPELCASENUM.clear());

	public AppellateVO() {
		super(AppellateBO.TABLE, AppellateBO.SYSTEM, AppellateBO.CORIS_DISTRICT_DB.setSource("D"), AppellateBO.CORIS_JUSTICE_DB.setSource("J"), AppellateBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AppellateBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AppellateVO(String source) {
		super(AppellateBO.TABLE, AppellateBO.SYSTEM, AppellateBO.CORIS_DISTRICT_DB.setSource("D"), AppellateBO.CORIS_JUSTICE_DB.setSource("J"), AppellateBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AppellateBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(appelCaseNum);
	}

}
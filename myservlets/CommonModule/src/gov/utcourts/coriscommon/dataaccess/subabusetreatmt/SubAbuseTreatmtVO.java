package gov.utcourts.coriscommon.dataaccess.subabusetreatmt;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SubAbuseTreatmtVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(SubAbuseTreatmtBO.INTCASENUM.clear()).addForeignKey("crim_case","int_case_num",false).setAsPrimaryKey();
	private TypeString subAbuseTreatmt = new TypeString("sub_abuse_treatmt").setFieldDescriptor(SubAbuseTreatmtBO.SUBABUSETREATMT.clear()).setNullable();
	private TypeString appSupervision = new TypeString("app_supervision").setFieldDescriptor(SubAbuseTreatmtBO.APPSUPERVISION.clear()).setNullable();
	private TypeString vehicleInvolved = new TypeString("vehicle_involved").setFieldDescriptor(SubAbuseTreatmtBO.VEHICLEINVOLVED.clear());

	public SubAbuseTreatmtVO() {
		super(SubAbuseTreatmtBO.TABLE, SubAbuseTreatmtBO.SYSTEM, SubAbuseTreatmtBO.CORIS_DISTRICT_DB.setSource("D"), SubAbuseTreatmtBO.CORIS_JUSTICE_DB.setSource("J"), SubAbuseTreatmtBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SubAbuseTreatmtBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SubAbuseTreatmtVO(String source) {
		super(SubAbuseTreatmtBO.TABLE, SubAbuseTreatmtBO.SYSTEM, SubAbuseTreatmtBO.CORIS_DISTRICT_DB.setSource("D"), SubAbuseTreatmtBO.CORIS_JUSTICE_DB.setSource("J"), SubAbuseTreatmtBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SubAbuseTreatmtBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(subAbuseTreatmt);
		this.getPropertyList().add(appSupervision);
		this.getPropertyList().add(vehicleInvolved);
	}

}
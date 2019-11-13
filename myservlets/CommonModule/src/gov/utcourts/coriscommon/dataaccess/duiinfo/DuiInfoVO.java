package gov.utcourts.coriscommon.dataaccess.duiinfo;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DuiInfoVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(DuiInfoBO.INTCASENUM.clear()).addForeignKey("crim_case","int_case_num",false).setAsPrimaryKey();
	private TypeInteger chargedAs = new TypeInteger("charged_as").setFieldDescriptor(DuiInfoBO.CHARGEDAS.clear()).addForeignKey("ordinal","ordinal_id",false).setNullable();
	private TypeInteger actuallyWas = new TypeInteger("actually_was").setFieldDescriptor(DuiInfoBO.ACTUALLYWAS.clear()).addForeignKey("ordinal","ordinal_id",false).setNullable();
	private TypeInteger sentencedAs = new TypeInteger("sentenced_as").setFieldDescriptor(DuiInfoBO.SENTENCEDAS.clear()).addForeignKey("ordinal","ordinal_id",false).setNullable();
	private TypeInteger bloodAlcohol = new TypeInteger("blood_alcohol").setFieldDescriptor(DuiInfoBO.BLOODALCOHOL.clear()).addForeignKey("blood_alcohol","ba_id",false).setNullable();
	private TypeString screening = new TypeString("screening").setFieldDescriptor(DuiInfoBO.SCREENING.clear());
	private TypeString education = new TypeString("education").setFieldDescriptor(DuiInfoBO.EDUCATION.clear());
	private TypeString treatment = new TypeString("treatment").setFieldDescriptor(DuiInfoBO.TREATMENT.clear());
	private TypeString monitoring = new TypeString("monitoring").setFieldDescriptor(DuiInfoBO.MONITORING.clear());

	public DuiInfoVO() {
		super(DuiInfoBO.TABLE, DuiInfoBO.SYSTEM, DuiInfoBO.CORIS_DISTRICT_DB.setSource("D"), DuiInfoBO.CORIS_JUSTICE_DB.setSource("J"), DuiInfoBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DuiInfoBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DuiInfoVO(String source) {
		super(DuiInfoBO.TABLE, DuiInfoBO.SYSTEM, DuiInfoBO.CORIS_DISTRICT_DB.setSource("D"), DuiInfoBO.CORIS_JUSTICE_DB.setSource("J"), DuiInfoBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DuiInfoBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(chargedAs);
		this.getPropertyList().add(actuallyWas);
		this.getPropertyList().add(sentencedAs);
		this.getPropertyList().add(bloodAlcohol);
		this.getPropertyList().add(screening);
		this.getPropertyList().add(education);
		this.getPropertyList().add(treatment);
		this.getPropertyList().add(monitoring);
	}

}
package gov.utcourts.coriscommon.dataaccess.scinterest;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ScInterestVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(ScInterestBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setNullable().setAsPrimaryKey();
	private TypeInteger jdmtSeq = new TypeInteger("jdmt_seq").setFieldDescriptor(ScInterestBO.JDMTSEQ.clear()).setNullable().setAsPrimaryKey();
	private TypeBigDecimal interest = new TypeBigDecimal("interest").setFieldDescriptor(ScInterestBO.INTEREST.clear()).setNullable();

	public ScInterestVO() {
		super(ScInterestBO.TABLE, ScInterestBO.SYSTEM, ScInterestBO.CORIS_DISTRICT_DB.setSource("D"), ScInterestBO.CORIS_JUSTICE_DB.setSource("J"), ScInterestBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ScInterestBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public ScInterestVO(String source) {
		super(ScInterestBO.TABLE, ScInterestBO.SYSTEM, ScInterestBO.CORIS_DISTRICT_DB.setSource("D"), ScInterestBO.CORIS_JUSTICE_DB.setSource("J"), ScInterestBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ScInterestBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(jdmtSeq);
		this.getPropertyList().add(interest);
	}

}
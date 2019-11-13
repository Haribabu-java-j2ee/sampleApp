package gov.utcourts.coriscommon.dataaccess.courtdivision;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CourtDivisionVO extends BaseVO { 

	private TypeInteger judgeCommId = new TypeInteger("judge_comm_id").setFieldDescriptor(CourtDivisionBO.JUDGECOMMID.clear()).addForeignKey("personnel","userid_srl",false).setAsPrimaryKey();
	private TypeInteger division = new TypeInteger("division").setFieldDescriptor(CourtDivisionBO.DIVISION.clear());

	public CourtDivisionVO() {
		super(CourtDivisionBO.TABLE, CourtDivisionBO.SYSTEM, CourtDivisionBO.CORIS_DISTRICT_DB.setSource("D"), CourtDivisionBO.CORIS_JUSTICE_DB.setSource("J"), CourtDivisionBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CourtDivisionBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CourtDivisionVO(String source) {
		super(CourtDivisionBO.TABLE, CourtDivisionBO.SYSTEM, CourtDivisionBO.CORIS_DISTRICT_DB.setSource("D"), CourtDivisionBO.CORIS_JUSTICE_DB.setSource("J"), CourtDivisionBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CourtDivisionBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(judgeCommId);
		this.getPropertyList().add(division);
	}

}
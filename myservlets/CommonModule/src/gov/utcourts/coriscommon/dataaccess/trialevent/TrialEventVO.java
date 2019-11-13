package gov.utcourts.coriscommon.dataaccess.trialevent;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class TrialEventVO extends BaseVO { 

	private TypeString category = new TypeString("category").setFieldDescriptor(TrialEventBO.CATEGORY.clear()).setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(TrialEventBO.SEQ.clear()).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(TrialEventBO.DESCR.clear());

	public TrialEventVO() {
		super(TrialEventBO.TABLE, TrialEventBO.SYSTEM, TrialEventBO.CORIS_DISTRICT_DB.setSource("D"), TrialEventBO.CORIS_JUSTICE_DB.setSource("J"), TrialEventBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TrialEventBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public TrialEventVO(String source) {
		super(TrialEventBO.TABLE, TrialEventBO.SYSTEM, TrialEventBO.CORIS_DISTRICT_DB.setSource("D"), TrialEventBO.CORIS_JUSTICE_DB.setSource("J"), TrialEventBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TrialEventBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(category);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(descr);
	}

}
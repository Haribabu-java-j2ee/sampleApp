package gov.utcourts.coriscommon.dataaccess.newtrialevent;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class NewTrialEventVO extends BaseVO { 

	private TypeString category = new TypeString("category").setFieldDescriptor(NewTrialEventBO.CATEGORY.clear()).setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(NewTrialEventBO.SEQ.clear()).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(NewTrialEventBO.DESCR.clear());

	public NewTrialEventVO() {
		super(NewTrialEventBO.TABLE, NewTrialEventBO.SYSTEM, NewTrialEventBO.CORIS_DISTRICT_DB.setSource("D"), NewTrialEventBO.CORIS_JUSTICE_DB.setSource("J"), NewTrialEventBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), NewTrialEventBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public NewTrialEventVO(String source) {
		super(NewTrialEventBO.TABLE, NewTrialEventBO.SYSTEM, NewTrialEventBO.CORIS_DISTRICT_DB.setSource("D"), NewTrialEventBO.CORIS_JUSTICE_DB.setSource("J"), NewTrialEventBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), NewTrialEventBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(category);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(descr);
	}

}
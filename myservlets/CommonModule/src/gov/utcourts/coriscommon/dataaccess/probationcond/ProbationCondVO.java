package gov.utcourts.coriscommon.dataaccess.probationcond;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ProbationCondVO extends BaseVO { 

	private TypeString condCode = new TypeString("cond_code").setFieldDescriptor(ProbationCondBO.CONDCODE.clear()).setAsPrimaryKey();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(ProbationCondBO.LOCNCODE.clear()).setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(ProbationCondBO.COURTTYPE.clear()).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(ProbationCondBO.DESCR.clear());
	private TypeString type = new TypeString("type").setFieldDescriptor(ProbationCondBO.TYPE.clear());

	public ProbationCondVO() {
		super(ProbationCondBO.TABLE, ProbationCondBO.SYSTEM, ProbationCondBO.CORIS_DISTRICT_DB.setSource("D"), ProbationCondBO.CORIS_JUSTICE_DB.setSource("J"), ProbationCondBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ProbationCondBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public ProbationCondVO(String source) {
		super(ProbationCondBO.TABLE, ProbationCondBO.SYSTEM, ProbationCondBO.CORIS_DISTRICT_DB.setSource("D"), ProbationCondBO.CORIS_JUSTICE_DB.setSource("J"), ProbationCondBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ProbationCondBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(condCode);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(type);
	}

}
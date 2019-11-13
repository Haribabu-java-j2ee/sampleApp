package gov.utcourts.coriscommon.dataaccess.reasondescr;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ReasonDescrVO extends BaseVO { 

	private TypeString category = new TypeString("category").setFieldDescriptor(ReasonDescrBO.CATEGORY.clear()).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(ReasonDescrBO.DESCR.clear()).setAsPrimaryKey();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(ReasonDescrBO.LOCNCODE.clear()).addForeignKey("profile","locn_code",false).setNullable().setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(ReasonDescrBO.COURTTYPE.clear()).addForeignKey("profile","court_type",false).setNullable().setAsPrimaryKey();

	public ReasonDescrVO() {
		super(ReasonDescrBO.TABLE, ReasonDescrBO.SYSTEM, ReasonDescrBO.CORIS_DISTRICT_DB.setSource("D"), ReasonDescrBO.CORIS_JUSTICE_DB.setSource("J"), ReasonDescrBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ReasonDescrBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public ReasonDescrVO(String source) {
		super(ReasonDescrBO.TABLE, ReasonDescrBO.SYSTEM, ReasonDescrBO.CORIS_DISTRICT_DB.setSource("D"), ReasonDescrBO.CORIS_JUSTICE_DB.setSource("J"), ReasonDescrBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ReasonDescrBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(category);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
	}

}
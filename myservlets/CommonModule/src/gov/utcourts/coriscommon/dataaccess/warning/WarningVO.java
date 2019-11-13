package gov.utcourts.coriscommon.dataaccess.warning;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class WarningVO extends BaseVO { 

	private TypeString descr = new TypeString("descr").setFieldDescriptor(WarningBO.DESCR.clear());
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(WarningBO.LOCNCODE.clear()).addForeignKey("profile","locn_code",false).setNullable();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(WarningBO.COURTTYPE.clear()).addForeignKey("profile","court_type",false).setNullable();

	public WarningVO() {
		super(WarningBO.TABLE, WarningBO.SYSTEM, WarningBO.CORIS_DISTRICT_DB.setSource("D"), WarningBO.CORIS_JUSTICE_DB.setSource("J"), WarningBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), WarningBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public WarningVO(String source) {
		super(WarningBO.TABLE, WarningBO.SYSTEM, WarningBO.CORIS_DISTRICT_DB.setSource("D"), WarningBO.CORIS_JUSTICE_DB.setSource("J"), WarningBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), WarningBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(descr);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
	}

}
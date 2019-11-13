package gov.utcourts.coriscommon.dataaccess.location;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class LocationVO extends BaseVO { 

	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(LocationBO.LOCNCODE.clear()).addForeignKey("tracking_type","locn_code",true).setAsPrimaryKey();
	private TypeString locnDescr = new TypeString("locn_descr").setFieldDescriptor(LocationBO.LOCNDESCR.clear()).setNullable();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(LocationBO.COURTTYPE.clear()).addForeignKey("tracking_type","court_type",true).setAsPrimaryKey();
	private TypeString locnType = new TypeString("locn_type").setFieldDescriptor(LocationBO.LOCNTYPE.clear()).setNullable();
	private TypeString dbName = new TypeString("db_name").setFieldDescriptor(LocationBO.DBNAME.clear()).setNullable();

	public LocationVO() {
		super(LocationBO.TABLE, LocationBO.SYSTEM, LocationBO.CORIS_DISTRICT_DB.setSource("D"), LocationBO.CORIS_JUSTICE_DB.setSource("J"), LocationBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), LocationBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public LocationVO(String source) {
		super(LocationBO.TABLE, LocationBO.SYSTEM, LocationBO.CORIS_DISTRICT_DB.setSource("D"), LocationBO.CORIS_JUSTICE_DB.setSource("J"), LocationBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), LocationBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(locnDescr);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(locnType);
		this.getPropertyList().add(dbName);
	}

}
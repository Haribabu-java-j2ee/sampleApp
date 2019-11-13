package gov.utcourts.coriscommon.dataaccess.databaseservernames;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DatabaseServerNamesVO extends BaseVO { 

	private TypeString type = new TypeString("type").setFieldDescriptor(DatabaseServerNamesBO.TYPE.clear());
	private TypeString name = new TypeString("name").setFieldDescriptor(DatabaseServerNamesBO.NAME.clear());

	public DatabaseServerNamesVO() {
		super(DatabaseServerNamesBO.TABLE, DatabaseServerNamesBO.SYSTEM, DatabaseServerNamesBO.CORIS_DISTRICT_DB.setSource("D"), DatabaseServerNamesBO.CORIS_JUSTICE_DB.setSource("J"), DatabaseServerNamesBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DatabaseServerNamesBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DatabaseServerNamesVO(String source) {
		super(DatabaseServerNamesBO.TABLE, DatabaseServerNamesBO.SYSTEM, DatabaseServerNamesBO.CORIS_DISTRICT_DB.setSource("D"), DatabaseServerNamesBO.CORIS_JUSTICE_DB.setSource("J"), DatabaseServerNamesBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DatabaseServerNamesBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(type);
		this.getPropertyList().add(name);
	}

}
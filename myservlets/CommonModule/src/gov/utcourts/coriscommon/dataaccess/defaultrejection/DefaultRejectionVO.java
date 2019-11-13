package gov.utcourts.coriscommon.dataaccess.defaultrejection;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DefaultRejectionVO extends BaseVO { 

	private TypeString category = new TypeString("category").setFieldDescriptor(DefaultRejectionBO.CATEGORY.clear()).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(DefaultRejectionBO.DESCR.clear()).setAsPrimaryKey();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(DefaultRejectionBO.LOCNCODE.clear()).addForeignKey("profile","locn_code",false).setNullable().setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(DefaultRejectionBO.COURTTYPE.clear()).addForeignKey("profile","court_type",false).setNullable().setAsPrimaryKey();

	public DefaultRejectionVO() {
		super(DefaultRejectionBO.TABLE, DefaultRejectionBO.SYSTEM, DefaultRejectionBO.CORIS_DISTRICT_DB.setSource("D"), DefaultRejectionBO.CORIS_JUSTICE_DB.setSource("J"), DefaultRejectionBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DefaultRejectionBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DefaultRejectionVO(String source) {
		super(DefaultRejectionBO.TABLE, DefaultRejectionBO.SYSTEM, DefaultRejectionBO.CORIS_DISTRICT_DB.setSource("D"), DefaultRejectionBO.CORIS_JUSTICE_DB.setSource("J"), DefaultRejectionBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DefaultRejectionBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
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
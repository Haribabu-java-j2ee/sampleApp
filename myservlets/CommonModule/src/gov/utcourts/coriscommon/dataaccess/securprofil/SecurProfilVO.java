package gov.utcourts.coriscommon.dataaccess.securprofil;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SecurProfilVO extends BaseVO { 

	private TypeString securProfilCode = new TypeString("secur_profil_code").setFieldDescriptor(SecurProfilBO.SECURPROFILCODE.clear()).setAsPrimaryKey();
	private TypeInteger securProfilValue = new TypeInteger("secur_profil_value").setFieldDescriptor(SecurProfilBO.SECURPROFILVALUE.clear());

	public SecurProfilVO() {
		super(SecurProfilBO.TABLE, SecurProfilBO.SYSTEM, SecurProfilBO.CORIS_DISTRICT_DB.setSource("D"), SecurProfilBO.CORIS_JUSTICE_DB.setSource("J"), SecurProfilBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SecurProfilBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SecurProfilVO(String source) {
		super(SecurProfilBO.TABLE, SecurProfilBO.SYSTEM, SecurProfilBO.CORIS_DISTRICT_DB.setSource("D"), SecurProfilBO.CORIS_JUSTICE_DB.setSource("J"), SecurProfilBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SecurProfilBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(securProfilCode);
		this.getPropertyList().add(securProfilValue);
	}

}
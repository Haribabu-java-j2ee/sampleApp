package gov.utcourts.coriscommon.dataaccess.securityfeature;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SecurityFeatureVO extends BaseVO { 

	private TypeString securityFeature = new TypeString("security_feature").setFieldDescriptor(SecurityFeatureBO.SECURITYFEATURE.clear());
	private TypeInteger securityValue = new TypeInteger("security_value").setFieldDescriptor(SecurityFeatureBO.SECURITYVALUE.clear()).setNullable();
	private TypeDate securityDate = new TypeDate("security_date").setFieldDescriptor(SecurityFeatureBO.SECURITYDATE.clear()).setNullable();

	public SecurityFeatureVO() {
		super(SecurityFeatureBO.TABLE, SecurityFeatureBO.SYSTEM, SecurityFeatureBO.CORIS_DISTRICT_DB.setSource("D"), SecurityFeatureBO.CORIS_JUSTICE_DB.setSource("J"), SecurityFeatureBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SecurityFeatureBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SecurityFeatureVO(String source) {
		super(SecurityFeatureBO.TABLE, SecurityFeatureBO.SYSTEM, SecurityFeatureBO.CORIS_DISTRICT_DB.setSource("D"), SecurityFeatureBO.CORIS_JUSTICE_DB.setSource("J"), SecurityFeatureBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SecurityFeatureBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(securityFeature);
		this.getPropertyList().add(securityValue);
		this.getPropertyList().add(securityDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(SecurityFeatureBO.SECURITYDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SecurityFeatureBO.SECURITYDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
package gov.utcourts.coriscommon.dataaccess.pretrialagency;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PreTrialAgencyVO extends BaseVO { 

	private TypeString ptsCode = new TypeString("pts_code").setFieldDescriptor(PreTrialAgencyBO.PTSCODE.clear());
	private TypeString ptsName = new TypeString("pts_name").setFieldDescriptor(PreTrialAgencyBO.PTSNAME.clear());
	private TypeString ptsAddress1 = new TypeString("pts_address_1").setFieldDescriptor(PreTrialAgencyBO.PTSADDRESS1.clear()).setNullable();
	private TypeString ptsAddress2 = new TypeString("pts_address_2").setFieldDescriptor(PreTrialAgencyBO.PTSADDRESS2.clear()).setNullable();
	private TypeString ptsCity = new TypeString("pts_city").setFieldDescriptor(PreTrialAgencyBO.PTSCITY.clear()).setNullable();
	private TypeString ptsStateCode = new TypeString("pts_state_code").setFieldDescriptor(PreTrialAgencyBO.PTSSTATECODE.clear()).setNullable();
	private TypeString ptsZipCode = new TypeString("pts_zip_code").setFieldDescriptor(PreTrialAgencyBO.PTSZIPCODE.clear()).setNullable();
	private TypeString ptsPhone = new TypeString("pts_phone").setFieldDescriptor(PreTrialAgencyBO.PTSPHONE.clear()).setNullable();
	private TypeString ptsEmail = new TypeString("pts_email").setFieldDescriptor(PreTrialAgencyBO.PTSEMAIL.clear()).setNullable();
	private TypeString ptsRemovedFlag = new TypeString("pts_removed_flag").setFieldDescriptor(PreTrialAgencyBO.PTSREMOVEDFLAG.clear()).setNullable();

	public PreTrialAgencyVO() {
		super(PreTrialAgencyBO.TABLE, PreTrialAgencyBO.SYSTEM, PreTrialAgencyBO.CORIS_DISTRICT_DB.setSource("D"), PreTrialAgencyBO.CORIS_JUSTICE_DB.setSource("J"), PreTrialAgencyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PreTrialAgencyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PreTrialAgencyVO(String source) {
		super(PreTrialAgencyBO.TABLE, PreTrialAgencyBO.SYSTEM, PreTrialAgencyBO.CORIS_DISTRICT_DB.setSource("D"), PreTrialAgencyBO.CORIS_JUSTICE_DB.setSource("J"), PreTrialAgencyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PreTrialAgencyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(ptsCode);
		this.getPropertyList().add(ptsName);
		this.getPropertyList().add(ptsAddress1);
		this.getPropertyList().add(ptsAddress2);
		this.getPropertyList().add(ptsCity);
		this.getPropertyList().add(ptsStateCode);
		this.getPropertyList().add(ptsZipCode);
		this.getPropertyList().add(ptsPhone);
		this.getPropertyList().add(ptsEmail);
		this.getPropertyList().add(ptsRemovedFlag);
	}

}
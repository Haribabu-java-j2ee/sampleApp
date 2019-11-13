package gov.utcourts.coriscommon.dataaccess.drlicissueagency;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DrLicIssueAgencyVO extends BaseVO { 

	private TypeInteger issueAgencyId = new TypeInteger("issue_agency_id").setFieldDescriptor(DrLicIssueAgencyBO.ISSUEAGENCYID.clear()).setAsPrimaryKey();
	private TypeString stateCode = new TypeString("state_code").setFieldDescriptor(DrLicIssueAgencyBO.STATECODE.clear());
	private TypeString countryCode = new TypeString("country_code").setFieldDescriptor(DrLicIssueAgencyBO.COUNTRYCODE.clear());
	private TypeString issueAgencyDescr = new TypeString("issue_agency_descr").setFieldDescriptor(DrLicIssueAgencyBO.ISSUEAGENCYDESCR.clear());

	public DrLicIssueAgencyVO(String source) {
		super(DrLicIssueAgencyBO.TABLE, DrLicIssueAgencyBO.SYSTEM, DrLicIssueAgencyBO.CORIS_DISTRICT_DB.setSource("D"), DrLicIssueAgencyBO.CORIS_JUSTICE_DB.setSource("J"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(issueAgencyId);
		this.getPropertyList().add(stateCode);
		this.getPropertyList().add(countryCode);
		this.getPropertyList().add(issueAgencyDescr);
	}

}
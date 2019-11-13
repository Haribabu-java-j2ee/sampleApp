package gov.utcourts.coriscommon.dataaccess.profilefeature;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ProfileFeatureVO extends BaseVO { 


	public ProfileFeatureVO(String source) {
		super(ProfileFeatureBO.TABLE, ProfileFeatureBO.SYSTEM, ProfileFeatureBO.CORIS_DISTRICT_DB.setSource("D"), ProfileFeatureBO.CORIS_JUSTICE_DB.setSource("J"));
		init();
		setSource(source);
	}

	private void init() {
	}

}
package gov.utcourts.coriscommon.dataaccess.helpidx;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class HelpIdxVO extends BaseVO { 

	private TypeString helpTopic = new TypeString("help_topic").setFieldDescriptor(HelpIdxBO.HELPTOPIC.clear()).setAsPrimaryKey();
	private TypeString helpDetail = new TypeString("help_detail").setFieldDescriptor(HelpIdxBO.HELPDETAIL.clear()).setAsPrimaryKey();

	public HelpIdxVO() {
		super(HelpIdxBO.TABLE, HelpIdxBO.SYSTEM, HelpIdxBO.CORIS_DISTRICT_DB.setSource("D"), HelpIdxBO.CORIS_JUSTICE_DB.setSource("J"), HelpIdxBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), HelpIdxBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public HelpIdxVO(String source) {
		super(HelpIdxBO.TABLE, HelpIdxBO.SYSTEM, HelpIdxBO.CORIS_DISTRICT_DB.setSource("D"), HelpIdxBO.CORIS_JUSTICE_DB.setSource("J"), HelpIdxBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), HelpIdxBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(helpTopic);
		this.getPropertyList().add(helpDetail);
	}

}
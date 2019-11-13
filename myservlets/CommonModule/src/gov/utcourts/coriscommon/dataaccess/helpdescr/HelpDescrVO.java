package gov.utcourts.coriscommon.dataaccess.helpdescr;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class HelpDescrVO extends BaseVO { 

	private TypeString helpDetail = new TypeString("help_detail").setFieldDescriptor(HelpDescrBO.HELPDETAIL.clear()).setAsPrimaryKey();
	private TypeString helpDescr = new TypeString("help_descr").setFieldDescriptor(HelpDescrBO.HELPDESCR.clear()).setNullable();
	private TypeString corisUrl = new TypeString("coris_url").setFieldDescriptor(HelpDescrBO.CORISURL.clear()).setNullable();
	private TypeString workbookUrl = new TypeString("workbook_url").setFieldDescriptor(HelpDescrBO.WORKBOOKURL.clear()).setNullable();

	public HelpDescrVO() {
		super(HelpDescrBO.TABLE, HelpDescrBO.SYSTEM, HelpDescrBO.CORIS_DISTRICT_DB.setSource("D"), HelpDescrBO.CORIS_JUSTICE_DB.setSource("J"), HelpDescrBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), HelpDescrBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public HelpDescrVO(String source) {
		super(HelpDescrBO.TABLE, HelpDescrBO.SYSTEM, HelpDescrBO.CORIS_DISTRICT_DB.setSource("D"), HelpDescrBO.CORIS_JUSTICE_DB.setSource("J"), HelpDescrBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), HelpDescrBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(helpDetail);
		this.getPropertyList().add(helpDescr);
		this.getPropertyList().add(corisUrl);
		this.getPropertyList().add(workbookUrl);
	}

}
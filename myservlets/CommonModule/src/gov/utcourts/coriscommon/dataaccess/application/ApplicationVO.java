package gov.utcourts.coriscommon.dataaccess.application;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ApplicationVO extends BaseVO { 

	private TypeInteger applicId = new TypeInteger("applic_id").setFieldDescriptor(ApplicationBO.APPLICID.clear()).addForeignKey("pers_applic","applic_id",true).addForeignKey("roleapplic_xref","applic_id",false).setAsPrimaryKey();
	private TypeString applicName = new TypeString("applic_name").setFieldDescriptor(ApplicationBO.APPLICNAME.clear()).setNullable();
	private TypeString applicDescr = new TypeString("applic_descr").setFieldDescriptor(ApplicationBO.APPLICDESCR.clear()).setNullable();
	private TypeString applicPasswd = new TypeString("applic_passwd").setFieldDescriptor(ApplicationBO.APPLICPASSWD.clear()).setNullable();

	public ApplicationVO() {
		super(ApplicationBO.TABLE, ApplicationBO.SYSTEM, ApplicationBO.CORIS_DISTRICT_DB.setSource("D"), ApplicationBO.CORIS_JUSTICE_DB.setSource("J"), ApplicationBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ApplicationBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public ApplicationVO(String source) {
		super(ApplicationBO.TABLE, ApplicationBO.SYSTEM, ApplicationBO.CORIS_DISTRICT_DB.setSource("D"), ApplicationBO.CORIS_JUSTICE_DB.setSource("J"), ApplicationBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ApplicationBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(applicId);
		this.getPropertyList().add(applicName);
		this.getPropertyList().add(applicDescr);
		this.getPropertyList().add(applicPasswd);
	}

}
package gov.utcourts.coriscommon.dataaccess.persapplic;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PersApplicVO extends BaseVO { 

	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(PersApplicBO.USERIDSRL.clear()).addForeignKey("personnel","userid_srl",false).setNullable().setAsPrimaryKey();
	private TypeInteger applicId = new TypeInteger("applic_id").setFieldDescriptor(PersApplicBO.APPLICID.clear()).addForeignKey("application","applic_id",false).setNullable().setAsPrimaryKey();
	private TypeString applicSecurLvl = new TypeString("applic_secur_lvl").setFieldDescriptor(PersApplicBO.APPLICSECURLVL.clear()).setNullable();

	public PersApplicVO() {
		super(PersApplicBO.TABLE, PersApplicBO.SYSTEM, PersApplicBO.CORIS_DISTRICT_DB.setSource("D"), PersApplicBO.CORIS_JUSTICE_DB.setSource("J"), PersApplicBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PersApplicBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PersApplicVO(String source) {
		super(PersApplicBO.TABLE, PersApplicBO.SYSTEM, PersApplicBO.CORIS_DISTRICT_DB.setSource("D"), PersApplicBO.CORIS_JUSTICE_DB.setSource("J"), PersApplicBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PersApplicBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(applicId);
		this.getPropertyList().add(applicSecurLvl);
	}

}
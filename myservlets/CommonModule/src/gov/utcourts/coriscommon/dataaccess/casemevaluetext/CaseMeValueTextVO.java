package gov.utcourts.coriscommon.dataaccess.casemevaluetext;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;
import gov.utcourts.courtscommon.dataaccess.types.TypeText;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CaseMeValueTextVO extends BaseVO { 

	private TypeInteger meId = new TypeInteger("me_id").setFieldDescriptor(CaseMeValueTextBO.MEID.clear()).addForeignKey("case_me","me_id",false).setNullable().setAsPrimaryKey();
	private TypeString meScreenId = new TypeString("me_screen_id").setFieldDescriptor(CaseMeValueTextBO.MESCREENID.clear()).setAsPrimaryKey();
	private TypeString meFieldId = new TypeString("me_field_id").setFieldDescriptor(CaseMeValueTextBO.MEFIELDID.clear()).setAsPrimaryKey();
	private TypeInteger meFieldSeq = new TypeInteger("me_field_seq").setFieldDescriptor(CaseMeValueTextBO.MEFIELDSEQ.clear()).setAsPrimaryKey();
	private TypeText meFieldValue = new TypeText("me_field_value").setFieldDescriptor(CaseMeValueTextBO.MEFIELDVALUE.clear()).setNullable();

	public CaseMeValueTextVO() {
		super(CaseMeValueTextBO.TABLE, CaseMeValueTextBO.SYSTEM, CaseMeValueTextBO.CORIS_DISTRICT_DB.setSource("D"), CaseMeValueTextBO.CORIS_JUSTICE_DB.setSource("J"), CaseMeValueTextBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseMeValueTextBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CaseMeValueTextVO(String source) {
		super(CaseMeValueTextBO.TABLE, CaseMeValueTextBO.SYSTEM, CaseMeValueTextBO.CORIS_DISTRICT_DB.setSource("D"), CaseMeValueTextBO.CORIS_JUSTICE_DB.setSource("J"), CaseMeValueTextBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseMeValueTextBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(meId);
		this.getPropertyList().add(meScreenId);
		this.getPropertyList().add(meFieldId);
		this.getPropertyList().add(meFieldSeq);
		this.getPropertyList().add(meFieldValue);
	}

}
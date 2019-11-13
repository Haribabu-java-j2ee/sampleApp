package gov.utcourts.coriscommon.dataaccess.casemevalue;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CaseMeValueVO extends BaseVO { 

	private TypeInteger meId = new TypeInteger("me_id").setFieldDescriptor(CaseMeValueBO.MEID.clear()).addForeignKey("case_me","me_id",false).setAsPrimaryKey();
	private TypeInteger meTypeSeq = new TypeInteger("me_type_seq").setFieldDescriptor(CaseMeValueBO.METYPESEQ.clear()).setAsPrimaryKey();
	private TypeString meScreenId = new TypeString("me_screen_id").setFieldDescriptor(CaseMeValueBO.MESCREENID.clear()).setAsPrimaryKey();
	private TypeString meFieldId = new TypeString("me_field_id").setFieldDescriptor(CaseMeValueBO.MEFIELDID.clear()).setAsPrimaryKey();
	private TypeInteger meFieldSeq = new TypeInteger("me_field_seq").setFieldDescriptor(CaseMeValueBO.MEFIELDSEQ.clear()).setAsPrimaryKey();
	private TypeString meFieldValue = new TypeString("me_field_value").setFieldDescriptor(CaseMeValueBO.MEFIELDVALUE.clear()).setNullable();

	public CaseMeValueVO() {
		super(CaseMeValueBO.TABLE, CaseMeValueBO.SYSTEM, CaseMeValueBO.CORIS_DISTRICT_DB.setSource("D"), CaseMeValueBO.CORIS_JUSTICE_DB.setSource("J"), CaseMeValueBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseMeValueBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CaseMeValueVO(String source) {
		super(CaseMeValueBO.TABLE, CaseMeValueBO.SYSTEM, CaseMeValueBO.CORIS_DISTRICT_DB.setSource("D"), CaseMeValueBO.CORIS_JUSTICE_DB.setSource("J"), CaseMeValueBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseMeValueBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(meId);
		this.getPropertyList().add(meTypeSeq);
		this.getPropertyList().add(meScreenId);
		this.getPropertyList().add(meFieldId);
		this.getPropertyList().add(meFieldSeq);
		this.getPropertyList().add(meFieldValue);
	}

}
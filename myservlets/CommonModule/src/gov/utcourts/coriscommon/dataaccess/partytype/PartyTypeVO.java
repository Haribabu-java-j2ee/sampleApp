package gov.utcourts.coriscommon.dataaccess.partytype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PartyTypeVO extends BaseVO { 

	private TypeString partyCode = new TypeString("party_code").setFieldDescriptor(PartyTypeBO.PARTYCODE.clear()).addForeignKey("party_case","party_code",false).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(PartyTypeBO.DESCR.clear());
	private TypeString validCourt = new TypeString("valid_court").setFieldDescriptor(PartyTypeBO.VALIDCOURT.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(PartyTypeBO.REMOVEDFLAG.clear());
	private TypeString category = new TypeString("category").setFieldDescriptor(PartyTypeBO.CATEGORY.clear()).setNullable();

	public PartyTypeVO() {
		super(PartyTypeBO.TABLE, PartyTypeBO.SYSTEM, PartyTypeBO.CORIS_DISTRICT_DB.setSource("D"), PartyTypeBO.CORIS_JUSTICE_DB.setSource("J"), PartyTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PartyTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PartyTypeVO(String source) {
		super(PartyTypeBO.TABLE, PartyTypeBO.SYSTEM, PartyTypeBO.CORIS_DISTRICT_DB.setSource("D"), PartyTypeBO.CORIS_JUSTICE_DB.setSource("J"), PartyTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PartyTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(partyCode);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(validCourt);
		this.getPropertyList().add(removedFlag);
		this.getPropertyList().add(category);
	}

}
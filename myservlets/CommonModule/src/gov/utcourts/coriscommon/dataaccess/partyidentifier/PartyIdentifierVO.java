package gov.utcourts.coriscommon.dataaccess.partyidentifier;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PartyIdentifierVO extends BaseVO { 

	private TypeInteger partyIdentifierId = new TypeInteger("party_identifier_id").setFieldDescriptor(PartyIdentifierBO.PARTYIDENTIFIERID.clear()).setAsPrimaryKey();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(PartyIdentifierBO.PARTYNUM.clear()).addForeignKey("party","party_num",false).setNullable();
	private TypeInteger idIssuerId = new TypeInteger("id_issuer_id").setFieldDescriptor(PartyIdentifierBO.IDISSUERID.clear()).addForeignKey("id_issuer","id_issuer_id",false).setNullable();
	private TypeString idNumber = new TypeString("id_number").setFieldDescriptor(PartyIdentifierBO.IDNUMBER.clear());
	private TypeInteger idTypeDefnId = new TypeInteger("id_type_defn_id").setFieldDescriptor(PartyIdentifierBO.IDTYPEDEFNID.clear()).addForeignKey("id_type_defn","id_type_defn_id",false);

	public PartyIdentifierVO() {
		super(PartyIdentifierBO.TABLE, PartyIdentifierBO.SYSTEM, PartyIdentifierBO.CORIS_DISTRICT_DB.setSource("D"), PartyIdentifierBO.CORIS_JUSTICE_DB.setSource("J"), PartyIdentifierBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PartyIdentifierBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PartyIdentifierVO(String source) {
		super(PartyIdentifierBO.TABLE, PartyIdentifierBO.SYSTEM, PartyIdentifierBO.CORIS_DISTRICT_DB.setSource("D"), PartyIdentifierBO.CORIS_JUSTICE_DB.setSource("J"), PartyIdentifierBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PartyIdentifierBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(partyIdentifierId);
		this.getPropertyList().add(partyNum);
		this.getPropertyList().add(idIssuerId);
		this.getPropertyList().add(idNumber);
		this.getPropertyList().add(idTypeDefnId);
	}

}
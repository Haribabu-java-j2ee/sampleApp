package gov.utcourts.coriscommon.dataaccess.idissuer;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class IdIssuerVO extends BaseVO { 

	private TypeInteger idIssuerId = new TypeInteger("id_issuer_id").setFieldDescriptor(IdIssuerBO.IDISSUERID.clear()).addForeignKey("party_identifier","id_issuer_id",true).setAsPrimaryKey();
	private TypeInteger stateId = new TypeInteger("state_id").setFieldDescriptor(IdIssuerBO.STATEID.clear()).addForeignKey("state","state_id",false).setNullable();
	private TypeInteger countryId = new TypeInteger("country_id").setFieldDescriptor(IdIssuerBO.COUNTRYID.clear()).addForeignKey("country","country_id",false).setNullable();

	public IdIssuerVO() {
		super(IdIssuerBO.TABLE, IdIssuerBO.SYSTEM, IdIssuerBO.CORIS_DISTRICT_DB.setSource("D"), IdIssuerBO.CORIS_JUSTICE_DB.setSource("J"), IdIssuerBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), IdIssuerBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public IdIssuerVO(String source) {
		super(IdIssuerBO.TABLE, IdIssuerBO.SYSTEM, IdIssuerBO.CORIS_DISTRICT_DB.setSource("D"), IdIssuerBO.CORIS_JUSTICE_DB.setSource("J"), IdIssuerBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), IdIssuerBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(idIssuerId);
		this.getPropertyList().add(stateId);
		this.getPropertyList().add(countryId);
	}

}
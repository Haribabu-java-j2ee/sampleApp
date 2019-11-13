package gov.utcourts.coriscommon.dataaccess.rule4202;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class Rule4202VO extends BaseVO { 

	private TypeInteger dmDocid = new TypeInteger("dm_docid").setFieldDescriptor(Rule4202BO.DMDOCID.clear()).setNullable();

	public Rule4202VO() {
		super(Rule4202BO.TABLE, Rule4202BO.SYSTEM, Rule4202BO.CORIS_DISTRICT_DB.setSource("D"), Rule4202BO.CORIS_JUSTICE_DB.setSource("J"), Rule4202BO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), Rule4202BO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public Rule4202VO(String source) {
		super(Rule4202BO.TABLE, Rule4202BO.SYSTEM, Rule4202BO.CORIS_DISTRICT_DB.setSource("D"), Rule4202BO.CORIS_JUSTICE_DB.setSource("J"), Rule4202BO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), Rule4202BO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(dmDocid);
	}

}
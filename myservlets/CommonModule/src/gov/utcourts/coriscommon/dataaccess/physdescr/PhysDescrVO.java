package gov.utcourts.coriscommon.dataaccess.physdescr;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PhysDescrVO extends BaseVO { 

	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(PhysDescrBO.PARTYNUM.clear()).setAsPrimaryKey();
	private TypeString height = new TypeString("height").setFieldDescriptor(PhysDescrBO.HEIGHT.clear()).setNullable();
	private TypeString weight = new TypeString("weight").setFieldDescriptor(PhysDescrBO.WEIGHT.clear()).setNullable();
	private TypeString eyeColor = new TypeString("eye_color").setFieldDescriptor(PhysDescrBO.EYECOLOR.clear()).setNullable();
	private TypeString hairColor = new TypeString("hair_color").setFieldDescriptor(PhysDescrBO.HAIRCOLOR.clear()).setNullable();
	private TypeString idDescr = new TypeString("id_descr").setFieldDescriptor(PhysDescrBO.IDDESCR.clear()).setNullable();

	public PhysDescrVO() {
		super(PhysDescrBO.TABLE, PhysDescrBO.SYSTEM, PhysDescrBO.CORIS_DISTRICT_DB.setSource("D"), PhysDescrBO.CORIS_JUSTICE_DB.setSource("J"), PhysDescrBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PhysDescrBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PhysDescrVO(String source) {
		super(PhysDescrBO.TABLE, PhysDescrBO.SYSTEM, PhysDescrBO.CORIS_DISTRICT_DB.setSource("D"), PhysDescrBO.CORIS_JUSTICE_DB.setSource("J"), PhysDescrBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PhysDescrBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(partyNum);
		this.getPropertyList().add(height);
		this.getPropertyList().add(weight);
		this.getPropertyList().add(eyeColor);
		this.getPropertyList().add(hairColor);
		this.getPropertyList().add(idDescr);
	}

}
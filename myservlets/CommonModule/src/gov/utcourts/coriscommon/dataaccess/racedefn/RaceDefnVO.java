package gov.utcourts.coriscommon.dataaccess.racedefn;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class RaceDefnVO extends BaseVO { 

	private TypeInteger raceDefnId = new TypeInteger("race_defn_id").setFieldDescriptor(RaceDefnBO.RACEDEFNID.clear()).setAsPrimaryKey();
	private TypeString raceCode = new TypeString("race_code").setFieldDescriptor(RaceDefnBO.RACECODE.clear()).setNullable();
	private TypeString raceDescr = new TypeString("race_descr").setFieldDescriptor(RaceDefnBO.RACEDESCR.clear()).setNullable();
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(RaceDefnBO.REMOVEDFLAG.clear()).setNullable();

	public RaceDefnVO() {
		super(RaceDefnBO.TABLE, RaceDefnBO.SYSTEM, RaceDefnBO.CORIS_DISTRICT_DB.setSource("D"), RaceDefnBO.CORIS_JUSTICE_DB.setSource("J"), RaceDefnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), RaceDefnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public RaceDefnVO(String source) {
		super(RaceDefnBO.TABLE, RaceDefnBO.SYSTEM, RaceDefnBO.CORIS_DISTRICT_DB.setSource("D"), RaceDefnBO.CORIS_JUSTICE_DB.setSource("J"), RaceDefnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), RaceDefnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(raceDefnId);
		this.getPropertyList().add(raceCode);
		this.getPropertyList().add(raceDescr);
		this.getPropertyList().add(removedFlag);
	}

}
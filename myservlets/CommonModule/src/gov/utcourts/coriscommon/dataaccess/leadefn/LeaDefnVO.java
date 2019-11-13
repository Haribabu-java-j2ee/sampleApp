package gov.utcourts.coriscommon.dataaccess.leadefn;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class LeaDefnVO extends BaseVO { 

	private TypeInteger leaSerial = new TypeInteger("lea_serial").setFieldDescriptor(LeaDefnBO.LEASERIAL.clear());
	private TypeString leaCode = new TypeString("lea_code").setFieldDescriptor(LeaDefnBO.LEACODE.clear()).setAsPrimaryKey();
	private TypeString leaDescr = new TypeString("lea_descr").setFieldDescriptor(LeaDefnBO.LEADESCR.clear());
	private TypeString jurisLvl = new TypeString("juris_lvl").setFieldDescriptor(LeaDefnBO.JURISLVL.clear());
	private TypeString cityCntyName = new TypeString("city_cnty_name").setFieldDescriptor(LeaDefnBO.CITYCNTYNAME.clear()).setNullable();
	private TypeString oriNum = new TypeString("ori_num").setFieldDescriptor(LeaDefnBO.ORINUM.clear()).setNullable().setAsPrimaryKey();
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(LeaDefnBO.REMOVEDFLAG.clear()).setNullable();
	private TypeString higherEd = new TypeString("higher_ed").setFieldDescriptor(LeaDefnBO.HIGHERED.clear()).setNullable();
	private TypeString leaEmailName = new TypeString("lea_email_name").setFieldDescriptor(LeaDefnBO.LEAEMAILNAME.clear()).setNullable();

	public LeaDefnVO() {
		super(LeaDefnBO.TABLE, LeaDefnBO.SYSTEM, LeaDefnBO.CORIS_DISTRICT_DB.setSource("D"), LeaDefnBO.CORIS_JUSTICE_DB.setSource("J"), LeaDefnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), LeaDefnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public LeaDefnVO(String source) {
		super(LeaDefnBO.TABLE, LeaDefnBO.SYSTEM, LeaDefnBO.CORIS_DISTRICT_DB.setSource("D"), LeaDefnBO.CORIS_JUSTICE_DB.setSource("J"), LeaDefnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), LeaDefnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(leaSerial);
		this.getPropertyList().add(leaCode);
		this.getPropertyList().add(leaDescr);
		this.getPropertyList().add(jurisLvl);
		this.getPropertyList().add(cityCntyName);
		this.getPropertyList().add(oriNum);
		this.getPropertyList().add(removedFlag);
		this.getPropertyList().add(higherEd);
		this.getPropertyList().add(leaEmailName);
	}

}
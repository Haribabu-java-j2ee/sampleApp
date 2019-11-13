package gov.utcourts.coriscommon.dataaccess.lea;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class LeaVO extends BaseVO { 

	private TypeString leaCode = new TypeString("lea_code").setFieldDescriptor(LeaBO.LEACODE.clear()).setAsPrimaryKey();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(LeaBO.LOCNCODE.clear()).setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(LeaBO.COURTTYPE.clear()).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(LeaBO.DESCR.clear());
	private TypeString jurisLvl = new TypeString("juris_lvl").setFieldDescriptor(LeaBO.JURISLVL.clear());
	private TypeString cityCntyName = new TypeString("city_cnty_name").setFieldDescriptor(LeaBO.CITYCNTYNAME.clear()).setNullable();
	private TypeString oriNum = new TypeString("ori_num").setFieldDescriptor(LeaBO.ORINUM.clear()).setNullable();
	private TypeString dolCode = new TypeString("dol_code").setFieldDescriptor(LeaBO.DOLCODE.clear()).setNullable();
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(LeaBO.REMOVEDFLAG.clear()).setNullable();
	private TypeString higherEd = new TypeString("higher_ed").setFieldDescriptor(LeaBO.HIGHERED.clear()).setNullable();

	public LeaVO() {
		super(LeaBO.TABLE, LeaBO.SYSTEM, LeaBO.CORIS_DISTRICT_DB.setSource("D"), LeaBO.CORIS_JUSTICE_DB.setSource("J"), LeaBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), LeaBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public LeaVO(String source) {
		super(LeaBO.TABLE, LeaBO.SYSTEM, LeaBO.CORIS_DISTRICT_DB.setSource("D"), LeaBO.CORIS_JUSTICE_DB.setSource("J"), LeaBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), LeaBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(leaCode);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(jurisLvl);
		this.getPropertyList().add(cityCntyName);
		this.getPropertyList().add(oriNum);
		this.getPropertyList().add(dolCode);
		this.getPropertyList().add(removedFlag);
		this.getPropertyList().add(higherEd);
	}

}
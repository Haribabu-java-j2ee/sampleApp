package gov.utcourts.coriscommon.dataaccess.prosecutor;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ProsecutorVO extends BaseVO { 

	private TypeInteger barNum = new TypeInteger("bar_num").setFieldDescriptor(ProsecutorBO.BARNUM.clear()).addForeignKey("attorney","bar_num",false).setAsPrimaryKey();
	private TypeString barState = new TypeString("bar_state").setFieldDescriptor(ProsecutorBO.BARSTATE.clear()).addForeignKey("attorney","bar_state",false).setAsPrimaryKey();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(ProsecutorBO.LOCNCODE.clear()).setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(ProsecutorBO.COURTTYPE.clear()).setAsPrimaryKey();
	private TypeString type = new TypeString("type").setFieldDescriptor(ProsecutorBO.TYPE.clear());

	public ProsecutorVO() {
		super(ProsecutorBO.TABLE, ProsecutorBO.SYSTEM, ProsecutorBO.CORIS_DISTRICT_DB.setSource("D"), ProsecutorBO.CORIS_JUSTICE_DB.setSource("J"), ProsecutorBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ProsecutorBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public ProsecutorVO(String source) {
		super(ProsecutorBO.TABLE, ProsecutorBO.SYSTEM, ProsecutorBO.CORIS_DISTRICT_DB.setSource("D"), ProsecutorBO.CORIS_JUSTICE_DB.setSource("J"), ProsecutorBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ProsecutorBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(barNum);
		this.getPropertyList().add(barState);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(type);
	}

}
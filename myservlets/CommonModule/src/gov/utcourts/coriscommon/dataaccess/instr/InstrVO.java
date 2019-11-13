package gov.utcourts.coriscommon.dataaccess.instr;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class InstrVO extends BaseVO { 

	private TypeString category = new TypeString("category").setFieldDescriptor(InstrBO.CATEGORY.clear()).setAsPrimaryKey();
	private TypeString title = new TypeString("title").setFieldDescriptor(InstrBO.TITLE.clear()).setAsPrimaryKey();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(InstrBO.LOCNCODE.clear()).setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(InstrBO.COURTTYPE.clear()).setAsPrimaryKey();
	private TypeString instrText = new TypeString("instr_text").setFieldDescriptor(InstrBO.INSTRTEXT.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(InstrBO.REMOVEDFLAG.clear());

	public InstrVO() {
		super(InstrBO.TABLE, InstrBO.SYSTEM, InstrBO.CORIS_DISTRICT_DB.setSource("D"), InstrBO.CORIS_JUSTICE_DB.setSource("J"), InstrBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), InstrBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public InstrVO(String source) {
		super(InstrBO.TABLE, InstrBO.SYSTEM, InstrBO.CORIS_DISTRICT_DB.setSource("D"), InstrBO.CORIS_JUSTICE_DB.setSource("J"), InstrBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), InstrBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(category);
		this.getPropertyList().add(title);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(instrText);
		this.getPropertyList().add(removedFlag);
	}

}
package gov.utcourts.coriscommon.dataaccess.pleatype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PleaTypeVO extends BaseVO { 

	private TypeString pleaCode = new TypeString("plea_code").setFieldDescriptor(PleaTypeBO.PLEACODE.clear()).addForeignKey("charge","plea_code",true).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(PleaTypeBO.DESCR.clear());
	private TypeString validCourt = new TypeString("valid_court").setFieldDescriptor(PleaTypeBO.VALIDCOURT.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(PleaTypeBO.REMOVEDFLAG.clear());
	private TypeString bciPleaCode = new TypeString("bci_plea_code").setFieldDescriptor(PleaTypeBO.BCIPLEACODE.clear()).setNullable();

	public PleaTypeVO() {
		super(PleaTypeBO.TABLE, PleaTypeBO.SYSTEM, PleaTypeBO.CORIS_DISTRICT_DB.setSource("D"), PleaTypeBO.CORIS_JUSTICE_DB.setSource("J"), PleaTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PleaTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PleaTypeVO(String source) {
		super(PleaTypeBO.TABLE, PleaTypeBO.SYSTEM, PleaTypeBO.CORIS_DISTRICT_DB.setSource("D"), PleaTypeBO.CORIS_JUSTICE_DB.setSource("J"), PleaTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PleaTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(pleaCode);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(validCourt);
		this.getPropertyList().add(removedFlag);
		this.getPropertyList().add(bciPleaCode);
	}

}
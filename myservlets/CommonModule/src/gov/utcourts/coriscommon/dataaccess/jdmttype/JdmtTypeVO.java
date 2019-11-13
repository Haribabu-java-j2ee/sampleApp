package gov.utcourts.coriscommon.dataaccess.jdmttype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class JdmtTypeVO extends BaseVO { 

	private TypeString jdmtCode = new TypeString("jdmt_code").setFieldDescriptor(JdmtTypeBO.JDMTCODE.clear()).addForeignKey("charge","jdmt_code",true).addForeignKey("judgment","jdmt_code",false).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(JdmtTypeBO.DESCR.clear());
	private TypeString category = new TypeString("category").setFieldDescriptor(JdmtTypeBO.CATEGORY.clear()).setNullable();
	private TypeString type = new TypeString("type").setFieldDescriptor(JdmtTypeBO.TYPE.clear()).setNullable();
	private TypeString bciCode = new TypeString("bci_code").setFieldDescriptor(JdmtTypeBO.BCICODE.clear()).setNullable();
	private TypeString validCourt = new TypeString("valid_court").setFieldDescriptor(JdmtTypeBO.VALIDCOURT.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(JdmtTypeBO.REMOVEDFLAG.clear());

	public JdmtTypeVO() {
		super(JdmtTypeBO.TABLE, JdmtTypeBO.SYSTEM, JdmtTypeBO.CORIS_DISTRICT_DB.setSource("D"), JdmtTypeBO.CORIS_JUSTICE_DB.setSource("J"), JdmtTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JdmtTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public JdmtTypeVO(String source) {
		super(JdmtTypeBO.TABLE, JdmtTypeBO.SYSTEM, JdmtTypeBO.CORIS_DISTRICT_DB.setSource("D"), JdmtTypeBO.CORIS_JUSTICE_DB.setSource("J"), JdmtTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JdmtTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(jdmtCode);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(category);
		this.getPropertyList().add(type);
		this.getPropertyList().add(bciCode);
		this.getPropertyList().add(validCourt);
		this.getPropertyList().add(removedFlag);
	}

}
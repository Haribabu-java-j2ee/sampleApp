package gov.utcourts.coriscommon.dataaccess.metype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class MeTypeVO extends BaseVO { 

	private TypeString meTypeCode = new TypeString("me_type_code").setFieldDescriptor(MeTypeBO.METYPECODE.clear()).addForeignKey("me_line_item","me_type_code",false).setAsPrimaryKey();
	private TypeString meTypeDescr = new TypeString("me_type_descr").setFieldDescriptor(MeTypeBO.METYPEDESCR.clear()).setNullable();
	private TypeInteger meTypeSeq = new TypeInteger("me_type_seq").setFieldDescriptor(MeTypeBO.METYPESEQ.clear());
	private TypeString newpage = new TypeString("newpage").setFieldDescriptor(MeTypeBO.NEWPAGE.clear()).setNullable();

	public MeTypeVO() {
		super(MeTypeBO.TABLE, MeTypeBO.SYSTEM, MeTypeBO.CORIS_DISTRICT_DB.setSource("D"), MeTypeBO.CORIS_JUSTICE_DB.setSource("J"), MeTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), MeTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public MeTypeVO(String source) {
		super(MeTypeBO.TABLE, MeTypeBO.SYSTEM, MeTypeBO.CORIS_DISTRICT_DB.setSource("D"), MeTypeBO.CORIS_JUSTICE_DB.setSource("J"), MeTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), MeTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(meTypeCode);
		this.getPropertyList().add(meTypeDescr);
		this.getPropertyList().add(meTypeSeq);
		this.getPropertyList().add(newpage);
	}

}
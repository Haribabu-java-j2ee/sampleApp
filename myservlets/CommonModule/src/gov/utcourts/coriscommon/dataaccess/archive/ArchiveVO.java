package gov.utcourts.coriscommon.dataaccess.archive;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ArchiveVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(ArchiveBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeString locnType = new TypeString("locn_type").setFieldDescriptor(ArchiveBO.LOCNTYPE.clear()).setAsPrimaryKey();
	private TypeString locnDescr = new TypeString("locn_descr").setFieldDescriptor(ArchiveBO.LOCNDESCR.clear()).setAsPrimaryKey();

	public ArchiveVO() {
		super(ArchiveBO.TABLE, ArchiveBO.SYSTEM, ArchiveBO.CORIS_DISTRICT_DB.setSource("D"), ArchiveBO.CORIS_JUSTICE_DB.setSource("J"), ArchiveBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ArchiveBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public ArchiveVO(String source) {
		super(ArchiveBO.TABLE, ArchiveBO.SYSTEM, ArchiveBO.CORIS_DISTRICT_DB.setSource("D"), ArchiveBO.CORIS_JUSTICE_DB.setSource("J"), ArchiveBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ArchiveBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(locnType);
		this.getPropertyList().add(locnDescr);
	}

}
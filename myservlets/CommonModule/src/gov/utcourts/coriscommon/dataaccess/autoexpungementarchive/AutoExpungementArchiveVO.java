package gov.utcourts.coriscommon.dataaccess.autoexpungementarchive;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AutoExpungementArchiveVO extends BaseVO { 

	private TypeInteger aexpArchiveId = new TypeInteger("aexp_archive_id").setFieldDescriptor(AutoExpungementArchiveBO.AEXPARCHIVEID.clear()).setAsPrimaryKey();
	private TypeString caseNum = new TypeString("case_num").setFieldDescriptor(AutoExpungementArchiveBO.CASENUM.clear());
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(AutoExpungementArchiveBO.LOCNCODE.clear());
	private TypeString caseType = new TypeString("case_type").setFieldDescriptor(AutoExpungementArchiveBO.CASETYPE.clear()).addForeignKey("case_type","case_type",false);
	private TypeDate fileDate = new TypeDate("file_date").setFieldDescriptor(AutoExpungementArchiveBO.FILEDATE.clear());
	private TypeDate deletionDate = new TypeDate("deletion_date").setFieldDescriptor(AutoExpungementArchiveBO.DELETIONDATE.clear());
	private TypeString otn = new TypeString("otn").setFieldDescriptor(AutoExpungementArchiveBO.OTN.clear()).setNullable();

	public AutoExpungementArchiveVO() {
		super(AutoExpungementArchiveBO.TABLE, AutoExpungementArchiveBO.SYSTEM, AutoExpungementArchiveBO.CORIS_DISTRICT_DB.setSource("D"), AutoExpungementArchiveBO.CORIS_JUSTICE_DB.setSource("J"), AutoExpungementArchiveBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AutoExpungementArchiveBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AutoExpungementArchiveVO(String source) {
		super(AutoExpungementArchiveBO.TABLE, AutoExpungementArchiveBO.SYSTEM, AutoExpungementArchiveBO.CORIS_DISTRICT_DB.setSource("D"), AutoExpungementArchiveBO.CORIS_JUSTICE_DB.setSource("J"), AutoExpungementArchiveBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AutoExpungementArchiveBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(aexpArchiveId);
		this.getPropertyList().add(caseNum);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(caseType);
		this.getPropertyList().add(fileDate);
		this.getPropertyList().add(deletionDate);
		this.getPropertyList().add(otn);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(AutoExpungementArchiveBO.FILEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AutoExpungementArchiveBO.FILEDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(AutoExpungementArchiveBO.DELETIONDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AutoExpungementArchiveBO.DELETIONDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
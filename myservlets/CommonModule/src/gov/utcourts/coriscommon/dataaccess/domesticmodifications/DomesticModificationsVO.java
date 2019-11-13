package gov.utcourts.coriscommon.dataaccess.domesticmodifications;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DomesticModificationsVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(DomesticModificationsBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false);
	private TypeInteger documentNum = new TypeInteger("document_num").setFieldDescriptor(DomesticModificationsBO.DOCUMENTNUM.clear()).addForeignKey("document","document_num",false).setAsPrimaryKey();
	private TypeDate startDate = new TypeDate("start_date").setFieldDescriptor(DomesticModificationsBO.STARTDATE.clear());
	private TypeInteger documentNumEnd = new TypeInteger("document_num_end").setFieldDescriptor(DomesticModificationsBO.DOCUMENTNUMEND.clear()).setNullable();
	private TypeDate endDate = new TypeDate("end_date").setFieldDescriptor(DomesticModificationsBO.ENDDATE.clear()).setNullable();
	private TypeString dmStatus = new TypeString("dm_status").setFieldDescriptor(DomesticModificationsBO.DMSTATUS.clear());

	public DomesticModificationsVO() {
		super(DomesticModificationsBO.TABLE, DomesticModificationsBO.SYSTEM, DomesticModificationsBO.CORIS_DISTRICT_DB.setSource("D"), DomesticModificationsBO.CORIS_JUSTICE_DB.setSource("J"), DomesticModificationsBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DomesticModificationsBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DomesticModificationsVO(String source) {
		super(DomesticModificationsBO.TABLE, DomesticModificationsBO.SYSTEM, DomesticModificationsBO.CORIS_DISTRICT_DB.setSource("D"), DomesticModificationsBO.CORIS_JUSTICE_DB.setSource("J"), DomesticModificationsBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DomesticModificationsBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(documentNum);
		this.getPropertyList().add(startDate);
		this.getPropertyList().add(documentNumEnd);
		this.getPropertyList().add(endDate);
		this.getPropertyList().add(dmStatus);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(DomesticModificationsBO.STARTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DomesticModificationsBO.STARTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(DomesticModificationsBO.ENDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DomesticModificationsBO.ENDDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
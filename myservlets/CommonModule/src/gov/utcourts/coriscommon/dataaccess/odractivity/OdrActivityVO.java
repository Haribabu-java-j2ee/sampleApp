package gov.utcourts.coriscommon.dataaccess.odractivity;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class OdrActivityVO extends BaseVO { 

	private TypeInteger odrActivityDocumentNum = new TypeInteger("odr_activity_document_num").setFieldDescriptor(OdrActivityBO.ODRACTIVITYDOCUMENTNUM.clear()).addForeignKey("document","document_num",false).setAsPrimaryKey();
	private TypeInteger odrActivityIntCaseNum = new TypeInteger("odr_activity_int_case_num").setFieldDescriptor(OdrActivityBO.ODRACTIVITYINTCASENUM.clear()).addForeignKey("kase","int_case_num",false);
	private TypeDate odrActivityEntryDatetime = new TypeDate("odr_activity_entry_datetime").setFieldDescriptor(OdrActivityBO.ODRACTIVITYENTRYDATETIME.clear());

	public OdrActivityVO() {
		super(OdrActivityBO.TABLE, OdrActivityBO.SYSTEM, OdrActivityBO.CORIS_DISTRICT_DB.setSource("D"), OdrActivityBO.CORIS_JUSTICE_DB.setSource("J"), OdrActivityBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OdrActivityBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public OdrActivityVO(String source) {
		super(OdrActivityBO.TABLE, OdrActivityBO.SYSTEM, OdrActivityBO.CORIS_DISTRICT_DB.setSource("D"), OdrActivityBO.CORIS_JUSTICE_DB.setSource("J"), OdrActivityBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OdrActivityBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(odrActivityDocumentNum);
		this.getPropertyList().add(odrActivityIntCaseNum);
		this.getPropertyList().add(odrActivityEntryDatetime);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(OdrActivityBO.ODRACTIVITYENTRYDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(OdrActivityBO.ODRACTIVITYENTRYDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
package gov.utcourts.coriscommon.dataaccess.bcirejections;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class BciRejectionsVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(BciRejectionsBO.INTCASENUM.clear()).addForeignKey("crim_case","int_case_num",false);
	private TypeDate rejectionDate = new TypeDate("rejection_date").setFieldDescriptor(BciRejectionsBO.REJECTIONDATE.clear());
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(BciRejectionsBO.SEQ.clear()).setNullable();

	public BciRejectionsVO() {
		super(BciRejectionsBO.TABLE, BciRejectionsBO.SYSTEM, BciRejectionsBO.CORIS_DISTRICT_DB.setSource("D"), BciRejectionsBO.CORIS_JUSTICE_DB.setSource("J"), BciRejectionsBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BciRejectionsBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public BciRejectionsVO(String source) {
		super(BciRejectionsBO.TABLE, BciRejectionsBO.SYSTEM, BciRejectionsBO.CORIS_DISTRICT_DB.setSource("D"), BciRejectionsBO.CORIS_JUSTICE_DB.setSource("J"), BciRejectionsBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BciRejectionsBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(rejectionDate);
		this.getPropertyList().add(seq);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(BciRejectionsBO.REJECTIONDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(BciRejectionsBO.REJECTIONDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
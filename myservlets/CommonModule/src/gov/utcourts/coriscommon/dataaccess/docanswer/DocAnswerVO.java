package gov.utcourts.coriscommon.dataaccess.docanswer;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DocAnswerVO extends BaseVO { 

	private TypeInteger documentNum = new TypeInteger("document_num").setFieldDescriptor(DocAnswerBO.DOCUMENTNUM.clear()).addForeignKey("document","document_num",false).setAsPrimaryKey();
	private TypeBigDecimal amt = new TypeBigDecimal("amt").setFieldDescriptor(DocAnswerBO.AMT.clear()).setNullable();
	private TypeDate noticeDate = new TypeDate("notice_date").setFieldDescriptor(DocAnswerBO.NOTICEDATE.clear()).setNullable();

	public DocAnswerVO() {
		super(DocAnswerBO.TABLE, DocAnswerBO.SYSTEM, DocAnswerBO.CORIS_DISTRICT_DB.setSource("D"), DocAnswerBO.CORIS_JUSTICE_DB.setSource("J"), DocAnswerBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocAnswerBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DocAnswerVO(String source) {
		super(DocAnswerBO.TABLE, DocAnswerBO.SYSTEM, DocAnswerBO.CORIS_DISTRICT_DB.setSource("D"), DocAnswerBO.CORIS_JUSTICE_DB.setSource("J"), DocAnswerBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocAnswerBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(documentNum);
		this.getPropertyList().add(amt);
		this.getPropertyList().add(noticeDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(DocAnswerBO.NOTICEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DocAnswerBO.NOTICEDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
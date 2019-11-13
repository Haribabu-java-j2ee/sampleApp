package gov.utcourts.coriscommon.dataaccess.jdmtroll;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class JdmtRollVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(JdmtRollBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false);
	private TypeString documentTitle = new TypeString("document_title").setFieldDescriptor(JdmtRollBO.DOCUMENTTITLE.clear());
	private TypeDate entryDatetime = new TypeDate("entry_datetime").setFieldDescriptor(JdmtRollBO.ENTRYDATETIME.clear()).setNullable();
	private TypeString pageNum = new TypeString("page_num").setFieldDescriptor(JdmtRollBO.PAGENUM.clear()).setNullable();
	private TypeInteger documentNum = new TypeInteger("document_num").setFieldDescriptor(JdmtRollBO.DOCUMENTNUM.clear()).setNullable();
	private TypeInteger sortSeq = new TypeInteger("sort_seq").setFieldDescriptor(JdmtRollBO.SORTSEQ.clear()).setNullable();
	private TypeInteger meId = new TypeInteger("me_id").setFieldDescriptor(JdmtRollBO.MEID.clear()).setNullable();

	public JdmtRollVO() {
		super(JdmtRollBO.TABLE, JdmtRollBO.SYSTEM, JdmtRollBO.CORIS_DISTRICT_DB.setSource("D"), JdmtRollBO.CORIS_JUSTICE_DB.setSource("J"), JdmtRollBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JdmtRollBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public JdmtRollVO(String source) {
		super(JdmtRollBO.TABLE, JdmtRollBO.SYSTEM, JdmtRollBO.CORIS_DISTRICT_DB.setSource("D"), JdmtRollBO.CORIS_JUSTICE_DB.setSource("J"), JdmtRollBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JdmtRollBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(documentTitle);
		this.getPropertyList().add(entryDatetime);
		this.getPropertyList().add(pageNum);
		this.getPropertyList().add(documentNum);
		this.getPropertyList().add(sortSeq);
		this.getPropertyList().add(meId);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(JdmtRollBO.ENTRYDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(JdmtRollBO.ENTRYDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
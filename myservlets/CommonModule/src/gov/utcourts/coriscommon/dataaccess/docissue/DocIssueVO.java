package gov.utcourts.coriscommon.dataaccess.docissue;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DocIssueVO extends BaseVO { 

	private TypeInteger documentNum = new TypeInteger("document_num").setFieldDescriptor(DocIssueBO.DOCUMENTNUM.clear()).addForeignKey("document","document_num",false).setAsPrimaryKey();
	private TypeInteger judgeCommId = new TypeInteger("judge_comm_id").setFieldDescriptor(DocIssueBO.JUDGECOMMID.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeDate returnDatetime = new TypeDate("return_datetime").setFieldDescriptor(DocIssueBO.RETURNDATETIME.clear()).setNullable();

	public DocIssueVO() {
		super(DocIssueBO.TABLE, DocIssueBO.SYSTEM, DocIssueBO.CORIS_DISTRICT_DB.setSource("D"), DocIssueBO.CORIS_JUSTICE_DB.setSource("J"), DocIssueBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocIssueBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DocIssueVO(String source) {
		super(DocIssueBO.TABLE, DocIssueBO.SYSTEM, DocIssueBO.CORIS_DISTRICT_DB.setSource("D"), DocIssueBO.CORIS_JUSTICE_DB.setSource("J"), DocIssueBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocIssueBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(documentNum);
		this.getPropertyList().add(judgeCommId);
		this.getPropertyList().add(returnDatetime);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(DocIssueBO.RETURNDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(DocIssueBO.RETURNDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
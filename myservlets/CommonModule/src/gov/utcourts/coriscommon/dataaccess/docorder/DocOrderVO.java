package gov.utcourts.coriscommon.dataaccess.docorder;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DocOrderVO extends BaseVO { 

	private TypeInteger documentNum = new TypeInteger("document_num").setFieldDescriptor(DocOrderBO.DOCUMENTNUM.clear()).addForeignKey("document","document_num",false).setAsPrimaryKey();
	private TypeInteger judgeCommId = new TypeInteger("judge_comm_id").setFieldDescriptor(DocOrderBO.JUDGECOMMID.clear()).addForeignKey("personnel","userid_srl",false);
	private TypeDate signedDate = new TypeDate("signed_date").setFieldDescriptor(DocOrderBO.SIGNEDDATE.clear());

	public DocOrderVO() {
		super(DocOrderBO.TABLE, DocOrderBO.SYSTEM, DocOrderBO.CORIS_DISTRICT_DB.setSource("D"), DocOrderBO.CORIS_JUSTICE_DB.setSource("J"), DocOrderBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocOrderBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DocOrderVO(String source) {
		super(DocOrderBO.TABLE, DocOrderBO.SYSTEM, DocOrderBO.CORIS_DISTRICT_DB.setSource("D"), DocOrderBO.CORIS_JUSTICE_DB.setSource("J"), DocOrderBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocOrderBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(documentNum);
		this.getPropertyList().add(judgeCommId);
		this.getPropertyList().add(signedDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(DocOrderBO.SIGNEDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DocOrderBO.SIGNEDDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
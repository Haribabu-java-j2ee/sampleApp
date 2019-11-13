package gov.utcourts.coriscommon.dataaccess.docdd;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DocDdVO extends BaseVO { 

	private TypeInteger documentNum = new TypeInteger("document_num").setFieldDescriptor(DocDdBO.DOCUMENTNUM.clear()).addForeignKey("document","document_num",false).setAsPrimaryKey();
	private TypeString ddType = new TypeString("dd_type").setFieldDescriptor(DocDdBO.DDTYPE.clear()).setNullable();
	private TypeInteger ddId = new TypeInteger("dd_id").setFieldDescriptor(DocDdBO.DDID.clear()).setNullable();
	private TypeString ddStatus = new TypeString("dd_status").setFieldDescriptor(DocDdBO.DDSTATUS.clear()).setNullable();
	private TypeDate ddStatusChangeDate = new TypeDate("dd_status_change_date").setFieldDescriptor(DocDdBO.DDSTATUSCHANGEDATE.clear()).setNullable();

	public DocDdVO() {
		super(DocDdBO.TABLE, DocDdBO.SYSTEM, DocDdBO.CORIS_DISTRICT_DB.setSource("D"), DocDdBO.CORIS_JUSTICE_DB.setSource("J"), DocDdBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocDdBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DocDdVO(String source) {
		super(DocDdBO.TABLE, DocDdBO.SYSTEM, DocDdBO.CORIS_DISTRICT_DB.setSource("D"), DocDdBO.CORIS_JUSTICE_DB.setSource("J"), DocDdBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocDdBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(documentNum);
		this.getPropertyList().add(ddType);
		this.getPropertyList().add(ddId);
		this.getPropertyList().add(ddStatus);
		this.getPropertyList().add(ddStatusChangeDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(DocDdBO.DDSTATUSCHANGEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DocDdBO.DDSTATUSCHANGEDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
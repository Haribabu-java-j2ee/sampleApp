package gov.utcourts.coriscommon.dataaccess.docreturn;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DocReturnVO extends BaseVO { 

	private TypeInteger documentNum = new TypeInteger("document_num").setFieldDescriptor(DocReturnBO.DOCUMENTNUM.clear()).addForeignKey("document","document_num",false).setAsPrimaryKey();
	private TypeString serviceType = new TypeString("service_type").setFieldDescriptor(DocReturnBO.SERVICETYPE.clear()).setNullable();
	private TypeString partyServed = new TypeString("party_served").setFieldDescriptor(DocReturnBO.PARTYSERVED.clear()).setNullable();
	private TypeString garnishee = new TypeString("garnishee").setFieldDescriptor(DocReturnBO.GARNISHEE.clear()).setNullable();
	private TypeDate serviceDate = new TypeDate("service_date").setFieldDescriptor(DocReturnBO.SERVICEDATE.clear()).setNullable();

	public DocReturnVO() {
		super(DocReturnBO.TABLE, DocReturnBO.SYSTEM, DocReturnBO.CORIS_DISTRICT_DB.setSource("D"), DocReturnBO.CORIS_JUSTICE_DB.setSource("J"), DocReturnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocReturnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DocReturnVO(String source) {
		super(DocReturnBO.TABLE, DocReturnBO.SYSTEM, DocReturnBO.CORIS_DISTRICT_DB.setSource("D"), DocReturnBO.CORIS_JUSTICE_DB.setSource("J"), DocReturnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocReturnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(documentNum);
		this.getPropertyList().add(serviceType);
		this.getPropertyList().add(partyServed);
		this.getPropertyList().add(garnishee);
		this.getPropertyList().add(serviceDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(DocReturnBO.SERVICEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DocReturnBO.SERVICEDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
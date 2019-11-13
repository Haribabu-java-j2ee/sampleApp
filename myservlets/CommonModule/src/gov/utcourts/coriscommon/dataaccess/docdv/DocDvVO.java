package gov.utcourts.coriscommon.dataaccess.docdv;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DocDvVO extends BaseVO { 

	private TypeInteger documentNum = new TypeInteger("document_num").setFieldDescriptor(DocDvBO.DOCUMENTNUM.clear()).addForeignKey("document","document_num",false).setAsPrimaryKey();
	private TypeString dvOrderCode = new TypeString("dv_order_code").setFieldDescriptor(DocDvBO.DVORDERCODE.clear()).setNullable();
	private TypeInteger poId = new TypeInteger("po_id").setFieldDescriptor(DocDvBO.POID.clear()).setNullable();
	private TypeString poStatus = new TypeString("po_status").setFieldDescriptor(DocDvBO.POSTATUS.clear()).setNullable();
	private TypeDate poStatusChangeDate = new TypeDate("po_status_change_date").setFieldDescriptor(DocDvBO.POSTATUSCHANGEDATE.clear()).setNullable();

	public DocDvVO() {
		super(DocDvBO.TABLE, DocDvBO.SYSTEM, DocDvBO.CORIS_DISTRICT_DB.setSource("D"), DocDvBO.CORIS_JUSTICE_DB.setSource("J"), DocDvBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocDvBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DocDvVO(String source) {
		super(DocDvBO.TABLE, DocDvBO.SYSTEM, DocDvBO.CORIS_DISTRICT_DB.setSource("D"), DocDvBO.CORIS_JUSTICE_DB.setSource("J"), DocDvBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocDvBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(documentNum);
		this.getPropertyList().add(dvOrderCode);
		this.getPropertyList().add(poId);
		this.getPropertyList().add(poStatus);
		this.getPropertyList().add(poStatusChangeDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(DocDvBO.POSTATUSCHANGEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DocDvBO.POSTATUSCHANGEDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
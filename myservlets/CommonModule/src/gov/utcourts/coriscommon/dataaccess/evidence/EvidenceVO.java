package gov.utcourts.coriscommon.dataaccess.evidence;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class EvidenceVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(EvidenceBO.INTCASENUM.clear()).addForeignKey("evidence_hist","int_case_num",false).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(EvidenceBO.SEQ.clear()).addForeignKey("evidence_hist","seq",false).setAsPrimaryKey();
	private TypeDate recvdDate = new TypeDate("recvd_date").setFieldDescriptor(EvidenceBO.RECVDDATE.clear()).setNullable();
	private TypeString containerDescr = new TypeString("container_descr").setFieldDescriptor(EvidenceBO.CONTAINERDESCR.clear()).setNullable();
	private TypeString storageLocn = new TypeString("storage_locn").setFieldDescriptor(EvidenceBO.STORAGELOCN.clear()).setNullable();
	private TypeDate disposalDate = new TypeDate("disposal_date").setFieldDescriptor(EvidenceBO.DISPOSALDATE.clear()).setNullable();
	private TypeInteger disposalClerk = new TypeInteger("disposal_clerk").setFieldDescriptor(EvidenceBO.DISPOSALCLERK.clear()).setNullable();
	private TypeString disposalAction = new TypeString("disposal_action").setFieldDescriptor(EvidenceBO.DISPOSALACTION.clear()).setNullable();

	public EvidenceVO() {
		super(EvidenceBO.TABLE, EvidenceBO.SYSTEM, EvidenceBO.CORIS_DISTRICT_DB.setSource("D"), EvidenceBO.CORIS_JUSTICE_DB.setSource("J"), EvidenceBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EvidenceBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public EvidenceVO(String source) {
		super(EvidenceBO.TABLE, EvidenceBO.SYSTEM, EvidenceBO.CORIS_DISTRICT_DB.setSource("D"), EvidenceBO.CORIS_JUSTICE_DB.setSource("J"), EvidenceBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EvidenceBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(recvdDate);
		this.getPropertyList().add(containerDescr);
		this.getPropertyList().add(storageLocn);
		this.getPropertyList().add(disposalDate);
		this.getPropertyList().add(disposalClerk);
		this.getPropertyList().add(disposalAction);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(EvidenceBO.RECVDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(EvidenceBO.RECVDDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(EvidenceBO.DISPOSALDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(EvidenceBO.DISPOSALDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
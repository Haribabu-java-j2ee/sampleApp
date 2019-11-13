package gov.utcourts.coriscommon.dataaccess.casemedocument;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CaseMeDocumentVO extends BaseVO { 

	private TypeInteger meId = new TypeInteger("me_id").setFieldDescriptor(CaseMeDocumentBO.MEID.clear()).addForeignKey("case_me","me_id",false);
	private TypeString meType = new TypeString("me_type").setFieldDescriptor(CaseMeDocumentBO.METYPE.clear());
	private TypeString signatureStatus = new TypeString("signature_status").setFieldDescriptor(CaseMeDocumentBO.SIGNATURESTATUS.clear()).setNullable();
	private TypeString signatureType = new TypeString("signature_type").setFieldDescriptor(CaseMeDocumentBO.SIGNATURETYPE.clear()).setNullable();
	private TypeInteger signatureUseridSrl = new TypeInteger("signature_userid_srl").setFieldDescriptor(CaseMeDocumentBO.SIGNATUREUSERIDSRL.clear()).setNullable();
	private TypeInteger documentNum = new TypeInteger("document_num").setFieldDescriptor(CaseMeDocumentBO.DOCUMENTNUM.clear()).addForeignKey("document","document_num",false).setNullable();
	private TypeString detailCode = new TypeString("detail_code").setFieldDescriptor(CaseMeDocumentBO.DETAILCODE.clear()).setNullable();

	public CaseMeDocumentVO() {
		super(CaseMeDocumentBO.TABLE, CaseMeDocumentBO.SYSTEM, CaseMeDocumentBO.CORIS_DISTRICT_DB.setSource("D"), CaseMeDocumentBO.CORIS_JUSTICE_DB.setSource("J"), CaseMeDocumentBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseMeDocumentBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CaseMeDocumentVO(String source) {
		super(CaseMeDocumentBO.TABLE, CaseMeDocumentBO.SYSTEM, CaseMeDocumentBO.CORIS_DISTRICT_DB.setSource("D"), CaseMeDocumentBO.CORIS_JUSTICE_DB.setSource("J"), CaseMeDocumentBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseMeDocumentBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(meId);
		this.getPropertyList().add(meType);
		this.getPropertyList().add(signatureStatus);
		this.getPropertyList().add(signatureType);
		this.getPropertyList().add(signatureUseridSrl);
		this.getPropertyList().add(documentNum);
		this.getPropertyList().add(detailCode);
	}

}
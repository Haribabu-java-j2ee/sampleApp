package gov.utcourts.coriscommon.dataaccess.docansparty;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DocAnsPartyVO extends BaseVO { 

	private TypeInteger documentNum = new TypeInteger("document_num").setFieldDescriptor(DocAnsPartyBO.DOCUMENTNUM.clear()).setAsPrimaryKey();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(DocAnsPartyBO.PARTYNUM.clear()).setAsPrimaryKey();

	public DocAnsPartyVO() {
		super(DocAnsPartyBO.TABLE, DocAnsPartyBO.SYSTEM, DocAnsPartyBO.CORIS_DISTRICT_DB.setSource("D"), DocAnsPartyBO.CORIS_JUSTICE_DB.setSource("J"), DocAnsPartyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocAnsPartyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DocAnsPartyVO(String source) {
		super(DocAnsPartyBO.TABLE, DocAnsPartyBO.SYSTEM, DocAnsPartyBO.CORIS_DISTRICT_DB.setSource("D"), DocAnsPartyBO.CORIS_JUSTICE_DB.setSource("J"), DocAnsPartyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocAnsPartyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(documentNum);
		this.getPropertyList().add(partyNum);
	}

}
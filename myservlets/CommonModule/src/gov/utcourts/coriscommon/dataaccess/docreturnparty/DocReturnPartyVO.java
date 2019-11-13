package gov.utcourts.coriscommon.dataaccess.docreturnparty;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DocReturnPartyVO extends BaseVO { 

	private TypeInteger documentNum = new TypeInteger("document_num").setFieldDescriptor(DocReturnPartyBO.DOCUMENTNUM.clear()).addForeignKey("document","document_num",false).setAsPrimaryKey();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(DocReturnPartyBO.PARTYNUM.clear()).addForeignKey("party","party_num",false).setAsPrimaryKey();

	public DocReturnPartyVO() {
		super(DocReturnPartyBO.TABLE, DocReturnPartyBO.SYSTEM, DocReturnPartyBO.CORIS_DISTRICT_DB.setSource("D"), DocReturnPartyBO.CORIS_JUSTICE_DB.setSource("J"), DocReturnPartyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocReturnPartyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DocReturnPartyVO(String source) {
		super(DocReturnPartyBO.TABLE, DocReturnPartyBO.SYSTEM, DocReturnPartyBO.CORIS_DISTRICT_DB.setSource("D"), DocReturnPartyBO.CORIS_JUSTICE_DB.setSource("J"), DocReturnPartyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocReturnPartyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(documentNum);
		this.getPropertyList().add(partyNum);
	}

}
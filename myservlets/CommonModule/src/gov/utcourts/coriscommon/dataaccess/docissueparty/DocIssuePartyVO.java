package gov.utcourts.coriscommon.dataaccess.docissueparty;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DocIssuePartyVO extends BaseVO { 

	private TypeInteger documentNum = new TypeInteger("document_num").setFieldDescriptor(DocIssuePartyBO.DOCUMENTNUM.clear()).addForeignKey("document","document_num",false).setAsPrimaryKey();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(DocIssuePartyBO.PARTYNUM.clear()).addForeignKey("party","party_num",false).setAsPrimaryKey();

	public DocIssuePartyVO() {
		super(DocIssuePartyBO.TABLE, DocIssuePartyBO.SYSTEM, DocIssuePartyBO.CORIS_DISTRICT_DB.setSource("D"), DocIssuePartyBO.CORIS_JUSTICE_DB.setSource("J"), DocIssuePartyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocIssuePartyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DocIssuePartyVO(String source) {
		super(DocIssuePartyBO.TABLE, DocIssuePartyBO.SYSTEM, DocIssuePartyBO.CORIS_DISTRICT_DB.setSource("D"), DocIssuePartyBO.CORIS_JUSTICE_DB.setSource("J"), DocIssuePartyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocIssuePartyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(documentNum);
		this.getPropertyList().add(partyNum);
	}

}
package gov.utcourts.coriscommon.dataaccess.docjudgmentinfo;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DocJudgmentInfoVO extends BaseVO { 

	private TypeInteger documentNum = new TypeInteger("document_num").setFieldDescriptor(DocJudgmentInfoBO.DOCUMENTNUM.clear()).addForeignKey("document","document_num",false).setAsPrimaryKey();
	private TypeInteger debtorPartyNum = new TypeInteger("debtor_party_num").setFieldDescriptor(DocJudgmentInfoBO.DEBTORPARTYNUM.clear()).addForeignKey("party","party_num",false);
	private TypeBigDecimal judgmentAmt = new TypeBigDecimal("judgment_amt").setFieldDescriptor(DocJudgmentInfoBO.JUDGMENTAMT.clear());

	public DocJudgmentInfoVO() {
		super(DocJudgmentInfoBO.TABLE, DocJudgmentInfoBO.SYSTEM, DocJudgmentInfoBO.CORIS_DISTRICT_DB.setSource("D"), DocJudgmentInfoBO.CORIS_JUSTICE_DB.setSource("J"), DocJudgmentInfoBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocJudgmentInfoBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DocJudgmentInfoVO(String source) {
		super(DocJudgmentInfoBO.TABLE, DocJudgmentInfoBO.SYSTEM, DocJudgmentInfoBO.CORIS_DISTRICT_DB.setSource("D"), DocJudgmentInfoBO.CORIS_JUSTICE_DB.setSource("J"), DocJudgmentInfoBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocJudgmentInfoBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(documentNum);
		this.getPropertyList().add(debtorPartyNum);
		this.getPropertyList().add(judgmentAmt);
	}

}
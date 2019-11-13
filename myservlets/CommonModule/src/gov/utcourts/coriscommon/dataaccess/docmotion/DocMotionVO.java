package gov.utcourts.coriscommon.dataaccess.docmotion;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DocMotionVO extends BaseVO { 

	private TypeInteger documentNum = new TypeInteger("document_num").setFieldDescriptor(DocMotionBO.DOCUMENTNUM.clear()).addForeignKey("document","document_num",false).setAsPrimaryKey();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(DocMotionBO.PARTYNUM.clear()).addForeignKey("party","party_num",false);

	public DocMotionVO() {
		super(DocMotionBO.TABLE, DocMotionBO.SYSTEM, DocMotionBO.CORIS_DISTRICT_DB.setSource("D"), DocMotionBO.CORIS_JUSTICE_DB.setSource("J"), DocMotionBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocMotionBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DocMotionVO(String source) {
		super(DocMotionBO.TABLE, DocMotionBO.SYSTEM, DocMotionBO.CORIS_DISTRICT_DB.setSource("D"), DocMotionBO.CORIS_JUSTICE_DB.setSource("J"), DocMotionBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocMotionBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(documentNum);
		this.getPropertyList().add(partyNum);
	}

}
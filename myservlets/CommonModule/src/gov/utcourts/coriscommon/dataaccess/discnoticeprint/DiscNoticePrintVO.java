package gov.utcourts.coriscommon.dataaccess.discnoticeprint;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DiscNoticePrintVO extends BaseVO { 

	private TypeInteger documentNum = new TypeInteger("document_num").setFieldDescriptor(DiscNoticePrintBO.DOCUMENTNUM.clear()).addForeignKey("document","document_num",false).setNullable().setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(DiscNoticePrintBO.SEQ.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(DiscNoticePrintBO.PARTYNUM.clear()).addForeignKey("party","party_num",false).setNullable();
	private TypeInteger barNum = new TypeInteger("bar_num").setFieldDescriptor(DiscNoticePrintBO.BARNUM.clear()).addForeignKey("attorney","bar_num",false).setNullable();
	private TypeString barState = new TypeString("bar_state").setFieldDescriptor(DiscNoticePrintBO.BARSTATE.clear()).addForeignKey("attorney","bar_state",false).setNullable();

	public DiscNoticePrintVO() {
		super(DiscNoticePrintBO.TABLE, DiscNoticePrintBO.SYSTEM, DiscNoticePrintBO.CORIS_DISTRICT_DB.setSource("D"), DiscNoticePrintBO.CORIS_JUSTICE_DB.setSource("J"), DiscNoticePrintBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DiscNoticePrintBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DiscNoticePrintVO(String source) {
		super(DiscNoticePrintBO.TABLE, DiscNoticePrintBO.SYSTEM, DiscNoticePrintBO.CORIS_DISTRICT_DB.setSource("D"), DiscNoticePrintBO.CORIS_JUSTICE_DB.setSource("J"), DiscNoticePrintBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DiscNoticePrintBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(documentNum);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(partyNum);
		this.getPropertyList().add(barNum);
		this.getPropertyList().add(barState);
	}

}
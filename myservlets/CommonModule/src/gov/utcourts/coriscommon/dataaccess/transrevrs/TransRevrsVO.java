package gov.utcourts.coriscommon.dataaccess.transrevrs;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class TransRevrsVO extends BaseVO { 

	private TypeInteger intJournalNum = new TypeInteger("int_journal_num").setFieldDescriptor(TransRevrsBO.INTJOURNALNUM.clear()).addForeignKey("trans","int_journal_num",false).setAsPrimaryKey();
	private TypeInteger transNum = new TypeInteger("trans_num").setFieldDescriptor(TransRevrsBO.TRANSNUM.clear()).addForeignKey("trans","trans_num",false).setAsPrimaryKey();
	private TypeInteger revrsJournalNum = new TypeInteger("revrs_journal_num").setFieldDescriptor(TransRevrsBO.REVRSJOURNALNUM.clear()).addForeignKey("trans","int_journal_num",false);
	private TypeInteger revrsTransNum = new TypeInteger("revrs_trans_num").setFieldDescriptor(TransRevrsBO.REVRSTRANSNUM.clear()).addForeignKey("trans","trans_num",false);

	public TransRevrsVO() {
		super(TransRevrsBO.TABLE, TransRevrsBO.SYSTEM, TransRevrsBO.CORIS_DISTRICT_DB.setSource("D"), TransRevrsBO.CORIS_JUSTICE_DB.setSource("J"), TransRevrsBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TransRevrsBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public TransRevrsVO(String source) {
		super(TransRevrsBO.TABLE, TransRevrsBO.SYSTEM, TransRevrsBO.CORIS_DISTRICT_DB.setSource("D"), TransRevrsBO.CORIS_JUSTICE_DB.setSource("J"), TransRevrsBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TransRevrsBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intJournalNum);
		this.getPropertyList().add(transNum);
		this.getPropertyList().add(revrsJournalNum);
		this.getPropertyList().add(revrsTransNum);
	}

}
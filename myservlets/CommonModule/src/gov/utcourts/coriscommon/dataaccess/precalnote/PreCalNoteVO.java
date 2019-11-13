package gov.utcourts.coriscommon.dataaccess.precalnote;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PreCalNoteVO extends BaseVO { 

	private TypeInteger noteNum = new TypeInteger("note_num").setFieldDescriptor(PreCalNoteBO.NOTENUM.clear()).setAsPrimaryKey();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(PreCalNoteBO.LOCNCODE.clear()).addForeignKey("profile","locn_code",false).setNullable();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(PreCalNoteBO.COURTTYPE.clear()).addForeignKey("profile","court_type",false).setNullable();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(PreCalNoteBO.DESCR.clear());
	private TypeString note = new TypeString("note").setFieldDescriptor(PreCalNoteBO.NOTE.clear());

	public PreCalNoteVO() {
		super(PreCalNoteBO.TABLE, PreCalNoteBO.SYSTEM, PreCalNoteBO.CORIS_DISTRICT_DB.setSource("D"), PreCalNoteBO.CORIS_JUSTICE_DB.setSource("J"), PreCalNoteBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PreCalNoteBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PreCalNoteVO(String source) {
		super(PreCalNoteBO.TABLE, PreCalNoteBO.SYSTEM, PreCalNoteBO.CORIS_DISTRICT_DB.setSource("D"), PreCalNoteBO.CORIS_JUSTICE_DB.setSource("J"), PreCalNoteBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PreCalNoteBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(noteNum);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(note);
	}

}
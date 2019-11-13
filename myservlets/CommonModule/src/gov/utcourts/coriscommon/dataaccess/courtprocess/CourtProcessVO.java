package gov.utcourts.coriscommon.dataaccess.courtprocess;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CourtProcessVO extends BaseVO { 

	private TypeInteger processId = new TypeInteger("process_id").setFieldDescriptor(CourtProcessBO.PROCESSID.clear()).addForeignKey("process_type","process_id",false).setNullable().setAsPrimaryKey();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(CourtProcessBO.LOCNCODE.clear()).addForeignKey("profile","locn_code",false).setNullable().setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(CourtProcessBO.COURTTYPE.clear()).addForeignKey("profile","court_type",false).setNullable().setAsPrimaryKey();
	private TypeString value = new TypeString("value").setFieldDescriptor(CourtProcessBO.VALUE.clear()).setNullable();
	private TypeString courtNote = new TypeString("court_note").setFieldDescriptor(CourtProcessBO.COURTNOTE.clear()).setNullable();

	public CourtProcessVO() {
		super(CourtProcessBO.TABLE, CourtProcessBO.SYSTEM, CourtProcessBO.CORIS_DISTRICT_DB.setSource("D"), CourtProcessBO.CORIS_JUSTICE_DB.setSource("J"), CourtProcessBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CourtProcessBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CourtProcessVO(String source) {
		super(CourtProcessBO.TABLE, CourtProcessBO.SYSTEM, CourtProcessBO.CORIS_DISTRICT_DB.setSource("D"), CourtProcessBO.CORIS_JUSTICE_DB.setSource("J"), CourtProcessBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CourtProcessBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(processId);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(value);
		this.getPropertyList().add(courtNote);
	}

}
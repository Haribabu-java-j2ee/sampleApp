package gov.utcourts.coriscommon.dataaccess.interpreter;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class InterpreterVO extends BaseVO { 

	private TypeString lastName = new TypeString("last_name").setFieldDescriptor(InterpreterBO.LASTNAME.clear());
	private TypeString firstName = new TypeString("first_name").setFieldDescriptor(InterpreterBO.FIRSTNAME.clear());
	private TypeInteger langId = new TypeInteger("lang_id").setFieldDescriptor(InterpreterBO.LANGID.clear()).addForeignKey("language","lang_id",false);
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(InterpreterBO.LOCNCODE.clear()).addForeignKey("profile","locn_code",false);
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(InterpreterBO.COURTTYPE.clear()).addForeignKey("profile","court_type",false).setNullable();

	public InterpreterVO() {
		super(InterpreterBO.TABLE, InterpreterBO.SYSTEM, InterpreterBO.CORIS_DISTRICT_DB.setSource("D"), InterpreterBO.CORIS_JUSTICE_DB.setSource("J"), InterpreterBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), InterpreterBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public InterpreterVO(String source) {
		super(InterpreterBO.TABLE, InterpreterBO.SYSTEM, InterpreterBO.CORIS_DISTRICT_DB.setSource("D"), InterpreterBO.CORIS_JUSTICE_DB.setSource("J"), InterpreterBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), InterpreterBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(lastName);
		this.getPropertyList().add(firstName);
		this.getPropertyList().add(langId);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
	}

}
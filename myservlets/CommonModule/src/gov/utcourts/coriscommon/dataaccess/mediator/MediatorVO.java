package gov.utcourts.coriscommon.dataaccess.mediator;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class MediatorVO extends BaseVO { 

	private TypeInteger mediatorSrl = new TypeInteger("mediator_srl").setFieldDescriptor(MediatorBO.MEDIATORSRL.clear()).addForeignKey("mediation","mediator_id",true).setAsPrimaryKey();
	private TypeString lastName = new TypeString("last_name").setFieldDescriptor(MediatorBO.LASTNAME.clear());
	private TypeString firstName = new TypeString("first_name").setFieldDescriptor(MediatorBO.FIRSTNAME.clear());
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(MediatorBO.COURTTYPE.clear());
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(MediatorBO.LOCNCODE.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(MediatorBO.REMOVEDFLAG.clear());

	public MediatorVO() {
		super(MediatorBO.TABLE, MediatorBO.SYSTEM, MediatorBO.CORIS_DISTRICT_DB.setSource("D"), MediatorBO.CORIS_JUSTICE_DB.setSource("J"), MediatorBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), MediatorBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public MediatorVO(String source) {
		super(MediatorBO.TABLE, MediatorBO.SYSTEM, MediatorBO.CORIS_DISTRICT_DB.setSource("D"), MediatorBO.CORIS_JUSTICE_DB.setSource("J"), MediatorBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), MediatorBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(mediatorSrl);
		this.getPropertyList().add(lastName);
		this.getPropertyList().add(firstName);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(removedFlag);
	}

}
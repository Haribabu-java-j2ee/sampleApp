package gov.utcourts.coriscommon.dataaccess.sysbldobjects;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBoolean;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeLong;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;
import gov.utcourts.courtscommon.dataaccess.types.TypeText;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SysbldobjectsVO extends BaseVO { 

	private TypeLong createOrder = new TypeLong("create_order").setFieldDescriptor(SysbldobjectsBO.CREATEORDER.clear());
	private TypeString bldId = new TypeString("bld_id").setFieldDescriptor(SysbldobjectsBO.BLDID.clear());
	private TypeInteger objKind = new TypeInteger("obj_kind").setFieldDescriptor(SysbldobjectsBO.OBJKIND.clear()).setNullable();
	private TypeText objSignature = new TypeText("obj_signature").setFieldDescriptor(SysbldobjectsBO.OBJSIGNATURE.clear());
	private TypeString objOwner = new TypeString("obj_owner").setFieldDescriptor(SysbldobjectsBO.OBJOWNER.clear()).setNullable();
	private TypeInteger sequence = new TypeInteger("sequence").setFieldDescriptor(SysbldobjectsBO.SEQUENCE.clear()).setNullable();
	private TypeText createSql = new TypeText("create_sql").setFieldDescriptor(SysbldobjectsBO.CREATESQL.clear()).setNullable();
	private TypeBoolean createCanFail = new TypeBoolean("create_can_fail").setFieldDescriptor(SysbldobjectsBO.CREATECANFAIL.clear()).setNullable();
	private TypeText dropSql = new TypeText("drop_sql").setFieldDescriptor(SysbldobjectsBO.DROPSQL.clear()).setNullable();
	private TypeBoolean dropCanFail = new TypeBoolean("drop_can_fail").setFieldDescriptor(SysbldobjectsBO.DROPCANFAIL.clear()).setNullable();
	private TypeText alterSql = new TypeText("alter_sql").setFieldDescriptor(SysbldobjectsBO.ALTERSQL.clear()).setNullable();
	private TypeBoolean alterCanFail = new TypeBoolean("alter_can_fail").setFieldDescriptor(SysbldobjectsBO.ALTERCANFAIL.clear()).setNullable();
	private TypeString state = new TypeString("state").setFieldDescriptor(SysbldobjectsBO.STATE.clear()).setNullable();
	private TypeString tempState = new TypeString("temp_state").setFieldDescriptor(SysbldobjectsBO.TEMPSTATE.clear()).setNullable();
	private TypeString finalState = new TypeString("final_state").setFieldDescriptor(SysbldobjectsBO.FINALSTATE.clear()).setNullable();
	private TypeText metadata = new TypeText("metadata").setFieldDescriptor(SysbldobjectsBO.METADATA.clear()).setNullable();
	private TypeString reserved1 = new TypeString("reserved1").setFieldDescriptor(SysbldobjectsBO.RESERVED1.clear()).setNullable();
	private TypeInteger reserved2 = new TypeInteger("reserved2").setFieldDescriptor(SysbldobjectsBO.RESERVED2.clear()).setNullable();
	private TypeInteger reserved3 = new TypeInteger("reserved3").setFieldDescriptor(SysbldobjectsBO.RESERVED3.clear()).setNullable();

	public SysbldobjectsVO() {
		super(SysbldobjectsBO.TABLE, SysbldobjectsBO.SYSTEM, SysbldobjectsBO.CORIS_DISTRICT_DB.setSource("D"), SysbldobjectsBO.CORIS_JUSTICE_DB.setSource("J"), SysbldobjectsBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SysbldobjectsBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SysbldobjectsVO(String source) {
		super(SysbldobjectsBO.TABLE, SysbldobjectsBO.SYSTEM, SysbldobjectsBO.CORIS_DISTRICT_DB.setSource("D"), SysbldobjectsBO.CORIS_JUSTICE_DB.setSource("J"), SysbldobjectsBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SysbldobjectsBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(createOrder);
		this.getPropertyList().add(bldId);
		this.getPropertyList().add(objKind);
		this.getPropertyList().add(objSignature);
		this.getPropertyList().add(objOwner);
		this.getPropertyList().add(sequence);
		this.getPropertyList().add(createSql);
		this.getPropertyList().add(createCanFail);
		this.getPropertyList().add(dropSql);
		this.getPropertyList().add(dropCanFail);
		this.getPropertyList().add(alterSql);
		this.getPropertyList().add(alterCanFail);
		this.getPropertyList().add(state);
		this.getPropertyList().add(tempState);
		this.getPropertyList().add(finalState);
		this.getPropertyList().add(metadata);
		this.getPropertyList().add(reserved1);
		this.getPropertyList().add(reserved2);
		this.getPropertyList().add(reserved3);
	}

}
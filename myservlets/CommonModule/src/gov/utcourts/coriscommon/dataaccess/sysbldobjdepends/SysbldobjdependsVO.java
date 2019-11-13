package gov.utcourts.coriscommon.dataaccess.sysbldobjdepends;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;
import gov.utcourts.courtscommon.dataaccess.types.TypeText;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SysbldobjdependsVO extends BaseVO { 

	private TypeString bldId = new TypeString("bld_id").setFieldDescriptor(SysbldobjdependsBO.BLDID.clear());
	private TypeInteger objKind = new TypeInteger("obj_kind").setFieldDescriptor(SysbldobjdependsBO.OBJKIND.clear()).setNullable();
	private TypeText objSignature = new TypeText("obj_signature").setFieldDescriptor(SysbldobjdependsBO.OBJSIGNATURE.clear());
	private TypeString objOwner = new TypeString("obj_owner").setFieldDescriptor(SysbldobjdependsBO.OBJOWNER.clear()).setNullable();
	private TypeInteger needObjKind = new TypeInteger("need_obj_kind").setFieldDescriptor(SysbldobjdependsBO.NEEDOBJKIND.clear()).setNullable();
	private TypeText needObjSignature = new TypeText("need_obj_signature").setFieldDescriptor(SysbldobjdependsBO.NEEDOBJSIGNATURE.clear());
	private TypeString needObjOwner = new TypeString("need_obj_owner").setFieldDescriptor(SysbldobjdependsBO.NEEDOBJOWNER.clear()).setNullable();
	private TypeString state = new TypeString("state").setFieldDescriptor(SysbldobjdependsBO.STATE.clear()).setNullable();
	private TypeString tempState = new TypeString("temp_state").setFieldDescriptor(SysbldobjdependsBO.TEMPSTATE.clear()).setNullable();
	private TypeString finalState = new TypeString("final_state").setFieldDescriptor(SysbldobjdependsBO.FINALSTATE.clear()).setNullable();
	private TypeString reserved1 = new TypeString("reserved1").setFieldDescriptor(SysbldobjdependsBO.RESERVED1.clear()).setNullable();
	private TypeInteger reserved2 = new TypeInteger("reserved2").setFieldDescriptor(SysbldobjdependsBO.RESERVED2.clear()).setNullable();
	private TypeInteger reserved3 = new TypeInteger("reserved3").setFieldDescriptor(SysbldobjdependsBO.RESERVED3.clear()).setNullable();

	public SysbldobjdependsVO() {
		super(SysbldobjdependsBO.TABLE, SysbldobjdependsBO.SYSTEM, SysbldobjdependsBO.CORIS_DISTRICT_DB.setSource("D"), SysbldobjdependsBO.CORIS_JUSTICE_DB.setSource("J"), SysbldobjdependsBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SysbldobjdependsBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SysbldobjdependsVO(String source) {
		super(SysbldobjdependsBO.TABLE, SysbldobjdependsBO.SYSTEM, SysbldobjdependsBO.CORIS_DISTRICT_DB.setSource("D"), SysbldobjdependsBO.CORIS_JUSTICE_DB.setSource("J"), SysbldobjdependsBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SysbldobjdependsBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(bldId);
		this.getPropertyList().add(objKind);
		this.getPropertyList().add(objSignature);
		this.getPropertyList().add(objOwner);
		this.getPropertyList().add(needObjKind);
		this.getPropertyList().add(needObjSignature);
		this.getPropertyList().add(needObjOwner);
		this.getPropertyList().add(state);
		this.getPropertyList().add(tempState);
		this.getPropertyList().add(finalState);
		this.getPropertyList().add(reserved1);
		this.getPropertyList().add(reserved2);
		this.getPropertyList().add(reserved3);
	}

}
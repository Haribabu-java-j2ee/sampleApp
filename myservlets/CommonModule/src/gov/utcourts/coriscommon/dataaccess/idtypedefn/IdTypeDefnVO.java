package gov.utcourts.coriscommon.dataaccess.idtypedefn;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class IdTypeDefnVO extends BaseVO { 

	private TypeInteger idTypeDefnId = new TypeInteger("id_type_defn_id").setFieldDescriptor(IdTypeDefnBO.IDTYPEDEFNID.clear()).addForeignKey("party_identifier","id_type_defn_id",false).setAsPrimaryKey();
	private TypeString typeCode = new TypeString("type_code").setFieldDescriptor(IdTypeDefnBO.TYPECODE.clear());
	private TypeString typeDescr = new TypeString("type_descr").setFieldDescriptor(IdTypeDefnBO.TYPEDESCR.clear());

	public IdTypeDefnVO() {
		super(IdTypeDefnBO.TABLE, IdTypeDefnBO.SYSTEM, IdTypeDefnBO.CORIS_DISTRICT_DB.setSource("D"), IdTypeDefnBO.CORIS_JUSTICE_DB.setSource("J"), IdTypeDefnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), IdTypeDefnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public IdTypeDefnVO(String source) {
		super(IdTypeDefnBO.TABLE, IdTypeDefnBO.SYSTEM, IdTypeDefnBO.CORIS_DISTRICT_DB.setSource("D"), IdTypeDefnBO.CORIS_JUSTICE_DB.setSource("J"), IdTypeDefnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), IdTypeDefnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(idTypeDefnId);
		this.getPropertyList().add(typeCode);
		this.getPropertyList().add(typeDescr);
	}

}
package gov.utcourts.coriscommon.dataaccess.nonmonbonddetl;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class NonmonBondDetlVO extends BaseVO { 

	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(NonmonBondDetlBO.ACCTNUM.clear()).addForeignKey("acct_bond","acct_num",false).setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(NonmonBondDetlBO.SEQ.clear()).setAsPrimaryKey();
	private TypeString type = new TypeString("type").setFieldDescriptor(NonmonBondDetlBO.TYPE.clear());
	private TypeString descr = new TypeString("descr").setFieldDescriptor(NonmonBondDetlBO.DESCR.clear()).setNullable();
	private TypeBigDecimal value = new TypeBigDecimal("value").setFieldDescriptor(NonmonBondDetlBO.VALUE.clear());

	public NonmonBondDetlVO() {
		super(NonmonBondDetlBO.TABLE, NonmonBondDetlBO.SYSTEM, NonmonBondDetlBO.CORIS_DISTRICT_DB.setSource("D"), NonmonBondDetlBO.CORIS_JUSTICE_DB.setSource("J"), NonmonBondDetlBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), NonmonBondDetlBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public NonmonBondDetlVO(String source) {
		super(NonmonBondDetlBO.TABLE, NonmonBondDetlBO.SYSTEM, NonmonBondDetlBO.CORIS_DISTRICT_DB.setSource("D"), NonmonBondDetlBO.CORIS_JUSTICE_DB.setSource("J"), NonmonBondDetlBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), NonmonBondDetlBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(acctNum);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(type);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(value);
	}

}
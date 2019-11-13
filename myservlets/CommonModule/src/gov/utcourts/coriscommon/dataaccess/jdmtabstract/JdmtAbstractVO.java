package gov.utcourts.coriscommon.dataaccess.jdmtabstract;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class JdmtAbstractVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(JdmtAbstractBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeInteger jdmtSeq = new TypeInteger("jdmt_seq").setFieldDescriptor(JdmtAbstractBO.JDMTSEQ.clear()).setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(JdmtAbstractBO.COURTTYPE.clear()).setAsPrimaryKey();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(JdmtAbstractBO.LOCNCODE.clear()).setAsPrimaryKey();
	private TypeString abstractCaseNum = new TypeString("abstract_case_num").setFieldDescriptor(JdmtAbstractBO.ABSTRACTCASENUM.clear());
	private TypeString dispCode = new TypeString("disp_code").setFieldDescriptor(JdmtAbstractBO.DISPCODE.clear()).setNullable();

	public JdmtAbstractVO() {
		super(JdmtAbstractBO.TABLE, JdmtAbstractBO.SYSTEM, JdmtAbstractBO.CORIS_DISTRICT_DB.setSource("D"), JdmtAbstractBO.CORIS_JUSTICE_DB.setSource("J"), JdmtAbstractBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JdmtAbstractBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public JdmtAbstractVO(String source) {
		super(JdmtAbstractBO.TABLE, JdmtAbstractBO.SYSTEM, JdmtAbstractBO.CORIS_DISTRICT_DB.setSource("D"), JdmtAbstractBO.CORIS_JUSTICE_DB.setSource("J"), JdmtAbstractBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JdmtAbstractBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(jdmtSeq);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(abstractCaseNum);
		this.getPropertyList().add(dispCode);
	}

}
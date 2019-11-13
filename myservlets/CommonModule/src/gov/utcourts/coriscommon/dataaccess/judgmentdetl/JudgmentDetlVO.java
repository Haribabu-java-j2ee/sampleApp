package gov.utcourts.coriscommon.dataaccess.judgmentdetl;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class JudgmentDetlVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(JudgmentDetlBO.INTCASENUM.clear()).addForeignKey("account_judgment_xref","int_case_num",false).addForeignKey("judgment","int_case_num",false).setAsPrimaryKey();
	private TypeInteger jdmtSeq = new TypeInteger("jdmt_seq").setFieldDescriptor(JudgmentDetlBO.JDMTSEQ.clear()).addForeignKey("account_judgment_xref","jdmt_seq",false).addForeignKey("judgment","jdmt_seq",false).setAsPrimaryKey();
	private TypeInteger detlSeq = new TypeInteger("detl_seq").setFieldDescriptor(JudgmentDetlBO.DETLSEQ.clear()).addForeignKey("account_judgment_xref","detl_seq",false).setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(JudgmentDetlBO.SEQ.clear()).addForeignKey("account_judgment_xref","seq",false).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(JudgmentDetlBO.DESCR.clear());
	private TypeBigDecimal amt = new TypeBigDecimal("amt").setFieldDescriptor(JudgmentDetlBO.AMT.clear());
	private TypeString dispCode = new TypeString("disp_code").setFieldDescriptor(JudgmentDetlBO.DISPCODE.clear()).addForeignKey("disp_type","disp_code",false).setNullable();
	private TypeDate dispDate = new TypeDate("disp_date").setFieldDescriptor(JudgmentDetlBO.DISPDATE.clear()).setNullable();

	public JudgmentDetlVO() {
		super(JudgmentDetlBO.TABLE, JudgmentDetlBO.SYSTEM, JudgmentDetlBO.CORIS_DISTRICT_DB.setSource("D"), JudgmentDetlBO.CORIS_JUSTICE_DB.setSource("J"), JudgmentDetlBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JudgmentDetlBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public JudgmentDetlVO(String source) {
		super(JudgmentDetlBO.TABLE, JudgmentDetlBO.SYSTEM, JudgmentDetlBO.CORIS_DISTRICT_DB.setSource("D"), JudgmentDetlBO.CORIS_JUSTICE_DB.setSource("J"), JudgmentDetlBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JudgmentDetlBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(jdmtSeq);
		this.getPropertyList().add(detlSeq);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(amt);
		this.getPropertyList().add(dispCode);
		this.getPropertyList().add(dispDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(JudgmentDetlBO.DISPDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(JudgmentDetlBO.DISPDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
package gov.utcourts.coriscommon.dataaccess.judgment;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class JudgmentVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(JudgmentBO.INTCASENUM.clear()).addForeignKey("judgment_detl","int_case_num",false).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeInteger jdmtSeq = new TypeInteger("jdmt_seq").setFieldDescriptor(JudgmentBO.JDMTSEQ.clear()).addForeignKey("judgment_detl","jdmt_seq",false).setAsPrimaryKey();
	private TypeDate filingDatetime = new TypeDate("filing_datetime").setFieldDescriptor(JudgmentBO.FILINGDATETIME.clear());
	private TypeDate jdmtDatetime = new TypeDate("jdmt_datetime").setFieldDescriptor(JudgmentBO.JDMTDATETIME.clear()).setNullable();
	private TypeBigDecimal jdmtAmt = new TypeBigDecimal("jdmt_amt").setFieldDescriptor(JudgmentBO.JDMTAMT.clear()).setNullable();
	private TypeString jdmtNum = new TypeString("jdmt_num").setFieldDescriptor(JudgmentBO.JDMTNUM.clear()).setNullable();
	private TypeString jdmtCode = new TypeString("jdmt_code").setFieldDescriptor(JudgmentBO.JDMTCODE.clear()).addForeignKey("jdmt_type","jdmt_code",false);
	private TypeString note = new TypeString("note").setFieldDescriptor(JudgmentBO.NOTE.clear()).setNullable();
	private TypeDate lastUpdDate = new TypeDate("last_upd_date").setFieldDescriptor(JudgmentBO.LASTUPDDATE.clear()).setNullable();
	private TypeString dispCode = new TypeString("disp_code").setFieldDescriptor(JudgmentBO.DISPCODE.clear()).addForeignKey("disp_type","disp_code",false).setNullable();
	private TypeDate dispDate = new TypeDate("disp_date").setFieldDescriptor(JudgmentBO.DISPDATE.clear()).setNullable();
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(JudgmentBO.USERIDSRL.clear()).setNullable();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(JudgmentBO.DESCR.clear()).setNullable();

	public JudgmentVO() {
		super(JudgmentBO.TABLE, JudgmentBO.SYSTEM, JudgmentBO.CORIS_DISTRICT_DB.setSource("D"), JudgmentBO.CORIS_JUSTICE_DB.setSource("J"), JudgmentBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JudgmentBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public JudgmentVO(String source) {
		super(JudgmentBO.TABLE, JudgmentBO.SYSTEM, JudgmentBO.CORIS_DISTRICT_DB.setSource("D"), JudgmentBO.CORIS_JUSTICE_DB.setSource("J"), JudgmentBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), JudgmentBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(jdmtSeq);
		this.getPropertyList().add(filingDatetime);
		this.getPropertyList().add(jdmtDatetime);
		this.getPropertyList().add(jdmtAmt);
		this.getPropertyList().add(jdmtNum);
		this.getPropertyList().add(jdmtCode);
		this.getPropertyList().add(note);
		this.getPropertyList().add(lastUpdDate);
		this.getPropertyList().add(dispCode);
		this.getPropertyList().add(dispDate);
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(descr);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(JudgmentBO.FILINGDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(JudgmentBO.FILINGDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(JudgmentBO.JDMTDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(JudgmentBO.JDMTDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(JudgmentBO.LASTUPDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(JudgmentBO.LASTUPDDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(JudgmentBO.DISPDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(JudgmentBO.DISPDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
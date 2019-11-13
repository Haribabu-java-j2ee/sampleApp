package gov.utcourts.coriscommon.dataaccess.acctadj;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AcctAdjVO extends BaseVO { 

	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(AcctAdjBO.ACCTNUM.clear()).addForeignKey("account","acct_num",false).setAsPrimaryKey();
	private TypeString distCode = new TypeString("dist_code").setFieldDescriptor(AcctAdjBO.DISTCODE.clear()).addForeignKey("dist_type","dist_code",false).setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(AcctAdjBO.SEQ.clear()).setAsPrimaryKey();
	private TypeBigDecimal adjAmt = new TypeBigDecimal("adj_amt").setFieldDescriptor(AcctAdjBO.ADJAMT.clear());
	private TypeDate adjDatetime = new TypeDate("adj_datetime").setFieldDescriptor(AcctAdjBO.ADJDATETIME.clear());
	private TypeString reason = new TypeString("reason").setFieldDescriptor(AcctAdjBO.REASON.clear());
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(AcctAdjBO.USERIDSRL.clear());
	private TypeString adjType = new TypeString("adj_type").setFieldDescriptor(AcctAdjBO.ADJTYPE.clear()).setNullable();
	private TypeDate effectiveDate = new TypeDate("effective_date").setFieldDescriptor(AcctAdjBO.EFFECTIVEDATE.clear()).setNullable();

	public AcctAdjVO() {
		super(AcctAdjBO.TABLE, AcctAdjBO.SYSTEM, AcctAdjBO.CORIS_DISTRICT_DB.setSource("D"), AcctAdjBO.CORIS_JUSTICE_DB.setSource("J"), AcctAdjBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AcctAdjBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AcctAdjVO(String source) {
		super(AcctAdjBO.TABLE, AcctAdjBO.SYSTEM, AcctAdjBO.CORIS_DISTRICT_DB.setSource("D"), AcctAdjBO.CORIS_JUSTICE_DB.setSource("J"), AcctAdjBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AcctAdjBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(acctNum);
		this.getPropertyList().add(distCode);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(adjAmt);
		this.getPropertyList().add(adjDatetime);
		this.getPropertyList().add(reason);
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(adjType);
		this.getPropertyList().add(effectiveDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(AcctAdjBO.ADJDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(AcctAdjBO.ADJDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(AcctAdjBO.EFFECTIVEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AcctAdjBO.EFFECTIVEDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
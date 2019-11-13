package gov.utcourts.coriscommon.dataaccess.checkdetl;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CheckDetlVO extends BaseVO { 

	private TypeInteger intJournalNum = new TypeInteger("int_journal_num").setFieldDescriptor(CheckDetlBO.INTJOURNALNUM.clear()).addForeignKey("trans","int_journal_num",false).setAsPrimaryKey();
	private TypeInteger transNum = new TypeInteger("trans_num").setFieldDescriptor(CheckDetlBO.TRANSNUM.clear()).addForeignKey("trans","trans_num",false).setAsPrimaryKey();
	private TypeInteger batchNum = new TypeInteger("batch_num").setFieldDescriptor(CheckDetlBO.BATCHNUM.clear()).addForeignKey("check_batch","batch_num",false);
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(CheckDetlBO.LOCNCODE.clear()).addForeignKey("check_batch","locn_code",false);
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(CheckDetlBO.COURTTYPE.clear()).addForeignKey("check_batch","court_type",false);
	private TypeString checkNum = new TypeString("check_num").setFieldDescriptor(CheckDetlBO.CHECKNUM.clear()).setNullable();
	private TypeBigDecimal reqtAmt = new TypeBigDecimal("reqt_amt").setFieldDescriptor(CheckDetlBO.REQTAMT.clear()).setNullable();
	private TypeInteger payeeNum = new TypeInteger("payee_num").setFieldDescriptor(CheckDetlBO.PAYEENUM.clear()).addForeignKey("party","party_num",false);
	private TypeDate voidDate = new TypeDate("void_date").setFieldDescriptor(CheckDetlBO.VOIDDATE.clear()).setNullable();
	private TypeString voidReason = new TypeString("void_reason").setFieldDescriptor(CheckDetlBO.VOIDREASON.clear()).setNullable();
	private TypeDate reconDate = new TypeDate("recon_date").setFieldDescriptor(CheckDetlBO.RECONDATE.clear()).setNullable();
	private TypeInteger reconUserid = new TypeInteger("recon_userid").setFieldDescriptor(CheckDetlBO.RECONUSERID.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeInteger reconStatus = new TypeInteger("recon_status").setFieldDescriptor(CheckDetlBO.RECONSTATUS.clear()).setNullable();

	public CheckDetlVO() {
		super(CheckDetlBO.TABLE, CheckDetlBO.SYSTEM, CheckDetlBO.CORIS_DISTRICT_DB.setSource("D"), CheckDetlBO.CORIS_JUSTICE_DB.setSource("J"), CheckDetlBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CheckDetlBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CheckDetlVO(String source) {
		super(CheckDetlBO.TABLE, CheckDetlBO.SYSTEM, CheckDetlBO.CORIS_DISTRICT_DB.setSource("D"), CheckDetlBO.CORIS_JUSTICE_DB.setSource("J"), CheckDetlBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CheckDetlBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intJournalNum);
		this.getPropertyList().add(transNum);
		this.getPropertyList().add(batchNum);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(checkNum);
		this.getPropertyList().add(reqtAmt);
		this.getPropertyList().add(payeeNum);
		this.getPropertyList().add(voidDate);
		this.getPropertyList().add(voidReason);
		this.getPropertyList().add(reconDate);
		this.getPropertyList().add(reconUserid);
		this.getPropertyList().add(reconStatus);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(CheckDetlBO.VOIDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CheckDetlBO.VOIDDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CheckDetlBO.RECONDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CheckDetlBO.RECONDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
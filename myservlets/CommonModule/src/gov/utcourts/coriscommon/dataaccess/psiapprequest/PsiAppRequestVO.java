package gov.utcourts.coriscommon.dataaccess.psiapprequest;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PsiAppRequestVO extends BaseVO { 

	private TypeInteger psiRequestId = new TypeInteger("psi_request_id").setFieldDescriptor(PsiAppRequestBO.PSIREQUESTID.clear()).setAsPrimaryKey();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(PsiAppRequestBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false);
	private TypeString psiAppRequestCode = new TypeString("psi_app_request_code").setFieldDescriptor(PsiAppRequestBO.PSIAPPREQUESTCODE.clear());
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(PsiAppRequestBO.CREATEDATETIME.clear());
	private TypeInteger meId = new TypeInteger("me_id").setFieldDescriptor(PsiAppRequestBO.MEID.clear()).setNullable();
	private TypeDate processedDatetime = new TypeDate("processed_datetime").setFieldDescriptor(PsiAppRequestBO.PROCESSEDDATETIME.clear()).setNullable();

	public PsiAppRequestVO() {
		super(PsiAppRequestBO.TABLE, PsiAppRequestBO.SYSTEM, PsiAppRequestBO.CORIS_DISTRICT_DB.setSource("D"), PsiAppRequestBO.CORIS_JUSTICE_DB.setSource("J"), PsiAppRequestBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PsiAppRequestBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PsiAppRequestVO(String source) {
		super(PsiAppRequestBO.TABLE, PsiAppRequestBO.SYSTEM, PsiAppRequestBO.CORIS_DISTRICT_DB.setSource("D"), PsiAppRequestBO.CORIS_JUSTICE_DB.setSource("J"), PsiAppRequestBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PsiAppRequestBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(psiRequestId);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(psiAppRequestCode);
		this.getPropertyList().add(createDatetime);
		this.getPropertyList().add(meId);
		this.getPropertyList().add(processedDatetime);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(PsiAppRequestBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(PsiAppRequestBO.CREATEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(PsiAppRequestBO.PROCESSEDDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(PsiAppRequestBO.PROCESSEDDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
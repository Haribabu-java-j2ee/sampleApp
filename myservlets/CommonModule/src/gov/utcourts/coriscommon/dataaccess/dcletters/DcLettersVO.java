package gov.utcourts.coriscommon.dataaccess.dcletters;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DcLettersVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(DcLettersBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeDate letter1Date = new TypeDate("letter1_date").setFieldDescriptor(DcLettersBO.LETTER1DATE.clear()).setNullable();
	private TypeDate letter2Date = new TypeDate("letter2_date").setFieldDescriptor(DcLettersBO.LETTER2DATE.clear()).setNullable();
	private TypeDate letter3Date = new TypeDate("letter3_date").setFieldDescriptor(DcLettersBO.LETTER3DATE.clear()).setNullable();
	private TypeDate letter4Date = new TypeDate("letter4_date").setFieldDescriptor(DcLettersBO.LETTER4DATE.clear()).setNullable();
	private TypeDate letter5Date = new TypeDate("letter5_date").setFieldDescriptor(DcLettersBO.LETTER5DATE.clear()).setNullable();

	public DcLettersVO() {
		super(DcLettersBO.TABLE, DcLettersBO.SYSTEM, DcLettersBO.CORIS_DISTRICT_DB.setSource("D"), DcLettersBO.CORIS_JUSTICE_DB.setSource("J"), DcLettersBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DcLettersBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DcLettersVO(String source) {
		super(DcLettersBO.TABLE, DcLettersBO.SYSTEM, DcLettersBO.CORIS_DISTRICT_DB.setSource("D"), DcLettersBO.CORIS_JUSTICE_DB.setSource("J"), DcLettersBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DcLettersBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(letter1Date);
		this.getPropertyList().add(letter2Date);
		this.getPropertyList().add(letter3Date);
		this.getPropertyList().add(letter4Date);
		this.getPropertyList().add(letter5Date);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(DcLettersBO.LETTER1DATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DcLettersBO.LETTER1DATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(DcLettersBO.LETTER2DATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DcLettersBO.LETTER2DATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(DcLettersBO.LETTER3DATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DcLettersBO.LETTER3DATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(DcLettersBO.LETTER4DATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DcLettersBO.LETTER4DATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(DcLettersBO.LETTER5DATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DcLettersBO.LETTER5DATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}
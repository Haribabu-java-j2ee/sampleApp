package gov.utcourts.coriscommon.dataaccess.language;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class LanguageVO extends BaseVO { 

	private TypeInteger langId = new TypeInteger("lang_id").setFieldDescriptor(LanguageBO.LANGID.clear()).addForeignKey("cal_lang","lang_id",false).addForeignKey("expunged_party","lang_id",true).addForeignKey("interpreter","lang_id",false).addForeignKey("party","lang_id",true).addForeignKey("party_old","lang_id",true).addForeignKey("s_party","lang_id",true).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(LanguageBO.DESCR.clear());
	private TypeInteger sortOrder = new TypeInteger("sort_order").setFieldDescriptor(LanguageBO.SORTORDER.clear());

	public LanguageVO() {
		super(LanguageBO.TABLE, LanguageBO.SYSTEM, LanguageBO.CORIS_DISTRICT_DB.setSource("D"), LanguageBO.CORIS_JUSTICE_DB.setSource("J"), LanguageBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), LanguageBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public LanguageVO(String source) {
		super(LanguageBO.TABLE, LanguageBO.SYSTEM, LanguageBO.CORIS_DISTRICT_DB.setSource("D"), LanguageBO.CORIS_JUSTICE_DB.setSource("J"), LanguageBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), LanguageBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(langId);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(sortOrder);
	}

}
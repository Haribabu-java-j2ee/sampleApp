package gov.utcourts.coriscommon.dto;


public class CaseMeDataDTO{
	String courtType;
	int intCaseNum;
	
	//String combinedProcessed;
	
	//Store Procedure Return Value
	int		statusCode;
	
	//Table me_type
	String meTypeMeTypeCode;
	String meTypeMeTypeDescr;
	int	   meTypeMeTypeSeq;
	String MeTypeNewPage;
	
	//Table me_line_item
	String meLineItemMeTypeCode;
	String meLineItemMeLineItem;
	int	   meLineItemMeSeq;
	String meLineItemAlways;
	String meLineItemJustify;
	String meLineItemDblspace;
	String meLineItemUnderscore;
	String meLineItemNewpage;
	String meLineItemGroupHeader; 
	
	// Table MeMaster
	String meMasterMeLineItem;
	int    meMasterMeSegment;
	String meMasterMeText;
	String meMasterMeScreenId;
	String meMasterMeFieldId;
	String meMasterAlign;
	String meMasterNewline;
	String meMasterAlways;
	String meMasterMultivalueText;
	String meMasterTextOnly;
	
	// Table Case Me Value
	int caseMeValueMeId;
	int caseMeValueMeTypeSeq;
	String caseMeValueMeScreenId;
	String caseMeValueMeFieldId;
	int caseMeValueMeFieldSeq;
	String caseMeValueMeFieldValue;
	
	// Table case_me_value_text
	int    caseMeValueTextMeId;
	String caseMeValueTextMeScreenId;
	String caseMeValueTextMeFieldId;
	int	   caseMeValueTextMeFieldSeq;
	String caseMeValueTextMeFieldValue;
	
//	public String getCombinedProcessed() {
//		return combinedProcessed;
//	}
//	public void setCombinedProcessed(String combinedProcessed) {
//		this.combinedProcessed = combinedProcessed;
//	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMeTypeMeTypeCode() {
		return meTypeMeTypeCode;
	}
	public void setMeTypeMeTypeCode(String meTypeMeTypeCode) {
		this.meTypeMeTypeCode = meTypeMeTypeCode;
	}
	public String getMeTypeMeTypeDescr() {
		return meTypeMeTypeDescr;
	}
	public void setMeTypeMeTypeDescr(String meTypeMeTypeDescr) {
		this.meTypeMeTypeDescr = meTypeMeTypeDescr;
	}
	public int getMeTypeMeTypeSeq() {
		return meTypeMeTypeSeq;
	}
	public void setMeTypeMeTypeSeq(int meTypeMeTypeSeq) {
		this.meTypeMeTypeSeq = meTypeMeTypeSeq;
	}
	public String getMeTypeNewPage() {
		return MeTypeNewPage;
	}
	public void setMeTypeNewPage(String MeTypeNewPage) {
		this.MeTypeNewPage = MeTypeNewPage;
	}
	public String getMeLineItemMeTypeCode() {
		return meLineItemMeTypeCode;
	}
	public void setMeLineItemMeTypeCode(String meLineItemMeTypeCode) {
		this.meLineItemMeTypeCode = meLineItemMeTypeCode;
	}
	public String getMeLineItemMeLineItem() {
		return meLineItemMeLineItem;
	}
	public void setMeLineItemMeLineItem(String meLineItemMeLineItem) {
		this.meLineItemMeLineItem = meLineItemMeLineItem;
	}
	public int getMeLineItemMeSeq() {
		return meLineItemMeSeq;
	}
	public void setMeLineItemMeSeq(int meLineItemMeSeq) {
		this.meLineItemMeSeq = meLineItemMeSeq;
	}
	public String getMeLineItemAlways() {
		return meLineItemAlways;
	}
	public void setMeLineItemAlways(String meLineItemAlways) {
		this.meLineItemAlways = meLineItemAlways;
	}
	public String getMeLineItemJustify() {
		return meLineItemJustify;
	}
	public void setMeLineItemJustify(String meLineItemJustify) {
		this.meLineItemJustify = meLineItemJustify;
	}
	public String getMeLineItemDblspace() {
		return meLineItemDblspace;
	}
	public void setMeLineItemDblspace(String meLineItemDblspace) {
		this.meLineItemDblspace = meLineItemDblspace;
	}
	public String getMeLineItemUnderscore() {
		return meLineItemUnderscore;
	}
	public void setMeLineItemUnderscore(String meLineItemUnderscore) {
		this.meLineItemUnderscore = meLineItemUnderscore;
	}
	public String getMeLineItemNewpage() {
		return meLineItemNewpage;
	}
	public void setMeLineItemNewpage(String meLineItemNewpage) {
		this.meLineItemNewpage = meLineItemNewpage;
	}
	public String getMeLineItemGroupHeader() {
		return meLineItemGroupHeader;
	}
	public void setMeLineItemGroupHeader(String meLineItemGroupHeader) {
		this.meLineItemGroupHeader = meLineItemGroupHeader;
	}
	public String getMeMasterMeLineItem() {
		return meMasterMeLineItem;
	}
	public void setMeMasterMeLineItem(String meMasterMeLineItem) {
		this.meMasterMeLineItem = meMasterMeLineItem;
	}
	public int getMeMasterMeSegment() {
		return meMasterMeSegment;
	}
	public void setMeMasterMeSegment(int meMasterMeSegment) {
		this.meMasterMeSegment = meMasterMeSegment;
	}
	public String getMeMasterMeText() {
		return meMasterMeText;
	}
	public void setMeMasterMeText(String meMasterMeText) {
		this.meMasterMeText = meMasterMeText;
	}
	public String getMeMasterMeScreenId() {
		return meMasterMeScreenId;
	}
	public void setMeMasterMeScreenId(String meMasterMeScreenId) {
		this.meMasterMeScreenId = meMasterMeScreenId;
	}
	public String getMeMasterMeFieldId() {
		return meMasterMeFieldId;
	}
	public void setMeMasterMeFieldId(String meMasterMeFieldId) {
		this.meMasterMeFieldId = meMasterMeFieldId;
	}
	public String getMeMasterAlign() {
		return meMasterAlign;
	}
	public void setMeMasterAlign(String meMasterAlign) {
		this.meMasterAlign = meMasterAlign;
	}
	public String getMeMasterNewline() {
		return meMasterNewline;
	}
	public void setMeMasterNewline(String meMasterNewline) {
		this.meMasterNewline = meMasterNewline;
	}
	public String getMeMasterAlways() {
		return meMasterAlways;
	}
	public void setMeMasterAlways(String meMasterAlways) {
		this.meMasterAlways = meMasterAlways;
	}
	public String getMeMasterMultivalueText() {
		return meMasterMultivalueText;
	}
	public void setMeMasterMultivalueText(String meMasterMultivalueText) {
		this.meMasterMultivalueText = meMasterMultivalueText;
	}
	public String getMeMasterTextOnly() {
		return meMasterTextOnly;
	}
	public void setMeMasterTextOnly(String meMasterTextOnly) {
		this.meMasterTextOnly = meMasterTextOnly;
	}
	public int getCaseMeValueMeId() {
		return caseMeValueMeId;
	}
	public void setCaseMeValueMeId(int caseMeValueMeId) {
		this.caseMeValueMeId = caseMeValueMeId;
	}
	public int getCaseMeValueMeTypeSeq() {
		return caseMeValueMeTypeSeq;
	}
	public void setCaseMeValueMeTypeSeq(int caseMeValueMeTypeSeq) {
		this.caseMeValueMeTypeSeq = caseMeValueMeTypeSeq;
	}
	public String getCaseMeValueMeScreenId() {
		return caseMeValueMeScreenId;
	}
	public void setCaseMeValueMeScreenId(String caseMeValueMeScreenId) {
		this.caseMeValueMeScreenId = caseMeValueMeScreenId;
	}
	public String getCaseMeValueMeFieldId() {
		return caseMeValueMeFieldId;
	}
	public void setCaseMeValueMeFieldId(String caseMeValueMeFieldId) {
		this.caseMeValueMeFieldId = caseMeValueMeFieldId;
	}
	public int getCaseMeValueMeFieldSeq() {
		return caseMeValueMeFieldSeq;
	}
	public void setCaseMeValueMeFieldSeq(int caseMeValueMeFieldSeq) {
		this.caseMeValueMeFieldSeq = caseMeValueMeFieldSeq;
	}
	public String getCaseMeValueMeFieldValue() {
		return caseMeValueMeFieldValue;
	}
	public void setCaseMeValueMeFieldValue(String caseMeValueMeFieldValue) {
		this.caseMeValueMeFieldValue = caseMeValueMeFieldValue;
	}
	public int getCaseMeValueTextMeId() {
		return caseMeValueTextMeId;
	}
	public void setCaseMeValueTextMeId(int caseMeValueTextMeId) {
		this.caseMeValueTextMeId = caseMeValueTextMeId;
	}
	public String getCaseMeValueTextMeScreenId() {
		return caseMeValueTextMeScreenId;
	}
	public void setCaseMeValueTextMeScreenId(String caseMeValueTextMeScreenId) {
		this.caseMeValueTextMeScreenId = caseMeValueTextMeScreenId;
	}
	public String getCaseMeValueTextMeFieldId() {
		return caseMeValueTextMeFieldId;
	}
	public void setCaseMeValueTextMeFieldId(String caseMeValueTextMeFieldId) {
		this.caseMeValueTextMeFieldId = caseMeValueTextMeFieldId;
	}
	public int getCaseMeValueTextMeFieldSeq() {
		return caseMeValueTextMeFieldSeq;
	}
	public void setCaseMeValueTextMeFieldSeq(int caseMeValueTextMeFieldSeq) {
		this.caseMeValueTextMeFieldSeq = caseMeValueTextMeFieldSeq;
	}
	public String getCaseMeValueTextMeFieldValue() {
		return caseMeValueTextMeFieldValue;
	}
	public void setCaseMeValueTextMeFieldValue(String caseMeValueTextMeFieldValue) {
		this.caseMeValueTextMeFieldValue = caseMeValueTextMeFieldValue;
	}
	public String getCourtType() {
		return courtType;
	}
	public void setCourtType(String courtType) {
		this.courtType = courtType;
	}
	public int getIntCaseNum() {
		return intCaseNum;
	}
	public void setIntCaseNum(int intCaseNum) {
		this.intCaseNum = intCaseNum;
	}

}

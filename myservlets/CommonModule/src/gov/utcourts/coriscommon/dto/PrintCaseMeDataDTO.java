package gov.utcourts.coriscommon.dto;

public class PrintCaseMeDataDTO{
	String meTypeCode;
	Boolean boldFont;
	String dblspace;
	String underscore;
	String newPage;
	String groupHeader;
	String leftText;
	String centerText;
	String rightText;
	int	   lineSpacing;
	
	public String getMeTypeCode() {
		return meTypeCode;
	}
	public void setMeTypeCode(String meTypeCode) {
		this.meTypeCode = meTypeCode;
	}
	public Boolean getBoldFont() {
		return boldFont;
	}
	public void setBoldFont(Boolean boldFont) {
		this.boldFont = boldFont;
	}
	public String getDblspace() {
		return dblspace;
	}
	public void setDblspace(String dblspace) {
		this.dblspace = dblspace;
	}
	public String getUnderscore() {
		return underscore;
	}
	public void setUnderscore(String underscore) {
		this.underscore = underscore;
	}
	public String getNewPage() {
		return newPage;
	}
	public void setNewPage(String newPage) {
		this.newPage = newPage;
	}
	public String getGroupHeader() {
		return groupHeader;
	}
	public void setGroupHeader(String groupHeader) {
		this.groupHeader = groupHeader;
	}
	public String getLeftText() {
		return leftText;
	}
	public void setLeftText(String leftText) {
		this.leftText = leftText;
	}
	public String getCenterText() {
		return centerText;
	}
	public void setCenterText(String centerText) {
		this.centerText = centerText;
	}
	public String getRightText() {
		return rightText;
	}
	public void setRightText(String rightText) {
		this.rightText = rightText;
	}
	public int getLineSpacing() {
		return lineSpacing;
	}
	public void setLineSpacing(int lineSpacing) {
		this.lineSpacing = lineSpacing;
	}
}

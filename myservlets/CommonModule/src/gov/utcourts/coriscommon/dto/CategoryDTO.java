package gov.utcourts.coriscommon.dto;

public class CategoryDTO {
	private String categoryCode;
	private String description;
	
	public CategoryDTO(String categoryCode, String description) {
		this.categoryCode = categoryCode;
		this.description = description;
	}
	
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

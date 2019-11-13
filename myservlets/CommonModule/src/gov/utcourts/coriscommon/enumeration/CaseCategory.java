package gov.utcourts.coriscommon.enumeration;

/**
 * Case categories that can be used. 
 * <li>{@link #CIVIL}</li> 
 * <li>{@link #CRIMINAL}</li> 
 * <li>{@link #DOMESTIC}</li> 
 * <li>{@link #SMALLCLAIMS}</li> 
 * <li>{@link #PROBATE}</li>
 * <li>{@link #UNDEFINED}</li>
 */
public enum CaseCategory {

    /**
     * Civil case category
     */
    CIVIL("V"),

    /**
     * Criminal case category
     */
    CRIMINAL("R"),

    /**
     * Domestic case category
     */
    DOMESTIC("D"),

    /**
     * Small Claims case category
     */
    SMALLCLAIMS("S"),

    /**
     * Probate case category
     */
    PROBATE("P"),

    /**
     * Not a case category but it is returned if category is not in this enum.
     */
    UNDEFINED("U");

    private String category;

    CaseCategory(final String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    /**
     * Resolve the enumeration from a string value
     * 
     * @param requestAttributeString
     *            String
     * @return CaseCategory
     */
    public static CaseCategory resolveEnumFromString(String caseCategory) {

        if (caseCategory != null) {
            for (CaseCategory cc : CaseCategory.values()) {
                if (caseCategory.equalsIgnoreCase(cc.category)) {
                    return cc;
                }
            }
        }

        return UNDEFINED;
    }
}

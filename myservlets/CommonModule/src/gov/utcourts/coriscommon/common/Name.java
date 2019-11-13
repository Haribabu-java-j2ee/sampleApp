package gov.utcourts.coriscommon.common;

/**
 * Defines the requirements for an object with the common name properties (first name, last name, etc.).
 * 
 * @author Sirius Computer Solutions, Inc.
 */
public interface Name {
    public String getFirstName();

    public String getLastName();

    public String getMiddleName();

    public String getNameSuffix();
}

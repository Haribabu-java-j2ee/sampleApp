package gov.utcourts.coriscommon.util;

/**
 * Defines the requirements for a mailing object with the common address properties (address1, city, state, etc.).
 * 
 * @author Dave Hayward
 */
public interface MailingAddress {
	public String getMailingAddress1();
	public String getMailingAddress2();
	public String getMailingCity();
	public String getMailingState();
	public String getMailingZip();
}

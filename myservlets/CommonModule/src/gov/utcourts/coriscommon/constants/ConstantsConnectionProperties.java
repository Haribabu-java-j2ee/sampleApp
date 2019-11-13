package gov.utcourts.coriscommon.constants;

import gov.utcourts.courtscommon.dataaccess.connection.ConnectionProperties;

public class ConstantsConnectionProperties {

    /**
     * Properties file for Coris Common Connection Properties All Databases
     * Used when you need to connect to all databases for updating common information 
     * such as attorney, offense
     */
	public static final ConnectionProperties CORIS_PRODUCTION_DISTRICT_DB = new ConnectionProperties("coris_production_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_PRODUCTION_JUSTICE_DB = new ConnectionProperties("coris_production_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_TEST_DISTRICT_DB = new ConnectionProperties("coris_test_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_TEST_JUSTICE_DB = new ConnectionProperties("coris_test_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_TRAINING_DB = new ConnectionProperties("coris_training_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_VERIFY_DB = new ConnectionProperties("coris_verify_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DEVELOPMENT_DB = new ConnectionProperties("coris_development_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CARE_PRODUCTION_DB = new ConnectionProperties("care_prod_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CARE_TEST_DB = new ConnectionProperties("care_test_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CARE_DEVELOPMENT_DB = new ConnectionProperties("care_dev_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
}

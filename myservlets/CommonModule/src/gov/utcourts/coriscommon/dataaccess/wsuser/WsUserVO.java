package gov.utcourts.coriscommon.dataaccess.wsuser;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class WsUserVO extends BaseVO { 

	private TypeInteger wsuserSrl = new TypeInteger("wsuser_srl").setFieldDescriptor(WsUserBO.WSUSERSRL.clear()).setAsPrimaryKey();
	private TypeString logname = new TypeString("logname").setFieldDescriptor(WsUserBO.LOGNAME.clear()).setNullable();
	private TypeString userPasswd = new TypeString("user_passwd").setFieldDescriptor(WsUserBO.USERPASSWD.clear()).setNullable();
	private TypeString httpsAddress = new TypeString("https_address").setFieldDescriptor(WsUserBO.HTTPSADDRESS.clear()).setNullable();
	private TypeString enabled = new TypeString("enabled").setFieldDescriptor(WsUserBO.ENABLED.clear());

	public WsUserVO() {
		super(WsUserBO.TABLE, WsUserBO.SYSTEM, WsUserBO.CORIS_DISTRICT_DB.setSource("D"), WsUserBO.CORIS_JUSTICE_DB.setSource("J"), WsUserBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), WsUserBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public WsUserVO(String source) {
		super(WsUserBO.TABLE, WsUserBO.SYSTEM, WsUserBO.CORIS_DISTRICT_DB.setSource("D"), WsUserBO.CORIS_JUSTICE_DB.setSource("J"), WsUserBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), WsUserBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(wsuserSrl);
		this.getPropertyList().add(logname);
		this.getPropertyList().add(userPasswd);
		this.getPropertyList().add(httpsAddress);
		this.getPropertyList().add(enabled);
	}

}
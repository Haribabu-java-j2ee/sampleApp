package gov.utcourts.coriscommon.dto;

import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;
import gov.utcourts.coriscommon.dataaccess.jdmttype.JdmtTypeBO;
import gov.utcourts.coriscommon.dataaccess.offense.OffenseBO;
import gov.utcourts.coriscommon.dataaccess.pleatype.PleaTypeBO;

public class ChargeDTO {
	private ChargeBO chargeBO;
	private PleaTypeBO pleaTypeBO;
	private JdmtTypeBO jdmtTypeBO;
	private OffenseBO offenseBO;
	public ChargeBO getChargeBO() {
		return chargeBO;
	}
	public void setChargeBO(ChargeBO chargeBO) {
		this.chargeBO = chargeBO;
	}
	public PleaTypeBO getPleaTypeBO() {
		return pleaTypeBO;
	}
	public void setPleaTypeBO(PleaTypeBO pleaTypeBO) {
		this.pleaTypeBO = pleaTypeBO;
	}
	public JdmtTypeBO getJdmtTypeBO() {
		return jdmtTypeBO;
	}
	public void setJdmtTypeBO(JdmtTypeBO jdmtTypeBO) {
		this.jdmtTypeBO = jdmtTypeBO;
	}
	public OffenseBO getOffenseBO() {
		return offenseBO;
	}
	public void setOffenseBO(OffenseBO offenseBO) {
		this.offenseBO = offenseBO;
	}

}

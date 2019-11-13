package gov.utcourts.corisweb.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ibm.json.java.JSONObject;

import gov.utcourts.coriscommon.dataaccess.country.CountryBO;
import gov.utcourts.coriscommon.dataaccess.idissuer.IdIssuerBO;
import gov.utcourts.coriscommon.dataaccess.idtypedefn.IdTypeDefnBO;
import gov.utcourts.coriscommon.dataaccess.party.PartyBO;
import gov.utcourts.coriscommon.dataaccess.partyidentifier.PartyIdentifierBO;
import gov.utcourts.coriscommon.dataaccess.state.StateBO;
import gov.utcourts.coriscommon.enumeration.PageMode;
import gov.utcourts.coriscommon.util.TextUtil;
import gov.utcourts.corisweb.util.URLEncryption;
import gov.utcourts.courtscommon.constants.BaseConstants;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;

/**
 * Servlet implementation class CorisPartyIDServlet
 */
@WebServlet("/CorisPartyIDServlet")
public class CorisPartyIDServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CorisPartyIDServlet.class.getName());
	
	/**
	 * @see BaseServlet#BaseServlet()
	 */
	public CorisPartyIDServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * (non-Javadoc)
	 * @see gov.utcourts.corisweb.servlet.BaseServlet#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageMode mode = PageMode.resolveEnumFromString(TextUtil.getParamAsString(request, "mode"));
		int partyNum = URLEncryption.getParamAsInt(request, "partyNum");
		int idTypeId = URLEncryption.getParamAsInt(request, "idTypeId");
		int idIssuerId = URLEncryption.getParamAsInt(request, "idIssuerId");
		String idNumber = URLEncryption.getParamAsString(request, "idValue");
		String courtType = URLEncryption.getParamAsString(request, "courtType");
		int partyIdentifierId = URLEncryption.getParamAsInt(request, "partyIdId");
		JSONObject retValObj = new JSONObject();
		PartyIdentifierBO partyIdBO = null;
		try {
			switch (mode) {
				case EDIT:
					new PartyIdentifierBO(courtType).setIdIssuerId(idIssuerId).setIdNumber(idNumber).setIdTypeDefnId(idTypeId).where(PartyIdentifierBO.PARTYIDENTIFIERID, partyIdentifierId).update();
					break;
				case ADD:
					partyIdBO = getPartyIdBO(partyNum, courtType, idIssuerId, idTypeId, idNumber);
					if (partyIdBO == null || partyIdBO.getPartyIdentifierId() == 0) {
						partyIdentifierId = addPartyId(partyNum, courtType, idIssuerId, idNumber, idTypeId);
					} else {
						String idTypeDescr = new IdTypeDefnBO(courtType).where(IdTypeDefnBO.IDTYPEDEFNID, idTypeId).find(IdTypeDefnBO.TYPEDESCR).getTypeDescr();
						retValObj.put("error", true);
						retValObj.put("errorMessage", "Cannot add " + idTypeDescr + ". Identical ID already exists");
					}
					break;
				case DELETE:
					deletePartyId(partyIdentifierId, courtType);
					break;
				case FIND:
					findPartyIDList(request);
					request.getRequestDispatcher("/jsp/corisPartyIdList.jsp").forward(request, response);
					break;
				case OPEN:
					openIdDefn(request);
					request.getRequestDispatcher("/jsp/corisPartyIdDefn.jsp").forward(request, response);
					break;
				default:
					PartyBO party = new PartyBO(courtType).where(PartyBO.PARTYNUM, partyNum).find();
					request.setAttribute("party", party);
					request.getRequestDispatcher("/jsp/corisPartyIdentifiers.jsp").forward(request, response);
					break;
			}
			retValObj.put("partyIdId", partyIdentifierId);
		} catch (Exception e) {
			retValObj.put("error", true);
			retValObj.put("errorMessage", e.getMessage());
			logger.error(e.getMessage());
		}
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(retValObj.toString());
	}
	
	private void openIdDefn(HttpServletRequest request) throws Exception {
		String mode = "add";
		int partyNum = TextUtil.getParamAsInt(request, "partyNum");
		String courtType = TextUtil.getParamAsString(request, "courtType");
		int partyIdId =TextUtil.getParamAsInt(request, "partyIdId");
		IdIssuerBO defaultIssuer = null;
		PartyIdentifierBO partyIdBO = new PartyIdentifierBO(courtType);
		if(partyIdId > 0){
			mode = "edit";
			partyIdBO = new PartyIdentifierBO(courtType).where(PartyIdentifierBO.PARTYIDENTIFIERID,partyIdId).find();
			defaultIssuer = new IdIssuerBO(courtType).where(IdIssuerBO.IDISSUERID,partyIdBO.getIdIssuerId()).find();	
		}
		else {
			defaultIssuer = new IdIssuerBO(courtType);
			defaultIssuer.setCountryId(new CountryBO(courtType).includeFields(CountryBO.COUNTRYID).where(CountryBO.PSCOUNTRYCODE,"US").find(CountryBO.COUNTRYID).getCountryId());
		}
		PartyBO party = new PartyBO(courtType).where(PartyBO.PARTYNUM, partyNum).find();
		List<IdIssuerBO> idIssuers = new IdIssuerBO(courtType).includeFields(IdIssuerBO.ALL_FIELDS)
														.includeTables(new StateBO(courtType).includeFields(StateBO.ALL_FIELDS).setOuter())	
														.addForeignKey(StateBO.STATEID, IdIssuerBO.STATEID).orderBy(StateBO.NAME)
														.search();
		
		List<IdTypeDefnBO> idTypes = new IdTypeDefnBO(courtType).includeFields(IdTypeDefnBO.ALL_FIELDS).orderBy(IdTypeDefnBO.TYPEDESCR).search();

		List<CountryBO> countries = new CountryBO(courtType).includeFields(CountryBO.ALL_FIELDS)
										.where(CountryBO.COUNTRYID, Exp.IN, new IdIssuerBO(courtType).searchAndGetColumnResults(IdIssuerBO.COUNTRYID))
										.search();
		request.setAttribute("mode", mode);
		request.setAttribute("defaultIssuer", defaultIssuer);
		request.setAttribute("party", party);
		request.setAttribute("idIssuers", idIssuers);
		request.setAttribute("countries", countries);
		request.setAttribute("idTypes", idTypes);
		request.setAttribute("partyIdBO", partyIdBO);
		
	}

	private void findPartyIDList(HttpServletRequest request) throws Exception {
		int partyNum = TextUtil.getParamAsInt(request, "partyNum");
		String courtType = TextUtil.getParamAsString(request, "courtType");
		HashMap<Integer, IdIssuerBO> partyIssuerMap = new HashMap<Integer, IdIssuerBO>();
		IdIssuerBO issuer = null;
		List<PartyIdentifierBO> partyIds = new PartyIdentifierBO(courtType).where(PartyIdentifierBO.PARTYNUM,partyNum)
											    .includeTables(new IdTypeDefnBO(courtType).includeFields(IdTypeDefnBO.TYPEDESCR), new IdIssuerBO(courtType))
												.addForeignKey(PartyIdentifierBO.IDISSUERID, IdIssuerBO.IDISSUERID)
												.addForeignKey(PartyIdentifierBO.IDTYPEDEFNID, IdTypeDefnBO.IDTYPEDEFNID)
												.orderBy(IdTypeDefnBO.TYPEDESCR,PartyIdentifierBO.PARTYIDENTIFIERID).search(); 
		for(PartyIdentifierBO pid:partyIds){
			if(!TextUtil.isEmpty(pid.getIdNumber())){
				issuer = new IdIssuerBO(courtType).includeTables(new StateBO(courtType).includeFields(StateBO.NAME).setOuter(), new CountryBO(courtType).includeFields(CountryBO.COUNTRYNAME))
												.addForeignKey(IdIssuerBO.STATEID, StateBO.STATEID)
												.addForeignKey(IdIssuerBO.COUNTRYID, CountryBO.COUNTRYID)
												.where(IdIssuerBO.IDISSUERID,pid.getIdIssuerId()).find();
				partyIssuerMap.put(pid.getPartyIdentifierId(), issuer);
			}
		}
		request.setAttribute("partyIds", partyIds);
		request.setAttribute("partyIssuerMap", partyIssuerMap);
	}

	/**
	 * @param partyNum
	 * @param courtType
	 * @param idIssuerId
	 * @param idNumber
	 * @param idTypeId
	 * @throws Exception
	 */
	private int addPartyId(int partyNum, String courtType, int idIssuerId, String idNumber, int idTypeId) throws Exception {
		PartyIdentifierBO pIdBO = new PartyIdentifierBO(courtType).setPartyNum(partyNum).setIdIssuerId(idIssuerId).setIdNumber(idNumber).setIdTypeDefnId(idTypeId).insert();
		return pIdBO.getPartyIdentifierId();
	}
	
	/**
	 * @param partyNum
	 * @param courtType
	 * @param idIssuerId
	 * @param idTypeId
	 * @param idNumber
	 * @return
	 * @throws Exception
	 */
	private PartyIdentifierBO getPartyIdBO(int partyNum, String courtType, int idIssuerId, int idTypeId, String idNumber) throws Exception {
		if (idNumber != null) {
			return new PartyIdentifierBO(courtType).includeFields(PartyIdentifierBO.ALL_FIELDS).where(PartyIdentifierBO.PARTYNUM, partyNum).where(PartyIdentifierBO.IDTYPEDEFNID, idTypeId)
					.where(PartyIdentifierBO.IDISSUERID, idIssuerId).where(PartyIdentifierBO.IDNUMBER, idNumber).toString(BaseConstants.PRINT + BaseConstants.RUN).find();
		} else {
			return new PartyIdentifierBO(courtType).includeFields(PartyIdentifierBO.ALL_FIELDS).where(PartyIdentifierBO.PARTYNUM, partyNum).where(PartyIdentifierBO.IDTYPEDEFNID, idTypeId)
					.where(PartyIdentifierBO.IDISSUERID, idIssuerId).toString(BaseConstants.PRINT + BaseConstants.RUN).find();
		}
	}
	
	/**
	 * @param partyIdId
	 * @param courtType
	 * @throws Exception
	 */
	private void deletePartyId(int partyIdId, String courtType) throws Exception {
		new PartyIdentifierBO(courtType).where(PartyIdentifierBO.PARTYIDENTIFIERID, partyIdId).delete();
	}
	
}

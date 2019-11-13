package gov.utcourts.corisweb.report;

import gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO;
import gov.utcourts.coriscommon.dataaccess.charge.ChargeBO;
import gov.utcourts.coriscommon.dataaccess.judgehist.JudgeHistBO;
import gov.utcourts.coriscommon.dataaccess.judgment.JudgmentBO;
import gov.utcourts.coriscommon.dataaccess.kase.KaseBO;
import gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO;
import gov.utcourts.coriscommon.dataaccess.stay.StayBO;
import gov.utcourts.coriscommon.dataaccess.warrant.WarrantBO;
import gov.utcourts.coriscommon.util.DateUtil;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.Expression;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringArrayDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.TableAndFieldDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.connection.DatabaseConnection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CasePendingSP {
	
	public static class ReturnValues {
		// return values
		public Integer p_status = null;
		public Date p_disp_date = null;
		public Integer p_disp_id = null;
		public Integer p_tot_stay_days = null;
		public Integer p_tot_disp_days = null;
		
		// variable
		public Integer p_int_case_num = null;
		public Date p_filing_date = null;
		public Integer p_days_since_filed = null;
		public Date p_case_disp_date = null;
		public Date p_debt_coll_date = null;
		public String p_debt_collection = null;
		public String p_case_type = null;
		public String p_case_type_cat = null;
		public Integer p_assn_judge_id = null;
		public Integer p_assn_comm_id = null;
		public Integer p_stay_count = null;
		public Date p_stay_beg_date = null;
		public Date p_stay_end_date = null;
		public Date p_hold_stay_end = null;
		public Date p_temp_end_date = null;
		public Date p_min_warr_date = null;
		public Integer p_warr_count = null;
		public Date p_warr_issue_date = null;
		public Date p_warr_recall_date = null;
		public Date p_jdmt_date = null;
		public Date p_chrg_disp_date = null;
		public Integer p_count = null;
		public Date p_finish_date = null;
		public Integer p_temp_daycount = null;
	}
	
	public static List<ReturnValues> calcPending(int p_userid_srl, KaseBO kase, Date p_report_date, String courtType) throws Exception {
		
		
		//CREATE PROCEDURE "informix".calc_pending(p_userid_srl INTEGER, p_int_case_num INTEGER, p_report_date DATE)
		//RETURNING INTEGER, INTEGER, DATE, INTEGER, INTEGER, INTEGER;
		//
		//DEFINE GLOBAL pg_sql_code    INTEGER DEFAULT 0;
		//DEFINE GLOBAL pg_isam_code   INTEGER DEFAULT 0;
		//DEFINE GLOBAL pg_error_value VARCHAR(80) DEFAULT "";
		//DEFINE GLOBAL pg_errlog_info VARCHAR(80) DEFAULT "";

		//-- returns
		//DEFINE p_status             INTEGER;
		//DEFINE p_disp_date          DATE;
		//DEFINE p_disp_id            INTEGER;
		//DEFINE p_tot_stay_days      INTEGER;
		//DEFINE p_tot_disp_days      INTEGER;
		List<ReturnValues> returnValuesList = new ArrayList<ReturnValues>();
		
		//--
		//DEFINE p_filing_date        DATE;
		//DEFINE p_days_since_filed   INTEGER;
		//DEFINE p_case_disp_date     DATE;
		//DEFINE p_debt_coll_date     DATE;
		//DEFINE p_debt_collection	  CHAR(1);
		//DEFINE p_case_type          CHAR(2);
		//DEFINE p_case_type_cat      CHAR(1);
		//DEFINE p_assn_judge_id      INTEGER;
		//DEFINE p_assn_comm_id       INTEGER;
		//DEFINE p_stay_count         INTEGER;
		//DEFINE p_stay_beg_date      DATE;
		//DEFINE p_stay_end_date      DATE;
		//DEFINE p_hold_stay_end      DATE;
		//DEFINE p_temp_end_date      DATE;
		//DEFINE p_min_warr_date      DATE;
		//DEFINE p_warr_count         INTEGER;
		//DEFINE p_warr_issue_date    DATE;
		//DEFINE p_warr_recall_date   DATE;
		//DEFINE p_jdmt_date          DATE;
		//DEFINE p_chrg_disp_date     DATE;
		//DEFINE p_count              INTEGER;
		//DEFINE p_finish_date        DATE;
		//DEFINE p_temp_daycount      INTEGER;
		
		//ON EXCEPTION SET pg_sql_code,pg_isam_code,pg_error_value
		//ROLLBACK WORK;
		//CALL ins_errorlog(p_userid_srl) RETURNING p_status;
		//RETURN p_status, p_int_case_num, NULL, NULL, NULL, NULL;
		//END EXCEPTION;

		//--SET DEBUG FILE TO "/home/paulb/pending/calcpending.debug";
		//--TRACE ON;
		//
		//BEGIN
		//LET pg_errlog_info = "calc_pending.01";
		//LET p_int_case_num = p_int_case_num;        
		//LET p_status = 100;
		
		//IF p_userid_srl = 0 THEN
		//	SELECT personnel.userid_srl INTO p_userid_srl
		//	FROM personnel, kase
		//	WHERE personnel.locn_code = kase.locn_code
		//	AND personnel.court_type = kase.court_type
		//	AND kase.int_case_num = p_int_case_num
		//	AND personnel.logname = "dpx";
		//END IF;
		if (p_userid_srl == 0)
			p_userid_srl = new PersonnelBO(courtType)
								.includeTables(
									new KaseBO().where(KaseBO.INTCASENUM, kase.getIntCaseNum())
								)
								.where(PersonnelBO.LOGNAME, "dpx")
								.addForeignKey(PersonnelBO.LOCNCODE, KaseBO.LOCNCODE)
								.addForeignKey(PersonnelBO.COURTTYPE, KaseBO.COURTTYPE)
								.find(PersonnelBO.USERIDSRL)
								.getUseridSrl();
		

		//LET pg_errlog_info = "calc_pending.02";

		//SELECT filing_date, disp_date, disp_id, debt_coll_date, debt_collection,
		//assn_judge_id, assn_comm_id, kase.case_type, case_type.category
		//INTO p_filing_date, p_case_disp_date, p_disp_id, p_debt_coll_date, p_debt_collection,
		//p_assn_judge_id, p_assn_comm_id, p_case_type, p_case_type_cat         
		//FROM kase, case_type
		//WHERE int_case_num = p_int_case_num
		//AND kase.case_type = case_type.case_type;

//		List<KaseBO> caseList = new KaseBO(courtType)
//									.includeTables(
//										new CaseTypeBO()
//									)
//									.addForeignKey(KaseBO.CASETYPE, CaseTypeBO.CASETYPE)
//									.where(KaseBO.INTCASENUM, Exp.IN, p_int_case_nums)
//									.search(KaseBO.INTCASENUM, KaseBO.FILINGDATE, KaseBO.DISPDATE, KaseBO.DISPID, KaseBO.DEBTCOLLDATE, KaseBO.DEBTCOLLECTION, KaseBO.ASSNJUDGEID, KaseBO.ASSNCOMMID, KaseBO.CASETYPE, CaseTypeBO.CATEGORY);
//		if (caseList.size() > 0) {
//			for (KaseBO kase : caseList) {
				
				ReturnValues returnValue = new ReturnValues();
				returnValue.p_int_case_num = kase.getIntCaseNum();
				returnValue.p_filing_date = kase.getFilingDate();
				returnValue.p_case_disp_date = kase.getDispDate();
				returnValue.p_disp_id = kase.getDispId();
				returnValue.p_debt_coll_date = kase.getDebtCollDate();
				returnValue.p_debt_collection = kase.getDebtCollection();
				returnValue.p_assn_judge_id = kase.getAssnJudgeId();
				returnValue.p_assn_comm_id = kase.getAssnCommId();
				returnValue.p_case_type = kase.getCaseType();
				returnValue.p_case_type_cat = (String) kase.get(CaseTypeBO.CATEGORY);
				
				//IF p_filing_date IS NULL THEN
			    //    RETURN p_status, p_int_case_num, NULL, NULL, NULL, NULL;
			    //ELSE
			    //    LET p_status = 0;
			    //END IF;
//				if (returnValue.p_filing_date == null) 
//					continue;
				
				int p_count = 0;
				
//				-- determine the true dispo date:    
//			    IF p_case_type_cat = "R" THEN
				if ("R".equalsIgnoreCase(returnValue.p_case_type_cat)) {

//			       LET pg_errlog_info = "calc_pending.02";
//			       SELECT count(*) INTO p_count
//			       FROM charge
//			       WHERE int_case_num = p_int_case_num;
				   p_count = new ChargeBO(courtType).count(ChargeBO.ALL_FIELDS).where(ChargeBO.INTCASENUM, returnValue.p_int_case_num).find().getCount();

//			       IF p_count = 0 THEN
				   if (p_count == 0) {
					   
//			          IF p_case_disp_date IS NULL THEN
					  if (returnValue.p_case_disp_date == null) {
						  
//			            IF p_case_type = "NA" THEN
						if ("NA".equalsIgnoreCase(returnValue.p_case_type)) {
							
//			              LET p_disp_date = p_filing_date;
						  returnValue.p_disp_date = returnValue.p_filing_date;
							
//			            ELSE
						} else {
							
//			              LET p_disp_date = NULL;
						  returnValue.p_disp_date = null;	
							
//			            END IF;
						}
						  
//			          ELSE
					  } else {
//			            LET p_disp_date = p_case_disp_date;
						returnValue.p_disp_date = returnValue.p_case_disp_date;  
//			          END IF;
					  }
					   
//			       ELSE
				   } else {
					   
//			         LET pg_errlog_info = "calc_pending.02a";
//			         SELECT count(*) INTO p_count
//			         FROM charge
//			         WHERE int_case_num = p_int_case_num
//			         AND jdmt_date IS NULL;
					 p_count = new ChargeBO(courtType).count(ChargeBO.ALL_FIELDS).where(ChargeBO.INTCASENUM, returnValue.p_int_case_num).where(ChargeBO.JDMTDATE, Exp.IS_NULL).find().getCount();  
					   
//			         IF p_count > 0 THEN
					 if (p_count > 0) {
						 
//			            IF p_case_disp_date IS NULL THEN
						if (returnValue.p_case_disp_date == null) {
							
//			                LET p_disp_date = NULL;
							returnValue.p_disp_date = null;
							
//			            ELSE
						} else {
							
//			                LET p_disp_date = p_case_disp_date;
							returnValue.p_disp_date = returnValue.p_case_disp_date;
							
//			            END IF;
						}
						 
//			         ELSE
					 } else {
						 
//			            SELECT max(jdmt_date) INTO p_chrg_disp_date
//			            FROM charge
//			            WHERE int_case_num = p_int_case_num;
//			            LET p_disp_date = p_chrg_disp_date;
						returnValue.p_disp_date = (Date) new ChargeBO(courtType).max(ChargeBO.JDMTDATE).where(ChargeBO.INTCASENUM, returnValue.p_int_case_num).find().getMax();  
						 
//			         END IF;
					 }	 
						 
//			         IF ((( p_disp_date IS NULL) AND (p_debt_coll_date IS NOT NULL)) AND	(p_debt_collection = 'Y' OR p_debt_collection = 'S')) THEN
					 if ((returnValue.p_disp_date == null && returnValue.p_debt_coll_date != null) && ("Y".equals(returnValue.p_debt_collection) || "S".equals(returnValue.p_debt_collection))) {
						 
//			            LET p_disp_date = p_debt_coll_date;
						returnValue.p_disp_date = returnValue.p_debt_coll_date;
						 
//			         END IF;
					 }
					   
//			       END IF;
				   }
				   
//			    ELSE
				} else {
					
//			       LET pg_errlog_info = "calc_pending.03";
//			       SELECT min(date(jdmt_datetime)) INTO p_jdmt_date
//			       FROM judgment
//			       WHERE int_case_num = p_int_case_num
//			       AND (disp_code IS NULL OR disp_code NOT IN ("AS", "WI", "WD"));
				   returnValue.p_jdmt_date = (Date) new JudgmentBO(courtType)
				   										.includeFields(new Expression(Exp.MIN, Exp.LEFT_PARENTHESIS, Exp.date(JudgmentBO.JDMTDATETIME), Exp.RIGHT_PARENTHESIS).as("jdmt_datetime"))
				   										.where(JudgmentBO.INTCASENUM, returnValue.p_int_case_num)
				   										.where(Exp.LEFT_PARENTHESIS, JudgmentBO.DISPCODE, Exp.IS_NULL, Exp.OR, JudgmentBO.DISPCODE, Exp.NOT_IN, new StringArrayDescriptor("AS", "WI", "WD"), Exp.RIGHT_PARENTHESIS)
				   										.find()
				   										.get("jdmt_datetime");	
					
//			       -- if set ASide or WIthdrawn, don't count it.
//			       IF p_case_disp_date IS NULL THEN
				   if (returnValue.p_case_disp_date == null) {
					   
//			           IF p_jdmt_date IS NULL THEN
					   if (returnValue.p_case_disp_date == null) {
						   
//			               IF p_case_type = "AJ" OR p_case_type = "TL" OR p_case_type = "SL" OR p_case_type = "WL" OR p_case_type = "HL" THEN
						   if ("AJ".equals(returnValue.p_case_type) || "TL".equals(returnValue.p_case_type) || "SL".equals(returnValue.p_case_type) || "WL".equals(returnValue.p_case_type) || "HL".equals(returnValue.p_case_type)) {
							   
//			                 LET p_disp_date = p_filing_date;
							 returnValue.p_disp_date = returnValue.p_filing_date; 
							   
//			               ELSE
					   	   } else {
					   		   
//			                 LET p_disp_date = NULL;
					   		 returnValue.p_disp_date = null;
						     
//			               END IF;
					   	   }
					   
//			           ELSE
					   } else {
						   
//			               LET p_disp_date = p_jdmt_date;
						   returnValue.p_disp_date = returnValue.p_jdmt_date;
						   
//			           END IF;
					   }
					   
//			       ELSE
				   } else {
					   
//			           IF p_jdmt_date IS NULL THEN
					   if (returnValue.p_jdmt_date == null) {
						   
//			              LET p_disp_date = p_case_disp_date;
						  returnValue.p_disp_date = returnValue.p_case_disp_date; 
						   
//			           ELSE
					   } else {
						   
//			              IF p_jdmt_date < p_case_disp_date THEN
						  if (returnValue.p_jdmt_date.before(returnValue.p_case_disp_date)) {
						   
//			                  LET p_disp_date = p_jdmt_date;
							  returnValue.p_disp_date = returnValue.p_jdmt_date;
						   
//			              ELSE
					      } else {
					    	  
//			                  LET p_disp_date = p_case_disp_date;
					    	  returnValue.p_disp_date = returnValue.p_case_disp_date;
						   
//			              END IF;
					      }
					   
//			           END IF;
					   }
					   
//			       END IF;
				   }
					
//			    END IF;
				}
				
//			    IF p_disp_date IS NOT NULL THEN
				if (returnValue.p_disp_date != null) {
					
//			       -- get the disposition judge/comm
//			       LET pg_errlog_info = "calc_pending.05";
//			       IF p_disp_id IS NULL THEN
				   if (returnValue.p_disp_id == null) {
					   
//			           SELECT min(judge_id) INTO p_disp_id 
//			           FROM judge_hist
//			           WHERE int_case_num = p_int_case_num
//			           AND start_date = (select max(start_date) FROM judge_hist jh2
//			                             where jh2.int_case_num = p_int_case_num
//			                             and jh2.start_date <= p_disp_date);
					   returnValue.p_int_case_num = new JudgeHistBO(courtType)
					   									.min(JudgeHistBO.JUDGEID)
					   									.where(JudgeHistBO.INTCASENUM, returnValue.p_int_case_num)
					   									.where(
					   										JudgeHistBO.STARTDATE, Exp.EQUALS, Exp.select(
					   											new JudgeHistBO().as("jh2")
					   											.max(JudgeHistBO.STARTDATE)
					   											.where(new TableAndFieldDescriptor("jh2", JudgeHistBO.INTCASENUM), Exp.EQUALS, returnValue.p_int_case_num.intValue())
					   											.where(new TableAndFieldDescriptor("jh2", JudgeHistBO.STARTDATE), Exp.LESS_THAN_OR_EQUAL_TO, returnValue.p_disp_date)
					   										)
					   									)
					   									.find()
					   									.getJudgeId();
					   
					   
//			           LET pg_errlog_info = "calc_pending.06";
//			           IF p_disp_id IS NULL THEN
					   if (returnValue.p_disp_id == null) {
						   
//			              IF p_assn_judge_id IS NOT NULL THEN
						  if (returnValue.p_assn_judge_id != null) {
							  
//			                  LET p_disp_id = p_assn_judge_id;
							  returnValue.p_disp_id = returnValue.p_assn_judge_id;
							  
//			              ELSE
						  } else {
							  
//			                IF p_assn_comm_id IS NOT NULL THEN
							if (returnValue.p_assn_comm_id != null) {
							  
//			                  LET p_disp_id = p_assn_comm_id;
							  returnValue.p_disp_id = returnValue.p_assn_comm_id;
							  
//			                END IF;
							}
							  
//			              END IF
						  }  
						   
//			           END IF;
					   }
					   
//			       END IF;
				   }	   
					
//			    END IF;
				}
			    
//			    LET pg_errlog_info = "calc_pending.07";
//			    LET p_tot_stay_days = 0;
				returnValue.p_tot_stay_days = 0;
				
//			    IF p_disp_date IS NULL THEN
				if (returnValue.p_disp_date == null) {
					
//			        LET p_days_since_filed = p_report_date - p_filing_date;
//			        LET p_finish_date = p_report_date;
					returnValue.p_days_since_filed = DateUtil.getDaysBetween(p_report_date, returnValue.p_filing_date);
					returnValue.p_finish_date = p_report_date;
					
//			    ELSE
				} else {	
					
//			        IF p_disp_date > p_report_date THEN
					if (returnValue.p_disp_date != null && returnValue.p_disp_date.after(p_report_date)) {
						
//			            LET p_days_since_filed = p_report_date - p_filing_date;
//			            LET p_disp_date = NULL;    -- to report it as undisposed as of the report date.
//			            LET p_finish_date = p_report_date;
						returnValue.p_days_since_filed = DateUtil.getDaysBetween(p_report_date, returnValue.p_filing_date);
						returnValue.p_disp_date = null;
						returnValue.p_finish_date = p_report_date;
						
//			        ELSE
				    } else {
				    	
//			            LET p_days_since_filed = p_disp_date - p_filing_date;
//			            LET p_finish_date = p_disp_date;
						returnValue.p_days_since_filed = DateUtil.getDaysBetween(returnValue.p_disp_date, returnValue.p_filing_date);
						returnValue.p_finish_date = returnValue.p_disp_date;
					
//			        END IF;
					}
				
//			    END IF;
				}
					
//			    LET pg_errlog_info = "calc_pending.08";
//			    SELECT min (issue_date) into p_min_warr_date
//			    FROM warrant
//			    WHERE int_case_num = p_int_case_num
//			    AND issue_date < p_finish_date
//			    AND (recall_date IS NULL OR issue_date < recall_date);
				returnValue.p_min_warr_date = (Date) new WarrantBO(courtType)
														.min(WarrantBO.ISSUEDATE)
														.where(WarrantBO.INTCASENUM, returnValue.p_int_case_num)
														.where(WarrantBO.ISSUEDATE, Exp.LESS_THAN, returnValue.p_finish_date)
														.where(Exp.LEFT_PARENTHESIS, WarrantBO.RECALLDATE, Exp.IS_NULL, Exp.OR, WarrantBO.ISSUEDATE, Exp.LESS_THAN, WarrantBO.RECALLDATE, Exp.RIGHT_PARENTHESIS)
											  			.find()
											  			.getMin();
				
//			    IF p_min_warr_date IS NULL THEN
				if (returnValue.p_min_warr_date == null) {
					
//			      LET p_warr_count = 0;
				  returnValue.p_warr_count = 0;
					
//			    ELSE
				} else {
					
//			      LET p_warr_count = 1;
				  returnValue.p_warr_count = 1;
					
//			    END IF;
				}
			    
//			    LET pg_errlog_info = "calc_pending.09";
//			    SELECT count(*)
//			    INTO p_stay_count
//			    FROM stay
//			    WHERE int_case_num = p_int_case_num
//			    AND begin_date < p_finish_date;
				returnValue.p_stay_count = new StayBO(courtType)
											.count(StayBO.ALL_FIELDS)
											.where(StayBO.INTCASENUM, returnValue.p_int_case_num)
											.where(StayBO.BEGINDATE, Exp.LESS_THAN, returnValue.p_finish_date)
											.find()
											.getCount();
				
			    
//			    IF p_stay_count > 0 THEN
				if (returnValue.p_stay_count > 0) {
					
//			        LET pg_errlog_info = "calc_pending.10";
//			        -- loop through stays, subtracting them out,
//			        --  and checking for overlapping warrants at the same time.
//			        LET p_hold_stay_end = "01/01/1900";
					returnValue.p_hold_stay_end = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1900");
					
//			        FOREACH c_loop_stays FOR
//			            SELECT begin_date, end_date
//			            INTO p_stay_beg_date, p_stay_end_date
//			            FROM stay
//			            WHERE int_case_num = p_int_case_num
//			            AND begin_date < p_finish_date
//			            ORDER by 1, 2
			            
					List<StayBO> c_loop_stays = new StayBO(courtType).where(StayBO.INTCASENUM, returnValue.p_int_case_num).where(StayBO.BEGINDATE, Exp.LESS_THAN, returnValue.p_finish_date).orderBy(1,2).search(StayBO.BEGINDATE, StayBO.ENDDATE);
					if (c_loop_stays.size() > 0) {
						for (StayBO stayBO : c_loop_stays) {
							returnValue.p_stay_beg_date = stayBO.getBeginDate();
							returnValue.p_stay_end_date = stayBO.getEndDate();
						
	//			            LET pg_errlog_info = "calc_pending.11";
	//			            -- deal with possibly overlapping stays:
	//			            IF p_stay_end_date IS NULL OR p_stay_end_date > p_finish_date THEN
							if (returnValue.p_stay_end_date == null || returnValue.p_stay_end_date.after(returnValue.p_finish_date)) {
								
	//			                LET p_stay_end_date = p_finish_date;
								returnValue.p_stay_end_date = returnValue.p_finish_date;
								
	//			            END IF;
							}
							
	//			            IF p_stay_end_date <= p_hold_stay_end THEN
							if (returnValue.p_stay_end_date.before(returnValue.p_hold_stay_end)) {
								
	//			                CONTINUE FOREACH;
								continue;
							
	//			            END IF;
							}
						
	//			            IF p_stay_beg_date < p_hold_stay_end THEN
							if (returnValue.p_stay_beg_date.before(returnValue.p_hold_stay_end)) {
								
	//			               LET p_stay_beg_date = p_hold_stay_end;
							   returnValue.p_stay_beg_date = returnValue.p_hold_stay_end;
								
	//			            END IF;
							}
							
	//			            IF p_stay_beg_date < p_filing_date THEN
							if (returnValue.p_stay_beg_date.before(returnValue.p_filing_date)) {
							
	//			               LET p_stay_beg_date = p_filing_date;
								returnValue.p_stay_beg_date = returnValue.p_filing_date;
							
	//			            END IF;
						    }
				            
	//			            -- now check for warrants
	//			            IF p_min_warr_date IS NOT NULL THEN
							if (returnValue.p_min_warr_date != null) {
								
	//			                LET pg_errlog_info = "calc_pending.15";
	//			                IF p_min_warr_date < p_stay_beg_date THEN
								if (returnValue.p_min_warr_date.before(returnValue.p_stay_beg_date)) {
									
	//			                    -- need to subtract out all warrants issued prior to end of this stay
	//			                    FOREACH c_get_prior_warr FOR
	//			                        SELECT issue_date, recall_date
	//			                        INTO p_warr_issue_date, p_warr_recall_date
	//			                        FROM warrant
	//			                        WHERE int_case_num = p_int_case_num
	//			                        AND issue_date >= p_min_warr_date
	//			                        AND issue_date < p_stay_beg_date
	//			                        AND (recall_date IS NULL OR issue_date < recall_date)
	//			                        ORDER BY 1, 2
	//			                        LET pg_errlog_info = "calc_pending.16";
	//			                        IF p_warr_recall_date IS NULL OR p_warr_recall_date > p_finish_date THEN
	//			                           LET p_warr_recall_date = p_finish_date;
	//			                           LET p_stay_beg_date = p_finish_date;
	//			                           LET p_stay_end_date = p_finish_date;
	//			                        ELSE
	//			                          IF p_warr_recall_date > p_stay_beg_date THEN
	//			                            LET p_stay_beg_date = p_warr_recall_date;
	//			                          END IF;
	//			                          IF p_warr_recall_date > p_stay_end_date THEN
	//			                            LET p_stay_end_date = p_warr_recall_date;
	//			                          END IF                    
	//			                        END IF;
	//			                        LET p_temp_daycount = p_warr_recall_date - p_warr_issue_date;
	//			                        LET p_tot_stay_days = p_tot_stay_days + p_temp_daycount;
	//			                    END FOREACH;
									
									List<WarrantBO> warrants = new WarrantBO(courtType)
																.where(WarrantBO.INTCASENUM, returnValue.p_int_case_num)
																.where(WarrantBO.ISSUEDATE, Exp.GREATER_THAN_OR_EQUAL_TO, returnValue.p_stay_beg_date)
																.where(Exp.LEFT_PARENTHESIS, WarrantBO.RECALLDATE, Exp.IS_NULL, Exp.OR, WarrantBO.ISSUEDATE, Exp.LESS_THAN, WarrantBO.RECALLDATE, Exp.RIGHT_PARENTHESIS)
																.orderBy(1,2)
																.search(WarrantBO.ISSUEDATE, WarrantBO.RECALLDATE);
									
									if (warrants.size() > 0) {
										for (WarrantBO warrant : warrants) {
											returnValue.p_warr_issue_date = warrant.getIssueDate();
											returnValue.p_warr_recall_date = warrant.getRecallDate();
											
											if (returnValue.p_warr_recall_date == null || returnValue.p_warr_recall_date.after(returnValue.p_finish_date)) {
												returnValue.p_warr_recall_date = returnValue.p_finish_date;
												returnValue.p_stay_beg_date = returnValue.p_finish_date;
												returnValue.p_stay_end_date = returnValue.p_finish_date;
											} else {
												if (returnValue.p_warr_recall_date != null && returnValue.p_warr_recall_date.after(returnValue.p_stay_beg_date)) {
													returnValue.p_stay_beg_date = returnValue.p_warr_recall_date; 
												}
												if (returnValue.p_warr_recall_date != null && returnValue.p_warr_recall_date.after(returnValue.p_stay_end_date)) {
													returnValue.p_stay_end_date = returnValue.p_warr_recall_date; 
												}
											}
											returnValue.p_temp_daycount = DateUtil.getDaysBetween(returnValue.p_warr_recall_date, returnValue.p_warr_issue_date);
											returnValue.p_tot_stay_days = returnValue.p_tot_stay_days + returnValue.p_temp_daycount;
										}
									}
									warrants = null;
									
	//			                    LET pg_errlog_info = "calc_pending.17";
	//			                    SELECT min (issue_date) into p_min_warr_date
	//			                    FROM warrant
	//			                    WHERE int_case_num = p_int_case_num
	//			                    AND issue_date < p_finish_date
	//			                    AND issue_date >= p_stay_beg_date
	//			                    AND (   recall_date IS NULL 
	//			                         OR (issue_date < recall_date
	//			                             AND recall_date > p_stay_end_date) );
									returnValue.p_min_warr_date = (Date) new WarrantBO(courtType)
																			.min(WarrantBO.ISSUEDATE)
																			.where(WarrantBO.INTCASENUM, returnValue.p_int_case_num)
																			.where(WarrantBO.ISSUEDATE, Exp.LESS_THAN, returnValue.p_finish_date)
																			.where(WarrantBO.ISSUEDATE, Exp.GREATER_THAN_OR_EQUAL_TO, returnValue.p_stay_beg_date)
																			.where(
																				Exp.LEFT_PARENTHESIS, 
																					WarrantBO.RECALLDATE, Exp.IS_NULL, 
																					Exp.OR, 
																					Exp.LEFT_PARENTHESIS,
																						WarrantBO.ISSUEDATE, Exp.LESS_THAN, WarrantBO.RECALLDATE,
																						Exp.AND,
																						WarrantBO.RECALLDATE, Exp.GREATER_THAN, returnValue.p_stay_end_date,
																					Exp.RIGHT_PARENTHESIS,
																				Exp.RIGHT_PARENTHESIS
																			)
																			.find()
																			.getMin();
									
									
	//			                END IF;
								}
							
	//			                IF p_min_warr_date IS NOT NULL THEN
								if (returnValue.p_min_warr_date != null) {
								
	//			                  IF p_min_warr_date > p_stay_end_date THEN
								  if (returnValue.p_min_warr_date != null && returnValue.p_min_warr_date.after(returnValue.p_stay_end_date)) {
									  
	//			                    -- warrant didn't start in this stay period, process it next time.
									  
	//			                  ELSE
								  } else {
									  
	//			                    -- warrant started during this stay
	//			                    LET pg_errlog_info = "calc_pending.20";
	//			                    LET p_warr_issue_date = p_min_warr_date;
	//			                    SELECT count(*) INTO p_count
	//			                    FROM warrant
	//			                    WHERE int_case_num = p_int_case_num
	//			                    AND issue_date = p_min_warr_date
	//			                    AND recall_date IS NULL;
									returnValue.p_count = new WarrantBO(courtType)
															.count(WarrantBO.ALL_FIELDS)
															.where(WarrantBO.INTCASENUM, returnValue.p_int_case_num)
															.where(WarrantBO.ISSUEDATE, returnValue.p_min_warr_date)
															.where(WarrantBO.RECALLDATE, Exp.IS_NULL)
															.find()
															.getCount();
									  
									  
	//			                    IF p_count > 0 THEN
									if (returnValue.p_count > 0) {
										
	//			                       LET p_warr_recall_date = NULL;
									   returnValue.p_warr_recall_date = null;
										
	//			                    ELSE
									} else {
										
	//			                      LET pg_errlog_info = "calc_pending.21";
	//			                      SELECT max(recall_date)
	//			                      INTO p_warr_recall_date
	//			                      FROM warrant
	//			                      WHERE int_case_num = p_int_case_num
	//			                      AND issue_date = p_min_warr_date
	//			                      AND issue_date < recall_date;
									  returnValue.p_warr_recall_date = (Date) new WarrantBO(courtType)
																				.max(WarrantBO.RECALLDATE)
																				.where(WarrantBO.INTCASENUM, returnValue.p_int_case_num)
																				.where(WarrantBO.ISSUEDATE, returnValue.p_min_warr_date)
																				.where(WarrantBO.ISSUEDATE, Exp.LESS_THAN, WarrantBO.RECALLDATE)
																				.find()
																				.getMax();
										
	//			                    END IF;
									}	
										
	//			                    LET pg_errlog_info = "calc_pending.25";
	//			                    IF p_warr_issue_date > p_stay_beg_date THEN
									if (returnValue.p_warr_issue_date != null && returnValue.p_warr_issue_date.after(returnValue.p_stay_beg_date)) {
									
	//			                        -- count stay days till warrant started
	//			                        LET p_temp_daycount = p_warr_issue_date - p_stay_beg_date;
	//			                        LET p_tot_stay_days = p_tot_stay_days + p_temp_daycount;
										returnValue.p_temp_daycount = DateUtil.getDaysBetween(returnValue.p_warr_issue_date, returnValue.p_stay_beg_date);
										returnValue.p_tot_stay_days = returnValue.p_tot_stay_days + returnValue.p_temp_daycount;
										
	//			                    END IF
									}
									
	//			                    IF p_warr_recall_date IS NULL OR p_warr_recall_date > p_finish_date THEN
									if (returnValue.p_warr_recall_date == null || returnValue.p_warr_recall_date.after(returnValue.p_finish_date)) {
									
	//			                        LET p_warr_recall_date = p_finish_date;
	//			                        LET p_stay_beg_date = p_finish_date;
	//			                        LET p_stay_end_date = p_finish_date;
										returnValue.p_warr_recall_date = returnValue.p_finish_date;
										returnValue.p_stay_beg_date = returnValue.p_finish_date;
										returnValue.p_stay_end_date = returnValue.p_finish_date;
										
	//			                    ELSE
									} else {
										
	//			                        IF p_warr_recall_date > p_stay_beg_date THEN
										if (returnValue.p_warr_recall_date != null && returnValue.p_warr_recall_date.after(returnValue.p_stay_beg_date)) {
											
	//			                            LET p_stay_beg_date = p_warr_recall_date;
											returnValue.p_stay_beg_date = returnValue.p_warr_recall_date;
											
	//			                        END IF;
										}
										
	//			                        IF p_warr_recall_date > p_stay_end_date THEN
										if (returnValue.p_warr_recall_date != null && returnValue.p_warr_recall_date.after(returnValue.p_stay_end_date)) {
										
	//			                          LET p_stay_end_date = p_warr_recall_date;
										  returnValue.p_stay_end_date = returnValue.p_warr_recall_date;
										
	//			                        END IF
										}
										
	//			                    END IF;
									}
									
	//			                    LET pg_errlog_info = "calc_pending.28";
	//			                    LET p_temp_daycount = p_warr_recall_date - p_warr_issue_date;
	//			                    LET p_tot_stay_days = p_tot_stay_days + p_temp_daycount;
									returnValue.p_temp_daycount = DateUtil.getDaysBetween(returnValue.p_warr_recall_date, returnValue.p_warr_issue_date);
									returnValue.p_tot_stay_days = returnValue.p_tot_stay_days + returnValue.p_temp_daycount;
									
	//			                    SELECT min (issue_date) into p_min_warr_date
	//			                    FROM warrant
	//			                    WHERE int_case_num = p_int_case_num
	//			                    AND issue_date < p_finish_date
	//			                    AND issue_date >= p_stay_beg_date
	//			                    AND (   recall_date IS NULL 
	//			                         OR (issue_date < recall_date
	//			                             AND recall_date > p_stay_end_date) );    
									returnValue.p_min_warr_date = (Date) new WarrantBO(courtType)
																			.min(WarrantBO.ISSUEDATE)
																			.where(WarrantBO.INTCASENUM, returnValue.p_int_case_num)
																			.where(WarrantBO.ISSUEDATE, Exp.LESS_THAN, returnValue.p_finish_date)
																			.where(
																				WarrantBO.RECALLDATE, Exp.IS_NULL,
																				Exp.OR,
																				Exp.LEFT_PARENTHESIS,
																					WarrantBO.ISSUEDATE, Exp.LESS_THAN, WarrantBO.RECALLDATE,
																					Exp.AND,
																					WarrantBO.RECALLDATE, Exp.GREATER_THAN, returnValue.p_stay_end_date,
																				Exp.RIGHT_PARENTHESIS
																			)
																			.find()
																			.getMin();
									
	//			                     
	//			                  END IF;
								  } // if (returnValue.p_min_warr_date.after(returnValue.p_stay_end_date)) 
									
	//			                END IF;
								} // if (returnValue.p_min_warr_date != null) 
								
	//			            END IF;
							} // if (returnValue.p_min_warr_date != null)	
							
	//			            LET p_temp_daycount = p_stay_end_date - p_stay_beg_date;
	//			            LET p_tot_stay_days = p_tot_stay_days + p_temp_daycount;
	//			            LET p_hold_stay_end = p_stay_end_date;
							returnValue.p_temp_daycount = DateUtil.getDaysBetween(returnValue.p_stay_beg_date, returnValue.p_stay_beg_date);
							returnValue.p_tot_stay_days = returnValue.p_tot_stay_days + returnValue.p_temp_daycount;
							returnValue.p_hold_stay_end = returnValue.p_stay_end_date;
							
	//			        END FOREACH;
						} // for
					} // if (c_loop_stays.size() > 0)
						
						
//			        -- finish up any remaining warrant data
//			        IF p_min_warr_date IS NOT NULL THEN
					if (returnValue.p_min_warr_date != null) {
					
//			           LET pg_errlog_info = "calc_pending.30";
//			           FOREACH c_loop_warrants FOR
//			              SELECT issue_date, recall_date
//			              INTO p_warr_issue_date, p_warr_recall_date
//			              FROM warrant
//			              WHERE int_case_num = p_int_case_num
//			              AND issue_date >= p_min_warr_date
//			              AND issue_date < p_finish_date
//			              AND (recall_date IS NULL 
//			                  OR issue_date < recall_date)
//			              ORDER BY 1, 2
//			              
//			              LET pg_errlog_info = "calc_pending.31";
//			              IF p_warr_recall_date IS NULL OR p_warr_recall_date > p_finish_date THEN
//			                  LET p_temp_daycount = p_finish_date - p_warr_issue_date;
//			              ELSE
//			                  LET p_temp_daycount = p_warr_recall_date - p_warr_issue_date;
//			              END IF;
//			              LET p_tot_stay_days = p_tot_stay_days + p_temp_daycount;
//			          END FOREACH;
					  List<WarrantBO> c_loop_warrants = new WarrantBO(courtType)
					  										.where(WarrantBO.INTCASENUM, returnValue.p_int_case_num)
					  										.where(WarrantBO.ISSUEDATE, Exp.GREATER_THAN_OR_EQUAL_TO, returnValue.p_min_warr_date)
					  										.where(
					  											Exp.LEFT_PARENTHESIS,
					  												WarrantBO.RECALLDATE, Exp.IS_NULL,
					  												Exp.OR,
					  												WarrantBO.ISSUEDATE, Exp.LESS_THAN, WarrantBO.RECALLDATE,
					  											Exp.RIGHT_PARENTHESIS
					  										)
					  										.orderBy(1,2)
					  										.search(WarrantBO.ISSUEDATE, WarrantBO.RECALLDATE);
					  if (c_loop_warrants.size() > 0) {
						  for (WarrantBO warrant : c_loop_warrants) {
							  returnValue.p_warr_issue_date = warrant.getIssueDate();
							  returnValue.p_warr_recall_date = warrant.getRecallDate();
							  
							  if (returnValue.p_warr_recall_date == null || returnValue.p_warr_recall_date.after(returnValue.p_finish_date)) {
								  returnValue.p_temp_daycount = DateUtil.getDaysBetween(returnValue.p_finish_date, returnValue.p_warr_issue_date);
							  } else {
								  returnValue.p_temp_daycount = DateUtil.getDaysBetween(returnValue.p_warr_recall_date, returnValue.p_warr_issue_date);
							  }
							  returnValue.p_tot_stay_days = returnValue.p_tot_stay_days + returnValue.p_temp_daycount; 
						  }
					  }
					  c_loop_warrants = null;
					
//			        END IF;
					}
				
//			    ELSE
				} else {	
					
//			       IF p_warr_count > 0 THEN
				   if (returnValue.p_warr_count > 0) {	
					
//			          -- subtract out warrants only.
//			          LET pg_errlog_info = "calc_pending.40";
//			          FOREACH c_loop_warrants FOR
//			              SELECT issue_date, recall_date
//			              INTO p_warr_issue_date, p_warr_recall_date
//			              FROM warrant
//			              WHERE int_case_num = p_int_case_num
//			              AND issue_date < p_finish_date
//			              AND (recall_date IS NULL
//			                  OR issue_date < recall_date)
//			              ORDER BY 1, 2
//			              
//			              LET pg_errlog_info = "calc_pending.41";
//			              IF p_warr_recall_date IS NULL OR p_warr_recall_date > p_finish_date THEN
//			                  LET p_temp_daycount = p_finish_date - p_warr_issue_date;
//			              ELSE
//			                  LET p_temp_daycount = p_warr_recall_date - p_warr_issue_date;
//			              END IF;
//			              LET p_tot_stay_days = p_tot_stay_days + p_temp_daycount;
//			          END FOREACH;
					  List<WarrantBO> c_loop_warrants = new WarrantBO(courtType)
					  									.where(WarrantBO.INTCASENUM, returnValue.p_int_case_num)
					  									.where(WarrantBO.ISSUEDATE, Exp.LESS_THAN, returnValue.p_finish_date)
					  									.where(
					  										Exp.LEFT_PARENTHESIS,
					  											WarrantBO.RECALLDATE, Exp.IS_NULL,
					  											Exp.OR,
					  											WarrantBO.ISSUEDATE, Exp.LESS_THAN, WarrantBO.RECALLDATE,
					  										Exp.RIGHT_PARENTHESIS
					  									)
					  									.orderBy(1,2)
					  									.search(WarrantBO.ISSUEDATE, WarrantBO.RECALLDATE);
					  
					  if (c_loop_warrants.size()> 0) {
						  for (WarrantBO warrant : c_loop_warrants) {
							  returnValue.p_warr_issue_date = warrant.getIssueDate();
							  returnValue.p_warr_recall_date = warrant.getRecallDate();
							  
							  if (returnValue.p_warr_recall_date == null || returnValue.p_warr_recall_date.after(returnValue.p_finish_date)) {
								  returnValue.p_temp_daycount = DateUtil.getDaysBetween(returnValue.p_finish_date, returnValue.p_warr_issue_date);
							  } else {
								  returnValue.p_temp_daycount = DateUtil.getDaysBetween(returnValue.p_warr_recall_date, returnValue.p_warr_issue_date);
							  }
							  returnValue.p_tot_stay_days = returnValue.p_tot_stay_days + returnValue.p_temp_daycount;
						  }
					  }
					  c_loop_warrants = null;
					   
//			       END IF
				   }
					
//			    END IF;
				}  // if (returnValue.p_stay_count > 0)
			    
//			    LET pg_errlog_info = "calc_pending.50";
//			    LET p_tot_disp_days = p_days_since_filed - p_tot_stay_days;
				returnValue.p_tot_disp_days = returnValue.p_days_since_filed - returnValue.p_tot_stay_days;
				
//			    IF p_status = 0 THEN
//			        -- yes it makes no sense to return int_case_num since it was passed in... but the 
//			        -- consultants for Court Services working with COGNOS say they needed it.
//			        RETURN p_status, p_int_case_num, p_disp_date, p_disp_id, p_tot_stay_days, p_tot_disp_days;
//			    END IF;
				
				returnValuesList.add(returnValue);
				returnValue = null;
				
//			}  
//		}
				
		return returnValuesList;
	}
	
	public static void main(String[] args) {
		DatabaseConnection.setUseJdbc();
		try {
			
			// CalcPendingSP.calcPending(70147, new IntegerArrayDescriptor(544521, 5441), new Date(), "D_READONLY");
			
		} catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

}

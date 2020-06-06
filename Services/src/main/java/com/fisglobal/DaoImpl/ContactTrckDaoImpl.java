package com.fisglobal.DaoImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.time.StopWatch;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fisglobal.Dao.ContactTrckDao;
import com.fisglobal.requestbo.ContactTrckSvcReq;
import com.fisglobal.responsebo.ContactTrckSvcResp;
import com.fisglobal.responsebo.MstrResp;
import com.fisglobal.responsebo.TraceDtls;
import com.fisglobal.responsebo.TraceRowMapper;

@Repository
@Transactional
public class ContactTrckDaoImpl implements ContactTrckDao {
	
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public ContactTrckSvcResp getContactTrckDtls(ContactTrckSvcReq req) {
		
		logger.info("ContactTrckDaoImpl() - getContactTrckDtls - Starts ");
		ContactTrckSvcResp traceResp = new ContactTrckSvcResp();
		
		List<TraceDtls> traceDtls = getTraceDtls(req);
		traceResp.setTraceDtl(traceDtls);
		
		if (traceDtls!= null && traceDtls.size()> 0){
			traceResp.setRspCde("000");
			traceResp.setRspMsg("Success");
		}
		logger.info("ContactTrckDaoImpl() - getContactTrckDtls - ends ");
		return traceResp;
	}
	
	 public List<TraceDtls> getTraceDtls(ContactTrckSvcReq req) {
		 logger.info("ContactTrckDaoImpl() - getTraceDtls - Starts *** StopWatch");
		 
		 StopWatch stopWatch = new StopWatch();
		 		 
		 String sql = "SELECT * FROM NVLTY_AUDT WHERE EID = ?";
	        Object[] args = {req.getEid()};
	        stopWatch.start();
	        @SuppressWarnings("unchecked")
	    	List<TraceDtls> traceLst = jdbcTemplate.query(sql, args, new TraceRowMapper());
	        stopWatch.stop();
	        
	        logger.info("ContactTrckDaoImpl() - getTraceDtls - ends *** " + stopWatch.getTime());
	        return traceLst;
	    }
	 
	 public ContactTrckSvcResp getContactTrckDtls(String eid) {
		 logger.info("ContactTrckDaoImpl() - getTraceDtls - Starts ");
			ContactTrckSvcResp traceResp = new ContactTrckSvcResp();
			
			List<TraceDtls> traceDtls = getTraceDtls(eid);
			traceResp.setTraceDtl(traceDtls);

			if (traceDtls != null && traceDtls.size() > 0) {
				traceResp.setRspCde("000");
				traceResp.setRspMsg("Success");

			}
			logger.info("ContactTrckDaoImpl() - getTraceDtls - Starts ");
			return traceResp;
		}
	 
	 public List<TraceDtls> getTraceDtls(String eid) {
		 logger.info("ContactTrckDaoImpl() - getTraceDtls - Starts *** StopWatch");
		 StopWatch stopWatch = new StopWatch();
		 
			String sql = "SELECT * FROM NVLTY_AUDT WHERE EID = ?";
			Object[] args = { eid };
		
			stopWatch.start();
			@SuppressWarnings("unchecked")
			List<TraceDtls> traceLst = jdbcTemplate.query(sql, args, new TraceRowMapper());
			stopWatch.stop();
			logger.info("ContactTrckDaoImpl() - getTraceDtls - Starts ***" + stopWatch.getTime() + "*** traceList size"+ traceLst.size() );
			return traceLst;
		}
	 
	 public ContactTrckSvcResp insertContactTrckDtls(String eid, String flrId, String band, String nr_eid) {
		 logger.info("ContactTrckDaoImpl() - insertContactTrckDtls - Starts *** StopWatch");
			ContactTrckSvcResp contactResp = new ContactTrckSvcResp();

			ContactTrckSvcReq contactReq = new ContactTrckSvcReq();
			contactReq.setEid(eid);
			contactReq.setLoc("GDY");
			contactReq.setFlrId(flrId);
			contactReq.setNreid(nr_eid);

			contactReq.setBand(band);

			// TODO - select query to get seatno, band, phno for nearbyEid
			MstrResp mstrResp = getDtlsFrmMstr(nr_eid);
			contactReq.setNrseatnbr(mstrResp.getSeatNbr());// nrseatnbr);
			contactReq.setNrband(mstrResp.getBand());// nrband);
			contactReq.setNreid_ph_no(mstrResp.getPhnNbr());// nreid_ph_no);

			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
			String strDate = dateFormat.format(date);
			contactReq.setDt(strDate);
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
			// insertActor.withTableName("NVLTY_AUDT").usingColumns("EID", "LOC",
			// "FLRID","NREID","DT","SEATNBR","BAND","NRSEATNBR","NRBAND","NREID_PH_NO");
			BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(contactReq);

			int rtnCd = insertActor.execute(param);
			stopWatch.stop();

			if (rtnCd > 0) {
				contactResp.setRspCde("000");
				contactResp.setRspMsg("Success");

			} else {
				contactResp.setRspCde("999");
				contactResp.setRspMsg("Failure");
			}
			logger.info("ContactTrckDaoImpl() - insertContactTrckDtls - ends ***" + stopWatch.getTime());
			return contactResp;

		}

		public MstrResp getDtlsFrmMstr(String eId) {
			logger.info("ContactTrckDaoImpl() - getDtlsFrmMstr - Starts ");
			String sql = "SELECT * FROM NVLTY_MSTR WHERE EID = ?";
			Object[] args = { eId };
			MstrResp mstrResp = jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(MstrResp.class));
			logger.info("ContactTrckDaoImpl() - getDtlsFrmMstr - ends ");
			return mstrResp;
		}


}

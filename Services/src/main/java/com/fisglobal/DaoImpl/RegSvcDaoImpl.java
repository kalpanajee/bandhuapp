package com.fisglobal.DaoImpl;

import org.apache.commons.lang.time.StopWatch;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fisglobal.Dao.RegSvcDao;
import com.fisglobal.requestbo.RegSvcReq;
import com.fisglobal.responsebo.MstrResp;
import com.fisglobal.responsebo.RegSvcResp;

@Repository
@Transactional
public class RegSvcDaoImpl implements RegSvcDao {
	
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public RegSvcResp insertRegReq(RegSvcReq req) {
		logger.info("RegSvcDaoImpl() - insertRegReq - Starts ");
		RegSvcResp aResp = new RegSvcResp();
		
		MstrResp mstrResp=new MstrResp();
		
		if(req.getSvcTyp().equals("login")){
			System.out.println("login");
			//get db call to register table to check if it is registered already and send the same output
			mstrResp = getDtlsFrmMstr(req.getEId().toUpperCase(),"L");
		}
		
		mstrResp = getDtlsFrmMstr(req.getEId().toUpperCase(),"R");
		System.out.println("MASTER RESPONSE GOT");
		
		req.setLocId((!mstrResp.getLocId().equals(""))?mstrResp.getLocId().toUpperCase():"");
		req.setFlrNbr((!mstrResp.getFlrNbr().equals(""))?mstrResp.getFlrNbr():"");
		req.setEmpTyp((!mstrResp.getEmpTyp().equals(""))?mstrResp.getEmpTyp().toUpperCase():"");
		req.setEnable("Y"); // setting default to Y now.
		req.setUsrTyp((!mstrResp.getUsrTyp().equals(""))?mstrResp.getUsrTyp().toUpperCase():"");
		req.setBand((!mstrResp.getBand().equals(""))?mstrResp.getBand().toUpperCase():"");
		req.setSeatNbr((!mstrResp.getSeatNbr().equals(""))?mstrResp.getSeatNbr().toUpperCase():"");
//		req.setSeatNbr((!mstrResp.getClrCd().equals(""))?mstrResp.getClrCd():"");
		
		if(mstrResp != null && !mstrResp.getPhnNbr().equals("")){
			req.setVldUsr("Y"); // if the user is present in mstr, then default to Y else error out. it cant be registered
			System.out.println("REQ" + req.getDvcId()+","+req.getEId()+","+req.getEmpTyp()+","+req.getEnable()+","+req.getFlrNbr()+","+req.getLocId()+","+req.getPhnNbr()+","+req.getUsrTyp()+","+req.getVldUsr());
			
			SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
			insertActor.withTableName("NVLTY_REG").usingColumns("EID", "DVCID", "PHNNBR","ENABLE","USRTYP","VLDUSR","EMPTYP","LOCID","FLRNBR","SEATNBR","BAND");
//			insertActor.withTableName("NVLTY_REG").usingColumns("EID", "DVCID", "PHNNBR","ENABLE","USRTYP","VLDUSR","EMPTYP","LOCID","FLRNBR");
			BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(req);
			
			int rtnCd = insertActor.execute(param);
			
			if (rtnCd > 0){
				aResp.setRspCde("000");
				aResp.setRspMsg("Success");
				aResp.setFName(mstrResp.getFName());
				aResp.setLName(mstrResp.getLName());
				aResp.setEId(mstrResp.getEId());
				aResp.setUsrTyp(mstrResp.getUsrTyp());
				aResp.setFlrNbr(mstrResp.getFlrNbr());
				aResp.setSeatNbr(mstrResp.getSeatNbr());
				aResp.setBand(mstrResp.getBand());
			} else {
				aResp.setRspCde("999");
				aResp.setRspMsg("Failure");
			}
		} else if (!mstrResp.getIsReg().equals("true")) {
			aResp.setRspCde("888");
			aResp.setRspMsg("Resistration not done");
		}
		logger.info("RegSvcDaoImpl() - insertRegReq - Starts ");
		return aResp;
	}
	
	 public MstrResp getDtlsFrmMstr(String eId, String tableName) {
		 logger.info("RegSvcDaoImpl() - getDtlsFrmMstr - Starts *** StopWatch");
	        String sql = "SELECT * FROM NVLTY_MSTR WHERE EID = ?";
	        String sqlReg = "SELECT * FROM NVLTY_REG WHERE EID = ?";
	        MstrResp mstrResp = new MstrResp();
	        Object[] args = {eId};
	        StopWatch stopWatch = new StopWatch();
	        if (tableName.equals("R")){
	        	stopWatch.start();
	         mstrResp = jdbcTemplate.queryForObject(sql, args,
	                        BeanPropertyRowMapper.newInstance(MstrResp.class));
	         stopWatch.stop();
	        } else {
	        	stopWatch.start();
	        	int regCnt = jdbcTemplate.queryForObject(sqlReg, args, Integer.class);
	        	stopWatch.stop();
	        	logger.info("RegSvcDaoImpl() - getDtlsFrmMstr - regCnt *** " + stopWatch.getTime());
	        	if(regCnt>0){
	        		stopWatch.start();
	        	 mstrResp = jdbcTemplate.queryForObject(sql, args,
	                        BeanPropertyRowMapper.newInstance(MstrResp.class));
	        	 stopWatch.stop();
		        	logger.info("RegSvcDaoImpl() - getDtlsFrmMstr - mstrResp *** " + stopWatch.getTime());
	        	 mstrResp.setIsReg("true");
	        	} else {
	        		// not registered
	        		mstrResp.setIsReg("false");
	        	}
	        }
	        logger.info("RegSvcDaoImpl() - getDtlsFrmMstr - ends ");
	        return mstrResp;
	    }
	 
	 public MstrResp getDtlsFrmMstr(String eId) {
		 logger.info("RegSvcDaoImpl() - getDtlsFrmMstr - Starts *** StopWatch" );
	        String sql = "SELECT * FROM NVLTY_MSTR WHERE EID = ?";
	        Object[] args = {eId};
	        StopWatch stopWatch = new StopWatch();
	        stopWatch.start();
	        MstrResp mstrResp = jdbcTemplate.queryForObject(sql, args,
	                        BeanPropertyRowMapper.newInstance(MstrResp.class));
	        stopWatch.stop();
	        logger.info("RegSvcDaoImpl() - getDtlsFrmMstr - ends *** "+ stopWatch.getTime());
	        return mstrResp;
	    }
	 
	 @Override
		public RegSvcResp getRegEidResp(String eid) {
		 logger.info("RegSvcDaoImpl() - getRegEidResp - Starts ");
			RegSvcResp aResp = new RegSvcResp();
			
			MstrResp mstrResp = getDtlsFrmMstr(eid);
			
			if (mstrResp != null && !mstrResp.getPhnNbr().equals("")){
				aResp.setRspCde("000");
				aResp.setRspMsg("Success");
				aResp.setFName(mstrResp.getFName());
				aResp.setLName(mstrResp.getLName());
				aResp.setEId(mstrResp.getEId());
				aResp.setUsrTyp(mstrResp.getUsrTyp());
				aResp.setFlrNbr(mstrResp.getFlrNbr());
				aResp.setSeatNbr(mstrResp.getSeatNbr());	
				aResp.setBand(mstrResp.getBand());
			} else {
				aResp.setRspCde("999");
				aResp.setRspMsg("Failure");
			}
			
			 logger.info("RegSvcDaoImpl() - getRegEidResp - Starts ");
			return aResp;
		}

		@Override
		public RegSvcResp getRegRes(String eid) {
			 logger.info("RegSvcDaoImpl() - getRegRes - Starts ");
			RegSvcResp aResp = new RegSvcResp();
			
			MstrResp mstrResp = getDtlsFrmMstr(eid);
			
			if (mstrResp != null && !mstrResp.getPhnNbr().equals("")){
				aResp.setRspCde("000");
				aResp.setRspMsg("Success");
				aResp.setFName(mstrResp.getFName());
				aResp.setLName(mstrResp.getLName());
				aResp.setEId(mstrResp.getEId());
				aResp.setUsrTyp(mstrResp.getUsrTyp());
				aResp.setFlrNbr(mstrResp.getFlrNbr());
				aResp.setSeatNbr(mstrResp.getSeatNbr());	
				aResp.setBand(mstrResp.getBand());
			} else {
				aResp.setRspCde("999");
				aResp.setRspMsg("Failure");
			}
			 logger.info("RegSvcDaoImpl() - getRegRes - ends ");
			return aResp;
		}


}

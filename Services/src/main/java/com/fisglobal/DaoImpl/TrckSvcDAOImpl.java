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

import com.fisglobal.Dao.TrckSvcDao;
import com.fisglobal.requestbo.TrckSvcReq;
import com.fisglobal.responsebo.DvcIdResp;
import com.fisglobal.responsebo.TrckSvcResp;

@Repository
@Transactional
public class TrckSvcDAOImpl implements TrckSvcDao {
	
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public TrckSvcResp insertTrckReq(TrckSvcReq req) {
		logger.info("TrckSvcDAOImpl() - insertTrckReq - Starts ");
        TrckSvcResp aResp = new TrckSvcResp(); 
StopWatch stopWatch = new StopWatch();
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        
        DvcIdResp dvcIdResp=new DvcIdResp();
      //here we are getting the NVLTY_REG table data record
        dvcIdResp = getRegDtls(req.getDvcId());
        
        //we are injecting that NVLTY_REG details into NVLTY_AUDT
        req.setEId(dvcIdResp.getEid().toUpperCase());
        req.setLoc(dvcIdResp.getLocid().toUpperCase());
        req.setFlrId(dvcIdResp.getFlrnbr());
        req.setSeatNbr(dvcIdResp.getSeatnbr().toUpperCase());
        
        //dt already set in impl, NREID will be sent int he request
//        insertActor.withTableName("NVLTY_AUDT").usingColumns("EID", "LOC", "FLRID","NREID","DT", "SEATNBR");
        stopWatch.start();
        insertActor.withTableName("NVLTY_AUDT").usingColumns("EID", "LOC", "FLRID","NREID","DT");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(req);
        
        int rtnCd = insertActor.execute(param);
        
        stopWatch.stop();
        logger.info("TrckSvcDAOImpl() - insertTrckReq - *** " + stopWatch.getTime());
        if (rtnCd > 0){
            aResp.setRspCde("000");
            aResp.setRspMsg("Success");
        } else {
            aResp.setRspCde("999");
            aResp.setRspMsg("Failure");
        }
        logger.info("TrckSvcDAOImpl() - insertTrckReq - ends ");
        return aResp;

    }
    
    public DvcIdResp getRegDtls(String id) {
    	logger.info("TrckSvcDAOImpl() - getRegDtls - Starts *** StopWatch");
        String sql = "SELECT * FROM NVLTY_REG WHERE DVCID = ?";
        Object[] args = {id};
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        DvcIdResp dvcIdResp = jdbcTemplate.queryForObject(sql, args,
                        BeanPropertyRowMapper.newInstance(DvcIdResp.class));
        stopWatch.stop();
        logger.info("TrckSvcDAOImpl() - getRegDtls - ends ***" + stopWatch.getTime());
        return dvcIdResp;
    }
	
	/*public List<TrckSvcReq> list() {
	    String sql = "SELECT * FROM NVLTY_AUDT";
	 
	    List<TrckSvcReq> listSale = jdbcTemplate.query(sql,
	            BeanPropertyRowMapper.newInstance(TrckSvcReq.class));
	 
	    return listSale;
	}*/
	
	
	/*public TrckSvcReq get(int id) {
	    String sql = "SELECT * FROM NVLTY_AUDT WHERE id = ?";
	    Object[] args = {id};
	    TrckSvcReq trckSvcReq = jdbcTemplate.queryForObject(sql, args,
	                    BeanPropertyRowMapper.newInstance(TrckSvcReq.class));
	    return trckSvcReq;
	}*/
	
	
	
}

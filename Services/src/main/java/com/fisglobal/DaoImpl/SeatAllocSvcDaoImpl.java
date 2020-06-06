package com.fisglobal.DaoImpl;

import java.util.List;

import org.apache.commons.lang.time.StopWatch;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fisglobal.Dao.SeatAllocSvcDao;
import com.fisglobal.requestbo.SeatAllocSvcReq;
import com.fisglobal.responsebo.EmpSeatRowMapper;
import com.fisglobal.responsebo.IndSeatDtl;
import com.fisglobal.responsebo.SeatAllocSvcResp;

@Repository
@Transactional
public class SeatAllocSvcDaoImpl implements SeatAllocSvcDao {
	
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public SeatAllocSvcResp retrieveSeatInfo(SeatAllocSvcReq req) {
		logger.info("SeatAllocSvcDaoImpl() - retrieveSeatInfo - Starts ");
		
		SeatAllocSvcResp seatAllocSvcResp = new SeatAllocSvcResp();
		
		List<IndSeatDtl> indSeatLst = getSeatDtls(req.getFlrId());
		seatAllocSvcResp.setIndSeatDtl(indSeatLst);
		logger.info("SeatAllocSvcDaoImpl() - retrieveSeatInfo - ends ");
		return seatAllocSvcResp;
	}
	
	public List<IndSeatDtl> getSeatDtls(String id) {
	 
		/* DateTimeFormatter  pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
     LocalDate datetime = null;
	   
     datetime = LocalDate.parse(LocalDate.now().toString(), pattern);
     java.util.Date dt = null;
	 
     try {
		dt = new SimpleDateFormat("yyyy-MM-dd").parse(datetime.toString());
	 } catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	 }*/
		logger.info("SeatAllocSvcDaoImpl() - getSeatDtls - Starts *** StopWatch"); 
     String sql = "SELECT EID,SEATNBR FROM NVLTY_MSTR WHERE FLRNBR = ?";
  
     Object[] args = {id};
     StopWatch stopWatch = new StopWatch();
     stopWatch.start();
     @SuppressWarnings("unchecked")
	 List<IndSeatDtl> indSeatLst = jdbcTemplate.query(sql, args, new EmpSeatRowMapper());
     stopWatch.stop();
     logger.info("SeatAllocSvcDaoImpl() - getSeatDtls - ends ***" + stopWatch.getTime());
     return indSeatLst;
  }

}

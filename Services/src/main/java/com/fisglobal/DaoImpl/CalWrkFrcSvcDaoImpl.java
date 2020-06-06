package com.fisglobal.DaoImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang.time.StopWatch;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fisglobal.Dao.CalWrkFrcSvcDao;

@Repository
@Transactional
public class CalWrkFrcSvcDaoImpl implements CalWrkFrcSvcDao {
	
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String getWrkFrcPercent(String flrNbr) {
		logger.info("CalWrkFrcSvcDaoImpl() - getWrkFrcPercent - Starts ");
		int empSeatRowCnt = getWrkFrcDtls(flrNbr);
		int totEmpRowCnt = getTotEmpCnt();
		
		float wrkFrcPercent = (empSeatRowCnt*100f/totEmpRowCnt);
		logger.info("CalWrkFrcSvcDaoImpl() - getWrkFrcPercent - ends ");
		return String.valueOf(wrkFrcPercent);
	}
	
	public int getWrkFrcDtls(String id) {
		logger.info("CalWrkFrcSvcDaoImpl() - getWrkFrcDtls - Starts *** StopWatch");
		int rowCnt = 0;
		 
		 DateTimeFormatter  pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	     LocalDate datetime = null;
		   
	     datetime = LocalDate.parse(LocalDate.now().toString(), pattern);
	     java.util.Date dt = null;
		try {
			dt = new SimpleDateFormat("yyyy-MM-dd").parse(datetime.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StopWatch stopWatch = new StopWatch();
		
	     String sql = "SELECT count(*) FROM NVLTY_AUDT WHERE FLRID = ? and DT = ?";
	    
	     Object[] args = {id,dt};
	     stopWatch.start();
	     rowCnt = jdbcTemplate.queryForObject(sql, args, Integer.class);
	     stopWatch.stop();
	     logger.info("CalWrkFrcSvcDaoImpl() - getWrkFrcDtls - ends ***" + stopWatch.getTime());
	        return rowCnt;
	    }
	
	public int getTotEmpCnt() {
		logger.info("CalWrkFrcSvcDaoImpl() - getTotEmpCnt - Starts *** StopWatch");
		int rowCnt = 0;
		StopWatch stopWatch = new StopWatch();
	     String sql = "SELECT count(*) FROM NVLTY_MSTR";
	    stopWatch.start();
	     rowCnt = jdbcTemplate.queryForObject(sql, Integer.class);
	     stopWatch.stop();
	     logger.info("CalWrkFrcSvcDaoImpl() - getTotEmpCnt - ends ***" + stopWatch.getTime());
	        return rowCnt;
	}
	
	/* public List<EmpSeatMapResp> getWrkFrcDtls(String id) {
	 logger.info("SeatAllocSvcDaoImpl() - getWrkFrcDtls - Starts ");
	 DateTimeFormatter  pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate datetime = null;
	   
    datetime = LocalDate.parse(LocalDate.now().toString(), pattern);
    java.util.Date dt = null;
	try {
		dt = new SimpleDateFormat("yyyy-MM-dd").parse(datetime.toString());
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		 
    String sql = "SELECT * FROM NVLTY_AUDT WHERE FLRID = ? and DT = ?";
   
    Object[] args = {id,dt};
    List<EmpSeatMapResp> empSeatLst = jdbcTemplate.query(sql, args, new EmpSeatRowMapper());
logger.info("SeatAllocSvcDaoImpl() - getWrkFrcDtls - ends ");
       return empSeatLst;
   }*/

}

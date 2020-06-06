package com.fisglobal.svcImpl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fisglobal.Dao.SeatAllocSvcDao;
import com.fisglobal.requestbo.SeatAllocSvcReq;
import com.fisglobal.responsebo.SeatAllocSvcResp;
import com.fisglobal.svc.SeatAllocSvc;

@Component
public class SeatAllocSvcImpl implements SeatAllocSvc {
	
	@Autowired
	private SeatAllocSvcDao seatAllocSvcDao;

	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public SeatAllocSvcResp  getSeatDtls(SeatAllocSvcReq req) {
		logger.info("SeatAllocSvcImpl() - getSeatDtls - Starts ");
		
		SeatAllocSvcResp seatAllocSvcResp = new SeatAllocSvcResp();
		
		seatAllocSvcResp = seatAllocSvcDao.retrieveSeatInfo(req);
		logger.info("SeatAllocSvcImpl() - getSeatDtls - ends ");
		
		return seatAllocSvcResp;
		
	}


}

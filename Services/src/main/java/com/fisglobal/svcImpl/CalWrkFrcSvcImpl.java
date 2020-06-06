package com.fisglobal.svcImpl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fisglobal.Dao.CalWrkFrcSvcDao;
import com.fisglobal.responsebo.CalWrkFrcResp;
import com.fisglobal.svc.CalWrkFrcSvc;

@Component
public class CalWrkFrcSvcImpl implements CalWrkFrcSvc {
	
	@Autowired
	private CalWrkFrcSvcDao calWrkFrcSvcDao;

	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public CalWrkFrcResp getWrkFrcPercent(String usrTyp, String flrNbr) {
		logger.info("CalWrkFrcSvcImpl() - getWrkFrcPercent - Starts ");
		CalWrkFrcResp calWrkFrcResp = new CalWrkFrcResp();
		
		String totCnt = calWrkFrcSvcDao.getWrkFrcPercent(flrNbr);
		
		calWrkFrcResp.setWrkFrcCnt(totCnt);
		calWrkFrcResp.setRspCde("000");
		calWrkFrcResp.setRspMsg("Success");
		logger.info("CalWrkFrcSvcImpl() - getWrkFrcPercent - ends ");
		return calWrkFrcResp;
	}

}

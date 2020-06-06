package com.fisglobal.svcImpl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fisglobal.Dao.TrckSvcDao;
import com.fisglobal.requestbo.TrckSvcReq;
import com.fisglobal.responsebo.TrckSvcResp;
import com.fisglobal.svc.TrckSvc;

@Component
public class TrckSvcImpl implements TrckSvc {


	@Autowired
	private TrckSvcDao trckSvcDao;

	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	public TrckSvcResp trckDtls(TrckSvcReq req) {

		logger.info("TrckSvcImpl() - trckDtls - Starts ");
		TrckSvcResp trckSvcResp=new TrckSvcResp();

		try{
		        DateTimeFormatter  pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        LocalDate datetime = null;
			   
		        datetime = LocalDate.parse(LocalDate.now().toString(), pattern);
		        java.util.Date d = new SimpleDateFormat("yyyy-MM-dd").parse(datetime.toString());
				req.setDt(d);
				trckSvcResp = trckSvcDao.insertTrckReq(req);
		} catch (Exception ex){
			logger.info("Error" + ex);
		}  

		logger.info("TrckSvcImpl() - trckDtls - Ends ");

		return trckSvcResp;

	}

}

package com.fisglobal.svcImpl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fisglobal.Dao.RegSvcDao;
import com.fisglobal.requestbo.RegSvcReq;
import com.fisglobal.responsebo.RegSvcResp;
import com.fisglobal.svc.RegSvc;

@Component
public class RegSvcImpl implements RegSvc {

	@Autowired
	private RegSvcDao regSvcDao;

	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	public RegSvcResp regDtls(RegSvcReq req) {
		
		logger.info("RegSvcImpl() - regDtls - Starts ");
		RegSvcResp regSvcResp=new RegSvcResp();

		try{
			regSvcResp = regSvcDao.insertRegReq(req);
		} catch (Exception ex){
			logger.info("Error" + ex);
		}  

		logger.info("RegSvcImpl() - regDtls - Ends ");

		return regSvcResp;
		
	}
	
	
	public RegSvcResp regEidDtls(String eid){
		
		logger.info("RegSvcImpl() - regEidDtls - Starts ");
		RegSvcResp regSvcResp=new RegSvcResp();

		try{
			regSvcResp = regSvcDao.getRegEidResp(eid);
		} catch (Exception ex){
			logger.info("Error" + ex);
		}  

		logger.info("RegSvcImpl() - regEidDtls - Ends ");

		return regSvcResp;

		
	}
	
	public RegSvcResp regGetRegSvc(String eid){
		
		logger.info("RegSvcImpl() - regGetRegSvc - Starts ");
		RegSvcResp regSvcResp=new RegSvcResp();

		try{
			regSvcResp = regSvcDao.getRegRes(eid);
		} catch (Exception ex){
			logger.info("Error" + ex);
		}  

		logger.info("RegSvcImpl() - regGetRegSvc - Ends ");

		return regSvcResp;
		
	}
	
	
}

package com.fisglobal.svcImpl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fisglobal.Dao.ContactTrckDao;
import com.fisglobal.requestbo.ContactTrckSvcReq;
import com.fisglobal.responsebo.ContactTrckSvcResp;
import com.fisglobal.svc.ContactTrckSvc;

@Component
public class ContactTrckSvcImpl implements ContactTrckSvc {
	
	@Autowired
	private ContactTrckDao contactTrckSvcDao;

	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	public ContactTrckSvcResp  getContactTrckDtls(ContactTrckSvcReq req) {
		logger.info("RegSvcDaoImpl() - getContactTrckDtls - Starts ");
		
		ContactTrckSvcResp contactTrckSvcResp = new ContactTrckSvcResp();
		logger.info("RegSvcDaoImpl() - getContactTrckDtls - ends ");
		return contactTrckSvcResp;
	}
	
	 public ContactTrckSvcResp getContactTrckEidDtls(String eid){
		 logger.info("RegSvcDaoImpl() - getContactTrckEidDtls - Starts ");
		 ContactTrckSvcResp contactTrckSvcResp = new ContactTrckSvcResp();
		 
		 contactTrckSvcResp = contactTrckSvcDao.getContactTrckDtls(eid);
		 logger.info("RegSvcDaoImpl() - getContactTrckEidDtls - ends ");
			return contactTrckSvcResp;
		 
	 }
	
	 
		public ContactTrckSvcResp insertContactTrckDtls(String eid, String flrId, String band,String nr_eid){
			logger.info("RegSvcDaoImpl() - insertContactTrckDtls - Starts ");
			ContactTrckSvcResp contactTrckSvcResp = new ContactTrckSvcResp();
			
			contactTrckSvcResp = contactTrckSvcDao.insertContactTrckDtls(eid,flrId,band,nr_eid);
			logger.info("RegSvcDaoImpl() - insertContactTrckDtls - ends ");
			return contactTrckSvcResp;
			
		}

}

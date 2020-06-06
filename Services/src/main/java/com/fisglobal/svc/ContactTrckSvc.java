package com.fisglobal.svc;

import com.fisglobal.requestbo.ContactTrckSvcReq;
import com.fisglobal.responsebo.ContactTrckSvcResp;

public interface ContactTrckSvc {
	
	public ContactTrckSvcResp  getContactTrckDtls(ContactTrckSvcReq req); 
	
    public ContactTrckSvcResp getContactTrckEidDtls(String eid);
	
	public ContactTrckSvcResp insertContactTrckDtls(String eid, String flrId, String band,String nr_eid);

}

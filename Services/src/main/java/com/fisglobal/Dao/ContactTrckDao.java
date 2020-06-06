package com.fisglobal.Dao;

import com.fisglobal.requestbo.ContactTrckSvcReq;
import com.fisglobal.responsebo.ContactTrckSvcResp;

public interface ContactTrckDao {
	
	public ContactTrckSvcResp getContactTrckDtls(ContactTrckSvcReq req);
	
	public ContactTrckSvcResp getContactTrckDtls(String eid);
	
	public ContactTrckSvcResp insertContactTrckDtls(String eid, String flrId, String band,String nr_eid);

}

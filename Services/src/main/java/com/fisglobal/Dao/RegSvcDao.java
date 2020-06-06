package com.fisglobal.Dao;

import com.fisglobal.requestbo.RegSvcReq;
import com.fisglobal.responsebo.RegSvcResp;

public interface RegSvcDao {
	
	public RegSvcResp insertRegReq(RegSvcReq req);
	
	public RegSvcResp getRegEidResp(String eid); 
	
	public RegSvcResp getRegRes(String eid);

}

package com.fisglobal.svc;

import com.fisglobal.requestbo.RegSvcReq;
import com.fisglobal.responsebo.RegSvcResp;

public interface RegSvc {
	
	public RegSvcResp  regDtls(RegSvcReq req);
	
	public RegSvcResp regEidDtls(String eid);
	
	public RegSvcResp regGetRegSvc(String eid);

}

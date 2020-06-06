package com.fisglobal.svc;

import com.fisglobal.requestbo.TrckSvcReq;
import com.fisglobal.responsebo.TrckSvcResp;

public interface TrckSvc {
	
	public TrckSvcResp trckDtls(TrckSvcReq req);

}

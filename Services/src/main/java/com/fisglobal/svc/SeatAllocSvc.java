package com.fisglobal.svc;

import com.fisglobal.requestbo.SeatAllocSvcReq;
import com.fisglobal.responsebo.SeatAllocSvcResp;

public interface SeatAllocSvc {
	
	public SeatAllocSvcResp  getSeatDtls(SeatAllocSvcReq req);

}

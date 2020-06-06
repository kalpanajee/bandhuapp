package com.fisglobal.Dao;

import com.fisglobal.requestbo.SeatAllocSvcReq;
import com.fisglobal.responsebo.SeatAllocSvcResp;

public interface SeatAllocSvcDao {
	
	public SeatAllocSvcResp retrieveSeatInfo(SeatAllocSvcReq req);


}

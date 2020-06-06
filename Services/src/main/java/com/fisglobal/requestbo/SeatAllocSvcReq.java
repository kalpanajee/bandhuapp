package com.fisglobal.requestbo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SeatAllocSvcReq implements java.io.Serializable {
	
	private static final long serialVersionUID = 3578486550204288752L;
	
	private String usrTyp;
	
	private String flrId;
}

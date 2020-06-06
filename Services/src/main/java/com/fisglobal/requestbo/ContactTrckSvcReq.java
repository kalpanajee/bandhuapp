package com.fisglobal.requestbo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ContactTrckSvcReq implements Serializable{
	
	private static final long serialVersionUID = 3578486550204288752L;
	
	private String eid;
	private String dt;
	private String loc;
	private String flrId;
	private String nreid;
	private String seatnbr;
	private String band;
	private String nrband;
	private String nreid_ph_no;
	private String nrseatnbr;

}

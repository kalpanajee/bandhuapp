package com.fisglobal.responsebo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TraceDtls implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String eid;
	private String loc;
	private String flrid;
	private String nreid;
	private String dt;
	private String seatnbr;
	private String band;
	private String nrseatnbr;
	private String nrband;
	private String nreid_ph_no;
	
}

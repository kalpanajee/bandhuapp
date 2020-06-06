package com.fisglobal.responsebo;

import java.util.List;

import com.fisglobal.requestbo.ErrDtls;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegSvcResp implements java.io.Serializable {

private static final long serialVersionUID = 1L;
	
//	private List<ErrDtls> errDtls;
	private String rspCde;
	private String rspMsg;
	private String eId;
	private String fName;
	private String LName;
	private String usrTyp;
	private String flrNbr;
	private String seatNbr;
	private String band;
}

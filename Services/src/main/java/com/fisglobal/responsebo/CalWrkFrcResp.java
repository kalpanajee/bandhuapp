package com.fisglobal.responsebo;

import java.io.Serializable;
import java.util.List;

import com.fisglobal.requestbo.ErrDtls;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CalWrkFrcResp implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String wrkFrcCnt;
//	private List<ErrDtls> errDtls;
	private String rspCde;
	private String rspMsg;

}

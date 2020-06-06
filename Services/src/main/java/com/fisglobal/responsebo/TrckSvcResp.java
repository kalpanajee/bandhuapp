package com.fisglobal.responsebo;

import java.util.List;

import com.fisglobal.requestbo.ErrDtls;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class TrckSvcResp implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
//	private List<ErrDtls> errDtls;
	private String rspCde;
	private String rspMsg;

}

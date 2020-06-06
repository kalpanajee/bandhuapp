package com.fisglobal.requestbo;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.fisglobal.util.NvltyConstants;
import com.fisglobal.util.PatternUtil;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegSvcReq implements java.io.Serializable {
	
	private static final long serialVersionUID = 3578486550204288752L;
	
	@NotBlank(message = NvltyConstants.EID_REQ)
	@Pattern(regexp = PatternUtil.PAT_USR_ID, message = NvltyConstants.EID_INV)
	private String eId;
	
	@NotBlank(message = NvltyConstants.DVCID_REQ)
	@Pattern(regexp = PatternUtil.PAT_USR_ID, message = NvltyConstants.DVCID_INV)
	private String dvcId;
	
	@NotBlank(message = NvltyConstants.PHNNBR_REQ)
	@Pattern(regexp = PatternUtil.PAT_USR_ID, message = NvltyConstants.PHNNBR_INV)
	private String phnNbr;
	
	private String enable;
	
	private String usrTyp;
	
	private String vldUsr;
	
	private String empTyp;
	
	private String locId;
	
	private String flrNbr;
	
	private String seatNbr;
	
	private String svcTyp;
	
	private String band;

}

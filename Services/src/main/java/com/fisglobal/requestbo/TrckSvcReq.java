package com.fisglobal.requestbo;


import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.fisglobal.util.NvltyConstants;
import com.fisglobal.util.PatternUtil;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class TrckSvcReq implements java.io.Serializable {
	
	private static final long serialVersionUID = 3578486550204288752L;
	
//	@NotBlank(message = NvltyConstants.EID_REQ)
	@Pattern(regexp = PatternUtil.PAT_USR_ID, message = NvltyConstants.EID_INV)
	private String eId;
	
//	@NotBlank(message = NvltyConstants.LOC_REQ)
	@Pattern(regexp = PatternUtil.PAT_USR_ID, message = NvltyConstants.LOC_INV)
	private String loc;
	
//	@NotBlank(message = NvltyConstants.FLRID_REQ)
	@Pattern(regexp = PatternUtil.PAT_USR_ID, message = NvltyConstants.FLRID_INV)
	private String flrId;
	
//	@NotBlank(message = NvltyConstants.NREID_REQ)
	@Pattern(regexp = PatternUtil.PAT_USR_ID, message = NvltyConstants.NREID_INV)
	private String nrEId;
	
	private Date dt;
	
	private String dvcId;
	
	private String seatNbr;
	
}

package com.fisglobal.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.fisglobal.requestbo.ErrDtls;
@Component
public class CommanUtils {
	
	@Autowired
	private Environment env;
	
	public ErrDtls addErrorMessage(String errCd){
		ErrDtls msgInfo = new ErrDtls();
		msgInfo.setErrCd(errCd);
		msgInfo.setErrMsg(env.getProperty(errCd));
		return msgInfo;
	}

}

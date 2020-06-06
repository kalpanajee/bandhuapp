package com.fisglobal.requestbo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrDtls implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String errCd;
	private String errMsg;

}

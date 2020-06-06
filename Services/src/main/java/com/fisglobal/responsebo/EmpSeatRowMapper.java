package com.fisglobal.responsebo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class EmpSeatRowMapper implements RowMapper {
	
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		EmpSeatMapResp empSeatRsltObj = new EmpSeatMapResp();
		
		empSeatRsltObj.setEid(rs.getString("EID"));
		empSeatRsltObj.setSeatNbr(rs.getString("SEATNBR"));
		
		return empSeatRsltObj;
	}
	

}

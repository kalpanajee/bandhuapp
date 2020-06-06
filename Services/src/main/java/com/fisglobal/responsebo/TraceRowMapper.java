package com.fisglobal.responsebo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class TraceRowMapper implements RowMapper {
	
public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		TraceMapResp traceRsltObj = new TraceMapResp();
		
		traceRsltObj.setEid(rs.getString("EID"));
		traceRsltObj.setSeatnbr(rs.getString("SEATNBR"));
		traceRsltObj.setLoc(rs.getString("LOC"));
		traceRsltObj.setFlrid(rs.getString("FLRID"));
		traceRsltObj.setBand(rs.getString("BAND"));
		traceRsltObj.setNreid(rs.getString("NREID"));
		traceRsltObj.setNrseatnbr(rs.getString("NRSEATNBR"));
		traceRsltObj.setNrband(rs.getString("NRBAND"));
		traceRsltObj.setNreid_ph_no(rs.getString("NREID_PH_NO"));

		return traceRsltObj;
	}

}

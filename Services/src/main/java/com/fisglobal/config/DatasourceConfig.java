package com.fisglobal.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import com.fisglobal.util.NvltyConstants;

//@Configuration
public class DatasourceConfig {
			
	/*@Bean(name = NvltyConstants.DATA_SOURCE_ORACLE)
	@ConfigurationProperties(prefix = NvltyConstants.DATA_SOURCE + NvltyConstants.DATA_SOURCE_ORACLE)
	public DataSource mainframeDataSource() {
		return DataSourceBuilder.create().build();
	}	
	
	@Bean(name = "jdbcDs1")
	public JdbcTemplate jdbcTemplateDs1(@Qualifier(NvltyConstants.DATA_SOURCE_ORACLE) DataSource ds1) {
		return new JdbcTemplate(ds1);
	}
	
	@Bean(name = NvltyConstants.JDBC_TEMPLATE_ORACLE)
	public JdbcTemplate namedjdbcTemplateMainFrame(@Qualifier(NvltyConstants.DATA_SOURCE_ORACLE) DataSource oracleDs) {
		return new JdbcTemplate(oracleDs);
	}*/

}

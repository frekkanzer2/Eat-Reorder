package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class DBConnectionPool {
	
	private static DataSource datasource;

	public static Connection getConnection() throws SQLException {
		if (datasource == null) {
			
			PoolProperties p = new PoolProperties();
			
			/*local use*/
			p.setUrl("jdbc:mysql://localhost:3306/eatreorder?serverTimezone=" +TimeZone.getDefault().getID()+"&useSSL=false&allowPublicKeyRetrieval=true");
			
			
			p.setDriverClassName("com.mysql.cj.jdbc.Driver");
			
			p.setUsername("root");
			p.setPassword("password");
			
			p.setMaxActive(100);
			p.setInitialSize(10);
			p.setMinIdle(10);
			p.setRemoveAbandonedTimeout(60);
			p.setRemoveAbandoned(true);
			
			datasource = new DataSource();
			datasource.setPoolProperties(p);
		}
		return datasource.getConnection();
	}
}

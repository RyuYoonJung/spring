package com.coolcode.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;
import oracle.jdbc.driver.OracleDriver;

@Log4j
public class JDBCTests {
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Test
	public void testConnection(){
		try (Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@np.coolcode.co.kr:1521:xe", "spring", "1234")){
			log.info(conn);
		}
			catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}

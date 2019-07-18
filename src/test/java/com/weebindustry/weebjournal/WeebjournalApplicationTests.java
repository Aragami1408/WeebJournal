package com.weebindustry.weebjournal;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeebjournalApplicationTests {

	@Autowired
	private DataSource dataSource;
	@Test
	public void testHikariIntegration() {
		assertEquals("com.zaxxer.hikari.HikariDataSource", dataSource.getClass().getName());
	}

}

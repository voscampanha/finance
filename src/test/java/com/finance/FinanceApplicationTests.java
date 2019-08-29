package com.finance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.finance.FinanceApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = FinanceApplication.class)
public class FinanceApplicationTests {

	@Test
	public void contextLoads() {
	}

}

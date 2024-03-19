package com.jorge.santillana;

import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jorge.santillana.service.DividendService;

class RandomRepeatTests {

	private static final Logger logger = LoggerFactory.getLogger(RandomRepeatTests.class);
	private static final int RANDOM_TESTS = 10; // t
	private static final int X_AND_Y_LIMIT = 100000;
	DividendService dividendService = new DividendService();
	
	@RepeatedTest(RANDOM_TESTS)
	public void randomTests(RepetitionInfo ri) {
		Random random = new Random();
		int x = 2 + random.nextInt(X_AND_Y_LIMIT - 2);
		int y = 1 + random.nextInt(x - 1);
		int n = y + random.nextInt(X_AND_Y_LIMIT - y);
		int k = dividendService.dividendCalculate(x, y, n);
		if (k != 0)
			Assertions.assertEquals(k, x * (k / x) + y);
		else
			Assertions.assertTrue(x + y > n && k == 0);
		
		logger.info("RANDOM TEST OK - " + ri.getCurrentRepetition() + "/" + RANDOM_TESTS + " " + "--- Divisor(x): " + x
				+ " - Resto(y): " + y + " - Límite(n): " + n + " --> RESULTADO - Dividendo(k):" + k + " --- " + k + "/"
				+ x + "=" + k / x);
	}
}

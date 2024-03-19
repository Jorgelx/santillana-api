package com.jorge.santillana;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jorge.santillana.service.DividendService;


class SantillanaApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(SantillanaApplicationTests.class);
	DividendService dividendService = new DividendService();

	@Test
	public void test() {
		int x = 5, y = 3, n = 100;
		int k = dividendService.dividendCalculate(x, y, n);
		Assertions.assertEquals(k, 98);
		if (k != 0)
			Assertions.assertEquals(k, x * (k / x) + y);
		else
			Assertions.assertTrue(x + y > n && k == 0);
		logger.info("TEST OK - Divisor(x): " + x + " - Resto(y): " + y + " - Límite(n): " + n
				+ " --> RESULTADO - Dividendo(k):" + k + " - " + k + "/" + x + "=" + k / x);
	}

	@Test
	public void testExample() {
		Assertions.assertAll(() -> Assertions.assertEquals(dividendService.dividendCalculate(7, 5, 12345), 12339),
				() -> Assertions.assertEquals(dividendService.dividendCalculate(5, 0, 4), 0),
				() -> Assertions.assertEquals(dividendService.dividendCalculate(10, 5, 15), 15),
				() -> Assertions.assertEquals(dividendService.dividendCalculate(17, 8, 54321), 54306),
				() -> Assertions.assertEquals(dividendService.dividendCalculate(499999993, 9, 1000000000), 999999995),
				() -> Assertions.assertEquals(dividendService.dividendCalculate(10, 5, 187), 185),
				() -> Assertions.assertEquals(dividendService.dividendCalculate(2, 0, 999999999), 999999998));
		logger.info("TEST EXAMPLE OK");
	}

}

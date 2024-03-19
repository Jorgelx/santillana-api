package com.jorge.santillana.service;

import org.springframework.stereotype.Service;


@Service
public class DividendService {

	/**
	 * Calcula el dividendo máximo
	 * 
	 * @param x - divisor
	 * @param y - resto
	 * @param n - máximo
	 * @return k - dividendo
	 */
	public int dividendCalculate(int x, int y, int n) {
		int k = ((n - y) / x) * x;
		// FIXME Si (n > x + y) no podemos sumar +y al resultado de la fórmula.
		if (k != 0)
			k += y;
		return k;
	}
	
}

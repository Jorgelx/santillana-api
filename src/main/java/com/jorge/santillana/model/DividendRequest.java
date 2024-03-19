package com.jorge.santillana.model;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class DividendRequest {

	@Min(value = 2, message = "x=${validatedValue}, x no puede ser menor que 2")
	@Max(value = 1000000000, message = "x= (${validatedValue}), x no puede ser mayor que 10^9")
	private Integer x;

	@Min(value = 0, message = "y=${validatedValue}, y no puede ser menor que 0")
	private Integer y;

	@Max(value = 1000000000, message = "n=${validatedValue}, n no puede ser mayor que 10^9")
	private Integer n;

	@AssertTrue(message = "y no puede ser mayor que x")
	public boolean isValidY() {
		return x != null && y != null  && y < x ;
	}

	@AssertTrue(message = "n debe ser mayor o igual a y")
	public boolean isValidN() {
		return y != null && n != null && n >= y;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getN() {
		return n;
	}

	public void setN(Integer n) {
		this.n = n;
	}

}

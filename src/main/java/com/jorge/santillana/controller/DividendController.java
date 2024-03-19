package com.jorge.santillana.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jorge.santillana.model.DividendRequest;
import com.jorge.santillana.service.DividendService;

import jakarta.validation.Valid;

@RestController
@Validated
public class DividendController {

	@Autowired
	DividendService dividendService;

	@GetMapping("/calculatek")
	public ResponseEntity<Integer> getDividend(@Valid DividendRequest request) {
		return ResponseEntity.ok(dividendService.dividendCalculate(request.getX(), request.getY(), request.getN()));
	}
	
	@PostMapping("/calculatek")
	public ResponseEntity<?> getDividendPost(@RequestBody List<@Valid DividendRequest> request) {
		List<Integer> results = new ArrayList<>();
		for (DividendRequest r : request) {
			results.add(dividendService.dividendCalculate(r.getX(), r.getY(), r.getN()));
		}
		return ResponseEntity.ok(results);
	}
}

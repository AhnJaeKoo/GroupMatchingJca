package com.enuri.gm.jca.controller.gm;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.enuri.gm.jca.service.BusinessService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("gm")
@RequiredArgsConstructor
public class BusinessController {

	private final BusinessService businessService;

	@CircuitBreaker(name = "default")
	@RequestMapping("/business")
	public String business() {
		return "gm/business";
	}

	@CircuitBreaker(name = "default")
	@GetMapping(value = "/filterStr", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> getFilterStr(@RequestParam("modelNm") String modelNm,
			@RequestParam("cateCode") String cateCode,
			@RequestParam("exFlag") String exFlag) {

		String result = businessService.getExKeyWordResult(modelNm, cateCode, exFlag);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
}
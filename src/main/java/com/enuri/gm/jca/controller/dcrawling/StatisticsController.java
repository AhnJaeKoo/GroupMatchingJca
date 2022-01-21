package com.enuri.gm.jca.controller.dcrawling;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.enuri.gm.jca.repository.eloc.TbDnCrwlSttRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "dcrawling")
@RequiredArgsConstructor
public class StatisticsController {

	private final TbDnCrwlSttRepository tbDnCrwlSttRepository;

	@CircuitBreaker(name = "default")
	@RequestMapping("/stat")
	public String statistics() {
		return "dcrawling/statistics";
	}

	@CircuitBreaker(name = "default")
	@GetMapping(value = "/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String, Object>>> getStatList(@RequestParam("cateCode") String cateCode,
			@RequestParam("sDate") String sDate,
			@RequestParam("eDate") String eDate) {

		List<Map<String, Object>> list = new ArrayList<>();
		list = tbDnCrwlSttRepository.findByCateCdAndSttDt(cateCode, sDate, eDate);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
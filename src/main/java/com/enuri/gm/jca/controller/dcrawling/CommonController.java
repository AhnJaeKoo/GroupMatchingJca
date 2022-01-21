package com.enuri.gm.jca.controller.dcrawling;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enuri.gm.jca.service.CommonService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "dcrawling")
@RequiredArgsConstructor
public class CommonController {

	private final CommonService commonService;

	@CircuitBreaker(name = "default")
	@GetMapping(value = "/common/cate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> getCategory() {
		return new ResponseEntity<>(commonService.getCategory(), HttpStatus.OK);
	}

	@CircuitBreaker(name = "default")
	@ResponseBody
	@RequestMapping(value = "/common/selectBoxCate")
	public ResponseEntity<String> getSelectBoxCate(@RequestParam("cateCode") String cateCode) {
		return new ResponseEntity<>(commonService.getJcaApiToCategory(cateCode), HttpStatus.OK);
	}
}
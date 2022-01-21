package com.enuri.gm.jca.controller.dcrawling.popup;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.enuri.gm.common.util.StringUtil;
import com.enuri.gm.jca.model.eloc.TblEnuriMaker;
import com.enuri.gm.jca.repository.eloc.TblEnuriMakerRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("dcrawling/popup/brndMkr")
@RequiredArgsConstructor
public class BrndMkrController {
	private final TblEnuriMakerRepository tblEnuriMakerRepository;

	@CircuitBreaker(name = "default")
	@RequestMapping
	public String brndMkrPopup() {
		return "dcrawling/popup/brndMkr";
	}

	@CircuitBreaker(name = "default")
	@GetMapping(value = "/mkrList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TblEnuriMaker>> mkrList(@RequestParam("keyword") String keyword) {
		List<TblEnuriMaker> result = new ArrayList<>();

		if (StringUtil.isNumber(keyword)) { // 숫자형이면
			result = tblEnuriMakerRepository.findByMakerIdAndDelYn(Integer.parseInt(keyword));
		} else {
			result = tblEnuriMakerRepository.findByMakerNmContainsAndDelYn("%" + keyword + "%");
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@CircuitBreaker(name = "default")
	@GetMapping(value = "/brndList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TblEnuriMaker>> brndList(@RequestParam("keyword") String keyword) {
		List<TblEnuriMaker> result = new ArrayList<>();

		if (StringUtil.isNumber(keyword)) { // 숫자형이면
			result = tblEnuriMakerRepository.findByBrandIdAndDelYn(Integer.parseInt(keyword));
		} else {
			result = tblEnuriMakerRepository.findByBrandNmContainsAndDelYn("%" + keyword + "%");
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
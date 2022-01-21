package com.enuri.gm.jca.controller.dcrawling.popup;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.enuri.gm.jca.model.eloc.GoodsAttribute;
import com.enuri.gm.jca.repository.eloc.GoodsAttributeRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("dcrawling/popup/enAttribute")
@RequiredArgsConstructor
public class EnAttributeController {
	private final GoodsAttributeRepository goodsCategoryRepository;

	@CircuitBreaker(name = "default")
	@RequestMapping
	public String enAttPopup() {
		return "dcrawling/popup/enAttribute";
	}

	@CircuitBreaker(name = "default")
	@GetMapping(value = "/enAttList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<GoodsAttribute>> enAttList(
			@RequestParam("cateCode") String cateCode,
			@RequestParam("keyword") String keyword) {

		List<GoodsAttribute> list = goodsCategoryRepository.findByCateCdStartsWithAndIdOrName(cateCode, keyword);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@CircuitBreaker(name = "default")
	@GetMapping(value = "/enAttElList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<GoodsAttribute>> enAttElList(
			@RequestParam("cateCode") String cateCode,
			@RequestParam("keyword") String keyword) {

		List<GoodsAttribute> list = goodsCategoryRepository.findByCateCdStartsWithAndElIdOrElName(cateCode, keyword);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}

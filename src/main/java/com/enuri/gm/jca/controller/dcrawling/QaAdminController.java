package com.enuri.gm.jca.controller.dcrawling;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.enuri.gm.common.util.MapUtil;
import com.enuri.gm.common.util.StringUtil;
import com.enuri.gm.jca.model.eloc.TbDnCrwlQa;
import com.enuri.gm.jca.repository.eloc.TbDnCrwlQaPlRepository;
import com.enuri.gm.jca.repository.eloc.TbDnCrwlQaRepository;
import com.enuri.gm.jca.service.DnCrawlingService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "dcrawling")
@RequiredArgsConstructor
public class QaAdminController {
	private final TbDnCrwlQaRepository tbDnCrwlQaRepository;
	private final TbDnCrwlQaPlRepository tbDnCrwlQaPlRepository;
	private final DnCrawlingService dnCrawlingService;

	@CircuitBreaker(name = "default")
	@RequestMapping("/qa-admin")
	public String qaAdmin() {
		return "dcrawling/qaAdmin";
	}

	@CircuitBreaker(name = "default")
	@GetMapping(value = "/qa-admin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Map<String, Object>>> qaList(@RequestParam("cateCode") String cateCode,
			@RequestParam("isNonAtt") boolean isNonAtt,
			@RequestParam("isNonBrndMkr") boolean isNonBrndMkr,
			@RequestParam("sortType") String sortType,
			Pageable pageable) {

		Page<Map<String, Object>> qaList = tbDnCrwlQaRepository.findByCateCdStartsWith(cateCode, isNonAtt, isNonBrndMkr, sortType, pageable);
		return new ResponseEntity<>(qaList, HttpStatus.OK);
	}

	@CircuitBreaker(name = "default")
	@GetMapping(value = "/qa-admin/id/{qaId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> qaInfo(@PathVariable("qaId") Long qaId) {
		TbDnCrwlQa tbDnCrwlQa = tbDnCrwlQaRepository.findById(qaId).get();
		Map<String, Object> map = MapUtil.ConverObjectToMap(tbDnCrwlQa);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@CircuitBreaker(name = "default")
	@GetMapping(value = "/qa-admin/plCount", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> plCount(@RequestParam("cateCode") String cateCode,
			@RequestParam("isNonAtt") boolean isNonAtt,
			@RequestParam("isNonBrndMkr") boolean isNonBrndMkr) {

		int count = tbDnCrwlQaPlRepository.countByCateCdStartsWith(cateCode, isNonAtt, isNonBrndMkr);
		return new ResponseEntity<>(count, HttpStatus.OK);
	}

	@CircuitBreaker(name = "default")
	@DeleteMapping(value = "/qa-admin/{qaIds}")
	public ResponseEntity<Long> updateUseYnByQaIds(@PathVariable("qaIds") List<Long> dnAtrIds,
			@RequestParam("userId") String userId) {

		long result = tbDnCrwlQaRepository.updateUseYnByQaIds(dnAtrIds, userId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@CircuitBreaker(name = "default")
	@PutMapping(value = "/qa-admin", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> updateQaByQaId(@RequestBody String jsonData) {
		List<Map<String, Object>> qaInfos = new ArrayList<>();
		qaInfos = StringUtil.jsonToListMap(jsonData, "qaInfos");
		long result = tbDnCrwlQaRepository.updateQaByQaId(qaInfos);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@CircuitBreaker(name = "default")
	@PostMapping(value = "/qa-admin", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> createModelByQaId(@RequestBody String jsonData) {
		List<Map<String, Object>> qaIds = new ArrayList<>();
		qaIds = StringUtil.jsonToListMap(jsonData, "qaIds");
		String userId = StringUtil.jsonToString(jsonData, "userId");
		int result = dnCrawlingService.createModelByQaId(qaIds, userId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
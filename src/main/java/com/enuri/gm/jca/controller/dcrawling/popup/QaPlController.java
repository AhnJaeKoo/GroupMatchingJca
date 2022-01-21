package com.enuri.gm.jca.controller.dcrawling.popup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.enuri.gm.common.util.StringUtil;
import com.enuri.gm.jca.repository.eloc.TbDnCrwlQaPlRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("dcrawling/popup/qaPl")
@RequiredArgsConstructor
public class QaPlController {
	private final TbDnCrwlQaPlRepository tbDnCrwlQaPlRepository;

	@CircuitBreaker(name = "default")
	@RequestMapping
	public String brndMkrPopup() {
		return "dcrawling/popup/qaPl";
	}

	@CircuitBreaker(name = "default")
	@GetMapping(value = "/list/{qaId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String, Object>>> list(@PathVariable("qaId") Long qaId) {
		List<Map<String, Object>> result = tbDnCrwlQaPlRepository.findByQaId(qaId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@CircuitBreaker(name = "default")
	@PutMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> updateQaPlByUseYn(@RequestBody String jsonData) {
		List<Map<String, Object>> qaPlList = new ArrayList<>();
		qaPlList = StringUtil.jsonToListMap(jsonData, "qaPls");
		long qaId = Long.parseLong(StringUtil.jsonToString(jsonData, "qaId"));
		String userId = StringUtil.jsonToString(jsonData, "userId");
		long result = tbDnCrwlQaPlRepository.updateByUseYn(qaPlList, qaId, userId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
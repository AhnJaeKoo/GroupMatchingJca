package com.enuri.gm.jca.controller.dcrawling;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.enuri.gm.common.util.StringUtil;
import com.enuri.gm.jca.dto.DnAttDto;
import com.enuri.gm.jca.repository.eloc.TbDnCrwlAtrEnrRepository;
import com.enuri.gm.jca.repository.eloc.TbDnCrwlAtrRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("dcrawling")
@RequiredArgsConstructor
public class DnAttributeController {
	private final TbDnCrwlAtrRepository tbDnCrwlAtrRepository;
	private final TbDnCrwlAtrEnrRepository tbDnCrwlAtrEnrRepository;

	@RequestMapping("/dnAtt-admin")
	public String dnAttAdmin() {
		return "dcrawling/attribute";
	}

	@CircuitBreaker(name = "default")
	@GetMapping(value = "/dnAtt-admin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DnAttDto>> getDnAttList(
			@RequestParam("cateCode") String cateCode,
			@RequestParam("dnAtrNm") String dnAtrNm,
			@RequestParam("enrAtrId") int enrAtrId,
			@RequestParam("enrAtrDtlId") int enrAtrDtlId) {

		List<DnAttDto> enAttList = tbDnCrwlAtrRepository.findByCateCdStartsWithAndName(cateCode, dnAtrNm, enrAtrId, enrAtrDtlId);
		return new ResponseEntity<>(enAttList, HttpStatus.OK);
	}

	@CircuitBreaker(name = "default")
	@DeleteMapping(value = "/dnAtt-admin/{dnAtrIds}")
	public ResponseEntity<Long> updateDnAttByUseYn(@PathVariable("dnAtrIds") List<Integer> dnAtrIds,
			@RequestParam("userId") String userId) {
		long result = tbDnCrwlAtrRepository.updateUseYnBydnAtrIds(dnAtrIds, userId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@CircuitBreaker(name = "default")
	@PutMapping(value = "/dnAtt-admin", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> updateDnAttByEnAttId(@RequestBody String jsonData) {
		List<Map<String, Object>> dnAtrIds = new ArrayList<>();
		dnAtrIds = StringUtil.jsonToListMap(jsonData, "dnAtrIds");
		String userId = StringUtil.jsonToString(jsonData, "userId");
		long result = tbDnCrwlAtrEnrRepository.updateEnIdByDnAtrId(dnAtrIds, userId);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}

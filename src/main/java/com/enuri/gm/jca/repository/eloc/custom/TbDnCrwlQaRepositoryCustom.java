package com.enuri.gm.jca.repository.eloc.custom;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TbDnCrwlQaRepositoryCustom {

	//@EntityGraph(attributePaths = {"models", "pricelists"})	// 즉시 그래프 탐색을 통한 트랜젝션의 지연로딩 오류 해결(N + 1)
	public Page<Map<String, Object>> findByCateCdStartsWith(String cateCd, boolean isNonAtt, boolean isNonBrndMkr, String sortType, Pageable pageable);

	/**
	  * @description : D크롤링 QA 테이블 use_yn = 'N' 처리
	  * @param : qa_id
	  * @return : 처리 건수
	  */
	public long updateUseYnByQaIds(List<Long> qaIds, String userId);

	/**
	  * @description : D크롤링 QA 테이블 정보 변경
	  * @param : 변경정보
	  * @return : 처리 건수
	  */
	public long updateQaByQaId(List<Map<String, Object>> qaInfos);
}

package com.enuri.gm.jca.repository.eloc.custom;

import java.util.List;

import com.enuri.gm.jca.dto.DnAttDto;

public interface TbDnCrwlAtrRepositoryCustom {

	/**
	  * @description : 속성정보와 속성원 정보를 조회한다.
	  * @param : 카테고리코드, 속성id/속성명
	  * @return : 속성정보 리스트
	  */
	public List<DnAttDto> findByCateCdStartsWithAndName(String category, String dnAtrNm, int enrAtrId, int enrAtrDtlId);

	/**
	  * @description : D사속성 테이블 use_yn = 'N' 처리
	  * @param : D사 속성ID
	  * @return : 처리 건수
	  */
	public long updateUseYnBydnAtrIds(List<Integer> dnAtrIds, String userId);
}

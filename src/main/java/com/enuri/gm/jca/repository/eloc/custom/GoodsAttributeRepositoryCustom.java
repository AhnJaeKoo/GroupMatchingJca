package com.enuri.gm.jca.repository.eloc.custom;

import java.util.List;

import com.enuri.gm.jca.model.eloc.GoodsAttribute;

public interface GoodsAttributeRepositoryCustom {

	/**
	  * @description : 속성정보와 속성원 정보를 조회한다.
	  * @param : 카테고리코드, 속성id/속성명
	  * @return : 속성정보 리스트
	  */
	public List<GoodsAttribute> findByCateCdStartsWithAndIdOrName(String category, String keyword);

	/**
	  * @description : 속성원 정보와 속성정보를 조회한다.
	  * @param : 카테고리코드, 속성원id/속성원명
	  * @return : 속성원정보 리스트
	  */
	public List<GoodsAttribute> findByCateCdStartsWithAndElIdOrElName(String category, String keyword);
}

package com.enuri.gm.jca.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.enuri.gm.common.business.ExKeyWord;
import com.enuri.gm.common.business.MoveCategory;
import com.enuri.gm.common.business.enums.ExFlag;

/**
  * @description : 기획팀에서 그룹매칭 안의 각종 키워드 적용 결과를 테스트 해볼수 있게 하기 위한 서비스
  * @Since : 2021. 6. 28.
  * @Author : AnJaeKoo
  * @History :
  */
@Service
public class BusinessService {
	public String getExKeyWordResult(String modelNm, String cateCode, String exFlag) {
		List<Map<String, Object>> exKeyWordList = new ArrayList<>();
		exKeyWordList = ExKeyWord.getExKeyWord(cateCode, getExFlag(exFlag));

		return switch(exFlag) {
					case "a" -> null;
					case "b" -> getMoveCategoryMatchInfo(exKeyWordList, modelNm, cateCode);
					case "c" -> null;
					case "d" -> null;
					case "g" -> null;
					default -> null;
				};
	}

	/**
	  * @description : flag별로 열거값 찾는다.
	  * @param : flag
	  * @return : 열거값
	  */
	private ExFlag getExFlag(String exFlag) {
		return switch (exFlag) {
					case "a" -> ExFlag.EX_PRICELIST;
					case "b" -> ExFlag.MOVE_CATEGORY;
					case "c" -> ExFlag.EX_INDEXER;
					case "d" -> ExFlag.PROPOTION;
					case "g" -> ExFlag.EX_BRAND_FACTORY;
					default -> null;
				};
	}


	/**
	  * @description : 이동 키워드 적용된 카테고리 찾기
	  * @param : 이동키워드 리스트, 모델명, 카테고리
	  * @return : 이동카테고리
	  */
	private String getMoveCategoryMatchInfo(List<Map<String, Object>> exKeyWordList, String modelNm, String cateCode) {
		StringBuffer buff = new StringBuffer();
		MoveCategory moveCategory = new MoveCategory(exKeyWordList);
		buff.append(moveCategory.getMatchInfoString(modelNm, cateCode));
		buff.append("\n\r 최종 카테고리 : " + moveCategory.getChangeCateCode(modelNm, cateCode));

		return buff.toString();
	}
}
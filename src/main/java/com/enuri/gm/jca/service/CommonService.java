package com.enuri.gm.jca.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.enuri.gm.jca.model.main.TblCategory;
import com.enuri.gm.jca.repository.main.TblCategoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
  * @description : 공통으로 사용될 서비스 클래스
  * @Since : 2021. 6. 17.
  * @Author : AnJaeKoo
  * @History :
  */
@Slf4j
@Service
@RequiredArgsConstructor
public class CommonService {

	private final TblCategoryRepository tblCategoryRepository;

	/**
	  * @description : 카테고리 정보 추출
	  * @param :
	  * @return : Map<cateCode, cateName>
	  */
	public Map<String, String> getCategory() {
		var map = new HashMap<String, String>();
		List<TblCategory> list = tblCategoryRepository.findBycSeqnoGreaterThan();
		list.forEach(cate -> {
			map.put(cate.getCaCode(), cate.getCName());
		});
		return map;
	}

	@Cacheable(value = "selectBoxCate", key = "#cateCode", unless="#result == \"\"")
	public String getJcaApiToCategory(String cateCode) {
		StringBuffer strbuff = new StringBuffer();
		String result = "";
		BufferedReader buffer = null;
		String urlAddress = "http://100.100.100.198:8080/jca_common/jca_jsp/GetCategory.jsp?cate=" + cateCode;

		try {
			URL url = new URL(urlAddress); // 호출할 url
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			buffer = new BufferedReader(new InputStreamReader(con.getInputStream(), "EUCKR"));

			String data;
			while ((data = buffer.readLine()) != null) { // response를 차례대로 출력
				strbuff.append(data);
			}

		} catch (IOException e) {
			log.error("urlAddress = {}", urlAddress, e);
		}

		result = strbuff.toString();
		log.info("getCateCode = {}", result);

		return result;
	}
}
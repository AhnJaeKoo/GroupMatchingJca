package com.enuri.gm.jca.repository.eloc.custom;

import java.util.List;
import java.util.Map;

public interface TbDnCrwlSttRepositoryCustom {

	public List<Map<String, Object>> findByCateCdAndSttDt(String cateCd, String sDate, String eDate);
}

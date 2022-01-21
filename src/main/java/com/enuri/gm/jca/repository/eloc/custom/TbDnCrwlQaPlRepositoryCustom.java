package com.enuri.gm.jca.repository.eloc.custom;

import java.util.List;
import java.util.Map;

public interface TbDnCrwlQaPlRepositoryCustom {

	public int countByCateCdStartsWith(String cateCd, boolean isNonAtt, boolean isNonBrndMkr);

	public long updateByUseYn(List<Map<String, Object>> list, long qaId, String userId);
}
